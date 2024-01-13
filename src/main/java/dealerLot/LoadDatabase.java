package dealerLot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CarRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Car("Toyota", "Camry", 2022, 0, true, Car.CarType.SEDAN)));
            log.info("Preloading " + repository.save(new Car("Honda", "CR-V", 2021, 15000, false, Car.CarType.SUV)));
        };
    }
}
