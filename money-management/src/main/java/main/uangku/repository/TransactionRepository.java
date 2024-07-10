package main.uangku.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.uangku.models.entities.Income;

public interface TransactionRepository extends JpaRepository<Income, Long> {
    @Query("SELECT SUM(i.value) FROM Income i")
    int sumNetworth();

    @Query("SELECT COUNT(i) FROM Income i")
    long totalTransaction();

    
}