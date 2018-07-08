package by.prostrmk.ritualServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.prostrmk.ritualServices")
public class RitualServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RitualServicesApplication.class, args);
    }
}
