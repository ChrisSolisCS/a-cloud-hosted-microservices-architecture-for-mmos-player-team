package edu.msudenver.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.account.Account;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter // Lombok annotation to generate getters
@Setter // Lombok annotation to generate setters
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity // JPA annotation to make this class an entity
@Table(name = "profile")
public class Profile {
    // JPA annotation to make this field the primary key
    @Id
    @Column(name = "profile_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long profileId;
    @JsonIgnore
    //@ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

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

    public Profile(Long profileId, String profileName, Account account, String classType, String gender, String origins, Boolean isActive) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.account = account;
        this.classType = classType;
        this.gender = gender;
        this.origins = origins;
        this.isActive = isActive;
    }
}
