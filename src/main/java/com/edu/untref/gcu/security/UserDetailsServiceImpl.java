package com.edu.untref.gcu.security;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.UsuarioDAO;
import com.edu.untref.gcu.domain.Usuario;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource(name = "usuarioDAO")
	private UsuarioDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		Usuario usuarioBase = this.usuarioDAO.findByUsername(userName);

		if (usuarioBase == null) {
			throw new UsernameNotFoundException("No existe usuario con el username: " + userName);
		}

		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(usuarioBase.getUserName(), usuarioBase.getPassword(),
				Boolean.TRUE, accountNonExpired, credentialsNonExpired,
				accountNonLocked, new ArrayList<GrantedAuthority>());
	}

}