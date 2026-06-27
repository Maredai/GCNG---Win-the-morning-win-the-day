# Design Specification: GCNG Alarm App

This document outlines the design principles, visual hierarchy, and UI component styling implemented in the GCNG privacy-first alarm application. 

## 1. Visual Theme and Color Palette
* **Base Theme**: The application utilizes a Light Mode base to provide a clean, modern, and accessible user experience.
* **Color System**: Built on Material Design 3 (M3).
  * **Primary**: Used for key interactive elements (Log In buttons, emphasized text like "Welcome Back" and section headers).
  * **Surface / Surface Variant**: Used for background layering and card containers to establish a subtle visual hierarchy without overwhelming the user.
  * **On-Surface Variant**: Applied to secondary text (descriptions, inactive states) to maintain readability while reducing cognitive load.

## 2. Layout and Spacing
* **Rule of Thirds & Negative Space**: Screens, particularly the Auth Screen, are designed with generous negative space to avoid a cramped interface. Elements are positioned considering the rule of thirds, anchoring the main focal points (logo, greeting, and primary actions) effectively across the screen height.
* **Padding & Margins**: A consistent 16dp to 24dp spacing system is applied between major functional blocks.

## 3. Component Styling
* **Buttons**: 
  * Primary actions utilize elevated filled buttons with a 16dp rounded corner radius and a standard 56dp height for optimal touch targets.
  * Secondary actions (like "Continue with Google" or "Skip") use outlined styles or standard text buttons to deprioritize them visually while maintaining accessibility.
* **Inputs**: Text fields utilize an outlined style with 16dp rounded corners, featuring custom focus border colors and leading icons for immediate visual recognition.
* **Cards (Title Card Style)**: Data and configuration options are grouped into categorized "Title Cards".
  * Example: The Alarm Setup screen groups Ringtones ("Standard", "Nature & Calm", "Electronic") and Tasks into distinct, elevated card containers. 
  * Example: The Home and Stats screens utilize these cards to highlight "Your Streak" and "Weekly Overview" metrics cleanly.

## 4. Screen-Specific Implementations
* **Auth Screen**: Emphasizes a welcoming onboarding flow with an enlarged brand logo, bold typography, and a clear separation between email authentication and OAuth (Google) options.
* **Home Screen**: Prioritizes immediate status recognition. Active alarms and current user streaks are displayed in high-contrast cards with toggle switches aligned to the right.
* **Alarm Setup**: Replaces standard flat lists with categorized title cards, making it easier for users to parse sound and task options by category.
* **Assessment Screen**: Includes a "Skip" action to ensure users are not permanently blocked by the onboarding or setup flow.
* **Stats Screen**: Utilizes side-by-side metric cards for average wake time and task success rate, complemented by linear progress indicators for favorite tasks.

## 5. Typography
* **Headlines**: Bold, high-contrast typography is used for screen titles and main greetings (`headlineLarge`, `displayMedium`).
* **Body**: Standard M3 body typography ensures readability for secondary information and labels.
