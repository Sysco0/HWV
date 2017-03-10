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
import hawlandshut.projekt.hwv.activity.Variables;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskArticle;
import hawlandshut.projekt.hwv.db.resource.repository.ArticleRepository;

public class ListTaskArticleAdapter extends BaseAdapter{

    private List<DBTaskArticle> dbTaskArticles;
    private final Context ctx;
    private final Activity mainActivity;
    private final LayoutInflater mInflater;
    private Long taskId;

    private SharedPreferences prefs;

    public ListTaskArticleAdapter(Context context, Activity act) {
        ctx = context;
        mainActivity = act;
        mInflater = LayoutInflater.from(ctx);
        prefs = ctx.getSharedPreferences("hawlandshut.projekt.hwv", Context.MODE_PRIVATE);
        taskId = prefs.getLong(Variables.ACTIVE_TASK_ID,-1);

        initAufmassListe();
    }

    public void setAufmassList(List<DBTaskArticle> _newAufmass){

        if(_newAufmass != null)
            dbTaskArticles = _newAufmass;
    }

    private void initAufmassListe(){
        dbTaskArticles = new ArrayList<>();
    }



    @Override
    public int getCount() {
        return dbTaskArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return dbTaskArticles.get(position);
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
        TextView anzahl = (TextView)v.findViewById(R.id.aufmassRow_Anzahl_textView);


        if(dbTaskArticles != null)
        {

            DBTaskArticle dbTaskArticle = dbTaskArticles.get(position);
            DBArticle article = ArticleRepository.getInstance().getByArticleId(dbTaskArticle.getArticleId());
            artname.setText(article.getName());
            barcode.setText(article.getBarcodeId());
            stdvpe.setText(Integer.toString(article.getMinOrderAmount()));
            einheit.setText(article.getUnit().getName());
            anzahl.setText(Long.toString(dbTaskArticle.getAmount()));
        }
        return v;
    }

}