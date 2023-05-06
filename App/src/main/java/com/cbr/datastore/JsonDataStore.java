package com.cbr.datastore;

import com.cbr.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class JsonDataStore implements DataStorer{

    private ObjectMapper mapper;
    private String folder;
    private ObjectWriter writer;

    public JsonDataStore(String folder) {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        prettyPrinter.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        this.mapper.setDefaultPrettyPrinter(prettyPrinter);
        this.writer = mapper.writer(prettyPrinter);
        this.folder = folder;
    }
    public<T extends  Serializable> List<T> loadAdditionalData(String dataName, Class<T> clazz){
        try {
            Path path = Paths.get(this.folder, dataName + ".json");
            if (!Files.exists(path)) {
                Files.createFile(path);
                this.storeAdditionalData(new ArrayList<T>(), dataName);
                return new ArrayList<>();
            } else {
                String json = new String(Files.readAllBytes(path));
                return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
            }
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read data in the folder!");
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read data in the folder!");
        }
        return null;
    };


    public DataList<Customer> loadClients(){
        try {
            Path customerPath = Paths.get(this.folder, "clients.json");
            String cwd = System.getProperty("user.dir");
            System.out.println("Current working directory: " + cwd);
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                this.storeClients(new DataList<Customer>());
                return new DataList<Customer>();
            } else {
                String customerJson = new String(Files.readAllBytes(customerPath));
                return mapper.readValue(customerJson, new TypeReference<DataList<Customer>>() {});
            }
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the clients.json file in the folder!");
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the clients.json file in the folder!");
        }
        return null;
    }

    public DataList<InventoryProduct> loadInventory(){
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.json");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                this.storeInventory(new DataList<InventoryProduct>());
                return new DataList<InventoryProduct>();
            } else {
                String inventoryJson = new String(Files.readAllBytes(inventoryPath));
                return mapper.readValue(inventoryJson, new TypeReference<DataList<InventoryProduct>>() {});
            }
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the inventory.json file in the folder!");
        } catch (IOException e){
            System.out.println("Failed to read the inventory.json file in the folder!");
        }
        return null;
    }
    public DataList<FixedInvoice> loadInvoices(){
        try {
            Path invoicesPath = Paths.get(this.folder, "invoices.json");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeInvoices(new DataList<FixedInvoice>());
                return new DataList<FixedInvoice>();
            } else {
                String invoicesJson = new String(Files.readAllBytes(invoicesPath));
                return mapper.readValue(invoicesJson, new TypeReference<DataList<FixedInvoice>>() {});
            }
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the invoices.json file in the folder!");
        } catch (IOException e){
            System.out.println("Failed to read the invoices.json file in the folder!");
        }
        return null;
    }
    public DataList<TemporaryInvoice> loadTemporaryInvoices(){
        try {
            Path invoicesPath = Paths.get(this.folder, "temporary-invoices.json");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeTemporaryInvoices(new DataList<TemporaryInvoice>());
                return new DataList<TemporaryInvoice>();
            } else {
                String invoicesJson = new String(Files.readAllBytes(invoicesPath));
                return mapper.readValue(invoicesJson, new TypeReference<DataList<TemporaryInvoice>>() {});
            }
        } catch (JsonProcessingException e){
            System.out.println("Failed to read the temporary-invoices.json file in the folder!");
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("Failed to read the temporary-invoices.json file in the folder!");
        }
        return null;
    }

    public void storeClients(DataList<Customer> records){
        try {
            String jsonDataString = this.writer.writeValueAsString(records);
            Files.write(Paths.get(this.folder, "clients.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store customer");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to write to clients.json file in the folder!");
        }
    }

    public void storeInventory(DataList<InventoryProduct> records){
        try {
            String jsonDataString = writer.writeValueAsString(records);
            Files.write(Paths.get(this.folder, "inventory.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store inventory");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to write to inventory.json file in the folder!");
        }
    }

    public void storeInvoices(DataList<FixedInvoice> records){
        try {
            String jsonDataString = writer.writeValueAsString(records);
            System.out.println(jsonDataString);
            Files.write(Paths.get(this.folder, "invoices.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store invoices");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to write to invoices.json file in the folder!");
        }
    }
    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records){
        try {
            String jsonDataString = writer.writeValueAsString(records);
            Files.write(Paths.get(this.folder, "temporary-invoices.json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store temporary invoices");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to write to inventory.json file in the folder!");
        }
    }

    public<T extends Serializable> void storeAdditionalData(List<T> records, String dataName){
        try {
            String jsonDataString = writer.writeValueAsString(records);
            Files.write(Paths.get(this.folder, dataName + ".json"), jsonDataString.getBytes());
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to store ");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to write in the folder!");
        }
    }
}
