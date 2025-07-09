# =================================================================
# Dockerfile para Certus Marketplace (Despliegue en Fly.io)
# =================================================================

# --- ETAPA 1: CONSTRUCCIÓN (Builder) ---
# Usamos una imagen oficial de Maven que incluye el JDK 17.
# El uso de una etiqueta específica (en lugar de 'latest') asegura builds reproducibles.
FROM maven:3.9.6-eclipse-temurin-17-focal AS builder

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero los archivos pom.xml para aprovechar el cache de Docker.
# Las dependencias solo se descargarán de nuevo si los POMs cambian.
COPY pom.xml .
COPY bootloader/pom.xml bootloader/
COPY auth-module/pom.xml auth-module/
COPY people-module/pom.xml people-module/
COPY users-module/pom.xml users-module/
COPY works-module/pom.xml works-module/
COPY paypal-module/pom.xml paypal-module/
COPY ui-module/pom.xml ui-module/

# Descargamos todas las dependencias del proyecto.
# 'go-offline' es ideal para este paso de cacheo.
RUN mvn dependency:go-offline

# Ahora copiamos todo el código fuente del proyecto.
COPY . .

# Compilamos la aplicación y creamos el JAR ejecutable.
# Usamos -DskipTests para acelerar el build en un entorno de despliegue.
RUN mvn clean package -DskipTests

# --- ETAPA 2: PRODUCCIÓN (Runtime) ---
# Partimos de una imagen mucho más ligera que solo contiene el Java Runtime Environment (JRE).
FROM eclipse-temurin:17-jre-focal

# Por seguridad, creamos un usuario no-root para ejecutar la aplicación.
RUN groupadd --system appuser && useradd --system --gid appuser appuser

# Establecemos el directorio de trabajo en la imagen final.
WORKDIR /app

# Copiamos únicamente el JAR ejecutable desde la etapa de construcción.
# Le damos un nombre genérico para simplificar el comando de ejecución.
COPY --from=builder /app/bootloader/target/*.jar app.jar

# Asignamos la propiedad del JAR al usuario que acabamos de crear.
RUN chown appuser:appuser app.jar

# Cambiamos al usuario no-root.
USER appuser

# Exponemos el puerto en el que corre la aplicación Spring Boot (definido en tu application.properties).
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor inicie.
ENTRYPOINT ["java", "-jar", "app.jar"]
