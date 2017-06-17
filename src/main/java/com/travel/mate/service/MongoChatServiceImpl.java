package com.travel.mate.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.travel.mate.dao.MongoDAO;
import com.travel.mate.dto.ChatDTO;

import org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;


@Repository
public class MongoChatServiceImpl implements MongoChatService{
	
	@Resource(name = "MongoDAO")
	private MongoDAO mongoDAO;
	
	private String COLLECTION = "chats";
	
	@Override
	public ArrayList<ChatDTO> showChats(int roomCode) {
		// TODO Auto-generated method stub
		System.out.println("show Chats by mongoSevice");	
		
		AggregationOperation match = Aggregation.match(Criteria.where("roomCode").is(roomCode));
		AggregationOperation sort = Aggregation.sort(new Sort(Sort.Direction.DESC,"sendDate"));
		AggregationOperation limit = Aggregation.limit(10);
		AggregationOperation sortagain = Aggregation.sort(new Sort(Sort.Direction.ASC,"sendDate"));
		
		Aggregation agg = Aggregation.newAggregation(
			    match,
			    sort,
			    limit,
			    sortagain
		);
		
		System.out.println(agg.toString());
		return mongoDAO.findAllChats(agg, COLLECTION);
	}

	@Override
	public ArrayList<ChatDTO> showChats(int roomCode, String messageCode) {
		// TODO Auto-generated method stub
		System.out.println("show more Chats by mongoSevice");
		
		Query query = new Query();
		ObjectId obj = new ObjectId(messageCode);
		System.out.println(obj);
		query.addCriteria(Criteria.where("roomCode").is(roomCode).and("_id").lt(obj)).with(new Sort(Sort.Direction.DESC, "sendDate")).limit(35);
		
		System.out.println(query.toString());
		
		return mongoDAO.findAllChats(query, COLLECTION);
	}

	@Override
	public int checkUnReadMessage(int userCode) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("receiverCode").is(userCode)); //receiverCode 가 userCode인
		query.addCriteria(Criteria.where("readFlag").is(false));  //그리고 readFlag가 false 인 
	
		return mongoDAO.findCount(query, COLLECTION);
		
	}

	@Override
	public void changeUnReadMessage(int roomCode, int userCode) {
		// TODO Auto-generated method stub
		Query query = new Query();
		
		query.addCriteria(Criteria.where("roomCode").is(roomCode));
		query.addCriteria(Criteria.where("senderCode").ne(userCode));
		Update update = new Update();
		update.set("readFlag", true);
		
		System.out.println(query);
		
		mongoDAO.changeUnReadMessage(query, update, COLLECTION);
	}

	@Override
	public ArrayList<Object> checkUnReadMessageList(int userCode) {
		// TODO Auto-generated method stub
		
		AggregationOperation match = Aggregation.match(Criteria.where("receiverCode").is(userCode).and("readFlag").is(false));
		AggregationOperation group = Aggregation.group("roomCode").count().as("unread");
		
		Aggregation agg = Aggregation.newAggregation(
				match,
				group
		);
		
		System.out.println(agg.toString());
		
		return mongoDAO.checkUnReadMessageList(agg, COLLECTION);
	}
}
