package ga.doblue.port;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PortApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(PortApplication.class, args);
	}
}
