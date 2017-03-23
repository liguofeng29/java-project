package spring4.second;

import org.springframework.stereotype.Component;

@Component("SomeServiceAImpl")
public class SomeServiceAImpl implements ISomeService {

    @Override
    public String getMessage() {
        return "AAA";
    }

}
