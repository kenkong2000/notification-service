# rakentest

Raken Email Service Demo 1.0.0

This is a sample asynchronous services server to send email thru sendgrid.com

Integration test with @SpringBootTest should be separated in another project or using SoapUI to test it automatically.

Required to setup environment variable: SENDGRID_API_KEY for sendgrid

Application.properties:

	send.raken.only true/false - send only to rakenapp.com domain if true. Default to true
	motd.source - url for the message of the day source
  	motd.user - basic auth username for motd source
  	motd.password - basic auth password for motd password

URL: POST /api/notification/email

Authorization: Basic

Request Parameters :

	enrich true/false - true will append message of the day provided by motd.source
  	
  
Request Body:

Mandatory fields: To

Body payload example: json/application
    
      
       [
        { "to":["test@rakenapp.com", "test2@rakenapp.com"], 
         "cc": ["manager@rakenapp.com"], 
         "bcc":[], 
         "subject":"hello world", 
         "body": "hello ken" 
       },
       { "to":["ken@test.com"], 
         "cc": [], 
         "bcc":[], 
         "subject":"hello world", 
         "body": "hello ken" 
       }
       ]



Responses:

Code 204 - Success 
Code 400 - Bad Request 
Code 401 - Bad authentication 
Code 403 - No permission for user role
