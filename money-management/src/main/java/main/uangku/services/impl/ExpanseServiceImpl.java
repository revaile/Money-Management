package main.uangku.services.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import main.uangku.models.entities.Income;
import main.uangku.repository.ExpanseRepository;
import main.uangku.repository.TransactionRepository;
import main.uangku.services.ExpanseService;

@Service
public class ExpanseServiceImpl implements ExpanseService {

    private final ExpanseRepository expanseRepository;
    private final TransactionRepository transactionRepository;

    public ExpanseServiceImpl(ExpanseRepository expanseRepository, TransactionRepository transactionRepository) {
        this.expanseRepository = expanseRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Income> getAllExpanseByDate() {
        return expanseRepository.getAllExpanseByDate("Expanse");
    }

    @Override
    @Transactional
    public String saveExpanse(Income income) {
        int networth = transactionRepository.sumNetworth();

        if (income.getValue() > networth) {
            return "Expense value cannot be greater than net worth";
        } 
            income.setCategory("Expanse");
            income.setDate(new Date());
            expanseRepository.save(income);
            return "Success";
        
    }

    @Override
    public int getTotalExpanse() {
        int totalExpanse = 0;

        try {
            totalExpanse = expanseRepository.sumExpanse("Expanse");
        } catch (Exception e) {
            System.err.println("Error fetching total expanse: " + e.getMessage());
        }

        return totalExpanse;
    }

    @Override
    public Income getExpenseById(Long id) {
        return expanseRepository.findById(id).get();
    }

    @Override
    public Income updateExpense(Income income) {
        return expanseRepository.save(income);
    }

}
