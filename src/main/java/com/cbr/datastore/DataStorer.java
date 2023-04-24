package com.cbr.datastore;

import com.cbr.models.*;

public interface DataStorer {
    public void storeCustomer(CustomerList records);
    public CustomerList loadCustomers();
    public Inventory loadInventory();
    public void storeInventory(Inventory records);
}
