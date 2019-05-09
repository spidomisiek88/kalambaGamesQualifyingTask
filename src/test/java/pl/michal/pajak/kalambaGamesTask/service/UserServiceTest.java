package pl.michal.pajak.kalambaGamesTask.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michal.pajak.kalambaGamesTask.model.entitis.User;
import pl.michal.pajak.kalambaGamesTask.repository.UserRepository;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUser() {
        User user = new User();
        user.setId(12);
        user.setCreationDate(LocalDate.now());

        Mockito.when(userRepository.isUserAlreadyExist(12)).thenReturn(false);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Assertions.assertTrue(userService.addUser(12));
    }

    @Test
    public void shouldNotAddUser() {
        User user = new User();
        user.setId(13);
        user.setCreationDate(LocalDate.now());

        Mockito.when(userRepository.isUserAlreadyExist(13)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Assertions.assertFalse(userService.addUser(13));
    }
}
