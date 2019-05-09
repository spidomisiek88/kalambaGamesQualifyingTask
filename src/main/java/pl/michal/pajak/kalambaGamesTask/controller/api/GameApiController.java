package pl.michal.pajak.kalambaGamesTask.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.pajak.kalambaGamesTask.service.GameService;
import pl.michal.pajak.kalambaGamesTask.service.UserService;

@RestController
@RequestMapping("/api")
public class GameApiController {

    final UserService userService;
    final GameService gameService;

    @Autowired
    public GameApiController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }


    @PostMapping(value = "/{userId}/{gameId}/{action}", produces = "application/json")
    public ResponseEntity setAction(@PathVariable("userId") int userId, @PathVariable("gameId") int gameId, @PathVariable("action") String action) {

        if (userService.addUser(userId)) {
            if (!gameService.addGame(gameId, userId, action)) {
                if (gameService.editGame(gameId, userId, action))
                    return ResponseEntity.ok().build();
                else
                    return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
