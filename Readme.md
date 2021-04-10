# Ranking Game

#### Make a service that maintains a real-time ranking of users playing a specific game.

### Requirements

- Clients submit users scores when they achieve important milestones in the game.
- Clients can submit absolute scores or relatives scores, for example:{'user': 123, 'total': 250}, or {'user': 789, 'score': +10}, or {'user': 789, 'score': -20}
- Any client can request the ranking at any time, using one of the following requests:
    - Absolute ranking, for example Top100, Top200, Top500.
    - Relative ranking, for instance: at100/3, meaning 3 users around position 100th of the ranking. that is positioned 97 98 99 100 101 102. 103.
