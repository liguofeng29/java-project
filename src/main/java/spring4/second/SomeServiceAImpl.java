package spring4.second;

import org.springframework.stereotype.Component;

@Component("SomeServiceAImpl")
public class SomeServiceAImpl implements SomeService {

    @Override
    public String getMessage() {
        return "AAA";
    }
}
