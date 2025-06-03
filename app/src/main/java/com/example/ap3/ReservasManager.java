package com.example.ap3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReservasManager {
    private static ReservasManager instance;
    private Map<String, Set<String>> reservas; // Chave: "sala|data", Valor: conjunto de horários reservados
    
    private ReservasManager() {
        reservas = new HashMap<>();
    }
    
    public static ReservasManager getInstance() {
        if (instance == null) {
            instance = new ReservasManager();
        }
        return instance;
    }
    
    // Adicionar uma reserva
    public void adicionarReserva(String sala, String data, Set<String> horarios) {
        String chave = sala + "|" + data;
        Set<String> horariosReservados = reservas.get(chave);
        
        if (horariosReservados == null) {
            horariosReservados = new HashSet<>();
            reservas.put(chave, horariosReservados);
        }
        
        horariosReservados.addAll(horarios);
    }
    
    // Verificar se um horário está reservado
    public boolean isHorarioReservado(String sala, String data, String horario) {
        String chave = sala + "|" + data;
        Set<String> horariosReservados = reservas.get(chave);
        
        if (horariosReservados != null) {
            return horariosReservados.contains(horario);
        }
        
        return false;
    }
    
    // Obter todos os horários reservados para uma sala e data
    public Set<String> getHorariosReservados(String sala, String data) {
        String chave = sala + "|" + data;
        Set<String> horariosReservados = reservas.get(chave);
        
        if (horariosReservados != null) {
            return new HashSet<>(horariosReservados); // Retorna uma cópia
        }
        
        return new HashSet<>();
    }
    
    // Verificar se pode reservar (não está ocupado)
    public boolean podeReservar(String sala, String data, String horario) {
        return !isHorarioReservado(sala, data, horario);
    }
} 