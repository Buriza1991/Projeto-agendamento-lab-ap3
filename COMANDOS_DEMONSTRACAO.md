# 🧪 Sistema de Agendamento de Laboratório - Comandos de Demonstração

## 📱 Preparação do Ambiente

### Pré-requisitos
```bash
# Verificar versão do Android Studio
android --version

# Verificar Java JDK (versão 11)
java -version
javac -version

# Verificar Gradle
gradle --version
```

### Setup do Projeto
```bash
# Clone do repositório
git clone <url-do-repositorio>
cd Projeto-agendamento-lab-ap3-main

# Permissões para Gradle (Linux/Mac)
chmod +x gradlew

# Build do projeto
./gradlew build

# Limpar build anterior (se necessário)
./gradlew clean
```

## 🚀 Comandos de Execução

### Ambiente de Desenvolvimento
```bash
# Abrir no Android Studio
studio .

# Build via linha de comando
./gradlew assembleDebug

# Instalar APK em dispositivo conectado
./gradlew installDebug

# Executar testes unitários
./gradlew test

# Executar testes de instrumentação
./gradlew connectedAndroidTest
```

### Comandos ADB (Android Debug Bridge)
```bash
# Verificar dispositivos conectados
adb devices

# Instalar APK manualmente
adb install app/build/outputs/apk/debug/app-debug.apk

# Ver logs da aplicação
adb logcat | grep "com.example.ap3"

# Limpar dados da aplicação
adb shell pm clear com.example.ap3

# Fazer screenshot
adb exec-out screencap -p > screenshot.png
```

## 🎬 Roteiro de Demonstração

### 1. Primeiro Acesso (Cadastro Inicial)
```
AÇÃO: Abrir aplicativo pela primeira vez
MOSTRAR: 
- Tela splash com nome do sistema
- Redirecionamento automático para cadastro (se não há usuários)
- Tela de cadastro inicial

COMANDOS DEMO:
1. Mostrar matrícula gerada automaticamente: "0001"
2. Preencher campos:
   - Nome: "João Silva"
   - Idade: "25"
   - Curso: "Engenharia"
   - Telefone: "(11) 99999-9999"
   - Endereço: "Rua das Flores, 123"
3. Pressione: Botão "Cadastrar"
4. Observe mensagem: "Sua matrícula é: 0001"
```

### 2. Sistema de Login com Matrícula
```
AÇÃO: Fazer login com matrícula como chave
MOSTRAR:
- Tela de login com campos Nome e Matrícula
- Validação de credenciais
- Mensagens de erro específicas

COMANDOS DEMO:
TESTE 1 - Login correto:
- Nome: "João Silva"
- Matrícula: "0001"
- Resultado: Login bem-sucedido

TESTE 2 - Nome errado:
- Nome: "Maria"
- Matrícula: "0001"
- Resultado: "Nome não confere com a matrícula"

TESTE 3 - Matrícula inexistente:
- Nome: "João"
- Matrícula: "9999"
- Resultado: "Matrícula não encontrada"
```

### 3. Segundo Cadastro (Matrícula Sequencial)
```
AÇÃO: Demonstrar cadastro de segundo usuário
SEQUÊNCIA:
1. Na tela de login, tocar "Novo Cadastro"
2. Mostrar nova matrícula gerada: "0002"
3. Preencher dados do segundo usuário:
   - Nome: "Maria Santos"
   - Idade: "22"
   - Curso: "Medicina"
4. Cadastrar e obter matrícula "0002"
5. Fazer login com nova matrícula
```

### 4. Demonstração de Agendamento (Usuário Logado)
```
AÇÃO: Fazer agendamento após login válido
SEQUÊNCIA:
1. Login bem-sucedido mostra: "Bem-vindo(a), João Silva!"
2. Toque "Fazer Agendamento"
3. Selecione "Laboratório 1" no spinner
4. Toque no campo data
5. Selecione data no DatePicker: "15/12/2024"
6. Visualize grade de horários:
   - Verde = Disponível
   - Vermelho = Reservado
7. Toque em horário disponível: "14h00"
8. Confirme o agendamento

RESULTADO ESPERADO:
- Toast: "Reserva feita para Laboratório 1 em 15/12/2024"
- Agendamento vinculado ao usuário logado
- Horário fica vermelho (reservado)
```

### 5. Histórico Personalizado
```
AÇÃO: Visualizar histórico do usuário logado
SEQUÊNCIA:
1. Menu → "Ver Histórico"
2. Lista agendamentos do usuário atual
3. Mostrar informações personalizadas:
   - Nome do usuário logado
   - Matrícula
   - Agendamentos realizados

COMANDOS DEMO:
- Mostrar filtro por usuário
- Demonstrar persistência de dados
```

