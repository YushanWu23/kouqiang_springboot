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
public class FoodController {
    @Resource
    IFoodService iFoodService;
    /*@GetMapping("/getFoodByBusinessId")//根据商家编号查询所属食品信息
    List<Food> getFoodByBusinessId(@RequestParam int businessId){
        return iFoodService.getFoodByBusinessId(businessId);
    }*/
    @GetMapping("/getByFoodId")
    Food getByFoodId(@RequestParam int fooId){
        return iFoodService.getByFoodId(fooId);
    }
    @GetMapping("/getAll")
    List<Food> getAll(){
        return iFoodService.getAll();
    }
}
