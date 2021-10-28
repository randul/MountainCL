package com.example.mountaincl.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mountaincl.MainActivity;
import com.example.mountaincl.R;
import com.example.mountaincl.helper.PrefManager;
import com.example.mountaincl.helper.RequestHandler;
import com.example.mountaincl.helper.URLS;
import com.example.mountaincl.helper.User;
import com.example.mountaincl.ui.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity{

    EditText editTextUserNameLogin, editTextPasswordLogin;
    TextView guestUser;
    int attempts = 0;
    Button btn_login, btn_register;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserNameLogin = findViewById(R.id.editTextUserNameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        guestUser = findViewById(R.id.textViewGuest);

        imageView = findViewById(R.id.imageMenuLogin);

        btn_login = findViewById(R.id.loginButton);
        btn_register = findViewById(R.id.registerButton);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginValidation();

            }
        });

        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });

        findViewById(R.id.textViewGuest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                User sports = new User(10001, "guest","+44 0841235123", "guest@example.com");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("User", sports);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void loginValidation() {
        final String username = editTextUserNameLogin.getText().toString().trim();
        final String password = editTextPasswordLogin.getText().toString();
        
        //validations
        if (TextUtils.isEmpty(username)) {
            editTextUserNameLogin.setError("Please enter username");
            editTextUserNameLogin.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            editTextPasswordLogin.setError("Enter a password");
            editTextPasswordLogin.requestFocus();
            return;
        }else {
            //if it passes all the validations
            //executing the async task
            UserLogin ul = new UserLogin(username, password);
            ul.execute();
        }

    }

    class UserLogin extends AsyncTask<Void, Void, String> {
        ProgressBar progressBar;
        String username;
        String password;
        UserLogin(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //Create request params
            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

            //returning the response
            return requestHandler.sendPostRequest(URLS.URL_LOGIN, params);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {

                System.out.println(s);
                //converting response to json object
                JSONObject obj = new JSONObject(s);

                System.out.println("HERE WE CREATE a JSONObject and place it in PrefManager");
                //if no error in response

                if (!obj.getBoolean("error")) {
                    Toast.makeText(LoginActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();


                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");
                    //creating a new user object
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("username"),
                            userJson.getString("phone"),
                            userJson.getString("email")
                    );

                    //storing the user in shared preferences
                    PrefManager.getInstance(LoginActivity.this).setUserLogin(user);

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password or both", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
