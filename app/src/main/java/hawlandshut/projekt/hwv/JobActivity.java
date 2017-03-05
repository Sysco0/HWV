package hawlandshut.projekt.hwv;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class JobActivity extends AppCompatActivity
        implements
        ChooseWorkerFragment.OnFragmentInteractionListener,
        AufmassFragement.OnFragmentInteractionListener,
        OnNewFragmentCreatedCallback,
        View.OnClickListener {

    protected String lastScan = "";
    private View aufmassTxtView;
    private Auftrag activeJob;

    protected void setWorker(Mitarbeiter arbeiter)
    {
        TextView workerName = (TextView)findViewById(R.id.WorkerName);
        workerName.setText( arbeiter.getKuerzel());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        Intent intent = getIntent();
        activeJob = intent.getParcelableExtra("activeJob");

        TextView jobID = (TextView)findViewById(R.id.textViewActiveJob);
        jobID.setText(activeJob.getKunde().getVorname() + " " + activeJob.getKunde().getName());

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

                if(aufmassTxtView != null){
                    TextView aufmassText = (TextView) aufmassTxtView.findViewById(R.id.aufmassTextView);
                    aufmassText.setText(lastScan);
                }
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

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(resultCode != 0){
            String re = "Scanned:" + scanResult.getContents();

            Context ctx = getApplicationContext();
            Toast toast = Toast.makeText(ctx, re, Toast.LENGTH_SHORT);
            toast.show();
            Log.d("Barcode", re);
            lastScan = re;
        }else{
            Log.d("Barcode", "Nothing scanned");
        }
    }

    //SAME LISTENER FOR BOTH FRAGMENTS!?
    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void newFragmentCreated(View fragmentView) {
        aufmassTxtView = fragmentView;
    }
}
