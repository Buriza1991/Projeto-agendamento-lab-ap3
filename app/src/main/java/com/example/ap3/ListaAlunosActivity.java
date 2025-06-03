package com.example.ap3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity implements AlunosAdapter.OnAlunoActionListener {

    ListView listAlunos;
    TextView txtSemAlunos;
    Button btnNovoAluno, btnVoltarMenu;
    AlunosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listAlunos = findViewById(R.id.listAlunos);
        txtSemAlunos = findViewById(R.id.txtSemAlunos);
        btnNovoAluno = findViewById(R.id.btnNovoAluno);
        btnVoltarMenu = findViewById(R.id.btnVoltarMenu);

        btnNovoAluno.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAlunosActivity.this, CadastroAlunoActivity.class);
            startActivity(intent);
        });

        btnVoltarMenu.setOnClickListener(v -> {
            finish();
        });

        atualizarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista() {
        List<Aluno> alunos = AlunosManager.getInstance().getAlunos();

        if (alunos.isEmpty()) {
            txtSemAlunos.setVisibility(View.VISIBLE);
            listAlunos.setVisibility(View.GONE);
        } else {
            txtSemAlunos.setVisibility(View.GONE);
            listAlunos.setVisibility(View.VISIBLE);
            
            adapter = new AlunosAdapter(this, alunos, this);
            listAlunos.setAdapter(adapter);
        }
    }

    @Override
    public void onAlunoRemovido() {
        atualizarLista();
    }
} 