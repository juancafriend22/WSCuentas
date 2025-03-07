# Usar la imagen de OpenJDK
FROM openjdk:17-jdk-slim

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Gradle
COPY build/libs/*.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8082

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]