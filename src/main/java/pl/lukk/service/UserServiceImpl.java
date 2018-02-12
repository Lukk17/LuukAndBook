package pl.lukk.service;

import java.util.ArrayList;
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
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(userRole);
        user.setRoles(roleList);
        //        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }

    @Override
    public void saveEditUser(User databaseUser, User userChanges)
    {
        if (userChanges.getName() != null)
        {
            databaseUser.setName(userChanges.getName());

        }

        if (userChanges.getSurname() != null)
        {
            databaseUser.setSurname(userChanges.getSurname());
        }

        if (userChanges.getEmail() != null)
        {
            databaseUser.setEmail(userChanges.getEmail());
        }

        if (userChanges.getId() != null)
        {
            databaseUser.setId(userChanges.getId());
        }

        if (userChanges.getOfferts() != null)
        {
            databaseUser.setOfferts(userChanges.getOfferts());
        }

        databaseUser.setEnabled(userChanges.getEnabled());

        userRepository.save(databaseUser);

    }

    @Override
    public void roleChange(Long userId, List<Long> rolesId)
    {
        User databaseUser = userRepository.findOne(userId);
        List<Role> roles = new ArrayList<>();

        for (int i = 0; i < rolesId.size(); i++)
        {
            if (rolesId.get(i) != null)
            {
                roles.add(roleRepository.findOne(rolesId.get(i)));
            }
        }
        databaseUser.setRoles(roles);
        userRepository.save(databaseUser);
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
