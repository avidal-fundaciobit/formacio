cmd /C mvn -DskipTests  install
xcopy /Y .\target\formaciospringmvc.war C:\projectes\jboss7.2\standalone\deployments