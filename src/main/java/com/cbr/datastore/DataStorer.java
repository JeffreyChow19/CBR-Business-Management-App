package com.cbr.datastore;

import com.cbr.models.*;

public interface DataStorer {
    public DataList<Customer> loadClients();
    public DataList<Product> loadInventory();
    public DataList<FixedInvoice> loadInvoices();
    public DataList<TemporaryInvoice> loadTemporaryInvoices();
    public void storeClients(DataList<Customer> records);
    public void storeInventory(DataList<Product> records);
    public void storeInvoices(DataList<FixedInvoice> records);
    public void storeTemporaryInvoices(DataList<TemporaryInvoice> records);
}
