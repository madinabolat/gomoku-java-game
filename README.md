# Gomoku

A classic Gomoku game with two play modes: console and network.

## Console Mode

Supports Human, Dumb Computer, and Smart Computer players.

```
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

Follow the prompts to choose player types, board size (3–15), and win condition.

## Network Mode

Human vs Human over a local network. Start one server and two clients in three separate terminals:

```
mvn compile exec:java -Dexec.mainClass="org.example.network.Server"
```

```
mvn compile exec:java -Dexec.mainClass="org.example.network.Client"
```

```
mvn compile exec:java -Dexec.mainClass="org.example.network.Client"
```

The first player to connect chooses the board size. Follow the prompts to play.
