package com.example.ap3;

import java.io.Serializable;

public class Aluno implements Serializable {
    private String id;
    private String nomeCompleto;
    private String matricula;
    private String idade;
    private String curso;
    private String telefone;
    private String endereco;

    public Aluno() {
        // Gerar ID único baseado no timestamp
        this.id = String.valueOf(System.currentTimeMillis());
    }

    public Aluno(String nomeCompleto, String matricula, int idade, String curso, String telefone, String endereco) {
        this();
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.idade = String.valueOf(idade);
        this.curso = curso;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = String.valueOf(idade);
    }

    // Setter que aceita String para compatibilidade
    public void setIdade(String idade) {
        this.idade = idade;
    }

    // Método para obter idade como int (para validações)
    public int getIdadeInt() {
        try {
            return Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            return 0; // Valor padrão
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
} 