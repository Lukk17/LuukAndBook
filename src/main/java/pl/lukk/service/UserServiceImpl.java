package pl.lukk.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Role;
import pl.lukk.entity.User;
import pl.lukk.repository.RoleRepository;
import pl.lukk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository        userRepository;
    private final RoleRepository        roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public Page<User> findAll(Pageable pageable)
    {
        return userRepository.findAll(pageable);
    }
    

    @Override
    public User findByUserEmail(String email)
    {
       
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User findByUserId(Long id)
    {
       
        return userRepository.findById(id);
    }

    @Override
    public void saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }
    @Override
    public void saveEditUser(User user)
    {
        
        userRepository.save(user);

    }
    @Override
    public boolean checkPassword(String newPassword, String password)
    {
       return passwordEncoder.matches(newPassword, password);
        
    }
    
    @Override
    public void delete(Long id)
    {
       
        userRepository.delete(userRepository.findById(id));
    }
    
    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

}
