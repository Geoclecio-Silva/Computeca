# Estágio de construção
# Usa uma imagem do OpenJDK 21 para compilar o código
FROM eclipse-temurin:21-jdk-jammy AS build

RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo de configuração do Maven
COPY pom.xml .

# Copia os arquivos de código-fonte
COPY src ./src

# Executa o comando Maven para construir o projeto
# O -DskipTests pula os testes para um build mais rápido
RUN mvn clean package -DskipTests

# Estágio de execução
# Usa uma imagem menor do OpenJDK 21 para a execução da aplicação
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR da aplicação do estágio de construção
COPY --from=build /app/target/bncc-0.0.1-SNAPSHOT.jar ./app.jar

# Define o comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]