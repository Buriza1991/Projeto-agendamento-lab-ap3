package com.example.ap3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    EditText edtNome, edtMatricula;
    Button btnEntrar, btnNovoCadastro;
    TextView txtTitulo, txtInstrucoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Inicializar AlunosManager se não foi inicializado
        AlunosManager.getInstance().initialize(this);

        // Inicializar views
        txtTitulo = findViewById(R.id.txtTitulo);
        txtInstrucoes = findViewById(R.id.txtInstrucoes);
        edtNome = findViewById(R.id.edtNome);
        edtMatricula = findViewById(R.id.edtMatricula);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);

        // Configurar textos
        txtTitulo.setText("Login do Sistema");
        txtInstrucoes.setText("Digite seu nome e matrícula para entrar");

        // Configurar listeners
        btnEntrar.setOnClickListener(v -> realizarLogin());
        btnNovoCadastro.setOnClickListener(v -> irParaCadastro());

        // Verificar se não há usuários cadastrados e redirecionar para cadastro
        if (!AlunosManager.getInstance().temAlunosCadastrados()) {
            Toast.makeText(this, "Nenhum usuário cadastrado. Faça seu primeiro cadastro!", Toast.LENGTH_LONG).show();
            irParaCadastro();
        }
    }

    private void realizarLogin() {
        String nome = edtNome.getText().toString().trim();
        String matricula = edtMatricula.getText().toString().trim();

        // Validações básicas
        if (nome.isEmpty()) {
            edtNome.setError("Digite seu nome");
            edtNome.requestFocus();
            return;
        }

        if (matricula.isEmpty()) {
            edtMatricula.setError("Digite sua matrícula");
            edtMatricula.requestFocus();
            return;
        }

        // Verificar se matrícula existe
        if (!AlunosManager.getInstance().matriculaExiste(matricula)) {
            Toast.makeText(this, "Matrícula não encontrada. Faça novo cadastro.", Toast.LENGTH_LONG).show();
            return;
        }

        // Validar login
        Aluno alunoLogado = AlunosManager.getInstance().validarLogin(nome, matricula);
        
        if (alunoLogado != null) {
            // Login bem-sucedido
            salvarSessaoUsuario(alunoLogado);
        } else {
            // Nome não confere com a matrícula
            String nomeCorreto = AlunosManager.getInstance().getNomePorMatricula(matricula);
            Toast.makeText(this, 
                "Nome não confere com a matrícula.\nNome cadastrado: " + nomeCorreto, 
                Toast.LENGTH_LONG).show();
        }
    }

    private void salvarSessaoUsuario(Aluno alunoLogado) {
        // Salvar dados da sessão
        SharedPreferences preferences = getSharedPreferences("usuario_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome_usuario", alunoLogado.getNomeCompleto());
        editor.putString("matricula_usuario", alunoLogado.getMatricula());
        editor.putString("id_usuario", alunoLogado.getId());
        editor.apply();

        // Mostrar boas-vindas
        Toast.makeText(this, 
            "Bem-vindo(a), " + alunoLogado.getNomeCompleto() + "!", 
            Toast.LENGTH_SHORT).show();

        // Ir para o menu
        Intent intent = new Intent(WelcomeActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void irParaCadastro() {
        Intent intent = new Intent(WelcomeActivity.this, CadastroInicialActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Permitir sair do app apenas se não há usuários cadastrados
        if (!AlunosManager.getInstance().temAlunosCadastrados()) {
            super.onBackPressed();
        } else {
            // Se há usuários, mostrar opção de cadastro
            Toast.makeText(this, "Use \"Novo Cadastro\" se não possui matrícula", Toast.LENGTH_SHORT).show();
        }
    }
}

