package br.com.credsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double transactionValue;

    @Column
    private boolean authorized;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yy-MM-dd", locale = "pt_BR", timezone = "Brazil/East")
    @Column
    private Date createdAt;

    @Column
    private Integer cardId;

    public Transaction() {
    }

    public Transaction(Integer id, Double transactionValue, boolean authorized, Integer cardId, Date createdAt) {
        this.id = id;
        this.transactionValue = transactionValue;
        this.authorized = authorized;
        this.cardId = cardId;
        this.createdAt = createdAt;
    }
}
