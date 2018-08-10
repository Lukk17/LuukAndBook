package pl.lukk.service;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import pl.lukk.Application;
import pl.lukk.entity.Role;
import pl.lukk.entity.User;

@SpringBootTest(classes =
{ Application.class })
@RunWith(SpringRunner.class)
//@SqlGroup(
//{ @Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) })
@TestPropertySource("/application.properties")
public class IntegrationTest
{

    @Autowired
    private UserService userService;

    @Test
    public void given_User_when_saving_user_then_user_is_saved()
    {
        //given
        Role role = new Role();
        role.setName("ROLE_USER");
        List<Role> roles = Arrays.asList(role);
        
        User newUser = new User();
        newUser.setEmail("integration@mail.com");
        newUser.setPassword("pass");
        newUser.setName("Integ");
        newUser.setSurname("Gration");
        //when
        userService.saveUser(newUser);
        User user = userService.findByUserEmail(newUser.getEmail());
        //then
        Assertions.assertThat(user.getEmail()).isEqualTo(newUser.getEmail());
        Assertions.assertThat(user.getName()).isEqualTo(newUser.getName());
        Assertions.assertThat(user.getSurname()).isEqualTo(newUser.getSurname());
        Assertions.assertThat(user.getId()).isEqualTo(newUser.getId());
        Assertions.assertThat(user.getRoles().equals(roles));
    }

    @Test(expected = NullPointerException.class)
    public void given_User_with_null_email_when_saving_user_then_null_pointer_execption_should_be_thrown()
    {
        //given
        User newUser = new User();
        newUser.setEmail(null);
        //when
        userService.saveUser(newUser);
        //then exception is thrown, if not fail
        Assert.fail();
    }

}
