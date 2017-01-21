package edu.msu.perrym23.spartangpa.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import edu.msu.perrym23.spartangpa.R;

public class CustomizeClassDialog extends DialogFragment {

    private View view;
    private RealmManager realmManager;

    protected CustomizeClassDialog.OnDialogClickedListener callback = null;

    public interface OnDialogClickedListener {
        public abstract void onDialogClicked();
    }

    public void setOnDialogClickedListener(CustomizeClassDialog.OnDialogClickedListener l){
        callback = l;
    }

    public CustomizeClassDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        realmManager = new RealmManager(getContext());

        builder.setTitle("Customize Classes");

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        view = inflater.inflate(R.layout.dialog_customize_classes, null);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.finish, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        final Dialog dlg = builder.create();

        dlg.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

            }
        });

        return dlg;
    }

    public void updateUI(){
        //Change all of the views to match the new dialog portion.
    }

}