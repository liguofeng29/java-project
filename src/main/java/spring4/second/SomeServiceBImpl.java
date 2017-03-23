package spring4.second;

import org.springframework.stereotype.Component;

@Component("SomeServiceBImpl")
public class SomeServiceBImpl implements ISomeService {

    @Override
    public String getMessage() {
        return "BBB";
    }
}
