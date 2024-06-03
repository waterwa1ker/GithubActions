package com.example.github_actions;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GithubActionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubActionsApplication.class, args);
	}

	@Bean
	MeterBinder meterBinder() {
		return meterRegistry -> {
			Counter
					.builder("find-all-counter")
					.description("Количество обращений к запросу findAll")
					.register(meterRegistry);
		};
	}
}

