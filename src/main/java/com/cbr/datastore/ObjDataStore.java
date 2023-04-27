package com.cbr.datastore;


import com.cbr.models.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjDataStore implements DataStorer {
    private String folder;

    public ObjDataStore(String folder){
        this.folder = folder;
    }
    public DataList<Customer> loadCustomers(){
        try {
            Path customerPath = Paths.get(this.folder, "customers.txt");
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                this.storeCustomer(new DataList<Customer>());
                return new DataList<Customer>();
            } else {
                FileInputStream fileInputStream
                        = new FileInputStream(customerPath.toFile());
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                DataList<Customer> ret = (DataList<Customer>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the customers file in the folder!");
        }
        return null;
    }
    public DataList<Product> loadInventory(){
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.txt");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                this.storeInventory(new DataList<Product>());
                return new DataList<Product>();
            } else {
                FileInputStream fileInputStream
                        = new FileInputStream(inventoryPath.toFile());
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                DataList<Product> ret = (DataList<Product>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the inventory file in the folder!");
        }
        return null;
    };
    public DataList<FixedInvoice> loadInvoices(){
        try {
            Path invoicesPath = Paths.get(this.folder, "invoices.txt");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeInvoices(new DataList<FixedInvoice>());
                return new DataList<FixedInvoice>();
            } else {
                FileInputStream fileInputStream
                        = new FileInputStream(invoicesPath.toFile());
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                DataList<FixedInvoice> ret = (DataList<FixedInvoice>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the invoices file in the folder!");
        }
        return null;
    }
    public DataList<TemporaryInvoice> loadTemporaryInvoices(){
        try {
            Path temporaryInvoicesPath = Paths.get(this.folder, "temporary-invoices.txt");
            if (!Files.exists(temporaryInvoicesPath)) {
                Files.createFile(temporaryInvoicesPath);
                this.storeTemporaryInvoices(new DataList<TemporaryInvoice>());
                return new DataList<TemporaryInvoice>();
            } else {
                FileInputStream fileInputStream
                        = new FileInputStream(temporaryInvoicesPath.toFile());
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                DataList<TemporaryInvoice> ret = (DataList<TemporaryInvoice>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the temporary-invoices.txt file in the folder!");
        }
        return null;
    }
    public void storeCustomer(DataList<Customer> records) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "customers.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("failed to store Customer");
        }
    };
    public void storeInventory(DataList<Product> records){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "inventory.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("failed to store Customer");
        }
    };
    public void storeInvoices(DataList<FixedInvoice> records){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "invoices.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("failed to store invoices");
        }
    };
    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "temporary-invoices.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("failed to store temporary invoices");
        }
    };
}