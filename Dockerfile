# Etapa 1: Build com Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o projeto (pom.xml e src)
COPY . .

# Executa o build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JDK 21 leve (sem Maven)
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de execução
WORKDIR /app

# Copia o JAR gerado no build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
