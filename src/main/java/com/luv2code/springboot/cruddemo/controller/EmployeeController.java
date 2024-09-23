package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

@GetMapping("/list")
    public String getEmployeeList(Model model){
    List<Employee> theEmployee=employeeService.findAll();
    model.addAttribute("employees",theEmployee);
    return "list-employes";
}

@GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee theEmployee=new Employee();
        model.addAttribute("employee",theEmployee);
        return "employee-form";
}

@GetMapping("/sortByLastName")
public String sortByLastNameForm(Model model){
    List<Employee> theEmployee=employeeService.sortEmployeeLastName();
    model.addAttribute("employees",theEmployee);
    return "list-employes";
}

@PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
}

@GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId, Model model){
        Employee theEmployee=employeeService.findById(theId);
        model.addAttribute("employee",theEmployee);
        return "employee-form";
}
@GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId")int theId){
        employeeService.deleteById(theId);
    return "redirect:/employees/list";
}
}
