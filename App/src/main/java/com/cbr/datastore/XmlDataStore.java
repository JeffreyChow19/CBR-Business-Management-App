package com.cbr.datastore;

import com.cbr.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class XmlDataStore implements DataStorer {
    private XmlMapper xmlMapper;
    private String folder;

    public XmlDataStore(String folder) {

        this.xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        // DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        // prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        // prettyPrinter.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        // this.xmlMapper.setDefaultPrettyPrinter(prettyPrinter);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        this.folder = folder;
    }

    public <T extends Serializable> List<T> loadAdditionalData(String dataName, Class<T> clazz) {
        try {
            Path path = Paths.get(this.folder, dataName + ".xml");
            if (!Files.exists(path)) {
                Files.createFile(path);
                this.storeAdditionalData(new ArrayList<T>(), dataName);
                return new ArrayList<T>();
            } else {
                String xml = new String(Files.readAllBytes(path));
                return xmlMapper.readValue(xml,
                        TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
            }
        } catch (JsonProcessingException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read data in the folder!");
        } catch (IOException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read data in the folder!");
        }
        return null;
    };

    public DataList<Customer> loadClients() {
        try {
            Path customerPath = Paths.get(this.folder, "clients.xml");
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                this.storeClients(new DataList<Customer>());
                return new DataList<Customer>();
            } else {
                String customerXml = new String(Files.readAllBytes(customerPath));
                return xmlMapper.readValue(customerXml, new TypeReference<DataList<Customer>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            // system.out.println("Failed to read the clients.xml file in the folder!");
        }
        return null;
    }

    public DataList<InventoryProduct> loadInventory() {
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.xml");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                this.storeInventory(new DataList<InventoryProduct>());
                return new DataList<InventoryProduct>();
            } else {
                String inventoryXml = new String(Files.readAllBytes(inventoryPath));
                return xmlMapper.readValue(inventoryXml, new TypeReference<DataList<InventoryProduct>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            // system.out.println("Failed to read the inventory.xml file in the folder!");
        }
        return null;
    }

    public DataList<FixedInvoice> loadInvoices() {
        try {
            Path invoicesPath = Paths.get(this.folder, "invoices.xml");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeInvoices(new DataList<FixedInvoice>());
                return new DataList<FixedInvoice>();
            } else {
                String invoicesXml = new String(Files.readAllBytes(invoicesPath));
                return xmlMapper.readValue(invoicesXml, new TypeReference<DataList<FixedInvoice>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            // system.out.println("Failed to read the invoices.xml file in the folder!");
        }
        return null;
    }

    public DataList<TemporaryInvoice> loadTemporaryInvoices() {
        try {
            Path invoicesPath = Paths.get(this.folder, "temporary-invoices.xml");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeTemporaryInvoices(new DataList<TemporaryInvoice>());
                return new DataList<TemporaryInvoice>();
            } else {
                String inventoryXml = new String(Files.readAllBytes(invoicesPath));
                return xmlMapper.readValue(inventoryXml, new TypeReference<DataList<TemporaryInvoice>>() {
                });
            }
        } catch (IOException e) {
            // system.out.println(e.getMessage());
            // system.out.println("Failed to read the temporary-invoices.xml file in the
            // folder!");
        }
        return null;
    }

    public void storeClients(DataList<Customer> records) {
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "clients.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            // system.out.println("Failed to write to clients.xml file in the folder!");
        }
    }

    public void storeInventory(DataList<InventoryProduct> records) {
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "inventory.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            // system.out.println("Failed to write to inventory.xml file in the folder!");
        }
    }

    public void storeInvoices(DataList<FixedInvoice> records) {
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "invoices.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            // system.out.println("Failed to write to invoices.xml file in the folder!");
        }
    }

    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records) {
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "temporary-invoices.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            // system.out.println("Failed to write to temporary-invoices.xml file in the
            // folder!");
        }
    }

    public <T extends Serializable> void storeAdditionalData(List<T> records, String dataName) {
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, dataName + ".xml"), xmlDataString.getBytes());
        } catch (JsonProcessingException e) {
            // system.out.println("Failed to store ");
            // system.out.println(e.getMessage());
        } catch (IOException e) {
            // system.out.println("Failed to write in the folder!");
        }
    }
}
