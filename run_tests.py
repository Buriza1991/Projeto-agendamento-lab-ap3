#!/usr/bin/env python3
"""
Script para executar testes end-to-end do app de agendamento
"""

import subprocess
import sys
import os

def check_python():
    """Verifica se Python está instalado"""
    try:
        subprocess.run([sys.executable, "--version"], check=True, capture_output=True)
        print("✅ Python encontrado!")
        return True
    except:
        print("❌ Python não encontrado!")
        print("📥 Instale Python de: https://www.python.org/downloads/")
        return False

def install_dependencies():
    """Instala dependências Python"""
    try:
        print("📦 Instalando dependências...")
        subprocess.run([sys.executable, "-m", "pip", "install", "-r", "requirements.txt"], check=True)
        print("✅ Dependências instaladas!")
        return True
    except subprocess.CalledProcessError as e:
        print(f"❌ Erro ao instalar dependências: {e}")
        return False

def run_tests():
    """Executa os testes"""
    try:
        print("🧪 Executando testes end-to-end...")
        subprocess.run([sys.executable, "-m", "pytest", "tests/", "-v"], check=True)
        print("✅ Testes executados com sucesso!")
        return True
    except subprocess.CalledProcessError as e:
        print(f"❌ Erro ao executar testes: {e}")
        return False

def main():
    """Função principal"""
    print("🚀 Iniciando execução dos testes end-to-end...")
    print("=" * 50)
    
    # Verificar Python
    if not check_python():
        return
    
    # Instalar dependências
    if not install_dependencies():
        return
    
    # Executar testes
    if not run_tests():
        return
    
    print("=" * 50)
    print("🎉 Processo concluído!")

if __name__ == "__main__":
    main() 