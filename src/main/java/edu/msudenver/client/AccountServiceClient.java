package edu.msudenver.client;

import edu.msudenver.client.model.reponse.AccountResponse;
import edu.msudenver.client.model.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountServiceClient {

    @Value("{account.service.host}")
    private String accountServiceHost;

    @Autowired
    private RestTemplate restTemplate;

    public AccountResponse[] getAllAccounts() {
        return restTemplate.getForObject(accountServiceHost + "/accounts", AccountResponse[].class);
    }

    public AccountResponse createAccount(AccountRequest request) {
        return restTemplate.postForObject(accountServiceHost + "/accounts",
                request, AccountResponse.class);
    }

    public AccountResponse[] getAccounts(int radius, String type) {
        return restTemplate.getForObject(accountServiceHost + "/accounts?radius={1}&type={2}",
                AccountResponse[].class,
                radius, // this is radius={1}
                type); // this is type={2}
    }
}
