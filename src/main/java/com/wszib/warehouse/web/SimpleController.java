package com.wszib.warehouse.web;

import com.wszib.warehouse.database.WarehouseDatabase;
import com.wszib.warehouse.model.WarehouseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SimpleController {
    @Autowired
    WarehouseDatabase warehouseDatabase;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }

    @GetMapping("/index")
    public String getItemList(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("items", warehouseDatabase.getAllItems());
        return "index";
    }

    @GetMapping("/additemform")
    public String showAddForm(WarehouseItem item) {
        return "add-item";
    }

    @PostMapping("/additem")
    public String addNewItem(@ModelAttribute("command") WarehouseItem item, BindingResult result, Model model) {
        model.addAttribute("item", new WarehouseItem());
        if (result.hasErrors()) {
            return "add-item";
        }
        warehouseDatabase.addNewItem(item);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String removeItem(@PathVariable("id") int id, Model model) {
        warehouseDatabase.removeItem(id);
        return "redirect:/index";
    }

    @ModelAttribute("item")
    public WarehouseItem defaultInstance() {
        WarehouseItem item = new WarehouseItem();
        //item.setName("First Item");
        return item;
    }


}
