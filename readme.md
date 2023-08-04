### Task:

Bus charter company wants to provide new service for travel agencies – draft ticket price.
To receive draft ticket price, following data must be provided:

- List of passengers
- List of luggage items of each passenger
  Draft ticket price is calculated as following:

1. Base price for an adult is provided by already existing service returning number from
   database based on given route (bus terminal name).
2. Child passengers receive 50% discount.
3. Luggage price is 30% of base price.
4. Tax rates are provided by already existing service, which provides list of percentage
   rates on given day of purchase.
   The result of calculation should contain both total price and prices for each individual item.

### IMPLEMENTATION DESCRIPTION:

* Overview: Spring Boot application with Java 17, using Maven.

* Approach: The app serves as a REST API, enabling communication through POST endpoint.
  JSON format is used for data exchange.

* Code Structure:
    * **Controller** (DraftTicketResource) handles incoming HTTP POST request and interacts with services (business
      service and mappers from request to context and from context to response). Endpoint: "/api/v1/draft"<br> <br>
    * **DraftTicketCalculationService** holds the core business logic for calculating draft prices. It takes input
      parameters, calls existing services for tax rate and base price, applies pricing rules and returns service context
      with updated calculation results. <br> <br>
    * **DraftTicketRequest** and **DraftTicketResponse** classes represent the request and response objects for the API,
      ensuring consistent data exchange with clients. **DraftTicketContext** class is middle layer model used for
      calculation - request data are mapped to context, calculation service updates it with values and then necessary
      data is mapped to response.

      **Example of DraftTicketRequest**:
        ```json {
        "destination": "VILNIUS",
        "passengers": [
          {
            "ageGroup": "ADULT",
            "numberOfBags": 2
          },
          {
            "ageGroup": "CHILD",
            "numberOfBags": 1
          }
        ]
      ```
      **Example of DraftTicketRequest**:
        ```json
        {
            "ticketPrices": [
                {
                    "ageGroup": "ADULT",
                    "passengerTicketPrice": 12.1,
                    "numberOfBags": 2,
                    "luggagePrice": 7.26
                },
                {
                    "ageGroup": "CHILD",
                    "passengerTicketPrice": 6.05,
                    "numberOfBags": 1,
                    "luggagePrice": 3.63
                }
            ],
            "totalPrice": 29.04
        }


    * **MissingDataException** is used when data is missing from the request.<br> <br>
    * **Builder Design Pattern** is used for all models to ensure readable construction of the objects (provided by Lombok library).<br> <br>

* Dependencies: Lombok is used to reduce boilerplate code.<br> <br>
* Testing: All service classes (mappers, controller and business service class (excluding mocked services)) have unit
  tests for different cases and ensuring full code coverage.
