package uta.fisei.ejercicio03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextRoman1;
    private EditText editTextRoman2;
    private EditText editTextRoman3;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRoman1 = findViewById(R.id.editTextRoman1);
        editTextRoman2 = findViewById(R.id.editTextRoman2);
        editTextRoman3 = findViewById(R.id.editTextRoman3);
        textViewResult = findViewById(R.id.textViewResult);
    }

    public void onClickMultiplyNumbers(View view) {
        String roman1 = editTextRoman1.getText().toString().toUpperCase();
        String roman2 = editTextRoman2.getText().toString().toUpperCase();
        String roman3 = editTextRoman3.getText().toString().toUpperCase();

        int decimal1 = romanToDecimal(roman1);
        int decimal2 = romanToDecimal(roman2);
        int decimal3 = romanToDecimal(roman3);

        int result = (decimal1*decimal2*decimal3);
        String resultRoman = decimalToRoman(result);

        String resulText= "";
        if (result > 3999){
            resulText = "Resultado supera MMMCMXCIX (3999) " + resultRoman+" || "+resulText;
        }else{
            resulText = "Resultado en Romano: " + resultRoman + "\nResultado en Decimal: " + result;
        }
        textViewResult.setText(resulText);
    }

    private int romanToDecimal(String roman) {
        if (roman == null || roman.isEmpty()) {
            return 0;
        }

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int numericValue = romanCharToDecimal(currentChar);

            if (numericValue < 0) {
                return 0;
            }

            if (numericValue < prevValue) {
                result -= numericValue;
            } else {
                result += numericValue;
            }

            prevValue = numericValue;
        }

        if (result > 3999){
            Toast.makeText(this,"Un numero es mayor a MMMCMXCIX (3999)",Toast.LENGTH_SHORT).show();
            return 0;
        }

        return result;
    }

    private int romanCharToDecimal(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    private String decimalToRoman(int decimal) {
        if (decimal <= 0 || decimal > 3999) {
            return "Número fuera de rango";
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        int thousandsPlace = decimal / 1000;
        int hundredsPlace = (decimal % 1000) / 100;
        int tensPlace = (decimal % 100) / 10;
        int onesPlace = decimal % 10;

        return thousands[thousandsPlace] + hundreds[hundredsPlace] + tens[tensPlace] + ones[onesPlace];
    }

}