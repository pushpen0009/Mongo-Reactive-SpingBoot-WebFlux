package com.micro.secureapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.message.ObjectMessage;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.BsonValueCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.secureapp.model.User;
import com.micro.secureapp.model.WatchStreamDoc;
import com.micro.secureapp.repository.WatchStreamRepository;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;

@Service
public class MongoWatcherServiceImpl implements MongoWatcherService {
	Logger log = LoggerFactory.getLogger(getClass());
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	private static BsonDocument resumeToken;
	private final WatchStreamRepository watchStreamRepository;
	
	@Autowired
	public MongoWatcherServiceImpl(MongoClient mongoClient,
			WatchStreamRepository watchStreamRepository) {
		this.watchStreamRepository = watchStreamRepository;
		resumeToken = null;
		if(watchStreamRepository.count()>0) {
			WatchStreamDoc doc = watchStreamRepository.findFirst1ByOrderByCreatedDateDesc();
			log.debug("Last document {}",doc);
			resumeToken = BsonDocument.parse(doc.getResumeToken());
		}
		CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
	    CodecRegistry fromProvider = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
	    CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(defaultCodecRegistry, fromProvider);
		database = mongoClient .getDatabase("pushpendra_demo").withCodecRegistry(pojoCodecRegistry);
		collection = database.getCollection("user");
		log.debug("Documents exists : {}",collection.countDocuments());
	}
	
	private void consumeDocument(ChangeStreamDocument<User> document) {
		log.debug("document : {}",document);
		WatchStreamDoc doc = new WatchStreamDoc();
		BsonDocument resumeToken = document.getResumeToken();
		doc.setResumeToken(document.getResumeToken().toJson());
		log.debug("ResumeToken : {}",resumeToken.toJson());
		log.debug("document opertion type: {}",document.getOperationType());
		doc.setUser(document.getFullDocument());
		doc.setOperationType(document.getOperationType());
		//Save watch Stream results with resume token to resume back.
		doc.setCreatedDate(new Date().getTime());
		doc.set_id(UUID.randomUUID());
		watchStreamRepository.save(doc);
		log.debug("Document Saved: {}",doc);
	}
	
	@Override
	public void watch() {
		if(resumeToken!=null) {
			collection.watch(User.class).resumeAfter(resumeToken).forEach(document -> {
				consumeDocument(document);
			});
		} else {
			collection.watch(User.class).forEach(document -> {
				consumeDocument(document);
			});
		}
	}

}
