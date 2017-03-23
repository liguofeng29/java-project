package spring4.third.modifyBeanDefine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        // ComponentA生成

        /**
         * singoletonはcontext生成時に生成されるが、
         * ModifyBeanDefinitionFactoryPostProcessorでscopeをprototypeに変更したため、生成されない
         */
    }
}