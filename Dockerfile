# .jar로 패키징을 위해 JDK image가 필요
FROM eclipse-temurin:17-jdk-alpine

#jar파일 위치를 지정해줌(굳이 안해도 되는데 혹시 모르니~)
ARG JAR_FILE=build/libs/*.jar

#copy
COPY ${JAR_FILE} ./backend.jar

# run
ENTRYPOINT ["java", "-jar", "./backend.jar"]


