package com.mycompany.myapp.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;

import com.mycompany.myapp.dto.Member;

public class SignInUserDetailsService {
	public void onAuthenticationBinding(Member member, User facebookUser) throws NullPointerException {
		member.setUsesrname(facebookUser.getId());
		member.setEmail(facebookUser.getEmail());
		member.setFirstName(facebookUser.getFirstName());
		member.setLastName(facebookUser.getLastName());
		
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member, null, ROLE.DEFAULT.getRoleList());
//		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
