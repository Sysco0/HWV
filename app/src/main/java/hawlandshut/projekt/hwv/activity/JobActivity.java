package hawlandshut.projekt.hwv.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.activity.callback.OnNewFragmentCreatedCallback;
import hawlandshut.projekt.hwv.adpater.ListTaskAdapter;
import hawlandshut.projekt.hwv.adpater.ListTaskArticleAdapter;
import hawlandshut.projekt.hwv.adpater.ListWorkeRangeAdapter;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBAddress;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskArticle;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskWorker;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBWorker;
import hawlandshut.projekt.hwv.db.resource.repository.AddressRepository;
import hawlandshut.projekt.hwv.db.resource.repository.ArticleRepository;
import hawlandshut.projekt.hwv.db.resource.repository.CustomerRepository;
import hawlandshut.projekt.hwv.db.resource.repository.TaskArticleRepository;
import hawlandshut.projekt.hwv.db.resource.repository.TaskRepository;
import hawlandshut.projekt.hwv.db.resource.repository.TaskWorkerRepository;
import hawlandshut.projekt.hwv.fragment.ChooseWorkerFragment;
import hawlandshut.projekt.hwv.fragment.TaskArticleFragement;


public class JobActivity extends AppCompatActivity
        implements
        ChooseWorkerFragment.OnFragmentInteractionListener,
        TaskArticleFragement.OnFragmentInteractionListener,
        OnNewFragmentCreatedCallback,
        View.OnClickListener {

    private Long taskId;
    protected String lastScan = "";
    private DBTask dbTask;
    private DBCustomer dbCustomer;
    private DBAddress dbAddress;

    private List<DBWorker> activeWorker = new ArrayList<>();

    public ArrayList<Integer> getWorkingPositions() {
        return workingPositions;
    }

    private ArrayList<Integer> workingPositions = new ArrayList<>();


    protected void setActiveFragment(int fragmentNr) {
        RelativeLayout arbeiterBG = (RelativeLayout) findViewById(R.id.chooseArbeiterBG);
        RelativeLayout aufmassBG = (RelativeLayout) findViewById(R.id.chooseAufmassBG);
        RelativeLayout barcodeButtonBG = (RelativeLayout) findViewById(R.id.barcodeScannerButtonBG);

        switch (fragmentNr) {
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

    public void toggleWorker(DBWorker dbWorker) {
        TextView workerName = (TextView) findViewById(R.id.WorkerName);

        if (activeWorker.contains(dbWorker)) {
            activeWorker.remove(dbWorker);
        } else {
            activeWorker.add(dbWorker);
        }

        String activeWorkers = "";
        List<DBTaskWorker> dbTaskWorkers = new ArrayList<>();
        TaskWorkerRepository taskWorkerRepository = TaskWorkerRepository.getInstance();
        for (DBWorker ma : activeWorker) {
            if (!activeWorkers.isEmpty())
                activeWorkers += ", ";
            activeWorkers += ma.getLastName();
            dbTaskWorkers.addAll(taskWorkerRepository.getByWorkerIdAndTaskId(ma.getWorkerId(),taskId));
        }

        if (workerName != null) {
            workerName.setText(activeWorkers);
        }


        ListView az = (ListView) findViewById(R.id.recordedWork);
        ((ListWorkeRangeAdapter) az.getAdapter()).setWorkTime(dbTaskWorkers);
        ((ListWorkeRangeAdapter) az.getAdapter()).notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Intent intent = getIntent();
         taskId = intent.getLongExtra(Variables.ACTIVE_TASK_ID, -1L);

        TextView Arbeiter = (TextView) findViewById(R.id.chooseArbeiter);
        TextView Aufmass = (TextView) findViewById(R.id.chooseAufmass);
        TextView BarcodeButton = (TextView) findViewById(R.id.barcodeScannerButton);
        TextView jobID = (TextView) findViewById(R.id.textViewActiveJob);

        Button pause = (Button) findViewById(R.id.iconPause);
        Button stop = (Button ) findViewById(R.id.iconStop);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (taskId > -1) {
            dbTask = TaskRepository.getInstance().getByTaskId(taskId);
            dbCustomer = CustomerRepository.getInstance().getByCustomerId(dbTask.getCustomerId());
            dbAddress = AddressRepository.getInstance().getByAddressId(dbCustomer.getAddressId());

            jobID.setText(String.format("Kunde: %s %s", dbCustomer.getFirstName(), dbCustomer.getLastName()));

        }


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
                TaskArticleFragement newFragment = TaskArticleFragement.newInstance(JobActivity.this);
                Bundle args = new Bundle();
                //args.putInt(TaskArticleFragement.ARG_POSITION, position); NOT NEEDED NOW EITHER
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

                TextView aufmassBtn = (TextView) findViewById(R.id.chooseAufmass);
                aufmassBtn.performClick();
            }
        });


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
       IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(resultCode != 0){
            boolean articleFound = false;

            String re = scanResult.getContents();
            //toast.show();
            Log.d("Barcode", re);
            lastScan = re;

            DBArticle article = ArticleRepository.getInstance().getByBarcode(re);
            if(null != article){
                DBTaskArticle dbTaskArticle = new DBTaskArticle();
                dbTaskArticle.setSync(false);
                dbTaskArticle.setAmount((long) article.getMinOrderAmount());
                dbTaskArticle.setTaskId(taskId);
                dbTaskArticle.setArticleId(article.getArticleId());
                TaskArticleRepository.getInstance().create(dbTaskArticle);

                ListTaskArticleAdapter tasks = (ListTaskArticleAdapter)((ListView)findViewById(R.id.listViewAufmass)).getAdapter();
                tasks.setAufmassList(TaskArticleRepository.getInstance().getByTaskId(taskId));
                tasks.notifyDataSetChanged();

                Toast.makeText(this,  String.format("Article %s (%s) added to task",article.getName(),re), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, String.format("Article with barcode %s not found",re), Toast.LENGTH_SHORT).show();
            }

        }else{
            Log.d("Barcode", "Nothing scanned");
            Toast.makeText(this, "Error scanning barcode", Toast.LENGTH_SHORT).show();

        }
    }

    public List<DBTaskArticle> getAufmassList() {
        return TaskArticleRepository.getInstance().getByTaskId(taskId);
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
    }

    public void toogleWorkingPosition(int position) {
        if (workingPositions.contains(position)) {
            workingPositions.remove(workingPositions.indexOf(position));
        } else {
            workingPositions.add(position);
        }

    }
}
