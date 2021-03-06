# notification-service

Notification Service Demo 1.0.0

This is a sample asynchronous services server to send email thru sendgrid.com

Actual Integration test should be separated such as running automatic SoapUI test connected to DEV/QA server

Required to setup environment variable: 
	
	SENDGRID_API_KEY for sendgrid 
	MOTD_API_KEY for OpenWeather


Application.properties:

	send.dev.only true/false - send only to designated dev domain if true. Default to true
	dev.domain - dev domain name
	from.email - the from email address used for sending email
	email.template - location of the email template
	motd.source - url for the message of the day source	
	motd.locations - city separated by comma for motd to retrieve weather information
	motd.template - the template string for the motd
	pool.size - pool size for the async service. Default to 1
	max.pool.size - maximum pool size for the async service. Default to 10
	queue.capacity - queue size to consume in the pool. Default to 200
	

URL: POST /api/notification/email

Authorization: Basic

Request Parameters :

	enrich true/false - true will append message of the day provided by motd.source
  	
  
Request Body:

Mandatory fields: 

	To

Body payload example: json/application
    
      
       [
        { "to":["tester1@abc123.com", "tester2@abc123.com"], 
         "cc": ["manager1@abc123.com"], 
         "bcc":[], 
         "subject":"notification service 1.0.0 completed", 
         "body": "Please proceed testing" 
       },
       { "to":["developer@abc123.com"], 
         "cc": [], 
         "bcc":[], 
         "subject":"notification service 1.0.0 test request sent", 
         "body": "Test request for notification service 1.0.0 has been sent" 
       }
       ]



Responses:

	Code 204 - Success 
	Code 400 - Bad Request 
	Code 401 - Bad authentication 
	Code 403 - No permission for user role
