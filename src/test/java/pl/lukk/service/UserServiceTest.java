package pl.lukk.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.lukk.entity.User;
import pl.lukk.repository.RoleRepository;
import pl.lukk.repository.UserRepository;

public class UserServiceTest
{
    private UserService userService;

    UserRepository userRepo;

    RoleRepository roleRepo;

    BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception
    {
        userRepo = mock(UserRepository.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        userService = new UserServiceImpl(userRepo, roleRepo, passwordEncoder);
    }

    @Test
    public void testFindAllPageable()
    {
        //given
        Pageable pageable = new PageRequest(0, 5);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        Page<User> users = new PageImpl<User>( userList, pageable, userList.size());
        
        when(userRepo.findAll(pageable))
        .thenReturn(users);
        
        //when
        Page<User> result = userService.findAll(pageable);
        //then
        
        assertEquals(users, result);
    }

    @Test
    public void testFindByUserEmail()
    {
        User user = new User();
        String email = "test@mail.com";
        user.setEmail(email);
        
        when(userRepo.findByEmail(email)).thenReturn(user);
        
        //when
        User result = userService.findByUserEmail(email);
        //then
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void testFindByUserId()
    {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        
        when(userRepo.findById(id)).thenReturn(user);
        //when
        User result = userService.findByUserId(id);
        //then
        assertEquals(user.getId(), result.getId());
    }

    
    @Test
    public void testCheckPassword()
    {
        String password = "pass";
        String newPassword = "pass";
        Boolean expected = false;
        
        when(passwordEncoder.matches(newPassword, password)).thenReturn(false);
        
        //when
        Boolean result = userService.checkPassword(newPassword, password);
        //then
        assertEquals(expected, result);
    }


    @Test
    public void testFindAll()
    {
        List<User> users = new ArrayList<>();
        users.add(new User());
        
        
        
        when(userRepo.findAll()).thenReturn(users);
        
        //when
        List<User> result = userService.findAll();
        //then
        assertEquals(users, result);
    }
}
