package com.example.ap3;

import java.util.ArrayList;

public class HistoricoSingleton {

    private static HistoricoSingleton instance;
    private ArrayList<Agendamento> historicoAgendamentos;

    private HistoricoSingleton() {
        historicoAgendamentos = new ArrayList<>();
    }

    public static synchronized HistoricoSingleton getInstance() {
        if (instance == null) {
            instance = new HistoricoSingleton();
        }
        return instance;
    }

    public ArrayList<Agendamento> getHistoricoAgendamentos() {
        return historicoAgendamentos;
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        historicoAgendamentos.add(agendamento);
    }
}
