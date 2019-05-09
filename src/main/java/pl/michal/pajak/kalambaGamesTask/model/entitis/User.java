package pl.michal.pajak.kalambaGamesTask.model.entitis;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    private int id;
    @Column(name = "creation_date")
    private LocalDate creationDate;
}
