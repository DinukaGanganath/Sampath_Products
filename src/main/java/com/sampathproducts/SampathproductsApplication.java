package com.sampathproducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.sampathproducts.Employee.EmployeeDao;
import com.sampathproducts.User.User;
import com.sampathproducts.User.UserDao;
import com.sampathproducts.Role.Role;
import com.sampathproducts.Role.RoleDao;

@SpringBootApplication
@RestController // to get the servlet mapping by Rest
public class SampathproductsApplication {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(SampathproductsApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String index() {
		return "<h1>Hello World !</h1>";
	}

	@RequestMapping(value = "/createadmin")
	public String generateAdmin() {
		User adminUser = new User();
		adminUser.setUser_name("Admin");
		adminUser.setUser_email("admin@gmail.com");
		adminUser.setUser_password(bcryptPasswordEncoder.encode("12345"));
		adminUser.setUser_status(true);
		adminUser.setUser_addeddate(LocalDateTime.now());

		adminUser.setEmployee_id(employeeDao.getReferenceById(4));

		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.getReferenceById(5));
		adminUser.setRoles(roles);

		userDao.save(adminUser);

		return "<script>window.location.replace('http://localhost:8080/login');</script>";
	}
}
