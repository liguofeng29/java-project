package spring4.third.lazyInitSample;

import org.springframework.stereotype.Component;

@Component
public class ComponentB {
    public ComponentB() {
        System.out.println("ComponentB生成");
    }
}
