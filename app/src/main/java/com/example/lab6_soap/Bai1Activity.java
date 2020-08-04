package com.example.lab6_soap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;

public class Bai1Activity extends AppCompatActivity implements View.OnClickListener {


    private EditText edtFC;
    private Button btnF;
    private Button btnC;
    private TextView tvResult;

    String strFC;
    int convertStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        edtFC = (EditText) findViewById(R.id.edtFC);
        btnF = (Button) findViewById(R.id.btnF);
        btnC = (Button) findViewById(R.id.btnC);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnF.setOnClickListener(this);
        btnC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnF:
                Log.e("call",  "btnF");
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION,
                        Constants.F_TO_C_METHOD_NAME);
                convertStyle = 1;
                break;
            case R.id.btnC:
                Log.e("call",  "btnC");
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION,
                        Constants.C_TO_F_METHOD_NAME);
                convertStyle = 0;
                break;
        }
    }

    //create and execute asynctask to get response from W3school server
    private void invokeAsyncTask(String convertParams, String soapAction,
                                 String methodName) {
        new ConvertTemperatureTask(Bai1Activity.this, soapAction, methodName,
                convertParams).execute(edtFC.getText()
                .toString().trim());
    }

    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        double resultTemperature = Double.parseDouble(result);
        //parse String to double
        if (convertStyle == 0) {// C degree to F degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Celsius = "
                    + String.format("%.2f", resultTemperature) + " degree Fahrenheit");
        } else {// F degree to C degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Fahrenheit = "
                    + String.format("%.2f", resultTemperature) + " degree Celsius");
        }
    }
}