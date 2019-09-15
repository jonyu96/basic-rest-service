ACMS Example REST Service
-

This application is an example REST API that ACMS REST services can be modeled after. It includes several basic features of a modern production level REST service.

These basic features include:
1. Basic package structure
2. Spring Security - Basic Authentication
3. Swagger
4. Spring Boot Actuator
5. Unit Test examples
6. Spring Profiles
7. Enabled Configuration Properties
8. Custom Exception Handler for error handling
9. Logback

## Swagger
        http://localhost:8080/swagger-ui.html

## Spring Actuator
###### GET /actuator/health
get status of application
###### GET /actuator/info
get git information of application

### POST /test

###### curl Request
```
curl -i -X POST \
  http://localhost:8080/test \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Basic dGVzdDp0ZXN0MTAw' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 27' \
  -H 'Content-Type: application/json' \
  -H 'Cookie: JSESSIONID=639473E390EE936177B1C7BEDEFFC42F' \
  -H 'Host: localhost:8080' \
  -H 'User-Agent: PostmanRuntime/7.16.3' \
  -d '{
	"msisdn": "1234567890"
}'
```
###### curl Response
```
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 15 Sep 2019 08:11:18 GMT

{"status":"Success"}
```

###### Request Body
```
{
    "msisdn": "1234567890"
}
```

###### Response Body
```
{
    "status": "Success"
}
```

###### Failure Response
```
{
    "code": "String",
    "reason": "String",
    "explanation": "String"
}
```
