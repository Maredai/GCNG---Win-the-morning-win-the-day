# GCNG Alarm App

## Overview
GCNG is a privacy-first morning routine and alarm application designed to ensure you wake up on time. Instead of simply tapping a button to dismiss your alarm, GCNG requires you to complete interactive tasks (both mental and physical) to turn it off. All sensitive tasks, such as camera and microphone usage, are processed entirely on-device to maintain strict user privacy.

## Key Features

### 🧠 Task-Based Alarm Dismissal
Alarms require cognitive or physical engagement to dismiss:
* **Mental & Logic**: Math Solver, Memory Match, Verse Copy.
* **Physical Actions**: Smile Cam, Head Shake, Rapid Blink, 10 Jumps.
* **Real World**: Scan Random Item, Color Finder, Audio Affirmation.

### 🔒 Privacy-First
All processing for tasks (like facial recognition for the Smile Cam or audio for Affirmations) happens locally on your device. Your data never leaves your phone.

### ⚙️ Granular Configuration
* Categorized ringtones (Nature & Calm, Electronic, Standard).
* Assign specific tasks per alarm.
* Toggle vibrate and snooze settings easily.
* Select which days the alarm should repeat.

### 📈 Gamification & Statistics
* **Streak Tracking**: Keep track of consecutive days of successful wake-ups.
* **Weekly Overview**: View your average wake time and task success rate.
* **Favorite Tasks**: See which tasks you use the most and how successful you are at completing them.

## How to Use

1. **Dashboard**: Upon opening the app, you will see your current wake-up streak and a list of your alarms. The top header displays the current date and highlights the current day of the week.
2. **Creating an Alarm**: Tap the `+` floating action button to create a new alarm.
3. **Configuration**:
    * Set the time (AM/PM).
    * Choose which days the alarm should repeat.
    * Toggle vibrate and snooze options.
    * Select your preferred alarm sound from various categories.
4. **Alarm Trigger**: When the alarm goes off, you will be prompted to complete your selected task. You cannot dismiss the alarm until the task is successfully completed.
5. **Statistics**: Navigate to the Stats screen to review your weekly performance, including your average wake time and task success rate.

## Installation / Setup

1. Open Android Studio.
2. Select **Open** and choose the directory containing this project.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical Android device.
