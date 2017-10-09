package org.xi.springcloud.routingzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class RoutingZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutingZuulApplication.class, args);
	}
}
