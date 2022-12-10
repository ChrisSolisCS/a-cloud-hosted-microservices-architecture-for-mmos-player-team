package edu.msudenver.stats;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity
@Table(name = "profiles")

public class Stats {

    @Column(name = "user_id") //do we need this
    private String userId;

    @Column(name = "attack") //do we need any constraints?
    private int attack;

    @Column(name = "zone_id")
    private String zoneId;

    @Column(name = "equipped_item")
    private String equippedItem;

    @Column(name = "name")
    private String name;

    @Column(name = "inventory_id")
    private String inventoryId;

    @Column(name = "item_id") //foreign key
    private String itemId;

    @Id
    @Column(name = "profile_id")
    private String profileId;

    @Column(name="hp")
    private int hp;

    @Column(name="xp")
    private int xp;

    @Column(name = "current_level") //big int
    private BigInteger currentLevel;

    @Column(name = "current_profile")
    @NotNull(message = "current profile cannot be null")
    private String currentProfile; //this had a * I assumed it was going to be our primary char

    // name of the profile
    // class type will determine the base stats of the profile (attack, hp, etc)
    public Stats(String name) {
        this.name = name;
    }
}