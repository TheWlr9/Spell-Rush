//ScoreEntry object class that stores a player's name and score
package com.spellrush.application;

public class ScoreEntry implements Comparable<ScoreEntry>{
    private final String playerName;
    private final int playerScore;

    public ScoreEntry(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    } //end ScoreEntry()

    public String getPlayerName() {
        return playerName;
    } //end getPlayerName()

    public int getPlayerScore() {
        return playerScore;
    } //end getPlayerScore()

    @Override
    public int compareTo(ScoreEntry scoreEntry) {
        return scoreEntry.getPlayerScore() - this.getPlayerScore();
    } //end compareTo()

    @Override
    public boolean equals(Object score) {
        boolean hasSameName = this.playerName.equals(((ScoreEntry)score).playerName);
        boolean hasEqualScore = this.playerScore == (((ScoreEntry)score).playerScore);
        return hasSameName && hasEqualScore;
    } //end equals()
}
