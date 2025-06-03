package com.example.ap3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class HorariosAdapter extends BaseAdapter {
    
    private Context context;
    private ArrayList<String> horarios;
    private Set<String> horariosReservadosGlobal; // Horários já reservados no sistema
    private Set<String> horariosSelecionados; // Horários selecionados pelo usuário atual
    private String sala;
    private String data;
    
    public HorariosAdapter(Context context, ArrayList<String> horarios, 
                          Set<String> horariosSelecionados, String sala, String data) {
        this.context = context;
        this.horarios = horarios;
        this.horariosSelecionados = horariosSelecionados;
        this.sala = sala;
        this.data = data;
        this.horariosReservadosGlobal = ReservasManager.getInstance().getHorariosReservados(sala, data);
    }
    
    public void atualizarReservas() {
        this.horariosReservadosGlobal = ReservasManager.getInstance().getHorariosReservados(sala, data);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return horarios.size();
    }
    
    @Override
    public Object getItem(int position) {
        return horarios.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_horario, parent, false);
        }
        
        String horario = horarios.get(position);
        
        TextView txtHorario = convertView.findViewById(R.id.txtHorario);
        TextView txtStatus = convertView.findViewById(R.id.txtStatus);
        LinearLayout container = (LinearLayout) convertView;
        
        txtHorario.setText(horario);
        
        // Verificar status do horário
        if (horariosReservadosGlobal.contains(horario)) {
            // Horário já reservado por outra pessoa
            container.setBackgroundColor(Color.parseColor("#FFCDD2")); // Vermelho claro
            txtStatus.setText("Ocupado");
            txtStatus.setTextColor(Color.parseColor("#D32F2F")); // Vermelho
            txtHorario.setTextColor(Color.parseColor("#666666")); // Cinza
            container.setEnabled(false);
        } else if (horariosSelecionados.contains(horario)) {
            // Horário selecionado pelo usuário atual
            container.setBackgroundColor(Color.parseColor("#C8E6C9")); // Verde claro
            txtStatus.setText("Selecionado");
            txtStatus.setTextColor(Color.parseColor("#388E3C")); // Verde
            txtHorario.setTextColor(Color.BLACK);
            container.setEnabled(true);
        } else {
            // Horário disponível
            container.setBackgroundColor(Color.WHITE);
            txtStatus.setText("Disponível");
            txtStatus.setTextColor(Color.parseColor("#4CAF50")); // Verde
            txtHorario.setTextColor(Color.BLACK);
            container.setEnabled(true);
        }
        
        return convertView;
    }
} 