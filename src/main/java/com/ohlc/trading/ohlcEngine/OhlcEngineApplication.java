package com.ohlc.trading.ohlcEngine;

import com.ohlc.trading.ohlcEngine.process.reader.WorkerReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OhlcEngineApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OhlcEngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OhlcEngineApplication.class, args);
	}

}
