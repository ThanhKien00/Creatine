# Model Design

User Context

- User:
    - Authentication Properties: username, email, password
    - Profile Properties: firstName, lastName, birthday, Gender, about_me

- Measurement:
  - record_day
  - height, weight, body_fat 
  - neck, shoulders, chest, bicep, forearm, wrist, waist, hips, thigh, calf
  - result
  - images

- Plan
  - s
  - calories

- Exercise:
    - title
    - duration
    - level: Beginner | Intermediate | Advanced |
    - tags: Json string
    - description
    - video, image
    - instructions {step, guide}

- Exercised: (User_Exercises)
  - user_id
  - recorded_exercises  
  - calories
  - duration

- Recorded_Exercise 
  - exercise_id
  - rep
  - avg_weight


- Routine:

 