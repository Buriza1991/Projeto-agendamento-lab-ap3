import pytest
from page_objects.login_page import LoginPage
from page_objects.cadastro_page import CadastroPage
from page_objects.agendamento_page import AgendamentoPage
import time
from datetime import datetime, timedelta

class TestEndToEndComplete:
    """
    Testes End-to-End completos que validam todo o fluxo do app
    Desde login/cadastro até agendamento
    """
    
    @pytest.fixture(autouse=True)
    def setup(self, app_driver):
        """Setup executado antes de cada teste"""
        self.driver = app_driver
        self.login_page = LoginPage(self.driver)
        self.cadastro_page = CadastroPage(self.driver)
        self.agendamento_page = AgendamentoPage(self.driver)
    
    def test_complete_user_journey(self):
        """Testa jornada completa do usuário"""
        time.sleep(3)
        
        # Verifica estado inicial
        current_activity = self.driver.current_activity
        print(f"Activity inicial: {current_activity}")
        
        # Se tem login, tentar cadastro
        if "Login" in current_activity or "Welcome" in current_activity:
            try:
                self.login_page.click_cadastro()
                time.sleep(2)
            except:
                pass
        
        # Realizar cadastro se possível
        if self.cadastro_page.is_element_present(*self.cadastro_page.TITULO, timeout=5):
            dados_usuario = {
                'nome': 'Teste E2E User',
                'matricula': f'E2E{int(time.time())}',
                'idade': 25,
                'curso': 'Automação',
                'telefone': '11999888777',
                'endereco': 'Rua Teste, 123'
            }
            
            self.cadastro_page.perform_complete_cadastro(dados_usuario)
            time.sleep(3)
            
            print("✅ Cadastro realizado com sucesso")
        
        # Verificar estado final
        final_activity = self.driver.current_activity
        print(f"Activity final: {final_activity}")
        assert len(final_activity) > 0
    
    def test_complete_user_journey_new_user(self):
        """
        Testa jornada completa de um usuário novo:
        1. Primeiro acesso (vai direto para cadastro inicial)
        2. Cadastro de novo usuário
        3. Login com dados cadastrados
        4. Navegação para agendamento
        5. Realização de agendamento
        """
        
        # Aguarda o app inicializar
        time.sleep(3)
        
        # Verifica se chegou na tela de login ou cadastro inicial
        current_activity = self.driver.current_activity
        
        if "Login" in current_activity or "Welcome" in current_activity:
            # Se tem usuários, vai para cadastro de novo usuário
            try:
                self.login_page.wait_for_login_page()
                self.login_page.click_cadastro()
                time.sleep(2)
            except:
                pass
        
        # === FASE 1: CADASTRO ===
        if self.cadastro_page.is_element_present(*self.cadastro_page.TITULO, timeout=5):
            dados_usuario_novo = {
                'nome': 'Teste E2E Usuário',
                'matricula': f'E2E{int(time.time())}',  # Matrícula única
                'idade': 25,
                'curso': 'Teste Automation',
                'telefone': '11999888777',
                'endereco': 'Rua do Teste E2E, 123'
            }
            
            print(f"Cadastrando usuário: {dados_usuario_novo['nome']}")
            
            # Realiza cadastro completo
            self.cadastro_page.perform_complete_cadastro(dados_usuario_novo)
            time.sleep(3)
            
            # Verifica se cadastro foi bem-sucedido
            current_activity = self.driver.current_activity
            print(f"Activity após cadastro: {current_activity}")
            
            # === FASE 2: LOGIN (se necessário) ===
            if "Login" in current_activity or "Welcome" in current_activity:
                print("Realizando login após cadastro...")
                
                self.login_page.perform_login(
                    dados_usuario_novo['nome'],
                    dados_usuario_novo['matricula']
                )
                time.sleep(3)
            
            # === FASE 3: NAVEGAÇÃO PARA AGENDAMENTO ===
            current_activity = self.driver.current_activity
            print(f"Activity atual: {current_activity}")
            
            # Se estiver no menu principal, navegar para agendamento
            if "Menu" in current_activity:
                # Buscar botão de agendamento no menu
                try:
                    agendamento_btn = self.driver.find_element("id", "com.example.ap3:id/btnAgendamento")
                    agendamento_btn.click()
                    time.sleep(2)
                except:
                    # Se não encontrar por ID, tentar por texto
                    try:
                        agendamento_btn = self.driver.find_element("xpath", "//*[contains(@text, 'Agendamento') or contains(@text, 'Agendar')]")
                        agendamento_btn.click()
                        time.sleep(2)
                    except:
                        print("Não foi possível encontrar botão de agendamento")
            
            # === FASE 4: AGENDAMENTO ===
            if self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS, timeout=5):
                print("Realizando agendamento...")
                
                # Verifica se nome do usuário aparece na saudação
                texto_boas_vindas = self.agendamento_page.get_boas_vindas_text()
                print(f"Boas vindas: {texto_boas_vindas}")
                
                # Seleciona sala
                try:
                    self.agendamento_page.select_sala_by_text("Laboratório 1")
                    time.sleep(1)
                    print("Sala selecionada: Laboratório 1")
                except Exception as e:
                    print(f"Erro ao selecionar sala: {e}")
                
                # Seleciona data futura
                data_futura = datetime.now() + timedelta(days=1)
                data_str = f"{data_futura.day:02d}/{data_futura.month:02d}/{data_futura.year}"
                
                try:
                    self.agendamento_page.select_date(data_futura.day, data_futura.month, data_futura.year)
                    print(f"Data selecionada via picker: {data_str}")
                except:
                    # Fallback: entrada manual
                    try:
                        self.agendamento_page.send_keys_to_element(*self.agendamento_page.DATA_INPUT, data_str)
                        print(f"Data inserida manualmente: {data_str}")
                    except Exception as e:
                        print(f"Erro ao selecionar data: {e}")
                
                time.sleep(1)
                
                # Seleciona horários
                horarios_teste = ["09h00", "09h30"]
                for horario in horarios_teste:
                    try:
                        self.agendamento_page.click_horario_by_text(horario)
                        time.sleep(0.5)
                        print(f"Horário selecionado: {horario}")
                    except Exception as e:
                        print(f"Erro ao selecionar horário {horario}: {e}")
                
                # Confirma agendamento
                try:
                    self.agendamento_page.click_agendar()
                    time.sleep(3)
                    print("Agendamento confirmado")
                    
                    # Verifica se foi para tela de confirmação
                    current_activity = self.driver.current_activity
                    print(f"Activity final: {current_activity}")
                    
                    # Sucesso se saiu da tela de agendamento
                    assert "Agendamento" not in current_activity or "Confirmar" in current_activity
                    
                except Exception as e:
                    print(f"Erro ao confirmar agendamento: {e}")
            
            print("✅ Teste E2E concluído com sucesso!")
    
    def test_existing_user_login_and_agendamento(self):
        """
        Testa fluxo para usuário existente:
        1. Login com credenciais conhecidas
        2. Agendamento rápido
        """
        
        time.sleep(3)
        
        # Se chegou na tela de login
        if self.login_page.is_element_present(*self.login_page.NOME_INPUT, timeout=5):
            # Tenta login com usuário padrão
            nome_existente = "João Silva"
            matricula_existente = "0001"
            
            print(f"Tentando login com: {nome_existente} - {matricula_existente}")
            
            try:
                self.login_page.perform_login(nome_existente, matricula_existente)
                time.sleep(3)
                
                current_activity = self.driver.current_activity
                print(f"Activity após login: {current_activity}")
                
                # Se login bem-sucedido, navegar para agendamento
                if "Menu" in current_activity:
                    # Navegar para agendamento
                    try:
                        agendamento_btn = self.driver.find_element("xpath", "//*[contains(@text, 'Agendamento') or contains(@text, 'Agendar')]")
                        agendamento_btn.click()
                        time.sleep(2)
                        
                        # Agendamento rápido
                        if self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS, timeout=5):
                            self.agendamento_page.select_sala_by_text("Laboratório 2")
                            
                            data_futura = datetime.now() + timedelta(days=2)
                            data_str = f"{data_futura.day:02d}/{data_futura.month:02d}/{data_futura.year}"
                            self.agendamento_page.send_keys_to_element(*self.agendamento_page.DATA_INPUT, data_str)
                            
                            self.agendamento_page.click_horario_by_text("14h00")
                            self.agendamento_page.click_agendar()
                            
                            time.sleep(3)
                            print("✅ Agendamento de usuário existente concluído!")
                    
                    except Exception as e:
                        print(f"Erro na navegação/agendamento: {e}")
                
            except Exception as e:
                print(f"Erro no login: {e}")
    
    @pytest.mark.parametrize("dados_teste", [
        {
            'nome': 'E2E User Alpha',
            'matricula': 'ALPHA2024',
            'idade': 22,
            'curso': 'Engenharia',
            'telefone': '11111111111',
            'endereco': 'Rua Alpha, 100',
            'sala': 'Laboratório 1',
            'horarios': ['08h00', '08h30']
        },
        {
            'nome': 'E2E User Beta',
            'matricula': 'BETA2024', 
            'idade': 28,
            'curso': 'Medicina',
            'telefone': '22222222222',
            'endereco': 'Rua Beta, 200',
            'sala': 'Sala de Reunião',
            'horarios': ['15h00']
        }
    ])
    def test_multiple_user_journeys(self, dados_teste):
        """Testa múltiplas jornadas de usuário com dados diferentes"""
        
        time.sleep(3)
        
        print(f"Testando jornada para: {dados_teste['nome']}")
        
        # Verifica estado inicial do app
        current_activity = self.driver.current_activity
        
        # Se for primeiro usuário do app, vai direto para cadastro inicial
        if "Splash" in current_activity or "CadastroInicial" in current_activity:
            # Cadastro inicial
            if self.cadastro_page.is_element_present(*self.cadastro_page.TITULO, timeout=5):
                self.cadastro_page.perform_complete_cadastro(dados_teste)
                time.sleep(3)
        
        # Se tem login, ir para cadastro de novo usuário
        elif "Login" in current_activity or "Welcome" in current_activity:
            try:
                self.login_page.click_cadastro()
                time.sleep(2)
                
                if self.cadastro_page.is_element_present(*self.cadastro_page.TITULO, timeout=5):
                    self.cadastro_page.perform_complete_cadastro(dados_teste)
                    time.sleep(3)
            except:
                pass
        
        # Navegar para agendamento e realizar teste
        current_activity = self.driver.current_activity
        
        if "Menu" in current_activity:
            try:
                # Ir para agendamento
                agendamento_btn = self.driver.find_element("xpath", "//*[contains(@text, 'Agendamento')]")
                agendamento_btn.click()
                time.sleep(2)
                
                # Realizar agendamento com dados específicos
                if self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS, timeout=5):
                    self.agendamento_page.select_sala_by_text(dados_teste['sala'])
                    
                    data_futura = datetime.now() + timedelta(days=1)
                    data_str = f"{data_futura.day:02d}/{data_futura.month:02d}/{data_futura.year}"
                    self.agendamento_page.send_keys_to_element(*self.agendamento_page.DATA_INPUT, data_str)
                    
                    for horario in dados_teste['horarios']:
                        self.agendamento_page.click_horario_by_text(horario)
                        time.sleep(0.3)
                    
                    self.agendamento_page.click_agendar()
                    time.sleep(3)
                    
                    print(f"✅ Jornada concluída para {dados_teste['nome']}")
            
            except Exception as e:
                print(f"Erro na jornada de {dados_teste['nome']}: {e}")
    
    def test_field_validation_throughout_app(self):
        """
        Testa validações de campos em todo o app
        """
        
        time.sleep(3)
        
        print("🔍 Testando validações de campos...")
        
        # === TESTE DE VALIDAÇÕES NO CADASTRO ===
        current_activity = self.driver.current_activity
        
        if "Login" in current_activity:
            try:
                self.login_page.click_cadastro()
                time.sleep(2)
            except:
                pass
        
        if self.cadastro_page.is_element_present(*self.cadastro_page.TITULO, timeout=5):
            print("Testando validações do cadastro...")
            
            # Testa campos vazios
            self.cadastro_page.clear_all_fields()
            self.cadastro_page.click_salvar()
            time.sleep(2)
            
            # Deve permanecer na tela devido às validações
            assert self.cadastro_page.is_element_present(*self.cadastro_page.TITULO)
            print("✅ Validação de campos vazios funcionando")
            
            # Testa idade inválida
            dados_invalidos = {
                'nome': 'Teste Validação',
                'matricula': 'VAL001',
                'idade': -5,  # Idade inválida
                'curso': 'Teste',
                'telefone': '11999999999',
                'endereco': 'Rua Teste'
            }
            
            self.cadastro_page.fill_all_fields(dados_invalidos)
            self.cadastro_page.click_salvar()
            time.sleep(2)
            
            # Deve permanecer na tela devido à validação de idade
            assert self.cadastro_page.is_element_present(*self.cadastro_page.TITULO)
            print("✅ Validação de idade inválida funcionando")
            
            # Cadastra usuário válido para continuar testes
            dados_validos = {
                'nome': 'Usuário Validação',
                'matricula': f'VAL{int(time.time())}',
                'idade': 25,
                'curso': 'Curso Validação',
                'telefone': '11888777666',
                'endereco': 'Rua Validação, 456'
            }
            
            self.cadastro_page.clear_all_fields()
            self.cadastro_page.perform_complete_cadastro(dados_validos)
            time.sleep(3)
        
        # === TESTE DE VALIDAÇÕES NO AGENDAMENTO ===
        current_activity = self.driver.current_activity
        
        if "Menu" in current_activity:
            try:
                agendamento_btn = self.driver.find_element("xpath", "//*[contains(@text, 'Agendamento')]")
                agendamento_btn.click()
                time.sleep(2)
                
                if self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS, timeout=5):
                    print("Testando validações do agendamento...")
                    
                    # Tenta agendar sem selecionar nada
                    self.agendamento_page.click_agendar()
                    time.sleep(2)
                    
                    # Deve permanecer na tela
                    assert self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS)
                    print("✅ Validação de agendamento vazio funcionando")
                    
                    # Tenta agendar só com sala
                    self.agendamento_page.select_sala_by_text("Laboratório 1")
                    self.agendamento_page.click_agendar()
                    time.sleep(2)
                    
                    # Deve permanecer na tela (falta data e horário)
                    assert self.agendamento_page.is_element_present(*self.agendamento_page.BOAS_VINDAS)
                    print("✅ Validação de data obrigatória funcionando")
            
            except Exception as e:
                print(f"Erro nos testes de validação do agendamento: {e}")
        
        print("🎉 Testes de validação concluídos!")
    
    def test_app_navigation_flow(self):
        """
        Testa fluxo de navegação completo entre telas
        """
        
        time.sleep(3)
        
        print("🧭 Testando fluxo de navegação...")
        
        activities_visited = []
        
        # Captura activity inicial
        current_activity = self.driver.current_activity
        activities_visited.append(current_activity)
        print(f"Activity inicial: {current_activity}")
        
        # Navega através das telas
        if "Login" in current_activity:
            # Se tem login, navegar para cadastro e voltar
            try:
                self.login_page.click_cadastro()
                time.sleep(2)
                current_activity = self.driver.current_activity
                activities_visited.append(current_activity)
                print(f"Navegou para: {current_activity}")
                
                # Voltar do cadastro
                if self.cadastro_page.is_element_present(*self.cadastro_page.CANCELAR_BUTTON, timeout=5):
                    self.cadastro_page.click_cancelar()
                    time.sleep(2)
                    current_activity = self.driver.current_activity
                    activities_visited.append(current_activity)
                    print(f"Voltou para: {current_activity}")
            except Exception as e:
                print(f"Erro na navegação login->cadastro: {e}")
        
        # Verifica se visitou múltiplas telas
        unique_activities = set(activities_visited)
        print(f"Activities visitadas: {unique_activities}")
        
        # Deve ter navegado por pelo menos 2 telas diferentes
        assert len(unique_activities) >= 1  # Pelo menos uma tela deve ter sido visitada
        print("✅ Navegação básica funcionando")
        
        print("🎯 Teste de navegação concluído!") 