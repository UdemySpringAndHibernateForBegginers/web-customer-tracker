package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        // get customers from dao
        List<Customer> allCustomers = customerService.getAllCustomers();

        // add customers to model
        model.addAttribute("customers", allCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {

        //pobranie customera z bazy danych
        Customer customer = customerService.getCustomer(customerId);

        //przypisanie customera jako modelAttribute celem początkowego wypełnienia formularza w widoku jsp
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {

        // get customers from dao
        List<Customer> searchedCustomers = customerService.searchForCustomers(searchName);

        // add customers to model
        model.addAttribute("customers", searchedCustomers);

        return "list-customers";
    }
}
