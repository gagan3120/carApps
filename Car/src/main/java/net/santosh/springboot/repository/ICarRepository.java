package net.santosh.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.santosh.springboot.model.*;


public interface ICarRepository extends JpaRepository<Car, Long>{
	public Car addCar(Car car);
	public Car removeCar(long id);
	public Car updateCar(long id, Car car);
	public Car getCar(long id);
	public List<Car> getAllCars();
	public List<Car> getCarsByLocation();
	public List<Car> getCarsByModel();
	public List<Car> getCarsByBrand();

}
