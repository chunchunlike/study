package org.xi.springcloud.consumerribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProviderPortError")
    public String getProviderPort() {
        return restTemplate.getForObject("http://PROVIDER-XI/getProviderPort", String.class);
    }

    @HystrixCommand(fallbackMethod = "sayHelloError")
    public String sayHello(String name) {
        return restTemplate.getForObject("http://PROVIDER-XI/sayHello?name=" + name, String.class);
    }

    public String getProviderPortError() {
        return "getProviderPort error!";
    }

    public String sayHelloError(String name) {
        return "sorry, " + name + ", sayHello error!";
    }
}
