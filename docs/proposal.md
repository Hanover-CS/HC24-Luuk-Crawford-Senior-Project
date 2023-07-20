## Underground Ordering
### Project Idea
My intention for this project is to create a way for people to order ahead of time at the underground. I would like to at minimum create an interface using items from the underground as an example of how this could be useful, as a proof of concept. This would be useful as some food such as the quesadillas can take 15 minutes to make. Being able to order ahead of time means students would have less disruption to their workflow waiting around for food to be prepared. I would like to have the app communicate with a server to send the order. And if I can get that to work, it would be good to have a chef's screen to receive the orders. Another stretch goal would be to add push notifications and incorporate Hanover Outlook logins to prevent abuse.

### Main Features
#### 1. Order an item
   
      - a menu to select items from
   
      - ability to customize item's toppings
   
      - confirm and submit
   

#### Once that works, if I have time it would be nice to also implement...

#### 2. A server
   
      - store orders

#### 3. Chef's screen
   
      - a way to view orders in progress (at least for the chef)

#### 4. Other
      - log in using Hanover Microsoft email
      - push notifications for customers to know when the order is complete

---

### Comparable solution (focusing on interface choices)

#### [Chipotle](https://www.chipotle.com/)

Chipotle on Android loads to a [welcome login screen](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/b88ffab7-d7fd-40e7-ab57-f08167a983dc). Though it can be skipped to view the menu and start to fill a cart after choosing a location.
I will make a landing screen that has a menu preview or a button to log in to Hanover email through Microsoft. The login will only be implemented if I happen to have extra time to figure out how that works.

Once login is confirmed, or if we pretend a login was confirmed the next page of actual ordering will show up.

Chipotle's menu loads as [a series of labeled pictures](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/74963349-cc17-45be-864e-66513fdf3f63) showing each of the kinds of items. And once an item is selected, it then loads the options specific to that menu item. 

