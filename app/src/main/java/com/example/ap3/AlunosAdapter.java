package com.example.ap3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AlunosAdapter extends BaseAdapter {
    
    private Context context;
    private List<Aluno> alunos;
    private OnAlunoActionListener listener;
    
    public interface OnAlunoActionListener {
        void onAlunoRemovido();
    }
    
    public AlunosAdapter(Context context, List<Aluno> alunos, OnAlunoActionListener listener) {
        this.context = context;
        this.alunos = alunos;
        this.listener = listener;
    }
    
    @Override
    public int getCount() {
        return alunos.size();
    }
    
    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_aluno, parent, false);
        }
        
        Aluno aluno = alunos.get(position);
        
        TextView txtNome = convertView.findViewById(R.id.txtNomeAluno);
        TextView txtMatricula = convertView.findViewById(R.id.txtMatriculaAluno);
        TextView txtCurso = convertView.findViewById(R.id.txtCursoAluno);
        Button btnEditar = convertView.findViewById(R.id.btnEditarAluno);
        Button btnExcluir = convertView.findViewById(R.id.btnExcluirAluno);
        
        txtNome.setText(aluno.getNomeCompleto());
        txtMatricula.setText("Matrícula: " + aluno.getMatricula());
        txtCurso.setText("Curso: " + aluno.getCurso());
        
        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, CadastroAlunoActivity.class);
            intent.putExtra("aluno", aluno);
            context.startActivity(intent);
        });
        
        btnExcluir.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                .setTitle("Confirmar Exclusão")
                .setMessage("Deseja realmente excluir o aluno " + aluno.getNomeCompleto() + "?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AlunosManager.getInstance().removerAluno(aluno.getId());
                    Toast.makeText(context, "Aluno removido com sucesso!", Toast.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onAlunoRemovido();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
        });
        
        return convertView;
    }
} 