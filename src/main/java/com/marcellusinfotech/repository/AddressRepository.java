package com.marcellusinfotech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcellusinfotech.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Optional<Address> findById(Integer addressId);

}
