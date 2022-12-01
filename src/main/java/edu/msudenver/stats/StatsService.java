package edu.msudenver.stats;


import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class StatsService {

        @Autowired
        private StatsRepository statsRepository;

        @PersistenceContext
        protected EntityManager entityManager;

        public List<Stats> getAllStats() {
            return statsRepository.findAll();
        }

        public Stats getProfileStats(String profileName) {
            try {
                return statsRepository.findById(profileName).get();
            } catch(NoSuchElementException | IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Transactional

        public Stats saveProfileStats(Stats stats) {
            stats = statsRepository.saveAndFlush(stats);
            entityManager.refresh(stats);
            return stats;
        }

        public boolean deleteStats(String stats) {
            try {
                if(statsRepository.existsById(stats)) {
                    statsRepository.deleteById(stats);
                    return true;
                }
            } catch(IllegalArgumentException ignored) {
                // ignored
            }
            return false;
        }
        
      //public Stats patchStats(Stats Stats) { //we need a patch method, I could not test if this works due to naming conventions
        //return StatsRepository.save(characterStats);
    //}

    }


