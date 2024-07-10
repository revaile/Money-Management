package main.uangku.services;

import java.util.List;
import main.uangku.models.entities.Income;

public interface IncomeServices {
    List<Income> getAllIncomeByDate();

    Income saveIncome(Income income);

    void deteteTransactionById(Long id);

    public int getTotalIncome();

    Income getIncomeById(Long id);
    
    Income updateIncome(Income income);
}
