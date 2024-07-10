package main.uangku.services;

import java.util.List;


import main.uangku.models.entities.Income;

public interface TransactionService {
    public int getNetworth();
    public long totalTransaction();
    List<Income> getAllTransaction();

    
}
