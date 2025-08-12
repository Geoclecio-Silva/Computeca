# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho no container
WORKDIR /app

# Copia o JAR para dentro do container
COPY target/seu-app-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
