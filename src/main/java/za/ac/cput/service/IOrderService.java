/*
Baby Cotton Club
IOrderService
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.service;

import za.ac.cput.domain.Order;

import java.util.List;

public interface IOrderService extends IService<Order, String> {
    List<Order> getAll();


}
