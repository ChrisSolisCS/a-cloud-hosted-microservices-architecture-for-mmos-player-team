package edu.msudenver.client;

import edu.msudenver.client.model.reponse.StatsResponse;
import edu.msudenver.client.model.request.StatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatsServiceClient {

    @Value("${stats.service.host}")
    private String statsServiceHost;

    @Autowired
    private RestTemplate restTemplate;

    public StatsResponse[] getAllStats() {
        return restTemplate.getForObject(statsServiceHost + "/stats", StatsResponse[].class);
    }

    public StatsResponse createStats(StatsRequest request) {
        return restTemplate.postForObject(statsServiceHost + "/stats",
                request, StatsResponse.class);
    }

    public StatsResponse[] getStats(int radius, String type) {
        return restTemplate.getForObject(statsServiceHost + "/stats?radius={1}&type={2}",
                StatsResponse[].class,
                radius, // this is radius={1}
                type); // this is type={2}
    }

}
