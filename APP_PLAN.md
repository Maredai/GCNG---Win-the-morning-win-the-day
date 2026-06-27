# GCNG Alarm App: Features and Process Plan

## 1. App Overview
The GCNG Alarm App is a privacy-first morning routine application designed to ensure users wake up on time by requiring them to complete specific interactive tasks to dismiss their alarms. All sensitive tasks (camera, microphone) are processed entirely on-device to maintain strict user privacy.

## 2. Core Features

### A. Authentication and Onboarding
* Secure email and password login, alongside Google OAuth integration.
* Educational onboarding flow detailing the privacy-first architecture and requesting necessary device permissions (Camera, Microphone, Notifications).
* Initial user assessment to tailor the morning routine experience (includes a skip option for frictionless entry).

### B. Task-Based Alarm Dismissal
Alarms cannot be dismissed with a simple tap; they require cognitive or physical engagement. Tasks are divided into categories:
* Mental and Logic: Math Solver, Memory Match, Verse Copy.
* Physical Actions: Smile Cam, Head Shake, Rapid Blink, 10 Jumps.
* Real World Interaction: Scan Random Item, Color Finder, Audio Affirmation.

### C. Granular Alarm Configuration
* Grouped ringtone selections categorized by mood (Standard, Nature and Calm, Electronic).
* Task assignment per alarm.
* Difficulty scaling (Easy, Medium, Hard, Auto-Scaling).

### D. Gamification and Statistics
* Streak Tracking: Monitors consecutive days of successful wake-ups.
* Weekly Overview: Calculates average wake times and task success rates.
* Task Analytics: Visual progress bars indicating the user's most frequently used and successful tasks.

## 3. User Journey and Process Flow

### Step 1: Initial Setup
1. User launches the application and is greeted by the Splash Screen.
2. User creates an account or logs in via the Auth Screen.
3. The application explains the privacy model and requests permissions via the Onboarding Screen.
4. User completes or skips the routine assessment on the Assessment Screen.

### Step 2: Daily Management
1. User lands on the Home Dashboard showing their current streak and active alarms.
2. User toggles existing alarms or creates a new one.
3. During creation, the user selects the time, categorized ringtone, and a wake-up task on the Alarm Setup Screen.

### Step 3: The Wake-Up Process
1. Alarm triggers at the designated time on the Alarm Ring Screen.
2. User engages with the assigned task. Processing (such as facial recognition for "Smile Cam") occurs securely on-device.
3. Upon successful completion, the alarm is dismissed via the Task Success Screen.

### Step 4: Review and Adjustment
1. User reviews their weekly performance on the Stats Screen.
2. User adjusts difficulty or task types via the Settings Screen to better suit their evolving routine.
