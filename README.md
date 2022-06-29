# EnergyApp App
EnergyApp application consists of a basic tool in order to show EMS information related to the power suppliers & energy consumption.
Following are some of the features available on the app:

* Charged/Discharged energy of the Quasar charger in kWh
* Sources' supplied power to the Building in realtime (Energy Live Data) in kW
* Statistics data about percentage contribution of each power supplier to the building.
* Start / Stop connection controls
* Line chart visualization of the cached data (Energy Historical Data)
* Emulated fake data source (QuasarDataSourceAdapter) such as a Bluetooth/REST/DDBB adapter. It provides with random data of power sources

---
## Technical details
* Clean code architecture
* Android Modules for each clean code's layer in order to keep each layer isolated from library dependencies
* MVVM Design patterns & UI State handling
* 100% Kotlin & Jetpack Compose
* Kotlin Coroutines - Flows
* Hilt for Dependency Injection
* Compose Navigation
* Unit & UI Test
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
* Add more UI Tests for screens with HiltAndroidTest integration
* Improvements on Images visualization

---
### Development & Design
* Author: Javier Camarena
* Contact: javier.camtri@gmail.com