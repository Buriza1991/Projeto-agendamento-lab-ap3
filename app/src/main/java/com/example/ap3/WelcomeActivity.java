package com.example.ap3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;
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
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);

        // Configurar textos
        txtTitulo.setText("Login do Sistema");
        txtInstrucoes.setText("Digite seu email e senha para entrar");

        // Configurar inputType para email e senha já está definido no XML

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
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();

        // Validações básicas
        if (email.isEmpty()) {
            edtEmail.setError("Digite seu email");
            edtEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()) {
            edtSenha.setError("Digite sua senha");
            edtSenha.requestFocus();
            return;
        }

        // Validar formato de email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Digite um email válido");
            edtEmail.requestFocus();
            return;
        }

        // Verificar se email existe
        if (!AlunosManager.getInstance().emailExiste(email)) {
            Toast.makeText(this, "Email não encontrado. Faça novo cadastro.", Toast.LENGTH_LONG).show();
            return;
        }

        // Validar login
        Aluno alunoLogado = AlunosManager.getInstance().validarLoginEmailSenha(email, senha);

        if (alunoLogado != null) {
            // Login bem-sucedido
            salvarSessaoUsuario(alunoLogado);
        } else {
            // Senha incorreta
            Toast.makeText(this, "Senha incorreta. Tente novamente.", Toast.LENGTH_LONG).show();
            edtSenha.setText("");
            edtSenha.requestFocus();
        }
    }

    private void salvarSessaoUsuario(Aluno alunoLogado) {
        // Salvar dados da sessão
        SharedPreferences preferences = getSharedPreferences("usuario_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome_usuario", alunoLogado.getNomeCompleto());
        editor.putString("matricula_usuario", alunoLogado.getMatricula());
        editor.putString("email_usuario", alunoLogado.getEmail());
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
            Toast.makeText(this, "Use \"Novo Cadastro\" se não possui conta", Toast.LENGTH_SHORT).show();
        }
    }
}
