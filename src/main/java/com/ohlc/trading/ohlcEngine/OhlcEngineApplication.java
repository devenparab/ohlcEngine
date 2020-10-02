package com.ohlc.trading.ohlcEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OHLCEngineApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OHLCEngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OHLCEngineApplication.class, args);
	}

}
