package pl.michal.pajak.kalambaGamesTask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.michal.pajak.kalambaGamesTask.controller.api.GameApiController;
import pl.michal.pajak.kalambaGamesTask.model.entitis.Game;
import pl.michal.pajak.kalambaGamesTask.model.entitis.User;
import pl.michal.pajak.kalambaGamesTask.model.enums.ActionType;
import pl.michal.pajak.kalambaGamesTask.repository.GameRepository;
import pl.michal.pajak.kalambaGamesTask.repository.UserRepository;
import pl.michal.pajak.kalambaGamesTask.service.GameService;
import pl.michal.pajak.kalambaGamesTask.service.UserService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GameApiControllerTest {

    @Mock
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    GameRepository gameRepository;
    @Mock
    GameService gameService;

    @InjectMocks
    GameApiController gameApiController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldSetAction() throws Exception {

        int userId = 11;
        int gameId = 12;
        String action = ActionType.ATTAC.getName();

        User user = new User();
        user.setId(userId);
        user.setCreationDate(LocalDate.now());

        Game game = new Game();
        game.setId(gameId);
        game.setAction(ActionType.ATTAC);
        game.setCreationDate(LocalDate.now());
        game.setUser(user);

        String restApiInformation = "/" + userId + "/" + gameId + "/" + action;

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(gameRepository.save(game)).thenReturn(game);
        Mockito.when(userService.addUser(userId)).thenReturn(true);
        Mockito.when(gameService.addGame(gameId,userId, action)).thenReturn(true);
        Mockito.when(gameService.editGame(gameId,userId, action)).thenReturn(true);


        mockMvc.perform(post("/api" + restApiInformation)).andExpect(status().isOk());
    }
}
