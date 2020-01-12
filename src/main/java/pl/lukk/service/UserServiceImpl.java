package pl.lukk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.Role;
import pl.lukk.entity.User;
import pl.lukk.repository.RoleRepository;
import pl.lukk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    StorageService storageService;

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

        return userRepository.findById(id).orElse(null);
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
        user.setPhotoPath("../uploads/profile.jpg");
        userRepository.save(user);

    }

    @Override
    public void saveEditUser(String email, User userChanges)
    {
        User databaseUser = userRepository.findByEmail(email);

        if (userChanges.getName() != null)
        {
            databaseUser.setName(userChanges.getName());
        }

        if (userChanges.getSurname() != null)
        {
            databaseUser.setSurname(userChanges.getSurname());
        }

        if (userChanges.getOffers() != null)
        {
            databaseUser.setOffers(userChanges.getOffers());
        }

        databaseUser.setEnabled(userChanges.isEnabled());

        userRepository.save(databaseUser);
    }

    @Override
    public void adminEditUser(User userChanges)
    {
        User databaseUser = userRepository.findByEmail(userChanges.getEmail());
        

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

        if (userChanges.getOffers() != null)
        {
            databaseUser.setOffers(userChanges.getOffers());
        }
        if (userChanges.getOffers() != null)
        {
            databaseUser.setEnabled(userChanges.isEnabled());
        }
        userRepository.save(databaseUser);

    }

    @Override
    public void savePhoto(String email, MultipartFile photo)
    {
        User user = userRepository.findByEmail(email);
        storageService.storeProfilePhoto(photo, user);

        String photoPath = ("../uploads/" + user.getEmail());
        user.setPhotoPath(photoPath);
        userRepository.save(user);
    }

    @Override
    public void roleChange(Long userId, List<Long> rolesId)
    {
        User databaseUser = userRepository.findById(userId).orElse(null);
        List<Role> roles = new ArrayList<>();

        for (int i = 0; i < rolesId.size(); i++)
        {
            if (rolesId.get(i) != null)
            {
                roles.add(roleRepository.findById(rolesId.get(i)).orElse(null));
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

        userRepository.delete(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

}
