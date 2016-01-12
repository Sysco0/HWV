package hawlandshut.projekt.hwv;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.PreferenceChangeEvent;

public class ListAdapterAuftrag extends BaseAdapter implements AdapterView.OnItemClickListener{

    private ArrayList<Auftrag> auftragsliste;
    private final Context ctx;
    private final LayoutInflater mInflater;
    private SharedPreferences prefs;

    public ListAdapterAuftrag(Context context) {
        ctx = context;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv",Context.MODE_PRIVATE);
        initAuftragsListe();
    }

    public void updateData(){
        auftragsliste = new ArrayList<>();
        String jsonStr = prefs.getString("jobdata","");
        Log.d("JSON:", jsonStr);
        //TODO: Check on success
        try {
            JSONObject jsonRootObj = new JSONObject(jsonStr);

            //Extract Array of Entrys
            JSONArray contacts = jsonRootObj.getJSONArray("contacts");

            for( int i = 0; i < contacts.length(); i++){
                JSONObject c = contacts.getJSONObject(i);
                auftragsliste.add(new Auftrag(i, c.getString("name"),"",c.getString("email"),new Date(),new Date()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initAuftragsListe(){
        updateData();

        /*
        auftragsliste.add(new Auftrag(2, "Gruber", "Jeppo", "Hagl hi macha", new Date(), new Date()));
        auftragsliste.add(new Auftrag(3, "Hackl", "Schorsch", "Heizung einbauen", new Date(), new Date()));
        auftragsliste.add(new Auftrag(4, "Meier", "Hans", "Rohrbruch", new Date(), new Date()));
        auftragsliste.add(new Auftrag(5, "Müller", "August", "Woas da Henker", new Date(), new Date()));
        auftragsliste.add(new Auftrag(6, "Sandler", "Dandler", "Alles Kalt", new Date(), new Date()));
        */
    }



    @Override
    public int getCount() {
        return auftragsliste.size();
    }

    @Override
    public Object getItem(int position) {
        return auftragsliste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.auftrag_row,parent,false);
        TextView nachn = (TextView)v.findViewById(R.id.nachname);
        TextView vorn = (TextView)v.findViewById(R.id.vorname);
        TextView besch = (TextView)v.findViewById(R.id.beschreibung);

        nachn.setText(auftragsliste.get(position).getName());
        vorn.setText(auftragsliste.get(position).getVorname());
        besch.setText(auftragsliste.get(position).getBeschreibung());

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Do smthg.
    }
}