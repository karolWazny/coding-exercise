# Requirements
- Football World Cup Score Board
  - can start a game
  - can finish a game
  - can update score of a game
  - can get summary of all games in the board
    - summary is sorted by total score
    - games with the same total score are ordered from the most recently added

# Assumptions
- Teams in Football World Cup are uniquely identified by their names, which are case-sensitive.
- Games on the score board are uniquely identified by a pair of team names.
  - the identity of the pair does depend on the order of the teams, meaning 'Poland - Germany' is not the same as 'Germany - Poland'
- One team cannot play in two different games simultaneously.
- The team cannot play in a game against itself.
- The summary is returned in two forms:
  - as a formatted string (proper for quickly displaying):
```
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
```
  - as an ordered array of records, that can be easily consumed by any client using this library (I do realise it is an extension to the requirements, but I felt it made little sense not to expose such API in this sort of library).
- The code does not throw errors when null team names are used.
- The library is intended to be used in a single-threaded environment; if the client tries to access the ScoreBoard concurrently,
it is their responsibility to build proper concurrent-access mechanisms.
- This code is intended to be used as a library; hence only the main interface (facade ScoreBoard) with a static factory
method along with the DTOs are public classes. All the business logic is hidden in the non-public classes. That minimizes
the chance of unintended usage and breaking something by the client. It also allows us to change the implementation
without worrying about breaking compatibility.

# Further development
The author focused on creating the solution according to the requirements of the exercise.
The aim was to implement as simple solution as possible, without overengineering anything.

However, if the solution was to grow (because of changing requirements), some alterations to the code could be made:
- create a configurable formatter for displaying a game (a one that takes a string template as an argument, like DateTimeFormatter does),
- add support for persistence, in example for a RDBMS,
- move logic related to storing and retrieving data behind some abstraction and keep only business logic in the ScoreBoard implementation,
- allow for more information about teams to be stored and displayed (for example country codes, like DEN instead of Denmark)

Beforementioned features could be nice, but in this context they would mean heavily overengineering of the solution,
so they were not implemented.
