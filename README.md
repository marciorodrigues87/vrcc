# vrcc

## building and running

Just run the command:
 
	mvn clean install && cd api &&  mvn jetty:run 

on project dir.

## local testing

To add a new property:
	
	curl -XPOST http://localhost:8080/properties -d @request.json --header 'Content-Type:application/json' -vvv

To get the added property:

	curl http://localhost:8080/properties/1 -vvv
	
To search for properties in an area:

	curl http://localhost:8080/properties\?ax\=0\&ay\=500\&bx\=600\&by\=0 