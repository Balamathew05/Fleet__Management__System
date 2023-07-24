package com.cab.Service;

import com.cab.Exception.AdminException;
import com.cab.Exception.CurrentUserSessionException;
import com.cab.Model.CurrentUserSession;
import com.cab.Model.UserLoginDTO;

public interface UserLoginService {

	public CurrentUserSession login(UserLoginDTO dto) throws AdminException;

	public String LogOut(String uuid) throws CurrentUserSessionException;

}
