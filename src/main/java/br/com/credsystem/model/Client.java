package br.com.credsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String documentNumber;

    @Column
    private Double salary;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yy-MM-dd HH:mm:ss", locale = "pt_BR", timezone = "Brazil/East")
    @Column
    private Date createdAt;

    @OneToMany(mappedBy = "client", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<Card> cards;

    public Client() {
    }

    public Client(Integer id, String documentNumber, Double salary, Date createdAt, List<Card> cards) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.salary = salary;
        this.createdAt = createdAt;
        this.cards = cards;
    }

}
