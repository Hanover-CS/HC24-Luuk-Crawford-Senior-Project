## Underground Ordering
### Project Idea
My intention for this project is to create a way for people to order ahead of time at the underground. I would like to at minimum create an interface using items from the underground as an example of how this could be useful, as a proof of concept. This would be useful as some food such as the quesadillas can take 15 minutes to make. Being able to order ahead of time means students would have less disruption to their workflow waiting around for food to be prepared. I would like to have the app communicate with a server to send the order. And if I can get that to work, it would be good to have a chef's screen to receive the orders. Another stretch goal would be to add push notifications and incorporate Hanover Outlook logins to prevent abuse.

### Main Features
1. Order an item
   - a menu to select items from
   - ability to customize item's toppings
   - confirm and submit
   

   Once that works, if I have time it would be nice to also implement these below...

2. Cloud storage
   - store orders

3. Chef's screen
   - a way to view orders in progress (at least for the chef)

4. Other
   - log in using Hanover Microsoft email
   - push notifications for customers to know when the order is complete

---

### Comparable solution (focusing on interface choices)

#### [Chipotle][link1] [1]

[Chipotle on Android][link2] loads to a welcome login screen [2]. It can be skipped to view the menu. You can start filling the cart after choosing a location.
I will make a landing screen that has a menu preview or a button to log in to Hanover email through Microsoft. The login will only be implemented if I happen to have extra time to figure out how that works.

<img src="https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/b88ffab7-d7fd-40e7-ab57-f08167a983dc" alt="Chipotle toppings example"  height="450">

Once login is confirmed, the next page of actual ordering will show up.

Chipotle's menu loads as a series of labeled pictures showing each of the kinds of items [2]. Once an item is selected, it then loads the options specific to that menu item. 

<img src="https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/aa747fa0-f0eb-485d-9742-ce18ebf854f0" alt="Chipotle toppings example"  height="450">


One option would be to just recreate the current ordering sheets with the same format with checkboxes next to each item. 

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/32851596/250412801-19be7b5f-ed5d-42bb-882a-7042fcce4b22.jpg" alt="Ordering Sheets image"  height="450">

I think a screen more similar to Chipotle would be better if I included pictures [3]. Using a scrolling list also means I can add and remove available items to fit the current menu without redesigning the interface.

Chipotle's customization of orders is done by having the customer select which items to include, with toppings able to be split into half portions when two are selected [2]. 


<img src="https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/76fddee4-b6bc-4153-ac04-6c2a0af3affb" alt="half portions Chipotle displayed"  height="450">



I would like to have a list of toppings presented similarly once an item is chosen. Checkboxes next to the options or the topping buttons are highlighted if selected.

Chipotle takes the possible customizations further with the quantity of ingredient customization with the option to put it on the side [2]. 


<img src="https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/aa747fa0-f0eb-485d-9742-ce18ebf854f0" alt="side option displayed"  height="450">
<div style="clear: both;"></div>


This would add a level of complexity I don't think I will be able to support in this amount of time.

Chipotle gives the option to add other items to the order. Since mealswipes only support one main item I will likely just have a confirm order button after a single item's customization.

Once an order is confirmed the next screen would have something to keep track of the order with cloud storage.

This is what I have in mind right now, in terms of the interface choices I have made:

![undergroundAppDraftCropped](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/5ae39d76-aec3-47c2-b9fb-a7024692bebe)

I am likely going to use more realistic pictures than the emojis pictured above.



### Development Plan

#### What platform to support?
**My current plan is to make it an Android app.** I might try to use the cross-platform development capabilities of my other choices below but Apple makes things difficult as Kotlin, which is in development but available for cross-platform, [expects me to have Xcode][link4] to test on iOS phones which are exclusive to macs [4].

#### Language and Integrated Development Environment (IDE) decisions
I intend to continue using [Android Studio][link5] as my IDE for this project. I chose this because I am more familiar with it as I used it during my May term Android development class and it's Google's official Android app IDE [5]. This means it will be up to date and have a lot of helpful documentation and helpful sources online.

