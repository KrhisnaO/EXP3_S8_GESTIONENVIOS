FROM openjdk:21-ea-24-oracle 
WORKDIR /app

COPY pom.xml .
COPY src /app/src

COPY target/gestionenvios-0.0.1-SNAPSHOT.jar app.jar

#ubicacion y nombre del wallet descomprimido
COPY Wallet_BBDDFS /app/wallet
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
