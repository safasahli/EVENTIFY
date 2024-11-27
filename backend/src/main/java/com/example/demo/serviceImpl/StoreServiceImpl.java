package com.example.demo.serviceImpl;

import com.example.demo.entities.Store;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.services.StoreService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getStoreById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.orElseThrow(() -> new RuntimeException("Store not found"));
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store updateStore(Long id, Store store) {
        Store existingStore = getStoreById(id);
        existingStore.setName(store.getName());
        existingStore.setDescription(store.getDescription());
        existingStore.setLogoUrl(store.getLogoUrl());
        existingStore.setApproved(store.isApproved());
        existingStore.setCommissionRate(store.getCommissionRate());
        return storeRepository.save(existingStore);
    }

    @Override
    public void deleteStore(Long id) {
        Store store = getStoreById(id);
        storeRepository.delete(store);
    }
}

