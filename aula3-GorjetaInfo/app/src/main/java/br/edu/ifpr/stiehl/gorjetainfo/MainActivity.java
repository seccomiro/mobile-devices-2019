package br.edu.ifpr.stiehl.gorjetainfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, TextWatcher {
    private EditText txtValor;
    private EditText txtGorjeta;
    private EditText txtTotal;
    private TextView labelPercentual;
    private SeekBar seekPercentual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValor = findViewById(R.id.txtValor);
        txtGorjeta = findViewById(R.id.txtGorjeta);
        txtTotal = findViewById(R.id.txtTotal);
        labelPercentual = findViewById(R.id.labelPercentual);
        seekPercentual = findViewById(R.id.seekPercentual);

        seekPercentual.setOnSeekBarChangeListener(this);
        txtValor.addTextChangedListener(this);
    }

    private int getPercentual() {
        return seekPercentual.getProgress();
    }

    private float getValor() {
        return Float.parseFloat(txtValor.getText().toString());
    }

    private void calcular(float valor, int percentual) {
        float gorjeta = valor * percentual / 100;
        float total = valor + gorjeta;

        labelPercentual.setText(percentual + "%");
        txtGorjeta.setText(gorjeta + "");
        txtTotal.setText(total + "");
    }

    private void calcular() {
        calcular(getValor(), getPercentual());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        calcular();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        calcular();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
