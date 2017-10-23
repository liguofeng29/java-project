package spring4.third.beanProxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        context.getBean(ServiceA.class).show();

        /**
         * メソッド開始
         * 私はAさんです。
         * メソッド終了
         */

        /**
         * 結果的に、beanに対してproxyを生成させた
         */
    }
}