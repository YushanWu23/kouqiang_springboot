package com.kq.controller;

import com.kq.pojo.Product;
import com.kq.service.IProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class ProductController {//商品
    @Resource
    IProductService iProductService;
    @GetMapping("/getByProductId")
    Product getByProductId(@RequestParam int productId){
        return iProductService.getByProductId(productId);
    }
    @GetMapping("/getAll")
    List<Product> getAll(){
        return iProductService.getAll();
    }
}
