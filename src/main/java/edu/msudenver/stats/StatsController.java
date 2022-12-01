package edu.msudenver.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class StatsController {
    @Autowired
    private StatsRepository statsRepository;
    //get all character stats
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Stats>> getAllStats() {
        return ResponseEntity.ok(statsRepository.findAll());
    }

    //get character stats by character id
    @GetMapping(value = "/{characterId}", produces = "application/json")
    public ResponseEntity<Stats> getStatsByCharacterId(@PathVariable String characterId) {
        Stats stats = statsRepository.findById(characterId).orElse(null);
        return new ResponseEntity<>(stats, stats == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    //create a character stats
    public Stats createStats(Stats characterStats) {
        return statsRepository.save(characterStats);
    }

    //delete a character stats
    public void deleteStats(String characterName) {
        statsRepository.deleteById(characterName);
    }
    //patch a character stats
    public Stats patchCharacterStats(Stats characterStats) {
        return statsRepository.save(characterStats);
    }

}
