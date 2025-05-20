# Requirements
- Football World Cup Score Board
  - can start a game
  - can finish a game
  - can update score of a game
  - can get summary of all games in board
    - summary is sorted by total score
    - games with the same total score are ordered from the most recently added

# Assumptions
- Teams in Football World Cup are uniquely identified by their names, which are case-sensitive.
- Games on the score board are uniquely identified by a pair of team names.
  - the identity of the pair does not depend on the order of the teams, meaning 'Poland - Germany' is the same as 'Germany - Poland'
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