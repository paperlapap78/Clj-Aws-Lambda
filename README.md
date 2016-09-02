# Clj-Aws-Lambda
Exercise implementation using Clojure in AWS Lambda


There are two lambdas in clojure
- hello world
- processing an S3 event

To test the lambdas, use the test function in the aws console
- send a json with firstName and lastName
- send the S3 Post sample template from the aws console

Deployment using AWS cli

aws lambda create-function   
	--function-name clj-hellopojo   
	--handler PojoHandler::handlepojo   
	--runtime java8   
	--memory 512   
	--timeout 10 
	--role  arn:aws:iam::<your_role>:role/lambda_deployer  
	--zip-file fileb://./target/lambda-clj-examples-0.1.0-standalone.jar
	
aws lambda create-function   
	--function-name clj-s3   
	--handler stream_handler  
	--runtime java8   
	--memory 512   
	--timeout 10 
	--role  arn:aws:iam::<your_role>:role/lambda_deployer  
	--zip-file fileb://./target/lambda-clj-examples-0.1.0-standalone.jar


