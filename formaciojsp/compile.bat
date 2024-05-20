@echo off 
call mvn install
xcopy .\target\formaciojsp.war C:\projectes\jboss7.2\standalone\deployments /c /d /i /y