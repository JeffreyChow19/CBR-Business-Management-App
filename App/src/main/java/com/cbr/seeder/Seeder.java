package com.cbr.seeder;

import com.cbr.datastore.DataStore;
import com.cbr.models.*;
import com.cbr.models.Pricing.BasePrice;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.javafaker.Faker;

import java.util.*;

public class Seeder {
    public static void main(String[] args){
        Faker faker = new Faker();

        DataStore jsonDataStore = new DataStore("JSON", "App/assets/data/json");
        DataStore xmlDataStore = new DataStore("XML", "App/assets/data/xml");
        DataStore objDataStore = new DataStore("OBJ", "App/assets/data/obj");// TEMPORARY INVOICES //
        List<TemporaryInvoice> temporaryInvoices = new ArrayList<>();
        for (int i = 0; i < 3;i++){
            Integer customerId = faker.number().numberBetween(3,7);
            Integer productId = faker.number().numberBetween(1, 50);
            TemporaryInvoice temp = new TemporaryInvoice("CS-" + customerId);
            temp.addProduct("IP-"+productId.toString());
            temporaryInvoices.add(temp);
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
            Double buyPrice = faker.number().randomDouble(2, 20000, 5000000);
            Double sellPrice = buyPrice + faker.number().randomDouble(2, 10000, 1000000);
            String imagePath = "file:assets/images/products/" + (i+1) + ".jpg";
            Integer stock = faker.number().numberBetween(0, 1000);
            String category = categories.get(rand.nextInt(categories.size()));
            Boolean status = faker.bool().bool();
            System.out.println(category);
            InventoryProduct product = new InventoryProduct(productName, new BasePrice(buyPrice), new BasePrice(sellPrice), imagePath, stock, category, status);
            System.out.println(product.getCategory());
            productList.add(product);
        }

        jsonDataStore.setInventory(new DataList<InventoryProduct>(productList));
        xmlDataStore.setInventory(new DataList<InventoryProduct>(productList));
        objDataStore.setInventory(new DataList<InventoryProduct>(productList));

//        for (Product p : jsonDataStore.getInventory().getDataList()){
//            System.out.println(p.getClass().getName());
//            System.out.println(p.getProductName());
//        }
//        for (Product p : xmlDataStore.getInventory().getDataList()){
//            System.out.println(p.getClass().getName());
//            System.out.println(p.getProductName());
//        }
//        for (Product p : objDataStore.getInventory().getDataList()){
//            System.out.println(p.getClass().getName());
//            System.out.println(p.getProductName());
//        }



        // CUSTOMERS //
        Customer cust1 = new Customer();
        Customer cust2 = new Customer();
        Member mem1 = new Member("CS-3", new ArrayList<>(), "rachel", "08123456789", new HashMap<>());
        Member mem2 = new Member("CS-4", new ArrayList<>(), "Livia", "08123456789", new HashMap<>());
        VIP vip1 = new VIP("CS-5", new ArrayList<>(), "Jason", "08123456789", false, new BasePrice(100.0), new HashMap<>());
        VIP vip2 = new VIP("CS-6", new ArrayList<>(), "Chow", "08123456789", true, new BasePrice(100.0), new HashMap<>());
        Member mem3 = new Member("CS-7", new ArrayList<>(), "Eugene", "08123456789", new HashMap<>());

        List<Customer> customerList = new ArrayList<Customer>();

        List<BoughtProduct> boughtProducts = new ArrayList<>();

        boughtProducts.add(new BoughtProduct(productList.get(0), 1, new HashMap<>()));
        boughtProducts.add(new BoughtProduct(productList.get(1), 1, new HashMap<>()));
        boughtProducts.add(new BoughtProduct(productList.get(2), 1, new HashMap<>()));

//        Map<String,String> additionalCost = new HashMap<>();
//        FixedInvoice invoice1 = new FixedInvoice(boughtProducts, cust1.getId(), new BasePrice(0.0), new BasePrice(0.0), new BasePrice(0.0), additionalCost, new BasePrice(0.0));
//        List<FixedInvoice> invoices = new ArrayList<>();
//        invoices.add(invoice1);

//        jsonDataStore.setInvoices(new DataList<>(invoices));
//        xmlDataStore.setInvoices(new DataList<>(invoices));
//
//        cust1.getInvoiceList().add(invoice1.getId());

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


    }
}
