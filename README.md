# Capybucks Store - Business Management App 
### 2nd Project IF2210 - Object-Oriented Programming 2023
```
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣶⣶⣆⠀⢸⣏⠓⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⢳⣿⡏⣼⣀⣸⣿⣧⣸⣇⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣼⣿⡅⠻⠿⢄⣠⠠⣄⡀⠉⠛⠿⣷⣶⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⠻⣿⣿⠂⢸⡧⠤⣼⣇⠀⠀⠀⠀⠉⣿⣿⣿⣷⣶⣄⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠃⠀⠀⠉⠀⠀⠈⠙⠛⢿⡿⠀⠀⠀⠀⢠⣿⠿⠿⣿⡿⠿⡆⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⡤⠤⠤⠶⠒⠒⠶⠶⠤⠤⠤⠖⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣼⣷⣾⣷⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣀⡤⠖⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢤⣤⣄⡈⠉⢹⡏⠁⣽⠶⢴⠆
⠀⠀⠀⢀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠴⠶⢿⠇⢸⡇⠰⡾⠶⠶⠀
⠀⠀⣠⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡴⠚⠛⠀⢨⣧⣀⠟⢦⠀⠀
⠀⣼⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⣠⣄⣴⣶⣶⣾⣿⠟⠉⠀⠀⠀⠀
⣸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⡿⢿⣿⣾⣿⣿⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀
⣿⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠸⠟⠛⡽⠛⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀
⣿⣿⡇⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢻⣿⣿⣿⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠀⠀⠀⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠈⣿⣿⣿⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠀⠀⠀⠀⠀⠀⠀⠀⣀⣾⠇⠀⢀⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⢹⣿⣿⣿⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢀⣼⣿⣇⠀⠀⠀⠀⠀⠀⣠⣴⣿⢹⣀⣾⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⢻⢿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣧⣾⣿⣿⡏⣸⣇⣸⣀⣶⣴⣿⡇⣹⣾⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠈⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣠⣾⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⠛⢻⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⢿⡟⠀⠀⠀⠀⣀⠀⢀⣤⣇⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣿⠃⢀⣼⣷⣾⣷⣾⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⢿⣿⣻⡿⠋⣿⣿⣿⣿⢧⣀⠻⠿⣿⣍⣺⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢠⡧⠼⠿⣟⣻⣿⢿⣿⣿⣿⣿⣿⣻⣻⠿⢭⣿⣳⣦⣍⠀⠀⠀⠀⠛⠿⢿⣮⣛⢮⣿⡆⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⠉⠙⠉⠉⠉⠉⠙⠙⠛⠓⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
❤ CBR--masbro
```
## Overview
>CBR Application - Business Management App is a desktop-based sales system (point-of-sales/POS) application, created using the Java programming language using the JavaFX library for the user interface. This application has features that are generally expected in a POS application, such as inventory management and transaction management. In addition, the application supports membership features to provide rewards to loyal customers; customers are divided into 3 categories, namely customers, members, and VIPs, with customers being the lowest level without rewards and VIPs being the highest level with the most rewards. The CBR Application - Business Management App also includes an export feature to PDF and provides extension/plug-in support to add program functionality.

This project is a Java implementation of Object-Oriented Programming principles such as:
- Inheritance
- Composition
- Interface
- Polymorphism
- Method Overriding and Method Overloading
- Java API Collection
- S.O.L.I.D. principles
- Design Pattern (Adapter, Singleton, Decorator, Builder, Flyweight)
- Reflection
- Threading

