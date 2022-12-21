package com.example.calculator2022;

/*
    Created at 2022 / 12 / 20
    Created by Kavindu Hasintha

 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;

    private Button btnClear;
    private Button btnPercentage;
    private Button btnParentheses;
    private Button btnDivide;
    private Button btnMultiply;
    private Button btnSubtract;
    private Button btnAdd;
    private Button btnEqual;
    private Button btnDecimal;

    private EditText edtTxtDisplay;
    private TextView txtPrevious;

    private boolean Parentheses = false;
    private boolean absoluteValue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);

        btnClear = findViewById(R.id.btnClear);
        btnPercentage = findViewById(R.id.btnPercentage);
        btnParentheses = findViewById(R.id.btnParentheses);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnDecimal = findViewById(R.id.btnDecimal);

        edtTxtDisplay = findViewById(R.id.edtTxtDisplay);
        txtPrevious = findViewById(R.id.txtPrevious);

        edtTxtDisplay.setShowSoftInputOnFocus(false);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("1");
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("2");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("3");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("4");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("5");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("6");
            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("7");
            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("8");
            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("9");
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("0");
            }
        });
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(".");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTxtDisplay.setText("");
                txtPrevious.setText("");
            }
        });

        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("%");
            }
        });

        btnParentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addParentheses();
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("÷");
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("×");
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("-");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add("+");
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEqual();
            }
        });

    }

    public void add(String x) {
        String exp = edtTxtDisplay.getText().toString();
        int cursorPosition = edtTxtDisplay.getSelectionStart();

        String leftExp = exp.substring(0, cursorPosition);
        String rightExp = exp.substring(cursorPosition);

        edtTxtDisplay.setText(String.format("%s%s%s", leftExp, x, rightExp));
        edtTxtDisplay.setSelection(cursorPosition + x.length());
    }

    public void addParentheses() {
        String exp = edtTxtDisplay.getText().toString();
        int cursorPosition = edtTxtDisplay.getSelectionStart();

        String leftExp = exp.substring(0, cursorPosition);
        String rightExp = exp.substring(cursorPosition);

        if (!Parentheses) {
            edtTxtDisplay.setText(String.format("%s%s%s", leftExp, "(", rightExp));
            Parentheses = true;
        } else {
            edtTxtDisplay.setText(String.format("%s%s%s", leftExp, ")", rightExp));
            Parentheses = false;
        }
        edtTxtDisplay.setSelection(cursorPosition + 1);
    }

    public void addEqual() {
        String exp = edtTxtDisplay.getText().toString();
        txtPrevious.setText(exp);

        if (absoluteValue) {
            exp = "abs(" + exp + ")";
        }

        exp.replaceAll("÷", "/");
        exp.replaceAll("×", "*");

        exp.replaceAll("√", "sqrt");
        exp.replaceAll("π", "3.141592653589793238");

        Expression mathEquation = new Expression(exp);
        String result = String.valueOf(mathEquation.calculate());

        edtTxtDisplay.setText(result);
        edtTxtDisplay.setSelection(result.length());
    }

    public void btnLnClick(View view) {
        add("ln(");
        Parentheses = true;
    }

    public void btnLogClick(View view) {
        add("log(");
        Parentheses = true;
    }

    public void btnEClick(View view) {
        add("e");
    }

    public void btnXSquaredClick(View view) {
        add("^(2)");
    }

    public void btnXTripledClick(View view) {
        add("^(3)");
    }

    public void btnXPowerYClick(View view) {
        add("^(");
        Parentheses = true;
    }

    public void btnSquareRootClick(View view) {
        add("√(");
        Parentheses = true;
    }

    public void btnSinClick(View view) {
        add("sin(");
        Parentheses = true;
    }

    public void btnCosClick(View view) {
        add("cos(");
        Parentheses = true;
    }

    public void btnTanClick(View view) {
        add("tan(");
        Parentheses = true;
    }

    public void btnArcSinClick(View view) {
        add("arcsin(");
        Parentheses = true;
    }

    public void btnArcCosClick(View view) {
        add("arccos(");
        Parentheses = true;
    }

    public void btnArcTanClick(View view) {
        add("arctan(");
        Parentheses = true;
    }

    public void btnPiClick(View view) {
        add("π");
        Parentheses = true;
    }

    public void btnAbsoluteValueClick(View view) {
        absoluteValue = true;
        addEqual();
    }
}