*** Settings ***
Library        AppiumLibrary

*** Test Cases ***
Deve abrir a tela 
    Open Application       http://127.0.0.1:4723/wd/hub
    ...    platformName=Android
    ...    deviceName=emulator-5554
    ...    app=C:/APK/app-debug.apk
    ...    automationName=UiAutomator2
    ...    appPackage=com.example.ap3
    ...    appActivity=com.example.ap3.MainActivity
    ...    fullReset=${False}
    ...    noReset=${True}

    Wait Until Element Is Visible    xpath=//android.widget.TextView[@content-desc="Predicted app: MeuApp"]    10s
    Click Element    xpath=//android.widget.TextView[@content-desc="Predicted app: MeuApp"]

    Wait Until Element Is Visible    xpath=//android.widget.TextView[@text="Seja Bem-Vindo!"]    10s
    Sleep    2s
    Wait Until Element Is Visible    id=com.example.ap3:id/edtNome   10s
    Input Text    id=com.example.ap3:id/edtNome    Davi Teste
    Wait Until Element Is Visible    id=com.example.ap3:id/edtMatricula  10s
    Input Text    id=com.example.ap3:id/edtMatricula  123123
    Click Element    id=com.example.ap3:id/btnEntrar

     Wait Until Element Is Visible    id=com.example.ap3:id/btnAgendamento   10s
    Click Element    id=com.example.ap3:id/btnAgendamento
    Wait Until Element Is Visible    id=com.example.ap3:id/edtData   10s
    Click Element    id=com.example.ap3:id/edtData
    Sleep    1
    Click Element    id=com.example.ap3:id/edtData
    Wait Until Element Is Visible    xpath=//android.widget.Button[@resource-id="android:id/button1"]    10s
    Click Element                    xpath=//android.widget.Button[@resource-id="android:id/button1"]
    Wait Until Element Is Visible    xpath=//android.widget.GridView[@resource-id="com.example.ap3:id/gridHorarios"]/android.widget.LinearLayout[15]    10s
    Click Element    xpath=//android.widget.GridView[@resource-id="com.example.ap3:id/gridHorarios"]/android.widget.LinearLayout[15]
    Click Element    id=com.example.ap3:id/btnAgendar
    Wait Until Element Is Visible    id=com.example.ap3:id/edtNome   10s
    Input Text    id=com.example.ap3:id/edtNome   Davi Teste AP3
    Wait Until Element Is Visible    id=com.example.ap3:id/edtMatricula  10s
    Input Text    id=com.example.ap3:id/edtMatricula  123123
    Click Element    id=com.example.ap3:id/btnConfirmarAgendamento
    Wait Until Element Is Visible    id=com.example.ap3:id/btnHistorico  10s
    Click Element    id=com.example.ap3:id/btnHistorico
    Sleep    3
    Click Element    id=com.example.ap3:id/btnVoltarMenu
    Wait Until Element Is Visible    xpath=//android.widget.Button[@resource-id="com.example.ap3:id/btnCadastroAlunos"]    10s
    Click Element    xpath=//android.widget.Button[@resource-id="com.example.ap3:id/btnCadastroAlunos"] 
    Wait Until Element Is Visible    id=com.example.ap3:id/btnVoltarMenu  10s
    Click Element    id=com.example.ap3:id/btnVoltarMenu
    Wait Until Element Is Visible    id=com.example.ap3:id/btnVoltar    10s
    Click Element    id=com.example.ap3:id/btnVoltar

    Wait Until Element Is Visible    id=com.example.ap3:id/edtNome   10s
    Input Text    id=com.example.ap3:id/edtNome    FIM DO TESTE OBRIGADO!


    Capture Page Screenshot


    Close Application
