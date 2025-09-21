package za.ac.cput.service;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.CustomerOrder;

import java.util.List;

public interface IAdminService extends IService<Admin, Integer> {

    List<Admin> getAll();
}