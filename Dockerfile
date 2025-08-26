# Etapa 2: runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/bncc-0.0.1-SNAPSHOT.jar ./app.jar

# Define a variável de ambiente para ativar o perfil 'prod'
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8081
ENTRYPOINT ["java","-jar","./app.jar"]