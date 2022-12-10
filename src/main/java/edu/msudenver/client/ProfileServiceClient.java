package edu.msudenver.client;

import edu.msudenver.client.model.reponse.ProfileResponse;
import edu.msudenver.client.model.request.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProfileServiceClient {

    @Value("${profile.service.host}")
    private String profileServiceHost;

    @Autowired
    private RestTemplate restTemplate;

    public ProfileResponse[] getAllProfiles() {
        return restTemplate.getForObject(profileServiceHost + "/profiles", ProfileResponse[].class);
    }

    public ProfileResponse createProfile(ProfileRequest request) {
        return restTemplate.postForObject(profileServiceHost + "/profile",
                request, ProfileResponse.class);
    }

    public ProfileResponse[] getProfiles(int radius, String type) {
        return restTemplate.getForObject(profileServiceHost + "/profiles?radius={1}&type={2}",
                ProfileResponse[].class,
                radius, // this is radius={1}
                type); // this is type={2}

    }
}
