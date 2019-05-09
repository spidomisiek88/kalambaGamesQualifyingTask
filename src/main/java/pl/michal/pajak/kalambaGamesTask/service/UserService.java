package pl.michal.pajak.kalambaGamesTask.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.pajak.kalambaGamesTask.model.entitis.User;
import pl.michal.pajak.kalambaGamesTask.repository.UserRepository;

@Service
@Data
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean addUser(int userId) {
        if(userRepository.isUserAlreadyExist(userId))
            return false;

        User user = new User();
        user.setId(userId);

        userRepository.save(user);
        return true;
    }
}
