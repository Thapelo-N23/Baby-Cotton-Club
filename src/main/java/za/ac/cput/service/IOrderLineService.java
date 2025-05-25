/*
Baby Cotton Club
IOrderLineService
Author: Tsireledzo Netshilonwe
Student Number: 230666426
Date: 2025/05/24
*/
package za.ac.cput.service;

import za.ac.cput.domain.OrderLine;

import java.util.List;

public interface IOrderLineService extends IService<OrderLine, String>{
    List<OrderLine> getAll();

}
