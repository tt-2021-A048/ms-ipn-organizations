package com.pan.msOrganization.service.impl;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pan.msOrganization.model.DBSequence;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public int getSequenceNumber(String sequenceName) {
		//Get seq number
		Query query = new Query(Criteria.where("id").is(sequenceName));
		//Update seq number
		Update update = new Update().inc("seq",1); 
		//Modify in document
		DBSequence counter = mongoOperations.findAndModify(
				query, 
				update, 
				options().returnNew(true).upsert(true),
				DBSequence.class
				);
		return !Objects.isNull(counter) ? counter.getSeq() : 1 ;
	}
}
