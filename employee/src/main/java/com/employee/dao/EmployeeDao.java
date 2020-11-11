package com.employee.dao;

import org.springframework.data.repository.CrudRepository;
import com.employee.modal.EmployeeMaster;


public interface EmployeeDao extends CrudRepository<EmployeeMaster, Integer>{


}
