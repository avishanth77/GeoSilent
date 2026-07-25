# GeoSilent AI

Android MVP scaffold for location and time based sound-profile automation.

## Included
- Kotlin + Jetpack Compose + Material 3
- MVVM/Clean Architecture boundaries
- Hilt dependency injection
- Rule model and repository contract
- Dashboard, rules, history, and settings surfaces
- Boot receiver seam for re-registering automation
- Min SDK 29, target SDK 35

## Build
1. Open this folder in Android Studio Ladybug or newer.
2. Let Gradle sync.
3. Run `./gradlew assembleDebug`.
4. Install `app/build/outputs/apk/debug/app-debug.apk`.

## Required setup before production
- Add a Google Maps/Places API key through `local.properties` and manifest metadata.
- Replace `InMemoryRuleRepository` with Room entities/DAOs and DataStore settings.
- Implement geofences with staged foreground/background location permission onboarding.
- Implement WorkManager schedule evaluation and calendar/Wi-Fi/charging receivers.
- Implement `SoundProfileController` with AudioManager and NotificationManager policy-access checks.
- Persist profile snapshots with timestamps and ignore restoration when a newer manual profile change is detected.
- Add execution logs, migrations, conflict resolution, and unit tests for evaluator/scheduler/restoration.

## Android limitations
DND requires Notification Policy access. Background location requires staged user permission. Exact alarms and notification permission are user-controlled. The app should explain unavailable actions rather than pretending they worked.

## Phase 2 intentionally excluded
Bluetooth, driving mode, AI suggestions, widgets, statistics, login, backups, biometric lock, complex rule groups, app launching, and Wi-Fi/Bluetooth toggling.
