package com.batrits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启SpringBoot对Servlet组件的支持
@SpringBootApplication
public class BatritsManagementApplication {

	public static void main(String[] args) {

        SpringApplication.run(BatritsManagementApplication.class, args);
	}

}
