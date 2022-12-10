package edu.msudenver.client.model.reponse;

import edu.msudenver.account.Account;

public class ProfileResponse {

    AccountResponse accountObject;
    private Long profileId;
    private Account account;
    private Long accountId;
    private String profileName;
    private String classType;
    private String gender;

    // setter methods

    public void setAccountObject(AccountResponse accountObject) {
        this.accountObject = accountObject;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // getter methods

    public AccountResponse getAccountObject() {
        return accountObject;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Account getAccount() {
        return account;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getClassType() {
        return classType;
    }

    public String getGender() {
        return gender;
    }

}
