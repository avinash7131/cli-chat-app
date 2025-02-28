# CLI Chat Application

🚀 **A simple command-line chat application built using Java Standard Edition (Java SE), JDBC, and MySQL.**

## 📌 Features
- 💬 **Private & Group Messaging** – Users can send messages privately or in groups.
- 📞 **Contact Management** – Save and organize contacts for easy communication.
- 🔐 **User Authentication** – Secure login system with password encryption.
- 💾 **Database Persistence** – Chat history and user data stored in MySQL.
- 📜 **Easy Setup** – Well-documented steps to get started quickly.

## 🛠️ Technologies Used
- **Java SE** – Core Java, OOP, and multithreading.
- **JDBC** – Database connectivity for storing and retrieving messages.
- **MySQL** – Data storage for users, messages, and contacts.

## 📂 Project Structure
```
CLI_Chat_App/
│-- src/
│   │-- Main.java
│   │-- ChatClient.java
│   │-- ChatServer.java
│   │-- DatabaseManager.java
│   │-- UserAuthentication.java
│-- database/
│   │-- chat_schema.sql
│-- README.md
```

## 🏗️ Setup Guide

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/yourusername/CLI_Chat_App.git
cd CLI_Chat_App
```

### 2️⃣ Configure the Database
- Install MySQL and create a database using `chat_schema.sql`.
- Update `DatabaseManager.java` with your MySQL credentials:
  ```java
  private static final String URL = "jdbc:mysql://localhost:3306/chat_db";
  private static final String USER = "your_mysql_user";
  private static final String PASSWORD = "your_mysql_password";
  ```

### 3️⃣ Compile & Run the Application
#### Start the Server
```sh
javac ChatServer.java
java ChatServer
```
#### Start the Client
```sh
javac ChatClient.java
java ChatClient
```

## 🎯 Future Enhancements
- ✅ Implement a GUI using JavaFX or Swing.
- ✅ Add file-sharing functionality.
- ✅ Improve encryption for secure messaging.

## 🤝 Contributing
Contributions are welcome! Feel free to fork the repository and submit a pull request. 🎉

## 📩 Contact
For queries, reach out on [LinkedIn](https://www.linkedin.com/in/kolta-budde-avinash-9169b2290) or email me at **avinashreddy1742001@gmail.com**. 🚀

---
**💡 If you like this project, consider giving it a ⭐ on GitHub!**
