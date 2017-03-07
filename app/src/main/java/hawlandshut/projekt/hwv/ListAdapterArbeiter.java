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

public class ListAdapterArbeiter extends BaseAdapter{

    private ArrayList<Mitarbeiter> workerList;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;
    private int selected;
    private SharedPreferences prefs;

    public ListAdapterArbeiter(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        initAuftragsListe();
    }

    public void updateData(){
        workerList = new ArrayList<>();
        workerList.add(new Mitarbeiter(1,"Sepp","Herberger","SeppH"));
        workerList.add(new Mitarbeiter(2,"Hans","Dämpfinger","HansD"));
        workerList.add(new Mitarbeiter(3,"Josef","Vollgereat","JosefV"));
        workerList.add(new Mitarbeiter(4,"No","Oana","NoO"));
        workerList.add(new Mitarbeiter(5,"Sepp","Herberger","SeppH"));
        workerList.add(new Mitarbeiter(6,"Hans","Dämpfinger","HansD"));
        workerList.add(new Mitarbeiter(7,"Josef","Vollgereat","JosefV"));
        workerList.add(new Mitarbeiter(8,"No","Oana","NoO"));
        workerList.add(new Mitarbeiter(9,"Sepp","Herberger","SeppH"));
        workerList.add(new Mitarbeiter(10,"Hans","Dämpfinger","HansD"));
        workerList.add(new Mitarbeiter(11,"Josef","Vollgereat","JosefV"));
        workerList.add(new Mitarbeiter(12,"No","Oana","NoO"));

    }

    private void initAuftragsListe(){
        updateData();
    }



    @Override
    public int getCount() {
        return workerList.size();
    }

    @Override
    public Object getItem(int position) {
        return workerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.worker_row,parent,false);
        TextView nachn = (TextView)v.findViewById(R.id.workerRow_nachname_textView);
        TextView vorn = (TextView)v.findViewById(R.id.workerRow_vorname_textView);
        TextView id = (TextView)v.findViewById(R.id.workerrow_id_textView);


        nachn.setText(workerList.get(position).getName());
        vorn.setText(workerList.get(position).getVorname());
        String statusText = "ID: "+ workerList.get(position).getId();
        id.setText(statusText);


        if(selected == position)
        {
            v.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorChosenItem));
        }

        return v;
    }

    public void setSelectedItem(int position)
    {
        selected = position;
    }


}