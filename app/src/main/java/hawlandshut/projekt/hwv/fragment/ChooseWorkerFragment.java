package hawlandshut.projekt.hwv.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import hawlandshut.projekt.hwv.ListAdapterArbeiter;
import hawlandshut.projekt.hwv.ListAdapterArbeitszeit;
import hawlandshut.projekt.hwv.Mitarbeiter;
import hawlandshut.projekt.hwv.R;
import hawlandshut.projekt.hwv.activity.JobActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseWorkerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseWorkerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseWorkerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChooseWorkerFragment.
     */
    public static ChooseWorkerFragment newInstance() {
        ChooseWorkerFragment fragment = new ChooseWorkerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View workerChooseView = inflater.inflate(R.layout.fragment_choose_worker, container, false);

        ListView workerListView = (ListView)workerChooseView.findViewById(R.id.workerList);
        ListAdapterArbeiter arbeiter = new ListAdapterArbeiter(getContext(),getActivity());
        workerListView.setAdapter(arbeiter);
        arbeiter.setWorkingPositions(((JobActivity)getActivity()).getWorkingPositions());
        workerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((JobActivity)getActivity()).toogleWorkingPosition(position);//setSelectedItem(position);
                ((ListAdapterArbeiter) parent.getAdapter()).setWorkingPositions(((JobActivity)getActivity()).getWorkingPositions());
                ((ListAdapterArbeiter) parent.getAdapter()).notifyDataSetChanged();

                Mitarbeiter aktiv = (Mitarbeiter)((ListAdapterArbeiter) parent.getAdapter()).getItem((position));
                ((JobActivity)getActivity()).toggleWorker(aktiv);
            }
        });

        ListView az = (ListView)workerChooseView.findViewById(R.id.recordedWork);
        ListAdapterArbeitszeit work = new ListAdapterArbeitszeit(getContext(),getActivity());
        az.setAdapter(work);
        work.setArbeitszeitList(((JobActivity)getActivity()).getArbeitszeitList());
        work.notifyDataSetChanged();
        //OnLongClickListener Delete Row

        return workerChooseView;
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
