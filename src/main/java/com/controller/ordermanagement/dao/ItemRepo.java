package com.controller.ordermanagement.dao;

import com.controller.ordermanagement.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    @Query("update Item set rating=?2 where itemId=?1")
    @Modifying  //insated of select if we are updating @query doesnot aacept so we get error therefore then modifying is used
    //@query only handles dql operations not dml so we use @modifying
    @Transactional //for bydefault method like inbuilt one transactional is default but whnever we write this type of jql
    //then we need to use transactional annotation
    public int updaterating(int itemId,double rating);
}
