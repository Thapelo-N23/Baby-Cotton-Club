package za.ac.cput.mapper;

import za.ac.cput.domain.Customer;
import za.ac.cput.dto.CustomerResponse;

import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerResponse toDto(Customer customer) {
        CustomerResponse dto = new CustomerResponse();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEmail(customer.getEmail());

        if (customer.getOrders() != null) {
            dto.setOrders(
                    customer.getOrders().stream()
                            .map(CustomerOrderMapper::toDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
