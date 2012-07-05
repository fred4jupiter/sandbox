@ECHO OFF

SET WS_IMPORT_EXEC=%JAVA_HOME%\bin\wsimport.exe
SET DIR=.\src\main\java
SET PACKAGE=com.opitzconsulting.spring.generated

call %WS_IMPORT_EXEC% -s %DIR% -p %PACKAGE% http://localhost:8282/services/HelloWorld?wsdl