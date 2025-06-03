# 🧪 Sistema de Agendamento de Laboratório

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://android.com)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://java.com)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> Sistema Android nativo para agendamento de laboratórios acadêmicos com **autenticação por matrícula única** e controle de acesso inteligente.

## 📱 Sobre o Projeto

O **Sistema de Agendamento de Laboratório** é uma aplicação Android inovadora desenvolvida para solucionar problemas de coordenação e conflitos no uso de espaços laboratoriais em instituições acadêmicas, implementando um **sistema de autenticação único baseado em matrícula sequencial**.

### ✨ Principais Funcionalidades

- 🔐 **Autenticação por Matrícula**: Sistema único onde matrícula funciona como chave de acesso
- 🔢 **Geração Automática**: Matrículas sequenciais (0001, 0002, 0003...) geradas automaticamente
- 📅 **Agendamento Seguro**: Interface com sistema de cores e controle de disponibilidade
- 👥 **Controle Individual**: Cada usuário possui histórico personalizado e rastreável
- 🎯 **Fluxo Inteligente**: Sistema decide automaticamente entre cadastro e login
- 📱 **Mobile First**: Desenvolvido especificamente para dispositivos móveis

## 🚀 Screenshots

<div align="center">
  <img src="screenshots/cadastro.png" width="200" alt="Cadastro com Matrícula"/>
  <img src="screenshots/login.png" width="200" alt="Login por Matrícula"/>
  <img src="screenshots/agendamento.png" width="200" alt="Agendamento Autenticado"/>
</div>

## 🔐 Sistema de Autenticação Inovador

### Como Funciona
1. **Primeiro Acesso**: Sistema detecta ausência de usuários e direciona para cadastro
2. **Geração de Matrícula**: Sistema gera automaticamente matrícula sequencial única
3. **Login Seguro**: Validação dupla com nome + matrícula para acesso
4. **Controle de Sessão**: Cada agendamento fica vinculado ao usuário autenticado

### Fluxo do Sistema
```
App Inicial → Verificação de Usuários
    ↓
┌─── Sem Usuários ────┐    ┌─── Com Usuários ───┐
│  Cadastro Obrigatório │    │     Login Direto    │
│  Matrícula: 0001      │    │   Nome + Matrícula   │
└───────────────────────┘    └─────────────────────┘
    ↓                              ↓
Sistema de Agendamento Autenticado
```

## 🏗️ Arquitetura

### Tecnologias Utilizadas

- **Linguagem**: Java
- **Plataforma**: Android (API 24-35)
- **Banco de Dados**: SQLite
- **Padrões**: MVC, Singleton, Adapter
- **UI**: Material Design
- **Autenticação**: Sistema próprio baseado em matrícula

### Estrutura do Projeto

```
app/src/main/java/com/example/ap3/
├── 🚀 Controle de Fluxo/
│   ├── SplashActivity.java          # Decisão inicial do fluxo
│   ├── CadastroInicialActivity.java # Cadastro com matrícula automática
│   └── WelcomeActivity.java         # Login com validação
├── 📱 Activities Principais/
│   ├── MainActivity.java            # Agendamento autenticado
│   ├── MenuActivity.java            # Menu personalizado
│   └── HistoricoActivity.java       # Histórico individual
├── 📊 Models/
│   ├── Agendamento.java             # Vinculado ao usuário
│   └── Aluno.java                   # Com matrícula única
├── 🔧 Adapters/
│   ├── AlunosAdapter.java
│   └── HorariosAdapter.java
├── 💼 Managers/
│   ├── AlunosManager.java           # Com validação de matrícula
│   └── ReservasManager.java
└── 🗄️ Database/
    └── DBHelper.java
```

## 📋 Pré-requisitos

- Android Studio 4.0+
- JDK 11+
- Android SDK (API 24+)
- Dispositivo Android ou Emulador

