package edu.msudenver.characterSheet;

//import edu.msudenver.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//Entity class for the character sheet
@Setter //Lombok annotation to generate setters
@Getter //Lombok annotation to generate getters
@Entity //JPA annotation to make this object an entity
@RequiredArgsConstructor // for the empty constructor
@Table(name = "character_sheet")
public class CharacterSheet {
    // primary key for the character sheet
    @Id
    @Column(name = "character_name")
    @NotNull(message = "you must have a character name")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //@UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = {"personNumber", "isActive"}), //uncommenting this will cause an error but this is how I would implement a unique constraint
    private String characterName;
    // foreign key from the accouct table
    // join annotation
    //@OneToMany() this is not working for some reason
    @JoinColumn(name = "account_id", updatable = false, insertable = false, referencedColumnName = "account_id")
    private Long accountId;
    // foreign key from the inventory table

    //Inventory branch = new Inventory(); //is this how I would call inventory
    @Column(name = "inventory_id")
    //@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true) //I think this is cascading deletions? I don't beleve this is linked to Inventory though. uncommenting this will cause an error
    private Long inventoryId;
    // foreign key from the stats table
    @Column(name = "stats_id")
    private Long statsId;
    // TODO: talk to matt about below itemId inventory table
    @Column(name = "equipped_item")
    private Long equippedItem;
    @Column(name = "class_type")
    private String classType;
    @Column(name = "gender")
    private String gender;
    @Column(name = "origins")
    private String origins;

    // constructor for the character sheet
    public CharacterSheet(@RequestBody String characterName, @RequestBody String gender, @RequestBody String origins,
                          Long accountId, Long inventoryId, Long statsId, @RequestBody String classType) {
        this.gender = gender;
        this.origins = origins;
        this.characterName = characterName;
        this.accountId = accountId;
        this.inventoryId = inventoryId;
        this.statsId = statsId;
        this.equippedItem = null; // this.equippedItem = null; //aiming for this to be the starting weapon?
        this.classType = classType;
    }
}