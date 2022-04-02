# ✅ [Java Test Task] To-Do App
Dear Candidate,

We've prepared a simple test task for you where you can show all your knowledge and skills in the Java area. Feel free to improvise and don't be afraid of challenges 👀. We all believe that you are full of 📚 knowledge and 💪 motivation.

Respectfully, ORIL team.

P.S. If you have any questions 🤔 or just want to chat 💬, please email me at vyacheslav.perfilyev@oril.co.

P.S.S. I bet that you could do it with your eyes shut and there is literally nothing that could stop you.
## 📃 Task
Your goal for the task is to show us as much as you know about Java (Spring) and Back-End development. The app should be not overcomplicated and you have to remember it's not 🚀 rocket science, so do not overload everything.

Our (ORIL) goal for the task is to see how deeply you understand the technology and figure out how you find solutions in different situations.

For the test task, you have to create your own GitHub repository.

DEADLINE: 1-2 days.

### Technical requirements
- Java 11+
- String Boot, Spring Data.
- MongoDB.


Technical task itself consists of two main parts and one optional:
1. Collect data.\
   You need to fetch cryptocurrency data prices from CEX.IO. For this task you should pull last prices for the following pairs: BTC/USD, ETH/USD and XRP/USD. This data should be stored in database, since you will use this data in the next two parts of the task. Feel free to store any additional information to database like 'createdAt' date etc.
2. Rest Endpoints.\
   You need to create a rest controller with the following endpoints
    -  GET ```/cryptocurrencies/minprice?name=[currency_name]``` - should return record with the lowest price of selected cryptocurrency.
    -  GET ```/cryptocurrencies/maxprice?name=[currency_name]``` - should return record with the highest price of selected cryptocurrency
       [currency_name] possible values: BTC, ETH or XRP. If some other value is provided then appropriate error message should be thrown.
    -  GET ```/cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size]``` - should return a selected page with selected number of elements and default sorting should be by price from lowest to highest. For example, if page=0&size=10, then you should return first 10 elements from database, sorted by price from lowest to highest.
       ```[page_number]``` and ```[page_size]``` request parameters should be optional, so if they are missing then you should set them default values ```page=0, size=10```.
3. * Generate a CSV report.\
     You need to create an endpoint that will generate a CSV report saved into file.
    - GET ```/cryptocurrencies/csv```
      Report should contain the following fields: Cryptocurrency Name, Min Price, Max Price. So there should be only three records in that report, because we have three different cryptocurrencies. Feel free to use any available library for generating csv files.

### ✔ Task evaluation
1. All parts of the task were implemented correctly
2. Quality and style of the code
3. All possible exception situations are handled properly
4. The project should be covered with tests.

## 🏁 Finishing the task
After everything is done, please commit and send a link to your GitHub repository to HR.

## 📑 Contributing

If you notice any mistake or have an idea of improving the test task, please feel free to contact vyacheslav.perfilyev@oril.co for an immediate response 🙌.