I will continue using Kotlin in [Android Studio][link5] as I did during my May term. It is [Google's top pick for Android development][link6] [5][6]. [JetBrains, the creator of Kotlin, also officially supports IntelliJ IDEA and Android Studio][link7] so this will be a stable coding environment to work in [7].

I also chose these options as they have support for [multiple platforms including mobile support in beta][link8] [8]. Meaning if this project were to attempt iOS or other support one day, most of the code should be reusable. Though I am partly [risking needing to rewrite other parts of my code][link9] [9] if "migration steps" for updates are needed.

Kotlin is a good choice because I have recent familiarity with it.
And even though multiplatform capabilities are not 100% complete, apps using Kotlin on Android are very stable as Google found they are [20% less likely to crash][link10] [10].

An option I considered for a programming language is [Dart][link11], which is what [Flutter][link12] uses [11][12]. And [both Dart and Kotlin work in Android Studio][link13] [13]. This used to be the top choice for cross-platform but [Kotlin is preferred for Android development][link13] [13]. If I were to for sure push iOS development I might choose Dart over Kotlin, but for this, I am satisfied with focusing on just Android but keeping the door open for cross-platform possibilities which Kotlin supports. 

Another option is [React Native][link14] [14]. Reach Native uses JavaScript. When [comparing React Native directly against Kotlin Multiplatform][link15], Kotlin is designed "for developing mobile applications" [15]. Using JavaScript with React Native requires [third-party libraries][link15] to achieve the same thing [15]. React Native offers a quick reload option to see changes but I am currently satisfied with the speed of Android Studio. This article also concluded that if focusing on multiplatform then React Native may be the better option. But for those focusing on Android, "you must consider Kotlin". 

### Cloud Storage

There are multiple potential cloud infrastructure providers including major offerings such as [Microsoft Azure][link16], [AWS][link17], and [Google Cloud][link18] [16][17][18]. 
Google also offers [Firebase][link19], which is what I intend to use [19]. This is my top choice because the setup was the most clear to follow even including video tutorials using the same setup I have with Kotlin and Android Studio, making it easier for me to implement correctly. [Google Cloud][link20] can be used for storage including realtime databases but [Firebase seems to be better for focusing on realtime databases][link21] [20][21]. I expect it would be a realtime database to ensure the flow of orders works well. Another option I explored and made an attempt to use was [Supabase][link22] [22]. I liked it as it seemed simple to use with a nice interface but I had trouble getting it set up with Kotlin in Android Studio so I swapped to Firebase which was easier to follow.

## Features I intend to deliver

1. Ability to pick from a list of items
   - Select the item you want to customize
   - Example items from Underground
   - Uses menu that's on Firebase
     
3. Ability to select toppings/side
   - Available toppings for item shown
   - Select and unselect toppings

4. Ability to submit an order (with your toppings/side)
   - Sends personalized order to Firebase

Since I've restarted and now have to rewrite my code using Jetpack Compose, I'm hesitant to push this further with more features not knowing what issues I may run into. 

If/when I get those completed, I would love to add these **stretch goals** to let users keep track of their order.

4. Ability to track order(s)
   - View your order and the status of it
   - Or atleast a view of all the orders

5. Push notification
   - Notification when the order is complete


### References

[1] “Mexican Food - Restaurant & Catering - Chipotle Mexican Grill,” Chipotle. https://www.chipotle.com (accessed Aug. 31, 2023).

[2] “Chipotle - Fresh Food Fast - Apps on Google Play.”. Available: https://play.google.com/store/apps/details?id=com.chipotle.ordering&hl=en_US (accessed Jul. 2, 2023).

[3] C. Luuk, “Photo of Underground Ordering Sheet.” May 24, 2023. Available: https://github-production-user-asset-6210df.s3.amazonaws.com/32851596/250412801-19be7b5f-ed5d-42bb-882a-7042fcce4b22.jpg (accessed Jul. 22, 2023)

[4] “Make your Android application work on iOS – tutorial, Kotlin,” Kotlin Help. https://kotlinlang.org/docs/multiplatform-mobile-integrate-in-existing-app.html#create-an-ios-project-in-xcode (accessed Jul. 9, 2023).

[5] “Download Android Studio & App Tools,” Android Developers. https://developer.android.com/studio (accessed Aug. 31, 2023).

[6] F. Lardinois, “Five years later, Google is still all-in on Kotlin,” TechCrunch, Aug. 18, 2022. https://techcrunch.com/2022/08/18/five-years-later-google-is-still-all-in-on-kotlin/ (accessed Sep. 06, 2023).

[7] “IDEs for Kotlin development, Kotlin,” Kotlin Help. https://kotlinlang.org/docs/kotlin-ide.html#intellij-idea (accessed Aug. 31, 2023).

[8] “Get started with Kotlin Multiplatform for mobile, Kotlin,” Kotlin Help. https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html (accessed Aug. 31, 2023).

[9] “Kotlin Multiplatform, Kotlin,” Kotlin Help. https://kotlinlang.org/docs/multiplatform.html (accessed Aug. 31, 2023).

[10] “Kotlin for Android, Kotlin,” Kotlin Help. https://kotlinlang.org/docs/android-overview.html (accessed Aug. 31, 2023).

[11] “Dart,” Google for Developers. https://developers.google.com/learn/topics/dart (accessed Aug. 31, 2023).

[12] “Flutter - Build apps for any screen.” //flutter.dev/ (accessed Aug. 31, 2023).

[13] S. Biswas, “Dart vs Kotlin: detailed comparison,” Codemagic blog, Feb. 02, 2021. Available: https://blog.codemagic.io/dart-vs-kotlin/ (accessed Jul. 20, 2023).

[14] “React Native · Learn once, write anywhere.” https://reactnative.dev/ (accessed Aug. 31, 2023).

[15] Nanthini, “React Native vs Kotlin: A Quick Comparison - DZone,” dzone.com. Available: https://dzone.com/articles/react-native-vs-kotlin-a-quick-comparison (accessed Jul. 20, 2023).

[16] “Cloud Computing Services, Microsoft Azure.” Microsoft Azure. Available: https://azure.microsoft.com/en-us (accessed Aug. 31, 2023).

[17] “Cloud Computing Services - Amazon Web Services (AWS),” Amazon Web Services, Inc. Available: https://aws.amazon.com/ (accessed Aug. 31, 2023).

[18] “Cloud Computing Services,” Google Cloud. Available: https://cloud.google.com/ (accessed Aug. 31, 2023).

[19] “Firebase, Google’s Mobile and Web App Development Platform,” Firebase. https://firebase.google.com/ (accessed Sep. 06, 2023).

[20] “Cloud Computing Services,” Google Cloud. https://cloud.google.com/ (accessed Sep. 06, 2023).

[21] J. Clark, “Firebase vs Google Cloud - What are the differences?,” Jun. 09, 2022. https://blog.back4app.com/firebase-vs-google-cloud/ (accessed Sep. 04, 2023).

[22] “Pricing & fees,” Supabase. https://supabase.com//pricing (Accessed Sep. 11, 2023)

[link1]: https://www.chipotle.com/
[link2]: https://play.google.com/store/apps/details?id=com.chipotle.ordering&hl=en_US

[link4]: https://kotlinlang.org/docs/multiplatform-mobile-integrate-in-existing-app.html#create-an-ios-project-in-xcode
[link5]: https://developer.android.com/studio
[link6]: https://techcrunch.com/2022/08/18/five-years-later-google-is-still-all-in-on-kotlin
[link7]: https://kotlinlang.org/docs/kotlin-ide.html#intellij-idea
[link8]: https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html
[link9]: https://kotlinlang.org/docs/multiplatform.html
[link10]: https://kotlinlang.org/docs/android-overview.html
[link11]: https://developers.google.com/learn/topics/dart
[link12]: https://flutter.dev/
[link13]: https://blog.codemagic.io/dart-vs-kotlin/
[link14]: https://reactnative.dev/
[link15]: https://dzone.com/articles/react-native-vs-kotlin-a-quick-comparison
[link16]: https://azure.microsoft.com/en-us
[link17]: https://aws.amazon.com/
[link18]: https://cloud.google.com/
[link19]: https://firebase.google.com/
[link20]: https://cloud.google.com/
[link21]: https://blog.back4app.com/firebase-vs-google-cloud/
[link22]: https://supabase.com/
