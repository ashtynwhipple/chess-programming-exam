package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenMovesCalculator {

    private final ChessBoard board;
    private final ChessPosition position;

    public QueenMovesCalculator(ChessPosition position, ChessBoard board) {
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

    public Collection<ChessMove> get_viable_moves() {
        List<ChessMove> viable_moves = new ArrayList<>();

        // create direction array

        int[][] directions = {
                {1,1},
                {-1,1},
                {-1,-1},
                {1,-1},
                {1,0},
                {-1,0},
                {0,-1},
                {0,1}
        };

        for (int[] direction: directions) {
            int col = position.getColumn();
            int row = position.getRow();

            while (true){
                col += direction[0];
                row += direction[1];

                if (!valid_move(new ChessPosition(row, col))){
                    break;
                }

                ChessPosition new_position = new ChessPosition(row, col);

                viable_moves.add(new ChessMove(position, new_position, null));
                if (board.getPiece(new_position) != null && is_opponent(board.getPiece(position), board.getPiece(new_position))){
                    break;
                }

            }

        }
        return viable_moves;
    }
}
