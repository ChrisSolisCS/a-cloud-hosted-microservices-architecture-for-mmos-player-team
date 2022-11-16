package edu.msudenver.characterSheet;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//TODO:
public class CharacterSheetService {

    @Autowired
    private CharacterSheetRepository characterSheetRepository;
    // entity manager
    @PersistenceContext
    private EntityManager entityManager;

    // find by characetr name and return character sheet
    public List<CharacterSheet> getCharacterSheetByCharacterName() {
        return characterSheetRepository.findAll();
    }

}
