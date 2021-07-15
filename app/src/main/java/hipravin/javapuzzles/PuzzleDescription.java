package hipravin.javapuzzles;

public class PuzzleDescription {
    private String puzzleId;
    private int titleStringId;

    public PuzzleDescription(String puzzleId, int headerStringId) {
        this.puzzleId = puzzleId;
        this.titleStringId = headerStringId;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public void setTitleStringId(int titleStringId) {
        this.titleStringId = titleStringId;
    }
}
