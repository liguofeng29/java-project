package spring4.second;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {

        ApplicationContext context1 = new AnnotationConfigApplicationContext(App.class);
        context1.getBean(SomeObject.class).run();

        ApplicationContext context2 = new FileSystemXmlApplicationContext(
                "classpath:spring4/second/applicationContext.xml");
        context2.getBean(SomeObject.class).run();
    }
}