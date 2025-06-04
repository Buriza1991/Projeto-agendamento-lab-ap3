package com.example.ap3;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class ConfirmarAgendamentoActivity extends AppCompatActivity {

    TextView txtSalaSelecionada, txtDataSelecionada, txtHorariosSelecionados;
    EditText edtNome, edtMatricula;
    Button btnConfirmarAgendamento, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_agendamento);

        // Inicializar views
        txtSalaSelecionada = findViewById(R.id.txtSalaSelecionada);
        txtDataSelecionada = findViewById(R.id.txtDataSelecionada);
        txtHorariosSelecionados = findViewById(R.id.txtHorariosSelecionados);
        edtNome = findViewById(R.id.edtNome);
        edtMatricula = findViewById(R.id.edtMatricula);
        btnConfirmarAgendamento = findViewById(R.id.btnConfirmarAgendamento);
        btnCancelar = findViewById(R.id.btnCancelar);

        // Receber dados da intent
        Intent intent = getIntent();
        String sala = intent.getStringExtra("sala");
        String data = intent.getStringExtra("data");
        ArrayList<String> horarios = intent.getStringArrayListExtra("horarios");

        // Exibir dados selecionados
        txtSalaSelecionada.setText("Sala: " + sala);
        txtDataSelecionada.setText("Data: " + data);

        // Filtro para permitir apenas letras e espaços no nome
        InputFilter letrasFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i)) && !Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        edtNome.setFilters(new InputFilter[]{letrasFilter});
        if (horarios != null && !horarios.isEmpty()) {
            StringBuilder horariosStr = new StringBuilder("Horários: ");
            for (int i = 0; i < horarios.size(); i++) {
                horariosStr.append(horarios.get(i));
                if (i < horarios.size() - 1) {
                    horariosStr.append(", ");
                }
            }
            txtHorariosSelecionados.setText(horariosStr.toString());
        }

        // Configurar botão confirmar
        btnConfirmarAgendamento.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            String matricula = edtMatricula.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show();
                return;
            }

            if (matricula.isEmpty()) {
                Toast.makeText(this, "Por favor, digite sua matrícula", Toast.LENGTH_SHORT).show();
                return;
            }

            // Salvar reservas no sistema global
            ReservasManager.getInstance().adicionarReserva(sala, data, new HashSet<>(horarios));

            // Criar agendamento completo
            String agendamentoCompleto = "Agendamento confirmado!\n" +
                    "Nome: " + nome + "\n" +
                    "Matrícula: " + matricula + "\n" +
                    "Sala: " + sala + "\n" +
                    "Data: " + data + "\n" +
                    "Horários: " + String.join(", ", horarios);

            // Salvar no histórico
            Agendamento agendamento = new Agendamento(sala, data);
            agendamento.setNome(nome);
            agendamento.setMatricula(matricula);
            agendamento.setHorarios(horarios);
            HistoricoSingleton.getInstance().adicionarAgendamento(agendamento);

            Toast.makeText(this, agendamentoCompleto, Toast.LENGTH_LONG).show();

            // Voltar para o menu principal
            Intent intentMenu = new Intent(ConfirmarAgendamentoActivity.this, MenuActivity.class);
            intentMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentMenu);
            finish();
        });

        // Configurar botão cancelar
        btnCancelar.setOnClickListener(v -> {
            finish(); // Volta para a tela anterior
        });
    }


} 