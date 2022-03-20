package com.example.calculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);

        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String str) {
        String oldStr = display.getText().toString();
        int pos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, pos);
        String rightStr = oldStr.substring(pos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(str);
        } else {
            display.setText(String.format("%s%s%s", leftStr, str, rightStr));
            display.setSelection(pos + 1);
        }
    }

    public void zero(View view) {
        updateText("0");
    }

    public void one(View view) {
        updateText("1");
    }

    public void two(View view) {
        updateText("2");
    }

    public void three(View view) {
        updateText("3");
    }

    public void four(View view) {
        updateText("4");
    }

    public void five(View view) {
        updateText("5");
    }

    public void six(View view) {
        updateText("6");
    }

    public void seven(View view) {
        updateText("7");
    }

    public void eight(View view) {
        updateText("8");
    }

    public void nine(View view) {
        updateText("9");
    }

    public void clear(View view) {
        display.setText("");
    }

    public void plusMinus(View view) {
        updateText("-");
    }

    public void parent(View view) {
        int pos = display.getSelectionStart();
        int openBar = 0;
        int closeBar = 0;
        int textLength = display.getText().length();

        for (int i = 0; i < pos; i++) {
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openBar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closeBar += 1;
            }
        }

        if (openBar == closeBar || display.getText().toString().substring(textLength - 1, textLength).equals(("("))) {
            updateText("(");
        } else if (closeBar < openBar && !display.getText().toString().substring(textLength - 1, textLength).equals(("("))) {
            updateText(")");
        }
        display.setSelection(pos + 1);
    }

    public void divide(View view) {
        updateText("÷");
    }

    public void multiply(View view) {
        updateText("×");
    }

    public void add(View view) {
        updateText("+");
    }

    public void subtract(View view) {
        updateText("-");
    }

    public void equals(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void point(View view) {
        updateText(".");
    }

}