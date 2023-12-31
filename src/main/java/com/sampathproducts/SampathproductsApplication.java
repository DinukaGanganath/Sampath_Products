package com.sampathproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // to get the servlet mapping by Rest
public class SampathproductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampathproductsApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String index() {
		return "<h1>Hello World !</h1>";
	}
}
