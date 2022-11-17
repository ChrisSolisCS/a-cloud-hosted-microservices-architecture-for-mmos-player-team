package edu.msudenver.inventory;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.characterSheet.CharacterSheet;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "inventory_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "character_id", insertable = false, updatable = false)
    private CharacterSheet character;

    @Column(name = "character_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long characterId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "equipped")
    private boolean equipped;

    @Column(name = "quantity")
    private int quantity;

    public Inventory(Long inventoryId, CharacterSheet character, Long characterId, Long catalogId, boolean equipped, int quantity) {
        this.inventoryId = inventoryId;
        this.character = character;
        this.characterId = characterId;
        this.catalogId = catalogId;
        this.equipped = equipped;
        this.quantity = quantity;
    }

    public Inventory(){

    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public CharacterSheet getCharacter() {
        return character;
    }

    public void setCharacter(CharacterSheet character) {
        this.character = character;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}