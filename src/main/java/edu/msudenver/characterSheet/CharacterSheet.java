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
    private String characterName;
    // foreign key from the accouct table
    // join annotation
    //@OneToMany() this is not working for some reason
    @JoinColumn(name = "account_id", updatable = false, insertable = false, referencedColumnName = "account_id")
    private Long accountId;
    // foreign key from the inventory table
    @Column(name = "inventory_id")
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
        this.equippedItem = null; // start with nothing equipped
        this.classType = classType;
    }
}
