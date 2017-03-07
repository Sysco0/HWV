package hawlandshut.projekt.hwv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapterAufmass extends BaseAdapter{

    private ArrayList<Artikel> aufmassList;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;

    private SharedPreferences prefs;

    public ListAdapterAufmass(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);

        initAuftragsListe();
    }

    public void setAufmassList(ArrayList<Artikel> _newAufmass){

        if(_newAufmass != null)
            aufmassList = _newAufmass;
    }

    private void initAuftragsListe(){
        aufmassList = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return aufmassList.size();
    }

    @Override
    public Object getItem(int position) {
        return aufmassList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.aufmass_row,parent,false);
        TextView artname = (TextView)v.findViewById(R.id.aufmassRow_Artikelname_textView);
        TextView barcode = (TextView)v.findViewById(R.id.aufmassRow_Barcode_textView);
        TextView stdvpe = (TextView)v.findViewById(R.id.aufmassRow_stdVPE_textView);
        TextView einheit = (TextView)v.findViewById(R.id.aufmassRow_Einheit_textView);


        artname.setText(aufmassList.get(position).getName());
        barcode.setText(aufmassList.get(position).getBarcodeID());
        stdvpe.setText(aufmassList.get(position).getStandardVPE());
        einheit.setText(aufmassList.get(position).getEinheit());

        return v;
    }

}