## 🔧 Comandos de Debug e Troubleshooting

### Logs Úteis
```bash
# Log específico da aplicação
adb logcat -s "AgendamentoLab"

# Filtrar por nivel de log
adb logcat *:E  # Apenas erros

# Salvar logs em arquivo
adb logcat > debug_logs.txt
```

### Reset do Estado para Testes
```bash
# Limpar todos os dados da aplicação
adb shell pm clear com.example.ap3

# Reiniciar aplicação (volta ao estado inicial)
adb shell am force-stop com.example.ap3
adb shell monkey -p com.example.ap3 1

# Simular primeiro acesso
adb shell rm -rf /data/data/com.example.ap3/shared_prefs/
```

### Performance Testing
```bash
# Monitor de memória
adb shell dumpsys meminfo com.example.ap3

# Monitor de CPU
adb shell top | grep com.example.ap3

# Capturar trace de performance
adb shell am start -S -W com.example.ap3/.SplashActivity
```

## 📊 Dados de Teste

### Cenário 1: Primeiro Usuário
```
Nome: "Professor João"
Matrícula: "0001" (gerada automaticamente)
Idade: "45"
Curso: "Coordenação"
Telefone: "(11) 98888-8888"
```

### Cenário 2: Segundo Usuário
```
Nome: "Aluna Maria"
Matrícula: "0002" (gerada automaticamente)
Idade: "20"
Curso: "Biomedicina"
Telefone: "(11) 97777-7777"
```

### Cenário 3: Terceiro Usuário
```
Nome: "Dr. Carlos"
Matrícula: "0003" (gerada automaticamente)
Idade: "50"
Curso: "Medicina"
Telefone: "(11) 96666-6666"
```

### Agendamentos de Teste
```
Usuário: Professor João (0001)
- Data: 15/12/2024, Laboratório 1, 14h00

Usuário: Aluna Maria (0002)  
- Data: 16/12/2024, Laboratório 2, 10h30

Usuário: Dr. Carlos (0003)
- Data: 17/12/2024, Sala de Reunião, 09h00
```

## 🎯 Cenários de Demonstração Avançada

### Cenário 1: Segurança por Matrícula
```
1. Tentar login com matrícula correta mas nome errado
2. Mostrar: "Nome não confere com a matrícula"
3. Demonstrar que matrícula é única por usuário
4. Mostrar nome cadastrado para facilitar correção
```

### Cenário 2: Controle de Acesso
```
1. Tentar acessar sem cadastro prévio
2. Sistema força redirecionamento para cadastro
3. Mostrar que não é possível "burlar" o sistema
4. Demonstrar matrícula como "senha" do sistema
```

### Cenário 3: Fluxo Completo Multi-usuário
```
1. Cadastrar usuário A → Login A → Agendar como A
2. Sair do sistema
3. Cadastrar usuário B → Login B → Agendar como B  
4. Mostrar históricos separados por usuário
5. Demonstrar integridade dos dados
```

### Cenário 4: Persistência e Reinício
```
1. Fazer agendamentos com usuário logado
2. Fechar app completamente
3. Reabrir app → Login novamente
4. Verificar que agendamentos persistiram
5. Demonstrar que matrícula continua funcionando
```

## 🔗 Comandos Úteis para Apresentação

### Preparação para Demo
```bash
# Limpar estado para demo "limpa"
adb shell pm clear com.example.ap3

# Preparar dispositivo para demo
adb shell settings put global animator_duration_scale 0.5
adb shell settings put global transition_animation_scale 0.5

# Verificar que app está no estado inicial
adb shell am start-activity com.example.ap3/.SplashActivity
```

### Durante a Apresentação
```bash
# Screenshot rápido
adb exec-out screencap -p > demo_$(date +%H%M%S).png

# Gravar tela (Android 4.4+)
adb shell screenrecord /sdcard/demo.mp4
# Para parar: Ctrl+C, depois
adb pull /sdcard/demo.mp4

# Mostrar dados salvos (para debug)
adb shell run-as com.example.ap3 cat /data/data/com.example.ap3/shared_prefs/usuario_prefs.xml
```

## 🚨 Pontos Importantes para Demo

### Destaques do Sistema
1. **Matrícula como Chave**: Sistema de segurança simples mas efetivo
2. **Geração Automática**: Matrículas sequenciais (0001, 0002, 0003...)
3. **Validação Dupla**: Nome + Matrícula devem conferir
4. **Fluxo Inteligente**: App decide se vai para cadastro ou login
5. **Persistência**: Dados mantidos entre sessões

### Mensagens Importantes
- "Sua matrícula é: XXXX - Anote esta matrícula!"
- "Matrícula não encontrada - Faça novo cadastro"
- "Nome não confere com a matrícula"
- "Bem-vindo(a), [Nome]!" (após login)

