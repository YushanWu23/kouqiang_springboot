package com.kq.controller.forUser;

import com.kq.pojo.forUser.Product;
import com.kq.service.forUser.IProductService;
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
