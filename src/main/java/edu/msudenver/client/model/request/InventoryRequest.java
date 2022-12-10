package edu.msudenver.client.model.request;

import edu.msudenver.profile.Profile;

public class InventoryRequest {


    private Long inventoryId;
    private Profile profile;
    private Long profileId;
    private Long catalogId;
    protected boolean equipped;
    private int quantity;
    private String type;
    private String name;

    // setter methods

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getter methods

    public Long getInventoryId() {
        return inventoryId;
    }

    public Profile getProfile() {
        return profile;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
