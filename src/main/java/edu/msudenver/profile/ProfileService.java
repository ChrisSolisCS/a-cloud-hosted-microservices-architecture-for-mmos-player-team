package edu.msudenver.profile;

import edu.msudenver.account.Account;
import edu.msudenver.account.AccountRepository;
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
        profile = profileRepository.saveAndFlush(profile);
        entityManager.refresh(profile);
        return profile;
    }

    public boolean deleteProfile(Long profileId) {
        try {
            if(profileRepository.existsById(profileId)) {
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
