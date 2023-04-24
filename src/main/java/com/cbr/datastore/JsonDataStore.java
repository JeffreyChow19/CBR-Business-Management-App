package com.cbr.datastore;

import com.cbr.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.*;

@Getter
public class JsonDataStore implements DataStorer{
    private ObjectMapper mapper;
    private String folder;

    public JsonDataStore(String folder) {
        this.mapper = new ObjectMapper();
        this.folder = folder;
    }

    public CustomerList loadCustomers(){
        try {
            Path customerPath = Paths.get(this.folder, "customers.json");
            String cwd = System.getProperty("user.dir");
            System.out.println("Current working directory: " + cwd);
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                return new CustomerList();
            } else {
                String customerJson = new String(Files.readAllBytes(customerPath));
                return mapper.readValue(customerJson, CustomerList.class);
            }
        } catch (JsonProcessingException e){
            System.out.println("Failed to read the customers.json file in the folder!");
        } catch (IOException e){
            System.out.println("Failed to read the customers.json file in the folder!");
        }
        return null;
    }

    public Inventory loadInventory(){
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.json");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                return new Inventory();
            } else {
                String inventoryJson = new String(Files.readAllBytes(inventoryPath));
                return mapper.readValue(inventoryJson, Inventory.class);
            }
        } catch (JsonProcessingException e){
            System.out.println("Failed to read the customers.json file in the folder!");
        } catch (IOException e){
            System.out.println("Failed to read the customers.json file in the folder!");
        }
        return null;
    }

    public void storeCustomer(CustomerList records){
        try {
            String jsonDataString = mapper.writeValueAsString(records);
            Files.write(Paths.get(this.folder, "customers.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store customer");
        }
        catch (IOException e) {
            System.out.println("Failed to write to customers.json file in the folder!");
        }
    }

    public void storeInventory(Inventory records){
        try {
            String jsonDataString = mapper.writeValueAsString(records);
            Files.write(Paths.get(this.folder, "inventory.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store inventory");
        }
        catch (IOException e) {
            System.out.println("Failed to write to inventory.json file in the folder!");
        }
    }

}
