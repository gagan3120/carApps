package net.santosh.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.santosh.springboot.exception.ModelAddException;
import net.santosh.springboot.exception.ModelEmptyListException;
import net.santosh.springboot.exception.ModelNotFoundException;
import net.santosh.springboot.exception.ModelUpdateException;
import net.santosh.springboot.exception.ResourceNotFoundException;
import net.santosh.springboot.model.Car;
import net.santosh.springboot.model.ForSale;
import net.santosh.springboot.repository.ICarRepository;
import net.santosh.springboot.repository.ICardRepository;
import net.santosh.springboot.repository.IForSaleRepository;

@Service
@Transactional
public class IForSaleServiceImpl implements IForSaleService {

	@Autowired
	private IForSaleRepository iForSaleRepository;
	
	@Autowired
	private ICarRepository ICarRepository;
	
	@Override
	public ForSale addSale(ForSale forSale) {
		try {
			ForSale sale_details = iForSaleRepository.save(forSale);
			Optional<Car> carList = this.ICarRepository.findById(sale_details.getCarId());
			if (carList.isPresent()) {
				Car carUpdate = carList.get();
				carUpdate.setOnSale(true);
				ICarRepository.save(carUpdate);
				return sale_details;
			}
			else {
				throw new ResourceNotFoundException("Car not found ");
			}
		} catch (Exception e) {
			throw new ModelAddException("Can't add the model for sale");
		}
	}

	@Override
	public void removeSale(long saleId) {
		Optional<ForSale> saleList = this.iForSaleRepository.findById(saleId);

		if (saleList.isPresent()) {
			try {
				Optional<Car> carList = this.ICarRepository.findById(saleList.get().getCarId());
				if (carList.isPresent()) {
					Car carUpdate = carList.get();
					carUpdate.setOnSale(false);
					ICarRepository.save(carUpdate);
				}
				else {
					throw new ResourceNotFoundException("Car not found ");
				}
			} catch (Exception e) {
				throw new ModelAddException("Can't remove the model from sale");
			}
			this.iForSaleRepository.delete(saleList.get());
		} else {
			throw new ResourceNotFoundException("sale not found with ID :" + saleId);
		}
	}

	@Override
	public ForSale updateSale(double cost, long saleId) {
		try {
			Optional<ForSale> saleList = this.iForSaleRepository.findById(saleId);
			if (saleList.isPresent()) {
				ForSale saleUpdate = saleList.get();
				saleUpdate.setCost(cost);

				iForSaleRepository.save(saleUpdate);
				return saleUpdate;
			} else {
				throw new ResourceNotFoundException("sale not found with ID :" + saleId);
			}
		} catch (Exception e) {
			throw new ModelUpdateException("Couldn't update the sale details, please try again ");
		}
	}

	@Override
	public ForSale getSale(long saleId) {
		Optional<ForSale> saleItem = this.iForSaleRepository.findById(saleId);
		try {
			if (saleItem.isPresent()) {
				return saleItem.get();
			} else {
				throw new ResourceNotFoundException("sale not found with ID :" + saleId);
			}
		} catch (Exception e) {
			throw new ModelNotFoundException("Couldn't find the sale by id" + saleId);
		}
	}

	@Override
	public List<Car> getAllSales() {
		try {
			return (List<Car>) ICarRepository.findByOnSaleTrue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ModelEmptyListException("Error retriving cars...please try again");
		}
	}

	@Override
	public ForSale getSaleByCarId(long carId) {
		Optional<ForSale> saleItem = this.iForSaleRepository.findByCarId(carId);
		try {
			if (saleItem.isPresent()) {
				return saleItem.get();
			} else {
				throw new ResourceNotFoundException("sale not found with ID :" + carId);
			}
		} catch (Exception e) {
			throw new ModelNotFoundException("Couldn't find the sale by id" + carId);
		}
	}

}
