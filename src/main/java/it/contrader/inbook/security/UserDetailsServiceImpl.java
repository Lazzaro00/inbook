package it.contrader.inbook.security;

import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Transactional
    public UserDetails loadByEmail(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotExistException("User Not Found with email "+ email));

        return UserDetailsImpl.build(user);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotExistException("User Not Found with email "+ email));

        return UserDetailsImpl.build(user);
    }
}
