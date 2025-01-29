package com.example.demo.services;

import com.example.demo.entities.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction saveTransaction(Transaction transaction);
    void deleteTransaction(Long id);
}
