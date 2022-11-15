package edu.msudenver.character;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;


@Entity
@Table(name = "character")

public class Character {
    @Column(name = "class_type") //class type
    private String classType; //why is class type

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
    private String inventory_id;

    @Column(name = "item_id") //foreign key
    private String itemId;


    @Id
    @Column(name = "character_id")
    private String characterId;

    @Column(name="hp")
    private int hp;

    @Column(name="xp")
    private int xp;

    @Column(name = "current_level") //big int
    private BigInteger currentLevel;
    
    @Column(name = "current_character")
    @NotNull(message = "currentCharacter cannot be null")
    private String currentCharacter; //this had a * I assumed it was going to be our primary char


    public Character() {

    }

    public Character(String name, String classType) {
        this.name = name;
        this.classType = classType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(String equippedItem) {
        this.equippedItem = equippedItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public BigInteger getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(BigInteger currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(String currentCharacter) {
        this.currentCharacter = currentCharacter;
    }
}