package edu.msudenver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        try {
            return userRepository.findById(userId).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public User saveUser(User user) {
        user = userRepository.saveAndFlush(user);
        entityManager.refresh(user);
        return user;
    }

    public boolean deleteUser(Long userId) {
        try {
            if(userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }

        return false;
    }
}
