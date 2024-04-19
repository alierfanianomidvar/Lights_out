package lights_out.record;

import java.util.List;

public record State(
        int[][] board,
        List<Move> moves,
        int nextPieceIndex) {
}
