package edu.msudenver.inventory;


import edu.msudenver.profile.Profile;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryRepository inventoryRepository;

    // Get all items in inventory
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Inventory>> getInventory() {return ResponseEntity.ok(inventoryService.getInventory()); }

    // Get 1 item in inventory
    @GetMapping(path = "/{inventoryId}", produces = "application/json")
    public ResponseEntity<Inventory> getInventoryItem(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventorySlot(inventoryId);
        return new ResponseEntity<>(inventory, inventory == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping(path = "/profile/{profileId}", produces = "application/json")
    public ResponseEntity<Inventory> getProfileInventory(@PathVariable Long profileId) {
        List<Inventory> inventory = new ArrayList<Inventory>();
        inventory = inventoryRepository.getInventoryByProfileId(profileId);
        return new ResponseEntity(inventory, inventory == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

//
//    @GetMapping(path = "/profile/{profileId}", produces = "application/json")
//    public ResponseEntity<Inventory> getOneInventory( @PathVariable Long profileId) {
//        Inventory inventory = inventoryService.getOneInventory(profileId);
//        return new ResponseEntity<>(inventory, inventory == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
//    }

    // Add new item to inventory
    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Inventory> addItem(@RequestBody Inventory inventoryId) {
        try {
            return new ResponseEntity<>(inventoryService.saveInventory(inventoryId), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        try {
            return new ResponseEntity<>(inventoryService.saveInventory(inventory), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{inventoryId}/profile/{profileId}")
    public ResponseEntity<Inventory> assignInventoryToProfile(@PathVariable Long inventoryId, @PathVariable Long profileId) {
        Inventory retrievedInventory = inventoryService.getInventorySlot(inventoryId);
        Profile profile = inventoryService.getProfile(profileId);
        if (retrievedInventory != null && profile != null) {
            retrievedInventory.setProfile(profile);
            try {
                return ResponseEntity.ok(inventoryService.saveInventory(retrievedInventory));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    // New Equip
    @PutMapping (path = "/{inventoryId}/profile/{profileId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Inventory> equipItem(@PathVariable Long inventoryId,
                                               @PathVariable Long profileId,
                                               @RequestBody Inventory updatedInventory) {
        Inventory retrievedInventory = inventoryService.getInventorySlot(inventoryId);
        // Different Equip types: weapon, armor, consumable
        String retrievedInventoryType = inventoryService.getInventorySlot(inventoryId).getType();
        Profile profile = inventoryService.getProfile(profileId);
        Set<Inventory> tempHashSet = profile.getInventoryList();
        // Converting HashSet to ArrayList
        ArrayList<Inventory> retrievedProfileInventory = new ArrayList<>(tempHashSet);

        if (retrievedInventory != null) {
            if(!retrievedInventory.isEquipped()) {
                // Looping through player's inventory
                for (int i = 0; i < retrievedProfileInventory.size(); i++) {
                    // Checks if items in inventory are equipped and are the same type (There is an item of same type equipped case)
                    if (retrievedProfileInventory.get(i).getType() == retrievedInventoryType
                            && retrievedProfileInventory.get(i).isEquipped()) {
                        // Unequips old item and equips new item
                        retrievedProfileInventory.get(i).setEquipped(false);
                        retrievedInventory.setEquipped(true);
                    }
                }
            }
            // Equips item (There is no item of same type equipped case)
            else {
                retrievedInventory.setEquipped(true);
            }
            try {
                return ResponseEntity.ok(inventoryService.saveInventory(retrievedInventory));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 */



    // Old Equip
    @PutMapping (path = "/{inventoryId}/profile/{profileId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Inventory> equipItem(@PathVariable Long inventoryId, @PathVariable Long profileId, @RequestBody Inventory updatedInventory) {
        Inventory retrievedInventory = inventoryService.getInventorySlot(inventoryId);
        Profile profile = inventoryService.getProfile(profileId);
        if (retrievedInventory != null && profile != null) {
            if(retrievedInventory.isEquipped() == false) {
                retrievedInventory.setEquipped(true);
            }
            else {
                retrievedInventory.setEquipped(false);
            }
            try {
                return ResponseEntity.ok(inventoryService.saveInventory(retrievedInventory));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping (path = "/{inventoryId}/profile/{profileId}/edit",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Inventory> changeQuantityType(@PathVariable Long inventoryId, @PathVariable Long profileId, @RequestBody Inventory updatedInventory) {
        Inventory retrievedInventory = inventoryService.getInventorySlot(inventoryId);
        Profile profile = inventoryService.getProfile(profileId);
        if (retrievedInventory != null && profile != null) {
            retrievedInventory.setQuantity(updatedInventory.getQuantity());
            retrievedInventory.setType(updatedInventory.getType());
            retrievedInventory.setName(updatedInventory.getName());
            try {
                return ResponseEntity.ok(inventoryService.saveInventory(retrievedInventory));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete item from inventory
    @DeleteMapping(path = "/{catalogId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long catalogId) {
        return new ResponseEntity<>(inventoryService.deleteInventory(catalogId) ?
                HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}