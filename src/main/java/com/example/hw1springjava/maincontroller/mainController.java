package com.example.hw1springjava.maincontroller;

import com.example.hw1springjava.DAO.ProductDao;
import com.example.hw1springjava.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class mainController {
    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @PostMapping("/save")
    public String saveProduct(
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            Model model) {
        Product product = new Product(type, name, price);
        productDao.save(product);
        model.addAttribute("product",product);
        return "reg";
    }
    @GetMapping("/allProducts")
    public String allProducts(Model model) {
        List<Product> all = productDao.findAll();
        model.addAttribute("all",all);
        return "index";
    }

    @GetMapping("/products1000")
    public String allProducts1000(Model model) {
        List<Product> all = productDao.findAll();
        Stream<Product> stream = all.stream();
        List<Product> collect = stream.filter(product -> product.getPrice() > 1000).collect(Collectors.toList());
        model.addAttribute("all",collect);
        return "index";
    }
    @GetMapping("/products2000")
    public String allProducts2000(Model model) {
        List<Product> all = productDao.findAll();
        Stream<Product> stream = all.stream();
        List<Product> collect = stream.filter(product -> product.getPrice() > 2000).collect(Collectors.toList());
        model.addAttribute("all",collect);
        return "index";
    }
    @GetMapping("/last10")
    public String last10(Model model) {
        List<Product> all = productDao.findAll();
        List<Product> collect = all.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).collect(Collectors.toList());
        List<Product> finish = new ArrayList<>();
        for (int i = 0 ; i<10; i++){
            finish.add(collect.get(i));
        model.addAttribute("all",finish);}
        return "index";
    }


    @GetMapping("/allProductsSort")
    public String allProductsSort(Model model) {
        List<Product> all = productDao.findAll();
        Stream<Product> stream = all.stream();
        List<Product> finish = stream.sorted((o1, o2) -> o1.getType().compareTo(o2.getType())).collect(Collectors.toList());
        model.addAttribute("all",finish);
        return "index";
    }}

