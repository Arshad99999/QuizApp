# Quiz App

## Overview
The Quiz App is an interactive and engaging quiz application that uses the Trivia API to provide users with a variety of questions. Users can customize their quiz experience by selecting the number of questions (up to 30), the category, and the difficulty level.

## Features
- Select the number of questions (maximum 30)
- Choose from various categories
- Adjust the difficulty level (easy, medium, hard)
- Randomized question order
- User-friendly interface

## Trivia API
This app utilizes the [Trivia API](https://the-trivia-api.com/v2/questions) to fetch quiz questions. The Trivia API is a free API that provides a wide range of trivia questions from various categories and difficulty levels.

### API Endpoints
- **Endpoint URL:** `https://the-trivia-api.com/v2/questions`
- **Parameters:**
  - `limit`: Number of questions (1-30)
  - `categories`: List of categories (comma-separated)
  - `difficulty`: Difficulty level (`easy`, `medium`, `hard`)

### Example API Request
```http
GET https://the-trivia-api.com/v2/questions?limit=10&categories=general_knowledge&difficulty=medium
```

### Example API Response

```http

[
    {
        "category": "general_knowledge",
        "id": "6496ac48e831b1ab5aa11146",
        "correctAnswer": "Lapidary",
        "incorrectAnswers": ["Byzantine", "Iconography", "Philately"],
        "question": {
            "text": "What is the term for the polishing and cutting of stones and gems?"
        },
        "tags": ["jewelry", "words"],
        "type": "text_choice",
        "difficulty": "hard",
        "regions": [],
        "isNiche": false
    },
    {
        "category": "general_knowledge",
        "id": "6239f82ea72e7a347ac879d4",
        "correctAnswer": "Romeo",
        "incorrectAnswers": ["Rwanda", "Roar", "Rowboat"],
        "question": {
            "text": "What word is used in the NATO Phonetic Alphabet for the letter R?"
        },
        "tags": ["language", "general_knowledge"],
        "type": "text_choice",
        "difficulty": "medium",
        "regions": [],
        "isNiche": false
    },
    {
        "category": "general_knowledge",
        "id": "6239f83da72e7a347ac879d8",
        "correctAnswer": "Whiskey",
        "incorrectAnswers": ["Wizard", "Warlock", "Womble"],
        "question": {
            "text": "What word is used in the NATO Phonetic Alphabet for the letter W?"
        },
        "tags": ["words", "general_knowledge"],
        "type": "text_choice",
        "difficulty": "medium",
        "regions": [],
        "isNiche": false
    },
    // More questions...
]
```
