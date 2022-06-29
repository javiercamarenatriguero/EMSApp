# EnergyApp App
Wedding application in order to get basic information about the location, date and some other details.
In addition to that, this is a tool for guests in so as to they could upload songs for the disco and taken photos during the day.
Following are some of the features available on the app:

* Wedding basic information: Date, time, people and locations
* Maps points: See where the different locations are pointed on Google Maps tool
* Songs: See the playlist for the disco and add a song suggestion
* Pictures: See the gallery of the weeding and upload your photos as a guest

---
## Technical details
* Clean code architecture (pending create Android modules for each layer)
* MVVM Design patterns & UI State handling
* 100% Kotlin & Jetpack Compose
* Kotlin Coroutines - Flows
* Hilt for Dependency Injection
* Compose Navigation
* Google Maps for Jetpack Compose
* Firebase services:

    * -> Crashlytics
    * -> Analytics
    * -> Performance
    * -> Firestore (images)
    * -> Realtime database (songs)

* Uploaded on Play Store (Until 10th of June)

---
### Screenshots
![Demo](https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/energyapp.gif)

<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_1.png" width="200">
<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_2.png" width="200">
<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_3.png" width="200">
<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_4.png" width="200">
<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_5.png" width="200">
<img src="https://bitbucket.org/javi_hetfield/energyapp/raw/master/screenshots/screen_6.png" width="200">
---

### External Libraries
* [Firebase](https://firebase.google.com/docs/android/setup): Crashlytics / Firebase Storage / Firebase realtime database
* [Accompanist](https://github.com/google/accompanist): Complementary library for Jetpack Compose
* [mockk](https://mockk.io/ANDROID.html): Mock Unit test library
---

### Requirements
* Min. Android SDK: 26
* Target Android SDK: 31
* Kotlin 1.6.10
---

### Version
* v1.6

---
### IDE tools
* Android Studio Chipmunk | 2021.2.1 Patch 1
* Bitbucket Repository

---
### Test
* Unit & Instrumentation Test
* Emulator (Android SDK 28/31)
* Xiaomi Note 5 / Realme GT (Android SDK 27/31)

---
### TODOs
* Create Android Modules for Domain & Data Layers (Currently just isolated in packages)
* Improvements on Images visualization: Pagination, Error Management...
* Stronger security for Firebase access
* Add more tests

---
### Development & Design
* Author: Javier Camarena
* Contact: javier.camtri@gmail.com