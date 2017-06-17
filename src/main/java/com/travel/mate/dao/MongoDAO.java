package com.travel.mate.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.travel.mate.common.dao.AbstractMongoDAO;
import com.travel.mate.dto.ChatDTO;


@Repository("MongoDAO")
public class MongoDAO extends AbstractMongoDAO{

	private String COLLECTION = "chats";
	
    public ArrayList<ChatDTO> findAllChats(Aggregation agg, String COLLECTION){
		return findAll(agg, COLLECTION);
    }
    
    public ArrayList<ChatDTO> findAllChats(Query query, String COLLECTION){
		return findAll(query, COLLECTION);
    }
    
    public ArrayList<Object> checkUnReadMessageList(Aggregation agg, String COLLECTION) {
    	return findCountList(agg, COLLECTION);
    }
    
    public int checkUnReadMessage(Query query) {
    	return findCount(query, COLLECTION);
    }
        
    public void changeUnReadMessage(Query query, Update update , String COLLECTION){
    	updateMulti(query,update,COLLECTION);
    }
    
}