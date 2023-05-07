package com.cbr.datastore;

import com.cbr.models.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataStore {
    @Getter
    @Setter
    private String folder;
    @Getter
    @Setter
    private String mode;
    @Getter
    private DataList<Customer> clients;
    @Getter
    private DataList<Member> members;
    @Getter
    private DataList<VIP> vipMembers;
    @Getter
    private DataList<InventoryProduct> inventory;
    @Getter
    private DataList<FixedInvoice> invoices;
    @Getter
    private DataList<TemporaryInvoice> temporaryInvoices; // for not-fixed transactions
    @Getter
    @Setter
    private List<? extends Serializable> additionalData;
    private DataStorer dataStorer;

    public DataStore(String mode, String folder) {
        if (mode.equals("JSON")) {
            this.dataStorer = new JsonDataStore(folder);
        } else if (mode.equals("XML")) {
            this.dataStorer = new XmlDataStore(folder);
        } else if (mode.equals("OBJ")) {
            this.dataStorer = new ObjDataStore(folder);
        }
        this.mode = mode;
        this.folder = folder;
        this.clients = this.dataStorer.loadClients();
        this.inventory = this.dataStorer.loadInventory();
        this.invoices = this.dataStorer.loadInvoices();
        this.temporaryInvoices = this.dataStorer.loadTemporaryInvoices();
    }

    public Customer getCustomerById(String id) {
        return this.clients.getById(id);
    }

    public InventoryProduct getProductById(String id) {
        return this.inventory.getById(id);
    }

    public <T extends Serializable> List<T> getAdditionalData(String dataName, Class<T> clazz) {
        this.additionalData = dataStorer.loadAdditionalData(dataName, clazz);
        return (List<T>) this.additionalData;
    }

    public <T extends Serializable> void setAdditionalData(List<T> additionalData, String dataName) {
        dataStorer.storeAdditionalData(additionalData, dataName);
    }

    public void addClient(Customer record) {
        this.clients.add(record);
        this.dataStorer.storeClients(this.clients);
    }

    public void updateClient(Customer client, String bill_id, Double deltaPoint) {
        Customer toUpdate = clients.getDataList().stream()
                .filter(c -> c.getId().equals(client.getId()))
                .findFirst()
                .get();

        toUpdate.getInvoiceList().add(bill_id);
        if (toUpdate instanceof Member || toUpdate instanceof VIP) {
            ((Member) toUpdate).addPoint(deltaPoint);
        }

        this.dataStorer.storeClients(this.clients);
    }

    public void deactivateMember(String id) {
        Customer member = this.getCustomerById(id);
        if (member instanceof Member) {
            Member memberObj = (Member) member;
            memberObj.setStatus(false);
            this.dataStorer.storeClients(this.clients);
        }
    }

    public void activateMember(String id) {
        Customer member = this.getCustomerById(id);
        if (member instanceof Member) {
            Member memberObj = (Member) member;
            memberObj.setStatus(true);
            this.dataStorer.storeClients(this.clients);
        }
    }

    public void updateCustomerInfo(Customer updatedCustomer) {
        Optional<Customer> customerOptional = clients.getDataList().stream()
                .filter(c -> c.getId() == updatedCustomer.getId())
                .findFirst();

        // If the customer is found, replace it with the updated customer object
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            int index = clients.getDataList().indexOf(customer);

            if (updatedCustomer instanceof Member) {
                clients.getDataList().set(index, (Member) updatedCustomer);
            } else if (updatedCustomer instanceof VIP) {
                clients.getDataList().set(index, (VIP) updatedCustomer);
            } else {
                clients.getDataList().set(index, updatedCustomer);
            }
        }
        this.dataStorer.storeClients(this.clients);
    }

    public void addProduct(InventoryProduct record) {
        this.inventory.add(record);
        this.dataStorer.storeInventory(this.inventory);
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

    public void addInvoice(FixedInvoice invoice) {
        this.invoices.add(invoice);
        this.dataStorer.storeInvoices(this.invoices);
    }

    public void deleteTemporaryInvoices(TemporaryInvoice invoice) {
        if (this.temporaryInvoices.getDataList().stream()
                .anyMatch(tempInvoice -> tempInvoice.getId().equals(invoice.getId()))) {
            this.temporaryInvoices.getDataList().remove(invoice);
            this.dataStorer.storeTemporaryInvoices(this.temporaryInvoices);
        }
    }

    public void decreaseProductStock(String productId, Integer toDecrease) {
        InventoryProduct product = this.getProductById(productId);
        product.setStock(product.getStock() - toDecrease);
        this.dataStorer.storeInventory(this.inventory);
    }

    public void deactivateProduct(String id) {
        InventoryProduct product = this.getProductById(id);
        product.setStatus(false);
        this.dataStorer.storeInventory(this.inventory);
    }

    public void updateProductInfo(InventoryProduct updatedProduct) {
        InventoryProduct product = this.getProductById(updatedProduct.getId());
        product.setProductName(updatedProduct.getProductName());
        product.setStock(updatedProduct.getStock());
        product.setBuyPrice(updatedProduct.getBuyPrice());
        product.setSellPrice(updatedProduct.getSellPrice());
        product.setImagePath(updatedProduct.getImagePath());
        product.setCategory(updatedProduct.getCategory());
        product.setStatus(updatedProduct.getStatus());
        this.dataStorer.storeInventory(this.inventory);
    }

    public void setInventory(DataList<InventoryProduct> inventory) {
        this.inventory = inventory;
        List<InventoryProduct> newInventory = new ArrayList<InventoryProduct>();
        for (InventoryProduct p : inventory.getDataList()) {
            InventoryProduct newProduct = p.clone();
            newInventory.add(newProduct);
        }
        this.dataStorer.storeInventory(new DataList<>(newInventory));
    }

    public void setCustomerList(DataList<Customer> clients) {
        this.clients = clients;
        this.dataStorer.storeClients(clients);
    }

    public void setTemporaryInvoices(DataList<TemporaryInvoice> temporaryInvoices) {
        this.temporaryInvoices = temporaryInvoices;
        this.dataStorer.storeTemporaryInvoices(temporaryInvoices);
    }

    public void setInvoices(DataList<FixedInvoice> fixedInvoices) {
        this.invoices = fixedInvoices;
        this.dataStorer.storeInvoices(fixedInvoices);
    }

    public List<Member> getMembers() {
        return this.getClients().getDataList().stream()
                .filter(c -> "member".equals(c.getType()))
                .map(c -> (Member) c).collect(Collectors.toList());
    }

    public List<VIP> getVips() {
        return this.getClients().getDataList().stream()
                .filter(c -> "VIP".equals(c.getType()))
                .map(c -> (VIP) c)
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomers() {
        return this.getClients().getDataList().stream()
                .filter(c -> "customer".equals(c.getType()))
                .collect(Collectors.toList());
    }

    // GET MEMBERS AND VIPS THAT ARE ACTIVE
    public List<Member> getMembersVips() {
        return this.getClients().getDataList().stream()
                .filter(c -> ("VIP".equals(c.getType()) || "member".equals(c.getType()))
                        && ((c instanceof Member && ((Member) c).getStatus() == true)
                                || (c instanceof VIP && ((VIP) c).getStatus() == true)))
                .map(c -> (Member) c)
                .collect(Collectors.toList());
    }

    // public void commit(){
    // for (InventoryProduct p : this.inventory.getDataList()){
    // if (p.getSellPrice().getClass().equals(PriceDecorator.class)){
    // p.setSellPrice(((PriceDecorator) p.getSellPrice()).getPrice());
    // }
    // if (p.getBuyPrice().getClass().equals(PriceDecorator.class)){
    // p.setBuyPrice(((PriceDecorator) p.getBuyPrice()).getPrice());
    // }
    // }
    // this.dataStorer.storeInventory(this.inventory);
    // }
}
