package com.cbr.datastore;

import com.cbr.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.*;

public class XmlDataStore implements DataStorer {
    private XmlMapper xmlMapper;
    private String folder;

    public XmlDataStore(String folder) {

        this.xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
//        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
//        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
//        prettyPrinter.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
//        this.xmlMapper.setDefaultPrettyPrinter(prettyPrinter);
        xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        this.folder = folder;
    }

    public DataList<Customer> loadCustomers(){
        try {
            Path customerPath = Paths.get(this.folder, "customers.xml");
            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
                this.storeCustomer(new DataList<Customer>());
                return new DataList<Customer>();
            } else {
                String customerXml = new String(Files.readAllBytes(customerPath));
                return xmlMapper.readValue(customerXml, new TypeReference<DataList<Customer>>() {});
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to read the customers.xml file in the folder!");
        }
        return null;
    }
    public DataList<Product> loadInventory(){
        try {
            Path inventoryPath = Paths.get(this.folder, "inventory.xml");
            if (!Files.exists(inventoryPath)) {
                Files.createFile(inventoryPath);
                this.storeInventory(new DataList<Product>());
                return new DataList<Product>();
            } else {
                String inventoryXml = new String(Files.readAllBytes(inventoryPath));
                return xmlMapper.readValue(inventoryXml, new TypeReference<DataList<Product>>() {});
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to read the inventory.xml file in the folder!");
        }
        return null;
    }
    public DataList<FixedInvoice> loadInvoices(){
        try {
            Path invoicesPath = Paths.get(this.folder, "invoices.xml");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeInvoices(new DataList<FixedInvoice>());
                return new DataList<FixedInvoice>();
            } else {
                String invoicesXml = new String(Files.readAllBytes(invoicesPath));
                return xmlMapper.readValue(invoicesXml, new TypeReference<DataList<FixedInvoice>>() {});
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to read the invoices.xml file in the folder!");
        }
        return null;
    }
    public DataList<TemporaryInvoice> loadTemporaryInvoices(){
        try {
            Path invoicesPath = Paths.get(this.folder, "temporary-invoices.xml");
            if (!Files.exists(invoicesPath)) {
                Files.createFile(invoicesPath);
                this.storeTemporaryInvoices(new DataList<TemporaryInvoice>());
                return new DataList<TemporaryInvoice>();
            } else {
                String inventoryXml = new String(Files.readAllBytes(invoicesPath));
                return xmlMapper.readValue(inventoryXml, new TypeReference<DataList<TemporaryInvoice>>() {});
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to read the temporary-invoices.xml file in the folder!");
        }
        return null;
    }
    public void storeCustomer(DataList<Customer> records){
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "customers.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write to customers.xml file in the folder!");
        }
    }
    public void storeInventory(DataList<Product> records){
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "inventory.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write to inventory.xml file in the folder!");
        }
    }
    public void storeInvoices(DataList<FixedInvoice> records){
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "invoices.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write to invoices.xml file in the folder!");
        }
    }
    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records){
        try {
            String xmlDataString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
            Files.write(Paths.get(this.folder, "temporary-invoices.xml"), xmlDataString.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write to temporary-invoices.xml file in the folder!");
        }
    }
}
