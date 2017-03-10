package hawlandshut.projekt.hwv.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import hawlandshut.projekt.hwv.AsyncResponse;
import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.adpater.ListTaskAdapter;
import hawlandshut.projekt.hwv.db.helper.DBHelper;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBAddress;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskArticle;
import hawlandshut.projekt.hwv.db.resource.repository.AddressRepository;
import hawlandshut.projekt.hwv.db.resource.repository.ArticleRepository;
import hawlandshut.projekt.hwv.db.resource.repository.CustomerRepository;
import hawlandshut.projekt.hwv.db.resource.repository.TaskRepository;
import hawlandshut.projekt.hwv.enums.Gender;
import hawlandshut.projekt.hwv.response.ArticleResponse;
import hawlandshut.projekt.hwv.response.TaskResponse;
import hawlandshut.projekt.hwv.response.convert.AddressConverter;
import hawlandshut.projekt.hwv.response.convert.ArticleConverter;
import hawlandshut.projekt.hwv.response.convert.CustomerConverter;
import hawlandshut.projekt.hwv.response.convert.TaskConverter;
import hawlandshut.projekt.hwv.response.pojo.ArticleElement;
import hawlandshut.projekt.hwv.response.pojo.TaskElement;
import hawlandshut.projekt.hwv.util.StringUtil;


public class MainActivity extends AppCompatActivity implements AsyncResponse<List<DBTask>>, AdapterView.OnItemClickListener {

    private ListView listViewTasks;
    private Button btnActivateJob;
    private Toolbar toolbar;
    private SharedPreferences sharedPref;
    private ListTaskAdapter listTaskAdapter;
    private DBTask task;
    private Context context;

