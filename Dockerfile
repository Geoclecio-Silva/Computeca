# Etapa 1: build
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app
COPY . .

# dá permissão pro mvnw antes de rodar
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/bncc-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]          # ← agora aponta para o jar correto
