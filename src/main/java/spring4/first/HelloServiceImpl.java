package spring4.first;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    public String getMessage() {
        return "Hello, World!";
    }

}