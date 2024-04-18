package lights_out;

import java.util.Arrays;


public class BoardState {
    private final int[] state;

    public BoardState(int[] state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardState that = (BoardState) o;
        return Arrays.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}
