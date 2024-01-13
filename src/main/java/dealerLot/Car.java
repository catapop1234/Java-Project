package dealerLot;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

    private @Id @GeneratedValue Long id;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private boolean isAutomatic;
    private CarType carType;

    public enum CarType {
        SEDAN,
        SUV,
        TRUCK,
        COUPE
    }

    public Car() {}

    public Car(String make, String model, int year, int mileage, boolean isAutomatic, CarType carType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.isAutomatic = isAutomatic;
        this.carType = carType;
    }

    public Long getId() {
        return this.id;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public int getMileage() {
        return this.mileage;
    }

    public boolean isAutomatic() {
        return this.isAutomatic;
    }

    public CarType getCarType() {
        return this.carType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    // Equals method for object comparison
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return Objects.equals(id, car.id) &&
            Objects.equals(make, car.make) &&
            Objects.equals(model, car.model) &&
            year == car.year &&  // Use == for primitive types like int
            Objects.equals(mileage, car.mileage) &&
            Objects.equals(isAutomatic, car.isAutomatic) &&
            carType == car.carType;
}

    // HashCode method for generating hash codes
    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, year, mileage, isAutomatic, carType);
    }

    // ToString method for providing a human-readable representation
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", isAutomatic=" + isAutomatic +
                ", carType=" + carType +
                '}';
    }
}
