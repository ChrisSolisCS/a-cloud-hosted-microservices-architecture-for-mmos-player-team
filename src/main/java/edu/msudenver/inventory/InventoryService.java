package edu.msudenver.inventory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InventoryService {

        static final int MAX_INVENTORY_SIZE = 20;

        @Autowired
        private InventoryRepository inventoryRepository;

        @PersistenceContext
        protected EntityManager entityManager;

        public List<Inventory> getInventory() { return inventoryRepository.findAll();}

        public Inventory getInventorySlot(Long inventoryId) {
                try {
                        return inventoryRepository.findById(inventoryId).get();
                } catch(NoSuchElementException | IllegalArgumentException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public Inventory saveItem(Long catalogId, Long inventoryId) {
                try {
                        Inventory in = inventoryRepository.findById(inventoryId).get();
                        if (in.getCatalogId() == null){
                                in.setCatalogId(catalogId);
                        }
                        return inventoryRepository.findById(inventoryId).get();
                } catch(NoSuchElementException | IllegalArgumentException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        @Transactional
        public Inventory saveInventory(Inventory inventory) {
                inventory = inventoryRepository.saveAndFlush(inventory);
                entityManager.refresh(inventory);
                return inventory;
        }

        public boolean deleteInventory(Long catalogId) {
                try {
                        if(inventoryRepository.existsById(catalogId) ){
                                inventoryRepository.deleteById(catalogId);
                                return true;
                        }
                } catch(IllegalArgumentException e) {
                        e.printStackTrace();
                }
                return false;
        }
}