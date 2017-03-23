package spring4.third.lazyInitSample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        // ComponentB以外は遅延生成に設定する
        // ComponentB生成

        context.getBean(ComponentA.class);
        // ComponentA生成

        /**
         * CoponentBはnew AnnotationConfigApplicationContextで生成される
         * それ以外は遅延生成になり、必要な際に生成させる
         */
    }
}