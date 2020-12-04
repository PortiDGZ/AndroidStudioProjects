package com.pforbes.binarycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int currentValue;
    int operand;
    boolean didEnterOperand;
    String entry;
    private enum Operation {
        AND_OP, OR_OP, XOR_OP, NAND_OP, NOR_OP, NXOR_OP, NO_OP, ADD_OP, SUB_OP, MOD_OP, MULT_OP
    };
    private Operation mOperation;

    TextView display_binary;
    TextView display_decimal;

    //// GENERAL ACTIVITY CALLBACKS ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display_binary = (TextView) findViewById(R.id.result_binary);
        display_decimal = (TextView) findViewById(R.id.result_decimal);

        currentValue = 0;
        operand = 0;
        mOperation = Operation.NO_OP;
        didEnterOperand = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //// KEYPAD CALLBACKS ////

    public void onEnterZero(View view) {
        currentValue <<= 1;
        updateDisplay();
    }

    public void onEnterOne(View view) {
        currentValue = (currentValue << 1) + 1;
        updateDisplay();
    }

    public void onClear(View view) {
        currentValue = 0;
        didEnterOperand = false;
        entry = null;
        updateDisplay();
    }

    public void onAllClear(View view) {
        currentValue = 0;
        didEnterOperand = false;
        entry = null;
        updateDisplay();
    }

    public void computeAnd(View view) {
        mOperation = Operation.AND_OP;
        display_decimal.setText(display_decimal.getText() + " & ");
        exchange();
    }

    public void computeOr(View view) {
        mOperation = Operation.OR_OP;
        display_decimal.setText(display_decimal.getText() + " | ");
        exchange();
    }

    public void computeNot(View view) {
        currentValue = invertInPlace(currentValue);
        updateDisplay();
    }

    public void computeXor(View view) {
        mOperation = Operation.XOR_OP;
        display_decimal.setText(display_decimal.getText() + " ^ ");
        exchange();
    }

    public void computeNand(View view) {
        mOperation = Operation.NAND_OP;
        display_decimal.setText(display_decimal.getText() + " &! ");
        exchange();
    }

    public void computeNor(View view) {
        mOperation = Operation.NOR_OP;
        display_decimal.setText(display_decimal.getText() + " |! ");
        exchange();
    }

    public void computeNxor(View view) {
        mOperation = Operation.NXOR_OP;
        display_decimal.setText(display_decimal.getText() + " ^! ");
        exchange();
    }

    public void computeAdd(View view) {
        mOperation = Operation.ADD_OP;
        display_decimal.setText(display_decimal.getText() + " + ");
        exchange();
    }

    public void computeSub(View view) {
        mOperation = Operation.SUB_OP;
        display_decimal.setText(display_decimal.getText() + " - ");
        exchange();
    }

    public void computeMod(View view) {
        mOperation = Operation.MOD_OP;
        display_decimal.setText(display_decimal.getText() + " mod ");
        exchange();
    }

    public void computeMult(View view) {
        mOperation = Operation.MULT_OP;
        display_decimal.setText(display_decimal.getText() + " * ");
        exchange();
    }

    public void calculate(View view) {
        if(didEnterOperand) {
            switch (mOperation) {
                case AND_OP:
                    currentValue &= operand;
                    break;
                case OR_OP:
                    currentValue |= operand;
                    break;
                case XOR_OP:
                    currentValue ^= operand;
                    break;
                case NAND_OP:
                    currentValue = operand & invertInPlace(currentValue);
                    break;
                case NOR_OP:
                    currentValue = operand | invertInPlace(currentValue);
                    break;
                case NXOR_OP:
                    currentValue = operand ^ invertInPlace(currentValue);
                    break;
                case ADD_OP:
                    currentValue += operand;
                    break;
                case SUB_OP:
                    currentValue = operand - currentValue;
                    break;
                case MULT_OP:
                    currentValue *= operand;
                    break;
                case MOD_OP:
                    currentValue = operand % currentValue;
                    break;
            }
            display_binary.setText(Integer.toBinaryString(currentValue));
            display_decimal.setText(currentValue + "");
            didEnterOperand = false;
            currentValue = 0;
            operand = 0;
        }
    }

    //// PRIVATE METHODS ////

    private void updateDisplay() {
        display_binary.setText(Integer.toBinaryString(currentValue));
        if(!didEnterOperand) {
            display_decimal.setText(currentValue + "");
        } else {
            display_decimal.setText(entry + currentValue);
        }
    }

    private void exchange() {
        operand = currentValue;
        currentValue = 0;
        didEnterOperand = true;
        entry = display_decimal.getText().toString();
    }

    private int invertInPlace(int x) {
        int k = Integer.toBinaryString(x).length();
        int y = ~x & ((1 << k) - 1);
        return y;
    }

}
