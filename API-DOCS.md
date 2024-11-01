# Fitness Forge API Documentation

Fitness Forge provides a comprehensive API for creating customized workout templates based on user-defined parameters such as workout goals, available time, and exercise preferences. Our algorithm dynamically compiles exercises from our extensive database of 874 exercises, optimizing for user goals and constraints.

## Table of Contents
- [Authentication](#authentication)
    - [Create Member](#create-new-member)
    - [Authenticate Member](#authenticate-existing-member)
    - [Get Member](#get-member)
    - [Update Member](#update-member)
    - [Get Workout Ids](#get-workout-ids)
- [Exercise](#exercise)
    - [Get Total Exercise Count](#get-total-exercise-count)
    - [Get Exercise By Id](#get-exercise-by-id)
    - [Get Exercise By Page](#get-exercise-by-page)
    - [Get Exercise Page With Filter](#get-exercise-page-with-filter)
- [Workout](#workout)
    - [Create Workout](#create-workout)
    - [Get Workout By Id](#get-workout-by-id)
    - [Get Workout By Name](#get-workout-by-name)
    - [Get Workout Exercises](#get-workout-exercises)
    - [Update Workout](#update-workout)
    - [Delete Workout](#delete-workout)

## Authentication

The application uses JWT token based authentication system. For every API request to be made on every endpoint requires a valid JWT token in order to make request get response. To access endpoints, include a token in the reqeust headers:

**Headers**: `Authorization: Bearer <token>`

There are two endpoints which are available for open access and don't require any authentication, the purpose of them being open is because this endpoints will allow new users to register accounts and also authenticate their existing accounts to recieve a JWT token for further communication.

### Create New Member

- **URL**: `/member/create`
- **Method**: `POST`
- **Description**: Creates a new member (if doesn't exist already) in the database.

#### Parameters

| Parameter     | Type    | Description                           | Required |
|---------------|---------|---------------------------------------|----------|
| `name`          | `string`  | Name of new member/use                | Yes      |
| `email`         | `string`  | The email of the member/user          | Yes      |
| `password`      | `string`  | The password of the member/user       | Yes      |
| `age`           | `number`  | The age of member/user                | No       |
| `gender`        | `string`  | The gender of the member/user         | No       |
| `weight`        | `number`  | The weight of the member/user (in kg) | No       |
| `height`        | `number`  | The height of the member/user (in ft) | No       |

#### Example Request

```http
POST /member/create
Content-Type: application/json
```

#### Example Request Body

```json
{
    "name": "example member",
    "email": "example@gmail.com",
    "password": "example123",
    "age": 23,
    "gender": "male",
    "weight": 75,
    "height": 5.11
}
```

#### Example Response Body (Success)
```json
# status code: 201 CREATED
{
    "joinedOn": "Fri Nov 01 11:20:52 IST 2024",
    "name": example member",
    "id": "1",
    "email": "example@gmail.com"
}
```
#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the member/user already exists)
{
    "timestamp": "01-11-2024 11:23:34",
    "messages": [
        "\nClass : com.fitnessforge.service.MemberService\nException : The provided entity already exists"
    ]
}
```

### Authenticate Existing Member

- **URL**: `/member/auth`
- **Method**: `POST`
- **Description**: Authenticates the existing member in database and if valid generates and passes the JWT token in the resposne headers (Authorization header).

#### Parameters

| Parameter     | Type    | Description                           | Required |
|---------------|---------|---------------------------------------|----------|
| `email`         | `string`  | The email of the member/user          | Yes      |
| `password`      | `string`  | The password of the member/user       | Yes      |

#### Example Request
```http
POST /member/auth
Content-Type: application/json
```

#### Example Request Body
```json
{
    "email": "example@gmail.com",
    "password": "example123",
}
```

#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "id": "1",
}
# And a JWT token in Authorization header of the response header.
```
#### Example Response Body (Failure)
```json
# status code: 401 UNAUTHORIZED (in case if the member/user was not found)
{
    "You provided wrong credentials"
}
```

**Note**: This is the only endpoint which will provide a JWT token, no other endpoint provides a valid JWT token.

### Get Member

- **URL**: `/member/{id}`
- **Method**: `GET`
- **Description**: Fetches the existing member of the provided ID.

#### Parameters

| Parameter    | Type   | Description                   | Required |
|--------------|--------|-------------------------------|----------|
| `id`           | `path`   | The id of the existing member | Yes      |

#### Example Request
```http
GET /member/1
Content-Type: application/json
Authorization: Bearer <token>
```

#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "id": 1,
    "name": "example",
    "age": 23,
    "gender": "male",
    "weight": 75,
    "height": 5.11,
    "email": "example@gmail.com",
    "password": null,
    "joinedOn": "2024-11-01",
    "workouts": null
}
```

#### Example Response Body (Failure)
```json
# status code: 401 UNAUTHORIZED (in case if the member/user was not found for the provided id)
{
    "timestamp": "01-11-2024 11:38:34",
    "messages": [
        "\nClass : com.fitnessforge.entity.Member\nException : The provided entity was not found"
    ]
}
```

### Update Member

- **URL**: `/member/update`
- **Method**: `PUT`
- **Description**: Updates the existing member with the provided parameters.

#### Parameters

| Parameter     | Type    | Description                           | Required |
|---------------|---------|---------------------------------------|----------|
| `id`            | `number`  | The id of the existing member         | Yes      |
| `name`          | `string`  | Name of new member/use                | Yes      |
| `email`         | `string`  | The email of the member/user          | Yes      |
| `password`      | `string`  | The password of the member/user       | Yes      |
| `age`           | `number`  | The age of member/user                | No       |
| `gender`        | `string`  | The gender of the member/user         | No       |
| `weight`        | `number`  | The weight of the member/user (in kg) | No       |
| `height`        | `number`  | The height of the member/user (in ft) | No       |

#### Example Request
```http
PUT /member/update
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Request Body
```json
{
    "id": 1,
    "name": "example",
    "age": 24,
    "gender": "male",
    "fitnessLevel": "begineer",
    "weight": 88.9,
    "height": 5.10,
    "email": "example@gmail.com",
    "password": "example123"
}
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "id": 1,
    "name": "example",
    "age": 24,
    "gender": "male",
    "fitnessLevel": "begineer",
    "weight": 88.9,
    "height": 5.1,
    "email": "example@gmail.com",
    "password": null,
    "joinedOn": "2024-11-01T06:25:11.194+00:00",
    "workouts": null
}
```
#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the member/user was not found)
{
    "timestamp": "01-11-2024 12:00:05",
    "messages": [
        "\nClass : com.fitnessforge.entity.Member\nException : The provided entity was not found"
    ]
}
```

### Get Workout Ids

- **URL**: `/member/workouts/{id}`
- **Method**: `GET`
- **Description**: Fetches the workout template created by user of the provided id.

#### Parameters

| Parameter    | Type   | Description                   | Required |
|--------------|--------|-------------------------------|----------|
| `id`           | `path`   | The id of the existing member | Yes      |

#### Example Request
```http
GET /member/workouts/1
Content-Type: application/json
Authorization: Bearer <token>
```

#### Example Response Body (Success)
```json
# status code: 200 OK 
[
    1, 2, 3
]
```

#### Example Response Body (Failure)
```json
# status code: 401 UNAUTHORIZED (in case if the member/user was not found for the provided id)
{
    "timestamp": "01-11-2024 11:38:34",
    "messages": [
        "\nClass : com.fitnessforge.entity.Member\nException : The provided entity was not found"
    ]
}
```

## Get Refresh Token

- **URL**: `/member/auth_refresh/{id}`
- **Method**: `GET`
- **Description**: Generates a new refresh token after, only if the existing JWT token passed in the request header and id is valid.

**Note**: Currently there is no mechanism created for automatically refresh JWT tokens, so it must be done manually and the ***expiration of JWT token is 2 hours.***

#### Parameters

| Parameter    | Type   | Description                   | Required |
|--------------|--------|-------------------------------|----------|
| `id`           | `path`   | The id of the existing member | Yes      |

#### Example Request
```http
GET /member/auth_refresh/1
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
[
    "<string of new jwt token>"
]
```

#### Example Response Body (Failure)
```json
# status code: 403 UNAUTHORIZED (In case the JWT token is not valid or expired)
{
    "JWT token is not valid"
}
```

## Exercise

This application provides a large amount of exercise database provided from [Free exercise database](https://github.com/yuhonas/free-exercise-db), which provides an comprehensive dataset of **874 exercises**. This endpoint provides various exercise fetching features like fetch exercise by multiple filters ([For more info on database schema](https://github.com/yuhonas/free-exercise-db/blob/main/schema.json)), by id, and also the exercises can be fetched in pagination format.

### Get Total Exercise Count

- **URL**: `/exercise/count`
- **Method**: `GET`
- **Description**: Fetches the total number of exercises present in the database

#### Example Request
```http
GET /exercise/count
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "count": 873
}
```
### Get Exercise By Id

- **URL**: `/exercise/{id}`
- **Method**: `GET`
- **Description**: Fetches exercise by its id.

#### Parameters

| Parameter    | Type   | Description                     | Required |
|--------------|--------|---------------------------------|----------|
| `id`           | `path`   | The id of the existing exercise | Yes      |

#### Example Request
```http
GET /exercise/1
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "exerciseId": 1,
    "id": "3_4_Sit-Up",
    "name": "3/4 Sit-Up",
    "force": "pull",
    "level": "beginner",
    "mechanic": "compound",
    "equipment": "body only",
    "primaryMuscles": [
        "abdominals"
    ],
    "secondaryMuscles": [],
    "instructions": [
        "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
        "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
        "Flex your hips and spine to raise your torso toward your knees.",
        "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only ¾ of the way down.",
        "Repeat for the recommended amount of repetitions."
    ],
    "category": "strength",
    "images": [
        "3_4_Sit-Up/0.jpg",
        "3_4_Sit-Up/1.jpg"
    ]
}
```
#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the exercise is not found for the provided id)
{
    "timestamp": "01-11-2024 12:32:23",
    "messages": [
        "\nClass : com.fitnessforge.entity.Exercise\nException : The provided entity was not found"
    ]
}
```

### Get Exercise By Page

- **URL**: `/exercise/page?page={page number}&size={size of page}`
- **Method**: `GET`
- **Description**: Fetch exercise in page format based on the page number and size

#### Query Parameters

| Parameter | Type   | Description                                             | Required | Default Value |
|-----------|--------|---------------------------------------------------------|----------|---------------|
| `page`      | `number` | The page number to fetch exercises from                 | No       | 0             |
| `size`      | `number` | The size of the page i.e the number of exercise per page| No       | 10            |

#### Example Request
```http
GET /exercise/page?page=0&size=3
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
[
    {
        "exerciseId": 1,
        "id": "3_4_Sit-Up",
        "name": "3/4 Sit-Up",
        "force": "pull",
        "level": "beginner",
        "mechanic": "compound",
        "equipment": "body only",
        "primaryMuscles": [
            "abdominals"
        ],
        "secondaryMuscles": [],
        "instructions": [
            "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
            "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
            "Flex your hips and spine to raise your torso toward your knees.",
            "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only ¾ of the way down.",
            "Repeat for the recommended amount of repetitions."
        ],
        "category": "strength",
        "images": [
            "3_4_Sit-Up/0.jpg",
            "3_4_Sit-Up/1.jpg"
        ]
    },
    {
        "exerciseId": 2,
        "id": "90_90_Hamstring",
        "name": "90/90 Hamstring",
        "force": "push",
        "level": "beginner",
        "mechanic": null,
        "equipment": "body only",
        "primaryMuscles": [
            "hamstrings"
        ],
        "secondaryMuscles": [
            "calves"
        ],
        "instructions": [
            "Lie on your back, with one leg extended straight out.",
            "With the other leg, bend the hip and knee to 90 degrees. You may brace your leg with your hands if necessary. This will be your starting position.",
            "Extend your leg straight into the air, pausing briefly at the top. Return the leg to the starting position.",
            "Repeat for 10-20 repetitions, and then switch to the other leg."
        ],
        "category": "stretching",
        "images": [
            "90_90_Hamstring/0.jpg",
            "90_90_Hamstring/1.jpg"
        ]
    },
    {
        "exerciseId": 3,
        "id": "Ab_Crunch_Machine",
        "name": "Ab Crunch Machine",
        "force": "pull",
        "level": "intermediate",
        "mechanic": "isolation",
        "equipment": "machine",
        "primaryMuscles": [
            "abdominals"
        ],
        "secondaryMuscles": [],
        "instructions": [
            "Select a light resistance and sit down on the ab machine placing your feet under the pads provided and grabbing the top handles. Your arms should be bent at a 90 degree angle as you rest the triceps on the pads provided. This will be your starting position.",
            "At the same time, begin to lift the legs up as you crunch your upper torso. Breathe out as you perform this movement. Tip: Be sure to use a slow and controlled motion. Concentrate on using your abs to move the weight while relaxing your legs and feet.",
            "After a second pause, slowly return to the starting position as you breathe in.",
            "Repeat the movement for the prescribed amount of repetitions."
        ],
        "category": "strength",
        "images": [
            "Ab_Crunch_Machine/0.jpg",
            "Ab_Crunch_Machine/1.jpg"
        ]
    }
]
```
#### Example Response Body (Failure)
```json
# status code: 403 FORBIDDEN (in case some invalid request query is passed, like a negative value)
```
### Get Exercise Page With Filter

- **URL**: `/exercise/page/filter?page={page number}&size={size of page}&<filter query parameters>`...
- **Method**: `GET`
- **Description**: Fetch filtered exercises in page format for the defined filters.

#### Query Parameters

| Parameter | Type     | Description                                               | Required | Default Value |
|-----------|----------|-----------------------------------------------------------|----------|---------------|
| `page`      | `number`   | The page number to fetch exercises from                   | No       | 0             |
| `size`      | `number`   | The size of the page i.e the number of exercise per page  | No       | 10            |
| `category`  | `string[]` | List of different type of workout categories              | No       | []            |
| `equipment` | `string[]` | List of available equipments to filter from               | No       | []            |
| `force`     | `string[]` | Different force type to filter from                       | No       | []            |
| `level`     | `string[]` | Different workout levels to filter from                   | No       | []            |
| `mechanic`  | `string[]` | Different types of mechanic like isolation, compound etc. | No       | []            |
| `name`      | `string[]` | Filter directly by different names of exercise            | No       | []            |

#### Example Request
```http
GET exercise/page/filter?page=0&size=3&level=beginner,intermediate&force=push,pull&mechanic=isolation,compound&name=hamstring
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
[
    {
        "exerciseId": 409,
        "id": "Leg-Up_Hamstring_Stretch",
        "name": "Leg-Up Hamstring Stretch",
        "force": "push",
        "level": "beginner",
        "mechanic": "isolation",
        "equipment": null,
        "primaryMuscles": [
            "hamstrings"
        ],
        "secondaryMuscles": [],
        "instructions": [
            "Lie flat on your back, bend one knee, and put that foot flat on the floor to stabilize your spine.",
            "Extend the other leg in the air. If you're tight, you wont be able to straighten it. That's okay. Extend the knee so that the sole of the lifted foot faces the ceiling (or as close as you can get it).",
            "Slowly straighten the legs as much as possible and then pull the leg toward your nose. Switch sides."
        ],
        "category": "stretching",
        "images": [
            "Leg-Up_Hamstring_Stretch/0.jpg",
            "Leg-Up_Hamstring_Stretch/1.jpg"
        ]
    },
    {
        "exerciseId": 542,
        "id": "Platform_Hamstring_Slides",
        "name": "Platform Hamstring Slides",
        "force": "pull",
        "level": "beginner",
        "mechanic": "isolation",
        "equipment": "other",
        "primaryMuscles": [
            "hamstrings"
        ],
        "secondaryMuscles": [
            "glutes"
        ],
        "instructions": [
            "For this movement a wooden floor or similar is needed. Lay on your back with your legs extended. Place a gym towel or a light weight underneath your heel. This will be your starting position.",
            "Begin the movement by flexing the knee, keeping your other leg straight.",
            "Continue bringing the heel closer to you, sliding it on the floor.",
            "At full knee flexion, reverse the movement to return to the starting position."
        ],
        "category": "strength",
        "images": [
            "Platform_Hamstring_Slides/0.jpg",
            "Platform_Hamstring_Slides/1.jpg"
        ]
    },
    {
        "exerciseId": 619,
        "id": "Seated_Band_Hamstring_Curl",
        "name": "Seated Band Hamstring Curl",
        "force": "pull",
        "level": "beginner",
        "mechanic": "isolation",
        "equipment": "other",
        "primaryMuscles": [
            "hamstrings"
        ],
        "secondaryMuscles": [],
        "instructions": [
            "Secure a band close to the ground and place a bench a couple feet away from it.",
            "Seat yourself on the bench and secure the band behind your ankles, beginning with your legs straight. This will be your starting position.",
            "Flex the knees, bringing your feet towards the bench. You may need to lean back slightly to keep your feet from striking the floor.",
            "Pause at the completion of the movement, and then slowly return to the starting position."
        ],
        "category": "strength",
        "images": [
            "Seated_Band_Hamstring_Curl/0.jpg",
            "Seated_Band_Hamstring_Curl/1.jpg"
        ]
    }
]
```
**Note**: For more information on different types of filters refer to the [Schema of the Database](https://github.com/yuhonas/free-exercise-db/blob/main/schema.json)

## Workout

This application provides a flexible way ot create your workout, and hence provides a lot of features to facilitate generating a workout. And the algorithm to generate the workout is designed to generate workout not based on assumptions but proper research based reps and sets required for every workout goal and calculates the appropiate amount of time requried. Due to which it is able to generate a very productive and usefull workout plan all personalized by the user it self.

### Create Workout

- **URL**: `/workout/create?memberId={memberId}`
- **Method**: `POST`
- **Description**: Creates a new workout (if doesn't exist already)

#### Parameters

| Parameter         | Type     | Description                                | Required |
|-------------------|----------|--------------------------------------------|----------|
| `name`              | `string`   | Name of new workout                        | Yes      |
| `level`             | `string`   | The level of workout                       | Yes      |
| `workoutCategory`   | `string`   | The category of workout                    | Yes      |
| `duration`          | `number`   | The total duration of workout (in minutes) | Yes      |
| `equipments`        | `string[]` | The equipments available                   | Yes      |
| `restDuration`      | `number`   | The duration of rest between sets          | Yes      |
| `cardioDays`        | `string[]` | The days of week to perform cardio on      | Yes      |
| `workoutDays`       | `string[]` | The days of week to perform workout on     | Yes      |
| `exerciseToExclude` | `string[]` | The exercise to exclude (by name)          | No       |

#### Query Parameters

| Parameter | Type   | Description               | Required |
|-----------|--------|---------------------------|----------|
| `memberId`  | `number` | The id of existing member | Yes      |

#### Example Request
```http
POST /workout/create?memberId=1
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Request Body
```json
{
    "name": "workout",
    "workoutCategory": "strength",
    "workoutDays": ["monday", "tuesday", "thursday", "friday"],
    "duration": 45,
    "equipments": ["dumbell", "machine"],
    "restDuration": 30,
    "cardioDays": ["monday", "thursday"],
    "level": "beginner",
    "exerciseToExclude": ["Standing Calf Raises"]
}
```

#### Example Response Body (Success)
```json
# status code: 200 OK
{
    "id": 4,
    "level": "beginner",
    "name": "workout",
    "workoutCategory": "strength",
    "workoutDays": [
        "monday",
        "tuesday",
        "thursday",
        "friday"
    ],
    "duration": 45,
    "equipments": [
        "dumbell",
        "machine"
    ],
    "restDuration": 30,
    "cardioDuration": 0,
    "exerciseToExclude": [
        "Standing Calf Raises"
    ],
    "cardioDays": [
        "monday",
        "thursday"
    ]
}
```

#### Example Response Body (Failure)
```json
# status code: 403 FORBIDDEN (in case if the workout already exists)
```

### Get Workout By Id

- **URL**: `/workout/{id}`
- **Method**: `GET`
- **Description**: Fetches existing workout based on the provided id.

#### Parameters

| Parameter    | Type   | Description                    | Required |
|--------------|--------|--------------------------------|----------|
| `id`           | `path`   | The id of the existing workout | Yes      |

#### Example Request
```http
GET /workout/1
Content-Type: application/json
Authorization: Bearer <token>
```

#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "id": 1,
    "level": "beginner",
    "name": "workout_11",
    "workoutCategory": "strength",
    "workoutDays": [
        "monday",
        "tuesday",
        "thursday",
        "friday"
    ],
    "duration": 45,
    "equipments": [
        "dumbell",
        "machine"
    ],
    "restDuration": 30,
    "cardioDuration": 0,
    "exerciseToExclude": [
        "Standing Calf Raises"
    ],
    "cardioDays": [
        "monday",
        "thursday"
    ]
}
```

#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the workout was not found for the provided id)
{
    "timestamp": "01-11-2024 04:45:47",
    "messages": [
        "\nClass : com.fitnessforge.entity.Workout\nException : The provided entity was not found"
    ]
}
```

### Get Workout By Name

- **URL**: `/workout?name={workout_name}`
- **Method**: `GET`
- **Description**: Fetches existing workout based on the name provided.

#### Parameters

| Parameter | Type | Description                                       | Required |
|-----------|------|---------------------------------------------------|----------|
| `name`      | `path` | The name of the existing workout (case sensitive) | Yes      |

#### Example Request
```http
GET /workout?name=workout
Content-Type: application/json
Authorization: Bearer <token>
```

#### Example Response Body (Success)
```json
# status code: 200 OK 
{
    "id": 4,
    "level": "beginner",
    "name": "workout",
    "workoutCategory": "strength",
    "workoutDays": [
        "monday",
        "tuesday",
        "thursday",
        "friday"
    ],
    "duration": 45,
    "equipments": [
        "dumbell",
        "machine"
    ],
    "restDuration": 30,
    "cardioDuration": 0,
    "exerciseToExclude": [
        "Standing Calf Raises"
    ],
    "cardioDays": [
        "monday",
        "thursday"
    ]
}
```

#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the workout was not found for the provided name)
{
    "timestamp": "01-11-2024 04:45:47",
    "messages": [
        "\nClass : com.fitnessforge.entity.Workout\nException : The provided entity was not found"
    ]
}
```

### Get Workout Exercises

- **URL**: `/workout/{id}/exercises`
- **Method**: `GET`
- **Description**: Fetches exercises based on the workout template of the provided id.

#### Parameters

| Parameter | Type | Description                    | Required |
|-----------|------|--------------------------------|----------|
| `id`        | `path` | The id of the existing workout | Yes      |

#### Example Request
```http
GET /workout/{id}/exercises
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Success)
```json
# status code: 200 OK 
[
    {
        "name": "strength_1",
        "exercises": [
            {
                "exerciseId": 109,
                "id": "Butterfly",
                "name": "Butterfly",
                "force": "pull",
                "level": "beginner",
                "mechanic": "isolation",
                "equipment": "machine",
                "primaryMuscles": [
                    "chest"
                ],
                "secondaryMuscles": [],
                "instructions": [
                    "Sit on the machine with your back flat on the pad.",
                    "Take hold of the handles. Tip: Your upper arms should be positioned parallel to the floor; adjust the machine accordingly. This will be your starting position.",
                    "Push the handles together slowly as you squeeze your chest in the middle. Breathe out during this part of the motion and hold the contraction for a second.",
                    "Return back to the starting position slowly as you inhale until your chest muscles are fully stretched.",
                    "Repeat for the recommended amount of repetitions."
                ],
                "category": "strength",
                "images": [
                    "Butterfly/0.jpg",
                    "Butterfly/1.jpg"
                ]
            },
            {
                "exerciseId": 134,
                "id": "Calf-Machine_Shoulder_Shrug",
                "name": "Calf-Machine Shoulder Shrug",
                "force": "pull",
                "level": "beginner",
                "mechanic": "isolation",
                "equipment": "machine",
                "primaryMuscles": [
                    "traps"
                ],
                "secondaryMuscles": [],
                "instructions": [
                    "Position yourself on the calf machine so that the shoulder pads are above your shoulders. Your torso should be straight with the arms extended normally by your side. This will be your starting position.",
                    "Raise your shoulders up towards your ears as you exhale and hold the contraction for a full second.",
                    "Slowly return to the starting position as you inhale.",
                    "Repeat for the recommended amount of repetitions."
                ],
                "category": "strength",
                "images": [
                    "Calf-Machine_Shoulder_Shrug/0.jpg",
                    "Calf-Machine_Shoulder_Shrug/1.jpg"
                ]
            },
            {
                "exerciseId": 135,
                "id": "Calf_Press",
                "name": "Calf Press",
                "force": "push",
                "level": "beginner",
                "mechanic": "isolation",
                "equipment": "machine",
                "primaryMuscles": [
                    "calves"
                ],
                "secondaryMuscles": [],
                "instructions": [
                    "Adjust the seat so that your legs are only slightly bent in the start position. The balls of your feet should be firmly on the platform.",
                    "Select an appropriate weight, and grasp the handles. This will be your starting position.",
                    "Straighten the legs by extending the knees, just barely lifting the weight from the stack. Your ankle should be fully flexed, toes pointing up. Execute the movement by pressing downward through the balls of your feet as far as possible.",
                    "After a brief pause, reverse the motion and repeat."
                ],
                "category": "strength",
                "images": [
                    "Calf_Press/0.jpg",
                    "Calf_Press/1.jpg"
                ]
            },
            {
                "exerciseId": 136,
                "id": "Calf_Press_On_The_Leg_Press_Machine",
                "name": "Calf Press On The Leg Press Machine",
                "force": "push",
                "level": "beginner",
                "mechanic": "isolation",
                "equipment": "machine",
                "primaryMuscles": [
                    "calves"
                ],
                "secondaryMuscles": [],
                "instructions": [
                    "Using a leg press machine, sit down on the machine and place your legs on the platform directly in front of you at a medium (shoulder width) foot stance.",
                    "Lower the safety bars holding the weighted platform in place and press the platform all the way up until your legs are fully extended in front of you without locking your knees. (Note: In some leg press units you can leave the safety bars on for increased safety. If your leg press unit allows for this, then this is the preferred method of performing the exercise.) Your torso and the legs should make perfect 90-degree angle. Now carefully place your toes and balls of your feet on the lower portion of the platform with the heels extending off. Toes should be facing forward, outwards or inwards as described at the beginning of the chapter. This will be your starting position.",
                    "Press on the platform by raising your heels as you breathe out by extending your ankles as high as possible and flexing your calf. Ensure that the knee is kept stationary at all times. There should be no bending at any time. Hold the contracted position by a second before you start to go back down.",
                    "Go back slowly to the starting position as you breathe in by lowering your heels as you bend the ankles until calves are stretched.",
                    "Repeat for the recommended amount of repetitions."
                ],
                "category": "strength",
                "images": [
                    "Calf_Press_On_The_Leg_Press_Machine/0.jpg",
                    "Calf_Press_On_The_Leg_Press_Machine/1.jpg"
                ]
            }
        ]
    },
    {other sets of exercises...}
]
```

#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the workout was not found for the provided id)
{
    "timestamp": "01-11-2024 04:56:16",
    "messages": [
        "\nClass : com.fitnessforge.entity.Workout\nException : The provided entity was not found"
    ]
}
```

### Update Workout

- **URL**: `/workout/update`
- **Method**: `PUT`
- **Description**: Updates the workout with the provided workout if the access token is valid and workout exists in the database.

#### Parameters

| Parameter         | Type     | Description                                | Required |
|-------------------|----------|--------------------------------------------|----------|
| `id`                | `number`   | The id of the existing workout             | Yes      |
| `name`              | `string`   | Name of new workout                        | Yes      |
| `level`             | `string`   | The level of workout                       | Yes      |
| `workoutCategory`   | `string`   | The category of workout                    | Yes      |
| `duration`          | `number`   | The total duration of workout (in minutes) | Yes      |
| `equipments`        | `string[]` | The equipments available                   | Yes      |
| `restDuration`      | `number`   | The duration of rest between sets          | Yes      |
| `cardioDays`        | `string[]` | The days of week to perform cardio on      | Yes      |
| `workoutDays`       | `string[]` | The days of week to perform workout on     | Yes      |
| `exerciseToExclude` | `string[]` | The exercise to exclude (by name)          | No       |

#### Example Request
```http
POST /workout/update
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Request Body
```json
{
    "id": 1,
    "name": "workout_11",
    "workoutCategory": "strength",
    "workoutDays": ["monday", "tuesday", "thursday", "friday"],
    "duration": 60,
    "equipments": ["dumbell", "machine"],
    "restDuration": 30,
    "cardioDays": ["monday", "thursday", "friday"],
    "level": "beginner",
    "exerciseToExclude": ["Standing Calf Raises"]
}
```
#### Example Response Body (Success)
```json
# status code: 200 OK
{
    "id": 1,
    "level": "beginner",
    "name": "workout_11",
    "workoutCategory": "strength",
    "workoutDays": [
        "monday",
        "tuesday",
        "thursday",
        "friday"
    ],
    "duration": 60,
    "equipments": [
        "dumbell",
        "machine"
    ],
    "restDuration": 30,
    "cardioDuration": 0,
    "exerciseToExclude": [
        "Standing Calf Raises"
    ],
    "cardioDays": [
        "monday",
        "thursday",
        "friday"
    ]
}
```

#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the workout was not found for the provided workout)
{
    "timestamp": "01-11-2024 04:56:16",
    "messages": [
        "\nClass : com.fitnessforge.entity.Workout\nException : The provided entity was not found"
    ]
}
```

### Delete Workout

- **URL**: `/workout/delete?workoutId={workout_id}&memberId={member_id}`
- **Method**: `DELETE`
- **Description**: Deletes existing workout based on the provided workoutId and memberId.

#### Query Parameters

| Parameter | Type   | Description             | Required |
|-----------|--------|-------------------------|----------|
| `workoutId` | `number` | The existing workout id | Yes      |
| `memberId`  | `number` | The existing member id  | Yes      |

#### Example Request
```http
GET /workout/delete?workoutId=1&memberId=1
Content-Type: application/json
Authorization: Bearer <token>
```
#### Example Response Body (Failure)
```json
# status code: 404 NOT FOUND (in case if the workout was not found for the provided workout)
{
    "timestamp": "01-11-2024 04:56:16",
    "messages": [
        "\nClass : com.fitnessforge.entity.Workout\nException : The provided entity was not found"
    ]
}
```
