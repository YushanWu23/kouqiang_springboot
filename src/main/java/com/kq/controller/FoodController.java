package com.kq.controller;

import com.kq.pojo.Food;
import com.kq.pojo.unuse.Doctor;
import com.kq.service.IFoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/food")
public class FoodController {//商品
    @Resource
    IFoodService iFoodService;
    @GetMapping("/getByFoodId")
    Food getByFoodId(@RequestParam int fooId){
        return iFoodService.getByFoodId(fooId);
    }
    @GetMapping("/getAll")
    List<Food> getAll(){
        return iFoodService.getAll();
    }
}
