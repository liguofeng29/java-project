package spring4.second;

import org.springframework.stereotype.Component;

@Component("SomeServiceBImpl")
public class SomeServiceBImpl implements SomeService {

    @Override
    public String getMessage() {
        return "BBB";
    }
}
