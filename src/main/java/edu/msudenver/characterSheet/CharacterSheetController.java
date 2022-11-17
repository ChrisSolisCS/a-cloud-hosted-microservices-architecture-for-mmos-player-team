package edu.msudenver.characterSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/characterSheets")
public class CharacterSheetController {
    @Autowired
    private CharacterSheetService characterSheetService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CharacterSheet>> getCharacterSheets() {
        return ResponseEntity.ok(characterSheetService.getCharacterSheets());
    }

    @GetMapping(path = "/{characterId}", produces = "application/json")
    public ResponseEntity<CharacterSheet> getCharacterSheet(@PathVariable Long characterId) {
        CharacterSheet characterSheet = characterSheetService.getCharacterSheet(characterId);
        return new ResponseEntity<>(characterSheet, characterSheet == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CharacterSheet> createCharacterSheet(@RequestBody CharacterSheet characterSheet) {
        try {
            return new ResponseEntity<>(characterSheetService.saveCharacterSheet(characterSheet), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{characterId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CharacterSheet> updateCharacterSheet(@PathVariable Long characterId, @RequestBody CharacterSheet updatedCharacterSheet) {
        CharacterSheet retrievedCharacterSheet = characterSheetService.getCharacterSheet(characterId);
        if (retrievedCharacterSheet != null) {
            retrievedCharacterSheet.setCharacterName(updatedCharacterSheet.getCharacterName());
            retrievedCharacterSheet.setGender(updatedCharacterSheet.getGender());
            retrievedCharacterSheet.setOrigins(updatedCharacterSheet.getOrigins());
            try {
                return ResponseEntity.ok(characterSheetService.saveCharacterSheet(retrievedCharacterSheet));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{characterId}")
    public ResponseEntity<Void> deleteCharacterSheet(@PathVariable Long characterId) {
        return new ResponseEntity<>(characterSheetService.deleteCharacterSheet(characterId) ?
                HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}