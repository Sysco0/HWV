package hawlandshut.projekt.hwv;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class JobActivity extends AppCompatActivity
        implements
        ChooseWorkerFragment.OnFragmentInteractionListener,
        AufmassFragement.OnFragmentInteractionListener,
        OnNewFragmentCreatedCallback,
        View.OnClickListener {

    protected String lastScan = "";
    private View aufmassTxtView;
    private RunningWork activeWork = new RunningWork();
    private Auftrag activeJob;
    private Mitarbeiter activeWorker;

    public ArrayList<Integer> getWorkingPositions() {
        return workingPositions;
    }

    private ArrayList<Integer> workingPositions = new ArrayList<>();


    protected void setActiveFragment(int fragmentNr)
    {
        RelativeLayout arbeiterBG = (RelativeLayout)findViewById(R.id.chooseArbeiterBG);
        RelativeLayout aufmassBG = (RelativeLayout)findViewById(R.id.chooseAufmassBG);
        RelativeLayout barcodeButtonBG = (RelativeLayout)findViewById(R.id.barcodeScannerButtonBG);

        switch (fragmentNr){
            case 1:
                arbeiterBG.setBackgroundColor(getColor(R.color.backgroundSelected));
                aufmassBG.setBackgroundColor(getColor(R.color.backgroundGrey));
                break;
            case 2:
                aufmassBG.setBackgroundColor(getColor(R.color.backgroundSelected));
                arbeiterBG.setBackgroundColor(getColor(R.color.backgroundGrey));
                break;
            default:
                break;
        }
    }

    protected void setWorker(Mitarbeiter arbeiter)
    {
        TextView workerName = (TextView)findViewById(R.id.WorkerName);
        workerName.setText( arbeiter.getKuerzel());
        activeWorker = arbeiter;
        activeWork.setNewArbeitsZeit(arbeiter);//TODO: REMOVE
        ListView az = (ListView)findViewById(R.id.recordedWork);
        ((ListAdapterArbeitszeit)az.getAdapter()).setArbeitszeitList(activeWork.getWorkedHours());
        ((ListAdapterArbeitszeit)az.getAdapter()).notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        //aufmassList = new ArrayList<>();
        activeWork.setAufmass(new ArrayList<AufmassArtikel>());

        Intent intent = getIntent();
        activeJob = intent.getParcelableExtra("activeJob");
        activeWork.setJob(activeJob);

        //Delete ArticleDatabase
        deleteArticleDatabase();
        //Load Article Database
        addTestRowsToDB();

        TextView jobID = (TextView)findViewById(R.id.textViewActiveJob);
        jobID.setText(activeWork.getJob().getKunde().getVorname() + " " + activeWork.getJob().getKunde().getName());

        TextView Arbeiter = (TextView)findViewById(R.id.chooseArbeiter);
        TextView Aufmass = (TextView)findViewById(R.id.chooseAufmass);
        TextView BarcodeButton = (TextView)findViewById(R.id.barcodeScannerButton);



        Arbeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                ChooseWorkerFragment newFragment = ChooseWorkerFragment.newInstance();
                Bundle args = new Bundle();
                //args.putInt(ChooseWorkerFragment.ARG_POSITION, position); NOT NEEDED NOW
                newFragment.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

                setActiveFragment(1);
            }
        });

        Aufmass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                AufmassFragement newFragment = AufmassFragement.newInstance(JobActivity.this);
                Bundle args = new Bundle();
                //args.putInt(AufmassFragement.ARG_POSITION, position); NOT NEEDED NOW EITHER
                newFragment.setArguments(args);


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

                setActiveFragment(2);
            }
        });

        BarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(JobActivity.this);
                integrator.initiateScan();
            }
        });




        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null){
                return;//seems strange - maybe a break?
            }

            //new ChooseWorker frag
            ChooseWorkerFragment workerFragment = ChooseWorkerFragment.newInstance();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            workerFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, workerFragment).commit();

            setActiveFragment(1);

        }
    }


    private void addTestRowsToDB()
    {
        DBAdapter db = DBAdapter.getsInstance(getApplicationContext());
        db.open();
        db.insertArtikelDatenRow("Paulaner Hefe Weißbier Naturtrüb rüb rübrübrübrübrübrübrübrüb rübrübrübrübrübrübrübrübrübrübrübrüb","4066600641964","Flaschen","(1Stk.)");
        db.insertArtikelDatenRow("Augustiner Edelstoff","4105250024007","Flaschen","(1Stk.)");
        db.insertArtikelDatenRow("Tegernsee Spezial Kasten","4016931051420","Kasten","(20Fl.)");
        db.insertArtikelDatenRow("Oettinger Hell Kasten","4014086910319","Kasten","(20Fl.)");
        db.close();
    }

    private void deleteArticleDatabase()
    {
        DBAdapter db = DBAdapter.getsInstance(getApplicationContext());
        db.open();
        db.deleteAll();
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(resultCode != 0){
            Context ctx = getApplicationContext();

            String re = scanResult.getContents();
            //Toast toast = Toast.makeText(ctx, re, Toast.LENGTH_SHORT);
            //toast.show();
            Log.d("Barcode", re);
            lastScan = re;

            DBAdapter db = DBAdapter.getsInstance(ctx);
            db.open();

            Cursor cursor = db.getArtikelDatenRow(lastScan);
            try {
                do {
                    if(cursor == null)
                        break;

                    String name = cursor.getString(2);
                    String barcode = cursor.getString(3);
                    String einheit = cursor.getString(4);
                    String stdVPE = cursor.getString(1);

                    activeWork.addArtikelToAufmass(new Artikel(barcode,stdVPE,name,einheit), 1);//TODO: ADD ANZAHL PICKER
                    cursor.moveToNext();
                }while(cursor.moveToNext());

            } finally {
                cursor.close();
            }
            db.close();
        }else{
            Log.d("Barcode", "Nothing scanned");
        }
    }

    public ArrayList<AufmassArtikel> getAufmassList() {
        return activeWork.getAufmass();
    }
    public ArrayList<Arbeitszeit> getArbeitszeitList() { return activeWork.getWorkedHours(); }

    //SAME LISTENER FOR BOTH FRAGMENTS!?
    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void newFragmentCreated(View fragmentView) {
    }

    public void toogleWorkingPosition(int position)
    {
        if(workingPositions.contains(position))
        {
            workingPositions.remove(workingPositions.indexOf(position));
        }else{
            workingPositions.add(position);
        }

    }
}
