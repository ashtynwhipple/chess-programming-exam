package chess.moves;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator {

    private final ChessBoard board;
    private final ChessPosition position;

    public PawnMovesCalculator(ChessPosition position, ChessBoard board) {
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

//    public ChessMove addPromotion (ChessMove move){
//        if (move.getPromotionPiece() == null && move.getEndPosition().getRow() == 1 || move.getPromotionPiece() == null && move.getEndPosition().getRow() == 8){
//            for (ChessPiece.PieceType type: ChessPiece.PieceType.values()){
//                if (type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
//                    return new ChessMove(move.getStartPosition(), move.getEndPosition(), type);
//                }
//            }
//        }
//    }

    public Collection<ChessMove> get_viable_moves() {
        List<ChessMove> viable_moves = new ArrayList<>();

        int col = position.getColumn(); // gets col
        int row = position.getRow(); // gets row


        if (board.getPiece(position).getTeamColor() == ChessGame.TeamColor.WHITE) {

            int new_row = row + 1;

            if (board.getPiece(new ChessPosition(new_row, col)) == null) {
                ChessPosition new_pos = new ChessPosition(new_row, col);
                viable_moves.add(new ChessMove(position, new_pos, null));

                int jump_row = row + 2;

                if (is_in_bounds(jump_row, col) && row == 2){
                    ChessPosition jump_pos = new ChessPosition(jump_row, col);

                    if (board.getPiece(jump_pos) == null) {
                        viable_moves.add(new ChessMove(position, jump_pos, null));
                    }
                }

            }
            int[][] directions = {
                    {1,1},
                    {-1,1}
            };


            for (int[] direction: directions) { // checks for diagonal
                int new_col2 = col + direction[0];
                int new_row2 = row + direction[1];

                if (valid_move(new ChessPosition(new_row2, new_col2))) {

                    ChessPosition new_pos2 = new ChessPosition(new_row2, new_col2);

                    if (is_opponent(board.getPiece(new_pos2), board.getPiece(position))){

                        viable_moves.add(new ChessMove(position, new_pos2, null));
                    }
                }
            }
        }
        if (board.getPiece(position).getTeamColor() == ChessGame.TeamColor.BLACK) {

            int new_row = row - 1;

            if (board.getPiece(new ChessPosition(new_row, col)) == null) {
                ChessPosition new_pos = new ChessPosition(new_row, col);
                viable_moves.add(new ChessMove(position, new_pos, null));

                int jump_row = row - 2;

                if (is_in_bounds(jump_row, col) && row == 7){
                    ChessPosition jump_pos = new ChessPosition(jump_row, col);

                    if (board.getPiece(jump_pos) == null) {
                        viable_moves.add(new ChessMove(position, jump_pos, null));
                    }
                }

            }


            int[][] directions = {
                    {-1,-1},
                    {1,-1}
            };

            for (int[] direction: directions) { // checks for diagonal
                int new_col2 = col + direction[0];
                int new_row2 = row + direction[1];

                if (valid_move(new ChessPosition(new_row2, new_col2))) {

                    ChessPosition new_pos2 = new ChessPosition(new_row2, new_col2);

                    if (is_opponent(board.getPiece(new_pos2), board.getPiece(position))){

                        viable_moves.add(new ChessMove(position, new_pos2, null));
                    }
                }
            }
        }

//        for (ChessMove move: viable_moves) {
//            if (move.getPromotionPiece() == null && move.getEndPosition().getRow() == 1 || move.getPromotionPiece() == null && move.getEndPosition().getRow() == 8){
//                for (ChessPiece.PieceType type: ChessPiece.PieceType.values()){
//                    if (type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
//                        viable_moves.add(new ChessMove(move.getStartPosition(), move.getEndPosition(), type));
//                    }
//                }
//            }
//        }

        List<ChessMove> new_moves = new ArrayList<>();

        for (ChessMove move: viable_moves) {
            if (move.getPromotionPiece() == null && move.getEndPosition().getRow() == 1 || move.getPromotionPiece() == null && move.getEndPosition().getRow() == 8){
                for (ChessPiece.PieceType type: ChessPiece.PieceType.values()){
                    if (type != ChessPiece.PieceType.KING && type != ChessPiece.PieceType.PAWN) {
                        new_moves.add(new ChessMove(move.getStartPosition(), move.getEndPosition(), type));
                    }
                }
            }
        }

        for (ChessMove new_move: new_moves){
            viable_moves.add(new_move);
        }

        viable_moves.removeIf(move -> move.getEndPosition().getRow() == 1 && move.getPromotionPiece() == null || move.getEndPosition().getRow() == 8 && move.getPromotionPiece() == null);

        return viable_moves;
    }
}
