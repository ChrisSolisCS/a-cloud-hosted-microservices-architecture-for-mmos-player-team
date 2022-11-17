package edu.msudenver.characterSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CharacterSheetService {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    public List<CharacterSheet> getCharacterSheets() {
        return characterSheetRepository.findAll();
    }

    public CharacterSheet getCharacterSheet(Long characterId) {
        try {
            return characterSheetRepository.findById(characterId).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public CharacterSheet saveCharacterSheet(CharacterSheet characterSheet) {
        characterSheet = characterSheetRepository.saveAndFlush(characterSheet);
        entityManager.refresh(characterSheet);
        return characterSheet;
    }

    public boolean deleteCharacterSheet(Long characterId) {
        try {
            if(characterSheetRepository.existsById(characterId)) {
                characterSheetRepository.deleteById(characterId);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }

        return false;
    }
}
