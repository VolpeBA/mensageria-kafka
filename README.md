
# 📬 Produtor e Consumidor Kafka

Este projeto demonstra a comunicação assíncrona entre dois serviços — **Produtor** e **Consumidor** — utilizando **Apache Kafka**.  
O **Produtor** envia mensagens para um tópico Kafka, enquanto o **Consumidor** escuta e processa essas mensagens.

---

## 🚀 Tecnologias Utilizadas

- 💻 Java 21  
- ⚙️ Spring Boot 3.4.4  
- 📡 Apache Kafka  
- 🛠️ Spring Kafka  
- 📘 Springdoc OpenAPI  
- 🧰 Gradle

---

## 🧱 Estrutura do Projeto

```
├── produtor
│   ├── src/main/java/com/volpe/produtor
│   ├── src/main/resources/application.properties
│   └── build.gradle
├── consumidor
│   ├── src/main/java/com/volpe/consumidor
│   ├── src/main/resources/application.properties
│   └── build.gradle
└── README.md
```

---

## 📨 Produtor

O serviço **Produtor** é responsável por enviar mensagens para um tópico Kafka via API REST.

### 🔗 Endpoint
```
POST /kafka
```

### 📦 Payload de Exemplo

```json
{
  "nome": "João da Silva",
  "destinatario": "maria@email.com",
  "assunto": "Promoção de Primavera",
  "tipoMensagem": "EMAIL"
}
```

### ⚙️ Configurações

- Servidor Kafka: `localhost:9092` (configurável via `application.properties`)
- Tópico Kafka: `messages-topic`

### 📚 Documentação da API

Acesse via Swagger:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📥 Consumidor

O serviço **Consumidor** escuta o tópico Kafka e processa as mensagens enviadas pelo Produtor.

- Configurado para escutar o tópico `messages-topic`
- Processamento baseado nos dados recebidos

---

## ▶️ Como Executar

### ✅ Pré-requisitos

- Java 21 instalado  
- Apache Kafka em execução  
- Gradle instalado  

### 📌 Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. **Inicie o servidor Kafka:**  
   Certifique-se de que o Kafka está rodando em `localhost:9092`.

3. **Execute o Produtor:**
   ```bash
   ./gradlew :produtor:bootRun
   ```

4. **Execute o Consumidor:**
   ```bash
   ./gradlew :consumidor:bootRun
   ```

5. **Acesse a documentação do Produtor:**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ⚙️ Configurações dos Serviços

### 🔧 `application.properties` do **Produtor**
```properties
spring.application.name=produtor
kafka.bootstrap.servers=localhost:9092
kafka.topic=messages-topic
```

### 🔧 `application.properties` do **Consumidor**
```properties
spring.application.name=consumidor
kafka.bootstrap.servers=localhost:9092
kafka.topic=messages-topic
```

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 👤 Autor

Feito com 💻 por **Bruno Volpe**  
[🔗 GitHub](https://github.com/seu-usuario) | [📧 E-mail](mailto:seu-email@exemplo.com)
