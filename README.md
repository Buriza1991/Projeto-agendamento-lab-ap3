# 🧪 Sistema de Agendamento de Laboratório

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://android.com)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://java.com)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> Sistema Android nativo para agendamento eficiente de laboratórios acadêmicos com interface intuitiva e controle de disponibilidade em tempo real.

## 📱 Sobre o Projeto

O **Sistema de Agendamento de Laboratório** é uma aplicação Android desenvolvida para solucionar problemas de coordenação e conflitos no uso de espaços laboratoriais em instituições acadêmicas. 

### ✨ Principais Funcionalidades

- 📅 **Agendamento Visual**: Interface com sistema de cores para disponibilidade
- 👥 **Gestão de Usuários**: Cadastro completo de alunos e professores  
- 📊 **Histórico Completo**: Rastreamento de todos os agendamentos
- 🎯 **Interface Intuitiva**: Material Design com UX otimizada
- 📱 **Mobile First**: Desenvolvido especificamente para dispositivos móveis

## 🚀 Screenshots

<div align="center">
  <img src="screenshots/welcome.png" width="200" alt="Tela de Boas-vindas"/>
  <img src="screenshots/agendamento.png" width="200" alt="Tela de Agendamento"/>
  <img src="screenshots/historico.png" width="200" alt="Histórico"/>
</div>

## 🏗️ Arquitetura

### Tecnologias Utilizadas

- **Linguagem**: Java
- **Plataforma**: Android (API 24-35)
- **Banco de Dados**: SQLite
- **Padrões**: MVC, Singleton, Adapter
- **UI**: Material Design

### Estrutura do Projeto

```
app/src/main/java/com/example/ap3/
├── 📱 Activities/          # Telas da aplicação
│   ├── MainActivity.java           # Agendamento principal
│   ├── WelcomeActivity.java       # Boas-vindas
│   ├── MenuActivity.java          # Menu principal
│   ├── CadastroAlunoActivity.java # Cadastro de alunos
│   └── HistoricoActivity.java     # Histórico
├── 📊 Models/              # Modelos de dados
│   ├── Agendamento.java
│   └── Aluno.java
├── 🔧 Adapters/           # Adapters para listas
│   ├── AlunosAdapter.java
│   └── HorariosAdapter.java
├── 💼 Managers/           # Lógica de negócio
│   ├── AlunosManager.java
│   └── ReservasManager.java
└── 🗄️ Database/          # Banco de dados
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

### 1. Primeiro Acesso
1. Abra o aplicativo
2. Digite seu nome na tela de boas-vindas
3. Toque em "Entrar"

### 2. Fazer Agendamento
1. No menu, toque "Fazer Agendamento"
2. Selecione o laboratório desejado
3. Escolha a data no calendário
4. Selecione horário disponível (verde)
5. Confirme o agendamento

### 3. Gerenciar Alunos
1. Menu → "Gerenciar Alunos"
2. Toque "+" para adicionar
3. Preencha os dados
4. Salve

### 4. Ver Histórico
1. Menu → "Ver Histórico"
2. Visualize todos os agendamentos
3. Toque para ver detalhes

## 📚 Documentação Completa

Este projeto inclui documentação detalhada para diferentes propósitos:

### 📖 Para Desenvolvedores
- **[Guia da Equipe](GUIA_EQUIPE.md)** - Setup, padrões e organização do desenvolvimento
- **[Comandos de Demonstração](COMANDOS_DEMONSTRACAO.md)** - Scripts e comandos para demos

### 🎯 Para Apresentações
- **[Roteiro de Apresentação](ROTEIRO_APRESENTACAO.md)** - Guia completo para apresentar o projeto

### 🔧 Recursos Técnicos
- **API Documentation** - [Em desenvolvimento]
- **Database Schema** - [docs/database.md]
- **Testing Guide** - [docs/testing.md]

## 🎬 Demo Rápida

### Cenário de Uso Típico

```
1. 📱 Usuário abre o app
2. 👋 Faz login com nome
3. 📅 Seleciona "Laboratório 1"
4. 📆 Escolhe data: "15/12/2024" 
5. ⏰ Seleciona horário: "14h00"
6. ✅ Confirma agendamento
7. 📊 Visualiza no histórico
```

**Resultado**: Agendamento criado em menos de 30 segundos!

## 🧪 Testes

### Executar Testes Unitários
```bash
./gradlew test
```

### Executar Testes de Interface
```bash
./gradlew connectedAndroidTest
```

### Gerar Relatório de Cobertura
```bash
./gradlew jacocoTestReport
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

## 🔧 Configuração de Desenvolvimento

### Configurar Git
```bash
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"
```

### Branching Strategy
```bash
# Feature branches
git checkout -b feature/nova-funcionalidade

# Bug fixes  
git checkout -b bugfix/correcao-bug

# Hotfixes
git checkout -b hotfix/correcao-urgente
```

## 📊 Status do Projeto

### ✅ Funcionalidades Implementadas
- [x] Tela de boas-vindas
- [x] Sistema de agendamento
- [x] Cadastro de alunos
- [x] Histórico de agendamentos
- [x] Persistência local (SQLite)
- [x] Interface responsiva

### 🔄 Em Desenvolvimento
- [ ] Notificações push
- [ ] Sincronização cloud
- [ ] Relatórios avançados
- [ ] Dark mode

### 🌟 Roadmap Futuro
- [ ] Integração com calendário
- [ ] Chat entre usuários
- [ ] Sistema de avaliação
- [ ] API REST
- [ ] Versão web complementar

## 🤝 Contribuindo

### Como Contribuir
1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

### Padrões de Commit
```bash
feat: adiciona nova funcionalidade
fix: corrige bug
docs: atualiza documentação
style: ajustes de formatação
refactor: refatora código
test: adiciona testes
```

## 📝 Changelog

### v1.0.0 (Atual)
- ✨ Implementação inicial
- 📱 Interface completa
- 🗄️ Banco SQLite estruturado
- 📊 Sistema de agendamento funcional

## 👥 Equipe

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/usuario1.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 1</b></sub><br />
      <sub>Tech Lead</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario2.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 2</b></sub><br />
      <sub>Frontend Android</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario3.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 3</b></sub><br />
      <sub>Backend/Database</sub>
    </td>
    <td align="center">
      <img src="https://github.com/usuario4.png" width="100px;" alt=""/><br />
      <sub><b>Nome Desenvolvedor 4</b></sub><br />
      <sub>QA/Testing</sub>
    </td>
  </tr>
</table>

## 📞 Suporte

### Reportar Bugs
- 🐛 [Issues no GitHub](https://github.com/seu-usuario/projeto/issues)
- 📧 Email: suporte@projeto.com

### FAQ

**P: O app funciona offline?**
R: Sim! Todos os dados são armazenados localmente no SQLite.

**P: Posso usar em tablets?**
R: Sim! A interface é responsiva e funciona em tablets Android.

**P: Como fazer backup dos dados?**
R: Atualmente manual. Backup automático está no roadmap.

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

Desenvolvido com ❤️ pela equipe [Nome da Equipe]

[⭐ Star no GitHub](https://github.com/seu-usuario/projeto) • [📱 Download APK](releases/latest) • [📖 Documentação](docs/) • [🐛 Reportar Bug](issues/)

</div> 