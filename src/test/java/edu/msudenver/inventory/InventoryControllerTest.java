package edu.msudenver.inventory;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.msudenver.account.Account;
import edu.msudenver.profile.Profile;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith({SpringExtension.class})
@WebMvcTest({InventoryController.class})
public class InventoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository inventoryRepository;


    @BeforeEach
    public void setup() {
    }

    long inventoryId = 1;
    long inventoryId2 = 2;

    long profileId = 1;

    long profileId2 = 2;

    long accountId = 123;

    long catalogId = 1;
    long catalogId2 = 2;

    boolean equipped = true;

    boolean equipped2 = false;

//    int quantity = 1;
//    int quantity2 = 2;

    String type = "weapon1";
    String type2 = "weapon2";
    Inventory testInventory1;
    Inventory testInventory2;

    Profile testProfile1;

    public InventoryControllerTest() {
        this.testInventory1 = new Inventory(this.inventoryId, this.profileId, this.catalogId, true, this.type);
        this.testInventory1.setQuantity(5);
        this.testInventory2 = new Inventory(this.inventoryId2, this.profileId2, this.catalogId2, false, this.type2);
        this.testInventory2.setQuantity(10);
        this.testProfile1 = new Profile(this.profileId, this.accountId, "ProfileName1", null, this.type, "female", "DepressedWarrior", true);
    }

    // Get all items in Inventory
    @Test
    public void getInventoryTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/", new Object[0]).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(this.inventoryService.getInventory()).thenReturn(Arrays.asList(this.testInventory1, this.testInventory2));
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId2)));
    }

    // Get one item in inventory
    @Test
    public void getInventoryItemTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/1", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(this.inventoryService.getInventorySlot(Mockito.eq(this.inventoryId))).thenReturn(this.testInventory1);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));
    }

    // Get profileInventory
    @Test
    public void getProfileInventoryTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/profile/1", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).contentType(MediaType.APPLICATION_JSON);
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(this.testInventory1);
        Mockito.when(this.inventoryRepository.getInventoryByProfileId(Mockito.eq(this.profileId))).thenReturn(inventoryList);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));
    }

    // Test creating a new inventory
    @Test
    public void testCreateInventory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String inventoryJson = mapper.writeValueAsString(this.testInventory1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inventory/", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).content(inventoryJson).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(this.inventoryService.saveInventory((Inventory)Mockito.any())).thenReturn(this.testInventory1);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));
    }

    //Update inventory for equipped item
    @Test
    public void testEquipItem() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String inventoryJson = mapper.writeValueAsString(this.testInventory1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/inventory/1/profile/1", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).content(inventoryJson).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(this.inventoryService.getInventorySlot(Mockito.eq(this.inventoryId))).thenReturn(this.testInventory1);
        Mockito.when(this.inventoryService.getProfile(Mockito.eq(this.profileId))).thenReturn(this.testProfile1);
        Mockito.when(this.inventoryService.saveInventory((Inventory)Mockito.any())).thenReturn(this.testInventory1);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));
        Assertions.assertTrue(response.getContentAsString().contains("\"equipped\":false"));

    }


    //Updating inventory for quantity
    @Test
    public void testChangeQuantity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String inventoryJson = mapper.writeValueAsString(this.testInventory1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/inventory/1/profile/1/edit", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).content(inventoryJson).contentType(MediaType.APPLICATION_JSON);

        Mockito.when(this.inventoryService.getInventorySlot(Mockito.eq(this.inventoryId))).thenReturn(this.testInventory2);

        Mockito.when(this.inventoryService.getProfile(Mockito.eq(this.profileId))).thenReturn(this.testProfile1);

        Mockito.when(this.inventoryService.saveInventory((Inventory)Mockito.any())).thenReturn(this.testInventory1);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(this.inventoryId)));

    }

//
   // Delete inventory
    @Test
    public void testDeleteInventory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(this.testInventory1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/inventory/1/", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON}).contentType(MediaType.APPLICATION_JSON);
        Mockito.when(this.inventoryService.deleteInventory(Mockito.eq(this.inventoryId))).thenReturn(true);
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}




