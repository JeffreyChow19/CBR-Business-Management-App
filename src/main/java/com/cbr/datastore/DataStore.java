package com.cbr.datastore;

import com.cbr.models.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataStore {
    @Getter @Setter
    private String folder;
    @Getter @Setter
    private String mode;
    @Getter private DataList<Customer> clients;
    @Getter private DataList<InventoryProduct> inventory;
    @Getter private DataList<FixedInvoice> invoices;
    @Getter private DataList<TemporaryInvoice> temporaryInvoices; // for not-fixed transactions
    private DataStorer dataStorer;


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
        this.mode = mode;
        this.folder = folder;
        this.clients = this.dataStorer.loadClients();
        this.inventory = this.dataStorer.loadInventory();
        this.invoices = this.dataStorer.loadInvoices();
        this.temporaryInvoices = this.dataStorer.loadTemporaryInvoices();
    }
    public Customer getCustomerById (String id){
        return this.clients.getById(id);
    }
    public InventoryProduct getProductById(String id){
        return this.inventory.getById(id);
    }

    public void addClient(Customer record){
        this.clients.add(record);
        this.dataStorer.storeClients(this.clients);
    }

    public void deactivateMember(String id){
        Customer member = this.getCustomerById(id);
        if (member instanceof Member){
            Member memberObj = (Member) member;
            memberObj.setStatus(false);
            this.dataStorer.storeClients(this.clients);
        }
    }
    public void updateCustomerInfo(String id){
        // to implement, maybe needs to separate between Member and VIP
    }

    public void addProduct(InventoryProduct record){
        this.inventory.add(record);
        this.dataStorer.storeInventory(this.inventory);
    }

    public void deactivateProduct(String id){
        InventoryProduct product = this.getProductById(id);
        product.setStatus(false);
        this.dataStorer.storeInventory(this.inventory);
    }
    public void updateProductInfo(Integer id){
        // to implement
    }

    public void setInventory(DataList<InventoryProduct> inventory){
        this.inventory = inventory;
        this.dataStorer.storeInventory(inventory);
    }

    public void setCustomerList(DataList<Customer> clients){
        this.clients = clients;
        this.dataStorer.storeClients(clients);
    }

    public void setTemporaryInvoices(DataList<TemporaryInvoice> temporaryInvoices){
        this.temporaryInvoices = temporaryInvoices;
        this.dataStorer.storeTemporaryInvoices(temporaryInvoices);
    }

    public void addTemporaryInvoice(TemporaryInvoice invoice) {
        boolean invoiceExists = temporaryInvoices.getDataList().stream()
                .anyMatch(ti -> ti.getId().equals(invoice.getId()));

        if (invoiceExists) {
            // Update the attributes of the existing TemporaryInvoice
            TemporaryInvoice existingInvoice = temporaryInvoices.getDataList().stream()
                    .filter(ti -> ti.getId().equals(invoice.getId()))
                    .findFirst()
                    .get();
            existingInvoice.setProductFrequencies(invoice.getProductFrequencies());
            // Update any other attributes here
        } else {
            // Add the new TemporaryInvoice
            this.temporaryInvoices.add(invoice);
        }
        this.dataStorer.storeTemporaryInvoices(this.temporaryInvoices);
    }

    public void setInvoices(DataList<FixedInvoice> fixedInvoices){
        this.invoices = fixedInvoices;
        this.dataStorer.storeInvoices(fixedInvoices);
    }

    public List<Member> getMembers(){
        return this.getClients().getDataList().stream()
                .filter(c -> "member".equals(c.getType()))
                .map(c -> (Member) c).collect(Collectors.toList());
    }

    public List<VIP> getVips(){
        return this.getClients().getDataList().stream()
                .filter(c -> "VIP".equals(c.getType()))
                .map(c -> (VIP) c)
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomers(){
        return this.getClients().getDataList().stream()
                .filter(c -> "customer".equals(c.getType()))
                .collect(Collectors.toList());
    }

    // GET MEMBERS AND VIPS THAT ARE ACTIVE
    public List<Member> getMembersVips() {
        return this.getClients().getDataList().stream()
                .filter(c -> ("VIP".equals(c.getType()) || "member".equals(c.getType())) && ((c instanceof Member && ((Member) c).getStatus() == true) || (c instanceof VIP && ((VIP) c).getStatus() == true)))
                .map(c -> (Member) c)
                .collect(Collectors.toList());
    }
}
