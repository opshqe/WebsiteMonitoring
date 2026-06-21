FROM amazoncorretto:21
WORKDIR /app
COPY . .
RUN find . -name "*.java" > sources.txt && javac @sources.txt
ENTRYPOINT ["java", "Main"]