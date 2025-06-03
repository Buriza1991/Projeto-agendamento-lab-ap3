package com.example.ap3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroInicialActivity extends AppCompatActivity {

    EditText edtNomeCompletoInicial, edtIdadeInicial, edtCursoInicial, 
             edtTelefoneInicial, edtEnderecoInicial;
    Button btnCadastrarInicial, btnJaTenhoCadastro;
    TextView txtMatriculaGerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_inicial);

        // Inicializar AlunosManager se não foi inicializado
        AlunosManager.getInstance().initialize(this);

        // Inicializar views
        txtMatriculaGerada = findViewById(R.id.txtMatriculaGerada);
        edtNomeCompletoInicial = findViewById(R.id.edtNomeCompletoInicial);
        edtIdadeInicial = findViewById(R.id.edtIdadeInicial);
        edtCursoInicial = findViewById(R.id.edtCursoInicial);
        edtTelefoneInicial = findViewById(R.id.edtTelefoneInicial);
        edtEnderecoInicial = findViewById(R.id.edtEnderecoInicial);
        btnCadastrarInicial = findViewById(R.id.btnCadastrarInicial);
        btnJaTenhoCadastro = findViewById(R.id.btnJaTenhoCadastro);

        // Mostrar qual será a próxima matrícula
        String proximaMatricula = AlunosManager.getInstance().gerarProximaMatricula();
        txtMatriculaGerada.setText("Sua matrícula será: " + proximaMatricula);

        // Configurar listeners
        btnCadastrarInicial.setOnClickListener(v -> realizarCadastro());
        btnJaTenhoCadastro.setOnClickListener(v -> irParaLogin());
    }

    private void realizarCadastro() {
        String nomeCompleto = edtNomeCompletoInicial.getText().toString().trim();
        String idade = edtIdadeInicial.getText().toString().trim();
        String curso = edtCursoInicial.getText().toString().trim();
        String telefone = edtTelefoneInicial.getText().toString().trim();
        String endereco = edtEnderecoInicial.getText().toString().trim();

        // Validações
        if (nomeCompleto.isEmpty()) {
            edtNomeCompletoInicial.setError("Nome é obrigatório");
            edtNomeCompletoInicial.requestFocus();
            return;
        }

        if (idade.isEmpty()) {
            edtIdadeInicial.setError("Idade é obrigatória");
            edtIdadeInicial.requestFocus();
            return;
        }

        if (curso.isEmpty()) {
            edtCursoInicial.setError("Curso é obrigatório");
            edtCursoInicial.requestFocus();
            return;
        }

        if (telefone.isEmpty()) {
            edtTelefoneInicial.setError("Telefone é obrigatório");
            edtTelefoneInicial.requestFocus();
            return;
        }

        if (endereco.isEmpty()) {
            edtEnderecoInicial.setError("Endereço é obrigatório");
            edtEnderecoInicial.requestFocus();
            return;
        }

        // Realizar cadastro
        try {
            String matriculaGerada = AlunosManager.getInstance().adicionarAluno(
                nomeCompleto, idade, curso, telefone, endereco
            );

            // Mostrar matrícula gerada
            Toast.makeText(this, 
                "Cadastro realizado!\nSua matrícula é: " + matriculaGerada + 
                "\nAnote esta matrícula!", 
                Toast.LENGTH_LONG).show();

            // Aguardar um pouco para o usuário ver a matrícula
            new android.os.Handler().postDelayed(() -> {
                // Ir direto para o menu (usuário já está "logado")
                Intent intent = new Intent(CadastroInicialActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }, 3000); // 3 segundos

        } catch (Exception e) {
            Toast.makeText(this, "Erro ao realizar cadastro: " + e.getMessage(), 
                Toast.LENGTH_SHORT).show();
        }
    }

    private void irParaLogin() {
        Intent intent = new Intent(CadastroInicialActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Se não há usuários cadastrados, permitir sair do app
        if (!AlunosManager.getInstance().temAlunosCadastrados()) {
            super.onBackPressed();
        } else {
            // Se há usuários, ir para login
            irParaLogin();
        }
    }
} 