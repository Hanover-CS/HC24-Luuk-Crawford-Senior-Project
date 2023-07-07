## Underground Ordering
### Project Idea
My intention for this project is to create a way for people to order ahead of time at the underground. I would like to at minimum create an interface using items at the underground as an example of how this could be useful, as a proof of concept. This would be useful as some food such as the quesadillas can take 15 minutes to make. Being able to order ahead of time means students would less disruption to their workflow waiting around for food to be prepared. I would like to have the app communicate to a server to send the order to. And if I can get that to work, it would be good to have a chef's screen to recieve the orders. Another stretch goal would be to incorporate hanover outlook logins to prevent abuse and push notifications.

### Main Features
#### 1. Order an item
   
      - a menu to select items from
   
      - ability to customize item's toppings
   
      - confirm and submit
   

#### Once that works, if I have time it would be nice to also impliment...

#### 2. A server
   
      - store orders

#### 3. Chef's screen
   
      - a way to view orders in progress (atleast for the chef)

#### 4. Other
      - login using hanover microsoft email
      - push notifications for customers to know when order is complete

---

### Comparable solution (focusing on interface choices)

#### [Chipotle](https://www.chipotle.com/)

Chipotle on android loads to a [welcome login screen](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/b88ffab7-d7fd-40e7-ab57-f08167a983dc). Though it can be skipped to view the menu and start to fill a cart after choosing a location.
I will make a landing screen that has a menu preview or a button to login to Hanover email through microsoft. The login will only be implimented if I happen to have extra time to figure out how that works.

Once login is confirmed, or if we pretend a login was confirmed the next page of actual ordering will show up.

Chipotle's menu loads as [a series of labeled pictures](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/74963349-cc17-45be-864e-66513fdf3f63) showing each of the kinds of items. And once an item is selected, it then loads the options specific for that menu item. 

One option would be to just recreate the [ordering sheets](https://github-production-user-asset-6210df.s3.amazonaws.com/32851596/250412801-19be7b5f-ed5d-42bb-882a-7042fcce4b22.jpg) with the same format but digital but I think a screen more similar to Chipotle would be better if I included pictures. Using a scrolling list also means I can add and remove available items to fit the current menu without redesigning the interface.

Chipotle's customization of orders is done by having the customer select which items to include, with meant being [split into half portions](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/76fddee4-b6bc-4153-ac04-6c2a0af3affb) if two are selected. 

I would like to have a list of toppings presented in a similar mannar once an item is chosen. Basically checkboxes next to the options or the topping buttons being highlighted if selected.

Chipotle takes the possible customizations further with [the quantity of an ingredient options with ability to put it on the side](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/aa747fa0-f0eb-485d-9742-ce18ebf854f0). This would add a level of complexity I don't think I will be able to support in this amount of time.

Chipotle gives the option to add other items to the order. Since mealswipes only support one main item I will likely just have a confirm order button after a single item's customization.

Once an order is confirmed the next screen would have something to keep track of the order if I get server communication both ways working.

### Server

There are multiple potential server options such as the three major offerings such as [Microsoft Azure](https://azure.microsoft.com/en-us), [AWS](https://aws.amazon.com/), and [Google Cloud](https://cloud.google.com/). I was thinking Microsoft Azure as that is what we use for our Hanover accounts, but I think using something like [supabase](https://supabase.com/) to set up an online database to store the orders would be good. It would have everything I need without the million extra options and types of storage to choose between. I'd also rather not need to sign up for free trials. It has Kotlin support as well. Supabase also has a nice interface so far, atleast for [creating the tables visually](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/769b538d-6129-4225-9c0f-c20dcd30a821). I do have some SQL experience but features like that are helpful for faster progress if I get this far.
