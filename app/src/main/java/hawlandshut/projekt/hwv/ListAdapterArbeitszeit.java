package hawlandshut.projekt.hwv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ListAdapterArbeitszeit extends BaseAdapter{

    private ArrayList<Arbeitszeit> arbeitszeitList;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;

    private SharedPreferences prefs;

    public ListAdapterArbeitszeit(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);

        initArbeitszeitListe();
    }

    public void setArbeitszeitList(ArrayList<Arbeitszeit> _newAufmass){

        if(_newAufmass != null)
            arbeitszeitList = _newAufmass;
    }

    private void initArbeitszeitListe(){
        arbeitszeitList = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return arbeitszeitList.size();
    }

    @Override
    public Object getItem(int position) {
        return arbeitszeitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.arbeitszeit_row,parent,false);
        TextView worker = (TextView)v.findViewById(R.id.arbeitsZeitRow_Worker_textView);
        TextView start = (TextView)v.findViewById(R.id.arbeitsZeitRow_Start_textView);
        TextView end = (TextView)v.findViewById(R.id.arbeitsZeitRow_End_textView);
        TextView minutes = (TextView)v.findViewById(R.id.arbeitsZeitRow_Minutes_textView);


        if(arbeitszeitList != null)
        {
            //getDateTimeInstance();

            DateFormat formatter = new SimpleDateFormat();
            //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy hh:mm");

            worker.setText(arbeitszeitList.get(position).getWorker().getKuerzel());
            start.setText(formatter.format(arbeitszeitList.get(position).getStartTime().getTime()));
            if(arbeitszeitList.get(position).isEnd())
            {
                end.setText(formatter.format(arbeitszeitList.get(position).getEndTime().getTime()));
            }else{
                end.setText("");
            }
            double minutesDouble = arbeitszeitList.get(position).secondsWorked();
            minutesDouble = minutesDouble/60.0;
            minutes.setText(String.format("%.2f",minutesDouble));
        }

        return v;
    }

}