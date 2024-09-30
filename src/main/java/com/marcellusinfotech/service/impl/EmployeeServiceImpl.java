package com.marcellusinfotech.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.marcellusinfotech.entity.Address;
import com.marcellusinfotech.entity.Employee;
import com.marcellusinfotech.entity.dto.AddressDto;
import com.marcellusinfotech.entity.dto.EmployeeDto;
import com.marcellusinfotech.exception.ResourceNotFoundException;
import com.marcellusinfotech.repository.AddressRepository;
import com.marcellusinfotech.repository.EmployeeRepository;
import com.marcellusinfotech.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final AddressRepository addressRepository;
	private final ModelMapper modelMapper;

	@Override
	public EmployeeDto registerEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		Set<Address> addresses = new HashSet();
		if (employeeDto.getAddressList() != null) {
			employeeDto.getAddressList().forEach(aDto -> {
				Address address = modelMapper.map(aDto, Address.class);
				addresses.add(address);
			});
		}
		employee.setAddressList(addresses);
		employeeRepository.save(employee);
		return employeeDto;
	}

	@Override
	public EmployeeDto findByEmployeeId(String employeeId) {
		EmployeeDto employeeDto = new EmployeeDto();
		Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId(employeeId);

		if (byEmployeeId.isPresent()) {
			Employee employee = byEmployeeId.get();
			EmployeeDto employeeDtos = modelMapper.map(employee, EmployeeDto.class);

			Set<AddressDto> addressDtos = new HashSet<>();
			employee.getAddressList().forEach(a -> {
				AddressDto addressDto = modelMapper.map(a, AddressDto.class);
				addressDtos.add(addressDto);
			});

			employeeDto.setAddressList(addressDtos);
			return employeeDtos;
		}

		throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
	}

	@Override
	public List<EmployeeDto> findAllEmployees() {
		List<Employee> listEmployees = employeeRepository.findAll();

		if (listEmployees.isEmpty()) {
			throw new ResourceNotFoundException("No employees found");
		}

		List<EmployeeDto> employeeDtos = new ArrayList<>();
		listEmployees.forEach(e -> {
			EmployeeDto employeeDto = modelMapper.map(e, EmployeeDto.class);

			Set<AddressDto> addressDtos = new HashSet<>();
			e.getAddressList().forEach(a -> {
				AddressDto addressDto = modelMapper.map(a, AddressDto.class);
				addressDtos.add(addressDto);
			});

			employeeDto.setAddressList(addressDtos);
			employeeDtos.add(employeeDto);
		});

		return employeeDtos;
	}

	@Override
	public EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

		modelMapper.map(employeeDto, employee);
		Set<Address> existingAddresses = employee.getAddressList();
		Set<Address> updatedAddresses = new HashSet<>();
		if (employeeDto.getAddressList() != null) {
			employeeDto.getAddressList().forEach(aDto -> {
				Address address;
				if (aDto.getAddressId() != null) {
					address = existingAddresses.stream()
							.filter(existingAddress -> existingAddress.getAddressId().equals(aDto.getAddressId()))
							.findFirst().orElseThrow(() -> new ResourceNotFoundException(
									"Address not found with ID: " + aDto.getAddressId()));

					Integer originalAddressId = address.getAddressId();
					modelMapper.map(aDto, address);
				} else {
					address = modelMapper.map(aDto, Address.class);
				}
				updatedAddresses.add(address);
			});
		}
		existingAddresses.addAll(updatedAddresses);
		employeeRepository.save(employee);
		EmployeeDto employeeDto2 = modelMapper.map(employee, EmployeeDto.class);

		return employeeDto2;
	}

	@Override
	public boolean deleteEmployee(String employeeId) {
		EmployeeDto employeeDto = new EmployeeDto();
		Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId(employeeId);

		if (byEmployeeId.isPresent()) {
			Employee employee = byEmployeeId.get();
			employeeRepository.delete(employee);
			return true;
		}
		return false;
	}

}
