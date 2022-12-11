package edu.msudenver.stats;


import edu.msudenver.profile.Profile;
import edu.msudenver.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    public List<Stats> getAllProfileStats() {
        return statsRepository.findAll();
    }

    public Stats getProfileStats(Long statsId) {
        try {
            return statsRepository.findById(statsId).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Profile getProfileForStats(Long profileId) {
        try {
            return profileRepository.findById(profileId).get();
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

    public boolean deleteProfileStats(Long statsId) {
        try {
            if(statsRepository.existsById(statsId)) {
                statsRepository.deleteById(statsId);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Stats patchProfileStats(Stats stats) { //we need a patch method, I could not test if this works due to naming conventions
        return statsRepository.save(stats);
    }
    // TODO: Adjust stats for equipped item
    // TODO: Add level up method
    // TODO: Adjust stats for selected class


}