package com.example.mountaincl.ui.changePass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.helper.RequestHandler;
import com.example.mountaincl.helper.URLS;
import com.example.mountaincl.helper.User;
import com.google.android.gms.common.util.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ChangePass extends AppCompatDialogFragment {

    EditText editTextPassword, editTextPassword2;

    PrefManager prefManager;
    String username;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressBar progressBar;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setCancelable(false);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);

        editTextPassword =  view.findViewById(R.id.confirmationPassword);
        editTextPassword2 = view.findViewById(R.id.Password);
        progressBar = view.findViewById(R.id.reprogressBar);

        //getting the current user
        User user = PrefManager.getInstance(getContext()).getUser();

        username = user.getUsername();

        builder.setView(view).setCancelable(false).setTitle("Change Password")
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // when we click on this button it redirects users to
                        //customListener to ger a validation

                        validatePassword();

                    }
                })
                .setNegativeButton(R.string.cancelFinish, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();

    }

//    class CustomListener implements View.OnClickListener {
//        private final Dialog dialog;
//
//        public CustomListener(Dialog dialog) {
//            this.dialog = dialog;
//        }

    public void validatePassword(){

            //now we will 1st check that all fields have something on them,
            //and second that the field on top and the field at the bottom have the same string

            final String password = editTextPassword.getText().toString();
            final String password2 = editTextPassword2.getText().toString();


            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Please enter username");
                editTextPassword.requestFocus();
                System.out.println("Error on empty pass");
                return;
            } else if (TextUtils.isEmpty(password2)) {
                editTextPassword2.setError("Enter a password");
                editTextPassword2.requestFocus();
                return;
            } else if (!password.equals(password2)) {
                editTextPassword2.setError("Make sure the password matches");
                editTextPassword2.requestFocus();
                System.out.println("Error on not matching Pass");
            } else {
                Change_Password newPassword = new Change_Password(password);
                newPassword.execute();

            }
    }

    class Change_Password extends AsyncTask<Void, Void, String> {
        ProgressBar progressBar;
        String password;

        Change_Password(String password){
            this.password = password;
        }


//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //progressBar = progressBar.findViewById(R.id.reprogressBar);
//            //progressBar.setVisibility(View.VISIBLE);
//        }

        @Override
        protected String doInBackground(Void... voids) {

            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //Create request params
            HashMap<String, String> params = new HashMap<>();
            System.out.println("new pass: "+password);
            System.out.println("username: "+username);
            params.put("username", username);
            params.put("password", password);

            //Create request params
            return requestHandler.sendPostRequest(URLS.URL_CHANGE, params);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(View.GONE);
            System.out.println(s);
            System.out.println("We are close to finish and create a JSON obj");
            try {
                System.out.println("the problem originates here");
                JSONObject obj = new JSONObject(s);
                System.out.println("not going through this yet, I don't know why");
                //Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
