package ga.doblue.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProjectApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
