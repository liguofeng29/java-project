package spring4.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SomeObject {

    @Autowired
    @Qualifier("SomeServiceAImpl")
    private SomeService serviceA;

    @Autowired
    @Qualifier("SomeServiceBImpl")
    private SomeService serviceB;

    public void run() {
        System.out.println(serviceA.getMessage());
        System.out.println(serviceB.getMessage());
    }
}
