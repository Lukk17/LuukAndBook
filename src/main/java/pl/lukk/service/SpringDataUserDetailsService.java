package pl.lukk.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.lukk.entity.Role;
import pl.lukk.entity.User;

public class SpringDataUserDetailsService implements UserDetailsService
{
    private UserService userService;

    @Autowired
    public void setUserRepository(UserService userService)
    {
        this.userService = userService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userService.findByUserEmail(email);
    if (user == null) {throw new UsernameNotFoundException(email); }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : user.getRoles()) {
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return new org.springframework.security.core.userdetails.User(
    user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
