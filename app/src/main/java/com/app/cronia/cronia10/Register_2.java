package com.app.cronia.cronia10;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Register_2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch register_switch_gender;
    TextView register_txt_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_2);

        register_txt_gender = (TextView)findViewById(R.id.register_txt_gender);
        register_switch_gender = (Switch)findViewById(R.id.register_switch_gender);

        register_switch_gender.setOnCheckedChangeListener(this);


        //statusbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.login_rect_color));



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
