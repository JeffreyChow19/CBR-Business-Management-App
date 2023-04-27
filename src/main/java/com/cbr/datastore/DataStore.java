package com.cbr.datastore;

import com.cbr.models.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class DataStore {
    @Getter protected DataList<Customer> customerList;
    @Getter protected DataList<Product> inventory;
    @Getter protected DataList<FixedInvoice> invoices;
    @Getter protected DataList<TemporaryInvoice> temporaryInvoices; // for not-fixed transactions
    protected DataStorer dataStorer;


    public DataStore(String mode, String folder){
        if (mode.equals("JSON")){
            this.dataStorer = new JsonDataStore(folder);
        }
        else if (mode.equals("XML")){
            this.dataStorer = new XmlDataStore(folder);
        }
        else if (mode.equals("OBJ")){
            this.dataStorer = new ObjDataStore(folder);
        }
        this.customerList = this.dataStorer.loadCustomers();
        this.inventory = this.dataStorer.loadInventory();
        this.invoices = this.dataStorer.loadInvoices();
        this.temporaryInvoices = this.dataStorer.loadTemporaryInvoices();
    }
    public Customer getCustomerById (String id){
        return this.customerList.getById(id);
    }
    public Product getProductById(String id){
        return this.inventory.getById(id);
    }

    public void addCustomer(Customer record){
        this.customerList.add(record);
        this.dataStorer.storeCustomer(this.customerList);
    }

    public void deactivateMember(String id){
        Customer member = this.getCustomerById(id);
        if (member instanceof Member){
            Member memberObj = (Member) member;
            memberObj.setStatus(false);
            this.dataStorer.storeCustomer(this.customerList);
        }
    }
    public void updateCustomerInfo(String id){
        // to implement, maybe needs to separate between Member and VIP
    }

    public void addProduct(Product record){
        this.inventory.add(record);
        this.dataStorer.storeInventory(this.inventory);
    }

    public void deactivateProduct(String id){
        Product product = this.getProductById(id);
        product.setStatus(false);
        this.dataStorer.storeInventory(this.inventory);
    }
    public void updateProductInfo(Integer id){
        // to implement
    }

    public void setInventory(DataList<Product> inventory){
        this.inventory = inventory;
        this.dataStorer.storeInventory(inventory);
    }

    public void setCustomerList(DataList<Customer> customerList){
        this.customerList = customerList;
        this.dataStorer.storeCustomer(customerList);
    }

    public void setTemporaryInvoices(DataList<TemporaryInvoice> temporaryInvoices){
        this.temporaryInvoices = temporaryInvoices;
        this.dataStorer.storeTemporaryInvoices(temporaryInvoices);
    }

    public void setInvoices(DataList<FixedInvoice> fixedInvoices){
        this.invoices = fixedInvoices;
        this.dataStorer.storeInvoices(fixedInvoices);
    }
}
