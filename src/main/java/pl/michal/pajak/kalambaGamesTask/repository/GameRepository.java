package pl.michal.pajak.kalambaGamesTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.pajak.kalambaGamesTask.model.entitis.Game;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `game` WHERE `id` = ?1")
    boolean isGameAlreadyExist(int gameId);

    @Query(nativeQuery = true, value = "SELECT * FROM `game` WHERE `id` = ?1")
    Optional<Game> findGameById(int gameId);

    @Query(nativeQuery = true, value = "SELECT * FROM `game` WHERE `user_id` = ?1")
    Iterable<Game> findAllGameByUserID(int userId);
}
