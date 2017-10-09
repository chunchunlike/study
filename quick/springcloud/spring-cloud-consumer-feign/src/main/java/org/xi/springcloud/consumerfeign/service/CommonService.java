package org.xi.springcloud.consumerfeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xi.springcloud.consumerfeign.service.hystric.CommonServiceHystric;

@FeignClient(value = "provider-xi", fallback = CommonServiceHystric.class)
public interface CommonService {

    @RequestMapping(value = "/getProviderPort", method = RequestMethod.GET)
    String getProviderPort();

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name") String name);
}
