package edu.msudenver.inventory;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.profile.Profile;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
@Getter // Lombok annotation to generate getters
@Setter // Lombok annotation to generate setters
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "inventory_id", columnDefinition = "SERIAL") // serial is a postgres thing that auto increments the id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id", insertable = false, updatable = false)
    private Profile profile;

    @Column(name = "profile_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long profileId;

    @Column(name = "catalog_id")
    private Long catalogId;

    @Column(name = "equipped")
    private boolean equipped;

    @Column(name = "quantity")
    private int quantity;

    public Inventory(Long inventoryId, Profile profile, Long profileId, Long catalogId, boolean equipped, int quantity) {
        this.inventoryId = inventoryId;
        this.profile = profile;
        this.profileId = profileId;
        this.catalogId = catalogId;
        this.equipped = equipped;
        this.quantity = quantity;
    }
}