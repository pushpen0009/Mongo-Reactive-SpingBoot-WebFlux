# Full Reactive

This repository contains backend that make use of **Reactive Web** patterns,
[Full Reactive Stack with Spring Boot 2, WebFlux, MongoDB).

## Components

### Spring Boot Reactive Web

A Spring Boot application that retrieves data using **Spring Reactive Web (WebFlux)**, instead of using the standard Web MVC framework. It connects to a MongoDB database in a reactive way too.

### Check on CMD with below command
curl -v -H "Accept: text/event-stream" http://localhost:8081/

###C:\Program Files\MongoDB\Server\4.2\bin\mongo

1) from first CMD, it will start the mongod with replica set
mongod --port 27017 --dbpath C:\Pushpendra\MyToshika_Github\Spring_Mongo_Reactive\data_rs01 --replSet rs01 --bind_ip localhost

2)run mongo and below from second CMD
rs.initiate()  // it will initilize the replica set
cfg = rs.conf();
cfg.protocolVersion=1;
rs.reconfig(cfg);
db.serverStatus().storageEngine