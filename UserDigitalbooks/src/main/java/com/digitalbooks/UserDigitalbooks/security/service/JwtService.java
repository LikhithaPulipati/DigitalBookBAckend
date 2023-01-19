package com.digitalbooks.UserDigitalbooks.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitalbooks.UserDigitalbooks.dao.UserDao;
import com.digitalbooks.UserDigitalbooks.entity.JwtRequest;
import com.digitalbooks.UserDigitalbooks.entity.JwtResponse;
import com.digitalbooks.UserDigitalbooks.entity.User;
import com.digitalbooks.UserDigitalbooks.security.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		User user = userDao.findById(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();

		if (user != null) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
