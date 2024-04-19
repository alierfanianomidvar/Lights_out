package lights_out.record;

public record Move(int pieceIndex, int x, int y) {
    @Override
    public String toString() {
        return String.format(
                "Piece %d at (%d, %d)",
                pieceIndex,
                x, y);
    }
}
