package dealerLot;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
class CarController {

    private final CarRepository repository;
    private final CarModelAssembler assembler;

    CarController(CarRepository repository, CarModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    @GetMapping("/cars")
    CollectionModel<EntityModel<Car>> all() {
        List<EntityModel<Car>> cars = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cars, linkTo(methodOn(CarController.class).all()).withSelfRel());
    }

    @PostMapping("/cars")
    Car newCar(@RequestBody Car newCar) {
        return repository.save(newCar);
    }

    // Single item
    @GetMapping("/cars/{id}")
    EntityModel<Car> one(@PathVariable Long id) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        return assembler.toModel(car);
    }

    @PutMapping("/cars/{id}")
    Car replaceCar(@RequestBody Car newCar, @PathVariable Long id) {
        return repository.findById(id)
                .map(car -> {
                    car.setMake(newCar.getMake());
                    car.setModel(newCar.getModel());
                    car.setYear(newCar.getYear());
                    car.setMileage(newCar.getMileage());
                    car.setAutomatic(newCar.isAutomatic());
                    car.setCarType(newCar.getCarType());
                    return repository.save(car);
                })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return repository.save(newCar);
                });
    }

    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
