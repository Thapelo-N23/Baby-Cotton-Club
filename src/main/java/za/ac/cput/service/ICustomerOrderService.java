/*
Baby Cotton Club
IOrderService
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.service;

import za.ac.cput.domain.CustomerOrder;

import java.util.List;

public interface ICustomerOrderService extends IService<CustomerOrder, Integer> {
    List<CustomerOrder> getAll();


}
