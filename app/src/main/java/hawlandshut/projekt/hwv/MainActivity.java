package hawlandshut.projekt.hwv;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener{

    JSONDataUpdater jsonDataUpdater = new JSONDataUpdater();
    ListView AuftragList;
    SharedPreferences sharedPref;
    Context mainContext;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
          mainContext = this;
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_active_job);
          sharedPref = getSharedPreferences(
                  "hawlandshut.projekt.hwv", Context.MODE_PRIVATE);


          AuftragList = (ListView)findViewById(R.id.listViewAuftrag);
          ListAdapterAuftrag myListAdapterAuftrag = new ListAdapterAuftrag(getApplicationContext(),this);

          Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
          setSupportActionBar(myToolbar);

          jsonDataUpdater.delegate = this;
          jsonDataUpdater.execute();

          ActionBar myBar = getSupportActionBar();
          if(myBar != null){
              myBar.setTitle("Auftragsliste");
          }

          AuftragList.setAdapter(myListAdapterAuftrag);
          AuftragList.setOnItemClickListener(this);




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
    public void processFinish(String output) {
        ListAdapterAuftrag adapter = (ListAdapterAuftrag)AuftragList.getAdapter();
        adapter.updateData();
        adapter.notifyDataSetChanged();
        //Choose item 1

        if(adapter.getCount() != 0)
        {
            AuftragList.performItemClick(AuftragList.getChildAt(0),0,AuftragList.getFirstVisiblePosition());
        }


        //May cause a Problem if no Jobs are available
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Do smthg.
        ((ListAdapterAuftrag) parent.getAdapter()).setSelectedItem(position);
        ((ListAdapterAuftrag) parent.getAdapter()).notifyDataSetChanged();

        Auftrag activeJob = (Auftrag) parent.getAdapter().getItem(position);
        TextView anrede = (TextView)findViewById(R.id.showJob_Anrede_textView);
        anrede.setText(activeJob.getKunde().getAnrede());
        TextView nname = (TextView)findViewById(R.id.showJob_Nachname_textView);
        nname.setText(activeJob.getKunde().getName());
        TextView vname = (TextView)findViewById(R.id.showJob_Vorname_textView);
        vname.setText(activeJob.getKunde().getVorname());
        TextView plzOrt = (TextView)findViewById(R.id.showJob_Plz_Ort_textView);
        plzOrt.setText(activeJob.getKunde().getPlz() + " " + activeJob.getKunde().getOrt()); //evtl unschön
        TextView strasse = (TextView)findViewById(R.id.showJob_Strasse_textView);
        strasse.setText(activeJob.getKunde().getAdresse());
        TextView zone = (TextView)findViewById(R.id.showJob_Zone_textView);
        zone.setText("Zone" + activeJob.getKunde().getZone());//dito


    }

    public class JSONDataUpdater extends AsyncTask<String, String, String> {

        public AsyncResponse delegate = null;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://www.wasserwacht-eggenfelden.de/docs/test.txt");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("jobdata", result);
            editor.apply();
            //Do something with the JSON string



            delegate.processFinish(result);

        }
    }
}
