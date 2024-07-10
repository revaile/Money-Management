package main.uangku.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import main.uangku.models.entities.Income;
import main.uangku.repository.IncomeRepository;
import main.uangku.services.IncomeServices;

@Service
public class IncomeServicesImpl implements IncomeServices {

    private final IncomeRepository incomeRepository;

    public IncomeServicesImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Income> getAllIncomeByDate() {
        return incomeRepository.getAllIncomeByDate("Income");
    }

    @Override
    public Income saveIncome(Income income) {
        income.setCategory("Income");
        income.setDate(new Date());
        return incomeRepository.save(income);
    }

    @Override
    public void deteteTransactionById(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public int getTotalIncome() {
        int totalIncome  = 0; 

        try {
            totalIncome = incomeRepository.sumValueByCategory("Income");
        } catch (Exception e) {
            System.err.println("Error fetching total income: " + e.getMessage());
        }

        return totalIncome;
    }

    @Override
    public Income getIncomeById(Long id) {
        return incomeRepository.findById(id).get();        
    }

    @Override
    public Income updateIncome(Income income) {
        return incomeRepository.save(income);
    }
}
