package com.cbr.seeder;

import com.cbr.datastore.DataStore;
import com.cbr.datastore.DataStorer;
import com.cbr.datastore.JsonDataStore;
import com.cbr.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class CustomerSeeder {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Customer cust1 = new Customer(1, new ArrayList<>());
        Customer cust2 = new Customer(2, new ArrayList<>());
        Member mem1 = new Member(3, new ArrayList<>(), "rachel", "08123456789");
        Member mem2 = new Member(4, new ArrayList<>(), "Livia", "08123456789");
        VIP vip1 = new VIP(5, new ArrayList<>(), "Jason", "08123456789", false, 100, 100000.0);
        VIP vip2 = new VIP(6, new ArrayList<>(), "Chow", "08123456789", true, 100, 100000.0);
        Member mem3 = new Member(7, new ArrayList<>(), "Eugene", "08123456789");

        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(cust1);
        customerList.add(cust2);
        customerList.add(mem1);
        customerList.add(mem2);
        customerList.add(vip1);
        customerList.add(vip2);
        customerList.add(mem3);

        DataStore myDataStore = new DataStore("JSON", "data");
        CustomerList customers = myDataStore.getCustomerList();
        System.out.println(customers);
        for (Customer c : customers.getDataList()){
            System.out.println(c.getId());
        }
        myDataStore.addCustomer(new Customer());

        customers = myDataStore.getCustomerList();
        System.out.println(customers);
        for (Customer c : customers.getDataList()){
            System.out.println(c.getId());
        }
    }
}
