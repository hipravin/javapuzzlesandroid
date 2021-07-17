package hipravin.javapuzzles.puzzles;

public class PuzzleMetadata {
    private boolean debugUnlocked = false;
    private boolean helpUnlocked = false;
    private boolean solutionUnlocked = false;

    private boolean debugEnabled = false;
    private boolean solutionShown = false;


    public boolean isDebugUnlocked() {
        return debugUnlocked;
    }

    public void setDebugUnlocked(boolean debugUnlocked) {
        this.debugUnlocked = debugUnlocked;
    }

    public boolean isHelpUnlocked() {
        return helpUnlocked;
    }

    public void setHelpUnlocked(boolean helpUnlocked) {
        this.helpUnlocked = helpUnlocked;
    }

    public boolean isSolutionUnlocked() {
        return solutionUnlocked;
    }

    public void setSolutionUnlocked(boolean solutionUnlocked) {
        this.solutionUnlocked = solutionUnlocked;
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public boolean isSolutionShown() {
        return solutionShown;
    }

    public void setSolutionShown(boolean solutionShown) {
        this.solutionShown = solutionShown;
    }
}
