package com.example.demo.services;

import com.example.demo.entities.Store;
import java.util.List;

public interface StoreService {
    Store createStore(Store store);
    Store getStoreById(Long id);
    List<Store> getAllStores();
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);
}
