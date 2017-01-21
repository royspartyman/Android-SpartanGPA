package edu.msu.perrym23.spartangpa.utils;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.msu.perrym23.spartangpa.R;

public class SemesterDialog extends DialogFragment {

    private View view;
    private Spinner spinnerTerm;
    private Spinner spinnerYear;
    private RealmManager realmManager;
    private SemesterState semesterState;

    enum SemesterState{INITIAL, ADDCLASS, CUSTOMIZE}

    protected OnDialogClickedListener callback = null;

    public interface OnDialogClickedListener {
        public abstract void onDialogClicked();
    }

    public void setOnDialogClickedListener(OnDialogClickedListener l){
        callback = l;
    }


    public SemesterDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        realmManager = new RealmManager(getContext());

        builder.setTitle("New Semester");

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        view = inflater.inflate(R.layout.dialog_semester, null);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String season = spinnerTerm.getSelectedItem().toString();
                        Integer year = Integer.parseInt(spinnerYear.getSelectedItem().toString());
                        if(!realmManager.checkExistingTerm(season, year)){
                            realmManager.createTerm(season, year);
                        }
                        callback.onDialogClicked();
                    }
                });

        final Dialog dlg = builder.create();

        dlg.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                spinnerTerm = (Spinner) view.findViewById(R.id.term_spinner);
                ArrayAdapter<CharSequence> adapterTerm = ArrayAdapter.createFromResource(getContext(),
                        R.array.terms, R.layout.spinner_item);
                adapterTerm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTerm.setAdapter(adapterTerm);

                spinnerYear = (Spinner) view.findViewById(R.id.year_spinner);
                ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(getContext(),
                        R.array.years, R.layout.spinner_item);
                adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerYear.setAdapter(adapterYear);
            }
        });

        return dlg;
    }

    public void updateUI(){
        //Change all of the views to match the new dialog portion.
    }

}
