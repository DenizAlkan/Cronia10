package com.app.cronia.cronia10;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cronia.cronia10.Database.DatabaseHelper;

import java.util.Calendar;

public class Register_2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch register_switch_gender;
    Button register_btn_finish;
    TextView register_txt_gender;
    TextView register_txt_name;
    TextView register_txt_surname;
    TextView register_txt_dateofbirth;
    String dateofbirth;
    String username,email,password,gender,name,surname;
    final DatabaseHelper mdb = new DatabaseHelper(this);
    int Register_state;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private static final String TAG = "Register2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_2);

        register_txt_gender = (TextView)findViewById(R.id.register_txt_gender);
        register_txt_name = (TextView)findViewById(R.id.register_txt_name);
        register_txt_surname = (TextView)findViewById(R.id.register_txt_surname);
        register_txt_dateofbirth = (TextView)findViewById(R.id.register_txt_dateofbirth);
        register_switch_gender = (Switch)findViewById(R.id.register_switch_gender);

        username = getIntent().getExtras().getString("username");
        password = getIntent().getExtras().getString("password");
        email = getIntent().getExtras().getString("email");



        register_switch_gender.setOnCheckedChangeListener(this);


        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_rect_color));


        register_txt_dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Register_2.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;


                String date = month + "/" + day + "/" + year;
                register_txt_dateofbirth.setText(date);
            }
        };

        register_btn_finish = (Button)findViewById(R.id.register_btn_finish);
        register_btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ardından Intent methodunu kullanarak nereden nereye gideceğini söylüyoruz.
                gender = register_txt_gender.getText().toString();
                name = register_txt_name.getText().toString();
                surname = register_txt_surname.getText().toString();
                dateofbirth = register_txt_dateofbirth.getText().toString();

                Register_state = mdb.registerControl(username, email);

                if ( Register_state == 0)
                {
                  mdb.userInsert(username,email,password,name,surname,dateofbirth,gender);
                    Log.d(TAG, username+","+email+","+password+","+name+","+surname+","+dateofbirth+","+gender);
                    Intent login = new Intent(Register_2.this, Login.class);
                    startActivity(login);



                }

                else

                {
                    Intent register1 = new Intent(Register_2.this, Register.class);
                    startActivity(register1);
                }


            }

        });

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(register_switch_gender.isChecked()){
            register_txt_gender.setText("Erkek");
        }else{
            register_txt_gender.setText("Kadın");
        }
    }
}
