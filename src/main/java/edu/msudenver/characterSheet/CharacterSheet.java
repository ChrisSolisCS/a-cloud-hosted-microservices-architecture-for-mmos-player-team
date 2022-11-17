package edu.msudenver.characterSheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.account.Account;
//import edu.msudenver.inventory.Inventory;
//import edu.msudenver.characterStats.CharacterStats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "characterSheets")

public class CharacterSheet {

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account account;


//    @OneToMany
//    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", insertable = false, updatable = false)
//    private Inventory inventory;
//
//    @ManyToOne()
//    @JoinColumn(name = "character_stats_id", referencedColumnName = "character_stats_id", insertable = false, updatable = false)
//    private CharacterStats characterStats;

//    @Column(name = "inventory_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Long inventoryId;
//
//    @Column(name = "character_stats_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Long characterStatsId;


    @Column(name = "account_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long accountId;

    @Id
    @Column(name = "character_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long characterId;

    @Column(name = "character_name")
    @NotNull(message = "you must have a character name")
    private String characterName;

    @Column(name = "class_type")
    private String classType;

//    // TODO: talk to matt about below itemId inventory table
//    @Column(name = "equipped_item")
//    private String equippedItem;

    @Column(name = "gender")
    private String gender;

    @Column(name = "origins")
    private String origins;


    public CharacterSheet(Long characterId, String characterName, Account account, Long accountId, String classType, String gender, String origins) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.account = account;
        this.accountId = accountId;
        this.classType = classType;
        this.gender = gender;
        this.origins = origins;
    }

    public CharacterSheet() {
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }
}
