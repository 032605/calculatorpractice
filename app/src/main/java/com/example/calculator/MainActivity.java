package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private TextView txt_expression;
    private TextView txt_result;
    private String operator = null;
    private String strOper = "";
    private String firstValue = "";
    private String secondValue = "";
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_table);

        txt_result = findViewById(R.id.txt_result);
        txt_expression = findViewById(R.id.txt_expression);

        for(int i=0; i<16; i++) {
            String buttonID = "b_num" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            findViewById(resID).setOnClickListener(mClickListener);
        }
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Button button = (Button) view;
            //txt_result.setText(txt_result.getText().toString() + button.getText().toString());

            String clickValue = button.getText().toString();

            switch (clickValue) {

                case "+":
                case "-":
                case "*":
                case "/":
                    strOper = clickValue;
                case "=":
                    if ("".equals(firstValue)) {
                        firstValue = txt_result.getText().toString();
                        txt_result.setText("");
                        txt_expression.setText(firstValue+strOper);
                    } else if (!"".equals(operator)) {
                        secondValue = txt_result.getText().toString();
                        txt_expression.setText(firstValue+strOper+secondValue+"=");
                        Integer cal = 0;
                        switch (operator) {
                            case "+": cal = Integer.parseInt(firstValue) + Integer.parseInt(secondValue); break;
                            case "-": cal = Integer.parseInt(firstValue) - Integer.parseInt(secondValue); break;
                            case "*": cal = Integer.parseInt(firstValue) * Integer.parseInt(secondValue); break;
                            case "/": cal = Integer.parseInt(firstValue) / Integer.parseInt(secondValue); break;
                        }

                        txt_result.setText(cal.toString());
                        firstValue = "";
                        isInit = true;

                        if ("=".equals(clickValue)) {
                            operator = "";
                            return;
                        }
                        firstValue = cal.toString();
                    }
                    operator = clickValue;

                    break ;

                case "DEL":
                    String text = txt_result.getText().toString();
                    txt_result.setText(text.substring(0, text.length() -1));

                    break;

                default:
                    if (isInit) {
                        isInit = false;
                        txt_result.setText(clickValue);
                    } else {
                        txt_result.setText(txt_result.getText().toString() + clickValue);
                    }
            }

        }
    };
}




