package hawlandshut.projekt.hwv.adpater;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.activity.JobActivity;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBWorker;
import hawlandshut.projekt.hwv.db.resource.repository.WorkerRepository;

public class ListWorkerAdapter extends BaseAdapter{

    private List<DBWorker> workerList;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;

    public void setWorkingPositions(ArrayList<Integer> workingPositions) {
        this.workingPositions = workingPositions;
    }

    private ArrayList<Integer> workingPositions = new ArrayList<>();
    private SharedPreferences prefs;

    public ListWorkerAdapter(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        initAuftragsListe();
    }

    public void updateData(){
        workerList = WorkerRepository.getInstance().list();


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


        DBWorker worker = workerList.get(position);

        nachn.setText(worker.getFirstName());
        vorn.setText(worker.getLastName());
        String statusText = "ID: "+ worker.getWorkerId();
        id.setText(statusText);

        workingPositions = ((JobActivity)mainActivity).getWorkingPositions();

        if(workingPositions.contains(position))
        {
            v.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorChosenItem));
        }

        return v;
    }




}