# Functional Requirements

## What are Functional Requirements

Functional Requirement defines specific behaviors, features, or interactions that a system must perform to meet user
needs. It's all about the features that directly help a user reach a goal. Functional Requirements do not specify how
the functions will be delivered, which is covered in technical specifications.

Functional requirements documents answer the “what” questions:

- What tasks should the system or product perform?
- What data or information should it store?
- What calculations should it do?
- What other systems should it interface with?

## Breakdown the Functional Requirement for Project Creatine

Inspired app: MyFitnessPal, Strava.

- User Context:
    - Register and Login with their own Google, Facebook accounts, Users can create their own account.
    - Make a Personal Profile (about username, email, photo, gender, date of birth, height, weight, Goals).
    - Show User Progress display in Dashboard, Nutrition of day in round chart, total nutrients.
    - Platform gives user recommendation about their goals → Give nutrition advice (meals) and training advice (activity
      based on their level).
  
- Training Context:
    - Platform provides Workout Database for user explore.
    - Build, Apply workout Routine (include name, Avg. duration, Avg. Calories, Exercises).
    - Log all exercise to calculate calories.
    - Platform using user phone camera to record their training process → Calculate how the user does the exercises,
      how much rep (record once per set). (Can make system scale quickly).
    - Platform supports professional trainer to create its own exercises, routines.

- Nutrition Context:
    - Platform provides Food Database for user explore.
    - Build and Apply Meal (include name, total calories, type, and foods).
    - Platform using AI to extract ingredients (Hard).
    - The Platform supports professional nutrients to create its own meals.

- Achievement Context:
  - The Platform supports managing point, trophy, achievement.
  - Users earn points for completing workouts, meeting nutritional goals, and maintaining consistency.
  - Trophy system recognizes milestone achievements (first workout, 30-day streak, weight goal reached).
  - Achievement badges displayed on user profiles to encourage competition and motivation.
  - Weekly and monthly leaderboards to compare progress with friends or community members.

- Social Context:
  - Users can connect with friends and follow their fitness journeys.
  - Option to share achievements, workouts, and meal plans on social media platforms.
  - Community forums organized by fitness goals and interests.
  - Challenge allowing users to create or join group fitness challenges.
  - Direct messaging for workout buddies and accountability partners.

- Analytics Context:
  - Detailed reports showing progress over time (weight, strength, endurance).
  - Insights on workout efficiency and nutrition balance.
  - Sleep tracking integration to correlate rest with performance.
  - Export functionality for personal data in various formats.
  - Trend analysis to identify patterns in user behavior and results.

- Integration Context:
  - Sync with wearable fitness devices and smartwatches.
  - Calendar integration for scheduling workouts and meal prep.
  - Music streaming service integration for workout playlists.
  - Health app integration (Apple Health, Google Fit).
  - Support for third-party nutrition and fitness apps through API.