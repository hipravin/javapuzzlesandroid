package hipravin.javapuzzles.puzzles;

import java.util.List;

public class PuzzleInvocationResult {
    final boolean passed;
    final List<String> output;

    public PuzzleInvocationResult(boolean passed, List<String> output) {
        this.passed = passed;
        this.output = output;
    }

    public boolean isPassed() {
        return passed;
    }

    public List<String> getOutput() {
        return output;
    }
}
