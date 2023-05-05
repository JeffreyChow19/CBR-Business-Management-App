package com.cbr.datastore;

import com.cbr.models.*;

import java.io.Serializable;
import java.util.List;

public interface DataStorer {
    public DataList<Customer> loadClients();
    public DataList<InventoryProduct> loadInventory();
    public DataList<FixedInvoice> loadInvoices();
    public DataList<TemporaryInvoice> loadTemporaryInvoices();

    public<T extends Serializable> List<T> loadAdditionalData(String dataName, Class<T> clazz);
    public void storeClients(DataList<Customer> records);
    public void storeInventory(DataList<InventoryProduct> records);
    public void storeInvoices(DataList<FixedInvoice> records);
    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records);

    public<T extends Serializable> void storeAdditionalData(List<T> records, String dataName);
}
