# spring-temporal-io

1) Bring up temporal io server. Navigate into docker-compose directory at the root of the project and type docker-compose up 
2) run mvn clean install to install on dependencies 
3) start server 
4) hit endpoint /api/v1/asyncbook. Postman collection provided at the root of the project. and see that the workflow completes
5) comment out the return statement in the CommentBookActivityImpl.java file and uncommment the runtime exception. 
6) rerun the application and rehit the endpoint. 
7) navigate to localhost:8088 to see the status of your workflow. It should be in a state of running. 
8) stop the application.
9) undo the changes that you made in step 5. 
10) rerun the application. 
11) navigate to localhost:8088 to see that the workflow completed. 