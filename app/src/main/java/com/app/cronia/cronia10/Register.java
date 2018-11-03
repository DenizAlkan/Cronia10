package com.app.cronia.cronia10;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cronia.cronia10.Database.DBFunc;
import com.app.cronia.cronia10.Database.DatabaseHelper;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    Button register_btn_next;
    TextView register_txt_username,register_txt_email,register_txt_pass;
    int Register_state;
    //final DBFunc dbx;
    final DatabaseHelper mdb = new DatabaseHelper(this);

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                   // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                   "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private static final Pattern USERNAME = Pattern.compile(
            "^"
                    +"[a-zA-Z0-9_-]{4,15}"+
                    "$"
                            //any letter

    );




 /*
    DatabaseHelper mDatabaseHelper;
    private Button buttonAdd, buttonView;
    private EditText editText; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register_txt_username = (TextView) findViewById(R.id.register_txt_username);
        register_txt_email = (TextView) findViewById(R.id.register_txt_email);
        register_txt_pass = (TextView) findViewById(R.id.register_txt_pass);


        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_rect_color));




        register_btn_next = (Button)findViewById(R.id.register_btn_next);
        register_btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validateEmail() | !validateUsername() | !validatePassword()) {
                    return;
                }

                String input = "Email: " + register_txt_email.getEditableText().toString();
                input += "\n";
                input += "Username: " + register_txt_username.getEditableText().toString();
                input += "\n";
                input += "Password: " + register_txt_pass.getEditableText().toString();

                Toast.makeText( getApplicationContext(),input, Toast.LENGTH_LONG).show();

                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                String username = register_txt_username.getText().toString();
                String email =register_txt_email.getText().toString();
                Log.d(TAG, email);
                Register_state =  mdb.registerControl(username,email);



                if ( Register_state == 0)
                {
                    Intent register2 = new Intent(Register.this, Register_2.class);
                    register2.putExtra("username", username);
                    register2.putExtra("email", email);
                    register2.putExtra("password", register_txt_pass.getText().toString());
                    startActivity(register2);

                }

                else if ( Register_state == 1)
                {
                    Toast.makeText(getApplicationContext(),"Üzgünüz. Bu kullanıcı adı daha önce alındı.", Toast.LENGTH_LONG).show();

                }

                else if ( Register_state == 2)
                {
                    Toast.makeText(getApplicationContext(),"Üzgünüz. Bu mail adresi daha önce kayıt edildi.", Toast.LENGTH_LONG).show();

                }

                else if ( Register_state == 3)
                {
                    Toast.makeText(getApplicationContext(),"Üzgünüz. Bu kullanıcı adı ve mail adresi daha önce kayıt edildi.", Toast.LENGTH_LONG).show();

                }






            }

        });

    }

    private boolean validateEmail() {
        String emailInput = register_txt_email.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            register_txt_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            register_txt_email.setError("Please enter a valid email address");
            return false;
        } else {
            register_txt_email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = register_txt_username.getEditableText().toString().trim();

        if (usernameInput.isEmpty()) {
            register_txt_username.setError("Field can't be empty");
            return false;
        }
        else if (!USERNAME.matcher(usernameInput).matches())
        {
            register_txt_username.setError("Adam ol");
            return false;
        }
        else if (usernameInput.length() > 15) {
            register_txt_username.setError("Username too long");
            return false;
        } else {
            register_txt_username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = register_txt_pass.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            register_txt_pass.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            register_txt_pass.setError("Password too weak");
            return false;
        } else {
            register_txt_pass.setError(null);
            return true;
        }
    }


}