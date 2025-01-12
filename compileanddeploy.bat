call ant -buildfile source_code\mybean\build.xml clean compile jar
call copy .\source_code\mybean\build\jar\MyBean.jar .\lib\
call ant -buildfile source_code\mybeanreader\build.xml clean compile dist
call copy .\source_code\mybeanreader\dist\myapp-0.1-dev.war .\webapps\