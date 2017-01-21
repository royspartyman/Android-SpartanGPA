package edu.msu.perrym23.spartangpa.tabs;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.msu.perrym23.spartangpa.R;
import edu.msu.perrym23.spartangpa.utils.RealmManager;

/**
 * Created by royspartyman on 12/27/16.
 */

public class SummaryFragment extends Fragment {

    private RealmManager realmManager;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.info_credits)
    TextView infoCreditsTV;

    @BindView(R.id.info_cumulative_gpa)
    TextView infoCumulativeGpaTV;

    @BindView(R.id.info_technical_gpa)
    TextView infoTechnicalGpaTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, view);
        realmManager = new RealmManager(getContext());
        //infoCreditsTV.setText(realmManager.getStudent().getCredits().toString());
        return view;
    }

}
