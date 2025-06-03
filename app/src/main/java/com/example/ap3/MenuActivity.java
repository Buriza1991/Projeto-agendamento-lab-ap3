package com.example.ap3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnAgendamento, btnHistorico, btnVoltar, btnCadastroAlunos;
    TextView txtBoasVindasMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // seu layout

        txtBoasVindasMenu = findViewById(R.id.txtBoasVindasMenu);
        btnAgendamento = findViewById(R.id.btnAgendamento);
        btnHistorico = findViewById(R.id.btnHistorico);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCadastroAlunos = findViewById(R.id.btnCadastroAlunos);

        // Recuperar nome do SharedPreferences
        SharedPreferences preferences = getSharedPreferences("usuario_prefs", MODE_PRIVATE);
        String nomeUsuario = preferences.getString("nome_usuario", "Usuário");

        // Mostrar nome no TextView
        txtBoasVindasMenu.setText("Olá, " + nomeUsuario + "!");

        // liga menu com tela de agendamento
        btnAgendamento.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, AgendamentoActivity.class);
            startActivity(intent);
        });

        btnHistorico.setOnClickListener(v -> {
            // Redirecionar para histórico
            Intent intent = new Intent(MenuActivity.this, HistoricoActivity.class);
            startActivity(intent);
        });

        btnCadastroAlunos.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ListaAlunosActivity.class);
            startActivity(intent);
        });

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
