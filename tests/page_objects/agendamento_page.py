from .base_page import BasePage
from selenium.webdriver.common.by import By

class AgendamentoPage(BasePage):
    """Page Object para a tela de agendamento"""
    
    # Locators
    BOAS_VINDAS = (By.ID, "com.example.ap3:id/tvBoasVindas")
    SALA_SPINNER = (By.ID, "com.example.ap3:id/spinnerSala")
    DATA_INPUT = (By.ID, "com.example.ap3:id/etData")
    HORARIOS_CONTAINER = (By.ID, "com.example.ap3:id/rvHorarios")
    AGENDAR_BUTTON = (By.ID, "com.example.ap3:id/btnAgendar")
    
    def wait_for_agendamento_page(self):
        """Aguarda a página de agendamento carregar"""
        self.wait_for_element(*self.BOAS_VINDAS)
    
    def get_boas_vindas_text(self):
        """Obtém o texto de boas vindas"""
        return self.get_element_text(*self.BOAS_VINDAS)
    
    def select_sala_by_text(self, sala_text):
        """Seleciona sala pelo texto"""
        try:
            # Tenta encontrar por texto
            sala_element = self.driver.find_element(By.XPATH, f"//*[@text='{sala_text}']")
            sala_element.click()
        except:
            # Fallback: clica no spinner e seleciona
            self.click_element(*self.SALA_SPINNER)
            sala_element = self.driver.find_element(By.XPATH, f"//*[@text='{sala_text}']")
            sala_element.click()
    
    def select_date(self, day, month, year):
        """Seleciona data via date picker"""
        try:
            # Clica no campo de data para abrir o picker
            self.click_element(*self.DATA_INPUT)
            
            # Seleciona ano
            year_element = self.driver.find_element(By.XPATH, f"//*[@text='{year}']")
            year_element.click()
            
            # Seleciona mês
            month_element = self.driver.find_element(By.XPATH, f"//*[@text='{month}']")
            month_element.click()
            
            # Seleciona dia
            day_element = self.driver.find_element(By.XPATH, f"//*[@text='{day}']")
            day_element.click()
            
            # Confirma seleção
            ok_button = self.driver.find_element(By.ID, "android:id/button1")
            ok_button.click()
            
        except Exception as e:
            print(f"Erro ao selecionar data via picker: {e}")
            raise
    
    def click_horario_by_text(self, horario_text):
        """Clica em um horário específico"""
        try:
            horario_element = self.driver.find_element(By.XPATH, f"//*[@text='{horario_text}']")
            horario_element.click()
        except:
            # Fallback: procura por texto contendo o horário
            horario_element = self.driver.find_element(By.XPATH, f"//*[contains(@text, '{horario_text}')]")
            horario_element.click()
    
    def click_agendar(self):
        """Clica no botão agendar"""
        self.click_element(*self.AGENDAR_BUTTON)
    
    def get_selected_horarios(self):
        """Obtém horários selecionados"""
        try:
            selected_elements = self.driver.find_elements(By.XPATH, "//*[@selected='true']")
            return [elem.text for elem in selected_elements]
        except:
            return [] 