package edu.msudenver.client;

import edu.msudenver.client.model.reponse.InventoryResponse;
import edu.msudenver.client.model.request.InventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.host}")
    private String inventoryServiceHost;

    @Autowired
    private RestTemplate restTemplate;

    public InventoryResponse[] getAllInventory() {
        return restTemplate.getForObject(inventoryServiceHost + "/inventory", InventoryResponse[].class);
    }

    public InventoryResponse createInventory(InventoryRequest request) {
        return restTemplate.postForObject(inventoryServiceHost + "/inventory",
                request, InventoryResponse.class);
    }

    public InventoryResponse[] getInventory(int radius, String type) {
        return restTemplate.getForObject(inventoryServiceHost + "/inventory?radius={1}&type={2}",
                InventoryResponse[].class,
                radius, // this is radius={1}
                type); // this is type={2}
    }
}
