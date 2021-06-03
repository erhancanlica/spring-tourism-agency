package bmmf.turzimProje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class TurizmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurizmApplication.class, args);
    }

}
