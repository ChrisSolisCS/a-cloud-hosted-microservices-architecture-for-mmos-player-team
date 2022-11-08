package edu.msudenver.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")

public class User {
    @Column(name = "gamer_tag")
    @NotNull(message = "gamerTag cannot be null")
    private String gamerTag;

    @Column(name = "email")
    @NotNull(message = "email cannot be null")
    private String email;

    @Id
    @Column(name = "user_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    public User(Long userId,String email, String gamerTag) {
        this.userId = userId;
        this.email = email;
        this.gamerTag = gamerTag;
    }

    public User(String email) {
        this.email = email;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
