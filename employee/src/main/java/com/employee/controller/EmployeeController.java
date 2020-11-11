package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.employee.dao.AddressMaterDao;
import com.employee.dao.EmployeeDao;
import com.employee.modal.AddressMaster;
import com.employee.modal.EmployeeMaster;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDao empDao;
	@Autowired
	private AddressMaterDao admDao;

	@GetMapping("form")
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("form");
		return mav;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("saveEmployee")
	public Map saveEmployee(
			 @ModelAttribute EmployeeMaster emp) {
		Map ma = new HashMap();
		try {
			if (emp.getId() == null || emp.getId() == 0) {
				empDao.save(emp);
				for (int i = 0; i < emp.getAddress().size(); i++) {
					AddressMaster am = new AddressMaster();
					am.setEmployeeID(emp.getId());
					am.setAddress(emp.getAddress().get(i));
					am.setType(emp.getType().get(i));
					admDao.save(am);
				}
			} else {

				Optional<EmployeeMaster> old1 = empDao.findById(emp.getId());
				EmployeeMaster old = old1.get();
				old.setName(emp.getName());
				old.setEmail(emp.getEmail());
				empDao.save(old);

				for (int i = 0; i < emp.getAddress().size(); i++) {
					Optional<AddressMaster> oldAm = admDao.findById(emp.getAddressID().get(i));
					AddressMaster am = oldAm.get();
					am.setEmployeeID(old.getId());
					am.setAddress(emp.getAddress().get(i));
					am.setType(emp.getType().get(i));
					admDao.save(am);
				}
			}
			ma.put("flag", 1);
			ma.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			ma.put("flag", 2);
			ma.put("msg", "Error Occured : " + e.getCause());
		}
		return ma;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("getEmployeeList")
	public List getEmployeeList() {
		return (List) empDao.findAll();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("deleteEmployee")
	public String deleteEmployee(@RequestParam("id") Integer id) {
		String msg = "";
		try {
			Optional<EmployeeMaster> employee = empDao.findById(id);
			if (employee.isPresent()) {
				List<AddressMaster> am = employee.get().getAddressMaster();
				for (AddressMaster adm : am) {
					admDao.delete(adm);
				}
				empDao.delete(employee.get());
				msg = "Employee is deleted for id = " + id;
			} else {
				msg = "Employee is not found for id = " + id;
			}

		} catch (Exception e) {
			// TODO: handle exception
			msg = "Error Occured :" + e.getCause();
			e.printStackTrace();
		}
		return msg;
	}
}
