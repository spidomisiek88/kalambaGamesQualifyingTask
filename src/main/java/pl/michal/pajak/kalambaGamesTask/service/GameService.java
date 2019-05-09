package pl.michal.pajak.kalambaGamesTask.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.pajak.kalambaGamesTask.model.entitis.Game;
import pl.michal.pajak.kalambaGamesTask.model.enums.ActionType;
import pl.michal.pajak.kalambaGamesTask.repository.GameRepository;
import pl.michal.pajak.kalambaGamesTask.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Data
public class GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    UserRepository userRepository;

    public boolean addGame(int gameId, int userId, String action) {
        if (gameRepository.isGameAlreadyExist(gameId))
            return false;

        Game game = new Game();
        game.setId(gameId);
        game.setAction(ActionType.valueOf(action.toUpperCase()));
        userRepository.findUserById(userId).ifPresent(user -> game.setUser(user));
        gameRepository.save(game);

        return true;
    }

    public boolean editGame(int gameId, int userId, String action) {

        Optional<Game> optionalGame = gameRepository.findGameById(gameId);
        if (!optionalGame.isPresent())
            return false;

        optionalGame.get().setAction(ActionType.valueOf(action.toUpperCase()));
        userRepository.findUserById(userId).ifPresent(user -> optionalGame.get().setUser(user));
        optionalGame.get().setCreationDate(LocalDate.now());
        gameRepository.save(optionalGame.get());

        return true;
    }
}
