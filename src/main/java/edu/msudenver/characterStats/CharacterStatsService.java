package edu.msudenver.characterStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CharacterStatsService {


        @Autowired
        private CharacterStatsRepository characterStatsRepository;

        @PersistenceContext
        protected EntityManager entityManager;

        public List<CharacterStats> getCountries() {
            return characterStatsRepository.findAll();
        }

        public CharacterStats getCharacterStats(String characterName) {
            try {
                return characterStatsRepository.findById(characterName).get();
            } catch(NoSuchElementException | IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Transactional
        public CharacterStats saveCharacterStats(CharacterStats stats) {
            stats = characterStatsRepository.saveAndFlush(stats);
            entityManager.refresh(stats);
            return stats;
        }

        public boolean deleteStats(String stats) {
            try {
                if(characterStatsRepository.existsById(stats)) {
                    characterStatsRepository.deleteById(stats);
                    return true;
                }
            } catch(IllegalArgumentException e) {}

            return false;
        }
    }


