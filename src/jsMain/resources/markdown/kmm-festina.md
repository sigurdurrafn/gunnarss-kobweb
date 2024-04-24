



Multiplatform at Festina
2 November 20213 December 2021 Siggi Gunnarsson

Development	

Festina are makers of a range of “hybrid” watches that look like normal wrist watches but have many functions of smart watches; activity tracking, notifications etc. We launched this technology initially under the Kronaby brand. Today, it powers watches from Lotus, Jaguar and Festina. We recently started using Kotlin Multiplatform to speed up development and reduce bugs. Read on for our experience with adopting this technology and our progress so far.

Hybrid watches are different from other smart watches in how they work. Our current watches run for around two years on a single battery and we expect each watch to be used for several years. To make this possible, we power our watches with a low power processor running a real time operating system. They rely on apps on connected Android and iOS phones for much of their additional smarts like keeping track of time and weather.

After a user sets the watch up for the first time, we don’t expect them to interact with our apps much. The software “ticks” quietly for years without the user having to think about it. Simply put, the watch works like a regular watch and unlike a smart watch. Due to this, we tend to think of our software like clockwork. 

Scaling up

A while ago, we started planning our next product line. We’re excited to share more details about these products later but suffice to say they are our most ambitious yet. Implementing all the planned features while keeping the code quality high will be a challenge, but something we must accomplish. Even with a larger budget, scaling up is notoriously hard to do in software. Readers of The Mythical Man-Month will know this has been known in software management for several decades.

So how can we add several powerful new features to our apps while keeping our code quality high and bug count low? There’s quite a lot of “business logic” in our apps. We read data from the watch and connected services, process, modify and sync them. If we could reuse this logic between our apps it would save us a lot of work in writing and testing code. We could then focus on the problems at hand, rather than doing our work one for each app and then synchronizing the solutions. This would be a huge win so the iOS and Android teams went to work, figuring this out. 

We considered available options but a new technology soon emerged as a clear winner.

Kotlin Multiplatform

We are obviously not the first team to come to the conclusion that we shouldn’t write all our code twice. Unfortunately code sharing between Android and iOS has nuances that make this task harder than it seems.

Depending on your multi-platform approach, you may need to train developers in a new programming language like C++, JavaScript or Dart. This can be unpopular and lead to staffing issues. Another common issue is that most approaches render the apps UI in a web-view or in their own rendering engine. This leads to UI that doesn’t feel “native” and does not mesh with the other apps on the user’s phone. 

As we were evaluating our options, a new multi-platform technology was emerging, Kotlin Multiplatform Mobile. The premise is this: You take some of your preexisting code modules written for the Android app and make it work for iOS. Then compile that code into a library that can be used in both apps.

https://kotlinlang.org/assets/images/index/mobile-platforms.svg

Copyright Jetbrains

This technology avoids the pitfalls of other multi-platform approaches. The UI is implemented natively on each platform, so it no risk of it looking “wrong”. There’s also no need to re-train developers in new programming languages since all code is in Kotlin which is already happily used by our Android developers. Kotlin is a popular and modern language that’s easy for Java and Swift developers to pick up. importantly, there’s no need to go “all in” at any point. Teams decide which modules are useful as multi platform and then selectively refactor and make them available to the iOS app.

This looks very simple on paper. In practice, there are many details to consider and decisions to make. We’ll discuss them in more detail in later updates but what follows is an overview of how things have gone so far.

Getting started

The first module to get a multi-platform treatment was our analytics library. Analytics are an important part of our apps’ long term health. It’s our best signal to what works well and what does not. Analytics require good synchronization between the apps because the same features need to be monitored between apps. We had already worked on sharing analytics between our apps but it had never been integrated into the iOS app. Analytics is a good candidate for code sharing because despite being important, it’s not very popular to work with. Developers were happy to move this responsibility into a multi-platform library.

We started refactoring the Android analytics module into a Kotlin Multiplatform library. The work was straight forward and the first proof of concept was ready within a week. We started by converting some legacy Java code to Kotlin, then moved all references to Android libraries behind an interface.

Surprisingly, the first result was a better Android app. We removed old java code and the updated code only relied on interfaces instead of specific platform libraries. This lead to both more readable and testable code. Before even integrating code into the iOS app, we were seeing unexpected benefits.

This was very early in the development of KMM so there were a few sharp corners, especially around tooling and integration into the iOS app. But overall this effort was a success. 

We decided to next tackle the “watch” module which contains much of the core business logic of our app. This was a larger undertaking but our experience from the earlier work guided our way. After some work we were happy to see we had the same result as with analytics; a codebase that was cleaner and more testable that what we had started with. Due to this focused cleanup the effort would probably have been worth it even if we had only used it in the Android app.

Current status

We’re very happy with our progress so far. The project is on schedule. We’re communicating well and we have better insight into the workings of both apps.

As a result we have merged our two app teams into one. A few months after starting this project, all our iOS developers have made contributions to the shared Kotlin code base.

We’ve implemented more than we could have with the old two team structure and we’re planning on using this approach for more of our products. We’re excited in where this technology is going and as KMM soon enters beta status, we’re optimistic about the future.

More on that later!


Festina Sweden
people@festinasweden.se

