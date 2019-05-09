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
import pl.michal.pajak.kalambaGamesTask.model.entitis.Game;
import pl.michal.pajak.kalambaGamesTask.model.entitis.User;
import pl.michal.pajak.kalambaGamesTask.model.enums.ActionType;
import pl.michal.pajak.kalambaGamesTask.repository.GameRepository;
import pl.michal.pajak.kalambaGamesTask.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GameServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    GameRepository gameRepository;
    @InjectMocks
    GameService gameService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddGame() {

        int userId = 14;
        int gameId = 15;

        User user = new User();
        user.setId(userId);
        user.setCreationDate(LocalDate.now());

        Game game = new Game();
        game.setId(gameId);
        game.setCreationDate(LocalDate.now());
        game.setAction(ActionType.DEFENSE);
        game.setUser(user);

        Mockito.when(userRepository.isUserAlreadyExist(userId)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(gameRepository.isGameAlreadyExist(gameId)).thenReturn(false);
        Mockito.when(gameRepository.save(game)).thenReturn(game);

        Assertions.assertTrue(gameService.addGame(gameId, userId, ActionType.DEFENSE.getName()));
    }

    @Test
    public void shouldNotAddGame() {

        int userId = 14;
        int gameId = 15;

        User user = new User();
        user.setId(userId);
        user.setCreationDate(LocalDate.now());

        Game game = new Game();
        game.setId(gameId);
        game.setCreationDate(LocalDate.now());
        game.setAction(ActionType.DEFENSE);
        game.setUser(user);

        Mockito.when(userRepository.isUserAlreadyExist(userId)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(gameRepository.isGameAlreadyExist(gameId)).thenReturn(true);
        Mockito.when(gameRepository.save(game)).thenReturn(game);

        Assertions.assertFalse(gameService.addGame(gameId, userId, ActionType.DEFENSE.getName()));
    }

    @Test
    public void shouldEditGame() {

        int userId = 14;
        int gameId = 15;

        User user = new User();
        user.setId(userId);
        user.setCreationDate(LocalDate.now());

        Game game = new Game();
        game.setId(gameId);
        game.setCreationDate(LocalDate.now());
        game.setAction(ActionType.DEFENSE);
        game.setUser(user);

        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(userRepository.isUserAlreadyExist(userId)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(gameRepository.isGameAlreadyExist(gameId)).thenReturn(true);
        Mockito.when(userRepository.findUserById(userId)).thenReturn(Optional.of(user));
        Mockito.when(gameRepository.findGameById(gameId)).thenReturn(Optional.of(game));
        Mockito.when(gameRepository.save(game)).thenReturn(game);

        Assertions.assertTrue(gameService.editGame(gameId, userId, ActionType.DEFENSE.getName()));
    }
}
