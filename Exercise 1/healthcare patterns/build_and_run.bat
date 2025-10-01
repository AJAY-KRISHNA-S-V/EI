@echo off
chcp 65001 >nul
echo Compiling Java sources (UTF-8)...
javac -encoding UTF-8 app\*.java util\*.java observer\*.java strategy\*.java singleton\*.java factory\*.java adapter\*.java decorator\*.java Main.java
if ERRORLEVEL 1 (
  echo Compilation failed.
  pause
  exit /b 1
)
echo Compilation successful.
echo Running application...
java Main
pause
