package pl.michal.pajak.kalambaGamesTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.michal.pajak.kalambaGamesTask.model.entitis.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `user` WHERE `id` = ?1")
    boolean isUserAlreadyExist(int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `id` = ?1")
    Optional<User> findUserById(int userId);
}
