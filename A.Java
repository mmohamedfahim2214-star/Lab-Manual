import java.util.*;

// Chess Game using OOP Concepts

abstract class Piece {
    protected boolean white;
    protected char symbol;

    public Piece(boolean white, char symbol) {
        this.white = white;
        this.symbol = symbol;
    }

    public boolean isWhite() {
        return white;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract boolean isValidMove(int sr, int sc, int er, int ec);
}

class King extends Piece {
    public King(boolean white) {
        super(white, white ? 'K' : 'k');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        return Math.abs(sr - er) <= 1 && Math.abs(sc - ec) <= 1;
    }
}

class Queen extends Piece {
    public Queen(boolean white) {
        super(white, white ? 'Q' : 'q');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        return sr == er || sc == ec || Math.abs(sr - er) == Math.abs(sc - ec);
    }
}

class Rook extends Piece {
    public Rook(boolean white) {
        super(white, white ? 'R' : 'r');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        return sr == er || sc == ec;
    }
}

class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white, white ? 'B' : 'b');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        return Math.abs(sr - er) == Math.abs(sc - ec);
    }
}

class Knight extends Piece {
    public Knight(boolean white) {
        super(white, white ? 'N' : 'n');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        int dr = Math.abs(sr - er);
        int dc = Math.abs(sc - ec);
        return (dr == 2 && dc == 1) || (dr == 1 && dc == 2);
    }
}

class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white, white ? 'P' : 'p');
    }

    public boolean isValidMove(int sr, int sc, int er, int ec) {
        if (white) {
            return ec == sc && er == sr - 1;
        } else {
            return ec == sc && er == sr + 1;
        }
    }
}

class Board {
    private Piece[][] board = new Piece[8][8];

    public Board() {
        setupBoard();
    }

    private void setupBoard() {
        // Black Pieces
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++)
            board[1][i] = new Pawn(false);

        // White Pieces
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        for (int i = 0; i < 8; i++)
            board[6][i] = new Pawn(true);
    }

    public void displayBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null)
                    System.out.print(". ");
                else
                    System.out.print(board[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public boolean movePiece(int sr, int sc, int er, int ec) {
        if (sr < 0 || sr >= 8 || sc < 0 || sc >= 8 ||
            er < 0 || er >= 8 || ec < 0 || ec >= 8) {
            return false;
        }

        Piece piece = board[sr][sc];

        if (piece == null)
            return false;

        if (piece.isValidMove(sr, sc, er, ec)) {
            board[er][ec] = piece;
            board[sr][sc] = null;
            return true;
        }

        return false;
    }
}

public class ChessGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            board.displayBoard();

            System.out.println("\nEnter move:");
            System.out.print("Source Row: ");
            int sr = sc.nextInt();
            System.out.print("Source Col: ");
            int scol = sc.nextInt();
            System.out.print("Destination Row: ");
            int er = sc.nextInt();
            System.out.print("Destination Col: ");
            int ecol = sc.nextInt();

            if (board.movePiece(sr, scol, er, ecol))
                System.out.println("Move Successful!");
            else
                System.out.println("Invalid Move!");

            System.out.print("Continue? (y/n): ");
            char ch = sc.next().charAt(0);
            if (ch == 'n' || ch == 'N')
                break;
        }

        sc.close();
    }
}
