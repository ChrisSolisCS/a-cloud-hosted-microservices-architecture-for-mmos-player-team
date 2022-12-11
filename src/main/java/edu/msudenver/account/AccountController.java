package edu.msudenver.account;

import edu.msudenver.profile.Profile;
import edu.msudenver.profile.ProfileService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping(path = "/{accountId}", produces = "application/json")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        return new ResponseEntity<>(account, account == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{accountId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account updatedAccount) {
        Account retrievedAccount = accountService.getAccount(accountId);
        if (retrievedAccount != null) {
            retrievedAccount.setEmail(updatedAccount.getEmail());
            retrievedAccount.setGamerTag(updatedAccount.getGamerTag());
            retrievedAccount.setPassword(updatedAccount.getPassword());
            retrievedAccount.setStatus(updatedAccount.getStatus());
            try {
                return ResponseEntity.ok(accountService.saveAccount(retrievedAccount));
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


