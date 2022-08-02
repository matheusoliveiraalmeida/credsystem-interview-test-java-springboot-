package br.com.credsystem.model;

import br.com.credsystem.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double availableLimit;

    @Column
    private Double totalLimit;

    @Column
    private String number;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yy-MM-dd HH:mm:ss", locale = "pt_BR", timezone = "Brazil/East")
    @Column
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Card() {
    }

    public Card(Integer id, Double availableLimit, Double totalLimit, String number, String password, CardStatus cardStatus, Date createdAt, Client client) {
        this.id = id;
        this.availableLimit = availableLimit;
        this.totalLimit = totalLimit;
        this.number = number;
        this.password = password;
        this.cardStatus = cardStatus;
        this.createdAt = createdAt;
        this.client = client;
    }
}