One option would be to just recreate the [ordering sheets](https://github-production-user-asset-6210df.s3.amazonaws.com/32851596/250412801-19be7b5f-ed5d-42bb-882a-7042fcce4b22.jpg) with the same format but digital but I think a screen more similar to Chipotle would be better if I included pictures. Using a scrolling list also means I can add and remove available items to fit the current menu without redesigning the interface.

Chipotle's customization of orders is done by having the customer select which items to include, with meant being [split into half portions](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/76fddee4-b6bc-4153-ac04-6c2a0af3affb) if two are selected. 

I would like to have a list of toppings presented similarly once an item is chosen. Checkboxes next to the options or the topping buttons are highlighted if selected.

Chipotle takes the possible customizations further with [quantity of ingredient customization with option to put it on the side](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/aa747fa0-f0eb-485d-9742-ce18ebf854f0). This would add a level of complexity I don't think I will be able to support in this amount of time.

Chipotle gives the option to add other items to the order. Since mealswipes only support one main item I will likely just have a confirm order button after a single item's customization.

Once an order is confirmed the next screen would have something to keep track of the order if I get server communication both ways working.

### Development Plan

#### What platform to support?
**My current plan is to make it an Android app.** I might try to use the cross-platform development capabilities of my other choices below but Apple makes things difficult as Kotlin, which is in development but available for cross-platform, [expects me to have Xcode](https://kotlinlang.org/docs/multiplatform-mobile-integrate-in-existing-app.html#create-an-ios-project-in-xcode) to test on iOS phones which is exclusive to macs.

#### Language and Integrated Development Environment (IDE) decisions
I intend to continue using [Android Studio](https://developer.android.com/studio) as my IDE for this project. I chose this because I am more familiar with it as I used it during my May term Android development class and it's Google's official Android app IDE. This means it will be up to date and have a lot of helpful documentation and helpful sources online.

I will continue using Kotlin in [Android Studio](https://developer.android.com/studio) as I did during my May term. It is [Google's top pick for Android development](https://techcrunch.com/2022/08/18/five-years-later-google-is-still-all-in-on-kotlin/) as mentioned in my [annotated bibliography](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/blob/main/docs/annotated-biblio.md). [JetBrains, the creator of Kotlin, also officially supports IntelliJ IDEA and Android Studio](https://kotlinlang.org/docs/kotlin-ide.html#intellij-idea) so this will be a stable coding environment to work in.

I also chose these options as they have support for [multiple platforms including mobile support in beta](https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html). Meaning if this project were to attempt iOS or other support one day, most of the code should be reusable. Though I am partly [risking needing to rewrite other parts of my code](https://kotlinlang.org/docs/multiplatform.html) if "migration steps" for updates are needed.

Kotlin is a good choice because I have recent familiarity with it and my research found I can use it server-side if needed as well. 
And even though multiplatform capabilities are not 100% complete, apps using Kotlin on Android are very stable as Google found they are [20% less likely to crash](https://kotlinlang.org/docs/android-overview.html).

An option I considered for a programming langauge is [Dart](https://developers.google.com/learn/topics/dart), which is what [Flutter](https://flutter.dev/) uses. And [both Dart and Kotlin work in Android Studio](https://blog.codemagic.io/dart-vs-kotlin/). This used to be the top choice for cross platform but [Kotlin is prefered for Android development](https://blog.codemagic.io/dart-vs-kotlin/). If I were to for sure push iOS development I might choose Dart over Kotlin, but for this I am satisfied with focusing on just Android but keeping the door open toward crossplatform that Kotlin provides. 

Another option is [React Native](https://reactnative.dev/). Reach Native uses JavaScript. When [comparing React Native directly against Kotlin Multiplatform](https://dzone.com/articles/react-native-vs-kotlin-a-quick-comparison), Kotlin is designed "for developing mobile applications". Using JavaScript with React Native requires [third-party libraries](https://dzone.com/articles/react-native-vs-kotlin-a-quick-comparison) to achieve the same thing. React Native offers a quick reload option to see changes but I am currently satisfied with the speed of Android Studio. This artlce also came to the conclusion that if focusing on multiplatform then React Native may be the better option. But for those focusing on Android, "you must consider Kotlin".

### Server

There are multiple potential server options such as the three major offerings such as [Microsoft Azure](https://azure.microsoft.com/en-us), [AWS](https://aws.amazon.com/), and [Google Cloud](https://cloud.google.com/). 
I was thinking Microsoft Azure as that is what we use for our Hanover accounts, but I think using something like [supabase](https://supabase.com/) to set up an online database to store the orders would be sufficient for my needs as I am just hoping to learn to get server and app communication working, I don't need or want to deal with more complicated offerings. Supabase has [more than enough storage and unlimited API calls](https://supabase.com/pricing) for me to test this app for free. It has Kotlin support as well so it meets my basic needs. Supabase also has a nice interface so far, at least for [creating the tables visually](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/769b538d-6129-4225-9c0f-c20dcd30a821). I do have some SQL experience but features like to visualize that are helpful for faster progress if I get to this.

### References ( wip, was using the wrong format. should be https://libguides.murdoch.edu.au/IEEE )

https://blog.codemagic.io/dart-vs-kotlin/   july 20th

“Chipotle - Fresh Food Fast - Apps on Google Play.” Google, play.google.com/store/apps/details?id=com.chipotle.ordering. Accessed 2 July 2023. 

“Make Your Android Application Work on IOS – Tutorial: Kotlin.” Kotlinlang, kotlinlang.org/docs/multiplatform-mobile-integrate-in-existing-app.html#decide-what-code-to-make-cross-platform. Accessed 9 July 2023. 

“Pricing.” Supabase, supabase.com/pricing. Accessed 9 July 2023. 

"Underground Order Sheet." Hanover College, https://github-production-user-asset-6210df.s3.amazonaws.com/32851596/250412801-19be7b5f-ed5d-42bb-882a-7042fcce4b22.jpg. Copied in person 24 May 2023.
