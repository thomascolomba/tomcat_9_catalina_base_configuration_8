# tomcat_9_catalina_base_configuration_8

Se expone un java MBean a una webapp.

proyecto de MBean con su código fuente y build de Ant : ./source_code/mymbean/

proyecto de la webapp que enseña el MBean con su código fuente y build de Ant : ./source_code/mymbeanreader/

Compilar y desplegar el jar del MBean se hace con : ./compileanddeploy.bat
(para el MBean :  ant clean compile jar + copiar el jar en la carpeta lib/ de CATALINA_BASE
para la webapp : ant clean compile dist  + copiar el war en la carpeta webapps/ de CATALINA_BASE)

Al acceder a la webapp : http://localhost:8080/myapp-0.1-dev/ControllerServletMBeanReader
Se ve : foo = value of foo set from server.xml in CATALINA_BASEfoo2 = Default Foo2 from MyBean.javafoo3 = Default Foo3 from MyBean.java, bar = 24

------------ CATALINA_BASE/conf/server.xml
en la etiqueta <GlobalNamingResources> :
	<Resource name="bean/MyBeanFactory" auth="Container"
		type="com.mycompany.packageofmybean.MyBean"
		factory="org.apache.naming.factory.BeanFactory"
		bar="24" foo="value of foo set from server.xml in CATALINA_BASE"/>


------------ MBean
public class MyBean {

  private String foo = "Default Foo from MyBean.java";
  private String foo2 = "Default Foo2 from MyBean.java";
  private String foo3 = "Default Foo3 from MyBean.java";

------------ webapp, ControllerServletMBeanReader.java
public void init() throws ServletException {
	// Do required initialization
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		MyBean bean = (MyBean) envCtx.lookup("bean/MyBeanFactoryDefinedInContext");
		message2 = "foo = " + bean.getFoo() + "foo2 = " + bean.getFoo2() + "foo3 = " + bean.getFoo3() + ", bar = " +bean.getBar();
	} catch(Exception e){
		message2 = e.getMessage();
	}
}
