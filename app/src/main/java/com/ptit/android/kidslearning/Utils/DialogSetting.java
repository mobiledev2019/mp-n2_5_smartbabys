package com.ptit.android.kidslearning.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ptit.android.kidslearning.R;

public class DialogSetting extends DialogFragment {
    Switch switch_sound;
    public DialogListener dialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DialogListener) {
            dialogListener = (DialogListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + "must implement DialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Set View
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_setting, null);

        //Mapping
        switch_sound = v.findViewById(R.id.switch_sound);
        switch_sound.setChecked(true);
        //Event
        switch_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dialogListener.onSwitchChecked(isChecked);
            }
        });

        //set builder for dialog
        builder.setTitle(R.string.tittle_setting)
                .setView(v)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogSetting.this.getDialog().cancel();

                    }
                });
        return builder.create();
    }

    public interface DialogListener {
        void onSwitchChecked(boolean check);
    }
}
