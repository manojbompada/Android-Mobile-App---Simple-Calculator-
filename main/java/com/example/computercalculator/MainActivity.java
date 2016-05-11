/*
    Cole Howell, Manoj Bompada
    MainActivity.java
    Homework #1
    ITCS 4180
 */

package com.example.computercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView tv1,tv2,tv3;
    private SeekBar sb;
    private EditText edtamt;
    private RadioGroup rg1,rg2;
    private Button cal,res;
    private Switch sw;
    Double shipamt=1.00;
    double mem=2,store=250,totalaccessories=0;
    private CheckBox chckmouse,chckflash,chckcool,chckcase;
    double cmouse,cflash,ccool,ccase;
    int tipvalue = 15 ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tipvalue);
        tv1.setText(15 + "%");
        tv2 = (TextView) findViewById(R.id.price);
        tv3 = (TextView) findViewById(R.id.statebudget);
        sb = (SeekBar) findViewById(R.id.seekBar);
        rg1 = (RadioGroup) findViewById(R.id.memoryradiogroup);
        rg2 = (RadioGroup) findViewById(R.id.storageradiogroup);
        sw = (Switch)findViewById(R.id.deliverysw);
        edtamt = (EditText)findViewById(R.id.amt);
        cal = (Button) findViewById(R.id.calculate);
        res = (Button) findViewById(R.id.reset);
        chckmouse = (CheckBox) findViewById(R.id.mouse);
        chckflash = (CheckBox) findViewById(R.id.flashdrive);
        chckcool  = (CheckBox) findViewById(R.id.cooling);
        chckcase  = (CheckBox) findViewById(R.id.carrycase);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);

                int checkid = rg1.getCheckedRadioButtonId();

                if(checkid == R.id.memory2gb){
                    mem = 2;
                }
                else if(checkid == R.id.memory4gb){
                    mem = 4;
                }
                else if(checkid == R.id.memory8gb){
                    mem = 8;
                }
                else if(checkid == R.id.memory16gb){
                    mem = 16;
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);

                int checkid = rg2.getCheckedRadioButtonId();

                if (checkid == R.id.store250gb) {
                    store = 250;
                } else if (checkid == R.id.store500gb) {
                    store = 500;
                } else if (checkid == R.id.store750gb) {
                    store = 750;
                } else if (checkid == R.id.store1tb) {
                    store = 1000;
                }
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipvalue = progress * 5;
                tv1.setText(tipvalue + "%");
                Log.d("demo", "tipvalue " + tipvalue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sw.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shipamt = 1.00;
                } else {
                    shipamt = 0.00;
                }

            }
        });


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtamt.getText().toString().length() !=  0) {
                    totalaccessories = cmouse+ccase+cflash+ccool;
                    double cost = ((10 * mem + 0.75 * store + 20 * totalaccessories) * (1 + (double)tipvalue/100)) + 5.95 * shipamt;

                    DecimalFormat df = new DecimalFormat();
                    df.setMinimumFractionDigits(2);
                    df.setMaximumFractionDigits(2);

                    tv2.setText("Price: $" + df.format(cost));

                    Double setamt = Double.parseDouble(edtamt.getText().toString());
                    if (setamt >= cost) {
                        tv3.setText("within budget");
                        tv3.setBackgroundResource(R.color.green);
                    } else {
                        tv3.setText("Over budget");
                        tv3.setBackgroundResource(R.color.red);
                    }
                }else{
                    setError();
                }
            }

        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(15 + "%");
                edtamt.setText("");
                rg1.check(R.id.memory2gb);
                rg2.check(R.id.store250gb);
                sw.setChecked(true);
                tv2.setText("Price: $0.00");
                tv3.setText("");
                chckmouse.setChecked(false);
                chckcool.setChecked(false);
                chckflash.setChecked(false);
                chckcase.setChecked(false);
            }
        });

    }

    private void setError() {

        Toast.makeText(this,"Enter a dollar amount",Toast.LENGTH_SHORT).show();
    }

    public void onCheckboxClickedmouse(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            cmouse = 1;
        }else{
            cmouse = 0;
        }
    }
    public void onCheckboxClickedflash(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            cflash = 1;
        }else{
            cflash = 0;
        }
    }
    public void onCheckboxClickedcool(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            ccool = 1;
        }else{
            ccool = 0;
        }
    }
    public void onCheckboxClickedcase(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            ccase = 1;
        }else{
            ccase = 0;
        }
    }


}