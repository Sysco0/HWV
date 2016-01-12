package hawlandshut.projekt.hwv;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements AsyncResponse{

    JSONDataUpdater jsonDataUpdater = new JSONDataUpdater();
    ListView AuftragList;
    SharedPreferences sharedPref;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_active_job);
          sharedPref = getSharedPreferences(
                  "hawlandshut.projekt.hwv", Context.MODE_PRIVATE);


          AuftragList = (ListView)findViewById(R.id.listViewAuftrag);
          ListAdapterAuftrag myListAdapterAuftrag = new ListAdapterAuftrag(getApplicationContext());

          Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
          setSupportActionBar(myToolbar);

          jsonDataUpdater.delegate = this;
          jsonDataUpdater.execute();

        ActionBar myBar = getSupportActionBar();
        myBar.setTitle("Mögliche Aufträge");

        AuftragList.setAdapter(myListAdapterAuftrag);

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
    }

    public class JSONDataUpdater extends AsyncTask<String, String, String> {

        public AsyncResponse delegate = null;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://api.androidhive.info/contacts/");
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
