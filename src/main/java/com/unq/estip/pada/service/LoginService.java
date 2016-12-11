package com.unq.estip.pada.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.errors.LoginException;
import com.unq.estip.pada.model.User;
import com.unq.estip.pada.persistence.LoginDAO;

@Transactional
public class LoginService {

	private LoginDAO loginDAO;

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void login(String username, String password) throws LoginException {

		User example = new User(username);
		List<User> u = loginDAO.findByExample(example);

		if (u.size() == 1) {
			String hashedPassword = cryptWithMD5(password);
			if(! hashedPassword.equals(  u.get(0).getPassword() )){
				throw new LoginException();
			}
		} else {
			throw new LoginException();
		}

	}

	public String cryptWithMD5(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			// Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
		return null;

	}

}
