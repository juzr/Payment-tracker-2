package paymenttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"view", "controller", "model"})
@SpringBootApplication
public class PaymenttrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymenttrackerApplication.class, args);
	}
}
