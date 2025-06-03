package com.example.ap3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 segundos
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        // Inicializar AlunosManager
        AlunosManager.getInstance().initialize(this);
        
        TextView txtSplash = findViewById(R.id.txtSplash);
        txtSplash.setText("Sistema de Agendamento\nde Laboratório");
        
        // Delay antes de decidir para onde ir
        new Handler().postDelayed(() -> {
            decidirProximaTela();
        }, SPLASH_DELAY);
    }
    
    private void decidirProximaTela() {
        Intent intent;
        
        // Se não há usuários cadastrados, vai direto para cadastro inicial
        if (!AlunosManager.getInstance().temAlunosCadastrados()) {
            intent = new Intent(SplashActivity.this, CadastroInicialActivity.class);
        } else {
            // Se há usuários, vai para login
            intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        }
        
        startActivity(intent);
        finish();
    }
} 