package com.app.cronia.cronia10;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cronia.cronia10.Database.DBFunc;
import com.app.cronia.cronia10.Database.DatabaseHelper;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    Button register_btn_next;
    TextView register_txt_username,register_txt_email,register_txt_pass;
    int Register_state;
    DBFunc mdb;


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
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                String username = register_txt_username.getText().toString();
                String email =register_txt_email.getText().toString();
                Register_state =  mdb.registerControl("a","e");

                if ( Register_state == 0)
                {
                    Intent go_to_main = new Intent(Register.this, Register_2.class);
                    startActivity(go_to_main);
                }






            }

        });

    }
       /* editText = (EditText) findViewById(R.id.editText);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        mDatabaseHelper = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    AddData(1,1);



            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(int item1,int item2) {
        boolean insertData = mDatabaseHelper.addData(item1,item2);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    */
}