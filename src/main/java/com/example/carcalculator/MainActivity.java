package com.example.carcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Spinner termSpinner;
    EditText enterCarPrice, enterDownPayment, enterAPR;
    TextView disMonthlyPrice,carPriceTxt;
    int carCost, downPayment, term;
    double interestRate, monthlyCost, totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        termSpinner = findViewById(R.id.termSpinner);
        enterCarPrice = findViewById(R.id.enterCarPrice);
        enterDownPayment = findViewById(R.id.enterDownPayment);
        enterAPR = findViewById(R.id.enterAPR);
        disMonthlyPrice = findViewById(R.id.disMonthlyPrice);
        carPriceTxt = findViewById(R.id.carPriceTxt);


        ArrayAdapter<String> termAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.terms));

        termSpinner.setAdapter(termAdapter);

        enterAPR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    calculateMonthlyCost();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTerm = parent.getItemAtPosition(position).toString();

                if(selectedTerm.equals("12 months")){
                    term = 12;
                }
                if(selectedTerm.equals("24 months")){
                    term = 24;
                }
                if(selectedTerm.equals("36 months")){
                    term = 36;
                }
                if(selectedTerm.equals("48 months")){
                    term = 48;
                }
                if(selectedTerm.equals("60 months")){
                    term = 60;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void calculateMonthlyCost(){

        if(enterCarPrice.getText().toString().equals("")){
            carCost = 0;
        }
        else{
            carCost = Integer.parseInt(enterCarPrice.getText().toString());
        }

        if(enterDownPayment.getText().toString().equals("")){
            downPayment = 0;
        }
        else{
            downPayment = Integer.parseInt(enterDownPayment.getText().toString());
        }

        if(enterAPR.getText().toString().equals("") || enterAPR.getText().toString().equals(".") ){
            interestRate = 0;
        }
        else{
            interestRate = (Double.parseDouble(enterAPR.getText().toString())) / 100.0;
        }




        totalCost =  ( Math.round(monthlyCost * term * 100.0) / 100.0) + downPayment;



        disMonthlyPrice.setText("Estimated Total Price: $" + formatNum(totalCost));


        System.out.println(monthlyCost);

    }

    public String formatNum(Double d){
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(d);
    }
}