package spring4.third.modifyBeanDefine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ComponentA {
    public ComponentA() {
        System.out.println("ComponentA生成");
    }
}