This project is built to meet the following [guidelines](https://docs.google.com/document/d/1SA2q8LrSiYntjCQ9Sx6xIGIAVYe6kcZg5iBjgibcEfk/edit#).

## Preview
1. Main Page
<img src="doc/mainPage.jpg"> 
2. Inventory
<img src="doc/inventory.jpg"> 
3. Clients
<img src="doc/clients.jpg"> 
4. Transaction
<img src="doc/transaction.jpg"> 
5. Plugin

![image](https://user-images.githubusercontent.com/91037907/236701536-7f645166-08ba-467e-9045-e841a7af6bba.png)

6. Settings (with Plugin-Systems loaded)

![image](https://user-images.githubusercontent.com/91037907/236701557-7f674f96-7fe6-4d8d-984b-4536277fb927.png)

7. Plugin-Chart-1

![image](https://user-images.githubusercontent.com/91037907/236701578-a2c4c08e-3908-4ebb-bbf6-bfa8469d7d4b.png)

8. Plugin-Chart-2

![image](https://user-images.githubusercontent.com/91037907/236701591-ed40de51-29f0-4c82-97d7-78840ec8ea24.png)

9. Plugin-System-1

![image](https://user-images.githubusercontent.com/91037907/236701995-7a67eb6e-018a-4874-bc2e-755ebadc5a2e.png)

10. Plugin-System-2 (with Plugin-System-1 loaded)

![image](https://user-images.githubusercontent.com/91037907/236702022-40bc48db-0a36-4f05-922d-f016937fbfd1.png)

11. Exported PDF (with Plugin-System-1 loaded)

![image](https://user-images.githubusercontent.com/91037907/236702988-5b6923a3-bfaf-4d7b-a6a2-46bfb1d6c30b.png)


## Prerequisites
- [Java](https://www.java.com/en/)
- [Maven](https://maven.apache.org/download.cgi)
- [Amazon Corretto 8 - OpenJDK](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/what-is-corretto-8.html)

## How to Run
1. Clone this repository.

```
 $ git clone https://github.com/JeffreyChow19/CBR-Business-Management-App.git
 ```
or
```
 $ git clone git@github.com:JeffreyChow19/CBR-Business-Management-App.git
```

2. Open terminal and navigate to the root directory of this repository, then navigate to App directory.
```
 $ cd App
```
3. Run the following commands to start using the app.

(Without `.jar` file) run this command
```bash
 $ mvn clean javafx:run
```
(With `.jar` file) run this command
```
 $ java -jar bin/app-1.0-jar-with-dependencies.jar
```
4. Optionally, to re-compile the `.jar` files for the app and plugins, navigate to the root directory and run the following command. The compilation results will be located at `*/target/` directories. 
```bash
 $ mvn clean package
```

## Directory Structure
```
├───App
│   ├───assets
│   │   ├───data
│   │   │   ├───json
│   │   │   ├───obj
│   │   │   └───xml
│   │   ├───icons
│   │   ├───images
│   │   │   └───products
│   │   └───styles
│   ├───bin
│   └───src
│       └───main
│           └───java
│               └───com
│                   └───cbr
│                       ├───datastore
│                       ├───exception
│                       ├───models
│                       │   └───Pricing
│                       ├───plugin
│                       ├───seeder
│                       ├───utils
│                       └───view
│                           ├───components
│                           │   ├───buttons
│                           │   ├───cards
│                           │   │   └───clientcard
│                           │   ├───cardslist
│                           │   ├───clockwidget
│                           │   ├───form
│                           │   │   └───dropdown
│                           │   ├───header
│                           │   │   ├───headermenu
│                           │   │   └───tabmenu
│                           │   ├───labels
│                           │   ├───logo
│                           │   ├───popup
│                           │   └───spinner
│                           ├───pages
│                           └───theme
├───bin
├───doc
├───Plugin-Base
│   └───src
│       └───main
│           └───java
│               └───com
│                   └───cbr
├───Plugin-Chart-1
│   └───src
│       └───main
│           └───java
│               └───com
│                   └───cbr
├───Plugin-Chart-2
│   └───src
│       └───main
│           ├───java
│           │   └───com
│           │       └───cbr
│           └───resources
│               └───META-INF
│                   └───services
├───Plugin-System-1
│   └───src
│       └───main
│           ├───java
│           │   └───com
│           │       └───cbr
│           └───resources
│               └───META-INF
│                   └───services
└───Plugin-System-2
    └───src
        └───main
            ├───java
            │   └───com
            │       └───cbr
            └───resources
                └───META-INF
                    └───services
```

## Authors

| Name                   | GitHub                                            | NIM      |
|------------------------|---------------------------------------------------|----------|
| Alexander Jason        | [AJason36](https://github.com/AJason36)           | 13521100 |
| Angela Livia Arumsari  | [liviaarumsari](https://github.com/liviaarumsari) | 13521094 |
| Eugene Yap Jin Quan    | [yuujin-Q](https://github.com/yuujin-Q)           | 13521074 |
| Jeffrey Chow           | [JeffreyChow19](https://github.com/JeffreyChow19) | 13521046 |
| Nathaniel Evan Gunawan | [nathanielevan](https://github.com/nathanielevan) | 13516055 |
| Rachel Gabriela Chen   | [chaerla](https://github.com/chaerla)             | 13521044 |