    @Override
    public void onResume() {
        super.onResume();
        // This registers mMessageReceiver to receive messages.
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,
                        new IntentFilter("hwv-notify"));
    }

    // Handling the received Intents for the "my-integer" event
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String message = intent.getStringExtra("message");
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,
                        new IntentFilter("hwv-notify"));

        context = this;
       // Initialize database connection
        DBHelper.getInstance(this.getApplicationContext(), "HWV_DB")
                .addResources(DBArticle.class)
                .addResources(DBTask.class)
                .addResources(DBCustomer.class)
                .addResources(DBAddress.class)
                .addResources(DBTaskArticle.class);

        //Download stuff
        new JSONDataUpdater<>(this).execute();
        //Load shared preferences
        sharedPref = getSharedPreferences(Variables.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        //Init view components
        setContentView(R.layout.activity_active_job);
        listViewTasks = (ListView) findViewById(R.id.listViewAuftrag);
        btnActivateJob = (Button) findViewById(R.id.startJobButton);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);


        ListTaskAdapter myListAdapterAuftrag = new ListTaskAdapter(getApplicationContext(), this);

        //add listners
        btnActivateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toJobActivity = new Intent(getApplicationContext(), JobActivity.class);
                toJobActivity.putExtra(Variables.ACTIVE_TASK_ID, task.getTaskId());
                startActivity(toJobActivity);
            }
        });

        listViewTasks.setAdapter(myListAdapterAuftrag);
        listViewTasks.setOnItemClickListener(this);
        listTaskAdapter = (ListTaskAdapter) listViewTasks.getAdapter();


        //init action bar
        setSupportActionBar(toolbar);
        ActionBar myBar = getSupportActionBar();
        if (myBar != null) {
            myBar.setTitle(Html.fromHtml("<b><font color='#FFFFFF'>Auftragsliste</font></b>"));
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
         if (id == R.id.action_refresh) {
             return true;
         }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processFinish(List<DBTask> tasks) {
        listTaskAdapter.updateData(tasks);
        listTaskAdapter.notifyDataSetChanged();
        //Choose item 1
        if (listTaskAdapter.getCount() != 0) {
            //May cause a Problem if no Jobs are available
            listViewTasks.performItemClick(listViewTasks.getChildAt(0), 0, listViewTasks.getFirstVisiblePosition());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Do smthg.
        ((ListTaskAdapter) parent.getAdapter()).setSelectedItem(position);
        ((ListTaskAdapter) parent.getAdapter()).notifyDataSetChanged();

        task = (DBTask) parent.getAdapter().getItem(position);
        DBCustomer dbCustomer = CustomerRepository.getInstance().getByCustomerId(task.getCustomerId());
        DBAddress dbAddress = AddressRepository.getInstance().getByAddressId(dbCustomer.getAddressId());

        if(task != null)
        {
            Button startJobButton = (Button) findViewById(R.id.startJobButton);
            startJobButton.setEnabled(true);
        }

        TextView viewCustomer = (TextView) findViewById(R.id.showJob_Anrede_textView);
        TextView viewPlace = (TextView) findViewById(R.id.showJob_Plz_Ort_textView);
        TextView viewStreet = (TextView) findViewById(R.id.showJob_Strasse_textView);
        TextView viewExtension = (TextView) findViewById(R.id.showJob_Zone_textView);
        TextView viewDescription = (TextView) findViewById(R.id.showJob_Beschreibung_textView);

        viewCustomer.setText((dbCustomer.getGender() == Gender.Male ? "Herr" : "Frau") + " " + dbCustomer.getFirstName() + " " + dbCustomer.getLastName());
        viewPlace.setText(dbAddress.getZipcode() + " " + dbAddress.getPlace()); //evtl unschön
        viewStreet.setText(dbAddress.getStreet());
        viewExtension.setText(StringUtil.ensure(dbAddress.getExtension()));//dito
        viewDescription.setText(StringUtil.ensure(task.getDescription()));
        viewDescription.setMovementMethod(new ScrollingMovementMethod());
    }



    public class JSONDataUpdater<T> extends AsyncTask<String, String, Boolean> {

        private final AsyncResponse<T> delegate;
        private final Gson jsonParser = new Gson();

        JSONDataUpdater(AsyncResponse<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            TaskRepository taskRepository = TaskRepository.getInstance();
            CustomerRepository customerRepository = CustomerRepository.getInstance();
            AddressRepository addressRepository = AddressRepository.getInstance();
            ArticleRepository articleRepository = ArticleRepository.getInstance();

            try {
                String jsonTaskResponse = getJson("https://hwv.mad2man.com/api/task");
                String jsonArticleResponse = getJson("https://hwv.mad2man.com/api/article");

                TaskResponse taskResponse = jsonParser.fromJson(jsonTaskResponse, TaskResponse.class);
                ArticleResponse articleResponse = jsonParser.fromJson(jsonArticleResponse, ArticleResponse.class);

                if (taskResponse.isSuccess() && articleResponse.isSuccess()) {

                    //delete not needed data here
                    taskRepository.deleteAll();
                    customerRepository.deleteAll();
                    addressRepository.deleteAll();
                    articleRepository.deleteAll();

                    //save to database
                    for (TaskElement task : taskResponse.getTasks()) {
                        taskRepository.create(TaskConverter.convert(task));
                        customerRepository.create(CustomerConverter.convert(task.getCustomer()));
                        addressRepository.create(AddressConverter.convert(task.getCustomer().getAddress()));
                    }

                    for (ArticleElement article : articleResponse.getArticles()) {
                        articleRepository.create(ArticleConverter.convert(article));
                    }
                }


            } catch (Exception e) {
                Log.e("Error getting data", e.getLocalizedMessage());
                return false;
            }
            return true;
        }


        private String getJson(String strUrl) throws IOException {
            StringBuilder result = new StringBuilder();
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }


        // Handling the received Intents for the "my-integer" event
        private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Extract data included in the Intent
                int yourInteger = intent.getIntExtra("message",-1/*default value*/);
            }
        };

    }
}
