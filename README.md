# Live Football World Cup Score Board

### Task requirements:
You are working in a sports data company, and we would like you to develop a new Live Football
World Cup Scoreboard library that shows all the ongoing matches and their scores.
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.



## Technical doc
Java version: 22
### App building
```sh
cd score-board-sr
package -f pom.xml
```

### Scoreboard class
```java
WorldCupScoreBoard implements FootballScoreBoard
```

### Interface
```List<Match> getMatches()```
>- returns list of matches as a stream of map's values

```void startMatch(Match match)```
>- starting a new match adding it to map of ongoing matches with initial score


### Model
```Match```
>- keeps home/guest ```Team``` match details
>- keeps score, set initial match score (0 - 0)


```MatchKey```
>- composite key consists of home and guest identifier
>- used as a key for scoreboard map of matches
>- input modified to 

```Team```
>- simple java record that keeps team's unique identifier

## Challenges
- teams input
   - ensure that team identifier is valid (not null or empty)
   - ensure that team is duplicated. In other words that if team is already playing, it cannot play another match
   - ensure that match key is consistent regardless of key sensitivity