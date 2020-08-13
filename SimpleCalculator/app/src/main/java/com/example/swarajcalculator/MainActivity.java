package com.example.swarajcalculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView et;


    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn0;
    Button btnadd;
    Button btnsub;
    Button btnmul;
    Button btndiv;
    Button btndot;
    Button equ;
    Button clear;

    double first,second,result;
    //first for first value entered and second for second value entered and result for answer
    boolean add=false,sub=false,mul=false,div=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the view id of the buttons

        btn9 = findViewById(R.id.buttonnine);
        btn8 = findViewById(R.id.buttoneight);
        btn7 = findViewById(R.id.buttonSeven);
        btn2 = findViewById(R.id.buttontwo);
        btn6 = findViewById(R.id.buttonsix);
        btn5 = findViewById(R.id.buttonfive);
        btn4 = findViewById(R.id.buttonfour);
        btn3 = findViewById(R.id.buttonthree);
        btn1 = findViewById(R.id.buttonone);
        btn0 = findViewById(R.id.buttonzero);
        equ = findViewById(R.id.buttonequal);
        clear = findViewById(R.id.buttonclear);
        btnadd = findViewById(R.id.buttonadd);
        btnsub = findViewById(R.id.buttonminus);
        btnmul = findViewById(R.id.buttonmultiply);
        btndiv = findViewById(R.id.buttondivide);
        btndot = findViewById(R.id.buttonDot);

        //finding the view id for text view
        final TextView et=findViewById(R.id.editText);
        et.setText("");


        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("9");
            }
        });



        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("8");
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("7");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("6");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("5");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("4");
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("3");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("2");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("1");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append("0");
            }
        });

        btndot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.append(".");
            }
        });

        //operators

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    first = Double.valueOf(et.getText().toString());
                    et.setText(null);
                    add = true;
                    sub = false;
                    mul = false;
                    div = false;
                } catch (Exception e) {}

            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    first = Double.valueOf(et.getText().toString());
                    et.setText(null);
                    add = false;
                    sub = true;
                    mul = false;
                    div = false;
                } catch (Exception e) {}
            }
        });

        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    first=Double.valueOf(et.getText().toString());
                    et.setText(null);
                    add=false;
                    sub=false;
                    mul=true;
                    div=false;
                }catch (Exception r){}
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    first=Double.valueOf(et.getText().toString());
                    et.setText(null);
                    add=false;
                    sub=false;
                    mul=false;
                    div=true;
                }catch(Exception e){}
            }
        });


        equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    second=Double.valueOf(et.getText().toString());
                    if(add){
                        result=first+second;
                        et.setText("" + result);
                    }else if(sub){
                        result = first - second;
                        et.setText("" + result);
                    }else if(mul){
                        result = first * second;
                        et.setText("" + result);
                    }else if(div){
                        result = first / second;
                        et.setText("" + result);
                    }
                }catch (Exception e){}


            }
        });

        //delete

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    /*we are taking a string and using settext we are reducing one element at one click
                    of button at a time using substring and all of that*/

                    String get=et.getText().toString();
                    et.setText(get.substring(0,get.length()-1));
                }catch (Exception e){}
            }
        });

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                et.setText(null);
                return true;
            }
        });




    }
}
