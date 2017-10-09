package org.xi.springcloud.consumerfeign.service.hystric;

import org.springframework.stereotype.Component;
import org.xi.springcloud.consumerfeign.service.CommonService;

@Component
public class CommonServiceHystric implements CommonService {

    @Override
    public String getProviderPort() {
        return "getProviderPort error!";
    }

    @Override
    public String sayHello(String name) {
        return "sorry, " + name + ", sayHello error!";
    }
}
