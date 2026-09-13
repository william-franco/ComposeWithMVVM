# Compose With MVVM

Sample Android app that lists and details users from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com) using Jetpack Compose and feature-based MVVM. Koin wires ViewModels, repositories, and Ktor-based `HttpService`; `ConnectionService` supports connectivity-aware behavior. Settings persist the dark theme with DataStore. UI loading states follow shared `StatePattern` and `ResultPattern` helpers in `common/patterns`.

## Structure

```mermaid
flowchart TB
  RoutesApp --> UserRoute
  RoutesApp --> SettingRoute
  UserRoute --> UserViewModel
  UserViewModel --> UserRepository
  UserRepository --> HttpService
  HttpService --> JSONPlaceholder[JSONPlaceholder API]
  SettingRoute --> SettingViewModel
  SettingViewModel --> SettingRepository
  SettingRepository --> DataStore
```

## Stack

| Technology | Version |
|------------|---------|
| Android Gradle Plugin | 9.4.0 |
| Kotlin | 2.2.10 |
| Compose BOM | 2026.02.01 |
| Koin | 4.2.2 |
| Navigation Compose | 2.9.3 |
| Ktor Client | 3.1.3 |
| DataStore | 1.1.7 |
| compileSdk / targetSdk | 37 |
| minSdk | 29 |
| JVM | 21 |

## Architecture

Feature-based MVVM with Koin for dependency injection.

```
src/
├── common/
│   ├── constants/
│   ├── patterns/
│   └── services/
├── di/
├── design/theme/
├── routes/
└── features/
    ├── users/
    └── settings/
```

## ScreenShots

| Image 1 | Image 2 | Image 3 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-1.png) | ![App Screenshot](assets/screenshots/screen-2.png) | ![App Screenshot](assets/screenshots/screen-3.png) |

| Image 4 | Image 5 | Image 6 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-4.png) | ![App Screenshot](assets/screenshots/screen-5.png) | ![App Screenshot](assets/screenshots/screen-6.png) |

## Commits

```
git add . && git commit -m ":rocket: Initial commit." && git push
git add . && git commit -m ":building_construction: Added initial project architecture." && git push
git add . && git commit -m ":building_construction: Update project architecture." && git push
git add . && git commit -m ":memo: Updated project documentation." && git push
git add . && git commit -m ":memo: Updated code documentation." && git push
git add . && git commit -m ":white_check_mark: Added feature xyz." && git push
git add . && git commit -m ":wrench: Fixed xyz usage." && git push
git add . && git commit -m ":heavy_minus_sign: Removed xyz." && git push
git add . && git commit -m ":memo: Adjusted project imports." && git push
git add . && git commit -m ":arrow_up: Updated dependencies." && git push
git add . && git commit -m ":arrow_down: Removed dependencies." && git push
git add . && git commit -m ":wastebasket: Removed unused code." && git push
git add . && git commit -m ":test_tube: Added test functionality xyz." && git push
git add . && git commit -m ":construction_worker: Building in progress." && git push
git add . && git commit -m ":construction_worker: Added CI build system." && git push
```

## License

[MIT License](https://opensource.org/licenses/MIT)

Copyright (c) 2026 William Franco.
