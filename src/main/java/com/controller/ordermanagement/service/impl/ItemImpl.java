package com.controller.ordermanagement.service.impl;

import com.controller.ordermanagement.dao.ItemRepo;
import com.controller.ordermanagement.dto.ItemReqDto;
import com.controller.ordermanagement.dto.ItemResDto;
import com.controller.ordermanagement.dto.UpdateItemReqDto;
import com.controller.ordermanagement.dto.UpdateRatingDto;
import com.controller.ordermanagement.exceptions.InvalidRatingException;
import com.controller.ordermanagement.exceptions.ItemNotFoundException;
import com.controller.ordermanagement.model.Item;
import com.controller.ordermanagement.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Override
    public ItemResDto addItem(ItemReqDto itemReqDto) {
        Item item=new Item();
        /*item.setItemName(itemReqDto.getItemName());
        item.setPrice(itemReqDto.getPrice());
        item.setQuantity(itemReqDto.getQuantity());
        item.setDiscount(itemReqDto.getDiscount());
        item.setRating(0);*/
        BeanUtils.copyProperties(itemReqDto,item);

        Item savedItem = itemRepo.save(item);

        ItemResDto itemResDto=new ItemResDto();

       // itemResDto.setItemId(savedItem.getItemId());
        BeanUtils.copyProperties(savedItem,itemResDto);
        return itemResDto;

    }

    @Override
    @Cacheable("items")
    public List<ItemResDto> getAllitems() {
        List<Item> all = itemRepo.findAll();
        List<ItemResDto> list = all.stream().map(items ->
        {
            ItemResDto itemResDto = new ItemResDto();
            BeanUtils.copyProperties(items, itemResDto);
            return itemResDto;
        }).toList();
        return list;
    }

    @Override
    public ItemResDto getItem(int id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with Id " + id));
        ItemResDto itemResDto=new ItemResDto();
        BeanUtils.copyProperties(item,itemResDto);
        return itemResDto;
    }

    @Override
    public List<ItemResDto> getItemByDiscountGreaterThan(Double  discount) {
        List<Item> all = itemRepo.findAll();
        if(discount==null){
            return all.stream().map(items ->
            {
                ItemResDto itemResDto = new ItemResDto();
                BeanUtils.copyProperties(items, itemResDto);
                return itemResDto;
            }).toList();
        }
        List<ItemResDto> list = all.stream().filter(item -> item.getDiscount() > discount).map(item -> {
            ItemResDto itemResDto = new ItemResDto();
            BeanUtils.copyProperties(item, itemResDto);
            return itemResDto;
        }).toList();
        return list;
    }

    @Override
    @CacheEvict(value = "items",key = "#id")
    public String deletedItem(int id){
        Item item = itemRepo.findById(id).orElseThrow(()->new ItemNotFoundException("Item not found "+id));
        itemRepo.deleteById(id);
        return "Deleted";
    }

    @Override
    @CachePut(value = "items",key = "#id")
    public ItemResDto updatedItem(int id, UpdateItemReqDto updateItemReqDto) {
        Item item = itemRepo.findById(id).orElseThrow(()->new ItemNotFoundException("Item not found "+id));
        BeanUtils.copyProperties(updateItemReqDto,item);

        Item save = itemRepo.save(item);
        ItemResDto itemResDto=new ItemResDto();
        BeanUtils.copyProperties(save,itemResDto);
        return itemResDto;
    }

    @Override
    public ItemResDto updateRating(UpdateRatingDto updateRatingDto) {

        if(updateRatingDto.getRating()<0 ||  updateRatingDto.getRating()>5){
            throw new InvalidRatingException("This item cannot be " +
                    "updated as the rating is  "+updateRatingDto.getRating());
        }
         itemRepo.updaterating(updateRatingDto.getItemId(), updateRatingDto.getRating());

        Item item = itemRepo.findById(updateRatingDto.getItemId()).orElseThrow(() -> new InvalidRatingException(updateRatingDto.getItemId()+" Itemid is not found")
        );

        ItemResDto itemResDto=new ItemResDto();

        BeanUtils.copyProperties(item,itemResDto);
        return itemResDto;
    }


}
