package main.uangku.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.uangku.models.entities.Income;
import main.uangku.services.ExpanseService;
import main.uangku.services.IncomeServices;
import main.uangku.services.TransactionService;

@Controller
public class IndexController {

    private IncomeServices incomeServices;
    private TransactionService transactionService;
    private ExpanseService expanseService;

    public IndexController(IncomeServices incomeServices, TransactionService transactionService, ExpanseService expanseService) {
        this.incomeServices = incomeServices;
        this.transactionService = transactionService;
        this.expanseService = expanseService;
    }

    @GetMapping("/home")
    public String listIncome(Model model) {
        int total = incomeServices.getTotalIncome();
        List<Income> transactions = transactionService.getAllTransaction();
        model.addAttribute("transactions", transactions);
        model.addAttribute("income", transactionService.getAllTransaction());
        model.addAttribute("totalNetworth", transactionService.getNetworth());
        model.addAttribute("totalTransaction", transactionService.totalTransaction());
        model.addAttribute("totalIncome", total);
        model.addAttribute("totalExpanse", expanseService.getTotalExpanse());
        return "index";
    }

    @GetMapping("/income")
    public String createIncomeForm(Model model) {
        Income income = new Income();
        model.addAttribute("income", income);
        model.addAttribute("incomes", incomeServices.getAllIncomeByDate());
        return "income";
    }

    @PostMapping("/income")
    public String addIncome(@ModelAttribute Income income ) {
        incomeServices.saveIncome(income);
        return "redirect:/income";
    }

    @PostMapping("/expanse")
    public String addExpanse(@ModelAttribute Income income ) {
        expanseService.saveExpanse(income);
        return "redirect:/expanse";
    }

    @GetMapping("/expanse")
    public String expanse(Model model) {
        Income income = new Income();
        model.addAttribute("expanse", income);
        model.addAttribute("expanseList", expanseService.getAllExpanseByDate());
        return "expanse";
    }

    @GetMapping("/income/{id}")
	public String deleteIncome(@PathVariable Long id) {
		incomeServices.deteteTransactionById(id);
		return "redirect:/income";
	}

    @GetMapping("/expanse/{id}")
	public String deleteExpanse(@PathVariable Long id) {
		incomeServices.deteteTransactionById(id);
		return "redirect:/expanse";
	}
	
    @PostMapping("/income/{id}")
    public String updateIncome(@PathVariable Long id, @ModelAttribute Income income, Model model){
        Income existingIncome = incomeServices.getIncomeById(id);
        existingIncome.setId(id);
        existingIncome.setValue(income.getValue());
        existingIncome.setDescription(income.getDescription());

        incomeServices.updateIncome(existingIncome);
        return"redirect:/income";
    }

    @GetMapping("/editIncome{id}")
    public String editIncome (@PathVariable Long id, Model model){
        model.addAttribute("income", incomeServices.getIncomeById(id));
        return "incomeEdit";
    }

    @PostMapping("/expense/{id}")
    public String updateExpanse(@PathVariable Long id, @ModelAttribute Income income, Model model){
        Income existingExpanse = incomeServices.getIncomeById(id);
        existingExpanse.setId(id);
        existingExpanse.setValue(income.getValue());
        existingExpanse.setDescription(income.getDescription());

        incomeServices.updateIncome(existingExpanse);
        return"redirect:/expanse";
    }

    @GetMapping("/editExpense{id}")
    public String editExpanse (@PathVariable Long id, Model model){
        model.addAttribute("expanse", incomeServices.getIncomeById(id));
        return "expenseEdit";
    }
}