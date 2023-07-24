package com.cab.Service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.Exception.AdminException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Model.Admin;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.UserLoginDTO;
import com.cab.Repositary.AdminRepo;
import com.cab.Repositary.CurrentUserSessionRepo;

@Service
public class UserLoginServiceimpl implements UserLoginService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentUserSessionRepo currRepo;

	@Override
	public CurrentUserSession login(UserLoginDTO dto) throws AdminException {

		Optional<Admin> findAdmin = adminRepo.findByEmail(dto.getEmail());

		if (findAdmin.isPresent()) {
			Admin currAdmin = findAdmin.get();
			Optional<CurrentUserSession> validAdminSession = currRepo.findById(currAdmin.getAdminId());
			if (validAdminSession.isPresent()) {
				throw new AdminException("Admin is currently Login Please Logout and then try");
			} else {
				if (currAdmin.getPassword().equals(dto.getPassword())) {
					String key = RandomStringUtils.randomAlphanumeric(6);
					String otp = RandomStringUtils.randomNumeric(6);
					CurrentUserSession curr = new CurrentUserSession();
					curr.setUuid(key);
					curr.setCurrRole("Admin");
					curr.setCurrStatus("Login Successfull");
					curr.setCurrUserId(currAdmin.getAdminId());
					return currRepo.save(curr);
				} else {
					throw new AdminException("Please Enter the correct Password");
				}
			}
		} else {
			throw new AdminException("Admin is Not Registered");
		}
	}

	@Override
	public String LogOut(String uuid) throws CurrentUserSessionException {
		// TODO Auto-generated method stub
		Optional<CurrentUserSession> validAdminOrCustomer = currRepo.findByUuid(uuid);
		if (validAdminOrCustomer.isPresent()) {

			currRepo.delete(validAdminOrCustomer.get());
			return "Admin Logged Out Successfully";

		} else {
			throw new CurrentUserSessionException("Admin Not Logged In with this Credentials");
		}
	}

}
