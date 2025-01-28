package chess;

import chess.moves.*;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(type == PieceType.KING){ // PASSED
            return new KingMovesCalculator(myPosition, board).get_viable_moves();
        }else if(type == PieceType.QUEEN){ // PASSED
            return new QueenMovesCalculator(myPosition, board).get_viable_moves();
        }else if(type == PieceType.PAWN){
            return new PawnMovesCalculator(myPosition, board).get_viable_moves();
        }else if(type == PieceType.BISHOP){ // PASSED
            return new BishopMovesCalculator(myPosition, board).get_viable_moves();
        }else if(type == PieceType.KNIGHT){ // PASSED
            return new KnightMovesCalculator(myPosition, board).get_viable_moves();
        }else if(type == PieceType.ROOK){ // PASSED
            return new RookMovesCalculator(myPosition, board).get_viable_moves();
        }else{
            return null;
        }
    }
}
