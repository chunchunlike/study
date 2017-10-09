package org.xi.springcloud.consumerribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonService {

    @Autowired
    RestTemplate restTemplate;

    public String getProviderPort() {
        return restTemplate.getForObject("http://PROVIDER-XI/getProviderPort",String.class);
    }
}
