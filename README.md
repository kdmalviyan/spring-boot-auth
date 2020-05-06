# spring-boot-auth

How to use it:
1. Create database as specified in properties files.
2. Start application
3. Once application is at 8080, make a post request on http://localhost:8080/public/login with body as:
  {
    "userName": "admin",
    "password": "Password1"
  }
Content-type should be application/json

# Secured Resources Access:
1. Only Admin
  Create/Update User
  POST http://localhost:8080/user
  PUT http://localhost:8080/user
  GET http://localhost:8080/home/admin
2. Only User
  GET http://localhost:8080/home/user
3. Admin or User
  GET http://localhost:8080/home/general
  GET http://localhost:8080/home/user
  GET http://localhost:8080/home/user/<id>
  DELETE http://localhost:8080/home/user/<id>
