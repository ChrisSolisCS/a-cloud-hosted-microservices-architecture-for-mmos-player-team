package edu.msudenver.characterSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class CharacterSheetController {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;
    //get all character sheets
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CharacterSheet>> getAllCharacterSheets() {
        return ResponseEntity.ok(characterSheetRepository.findAll());
    }

    //get character sheet by character name
    @GetMapping(value = "/{characterName}", produces = "application/json")
    public ResponseEntity<CharacterSheet> getCharacterSheetByCharacterName(@PathVariable String characterName) {
        CharacterSheet characterSheet = characterSheetRepository.getCharacterSheetByCharacterName(characterName);
        return new ResponseEntity<>(characterSheet, characterSheet == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    //create a character sheet
    public CharacterSheet createCharacterSheet(CharacterSheet characterSheet) {
        return characterSheetRepository.save(characterSheet);
    }

    //delete a character sheet
    public void deleteCharacterSheet(String characterName) {
        characterSheetRepository.deleteById(characterName);
    }
    //patch a character sheet
    public CharacterSheet patchCharacterSheet(CharacterSheet characterSheet) {
        return characterSheetRepository.save(characterSheet);
    }

}
