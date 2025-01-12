call ant -buildfile source_code\mymbean\build.xml clean compile jar
call copy .\source_code\mymbean\build\jar\MyBean.jar .\lib\
call ant -buildfile source_code\mymbeanreader\build.xml clean compile dist
call copy .\source_code\mymbeanreader\dist\myapp-0.1-dev.war .\webapps\