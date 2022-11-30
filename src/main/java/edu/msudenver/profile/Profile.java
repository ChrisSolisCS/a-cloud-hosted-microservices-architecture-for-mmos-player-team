package edu.msudenver.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.account.Account;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Getter
@Setter
@RequiredArgsConstructor // EMPTY CONSTRUCTOR
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long profileId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
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


    public Profile(Long profileId, String profileName, Account account, String classType, String gender, String origins, Boolean is_active) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.account = account;
        this.classType = classType;
        this.gender = gender;
        this.origins = origins;
        this.isActive = is_active;
    }

//    public Profile() {
//    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Account getAccount() {
        return account;
    }

    public void assignAccount(Account account){
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


}
