package edu.msudenver.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
@Getter
@Setter
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity
@Table(name = "accounts")

public class Account {
    @Id
    @Column(name = "account_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long accountId;

    @Column(name = "email")
    @NotNull(message = "email cannot be null")
    private String email;

    @Column(name = "gamer_tag")
    @NotNull(message = "gamerTag cannot be null")
    private String gamerTag;

    @Column(name = "isOnline")
    private Boolean isOnline;


    public Account(Long accountId, String email, String gamerTag, Boolean isOnline) {
        this.accountId = accountId;
        this.email = email;
        this.gamerTag = gamerTag;
        this.isOnline = isOnline;
    }
}
