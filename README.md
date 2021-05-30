# **Reservation Application**

## Project proposal for an appointment changing application that also gives feedback to owners about their dogs.

**Functions of the Application**

The application takes care of a list of dogs reserved for the daycare. Each dog object will contain its name,
weights, time of appointment, and food of preference. Each day, there will be 8 hours of reservable time available 
from 10 am to 18 pm and each dog can only reserve one hour max per day. The customer can enter the dog info into the system and modify this entry if an error is made in the dog's
name, weight or food of preference. Then the customer can reserve the dog into an available time if the time is within hours of operation, no other dog is reserved at the same 
time, and the dog has never been reserved at any other time in the schedule before. Then, according to the same criteria for time, the customer can modify the reservation to any
other time as intended. Furthermore, for both customer and owner, a clear schedule can be shown with the hours of operation, and the dog that is reserved at a specific time, otherwise,
it will show time available. 
The application will contain activities that a dog can do and it will output a list of activities that a dog can do based on its weight. At the end of each reservation,
the application will store what the dog ate, list of activities it has done within the appointed time, and the amount of 
money charged for the activities. This can then be outputted to the customer if asked for along with basic doggy info and reservation time. 

**Users of the Application**
- The doggy daycare owner to see the appointed times and modify it according to customers demands.
- The owners of the dogs to get feedbacks about their dogs status, activities and what food they ate for the session.
- Also for both daycare and dogs owners, the application will return the fee due depending on what dog done.

**Interests to Me**

This project is of particular interest to me because I love dogs, and I used to volunteer for a child daycare. When I 
volunteered for the daycare, it didn't have any system of storing appointments and stuff. All the functions of the 
project I have listed above are for situations where it would have been extremely useful to have an application to 
solve all the problems I have encountered. Especially, the feedback to the customer on what the child has done or in 
this case, what the dog has done. I want to see how much easier and clearer this application can make for an appointed 
business like a daycare. 

## User Stories

- As a user, I want to be able to create a new dog, and add it to my list of reservation of the day. (Please Mark)
- As a user, I want to be able to modify my dog entry if I made any mistake on the name, weight or food. (Please Mark)
- As a user, I want to be able to modify a dog reservation and reserve it to a different time of the list of reservation. (Please Mark)
- As a user, I want to be able to check a clear schedule of all the hours of operation that are reserved and that are available. (Please Mark)
- As a user, I want to be able to create an activity and add that activity to a list of activities then output a list of activities a dog can do based on its conditions like weight.
- As a user, I want to be able to select a reserved dog in the schedule and see its information like reserved time, name, food preference, weight, activities done and cost of session.

**New save user stories**

- As a user, when I quit the application, I want to save the schedule of the reservations with all the dogs listed at correct time
- AS a user, I want to be able to retract that schedule of reservations with all the dogs at the exact timings and receive correct doggy information if I choose to do so

## Phase 4: Task 2 

- I chose to make a class in my model package more robust by adding a method that throws an exception. The class is Dog and the method is setDogWeight that throws WeightException if 
weight is over 200 or lower than 10. 

## Phase 4: Task 3
- To improve refactoring based on my UML diagram, I first notice all the panels that connect to the DoggyDayCareGui. I see that there are way too much coupling here, I would reduce this by
creating an interface called Panel in which all Panels would implement, and the Panel interface would contain a bidirectional association with the DoggyDayCareGui to access all the methods
and the fields within that class. This will also remove all the duplicated codes within the panels which happens quite frequently. 
- Second, I see that some of my panels have associations with the Reservations, Dog, and Activities classes. This is due to bad cohesion in the panels classes. Instead of those classes getting
user inputs, while also modifying the Reservation, Dog or Activities objects, I should pass them back into the DoggyDayCareGui for them to be modified. So that the panel classes should only 
be responsible for showing the visuals for the users to input their details in and then simply transporting them back into the DoggyDayCareGui to modify its fields accordingly. This will 
remove all the associations between the Panels and the model classes, successfully controlling the cohesion and the coupling of the project. 
- Third, by looking at the lines from the panels that associate with the DoggyDayCareGui, I can see that that class does way too much. So, to improve cohesion, I would make separate helper
classes that would take over some of the functionalities for that class. For example, instead of the class controlling the data from the users, and also showing which panels to display, I can just 
make that into two different classes.
