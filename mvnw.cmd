@echo off
setlocal
set BASE_DIR=%~dp0
set WRAPPER_DIR=%BASE_DIR%\.mvn\wrapper
set JAR=%WRAPPER_DIR%\maven-wrapper.jar
if not exist "%JAR%" (
  echo Missing %JAR%. Download it first.
  exit /b 1
)
java -classpath "%JAR%" -Dmaven.multiModuleProjectDirectory="%BASE_DIR%" org.apache.maven.wrapper.MavenWrapperMain %*
