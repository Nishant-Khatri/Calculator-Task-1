package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket, buttonDivide, buttonMultiply, buttonPlus, buttonSubtract, buttonEquals;
    MaterialButton button1, button0, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot, buttonAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOpenBracket = findViewById(R.id.buttonopenbracket);
        buttonOpenBracket.setOnClickListener(this);
        buttonCloseBracket = findViewById(R.id.buttonclosebracket);
        buttonCloseBracket.setOnClickListener(this);
        buttonDivide = findViewById(R.id.buttondevide);
        buttonDivide.setOnClickListener(this);
        buttonMultiply = findViewById(R.id.buttonmultiply);
        buttonMultiply.setOnClickListener(this);
        buttonPlus = findViewById(R.id.buttonplus);
        buttonPlus.setOnClickListener(this);
        buttonSubtract = findViewById(R.id.buttonsubtract);
        buttonSubtract.setOnClickListener(this);
        buttonEquals = findViewById(R.id.buttonequals);
        buttonEquals.setOnClickListener(this);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(this);
        buttonAC = findViewById(R.id.buttonAC);
        buttonAC.setOnClickListener(this);
        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();

        String data = solutionTv.getText().toString();
        if (btnText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (btnText.equals("=")) {

            solutionTv.setText(resultTv.getText());
            return;
        }
        if (btnText.equals("C")) {
            if(!data.isEmpty()){
            data = data.substring(0, data.length() - 1);}
        } else
        {
            data += btnText;
        }
            solutionTv.setText(data);
        String res=getResult(data);
        if(!res.equals("Invalid")){
            resultTv.setText(res);
        }

    }

    String getResult(String data)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalres=context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            double res=Double.parseDouble(finalres);
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            finalres = decimalFormat.format(res);
            return finalres;
        }
        catch (Exception e){
            return "Invalid";
        }
    }
    }
