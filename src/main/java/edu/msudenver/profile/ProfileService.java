package edu.msudenver.profile;

import edu.msudenver.account.Account;
import edu.msudenver.account.AccountRepository;
import edu.msudenver.stats.Stats;
import edu.msudenver.stats.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service // business logic
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatsRepository statsRepository;
    @PersistenceContext
    protected EntityManager entityManager;
    private Account account;

    public List<Profile> getProfiles() {

        return profileRepository.findAll();
    }

    public Profile getProfile(Long profileId) {
        try {
            return profileRepository.findById(profileId).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Profile saveProfile(Profile profile) {
        String classType = profile.getClassType().toLowerCase();
        profile = profileRepository.saveAndFlush(profile);
        entityManager.refresh(profile);
        Long afterPId = profile.getProfileId();
        Stats retrievedStats = new Stats();
        retrievedStats.setProfileId(afterPId);
        retrievedStats.setXp(0);

        try{
            if (classType.matches("warrior")){
                retrievedStats.setAttack(6);
                retrievedStats.setHp(10);
                retrievedStats.setDefense(4);
                statsRepository.saveAndFlush(retrievedStats);
                entityManager.refresh(retrievedStats);

            } else if (classType.matches("tank")){
                retrievedStats.setAttack(5);
                retrievedStats.setHp(10);
                retrievedStats.setDefense(8);
                statsRepository.saveAndFlush(retrievedStats);
                entityManager.refresh(retrievedStats);

            } else if (classType.matches("paladin")){
                retrievedStats.setAttack(4);
                retrievedStats.setHp(14);
                retrievedStats.setDefense(4);
                statsRepository.saveAndFlush(retrievedStats);
                entityManager.refresh(retrievedStats);

            }else {
                System.out.println("Valid class types are warrior, paladin or tank. Choose wisely.");
                throw new java.lang.Error(classType + " is not accepted");
            }
            return profile;
        }catch (Exception e){
            System.out.println("Catch exception");
            e.printStackTrace();
        }
        return profile;
    }

    public boolean deleteProfile(Long profileId) {
        try {
            if(profileRepository.existsById(profileId)) {
                Stats retrievedStats = statsRepository.getStatByProfileId(profileId);
                statsRepository.deleteById(retrievedStats.getStatsId());
                profileRepository.deleteById(profileId);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountP(Long accountId) {
        try {
            return accountRepository.findById(accountId).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void assignAccount(Account account){
        this.account = account;
    }
}
