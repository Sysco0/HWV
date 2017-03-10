package hawlandshut.projekt.hwv.adpater;

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
import java.util.Date;
import java.util.List;

import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.activity.Variables;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskWorker;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBWorker;
import hawlandshut.projekt.hwv.db.resource.repository.TaskWorkerRepository;
import hawlandshut.projekt.hwv.db.resource.repository.WorkerRepository;

public class ListWorkeRangeAdapter extends BaseAdapter {

    private List<DBTaskWorker> workRange;
    private Long taskId;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;

    private SharedPreferences prefs;

    public ListWorkeRangeAdapter(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        taskId = prefs.getLong(Variables.ACTIVE_TASK_ID, -1);
        setWorkTime(TaskWorkerRepository.getInstance().getByTaskId(taskId));
    }

    public void setWorkTime(List<DBTaskWorker> _newWorkRange){

        if(_newWorkRange != null)
            workRange = _newWorkRange;
    }
    @Override
    public int getCount() {
        return workRange.size();
    }

    @Override
    public Object getItem(int position) {
        return workRange.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.arbeitszeit_row, parent, false);
        TextView worker = (TextView) v.findViewById(R.id.arbeitsZeitRow_Worker_textView);
        TextView start = (TextView) v.findViewById(R.id.arbeitsZeitRow_Start_textView);
        TextView end = (TextView) v.findViewById(R.id.arbeitsZeitRow_End_textView);
        TextView minutes = (TextView) v.findViewById(R.id.arbeitsZeitRow_Minutes_textView);


        if (null != this.workRange) {
            //getDateTimeInstance();
            DBTaskWorker dbTaskWorker = workRange.get(position);
            DBWorker dbWorker = WorkerRepository.getInstance().getByWorkerId(dbTaskWorker.getWorkerId());

            DateFormat formatter = new SimpleDateFormat();
            //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy hh:mm");

            worker.setText(String.format("%s %s", dbWorker.getFirstName(), dbWorker.getLastName()));
            start.setText(formatter.format(new Date(dbTaskWorker.getStartTime()).getTime()));
            long milliSeconds = 0;
            if(null != dbTaskWorker.getEndTime()) {
                end.setText(formatter.format((new Date(dbTaskWorker.getEndTime()).getTime())));
                milliSeconds = dbTaskWorker.getEndTime() - dbTaskWorker.getStartTime();
            }else {
                milliSeconds = System.currentTimeMillis() - dbTaskWorker.getStartTime();
            }
            minutes.setText(String.format("%.2f", milliSeconds/(1000d * 60d)));
        }

        return v;
    }

}