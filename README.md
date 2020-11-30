# Portfolio Twitter Application


Build a simple portfolio Java web app that displays the profile image, name, some text with
the experience and a 5 tweet list of the user’s Twitter timeline.

# Steps to get App

To run the application, please take into account the following steps:

  - Install JAVA_HOME environments variable for Java 11.
  
```sh
mvnw spring-boot:run
```

### Frameworks and Libraries

| Framework/Library |
| ------ |
| Spring Boot |
| Spring JPA Data |
| Spring Social Twitter |
| Lombok |
| Java 11 |
| HTML / CSS / JQuery |
| Maven |
| MySQL |

### Endpoints
- Get All profiles GET endpoint: http://localhost:8080/profile
```sh
{
    "profiles": [
        {
            "idPortfolio": int,
            "description": "String",
            "imageUrl": "String",
            "twitterUserName": "String",
            "title": "String"
        }
    ]
}
```
- Get profile user: GET http://localhost:8080/profile/{id}
```sh
{
    "profileInfo": {
        "idPortfolio": int,
        "description": "String",
        "imageUrl": "String",
        "twitterUsername": "String",
        "title": "String"
    },
    "tweets": [
        {
            "id": int,
            "idStr": "String",
            "text": "String",
            "createdAt": "DateTime",
            "fromUser": "String",
            "profileImageUrl": "String",
            "toUserId": int,
            "inReplyToScreenName": "String",
            "fromUserId": int,
            "languageCode": "String",
            "source": "String",
            "retweetCount": int,
            "retweeted": false
        }
    ]
}
```
- Update profile user: PUT http://localhost:8080/profile/{id}
- Request
```sh
{
    "description": "String",
    "imageUrl": "String",
    "twitterUsername": "String",
    "title": "String",
    "tittle": "String"
}
```
- Response
```sh
{
    "message": "String"
}
```
