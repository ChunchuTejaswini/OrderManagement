package com.controller.ordermanagement.controller;

import com.controller.ordermanagement.dto.ItemReqDto;
import com.controller.ordermanagement.dto.ItemResDto;
import com.controller.ordermanagement.dto.UpdateItemReqDto;
import com.controller.ordermanagement.dto.UpdateRatingDto;
import com.controller.ordermanagement.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    private static final Logger logger=LoggerFactory.getLogger(ItemController.class);

    @PostMapping("/addItem")

    public ResponseEntity<ItemResDto> addItem( ItemReqDto itemReqDto){

        logger.trace("Request is received to add the item in item controller");

        logger.trace("Recived Itemreqdto ,{}",itemReqDto);
        logger.trace("Calling service to item controller");
       ItemResDto itemResDto = itemService.addItem(itemReqDto);
       logger.trace("Reacievd data from the service");
       logger.trace("sending request of item to item controller");
        return new ResponseEntity<>(itemResDto, HttpStatus.CREATED);
      //  return itemService.addItem(itemReqDto);

    }

    @GetMapping
    public ResponseEntity<List<ItemResDto>> getAllitems(){
        List<ItemResDto> allitems = itemService.getAllitems();
        return ResponseEntity.ok(allitems);
    }

    @GetMapping("/get/{id}")
    public ItemResDto getItem(@PathVariable  int id){
        return itemService.getItem(id);
    }

    @GetMapping("/byDiscount")
    public List<ItemResDto> getItemByDiscountGreaterThan(@RequestParam(name="discount", required=false) Double discount){

        return itemService.getItemByDiscountGreaterThan(discount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedItem(@PathVariable int id){
        String s = itemService.deletedItem(id);
        return ResponseEntity.ok("deleted the item");
    }

    //update operation put/patch ->put ->changing whole data,patch->updating partial data id is mandatory with the updated data

    //id is collected in path variable and updateing data in the form of paylaod

    @PutMapping("update/{id}")
    public ItemResDto updatedItem(@PathVariable int id, @RequestBody UpdateItemReqDto updateItemReqDto){

        return itemService.updatedItem(id, updateItemReqDto);
    }

    @PutMapping("/rating")
    public ItemResDto updateRating(@RequestBody  UpdateRatingDto updateRatingDto){
       return itemService.updateRating(updateRatingDto);
    }

}

//request from the client goes to dispacther servlet an dnext to controller to service layer to dao layer
//logging.level.root=TRACE
//logging.level.root=DEBUG
//logging.level.root=INFO
//logging.level.root=WARN
//logging.level.root=ERROR