package com.example.ap3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    ListView listHistorico;
    TextView txtSemHistorico;
    Button btnVoltarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listHistorico = findViewById(R.id.listHistorico);
        txtSemHistorico = findViewById(R.id.txtSemHistorico);
        btnVoltarMenu = findViewById(R.id.btnVoltarMenu);

        // Configurar botão voltar
        btnVoltarMenu.setOnClickListener(v -> {
            Intent intent = new Intent(HistoricoActivity.this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        atualizarHistorico();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Atualizar a lista quando voltar para a activity
        atualizarHistorico();
    }
    
    private void atualizarHistorico() {
        // Pega o histórico de agendamentos do singleton
        ArrayList<Agendamento> historico = HistoricoSingleton.getInstance().getHistoricoAgendamentos();

        if (historico.isEmpty()) {
            // Mostrar mensagem de lista vazia
            txtSemHistorico.setVisibility(View.VISIBLE);
            listHistorico.setVisibility(View.GONE);
        } else {
            // Mostrar lista com histórico
            txtSemHistorico.setVisibility(View.GONE);
            listHistorico.setVisibility(View.VISIBLE);
            
            // Usar o adapter customizado
            HistoricoAdapter adapter = new HistoricoAdapter(this, historico);
            listHistorico.setAdapter(adapter);
        }
    }
    
    @Override
    public void onBackPressed() {
        // Voltar para o menu
        Intent intent = new Intent(HistoricoActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
