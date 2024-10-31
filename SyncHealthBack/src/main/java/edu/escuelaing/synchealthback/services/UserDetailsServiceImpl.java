package edu.escuelaing.synchealthback.services;

import edu.escuelaing.synchealthback.enums.RoleEntity;
import edu.escuelaing.synchealthback.models.UserEntity;
import edu.escuelaing.synchealthback.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userByEmail = userRepository.findByEmail(username);
        if(userByEmail.isEmpty()){
            userByEmail = userRepository.findByFullName(username);
        }
        UserEntity user = userByEmail.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        RoleEntity role = user.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name());
        return new User(user.getFullName(), user.getHashPassword(), true, true, true, true, Collections.singletonList(authority));
    }
}
