package com.travel.mate.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.travel.mate.dto.ChatDTO;

public class AbstractMongoDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public ArrayList<ChatDTO> findAll(Aggregation agg, String COLLECTION){
		AggregationResults<ChatDTO> data = mongoTemplate.aggregate(agg, COLLECTION, ChatDTO.class);
		return new ArrayList(data.getMappedResults()); 
	}
	
	public ArrayList<ChatDTO> findAll(Query query, String COLLECTION){
		return (ArrayList<ChatDTO>) mongoTemplate.find(query, ChatDTO.class, COLLECTION);
	}
	
	public ArrayList<Object> findCountList(Aggregation agg, String COLLECTION){
		AggregationResults<Object> data = mongoTemplate.aggregate(agg, COLLECTION, Object.class);
		return new ArrayList(data.getMappedResults()); 
	}
	
	public int findCount(Query query, String COLLECTION){
		return mongoTemplate.find(query, ChatDTO.class, COLLECTION).size();
	}
	
	public void updateMulti(Query query, Update update, String COLLECTION){
		mongoTemplate.updateMulti(query, update, COLLECTION);
	}
}
