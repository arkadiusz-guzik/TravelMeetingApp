package com.arkadiuszguzik.TravelMeeting.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkadiuszguzik.TravelMeeting.dao.RoleRepository;
import com.arkadiuszguzik.TravelMeeting.dao.UserRepository;
import com.arkadiuszguzik.TravelMeeting.entity.Role;
import com.arkadiuszguzik.TravelMeeting.entity.User;
import com.arkadiuszguzik.TravelMeeting.user.CrmUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
			
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public void save(CrmUser crmUser) {
		User user = new User();
		 
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setEmail(crmUser.getEmail());

		user.setRoles(Arrays.asList(findRoleByName("ROLE_USER")));
		
		userDao.save(user);

	}
	
	@Override
	public User findByUserName(String userName) {
		
		List<User> listOfUsers = userDao.findAll();

		for(User user : listOfUsers) {
			if(user.getUserName().equals(userName)) {
				
				return user;
			}
		}

		return null;
	}
	
	private Role findRoleByName(String roleName) {
		
		List<Role> listOfRoles = roleDao.findAll();
		
		for(Role role : listOfRoles) {

			if(role.getName().equals(roleName)) {
				return role;
			}
		}
		
		return null;
	}

}
