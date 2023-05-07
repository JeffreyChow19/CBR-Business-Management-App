package com.cbr.datastore;

import com.cbr.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ObjDataStore implements DataStorer {
    private String folder;

    public ObjDataStore(String folder) {
        this.folder = folder;
    }

    public <T extends Serializable> List<T> loadAdditionalData(String dataName, Class<T> clazz) {
        try {
            Path path = Paths.get(this.folder, dataName + ".txt");
            if (!Files.exists(path)) {
                Files.createFile(path);
                this.storeAdditionalData(new ArrayList<T>(), dataName);
                return new ArrayList<>();
            } else {
                FileInputStream fileInputStream = new FileInputStream(path.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                List<T> ret = (List<T>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (JsonProcessingException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    };

    public DataList<Customer> loadClients() {
        try {
            Path customerPath = Paths.get(this.folder, "clients.txt");
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                this.storeClients(new DataList<Customer>());
                return new DataList<Customer>();
            } else {
                FileInputStream fileInputStream = new FileInputStream(customerPath.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                DataList<Customer> ret = (DataList<Customer>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read the clients file in the folder!");
        }
        return null;
    }

    public DataList<InventoryProduct> loadInventory() {
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.txt");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                this.storeInventory(new DataList<InventoryProduct>());
                return new DataList<InventoryProduct>();
            } else {
                FileInputStream fileInputStream = new FileInputStream(inventoryPath.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                DataList<InventoryProduct> ret = (DataList<InventoryProduct>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read the inventory file in the folder!");
        }
        return null;
    };

    public DataList<FixedInvoice> loadInvoices() {
        try {
            Path invoicesPath = Paths.get(this.folder, "invoices.txt");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeInvoices(new DataList<FixedInvoice>());
                return new DataList<FixedInvoice>();
            } else {
                FileInputStream fileInputStream = new FileInputStream(invoicesPath.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                DataList<FixedInvoice> ret = (DataList<FixedInvoice>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read the invoices file in the folder!");
        }
        return null;
    }

    public DataList<TemporaryInvoice> loadTemporaryInvoices() {
        try {
            Path temporaryInvoicesPath = Paths.get(this.folder, "temporary-invoices.txt");
            if (!Files.exists(temporaryInvoicesPath)) {
                Files.createFile(temporaryInvoicesPath);
                this.storeTemporaryInvoices(new DataList<TemporaryInvoice>());
                return new DataList<TemporaryInvoice>();
            } else {
                FileInputStream fileInputStream = new FileInputStream(temporaryInvoicesPath.toFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                DataList<TemporaryInvoice> ret = (DataList<TemporaryInvoice>) objectInputStream.readObject();
                objectInputStream.close();
                return ret;
            }
        } catch (IOException | ClassNotFoundException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read the temporary-invoices.txt file in the
            // folder!");
        }
        return null;
    }

    public void storeClients(DataList<Customer> records) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "clients.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            // system.out.println("failed to store Customer");
        }
    };

    public void storeInventory(DataList<InventoryProduct> records) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "inventory.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            // system.out.println("failed to store Customer");
        }
    };

    public void storeInvoices(DataList<FixedInvoice> records) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(this.folder, "invoices.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            // system.out.println("failed to store invoices");
        }
    };

    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    Paths.get(this.folder, "temporary-invoices.txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            // system.out.println("failed to store temporary invoices");
        }
    };

    public <T extends Serializable> void storeAdditionalData(List<T> records, String dataName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    Paths.get(this.folder, dataName + ".txt").toFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            // system.out.println("failed to store temporary invoices");
        }
    }
}
