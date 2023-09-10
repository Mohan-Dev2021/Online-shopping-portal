package com.online.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.online.shop.file.storage.*;
@SpringBootApplication

public class OnlineShoppingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingPlatformApplication.class, args);
	}

}