## ⚡ Instalação Rápida

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/projeto-agendamento-lab.git
cd Projeto-agendamento-lab-ap3-main
```

### 2. Abra no Android Studio
```bash
# Abrir Android Studio e selecionar a pasta do projeto
# Ou via linha de comando:
studio .
```

### 3. Build e Execute
```bash
# Via Android Studio: Build → Make Project
# Ou via terminal:
./gradlew assembleDebug
./gradlew installDebug
```

## 🎯 Como Usar

### 1. Primeiro Acesso (Cadastro Automático)
1. Abra o aplicativo
2. Sistema detecta primeiro uso e vai para cadastro
3. Matrícula é gerada automaticamente (ex: "0001")
4. Preencha seus dados pessoais
5. **Anote sua matrícula** - ela é sua chave de acesso!

### 2. Login com Matrícula
1. Digite seu nome completo
2. Digite sua matrícula (ex: "0001")
3. Sistema valida nome + matrícula
4. Acesso liberado apenas com dados corretos

### 3. Agendamento Autenticado
1. Com login válido, acesse "Fazer Agendamento"
2. Selecione laboratório e data
3. Escolha horário disponível (verde)
4. Agendamento fica vinculado ao seu usuário

### 4. Histórico Personalizado
1. Acesse "Ver Histórico"
2. Visualize apenas seus agendamentos
3. Controle individual de reservas

## 🔐 Segurança e Validações

### Sistema de Proteção
- **Acesso Negado** sem matrícula válida
- **Validação Dupla** nome + matrícula obrigatória
- **Mensagens Específicas** para cada tipo de erro
- **Controle Individual** por usuário autenticado

### Casos de Erro Tratados
```
❌ Matrícula inexistente → "Matrícula não encontrada"
❌ Nome incorreto → "Nome não confere com a matrícula"
❌ Campos vazios → Validação com foco no campo
✅ Login correto → "Bem-vindo(a), [Nome]!"
```

## 📚 Documentação Completa

Este projeto inclui documentação detalhada para diferentes propósitos:

### 📖 Para Desenvolvedores
- **[Comandos de Demonstração](COMANDOS_DEMONSTRACAO.md)** - Scripts e comandos para demos com foco no sistema de autenticação

### 🎯 Para Apresentações
- **[Roteiro de Apresentação](ROTEIRO_APRESENTACAO.md)** - Guia completo destacando inovações do sistema de matrícula

### 🔧 Recursos Técnicos
- **API Documentation** - [Em desenvolvimento]
- **Database Schema** - [docs/database.md]
- **Testing Guide** - [docs/testing.md]

## 🎬 Demo Rápida do Sistema de Autenticação

### Cenário: Primeiro Usuário
```
1. 🚀 App abre → Detecta primeiro uso
2. 📝 Cadastro automático com matrícula "0001"
3. 👤 Usuário: "Professor João Silva"
4. 🔑 Matrícula gerada: "0001"
5. 🔐 Login: Nome + Matrícula
6. ✅ Acesso liberado para agendamentos
```

### Cenário: Segundo Usuário
```
1. 🔄 Novo cadastro gera matrícula "0002"
2. 👤 Usuário: "Aluna Maria Santos"  
3. 🔑 Matrícula: "0002"
4. 📊 Histórico individual separado
5. 🎯 Controle personalizado de agendamentos
```

**Resultado**: Sistema completamente seguro com chaves únicas por usuário!

## 🧪 Testes

### Executar Testes Unitários
```bash
./gradlew test
```

### Executar Testes de Interface
```bash
./gradlew connectedAndroidTest
```

### Testar Sistema de Autenticação
```bash
# Reset completo para testes
adb shell pm clear com.example.ap3

