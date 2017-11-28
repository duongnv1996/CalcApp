package com.lucky.mohcine.calcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView screen_display;
    private TextView screen_result;
    private String display = "";
    private String result = "";
    private String currentOperator= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen_display = (TextView) findViewById(R.id.textView2);
        screen_result = (TextView) findViewById(R.id.textView);
        screen_display.setText(display);
        screen_result.setText(result);

    }

    public double operator( String a ,String b ,  String opt){

        switch (opt){
            case "+" : return Double.valueOf(a) + Double.valueOf(b);
            case "-" : return Double.valueOf(a) - Double.valueOf(b);
            case "x" : return Double.valueOf(a) * Double.valueOf(b);
            case "/" : try {
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("TAG",e.getMessage());}
                default:return 0;

        }

    }

    public void onSetmins(View view){
        String mins= "-";
        display = mins+display;
        updateDisplay();

    }

    public  void clear(){
        display = "";
        result = "";
        screen_display.setText(display);
        screen_result.setText(result);
    }

    public void updateDisplay(){
        screen_display.setText(display);
    }

    public void onSetNumbres(View view){
        Button btn_numbre = (Button) view;
        display += btn_numbre.getText();
        updateDisplay();
    }

    public void onSetOperator (View view ){
        Button btn_operator = (Button) view;

        if (result != ""){
            String _display = result;
            clear();
            display = _display;

        }

        display += btn_operator.getText();
        currentOperator = btn_operator.getText().toString();
        updateDisplay();
    }


    public void onClear(View view){
        clear();

    }

    public  void clickEqual(View view){


        if(screen_display.getText() == "" && screen_display.getText() == currentOperator ){
            Toast.makeText(MainActivity.this , "Please entre your operation" ,Toast.LENGTH_LONG).show();
        }

        onEqual();
    }

    public boolean onEqual() {
//
//        if (currentOperator == "") return false;
//        String [] _display = display.split(Pattern.quote(currentOperator));
//        if (_display.length<2) return false;
//        if(currentOperator.equals("÷")) currentOperator ="/";
//        result = String.valueOf(operator(_display[0], _display[1], currentOperator));
//        screen_result.setText(String.valueOf(result));

        String cal =  screen_display.getText().toString();
       cal =  cal.replace("x","*").replace("÷","/").replace("%","/100");

        Expression e = new ExpressionBuilder(cal)
                .build();
        double result = e.evaluate();
        screen_result.setText(String.valueOf(result));
        return true;
    }

    public void onClickPercent(View view){

        if (result!=""){

        display = result += "%" ;
            updateDisplay();

        double amount = Double.parseDouble(screen_result.getText().toString());

        double res = (amount / 100.0f) ;
        screen_result.setText(String.valueOf(res));}


        }
    }




