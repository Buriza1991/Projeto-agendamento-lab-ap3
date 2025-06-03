package com.example.ap3;

import java.util.ArrayList;

public class Agendamento {
    private String sala;
    private String data;
    private String nome;
    private String matricula;
    private ArrayList<String> horarios;

    public Agendamento(String sala, String data) {
        this.sala = sala;
        this.data = data;
        this.horarios = new ArrayList<>();
    }

    public String getSala() {
        return sala;
    }

    public String getData() {
        return data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }
}