# Verificar primeiro acesso
adb shell am start-activity com.example.ap3/.SplashActivity
```

## 🚀 Build e Deploy

### Build de Debug
```bash
./gradlew assembleDebug
# APK gerado em: app/build/outputs/apk/debug/
```

### Build de Release
```bash
./gradlew assembleRelease
# APK gerado em: app/build/outputs/apk/release/
```

### Para Google Play Store
```bash
./gradlew bundleRelease
# AAB gerado em: app/build/outputs/bundle/release/
```

## 📊 Status do Projeto

### ✅ Funcionalidades Implementadas
- [x] **Sistema de autenticação por matrícula**
- [x] **Geração automática sequencial de matrículas**
- [x] **Fluxo inteligente (cadastro/login)**
- [x] **Validação dupla nome + matrícula**
- [x] Sistema de agendamento autenticado
- [x] Histórico personalizado por usuário
- [x] Persistência local (SQLite)
- [x] Interface responsiva

### 🔄 Em Desenvolvimento
- [ ] Notificações de lembrete por usuário
- [ ] Sincronização cloud dos dados
- [ ] Relatórios administrativos
- [ ] Dark mode

### 🌟 Roadmap Futuro
- [ ] Integração com calendário pessoal
- [ ] Sistema de reserva recorrente
- [ ] Chat entre usuários autenticados
- [ ] API REST para integração externa
- [ ] Versão web complementar

## 🌟 Diferenciais Únicos

### Por que nosso sistema é inovador?

1. **🔐 Autenticação Simplificada**: Matrícula como "senha" fácil de lembrar
2. **🔢 Geração Automática**: Sistema cria chaves únicas sequenciais
3. **🎯 Fluxo Inteligente**: App decide automaticamente o que fazer
4. **🛡️ Segurança Efetiva**: Impossível acessar sem cadastro válido
5. **📊 Rastreabilidade Total**: Cada ação vinculada ao usuário

### Comparação com Sistemas Tradicionais
```
Sistema Tradicional ❌         Nosso Sistema ✅
├─ Senha complexa              ├─ Matrícula simples (0001)
├─ Login genérico              ├─ Validação dupla segura
├─ Sem controle individual     ├─ Histórico personalizado
└─ Acesso sem identificação    └─ Rastreabilidade completa
```

## 🤝 Contribuindo

### Como Contribuir
1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

### Padrões de Commit
```bash
feat: adiciona nova funcionalidade de autenticação
fix: corrige validação de matrícula
docs: atualiza documentação do sistema de login
style: ajustes de formatação
refactor: refatora sistema de validação
test: adiciona testes para autenticação
```

## 📝 Changelog

### v1.0.0 (Atual) - Sistema de Autenticação
- ✨ **Sistema de matrícula única implementado**
- 🔐 **Autenticação por nome + matrícula**
- 🔢 **Geração automática sequencial**
- 🎯 **Fluxo inteligente de cadastro/login**
- 📱 Interface completa com validações
- 🗄️ Banco SQLite estruturado
- 📊 Sistema de agendamento autenticado

## 👥 Equipe

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/usuario1.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 1</b></sub><br />
      <sub>Tech Lead & Autenticação</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario2.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 2</b></sub><br />
      <sub>Frontend Android & UX</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario3.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 3</b></sub><br />
      <sub>Backend/Database & Segurança</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario4.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 4</b></sub><br />
      <sub>QA/Testing & Validações</sub>
    </td>
  </tr>
</table>

## 📞 Suporte

### Reportar Bugs
- 🐛 [Issues no GitHub](https://github.com/seu-usuario/projeto/issues)
- 📧 Email: suporte@projeto.com

### FAQ

**P: Como funciona o sistema de matrícula?**
R: O sistema gera automaticamente matrículas sequenciais (0001, 0002...) que funcionam como chave única de cada usuário para login.

**P: Posso acessar sem matrícula?**
R: Não! O sistema exige cadastro prévio e login com nome + matrícula para qualquer acesso.

**P: E se eu esquecer minha matrícula?**
R: Atualmente é necessário anotar a matrícula. Funcionalidade de recuperação está no roadmap.

**P: O app funciona offline?**
R: Sim! Todos os dados são armazenados localmente no SQLite.

**P: Posso usar em tablets?**
R: Sim! A interface é responsiva e funciona em tablets Android.

## 📄 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🙏 Agradecimentos

- **Professor Orientador**: [Nome do Professor]
- **Instituição**: [Nome da Instituição]
- **Comunidade Android**: Pela documentação e recursos
- **Material Design**: Pelas diretrizes de UI/UX

---

<div align="center">

**🧪 Sistema de Agendamento de Laboratório**
*Com Autenticação Inovadora por Matrícula*

Desenvolvido com ❤️ pela equipe [Nome da Equipe]

[⭐ Star no GitHub](https://github.com/seu-usuario/projeto) • [📱 Download APK](releases/latest) • [📖 Documentação](docs/) • [🐛 Reportar Bug](issues/)

**🔐 "Cada usuário, uma chave única - Segurança com simplicidade"**

</div> 