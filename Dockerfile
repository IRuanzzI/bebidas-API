# Etapa de build utilizando Maven com Java 17
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Executa o Maven para construir o projeto e gerar o .jar
RUN mvn package -DskipTests

# Etapa de execução do container utilizando uma imagem mais leve
FROM openjdk:17-jdk-slim

# Porta onde a aplicação irá rodar
EXPOSE 8080

# Copia o .jar gerado na etapa de build para a imagem final
COPY --from=build /app/target/servico-bebdidas-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
