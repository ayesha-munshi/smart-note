FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/smart-note.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]