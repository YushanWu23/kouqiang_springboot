package com.kq.controller.forUser;

import com.kq.pojo.forUser.DeliveryAddress;
import com.kq.service.forUser.IDeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/deliveryAddress")
public class DeliveryAddressController {
    @Resource
    IDeliveryAddressService iDeliveryAddressService;
    @GetMapping("/getDeliveryAddressByUserId")//根据用户编号查询所属送货地址
    List<DeliveryAddress> getDeliveryAddressByUserId(@RequestParam String userId){
        return iDeliveryAddressService.getDeliveryAddressByUserId(userId);
    }
    @GetMapping("/getDeliveryAddressByDaId")//根据送货地址编号查询送货地址
    DeliveryAddress getDeliveryAddressByDaId(@RequestParam int daId){
        return iDeliveryAddressService.getDeliveryAddressByDaId(daId);
    }
    @PostMapping("/saveDeliveryAddress")
    int saveDeliveryAddress(@RequestParam String contactName,@RequestParam int contactSex,@RequestParam String contactTel,@RequestParam String address,@RequestParam String userId){
        return iDeliveryAddressService.saveDeliveryAddress(contactName,contactSex,contactTel,address,userId);
    }
    @PostMapping("/updateDeliveryAddress")
    int updateDeliveryAddress (@RequestParam int daId,@RequestParam String contactName,@RequestParam int contactSex,@RequestParam String contactTel,@RequestParam String address){
        return iDeliveryAddressService.updateDeliveryAddress (daId,contactName,contactSex,contactTel,address);
    }
    @GetMapping("/removeDeliveryAddress")
    int removeDeliveryAddress(@RequestParam int daId,@RequestParam String userId){
        return iDeliveryAddressService.removeDeliveryAddress(daId,userId);
    }
}
