package pl.michal.pajak.kalambaGamesTask.model.entitis;

import lombok.Data;
import pl.michal.pajak.kalambaGamesTask.model.enums.ActionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "game")
@Data
public class Game {
    @Id
    private int id;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private ActionType action;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
