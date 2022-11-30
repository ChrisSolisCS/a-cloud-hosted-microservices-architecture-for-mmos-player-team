package edu.msudenver.inventory;


import edu.msudenver.account.Account;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    // Get all items in inventory
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Inventory>> getInventory() {return ResponseEntity.ok(inventoryService.getInventory()); }

    // Get 1 item in inventory
    @GetMapping(path = "/{inventoryId}", produces = "application/json")
    public ResponseEntity<Inventory> getInventoryItem(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventorySlot(inventoryId);
        return new ResponseEntity<>(inventory, inventory == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

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
//    // Update item in inventory
//    @PatchMapping(path = "/{inventoryId}",
//            consumes = "application/json",
//            produces = "application/json")
//    public ResponseEntity<Inventory> updateInventory(@PathVariable Long inventoryId, @RequestBody Inventory updatedInventory) {
//        Inventory retrievedInventory = inventoryService.getInventorySlot(inventoryId);
//
//        if (retrievedInventory != null) {
//            try {
//                retrievedInventory.setCatalogId(updatedInventory.getCatalogId());
//                return ResponseEntity.ok(inventoryService.saveInventory(retrievedInventory));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
//            }
//        } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
//    }

    // Delete item from inventory
    @DeleteMapping(path = "/{catalogId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long catalogId) {
        return new ResponseEntity<>(inventoryService.deleteInventory(catalogId) ?
                HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}