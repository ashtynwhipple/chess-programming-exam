package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator {

    private final ChessBoard board;
    private final ChessPosition position;

    public KnightMovesCalculator(ChessPosition position, ChessBoard board) {
        this.position = position;
        this.board = board;
    }

    public boolean is_opponent(ChessPiece og_piece, ChessPiece move_to_piece){
        if (og_piece == null){
            return false;
        }
        return !og_piece.getTeamColor().equals(move_to_piece.getTeamColor());
    }

    public boolean is_in_bounds(int row, int col){
        return (row < 9 && row > 0 && col < 9 && col > 0);
    }

    public boolean valid_move(ChessPosition new_position){
        // return true if in bounds and null
        //return true if is opponent

        if (is_in_bounds(new_position.getRow(), new_position.getColumn())){
            if (board.getPiece(new_position) == null){
                return true;
            }
            return is_opponent(board.getPiece(new_position), board.getPiece(position));
        }else{
            return false;
        }
    }

    public Collection<ChessMove> get_viable_moves(){
        List<ChessMove> viable_moves = new ArrayList<>();

        int[] change_row = {1, -1, 2, -2, 2, -2, 1, -1};
        int[] change_col = {2, 2, 1, 1, -1, -1, -2, -2};

        for (int i = 0; i < 8; i++){
            int new_row = position.getRow() + change_row[i];
            int new_col = position.getColumn() + change_col[i];

            ChessPosition new_position = new ChessPosition(new_row, new_col);

            if (valid_move(new_position)) {
                viable_moves.add(new ChessMove(position, new_position, null));
            }
        }

        return viable_moves;
    }

}
