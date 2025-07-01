from .base_page import BasePage
from selenium.webdriver.common.by import By

class LoginPage(BasePage):
    """Page Object para a tela de login"""
    
    # Locators
    NOME_INPUT = (By.ID, "com.example.ap3:id/etNome")
    MATRICULA_INPUT = (By.ID, "com.example.ap3:id/etMatricula")
    LOGIN_BUTTON = (By.ID, "com.example.ap3:id/btnLogin")
    CADASTRO_BUTTON = (By.ID, "com.example.ap3:id/btnCadastro")
    TITULO = (By.ID, "com.example.ap3:id/tvTitulo")
    
    def wait_for_login_page(self):
        """Aguarda a página de login carregar"""
        self.wait_for_element(*self.TITULO)
    
    def perform_login(self, nome, matricula):
        """Realiza login com nome e matrícula"""
        self.send_keys_to_element(*self.NOME_INPUT, nome)
        self.send_keys_to_element(*self.MATRICULA_INPUT, matricula)
        self.click_element(*self.LOGIN_BUTTON)
    
    def click_cadastro(self):
        """Clica no botão de cadastro"""
        self.click_element(*self.CADASTRO_BUTTON)
    
    def get_error_message(self):
        """Obtém mensagem de erro se houver"""
        try:
            error_element = self.driver.find_element(By.ID, "com.example.ap3:id/tvError")
            return error_element.text
        except:
            return None 