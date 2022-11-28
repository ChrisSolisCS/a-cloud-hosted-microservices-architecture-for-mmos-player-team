package edu.msudenver.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.account.Account;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
@Getter
@Setter
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity
@Table(name = "profile")
public class Profile {
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private Account account;

//    @ManyToOne()
//    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", insertable = false, updatable = false)
//    private Inventory inventory;
//
//    @ManyToOne()
//    @JoinColumn(name = "stats_id", referencedColumnName = "stats_id", insertable = false, updatable = false)
//    private profileStats stats;

//    @Column(name = "inventory_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Long inventoryId;
//
//    @Column(name = "stats_id")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Long statsId;


    @Column(name = "account_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long accountId;

    @Id
    @Column(name = "profile_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long profileId;

    @Column(name = "profile_name")
    @NotNull(message = "you must have a profile name")
    private String profileName;

    @Column(name = "class_type")
    private String classType;

    @Column(name = "gender")
    private String gender;

    @Column(name = "origins")
    private String origins;

    @Column(name = "is_active")
    private Boolean isActive;


    public Profile(Long profileId, String profileName, Account account, Long accountId, String classType, String gender, String origins, Boolean is_active) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.account = account;
        this.accountId = accountId;
        this.classType = classType;
        this.gender = gender;
        this.origins = origins;
        this.isActive = is_active;
    }
}
