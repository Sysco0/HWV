package hawlandshut.projekt.hwv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListAdapterAuftrag extends BaseAdapter{

    private ArrayList<Auftrag> auftragsliste;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;
    private int selected;
    private SharedPreferences prefs;

    public ListAdapterAuftrag(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        initAuftragsListe();
    }

    public void updateData(){
        auftragsliste = new ArrayList<>();
        String jsonStr = prefs.getString("jobdata", "");
        if(!jsonStr.isEmpty())
        {
            Type listofAuftrag = new TypeToken<ArrayList<Auftrag>>(){}.getType();

            //String s = gson.toJson(list, listOfTestObject);
            Gson gson = new GsonBuilder().create();

            auftragsliste = gson.fromJson(jsonStr, listofAuftrag);

        }

    }

    private void initAuftragsListe(){
        updateData();
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
        TextView nachn = (TextView)v.findViewById(R.id.AuftragRow_nachname_textView);
        TextView vorn = (TextView)v.findViewById(R.id.AuftragRow_vorname_textView);
        TextView status = (TextView)v.findViewById(R.id.AuftragRow_status_textView);
        TextView besch = (TextView)v.findViewById(R.id.beschreibung);


        nachn.setText(auftragsliste.get(position).getKunde().getName());
        vorn.setText(auftragsliste.get(position).getKunde().getVorname());
        String statusText = "Status: "+ auftragsliste.get(position).getAuftrStat();
        status.setText(statusText);

        besch.setText(auftragsliste.get(position).getBeschreibung());

        if(selected == position)
        {
            v.setBackgroundColor(mainActivity.getResources().getColor(R.color.backgroundSelected));
        }

        return v;
    }

    public void setSelectedItem(int position)
    {
        selected = position;
    }

}