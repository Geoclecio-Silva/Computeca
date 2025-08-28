# Etapa 1: build
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app
COPY . .

# Dá permissão para o mvnw antes de rodar
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/bncc-0.0.1-SNAPSHOT.jar ./app.jar

# Define a variável de ambiente para ativar o perfil 'prod'
ENV SPRING_PROFILES_ACTIVE=prod

# A porta do servidor será 8081, conforme seu application.properties
EXPOSE 8081

ENTRYPOINT ["java","-jar","./app.jar"]