package net.santosh.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.santosh.springboot.model.*;
/***************************************************************
 * @author              G Gagandeep reddy
 * Description          It is a test repository interface that extends JPA repository 
 *                      that contains inbuilt methods for various CRUD operations
 * Version              1.0
 * created date         24-03-2021
 *
 ****************************************************************/

public interface ICarRepository extends JpaRepository<Car, Long> {

	List<Car> findAll();
	
	List<Car> findByUserId(String userId);

	List<Car> findByModel(String model);

	List<Car> findByBrand(String brand);
	
	List<Car> findByOnSaleTrue();

}
