package com.cbr.seeder;

import com.cbr.datastore.DataStore;
import com.cbr.models.*;
import com.github.javafaker.Faker;

import java.lang.reflect.Field;
import java.util.*;

public class Seeder {
    public static void main(String[] args){
        Faker faker = new Faker();

        DataStore jsonDataStore = new DataStore("JSON", "assets/data/json");
        DataStore xmlDataStore = new DataStore("XML", "assets/data/xml");
        DataStore objDataStore = new DataStore("OBJ", "assets/data/obj");// TEMPORARY INVOICES //
        List<TemporaryInvoice> temporaryInvoices = new ArrayList<>();
        for (int i = 0; i < 3;i++){
            Integer customerId = faker.number().numberBetween(1,7);
            Integer productId = faker.number().numberBetween(1, 50);
//            TemporaryInvoice temp = new TemporaryInvoice(customerId.toString());
//            temp.addProduct("P-"+productId.toString());
//            temporaryInvoices.add(temp);
        }
        jsonDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
        xmlDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
        objDataStore.setTemporaryInvoices(new DataList<TemporaryInvoice>(temporaryInvoices));
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
    // PRODUCTS //
        List<InventoryProduct> productList = new ArrayList<>();

        List<String> categories = new ArrayList<>(Arrays.asList(
                "Makanan", "Minuman", "Pakaian", "Gadget"
        ));
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            String productName = faker.commerce().productName();
            Double buyPrice = faker.number().randomDouble(2, 1, 100);
            Double sellPrice = buyPrice + faker.number().randomDouble(2, 1, 50);
            String imagePath = "file:assets/images/products/" + (i+1) + ".jpg";
            Integer stock = faker.number().numberBetween(0, 1000);
            String category = categories.get(rand.nextInt(categories.size()));
            Boolean status = faker.bool().bool();

            InventoryProduct product = new InventoryProduct(productName, buyPrice, sellPrice, imagePath, stock, category, status);
            productList.add(product);
        }

        jsonDataStore.setInventory(new DataList<InventoryProduct>(productList));
        xmlDataStore.setInventory(new DataList<InventoryProduct>(productList));
        objDataStore.setInventory(new DataList<InventoryProduct>(productList));

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



        // CUSTOMERS //
        Customer cust1 = new Customer();
        Customer cust2 = new Customer();
        Member mem1 = new Member("C-3", new ArrayList<>(), "rachel", "08123456789");
        Member mem2 = new Member("C-4", new ArrayList<>(), "Livia", "08123456789");
        VIP vip1 = new VIP("C-5", new ArrayList<>(), "Jason", "08123456789", false, 100, 100000.0);
        VIP vip2 = new VIP("C-6", new ArrayList<>(), "Chow", "08123456789", true, 100, 100000.0);
        Member mem3 = new Member("C-7", new ArrayList<>(), "Eugene", "08123456789");

        List<Customer> customerList = new ArrayList<Customer>();

        List<BoughtProduct> boughtProducts = new ArrayList<>();

        boughtProducts.add(new BoughtProduct(productList.get(0), 1));
        boughtProducts.add(new BoughtProduct(productList.get(1), 1));
        boughtProducts.add(new BoughtProduct(productList.get(2), 1));

        FixedInvoice invoice1 = new FixedInvoice(boughtProducts, cust1.getId());
        List<FixedInvoice> invoices = new ArrayList<>();
        invoices.add(invoice1);

        jsonDataStore.setInvoices(new DataList<>(invoices));
        xmlDataStore.setInvoices(new DataList<>(invoices));

        cust1.getInvoiceList().add(invoice1.getId());

        customerList.add(cust1);

        customerList.add(cust2);
        customerList.add(mem1);
        customerList.add(mem2);
        customerList.add(vip1);
        customerList.add(vip2);
        customerList.add(mem3);

        jsonDataStore.setCustomerList(new DataList<Customer>(customerList));
        xmlDataStore.setCustomerList(new DataList<Customer>(customerList));
        objDataStore.setCustomerList(new DataList<Customer>(customerList));

        for (Customer c : jsonDataStore.getClients().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }
        for (Customer c : xmlDataStore.getClients().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }
        for (Customer c : objDataStore.getClients().getDataList()){
            System.out.println(c.getClass().getName());
            System.out.println(c.getId());
        }

        // FIXED INVOICES //

    }
}
