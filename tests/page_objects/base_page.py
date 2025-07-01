from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException

class BasePage:
    """Classe base para todos os page objects"""
    
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)
    
    def is_element_present(self, by, value, timeout=10):
        """Verifica se um elemento está presente na tela"""
        try:
            WebDriverWait(self.driver, timeout).until(
                EC.presence_of_element_located((by, value))
            )
            return True
        except TimeoutException:
            return False
    
    def wait_for_element(self, by, value, timeout=10):
        """Aguarda um elemento ficar visível"""
        return WebDriverWait(self.driver, timeout).until(
            EC.visibility_of_element_located((by, value))
        )
    
    def click_element(self, by, value):
        """Clica em um elemento"""
        element = self.wait_for_element(by, value)
        element.click()
    
    def send_keys_to_element(self, by, value, text):
        """Envia texto para um elemento"""
        element = self.wait_for_element(by, value)
        element.clear()
        element.send_keys(text)
    
    def get_element_text(self, by, value):
        """Obtém o texto de um elemento"""
        element = self.wait_for_element(by, value)
        return element.text 