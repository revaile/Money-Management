package main.uangku.services.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.uangku.models.entities.Income;
import main.uangku.repository.ExpanseRepository;
import main.uangku.repository.IncomeRepository;
import main.uangku.repository.TransactionRepository;
import main.uangku.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final IncomeRepository incomeRepository;
    private final ExpanseRepository expanseRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, IncomeRepository incomeRepository, ExpanseRepository expanseRepository) {
        this.transactionRepository = transactionRepository;
        this.incomeRepository = incomeRepository;
        this.expanseRepository = expanseRepository;
    }

    @Override
    public int getNetworth() {
        int totalIncome = 0;
        int totalExpanse = 0;

        // Handle null values
        try {
            totalIncome = incomeRepository.sumValueByCategory("Income");
        } catch (Exception e) {
            System.err.println("Error fetching total income: " + e.getMessage());
        }

        try {
            totalExpanse = expanseRepository.sumExpanse("Expanse");
            
        } catch (Exception e) {
            System.err.println("Error fetching total expanse: " + e.getMessage());
        }


        return totalIncome - totalExpanse;
    }

    @Override
    public long totalTransaction() {
        return transactionRepository.totalTransaction();
    }

    @Override
    public List<Income> getAllTransaction() {
        return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

}
