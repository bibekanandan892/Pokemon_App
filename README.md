
# Pokemon using Jetpack Compose

Kotlin 1.9.23, Jetpack Compose, Hilt, Clean Architecture, Room DB, Ktor Client, Lottie, Paging 3, and more...
## APK Download

You can download the APK for the app [here](https://drive.google.com/file/d/1lB2IEMpsfbJ96cF0Mo-nVXDUYOG-yHW6/view?usp=sharing).

## Project Video
#### Portrait
![screen-20240804-192346](https://github.com/user-attachments/assets/d19da514-f7e4-44b4-bdda-10de6fbbb7e1)
#### landscape
![screen-20240804-211018-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/916599f6-cb97-48c7-ab43-4cd76f0f65c7)


## Screenshots

### Mobile
#### Portrait
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/d4b026b9-5d14-4505-ba2d-b964073fe882" /></td>
    <td><img src="https://github.com/user-attachments/assets/d4b026b9-5d14-4505-ba2d-b964073fe882" /></td>
    <td><img src="https://github.com/user-attachments/assets/6a725a3b-979b-4827-8742-2554336026d4"/></td>
    <td><img src="https://github.com/user-attachments/assets/e14d4f57-d88b-4a34-9d83-2741789c52a4"/></td>
    <td><img src="https://github.com/user-attachments/assets/4c11ac08-72f2-41b6-b967-a7e774ba6ff7"/></td>
  </tr>
</table>


#### landscape
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/5958fdb1-8f00-4b64-a405-52a4d0445ec0" /></td>
    <td><img src="https://github.com/user-attachments/assets/16cf364e-67e6-4b34-a180-313fbf701c97" /></td>
  </tr>
</table>

## Overview

This project showcases a Pokémon app built with Jetpack Compose, leveraging a variety of modern technologies and best practices.


### Project Structure
<img src="https://github.com/user-attachments/assets/f054f579-d225-49d8-a696-23a535eb765a" />


* **Clean Architecture:** Utilizes the modern Android approach of building apps with Clean Architecture and MVVM.
* **Offline-First Approach:** Caches data in Room DB when the internet connection is not available.
* **Network Observer:** Continuously observes network state and handles network changes.
* **Coil Image Loading:** Loads images using Coil, with or without loading animation.
* **Lib Versions Catalog:** Manages dependencies and their versions using `lib.versions.toml`.
* **Lottie Animations:** Uses Lottie for network animations.

## Built With

* **Android Studio Koala:** IDE used for development.
* **Kotlin:** Primary programming language.
* **KSP:** Kotlin Symbol Processing API for Hilt.
* **Jetpack Compose:** UI Toolkit for building native UIs.
* **Jetpack Libraries:** Includes Lifecycle, ViewModel, Navigation Compose, etc.
* **Hilt:** Dependency Injection framework.
* **Coil:** Image loading library.
* **Ktor Client:** Networking and API management.
* **Kotlin Coroutines & Flow:** For asynchronous tasks and data management.
* **Paging 3:** For efficient data paging and loading.
* **Room DB:** For local database storage and caching.

## Device Support

* **Landscape and Portrait Modes:** Supports both landscape and portrait orientations.
