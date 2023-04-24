package com.cbr.datastore;

import com.cbr.models.*;
import lombok.Getter;

import java.io.IOException;

@Getter
public class DataStore {
    protected CustomerList customerList;
    protected Inventory inventory;

    protected DataStorer dataStorer;

    public DataStore(String mode, String folder) throws IOException {
        if (mode.equals("JSON")){
            this.dataStorer = new JsonDataStore(folder);
        }
        this.customerList = this.dataStorer.loadCustomers();
        this.inventory = this.dataStorer.loadInventory();
    }
    public Customer getCustomerById (Integer id){
        return this.customerList.getById(id);
    }
    public Product getProductById(Integer id){
        return this.inventory.getById(id);
    }

    public void addCustomer(Customer record){
        this.customerList.add(record);
        this.dataStorer.storeCustomer(this.customerList);
    }

    public void deactivateMember(Integer id){
        Customer member = this.getCustomerById(id);
        if (member instanceof Member){
            Member memberObj = (Member) member;
            memberObj.setStatus(false);
            this.dataStorer.storeCustomer(this.customerList);
        }
    }
    public void updateCustomerInfo(Integer id){
        // to implement, maybe needs to separate between Member and VIP
    }

    public void addProduct(Product record){
        this.inventory.add(record);
        this.dataStorer.storeInventory(this.inventory);
    }

    public void deactivateProduct(Integer id){
        Product product = this.getProductById(id);
        product.setStatus(false);
        this.dataStorer.storeInventory(this.inventory);
    }
    public void updateProductInfo(Integer id){
        // to implement
    }
}
