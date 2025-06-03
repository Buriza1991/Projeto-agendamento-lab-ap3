package com.example.ap3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroAlunoActivity extends AppCompatActivity {

    EditText edtNomeCompleto, edtMatriculaAluno, edtIdade, edtCurso, edtTelefone, edtEndereco;
    Button btnSalvar, btnCancelar;
    TextView txtTitulo;
    
    Aluno alunoEditando = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        // Inicializar views
        txtTitulo = findViewById(R.id.txtTituloCadastro);
        edtNomeCompleto = findViewById(R.id.edtNomeCompleto);
        edtMatriculaAluno = findViewById(R.id.edtMatriculaAluno);
        edtIdade = findViewById(R.id.edtIdade);
        edtCurso = findViewById(R.id.edtCurso);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEndereco = findViewById(R.id.edtEndereco);
        btnSalvar = findViewById(R.id.btnSalvarAluno);
        btnCancelar = findViewById(R.id.btnCancelarCadastro);

        // Verificar se é edição
        if (getIntent().hasExtra("aluno")) {
            alunoEditando = (Aluno) getIntent().getSerializableExtra("aluno");
            txtTitulo.setText("Editar Aluno");
            preencherCampos();
        }

        btnSalvar.setOnClickListener(v -> salvarAluno());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void preencherCampos() {
        if (alunoEditando != null) {
            edtNomeCompleto.setText(alunoEditando.getNomeCompleto());
            edtMatriculaAluno.setText(alunoEditando.getMatricula());
            edtIdade.setText(alunoEditando.getIdade());
            edtCurso.setText(alunoEditando.getCurso());
            edtTelefone.setText(alunoEditando.getTelefone());
            edtEndereco.setText(alunoEditando.getEndereco());
        }
    }

    private void salvarAluno() {
        // Obter valores dos campos
        String nome = edtNomeCompleto.getText().toString().trim();
        String matricula = edtMatriculaAluno.getText().toString().trim();
        String idadeStr = edtIdade.getText().toString().trim();
        String curso = edtCurso.getText().toString().trim();
        String telefone = edtTelefone.getText().toString().trim();
        String endereco = edtEndereco.getText().toString().trim();

        // Validações
        if (nome.isEmpty()) {
            edtNomeCompleto.setError("Nome é obrigatório");
            edtNomeCompleto.requestFocus();
            return;
        }

        if (matricula.isEmpty()) {
            edtMatriculaAluno.setError("Matrícula é obrigatória");
            edtMatriculaAluno.requestFocus();
            return;
        }

        if (idadeStr.isEmpty()) {
            edtIdade.setError("Idade é obrigatória");
            edtIdade.requestFocus();
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade < 1 || idade > 150) {
                edtIdade.setError("Idade inválida");
                edtIdade.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            edtIdade.setError("Idade inválida");
            edtIdade.requestFocus();
            return;
        }

        if (curso.isEmpty()) {
            edtCurso.setError("Curso é obrigatório");
            edtCurso.requestFocus();
            return;
        }

        if (telefone.isEmpty()) {
            edtTelefone.setError("Telefone é obrigatório");
            edtTelefone.requestFocus();
            return;
        }

        if (endereco.isEmpty()) {
            edtEndereco.setError("Endereço é obrigatório");
            edtEndereco.requestFocus();
            return;
        }

        // Verificar se a matrícula já existe
        if (alunoEditando == null) {
            // Novo aluno
            if (AlunosManager.getInstance().existeMatricula(matricula)) {
                edtMatriculaAluno.setError("Matrícula já cadastrada");
                edtMatriculaAluno.requestFocus();
                Toast.makeText(this, "Esta matrícula já está cadastrada!", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            // Editando aluno
            if (AlunosManager.getInstance().existeMatriculaExcetoAluno(matricula, alunoEditando.getId())) {
                edtMatriculaAluno.setError("Matrícula já cadastrada");
                edtMatriculaAluno.requestFocus();
                Toast.makeText(this, "Esta matrícula já está cadastrada!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Criar ou atualizar aluno
        if (alunoEditando == null) {
            // Novo aluno
            Aluno novoAluno = new Aluno(nome, matricula, idade, curso, telefone, endereco);
            AlunosManager.getInstance().adicionarAluno(novoAluno);
            Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            // Atualizar aluno existente
            alunoEditando.setNomeCompleto(nome);
            alunoEditando.setMatricula(matricula);
            alunoEditando.setIdade(idade);
            alunoEditando.setCurso(curso);
            alunoEditando.setTelefone(telefone);
            alunoEditando.setEndereco(endereco);
            AlunosManager.getInstance().atualizarAluno(alunoEditando);
            Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
} 