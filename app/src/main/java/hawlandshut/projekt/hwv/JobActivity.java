package hawlandshut.projekt.hwv;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JobActivity extends AppCompatActivity
        implements
        ChooseWorkerFragment.OnFragmentInteractionListener,
        AufmassFragement.OnFragmentInteractionListener,
        View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        TextView Arbeiter = (TextView)findViewById(R.id.chooseArbeiter);
        TextView Aufmass = (TextView)findViewById(R.id.chooseAufmass);

        Arbeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create fragment and give it an argument specifying the article it should show
                ChooseWorkerFragment newFragment = new ChooseWorkerFragment();
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
                AufmassFragement newFragment = new AufmassFragement();
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
            }
        });

        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null){
                return;//seems strage - maybe a break?
            }

            //new ChooseWorker frag
            ChooseWorkerFragment workerFragment = new ChooseWorkerFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            workerFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, workerFragment).commit();

        }
    }


    //SAME LISTENER FOR BOTH FRAGMENTS!?
    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public void onClick(View v) {

    }
}
