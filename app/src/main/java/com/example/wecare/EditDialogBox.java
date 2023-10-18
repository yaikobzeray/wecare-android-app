package com.example.wecare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;

public class EditDialogBox  extends AppCompatDialogFragment {
    private String title;
    private String hintText;

    private EditDialogListener listener;

    public EditDialogBox(String title, String hintText) {
        this.title = title;
        this.hintText = hintText;
    }
    private EditText editText;




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view =  layoutInflater.inflate(R.layout.edit_layout,null);

        editText = view.findViewById(R.id.editText);
        builder.setView(view)

                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("cancel");
                    }
                }).setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     String text = editText.getText().toString();

                     listener.applyText(text,title);
                    }
                });

        editText.setHint(hintText);
        return builder.create();


    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString() + "EditDialogListener must be implemented");
        }
    }

    public interface EditDialogListener{
        void applyText(String text,String inputType);


    }

}


