package com.example.calculatorkraski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private EditText editTextWidth, editTextHeight, editTextPaintConsumption, editTextLayers, editTextCanVolume;
    private TextView textViewResult;
    private RadioButton radioButton10Percent, radioButton15Percent, radioButtonNoReserve;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим все необходимые View элементы
        editTextWidth = findViewById(R.id.editTextWidth);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextPaintConsumption = findViewById(R.id.editTextPaintConsumption);
        editTextLayers = findViewById(R.id.editTextLayers);
        editTextCanVolume = findViewById(R.id.editTextCanVolume);
        textViewResult = findViewById(R.id.textViewResult);
        radioButton10Percent = findViewById(R.id.radioButton10Percent);
        radioButton15Percent = findViewById(R.id.radioButton15Percent);
        radioButtonNoReserve = findViewById(R.id.radioButtonNoReserve);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFieldsNotEmpty()) {
                    calculatePaint();
                }
            }
        });

    }
    private boolean checkFieldsNotEmpty() {
        if (TextUtils.isEmpty(editTextWidth.getText().toString())) {
            editTextWidth.setError("Введите ширину");
            return false;
        }
        if (TextUtils.isEmpty(editTextHeight.getText().toString())) {
            editTextHeight.setError("Введите высоту");
            return false;
        }
        if (TextUtils.isEmpty(editTextPaintConsumption.getText().toString())) {
            editTextPaintConsumption.setError("Введите расход краски");
            return false;
        }
        if (TextUtils.isEmpty(editTextLayers.getText().toString())) {
            editTextLayers.setError("Введите количество слоев");
            return false;
        }
        if (TextUtils.isEmpty(editTextCanVolume.getText().toString())) {
            editTextCanVolume.setError("Введите объем банки");
            return false;
        }
        return true;
    }
    private void calculatePaint() {
// Получаем введенные пользователем значения
        double width = Double.parseDouble(editTextWidth.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        double paintConsumption = Double.parseDouble(editTextPaintConsumption.getText().toString());
        int layers = Integer.parseInt(editTextLayers.getText().toString());
        double canVolume = Double.parseDouble(editTextCanVolume.getText().toString());

// Выполняем расчет в зависимости от выбранного варианта запаса
        double paintNeeded;
        if (radioButton10Percent.isChecked()) {
            paintNeeded = width * height * paintConsumption * layers * 1.1;
        } else if (radioButton15Percent.isChecked()) {
            paintNeeded = width * height * paintConsumption * layers * 1.15;
        } else {
            paintNeeded = width * height * paintConsumption * layers;
        }

// Вычисляем необходимое количество банок краски
        int cansNeeded = (int) Math.ceil(paintNeeded / canVolume);

// Выводим результат на экран
        textViewResult.setText("Результат: " + cansNeeded + " банок краски");
    }
}
