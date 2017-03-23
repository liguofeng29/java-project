package spring4.third.lazyInitSample;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {
    public ComponentA() {
        System.out.println("ComponentA生成");
    }
}
