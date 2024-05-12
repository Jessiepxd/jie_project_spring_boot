package ca.sheridancollege.chen162;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ca.sheridancollege")
public class Assignment3JieChenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment3JieChenApplication.class, args);
	}

}
