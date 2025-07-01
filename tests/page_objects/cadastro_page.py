from .base_page import BasePage
from selenium.webdriver.common.by import By

class CadastroPage(BasePage):
    """Page Object para a tela de cadastro"""
    
    # Locators
    TITULO = (By.ID, "com.example.ap3:id/tvTitulo")
    NOME_INPUT = (By.ID, "com.example.ap3:id/etNome")
    MATRICULA_INPUT = (By.ID, "com.example.ap3:id/etMatricula")
    IDADE_INPUT = (By.ID, "com.example.ap3:id/etIdade")
    CURSO_INPUT = (By.ID, "com.example.ap3:id/etCurso")
    TELEFONE_INPUT = (By.ID, "com.example.ap3:id/etTelefone")
    ENDERECO_INPUT = (By.ID, "com.example.ap3:id/etEndereco")
    SALVAR_BUTTON = (By.ID, "com.example.ap3:id/btnSalvar")
    CANCELAR_BUTTON = (By.ID, "com.example.ap3:id/btnCancelar")
    
    def wait_for_cadastro_page(self):
        """Aguarda a página de cadastro carregar"""
        self.wait_for_element(*self.TITULO)
    
    def fill_all_fields(self, dados):
        """Preenche todos os campos com os dados fornecidos"""
        self.send_keys_to_element(*self.NOME_INPUT, dados['nome'])
        self.send_keys_to_element(*self.MATRICULA_INPUT, dados['matricula'])
        self.send_keys_to_element(*self.IDADE_INPUT, str(dados['idade']))
        self.send_keys_to_element(*self.CURSO_INPUT, dados['curso'])
        self.send_keys_to_element(*self.TELEFONE_INPUT, dados['telefone'])
        self.send_keys_to_element(*self.ENDERECO_INPUT, dados['endereco'])
    
    def clear_all_fields(self):
        """Limpa todos os campos"""
        self.send_keys_to_element(*self.NOME_INPUT, "")
        self.send_keys_to_element(*self.MATRICULA_INPUT, "")
        self.send_keys_to_element(*self.IDADE_INPUT, "")
        self.send_keys_to_element(*self.CURSO_INPUT, "")
        self.send_keys_to_element(*self.TELEFONE_INPUT, "")
        self.send_keys_to_element(*self.ENDERECO_INPUT, "")
    
    def perform_complete_cadastro(self, dados):
        """Realiza cadastro completo"""
        self.fill_all_fields(dados)
        self.click_element(*self.SALVAR_BUTTON)
    
    def click_salvar(self):
        """Clica no botão salvar"""
        self.click_element(*self.SALVAR_BUTTON)
    
    def click_cancelar(self):
        """Clica no botão cancelar"""
        self.click_element(*self.CANCELAR_BUTTON) 