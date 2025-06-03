package com.example.ap3;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSalas;
    EditText edtData;
    Button btnAgendar;
    TextView txtBoasVindas;
    ListView listHorarios;

    String[] horarios = {
            "08h00", "08h30", "09h00", "09h30",
            "10h00", "10h30", "11h00", "11h30",
            "12h00", "12h30", "13h00", "13h30",
            "14h00", "14h30", "15h00"
    };

    String[] salas = {"Laboratório 1", "Laboratório 2", "Sala de Reunião", "Auditório"};

    ArrayList<String> listaHorarios = new ArrayList<>();
    HashSet<String> horariosReservados = new HashSet<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);  // seu layout
        SharedPreferences preferences = getSharedPreferences("usuario_prefs", MODE_PRIVATE);
        String nomeUsuario = preferences.getString("nome_usuario", "Usuário");

        txtBoasVindas.setText("Olá, " + nomeUsuario + "!");



        spinnerSalas = findViewById(R.id.spinnerSalas);
        edtData = findViewById(R.id.edtData);
        btnAgendar = findViewById(R.id.btnAgendar);
        txtBoasVindas = findViewById(R.id.txtBoasVindas);
        listHorarios = findViewById(R.id.gridHorarios);

        // Configura spinner de salas
        ArrayAdapter<String> salasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salas);
        salasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalas.setAdapter(salasAdapter);

        // Adiciona horários na lista
        Collections.addAll(listaHorarios, horarios);

        // Adapter da lista de horários com cores
        ArrayAdapter<String> horariosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaHorarios) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String horario = getItem(position);
                if (horariosReservados.contains(horario)) {
                    view.setBackgroundColor(Color.RED); // Reservado
                } else {
                    view.setBackgroundColor(Color.GREEN); // Disponível
                }
                return view;
            }
        };
        listHorarios.setAdapter(horariosAdapter);

        // Clique nos horários da lista
        listHorarios.setOnItemClickListener((parent, view, position, id) -> {
            String horarioSelecionado = listaHorarios.get(position);
            if (horariosReservados.contains(horarioSelecionado)) {
                Toast.makeText(this, "Horário já reservado!", Toast.LENGTH_SHORT).show();
            } else {
                horariosReservados.add(horarioSelecionado);
                view.setBackgroundColor(Color.RED);
                Toast.makeText(this, "Horário reservado: " + horarioSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

        // Ao clicar no campo de data, abre DatePicker
        edtData.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int ano = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                edtData.setText(dataSelecionada);
            }, ano, mes, dia);
            datePicker.show();
        });

        // Botão Agendar
        btnAgendar.setOnClickListener(v -> {
            String salaSelecionada = spinnerSalas.getSelectedItem().toString();
            String data = edtData.getText().toString();

            if (data.isEmpty()) {
                Toast.makeText(this, "Selecione a data!", Toast.LENGTH_SHORT).show();
            } else {
                // Cria o objeto agendamento com sala e data
                Agendamento agendamento = new Agendamento(salaSelecionada, data);

                // Adiciona no histórico singleton
                HistoricoSingleton.getInstance().adicionarAgendamento(agendamento);

                Toast.makeText(this, "Reserva feita para " + salaSelecionada + " em " + data, Toast.LENGTH_LONG).show();
            }
        });
    }
}
