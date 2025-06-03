package com.example.ap3;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;

public class AgendamentoActivity extends AppCompatActivity {

    Spinner spinnerSalas;
    EditText edtData;
    Button btnAgendar, btnVoltar;
    TextView txtBoasVindas;
    GridView gridHorarios;
    HorariosAdapter horariosAdapter;

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
        setContentView(R.layout.activity_agendamento); // ✅ use o novo layout XML

        spinnerSalas = findViewById(R.id.spinnerSalas);
        edtData = findViewById(R.id.edtData);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtBoasVindas = findViewById(R.id.txtBoasVindas);
        gridHorarios = findViewById(R.id.gridHorarios);
        SharedPreferences preferences = getSharedPreferences("usuario_prefs", MODE_PRIVATE);
        String nomeUsuario = preferences.getString("nome_usuario", "Usuário");

        // Mostrar nome no TextView
        txtBoasVindas.setText("Olá, " + nomeUsuario + "!");

        ArrayAdapter<String> salasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salas);
        salasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalas.setAdapter(salasAdapter);

        Collections.addAll(listaHorarios, horarios);

        // Configurar listeners para atualizar o grid quando mudar sala ou data
        spinnerSalas.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                atualizarGridHorarios();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        gridHorarios.setOnItemClickListener((parent, view, position, id) -> {
            String horarioSelecionado = listaHorarios.get(position);
            String salaSelecionada = spinnerSalas.getSelectedItem().toString();
            String data = edtData.getText().toString();
            
            if (data.isEmpty()) {
                Toast.makeText(this, "Por favor, selecione uma data primeiro", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Verificar se o horário está disponível
            if (ReservasManager.getInstance().isHorarioReservado(salaSelecionada, data, horarioSelecionado)) {
                Toast.makeText(this, "Este horário já está reservado!", Toast.LENGTH_SHORT).show();
            } else {
                if (horariosReservados.contains(horarioSelecionado)) {
                    horariosReservados.remove(horarioSelecionado);
                    Toast.makeText(this, "Horário desmarcado: " + horarioSelecionado, Toast.LENGTH_SHORT).show();
                } else {
                    horariosReservados.add(horarioSelecionado);
                    Toast.makeText(this, "Horário selecionado: " + horarioSelecionado, Toast.LENGTH_SHORT).show();
                }
                atualizarGridHorarios();
            }
        });

        edtData.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int ano = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                edtData.setText(dataSelecionada);
                horariosReservados.clear();
                atualizarGridHorarios();
            }, ano, mes, dia);
            datePicker.show();
        });

        btnAgendar.setOnClickListener(v -> {
            String salaSelecionada = spinnerSalas.getSelectedItem().toString();
            String data = edtData.getText().toString();

            if (data.isEmpty()) {
                Toast.makeText(this, "Selecione a data!", Toast.LENGTH_SHORT).show();
            } else if (horariosReservados.isEmpty()) {
                Toast.makeText(this, "Selecione pelo menos um horário!", Toast.LENGTH_SHORT).show();
            } else {
                // Abrir tela de confirmação
                Intent intent = new Intent(AgendamentoActivity.this, ConfirmarAgendamentoActivity.class);
                intent.putExtra("sala", salaSelecionada);
                intent.putExtra("data", data);
                intent.putStringArrayListExtra("horarios", new ArrayList<>(horariosReservados));
                startActivity(intent);
            }
        });

        // Adicionar listener para o botão voltar
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(AgendamentoActivity.this, MenuActivity.class);
            startActivity(intent);
            finish(); // Finaliza a activity atual para não ficar na pilha
        });
    }
    
    private void atualizarGridHorarios() {
        String salaSelecionada = spinnerSalas.getSelectedItem().toString();
        String data = edtData.getText().toString();
        
        if (!data.isEmpty()) {
            horariosAdapter = new HorariosAdapter(this, listaHorarios, horariosReservados, salaSelecionada, data);
            gridHorarios.setAdapter(horariosAdapter);
        }
    }
}
