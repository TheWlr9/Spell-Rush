[![pipeline status](https://code.cs.umanitoba.ca/comp3350-summer2019/0xc0de---12/badges/develop/pipeline.svg)](https://code.cs.umanitoba.ca/comp3350-summer2019/0xc0de---12/commits/develop)

# 0xC0DE - 12

We put the "1100 0000 1101 1110" in "encode"!

# "Spell Rush"  Vision Statement

Spell Rush is an action game with a focus on an innovative battle system that uses gesture recognition. 

### Project Description

Players will draw symbols on their screen to perform actions such as attacks and defenses to battle a simple AI enemy. Players will need to make quick decisions in real time, with more powerful actions requiring more drawing complexity. Throughout the game, players will strategize their actions based on incoming attacks, their current defenses, health, and learned attack gestures. Players will be rewarded with scores that reflect their competence in battle as well as new actions and/or powerups. These scores can be submitted to a local leaderboard. 

### Who Our Users Are

Our game appeals to casual Android users with as little as 5 minutes to spare for a fast-paced real-time strategy boss rush game. Due to the simple combat system, users anywhere from 7 - 90 years of age will enjoy our game.  The leaderboard will challenge the more hardcore gaming audience and completionists to achieve their highest score while uncovering new abilities and gestures.

### Project Value

Our app provides entertainment. Users will exercise their problem-solving skills as they work toward mastery of our battle system. As the difficulty increases, players will be pushed to become ever more proficient. Users will be encouraged to share their experiences and scores with friends. The app will spark friendly competition among players through the leaderboard and acceptable amounts of "trash-talking".

### Project Success Criteria

Our team will consider this project a success if the following at least two of the following four criteria are met. Firstly, 50% of the players will return to the app after their first session. Secondly, 46% of our players will play until a "game over" at least thrice during their first play session. Thirdly, our player base reaches at least 8 unique daily users sometime during its 1-month initial release window.  Lastly, players rate our application at least an average of 3.5 out of 5 stars on our in-app rating system.

# Programming Practices

## Methods, Classes, Etc.

- Method / Function names should always use an action verb

- Use camel case for naming in all `.java` code.`

- bracket goes on right

-  Put an `//end` comment on the end brackets of substaintial code blocks

- No classes larger than 500 lines -> try to keep it under 250.

- Methods under ~50 lines.

  - if it's longer, you can refactor things.

  **Example:**

  ```
  public void doTheThing() {
  
  } // end doTheThing()
  ```

## Testing

- Use the provdided JUnit unit testing library
  - Write Unit tests for your task as you're implementing your task, *and after*
  - Normal cases, edge cases, etc.
  - Follow course guidelines on testing

## Merging, etc

- Create a new branch and pull request for each user story.
  - must have approval (by atleast 2 people? exact number depends on size of user story)
    - Merge request creator must make the post-review changes, barring extreme 'crunch time'
  - Put the name of the gitlab ticket, and link to gitlab issue in the PR
- **Squash and merge**
- Try to use good commit messages

- Small hotfixes onto develop are OK
  - just bring up in slack what yer doin'

## Architecture

- 3-tier architecture, seperated into packages