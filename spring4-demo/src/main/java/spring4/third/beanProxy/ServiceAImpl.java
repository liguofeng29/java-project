package spring4.third.beanProxy;

import org.springframework.stereotype.Component;

@Component
public class ServiceAImpl implements ServiceA {
    public void show() {
        System.out.println("私はAさんです。");
    }
}
