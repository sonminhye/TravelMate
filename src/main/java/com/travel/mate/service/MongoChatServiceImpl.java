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
import com.travel.mate.dto.ChatRoomDTO;

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
		
		//해당 방에
		AggregationOperation match = Aggregation.match(Criteria.where("roomCode").is(roomCode));
		//보낸 시간 기준으로 내림차순한 다음
		AggregationOperation sort = Aggregation.sort(new Sort(Sort.Direction.DESC,"sendDate"));
		//35개를 뽑고
		AggregationOperation limit = Aggregation.limit(35);
		//다시 그것을 오름차순 한다
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
		//해당 메세지보다 작은 애들 중 35개 가져오기 날짜별로 내림차순
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
		//해당 룸코드가 roomCode 인
		query.addCriteria(Criteria.where("roomCode").is(roomCode));
		//그리고 보낸사람이 내가 아닌 메세지들을
		query.addCriteria(Criteria.where("senderCode").ne(userCode));
		//읽음 처리 하기
		Update update = new Update();
		update.set("readFlag", true);
		
		System.out.println(query.toString());
		
		mongoDAO.changeUnReadMessage(query, update, COLLECTION);
	}

	@Override
	public ArrayList<ChatRoomDTO> checkUnReadMessageList(int userCode) {
		// TODO Auto-generated method stub
		
		//받는 사람이 userCode 이며, 읽지 않은 메세지가 있을 경우
		AggregationOperation match = Aggregation.match(Criteria.where("receiverCode").is(userCode).and("readFlag").is(false));
		//룸 코드를 기준으로 group 짓고, 그 수를 count 해서 unread 로 이름짓는다. 또한 roomCode 필드도 추가한다.
		AggregationOperation group = Aggregation.group("roomCode").count().as("unread").addToSet("roomCode").as("roomCode");
		
		
		Aggregation agg = Aggregation.newAggregation(
				match,
				group
		);
		
		System.out.println(agg.toString());
		return mongoDAO.checkUnReadMessageList(agg, COLLECTION);
	}
}
