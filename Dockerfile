FROM tomcat:8.5-jre11-temurin
COPY target/helloWitchMe-0.0.0 /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
