# Etapa 1: build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o código e o wrapper
COPY . .

# Faz o build (gera o .jar na pasta target/)
RUN mvn clean package -DskipTests

# Etapa 2: runtime com JDK leve
FROM eclipse-temurin:17-jdk-alpine

# Define diretório de trabalho
WORKDIR /app

# Copia o .jar gerado na imagem anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta usada pelo Spring Boot (Render automaticamente redireciona)
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
