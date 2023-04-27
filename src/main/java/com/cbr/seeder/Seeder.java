package com.cbr.seeder;

import com.cbr.datastore.DataStore;
import com.cbr.models.*;
import com.github.javafaker.Faker;

import java.util.*;

public class Seeder {
    public static void main(String[] args){
        DataStore jsonDataStore = new DataStore("JSON", "data/json");
        DataStore xmlDataStore = new DataStore("XML", "data/xml");
        DataStore objDataStore = new DataStore("OBJ", "data/obj");
        // CUSTOMERS //
        Customer cust1 = new Customer();
        Customer cust2 = new Customer();
        Member mem1 = new Member("C-3", new ArrayList<>(), "rachel", "08123456789");
        Member mem2 = new Member("C-4", new ArrayList<>(), "Livia", "08123456789");
        VIP vip1 = new VIP("C-5", new ArrayList<>(), "Jason", "08123456789", false, 100, 100000.0);
        VIP vip2 = new VIP("C-6", new ArrayList<>(), "Chow", "08123456789", true, 100, 100000.0);
        Member mem3 = new Member("C-7", new ArrayList<>(), "Eugene", "08123456789");

        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(cust1);
        customerList.add(cust2);
        customerList.add(mem1);
        customerList.add(mem2);
        customerList.add(vip1);
        customerList.add(vip2);
        customerList.add(mem3);

//        jsonDataStore.setCustomerList(new DataList<Customer>(customerList));
//        xmlDataStore.setCustomerList(new DataList<Customer>(customerList));
//        objDataStore.setCustomerList(new DataList<Customer>(customerList));

        for (Customer c : jsonDataStore.getCustomerList().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }
        for (Customer c : xmlDataStore.getCustomerList().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }
        for (Customer c : objDataStore.getCustomerList().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }

        // PRODUCTS //
        List<Product> productList = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            String productName = faker.commerce().productName();
            Double buyPrice = faker.number().randomDouble(2, 1, 100);
            Double sellPrice = buyPrice + faker.number().randomDouble(2, 1, 50);
            Integer imagePath = null; // change this to set a random image path if needed
            Integer stock = faker.number().numberBetween(0, 1000);
            String category = faker.commerce().department();
            Boolean status = faker.bool().bool();

            Product product = new Product(productName, buyPrice, sellPrice, imagePath, stock, category, status);
            productList.add(product);
        }

//        jsonDataStore.setInventory(new DataList<Product>(productList));
//        xmlDataStore.setInventory(new DataList<Product>(productList));
//        objDataStore.setInventory(new DataList<Product>(productList));

        for (Product p : jsonDataStore.getInventory().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getProductName());
        }
        for (Product p : xmlDataStore.getInventory().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getProductName());
        }
        for (Product p : objDataStore.getInventory().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getProductName());
        }

        // TEMPORARY INVOICES //
        List<TemporaryInvoice> temporaryInvoices = new ArrayList<>();
        for (int i = 0; i < 3;i++){
            Integer customerId = faker.number().numberBetween(1,7);
            Integer productId = faker.number().numberBetween(1, 50);
            TemporaryInvoice temp = new TemporaryInvoice(customerId.toString());
            temp.addProduct("P-"+productId.toString());
            temporaryInvoices.add(temp);
        }
//        jsonDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
//        xmlDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
//        objDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
        for (TemporaryInvoice p : jsonDataStore.getTemporaryInvoices().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getCreatedAt());
        }
        for (TemporaryInvoice p : xmlDataStore.getTemporaryInvoices().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getCreatedAt().getHour());
        }
        for (TemporaryInvoice p : objDataStore.getTemporaryInvoices().getDataList()){
            System.out.println(p.getClass().getName());
            System.out.println(p.getCreatedAt().getHour());
        }
        // FIXED INVOICES //

    }
}
