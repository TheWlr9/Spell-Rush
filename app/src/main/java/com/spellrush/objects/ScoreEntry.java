//ScoreEntry object class that stores a player's name and score
package com.spellrush.objects;

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
        //Split up line / split up calls (cleaner somehow? Local variables in method??)
        return (this.playerName.equals(((ScoreEntry)score).playerName) && (this.playerScore == (((ScoreEntry)score).playerScore)));
    } //end equals()
}
