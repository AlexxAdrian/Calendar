package com.example.mycalendarapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    // Declaración del CalendarView
    private CalendarView calendarView;

    // Nombre del archivo SharedPreferences
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String DATE_KEY = "selectedDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Inicialización del CalendarView
        calendarView = findViewById(R.id.calendarView);

        // Establecer un listener para la selección de fechas
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // month va de 0 (enero) a 11 (diciembre)
                int correctedMonth = month + 1;

                // Guardar la fecha seleccionada en una cadena
                String selectedDate = dayOfMonth + "/" + correctedMonth + "/" + year;

                // Guardar la fecha en SharedPreferences
                saveDateInSharedPreferences(selectedDate);

                // Mostrar la fecha seleccionada usando un Toast
                Toast.makeText(CalendarActivity.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        // Cargar la fecha guardada (si existe) y establecerla en el CalendarView
        String savedDate = getDateFromSharedPreferences();
        if (savedDate != null) {
            Toast.makeText(this, "Fecha guardada: " + savedDate, Toast.LENGTH_SHORT).show();
        }
    }

    // Método para guardar la fecha en SharedPreferences
    private void saveDateInSharedPreferences(String date) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE_KEY, date);
        editor.apply(); // Usar apply() para guardar de manera asíncrona
    }

    // Método para obtener la fecha guardada de SharedPreferences
    private String getDateFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(DATE_KEY, null);
    }
}
