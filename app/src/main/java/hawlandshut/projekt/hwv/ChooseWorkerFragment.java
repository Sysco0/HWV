package hawlandshut.projekt.hwv;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseWorkerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseWorkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseWorkerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChooseWorkerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChooseWorkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseWorkerFragment newInstance() {
        ChooseWorkerFragment fragment = new ChooseWorkerFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_choose_worker, container, false);

        ListView lv = (ListView)rootView.findViewById(R.id.workerList);
        ListAdapterArbeiter arbeiter = new ListAdapterArbeiter(getContext(),getActivity());
        lv.setAdapter(arbeiter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListAdapterArbeiter) parent.getAdapter()).setSelectedItem(position);
                ((ListAdapterArbeiter) parent.getAdapter()).notifyDataSetChanged();
                Mitarbeiter aktiv = (Mitarbeiter)((ListAdapterArbeiter) parent.getAdapter()).getItem((position));
                ((JobActivity)getActivity()).setWorker(aktiv);
            }
        });

        ListView az = (ListView)rootView.findViewById(R.id.recordedWork);
        ListAdapterArbeitszeit work = new ListAdapterArbeitszeit(getContext(),getActivity());
        az.setAdapter(work);
        work.setArbeitszeitList(((JobActivity)getActivity()).getArbeitszeitList());
        work.notifyDataSetChanged();
        //OnLongClickListener Delete Row

        return rootView;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
