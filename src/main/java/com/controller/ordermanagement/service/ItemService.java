package com.controller.ordermanagement.service;

import com.controller.ordermanagement.dto.ItemReqDto;
import com.controller.ordermanagement.dto.ItemResDto;
import com.controller.ordermanagement.dto.UpdateItemReqDto;
import com.controller.ordermanagement.dto.UpdateRatingDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ItemService {
     public ItemResDto addItem(ItemReqDto itemReqDto);
     public List<ItemResDto> getAllitems();
     public ItemResDto getItem(int id);
     public List<ItemResDto> getItemByDiscountGreaterThan(Double discount);
     public String deletedItem(int id);
     public ItemResDto updatedItem(int id,  UpdateItemReqDto updateItemReqDto);
     public ItemResDto updateRating(@RequestBody UpdateRatingDto updateRatingDto);
}
