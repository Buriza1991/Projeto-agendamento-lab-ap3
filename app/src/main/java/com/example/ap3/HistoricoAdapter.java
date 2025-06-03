package com.example.ap3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoricoAdapter extends BaseAdapter {
    
    private Context context;
    private ArrayList<Agendamento> agendamentos;
    
    public HistoricoAdapter(Context context, ArrayList<Agendamento> agendamentos) {
        this.context = context;
        this.agendamentos = agendamentos;
    }
    
    @Override
    public int getCount() {
        return agendamentos.size();
    }
    
    @Override
    public Object getItem(int position) {
        return agendamentos.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_historico, parent, false);
        }
        
        // Obter o agendamento atual
        Agendamento agendamento = agendamentos.get(position);
        
        // Vincular as views
        TextView txtNome = convertView.findViewById(R.id.txtNome);
        TextView txtMatricula = convertView.findViewById(R.id.txtMatricula);
        TextView txtSala = convertView.findViewById(R.id.txtSala);
        TextView txtData = convertView.findViewById(R.id.txtData);
        TextView txtHorarios = convertView.findViewById(R.id.txtHorarios);
        
        // Preencher os dados
        txtNome.setText("Nome: " + (agendamento.getNome() != null ? agendamento.getNome() : "Não informado"));
        txtMatricula.setText("Matrícula: " + (agendamento.getMatricula() != null ? agendamento.getMatricula() : "Não informada"));
        txtSala.setText("Sala: " + agendamento.getSala());
        txtData.setText("Data: " + agendamento.getData());
        
        // Formatar horários
        if (agendamento.getHorarios() != null && !agendamento.getHorarios().isEmpty()) {
            String horarios = String.join(", ", agendamento.getHorarios());
            txtHorarios.setText("Horários: " + horarios);
        } else {
            txtHorarios.setText("Horários: Não informados");
        }
        
        return convertView;
    }
} 