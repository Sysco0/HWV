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
import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;
import hawlandshut.projekt.hwv.db.resource.repository.CustomerRepository;

public class ListTaskAdapter extends BaseAdapter {

    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;
    private List<DBTask> tasks;
    private int selected;
    private SharedPreferences prefs;

    public ListTaskAdapter(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        tasks = new ArrayList<>();
    }

    public void updateData(List<DBTask> dbTasks) {
        tasks = dbTasks;

    }


    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = mInflater.inflate(R.layout.auftrag_row, parent, false);
        TextView nachn = (TextView) v.findViewById(R.id.AuftragRow_nachname_textView);
        TextView vorn = (TextView) v.findViewById(R.id.AuftragRow_vorname_textView);
        TextView status = (TextView) v.findViewById(R.id.AuftragRow_status_textView);
        TextView besch = (TextView) v.findViewById(R.id.beschreibung);

        DBTask task = tasks.get(position);
        DBCustomer customer = CustomerRepository.getInstance().getByCustomerId(task.getCustomerId());
        nachn.setText(customer.getFirstName());
        vorn.setText(customer.getLastName());
        String statusText = "Status: " + task.getState();
        status.setText(statusText);

        besch.setText(task.getDescription());

        if (selected == position) {
            v.setBackgroundColor(mainActivity.getResources().getColor(R.color.backgroundSelected));
        }

        return v;
    }

    public void setSelectedItem(int position) {
        selected = position;
    }

}