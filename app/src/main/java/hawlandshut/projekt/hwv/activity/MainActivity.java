package hawlandshut.projekt.hwv.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import hawlandshut.projekt.hwv.AsyncResponse;
import hawlandshut.projekt.hwv.ListAdapterAuftrag;
import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.db.helper.DBHelper;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBAddress;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;
import hawlandshut.projekt.hwv.db.resource.repository.AddressRepository;
import hawlandshut.projekt.hwv.db.resource.repository.CustomerRepository;
import hawlandshut.projekt.hwv.db.resource.repository.TaskRepository;
import hawlandshut.projekt.hwv.enums.Gender;
import hawlandshut.projekt.hwv.response.TaskResponse;
import hawlandshut.projekt.hwv.response.convert.AddressConverter;
import hawlandshut.projekt.hwv.response.convert.CustomerConverter;
import hawlandshut.projekt.hwv.response.convert.TaskConverter;
import hawlandshut.projekt.hwv.response.pojo.TaskElement;


public class MainActivity extends AppCompatActivity implements AsyncResponse<List<DBTask>>, AdapterView.OnItemClickListener {

    JSONDataUpdater jsonDataUpdater = new JSONDataUpdater();
    ListView AuftragList;
    SharedPreferences sharedPref;
    DBTask activeJob;
    Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_job);
        sharedPref = getSharedPreferences(
                "hawlandshut.projekt.hwv", Context.MODE_PRIVATE);


        AuftragList = (ListView) findViewById(R.id.listViewAuftrag);
        ListAdapterAuftrag myListAdapterAuftrag = new ListAdapterAuftrag(getApplicationContext(), this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        jsonDataUpdater.delegate = this;
        jsonDataUpdater.execute();

        ActionBar myBar = getSupportActionBar();
        if (myBar != null) {
            myBar.setTitle(Html.fromHtml("<b><font color='#FFFFFF'>Auftragsliste</font></b>"));
        }

        AuftragList.setAdapter(myListAdapterAuftrag);
        AuftragList.setOnItemClickListener(this);

        Button startJobButton = (Button) findViewById(R.id.startJobButton);
        startJobButton.setEnabled(false);
        startJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toJobActivity = new Intent(getApplicationContext(), JobActivity.class);
                //toJobActivity.putExtra("activeJob", activeJob);
                startActivity(toJobActivity);
            }
        });

        /**
         * Initialize database connection
         */
        DBHelper.getInstance(this.getApplicationContext(), "HWV_DB")
                .addResources(DBArticle.class)
                .addResources(DBTask.class)
                .addResources(DBCustomer.class)
                .addResources(DBAddress.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_active_job, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //     return true;
        // }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void processFinish(List<DBTask> tasks) {
        ListAdapterAuftrag adapter = (ListAdapterAuftrag) AuftragList.getAdapter();
        adapter.updateData(tasks);
        adapter.notifyDataSetChanged();
        //Choose item 1

        if (adapter.getCount() != 0) {//May cause a Problem if no Jobs are available
            AuftragList.performItemClick(AuftragList.getChildAt(0), 0, AuftragList.getFirstVisiblePosition());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Do smthg.
        ((ListAdapterAuftrag) parent.getAdapter()).setSelectedItem(position);
        ((ListAdapterAuftrag) parent.getAdapter()).notifyDataSetChanged();

        activeJob = (DBTask) parent.getAdapter().getItem(position);
        DBCustomer dbCustomer = CustomerRepository.getInstance().getByCustomerId(activeJob.getCustomerId());
        DBAddress dbAddress = AddressRepository.getInstance().getByAddressId(dbCustomer.getAddressId());

        if(activeJob != null)
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
        viewExtension.setText(ensure(dbAddress.getExtension()));//dito
        viewDescription.setText(ensure(activeJob.getDescription()));
        viewDescription.setMovementMethod(new ScrollingMovementMethod());
    }

    private String ensure(String str){
        if(null == str){return "";}
        return str;
    }

    public class JSONDataUpdater extends AsyncTask<String, String, String> {

        public AsyncResponse<List<DBTask>> delegate = null;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {
                //  URL url = new URL("http://www.wasserwacht-eggenfelden.de/docs/test.json");
                URL url = new URL("https://hwv.mad2man.com/api/task");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Gson gson = new Gson();
                TaskResponse response = gson.fromJson(result.toString(), TaskResponse.class);
                TaskRepository taskRepository = TaskRepository.getInstance();
                CustomerRepository customerRepository = CustomerRepository.getInstance();
                AddressRepository addressRepository = AddressRepository.getInstance();
                taskRepository.deleteAll();
                customerRepository.deleteAll();
                addressRepository.deleteAll();
                // if (response.isSuccess()) {
                //save to database
                for (TaskElement task : response.getTasks()) {
                    taskRepository.create(TaskConverter.convert(task));
                    customerRepository.create(CustomerConverter.convert(task.getCustomer()));
                    addressRepository.create(AddressConverter.convert(task.getCustomer().getAddress()));
                }
                //  }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            delegate.processFinish(TaskRepository.getInstance().list());
        }
    }
}
