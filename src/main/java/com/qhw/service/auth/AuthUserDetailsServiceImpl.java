package com.qhw.service.auth;

import com.qhw.dao.UserRepository;
import com.qhw.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2020/4/27  20:02
 */
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
        }else {
            //查找角色
//            List<String> roles =  roleService.getRolesByUserName(username);
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            for (String role : roles) {
//                authorities.add(new SimpleGrantedAuthority(role));
//            }
            return new org.springframework.security.core.userdetails.User(username,user.getPassword(),
                    true,true,true,true, AuthorityUtils.NO_AUTHORITIES);
        }
    }
}
