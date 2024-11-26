# Usar a imagem base do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado para o container
COPY /target/*.jar /app/app.jar

# Expor a porta usada pela aplicação
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "/app/app.jar"]