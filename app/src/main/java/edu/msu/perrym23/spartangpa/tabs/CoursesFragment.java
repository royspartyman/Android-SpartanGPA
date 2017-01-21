package edu.msu.perrym23.spartangpa.tabs;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.msu.perrym23.spartangpa.R;
import edu.msu.perrym23.spartangpa.RecyclerViewAdapters.SemesterAdapter;
import edu.msu.perrym23.spartangpa.utils.ClassDialog;
import edu.msu.perrym23.spartangpa.utils.CustomizeClassDialog;
import edu.msu.perrym23.spartangpa.utils.RealmManager;
import edu.msu.perrym23.spartangpa.utils.SemesterDialog;


public class CoursesFragment extends Fragment {

    private SemesterDialog semesterDialog = null;
    private ClassDialog classDialog = null;
    private CustomizeClassDialog customizeClassDialog = null;
    private RecyclerView mRecyclerView;
    private SemesterAdapter semesterAdapter;
    private RealmManager realmManager;
    protected RecyclerView.LayoutManager mLayoutManager;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.missing_semester)
    ImageView missingSemesterIV;

    @BindView(R.id.missing_semester_text)
    TextView missingSemesterTV;

    @BindView(R.id.add_semester)
    FloatingActionButton addSemesterFAB;

    @OnClick(R.id.add_semester)
    public void onAddSemesterClick(){
        semesterDialog = new SemesterDialog();
        semesterDialog.show(getFragmentManager(), "new semester");
        semesterDialog.setOnDialogClickedListener(new SemesterDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked() {
                classDialog = new ClassDialog();
                classDialog.show(getFragmentManager(), "class amount");
                classDialog.setOnDialogClickedListener(new ClassDialog.OnDialogClickedListener() {
                    @Override
                    public void onDialogClicked() {
                        customizeClassDialog = new CustomizeClassDialog();
                        customizeClassDialog.show(getFragmentManager(), "cuztomize classes");
                    }
                });
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        ButterKnife.bind(this, view);
        realmManager = new RealmManager(this.getContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.semester_recyclerview);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        semesterAdapter = new SemesterAdapter(realmManager.getTerms());
        // Set CustomAdapter as the adapter for RecyclerView.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(semesterAdapter);

        if (realmManager.getTerms().size() == 0){
            missingSemesterIV.setVisibility(View.VISIBLE);
            missingSemesterTV.setVisibility(View.VISIBLE);
        }

        return view;

    }
}
