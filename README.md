# Singapore Car Park (SCP)
SCP is a useful application that provides an API to find the nearest parking lot available to your current location in Singapore City.
It connects to the official https://api.data.gov.sg/ fetch the latest available car parks in the country. The availability for each parking
lot will be updated per 1 minute to ensure you get the most accurate data.


### How To Run
* Use Docker Compose to run the API: ```docker compose up --build``` 
* Execute DML script ```sql/singapore_carpark_info.sql```
* Find the nearest available car park to your position using API http://your_host:8080/carparks/nearest?latitude=<your_latitude>&longitude=<your_longitude>

### API
Refer to Swagger page at  http://your_host:8080/swagger-ui.html for the API specifications.

API call example:  
Request:  
```
GET localhost:8080/carparks/nearest?latitude=1.0&longitude=100.0&page=0&size=2  
```
Response:
```yaml
[
  {
     "code": "J62",
     "address": "BLK 902/908 JURONG WEST STREET 91",
     "latitude": 1.3408897822679071,
     "longitude": 103.68540763961389,
     "totalLots": 700,
     "availableLots": 12
  },
  {
    "code": "J62M",
    "address": "BLK 901 JURONG WEST STREET 91",
    "latitude": 1.3398730274812336,
    "longitude": 103.68655537092351,
    "totalLots": 200,
    "availableLots": 165
  }
]
```





