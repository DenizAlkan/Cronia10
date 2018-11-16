package com.app.cronia.cronia10;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Display;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.app.cronia.cronia10.Database.DatabaseHelper;

        import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    //Butonumuz için değişken oluşturuyoruz.
    Button LoginButton;
    TextView txt;
    TextView txtRegister;
    EditText login_txt_username,login_txt_pass;
    final DatabaseHelper mdb = new DatabaseHelper(this);
    int LoginState;

    //session durumu
    SessionManager session;
    String userID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Session Manager tanımladık.
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == true){

            startActivity(new Intent(Login.this, MainActivity.class));
        }

        login_txt_username = (EditText) findViewById(R.id.login_txt_username);
        login_txt_pass = (EditText) findViewById(R.id.login_txt_pass);

        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_rect_color));

        LoginButton = (Button)findViewById(R.id.login_btn_login);
        LoginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                LoginState = mdb.loginControl(login_txt_username.getText().toString(),login_txt_pass.getText().toString());
                userID = mdb.getUserID(login_txt_username.getText().toString());

                if (LoginState == 0)
                {
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı yada Şifre Hatalı", Toast.LENGTH_LONG).show();

                }

                else
                {
                    //giriş başarılı ise Session'a verilerimizi yolladık.
                    session.createLoginSession(login_txt_username.getText().toString(), login_txt_pass.getText().toString(),userID);
                    //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                    Intent go_to_main = new Intent(Login.this, MainActivity.class);
                    startActivity(go_to_main);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                }







            }

        });


        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                Intent go_to_main2 = new Intent(Login.this, Register.class);
                startActivity(go_to_main2);
            }

        });

    }


    public void click(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Register.class);
        startActivity(i);
    }


}

// ömer can burayı görmen lazım