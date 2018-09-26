
# Use Swagger doc API in future

Raken Email Service Demo  1.0.0 


This is a sample asynchronous services server to send email thru sendgrid.com



Integration test with @SpringBootTest should be separated in another project or using SoapUI to test it automatically. 



Application properties:

	send.raken.only true/false - send only to rakenapp.com domain if true. Default to true


URL: POST  /api/notification/email

Authorization: Basic 


Request Parameters : 

		enrich true/false - true will append message of the day provided by appweather.org


Request Body:

Mandatory fields: To

Model
[
 {
	"to":["tester@rakenapp.com", "tester2@rakenapp.com"],
	"cc": ["manager@rakenapp.com"],
	"bcc":[],
	"subject":"test subject",
	"body": "this is just a test"
 
 
 }, {
 	"to":["ken@test.com"],
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


