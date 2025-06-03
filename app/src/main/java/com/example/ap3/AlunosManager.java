package com.example.ap3;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class AlunosManager {
    
    private static AlunosManager instance;
    private List<Aluno> alunos;
    private Context context;
    private SharedPreferences preferences;
    
    private AlunosManager() {
        this.alunos = new ArrayList<>();
    }
    
    public static AlunosManager getInstance() {
        if (instance == null) {
            instance = new AlunosManager();
        }
        return instance;
    }
    
    public void initialize(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences("alunos_prefs", Context.MODE_PRIVATE);
        carregarAlunos();
    }
    
    // Gerar próxima matrícula sequencial
    public String gerarProximaMatricula() {
        int proximoNumero = alunos.size() + 1;
        return String.format("%04d", proximoNumero);
    }
    
    // Verificar se existe alunos cadastrados
    public boolean temAlunosCadastrados() {
        return !alunos.isEmpty();
    }
    
    // Adicionar novo aluno com matrícula automática
    public String adicionarAluno(String nomeCompleto, String idade, String curso, String telefone, String endereco) {
        String novaMatricula = gerarProximaMatricula();
        
        Aluno novoAluno = new Aluno();
        novoAluno.setId(String.valueOf(System.currentTimeMillis()));
        novoAluno.setNomeCompleto(nomeCompleto);
        novoAluno.setMatricula(novaMatricula);
        novoAluno.setIdade(idade);
        novoAluno.setCurso(curso);
        novoAluno.setTelefone(telefone);
        novoAluno.setEndereco(endereco);
        
        alunos.add(novoAluno);
        salvarAlunos();
        
        return novaMatricula;
    }
    
    // Adicionar aluno completo (para compatibilidade)
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        salvarAlunos();
    }
    
    // Atualizar aluno existente
    public void atualizarAluno(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(aluno.getId())) {
                alunos.set(i, aluno);
                salvarAlunos();
                break;
            }
        }
    }
    
    // Remover aluno por ID
    public void removerAluno(String id) {
        alunos.removeIf(aluno -> aluno.getId().equals(id));
        salvarAlunos();
    }
    
    // Validar login com nome e matrícula
    public Aluno validarLogin(String nome, String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                // Matrícula existe, verificar se o nome confere
                if (aluno.getNomeCompleto().trim().equalsIgnoreCase(nome.trim())) {
                    return aluno; // Login válido
                } else {
                    // Nome não confere com a matrícula
                    return null; 
                }
            }
        }
        // Matrícula não encontrada
        return null;
    }
    
    // Verificar se matrícula existe
    public boolean matriculaExiste(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }
    
    // Verificar se matrícula existe (compatibilidade)
    public boolean existeMatricula(String matricula) {
        return matriculaExiste(matricula);
    }
    
    // Verificar se matrícula existe exceto para um aluno específico
    public boolean existeMatriculaExcetoAluno(String matricula, String alunoId) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula) && !aluno.getId().equals(alunoId)) {
                return true;
            }
        }
        return false;
    }
    
    // Obter nome do usuário pela matrícula (para facilitar correção)
    public String getNomePorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno.getNomeCompleto();
            }
        }
        return null;
    }
    
    // Obter aluno por matrícula
    public Aluno getAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
    
    // Obter aluno por ID
    public Aluno getAlunoPorId(String id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                return aluno;
            }
        }
        return null;
    }
    
    // Obter lista de todos os alunos (compatibilidade)
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
    
    // Obter lista de todos os alunos
    public List<Aluno> getTodosAlunos() {
        return new ArrayList<>(alunos);
    }
    
    // Salvar alunos no SharedPreferences
    private void salvarAlunos() {
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            
            // Salvar quantidade de alunos
            editor.putInt("total_alunos", alunos.size());
            
            // Salvar cada aluno individualmente
            for (int i = 0; i < alunos.size(); i++) {
                Aluno aluno = alunos.get(i);
                String prefix = "aluno_" + i + "_";
                
                editor.putString(prefix + "id", aluno.getId());
                editor.putString(prefix + "nome", aluno.getNomeCompleto());
                editor.putString(prefix + "matricula", aluno.getMatricula());
                editor.putString(prefix + "idade", aluno.getIdade());
                editor.putString(prefix + "curso", aluno.getCurso());
                editor.putString(prefix + "telefone", aluno.getTelefone());
                editor.putString(prefix + "endereco", aluno.getEndereco());
            }
            
            editor.apply();
        }
    }
    
    // Carregar alunos do SharedPreferences
    private void carregarAlunos() {
        if (preferences != null) {
            alunos.clear();
            
            int totalAlunos = preferences.getInt("total_alunos", 0);
            
            for (int i = 0; i < totalAlunos; i++) {
                String prefix = "aluno_" + i + "_";
                
                String id = preferences.getString(prefix + "id", "");
                String nome = preferences.getString(prefix + "nome", "");
                String matricula = preferences.getString(prefix + "matricula", "");
                String idade = preferences.getString(prefix + "idade", "");
                String curso = preferences.getString(prefix + "curso", "");
                String telefone = preferences.getString(prefix + "telefone", "");
                String endereco = preferences.getString(prefix + "endereco", "");
                
                if (!id.isEmpty() && !nome.isEmpty() && !matricula.isEmpty()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(id);
                    aluno.setNomeCompleto(nome);
                    aluno.setMatricula(matricula);
                    aluno.setIdade(idade);
                    aluno.setCurso(curso);
                    aluno.setTelefone(telefone);
                    aluno.setEndereco(endereco);
                    
                    alunos.add(aluno);
                }
            }
        }
    }
    
    // Limpar todos os dados (para testes)
    public void limparTodosDados() {
        alunos.clear();
        if (preferences != null) {
            preferences.edit().clear().apply();
        }
    }
} 