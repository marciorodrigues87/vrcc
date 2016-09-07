# vrcc

## environment

You will need the following dependencies:

	java8
	maven3
	docker
	docker-compose
	a docker-machine named default
	docker env configured (docker-machine start default && eval $(docker-machine env default))

## building and running

Just run the command:
 
	cd app && mvn clean install && cd .. && docker-compose up --build

on project root dir.

NOTE: If the application receives error when connecting to the database, wait until the database container is created and then run the command again :(

## local testing

To add a new property:
	
	curl -XPOST http://$(docker-machine ip default):8080/properties -d @request.json --header 'Content-Type:application/json' -vvv

To get the added property:

	curl http://$(docker-machine ip default):8080/properties/1 -vvv
	
To search for properties in an area:

	curl http://$(docker-machine ip default):8080/properties\?ax\=0\&ay\=500\&bx\=600\&by\=0

## automated tests

After building and running, just run the following command:

	cd app/integration-tests && mvn test -P integration-tests -Dvrcc.tests.integration.host=http://$(docker-machine ip default)

on project root dir.

## logs

To see the application logs access this link:

	http://$(docker-machine ip default):9292/index.html#/dashboard/file/logstash.json

Or run (for Mac user):

	open -a Safari http://$(docker-machine ip default):9292/index.html#/dashboard/file/logstash.json
