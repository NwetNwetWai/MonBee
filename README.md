
## **MonBee**

Mon for Money,
Bee for Field Officer

## **Introduction**

 - User can check customer lists at MonBee
 -  Users can add new customer details offline. The data will be stored in the app's local database and can be synced with the server when online by manual.
 - User Can upload Data to server when the connection is back to OnLine. 
 - User can generate Json file to /data/user/0/com.hana.monbee/app_data/
 - The apps need internet access for signIn.
 - From Myanmar, The app requires a VPN connection only during the first run to fetch initial data from the server.
 Afterward, if the network is unavailable or VPN is unavailable, the app will retrieve data from the local database to ensure functionality.
- Support Dark Mode/Light Mode.
  

## **Installation**

To install MonBee, follow these steps:

1. Clone the repository: **`git clone https://github.com/NwetNwetWai/MonBee.git`**
2. Navigate to the project directory: **`cd /app/src/main/kotlin/com/hana/monbee`**
3. Run

## **UI**
![authScreen](https://github.com/user-attachments/assets/811dfebe-1cba-4d87-9c42-e2005ab974bf)
![customerList](https://github.com/user-attachments/assets/7487ed66-766d-42e2-ab9b-86265044ce6e)
![customerDetails](https://github.com/user-attachments/assets/39095d6d-fa54-460d-88c8-21a7f9fa7425)
![AddNewCustomer](https://github.com/user-attachments/assets/a6f6f8c5-fba5-450a-9fe9-8cea900e5cd8)
![actions](https://github.com/user-attachments/assets/f06b70c3-49ff-4eb0-9cda-742ce9b121df)

## **Architecture**

<img width="738" alt="MonBeeArchitecture" src="https://github.com/user-attachments/assets/5c82a93c-3f21-4950-90c1-b32157fbe802">

