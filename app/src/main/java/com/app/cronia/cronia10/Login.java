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
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    //Butonumuz için değişken oluşturuyoruz.
    Button LoginButton;
    TextView txt;
    TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_rect_color));

        LoginButton = (Button)findViewById(R.id.login_btn_login);
        LoginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                Intent go_to_main = new Intent(Login.this, MainActivity.class);
                startActivity(go_to_main);
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