package spring4.third.modifyBeanDefine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ComponentB {
    public ComponentB() {
        System.out.println("ComponentB生成");
    }
}
