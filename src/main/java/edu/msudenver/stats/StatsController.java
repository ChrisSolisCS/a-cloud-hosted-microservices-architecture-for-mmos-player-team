package edu.msudenver.stats;

import edu.msudenver.characterSheet.CharacterSheet;
import edu.msudenver.characterSheet.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class StatsController {
    @Autowired
    private StatsRepository StatsRepository;
    //get all character stats
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Stats>> getAllStats() {
        return ResponseEntity.ok(StatsRepository.findAll());
    }

    //get character stats by character name
    @GetMapping(value = "/{characterName}", produces = "application/json")
    public ResponseEntity<Stats> getStatsByCharacterName(@PathVariable String characterName) {
        Stats characterStats = StatsRepository.getStatsByCharacterName(characterName); //this method will probably not work
        return new ResponseEntity<>(characterStats, characterStats == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    //create a character stats
    public Stats createStats(Stats characterStats) {
        return StatsRepository.save(characterStats);
    }

    //delete a character stats
    public void deleteStats(String characterName) {
        StatsRepository.deleteById(characterName);
    }
    //patch a character stats
    public Stats patchCharacterStats(Stats characterStats) {
        return StatsRepository.save(characterStats);
    }

}
