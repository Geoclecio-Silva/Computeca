# Estágio de construção
FROM eclipse-temurin:21-jdk-jammy AS build

# Instala o Maven para poder construir o projeto
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo de configuração e as dependências primeiro.
# Isso melhora a performance do build no Docker
COPY pom.xml .

# O comando 'mvn dependency:go-offline' pré-baixa todas as dependências do pom.xml
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Executa o comando Maven para construir o projeto, pulando os testes
RUN mvn clean package -DskipTests

# Estágio de execução
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia o arquivo JAR da aplicação do estágio de construção
COPY --from=build /app/target/bncc-0.0.1-SNAPSHOT.jar ./app.jar

# Define o comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]