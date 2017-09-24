**Welcome to SpringDemo!**
===================


This repository is to demonstrate some tech stack including but not limited to: 

 - Spring Boot
 - Jersey
 - Jackson
 - Dozer
 - Builder design pattern

----------


**Installation Guideline**
-------------

The following are the steps to install this application **including the postman collection link**

> **Steps:**

> 

 >1. Open the project in Eclipse or any IDE able to open eclipse projects

 >2. After importing the project into Eclipse, run the following maven command within eclipse
 >**Maven** => **clean install package**

> 3. You're supposed to still have a problem because of Lombok dependency as it's still unidentified:
>  so do the following to resolve:
	>  - Check your project generated maven dependencies in the project structure to find which version of lombok is selected.
	>  - The generated jar dependency in my case is: **lombok-1.16.16.jar**
	>  - Go to your **~.m2 directory** on your local machine **org => lombok => your project selected version**
	>  - Open that Jar and select your IDE that the project is imported on then click Install and then quit the installer.
	>  - Restart your IDE
>4. Now run the project, it's supposed to run on **localhost:2222**
>5. You can easily import postman collection given the following link:
	> - [Postman collection link](https://www.getpostman.com/collections/395c065a5102667859b4)
>6. **Enjoy :)**	


----------

