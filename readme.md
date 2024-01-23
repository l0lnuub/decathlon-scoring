# DECATHLON SCORING

## Overview
    Application is an API for calculating decathlon scores.
    There are also simple .html pages to calculate scores.

### Prerequisites
- Java 17 
  - Since application is running on Spring Boot 3.x, Java 17 is necessary to run the application

### Launching application
- Import gradle files
- In terminal run ```./gradlew bootRun```

### Using the API
- There are two endpoints:
  - http://localhost:9021/api/scores
    - Takes in all scores and returns score for each event + total score
    - Takes in a json body with all results filled out. Json body is representation of Performances class in application. Example json:
      ```
      {
        "hundred": 10.827,              // 100m time in seconds
        "longJump": 735.8,              // long jump distance in cm
        "shotPut": 16.78,               // shot put in meters
        "highJump": 210.34,             // high jump in cm
        "fourHundred": 48.19,           // 400m time in seconds
        "hurdles": 14.59,               // hurdles time in seconds
        "discus": 51.4,                 // discus distance in meters
        "poleVault": 496.45,            // pole vault height in cm
        "javelin": 70.67,               // javelin throw in meters
        "longRun": 4:07.42              // 1500m time in minutes:seconds
      }
      ```
      - Returns json with one extra field (total), which sums up all points. 
        Returning json is representation of FinalScore class in application. Previous example returns the following response: 
      ```
      {
        "hundred": 900,
        "longJump": 900,
        "shotPut": 900,
        "highJump": 900,
        "fourHundred": 900,
        "hurdles": 900,
        "discus": 900,
        "poleVault": 900,
        "javelin": 900,
        "longRun": 900,
        "total": 9000
      }
      ```
  - http://localhost:9021/api/scoreByEvent
    - Takes a single event with it's result and returns the score for that event
    - Takes json body with event name and result. Event name MUST equal one element in
      DecathlonEvents enum. Json (SingleEvent.class) example: 
    ```
    {
      "event": "highJump", 
      "result": "199.34"
    }
    ```
    - Returns an int value for points received. Previous example returns:
    ```
    798
    ```
      
### Using web browser
- Two endpoints:
  - http://localhost:9021/scores - For all event scores
  - http://localhost:9021/scoreByEvent - For single event scores

