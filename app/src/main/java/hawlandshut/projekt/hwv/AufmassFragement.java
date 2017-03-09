package hawlandshut.projekt.hwv;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import hawlandshut.projekt.hwv.activity.JobActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AufmassFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AufmassFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AufmassFragement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    OnNewFragmentCreatedCallback scanCallback;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AufmassFragement(){
    //Required empty Constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AufmassFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static AufmassFragement newInstance(OnNewFragmentCreatedCallback scanCallback) {
        AufmassFragement fragment = new AufmassFragement();
        fragment.setOnNewScannerDataCallback(scanCallback);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aufmass_fragement, container, false);

        ListView aufmassListView = (ListView)v.findViewById(R.id.listViewAufmass);

        ListAdapterAufmass aufmass = new ListAdapterAufmass(getContext(),getActivity());


        aufmass.setAufmassList(((JobActivity)getActivity()).getAufmassList());
        aufmassListView.setAdapter(aufmass);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(scanCallback != null)
            scanCallback.newFragmentCreated(view);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public void setOnNewScannerDataCallback(OnNewFragmentCreatedCallback sc){
        scanCallback = sc;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
