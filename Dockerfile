# Usando uma imagem base com JDK 17
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Copiar o pom.xml e os arquivos do projeto para o container
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Compilar e empacotar o aplicativo
RUN ./mvnw clean package -DskipTests

# Fase de execução
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiar o arquivo .jar gerado para a fase de execução
COPY --from=build /app/target/*.jar app.jar

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]