# vrcc

## environment

You will need the following dependencies:

	docker
	a docker-machine named default
	docker env configured (docker-machine start default && eval $(docker-machine env default))

## building and running

Just run the command:
 
	cd app && mvn clean install && cd .. && docker-compose up --build

on project root dir.

## local testing

To add a new property:
	
	curl -XPOST http://$(docker-machine ip default):8080/properties -d @request.json --header 'Content-Type:application/json' -vvv

To get the added property:

	curl http://$(docker-machine ip default):8080/properties/1 -vvv
	
To search for properties in an area:

	curl http://$(docker-machine ip default):8080/properties\?ax\=0\&ay\=500\&bx\=600\&by\=0 
