cmd /C mvn -DskipTests  install
xcopy /Y .\target\formaciotiles.war C:\projectes\jboss7.2\standalone\deployments