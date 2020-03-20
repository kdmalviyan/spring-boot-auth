# spring-boot-auth

How to use it:
1. Create database as specified in properties files.
2. Start application
3. Once application is at 8080, make a post request on http://localhost:8080/public/login with body as:
  {
    "userName": "admin",
    "password": "Password1"
  }