### Casos de Teste Obrigatórios
1. ✅ Primeiro acesso (sem cadastros) → Vai direto para cadastro
2. ✅ Login correto → Acesso liberado
3. ✅ Login com erro → Mensagens específicas
4. ✅ Múltiplos usuários → Matrículas sequenciais
5. ✅ Persistência → Dados mantidos após restart 

# 🧪 Comandos para Executar Testes End-to-End

## 📋 Pré-requisitos

### 1. **Instalar Python**
```bash
# Baixe e instale Python de: https://www.python.org/downloads/
# Durante a instalação, marque "Add Python to PATH"
```

### 2. **Instalar Node.js**
```bash
# Baixe e instale Node.js de: https://nodejs.org/
```

### 3. **Instalar Appium**
```bash
npm install -g appium
```

### 4. **Instalar Android SDK**
- Instale Android Studio
- Configure ANDROID_HOME no PATH
- Instale um emulador ou conecte um dispositivo físico

## 🚀 Execução Rápida

### **Opção 1: Script Automático**
```bash
# Execute o script que configura tudo automaticamente
python run_tests.py
```

### **Opção 2: Comandos Manuais**
```bash
# 1. Instalar dependências
pip install -r requirements.txt

# 2. Build do app Android
./gradlew assembleDebug

# 3. Instalar no dispositivo
./gradlew installDebug

# 4. Iniciar Appium Server (em outro terminal)
appium --base-path /wd/hub

# 5. Executar testes
python -m pytest tests/ -v
```

## 🧪 Comandos de Teste Específicos

### **Executar Todos os Testes**
```bash
python -m pytest tests/ -v
```

### **Executar Teste Específico**
```bash
# Teste de jornada completa
python -m pytest tests/test_end_to_end_complete.py::TestEndToEndComplete::test_complete_user_journey -v

# Teste de usuário novo
python -m pytest tests/test_end_to_end_complete.py::TestEndToEndComplete::test_complete_user_journey_new_user -v

# Teste de validações
python -m pytest tests/test_end_to_end_complete.py::TestEndToEndComplete::test_field_validation_throughout_app -v

# Teste de navegação
python -m pytest tests/test_end_to_end_complete.py::TestEndToEndComplete::test_app_navigation_flow -v
```

### **Executar com Relatório**
```bash
# Relatório HTML
python -m pytest tests/ --html=test_report.html --self-contained-html

# Relatório XML (para CI/CD)
python -m pytest tests/ --junitxml=test_results.xml
```

### **Executar em Modo Debug**
```bash
# Com mais informações de debug
python -m pytest tests/ -v -s --tb=long
```

## 🔧 Configuração do Dispositivo

### **Para Emulador Android**
```bash
# Listar emuladores disponíveis
emulator -list-avds

# Iniciar emulador
emulator -avd [nome_do_emulador]
```

### **Para Dispositivo Físico**
```bash
# Habilitar modo desenvolvedor no Android
# Conectar via USB
# Habilitar depuração USB

# Verificar dispositivos conectados
adb devices
```

## 🐛 Solução de Problemas

### **Erro: Python não encontrado**
```bash
# Instalar Python e adicionar ao PATH
# Ou usar py launcher no Windows
py -m pip install -r requirements.txt
```

### **Erro: Appium não encontrado**
```bash
# Instalar Node.js primeiro
npm install -g appium
```

### **Erro: Dispositivo não encontrado**
```bash
# Verificar se dispositivo está conectado
adb devices

# Reiniciar servidor ADB
adb kill-server
adb start-server
```

### **Erro: App não instalado**
```bash
# Build e instalar o app
./gradlew clean assembleDebug installDebug
```

## 📊 Interpretando Resultados

### **Testes Passando**
```
✅ test_complete_user_journey PASSED
✅ test_field_validation_throughout_app PASSED
```

### **Testes Falhando**
```
❌ test_complete_user_journey FAILED
    assert False
    +  where False = <function is_element_present at 0x...>
```

### **Relatórios Gerados**
- `test_report.html` - Relatório visual detalhado
- `test_results.xml` - Relatório para CI/CD

## 🎯 Dicas Importantes

1. **Sempre inicie o Appium Server** antes de executar os testes
2. **Certifique-se que o app está instalado** no dispositivo
3. **Use dispositivos com Android 7.0+** para melhor compatibilidade
4. **Mantenha o dispositivo desbloqueado** durante os testes
5. **Feche outros apps** para evitar conflitos

## 📱 Configuração do App

### **Permissões Necessárias**
- Armazenamento
- Câmera (se usar QR Code)
- Notificações

### **Configurações Recomendadas**
- Modo desenvolvedor habilitado
- Depuração USB ativada
- Tela sempre ligada durante testes 