# Automatización de pruebas para ParaBank

Este proyecto demuestra la automatización de pruebas funcionales para el sitio web [ParaBank](https://parabank.parasoft.com/parabank/index.htm), abarcando tanto pruebas de interfaz (UI) como validaciones de API. Fue desarrollado en Java utilizando Selenium WebDriver, JUnit, Rest Assured y ExtentReports.

## Tecnologías y herramientas utilizadas

- **Java 17** – Lenguaje principal
- **Selenium WebDriver** – Automatización de la interfaz gráfica del usuario
- **JUnit 5** – Framework de testing
- **Rest Assured** – Validación de endpoints de API REST
- **ExtentReports** – Generación de reportes visuales de ejecución
- **Maven** – Gestión de dependencias y construcción del proyecto
- **Docker** – Para levantar la API localmente y asegurar su disponibilidad

## Objetivo del proyecto

Automatizar un flujo completo de pruebas para funcionalidades clave del sistema ParaBank, incluyendo registro de usuario, apertura de cuentas, transferencia de fondos y validaciones de API. Se implementaron suites, etiquetas y reportes para lograr una ejecución organizada y profesional.

---

## Funcionalidades automatizadas

### Front-end (UI)
1. **Registro de nuevo usuario**
2. **Apertura de una nueva cuenta (tipo Savings)**
3. **Visualización del resumen de cuentas**
4. **Transferencia de fondos entre cuentas**
5. **Actividad de la cuenta (por mes)**

Cada paso verifica la correcta visualización de mensajes esperados, como confirmaciones de creación de cuenta o finalización de transferencias.

### Back-end (API)
Se validan los códigos de estado HTTP `200 OK` en cada una de las siguientes etapas:

- Registro:  
  `https://parabank.parasoft.com/parabank/register.htm`
- Apertura de cuenta:  
  `https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?...`
- Resumen de cuentas:  
  `https://parabank.parasoft.com/parabank/overview.htm`
- Transferencia de fondos:  
  `https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?...`
- Actividad mensual de la cuenta:  
  `https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/{id}/transactions/month/All/type/All`

---

## Estructura del proyecto
```text
├── src
│ ├── main
│ │ └── java
│ │   ├── pages 
│ │   └── utils 
│ ├── test
│ │ ├── api tests
│ │ └── web tests 
├── target
│ └── reports
├── pom.xml
└── README.md
```


---

## Reportes

Al finalizar la ejecución de pruebas, se genera un reporte en formato HTML con **ExtentReports**, que incluye:

- Detalle paso a paso
- Capturas de pantalla ante fallas (si se implementa)
- Estado final de cada test (PASSED / FAILED)

---

## Ejecución del proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/parabank-automation.git
   cd parabank-automation

---

## Entorno local con Docker

Debido a problemas de disponibilidad con el entorno público de ParaBank, se utilizó **Docker** para correr una instancia local de la API, garantizando estabilidad durante las pruebas automatizadas.

### Cómo levantar el entorno local:

1. Asegúrate de tener Docker instalado.
2. Corre el siguiente comando para levantar la API local:
   ```bash
   docker run -d -p 8080:8080 --name parabank parasoft/parabank
3. La aplicación estará disponible en:
   http://localhost:8080/parabank


