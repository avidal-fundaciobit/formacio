cmd /C mvn -DskipTests clean  install

xcopy /Y .\target\formaciorest.war C:\projectes\jboss7.2\standalone\deployments