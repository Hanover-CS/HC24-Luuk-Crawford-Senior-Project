# Biblio

## Similar apps

### Example 1: [Wendy's](https://order.wendys.com/categories?site=menu&lang=en_US)

Wendy's is a popular fast food restaurant that has ordering online capabilities, ranging from ordering ahead of time for dine-in, takeout, or even delivery.
As seen below, the mobile browser and app look pretty much the same, with needing to click an item to customize and add to the cart. With the PC browser, you can click add to cart, and it will give the option between combo or single item.

[Wendy's browser][1] example:
[Wendy's PC ordering screenshot](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/c66e8ef7-ba24-4c58-b5c0-6485cb900e8d)

[Wendy's browser][1] example (view from mobile):
[Wendy's mobile browser view screenshot](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/2c80eb13-3fe6-4da7-85f0-2dbb5049ad71)

Wendy's also has a version on the [Google Play][2] and [Apple's app store][3].
[Wendy's app view screenshot](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/67cf3bfd-43c7-40b5-a942-7f0cfe5f7462)


#### Thoughts:
  Wendy's layout is one way I could try and organize the app. Having the menu items in different slots would likely make it easier to make menu changes in the future. Having this much fancy-looking customization to orders and fancy looking interface is something I don't think I should aim for in this amount of time. I may make the menu a scroll of the options. Toppings and such can show up after selecting an item, similar to the app store image with checkboxes to optional toppings and such. Pictures would be nice, so I would add some temporary ones. I might prefer a smaller layout. It would be cool if I could just emulate the [current ordering sheets](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/19be7b5f-ed5d-42bb-882a-7042fcce4b22) into digital format since people are already familiar with those. 

### Example 2: [Chipotle](https://www.chipotle.com/) 

Chipotle is a good example because they have food similar to the underground such as their [quesadilla and burrito options, viewed from the mobile browser](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/353d2fc6-e825-4613-a81b-7232145e9a7b). For example, on the burrito up to 2 meats can be selected, and if 2 are selected they are [half portions visually](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/fbe3fa9d-8ac7-4149-b638-e803b37f4629). Chipotle apps available in [Google Play](https://play.google.com/store/apps/details?id=com.chipotle.ordering) and [Apple's app store](https://apps.apple.com/us/app/chipotle-fresh-food-fast/id327228455).

#### Thoughts:
  I like the process of Chipotle's ordering more than Wendy's because instead of assuming what people want it requires the person to select what should be added to the order, the same way the [current ordering sheets](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/19be7b5f-ed5d-42bb-882a-7042fcce4b22) work. The current ordering sheets do not include the burrito because for those you need to talk to the chef working there like Subway.

### Example 3: [Taco Bell](https://www.tacobell.com/food)

Taco Bell also has burritos and most orders are customizable. Items can be taken as is or customized to the customer's liking. Taco Bell seems to have more customization than the other competitors. They let the customer choose how much of each ingredient and swap them with others. For example the [beef slot has 10 options](https://github.com/Hanover-CS/HC24-Luuk-Crawford-Senior-Project/assets/32851596/5be3b2bd-814c-4f92-b772-28643185b69c). They also include the option to put some of the topping options on the side. App available in [Google Play](https://play.google.com/store/apps/details?id=com.tacobell.ordering&hl=en) and [Apple's app store](https://apps.apple.com/us/app/taco-bell-fast-food-delivery/id497387361).


### Thoughts:
  There is a lot of customization that the Underground doesn't currently offer. I don't expect to reach this level of customization because I don't think I'll have time to add a quantity or on the side option. Maybe I could have a spot for people to add a note on the order but that would make things more difficult than it already is.


## Language & technology options

### Web app development
Web app development would allow any web-using device to use the app.

The downside is I would need to spend a lot more time to get started because I don’t have experience with this and would likely run into more issues starting from nothing. I would rather resume learning mobile app development than start something from scratch for a solo project.

### Multiplatform mobile development [top options](https://kotlinlang.org/docs/cross-platform-frameworks.html#kotlin-multiplatform-mobile)
Multiplatform development means most code I make should also work for multiple platforms without needing to have completely different versions. If I pursue mobile development, this means when I’m testing and working with an Android (for example), it is coded in an environment that should also work with iOS devices without needing to rewrite too much code. This makes supporting multiple platforms easier as I wouldn't need to learn and use two different languages at a time for iOS and Android.

This will be useful if I have time to attempt to make it work on iOS, though I don't think I will be able to unless I do more research to figure out a way around Xcode being Mac only.

#### [Kotlin Multiplatform Mobile](https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html)
- Created by [JetBrains](https://www.jetbrains.com/) in 2011
- Kotlin is a multiplatform programming language that ["used by over 60% of professional Android developers"](https://developer.android.com/kotlin) and [Google's top pick for Android development](https://techcrunch.com/2022/08/18/five-years-later-google-is-still-all-in-on-kotlin/)
- Kotlin allows developers to use the same code between platforms. [For example macOS, iOS, Linux Windows, and Android](https://kotlinlang.org/docs/native-overview.html#target-platforms) can share code meaning less rewriting per platform for [core layers, business logic, presentation logic, and more](https://kotlinlang.org/docs/multiplatform-mobile-faq.html).
- Kotlin multiplatform for mobile is currently in [beta](https://kotlinlang.org/docs/components-stability.html#stability-levels-explained).
- [JetBrains officially supports IntelliJ IDEA and Android Studio](https://kotlinlang.org/docs/kotlin-ide.html#intellij-idea). Other IDEs have community support as well.
- Also good for [server side](https://kotlinlang.org/docs/server-overview.html#frameworks-for-server-side-development-with-kotlin) and scalability. 

#### [Flutter](https://flutter.dev/)
- Flutter is an option that was created by Google in 2017.
- It uses a language Google developed called [Dart](https://developers.google.com/learn/topics/dart).
- Some of the [key features](https://kotlinlang.org/docs/cross-platform-frameworks.html#react-native) noted are that it has a feature that can let you see what your changes are doing without needing to recompile.
- It also has its own rendering engine instead of relying on web browser technology.
- Being in the Google ecosystem means easier access to features such as Google's Material Design which can be useful for making app interfaces.

#### [React Native](https://reactnative.dev/)
- Created by [Meta](https://about.meta.com/) in 2015.
- This option uses JavaScript.
- This option also has a fast refresh feature to see changes quickly.
- React Native focuses on UI.
- It uses [Flipper](https://fbflipper.com/) which helps with debugging on mobile.



[1]: https://order.wendys.com/categories?site=menu&lang=en_US
[2]: https://play.google.com/store/apps/details?id=com.wendys.nutritiontool
[3]: https://apps.apple.com/us/app/wendys/id540518599
