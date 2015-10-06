set SCRIPT_DIR=%~dp0
java -XX:+CMSClassUnloadingEnabled -Xmx1024M -Xss2M -jar "%SCRIPT_DIR%\sbt-launch-0.13.1.jar" %*
