package net.santosh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.santosh.springboot.model.Payment;


/***************************************************************
 * @author              Chitta YAswanth sai
 * Description          It is a test repository interface that extends JPA repository 
 *                      that contains inbuilt methods for various CRUD operations
 * Version              1.0
 * created date         24-03-2021
 *
 ****************************************************************/

public interface IPaymentRepository extends JpaRepository<Payment, Long> {

}
