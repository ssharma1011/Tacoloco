# Tacoloco
This API provides a POST operation, URL - http://localhost:8080/order/total. 
**Request** of this API will be a list to adjust multiple menu items and will consist of Name of the menu item and their respective quantities. Ex -  
[
    {
        "name": "Veggie Taco",
        "quantity":1
    },
    {
        "name": "Chicken Taco",
        "quantity":3
    }
]

NOTE - Menu items are configured at the properties file level as of now and more menu items can be added in applications.yml file

**Response** of this API will be a simple text message showing the total of a valid order

Scenario 1 - Valid menu item, Valid Quantity
![image](https://user-images.githubusercontent.com/25885367/121796265-be902880-cbe5-11eb-87c8-596304d55101.png)

Scenario 2 - Valid quantity, Invalid menu item
![image](https://user-images.githubusercontent.com/25885367/121796402-ad93e700-cbe6-11eb-9903-e6c1deb25a89.png)

Scenario 3 - Invalid quantity
![image](https://user-images.githubusercontent.com/25885367/121796420-d74d0e00-cbe6-11eb-9e07-413d9fc3f93c.png)

Scenario 4 - Invalid Quantity for only 1 menu item and valid for another. Provides success Response
![image](https://user-images.githubusercontent.com/25885367/121796439-fb105400-cbe6-11eb-9e6f-bd50155a836d.png)

Scenario 5 - Quantity is greater alteast 4 and 20% discount is provided
![image](https://user-images.githubusercontent.com/25885367/121796511-838ef480-cbe7-11eb-8043-b59c21353bf8.png)



