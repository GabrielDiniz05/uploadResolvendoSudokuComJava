public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args){
        //Aqui você coloca a tabela do sudoku que deseja resolver;

        int [][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        if(solveBoard(board)){
            System.out.println("Solved Successfully!");
        }else{
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);
    }

    /**
    Um método que irá percorrer a tabela e printa-lá;
     */
    private static void printBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    /**
    Um método para verificar se o número que nós escolhemos está na Linha/Row(Horizontal);
     */
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    /**
    Um método para verificar se o número que nós escolhemos está na Coluna/Column(Vertical);
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    /**
    Um método para verificar se o número que nós escolhemos está na Caixa/Box(3 por 3);
     */
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    /**
    Um método para verificar se o local na tabela é válido, note que se usa os 3 métodos anteriores para a verificação;
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    /**
    Um método para resolver o sudoku;

    Primeiramente se utiliza um FOR para percorrer as Linhas/Row da tabela, e outro FOR para percorrer as Colunas/Column;

    Após isso fazemos uma verificação para saber se o número que está na Linha e Coluna é igual a zero,
        Se SIM:{
            Se utiliza um FOR para percorrer de 1 a 9 (esses são os números que serão colocados na tabela);}
            Verificação para saber se o local é válido,
                Se SIM:{
                    Colocamos o número;}

                    Verificação para saber se o método irá retornar TRUE ou FALSE (Mesmo se o local for válido para o posicionamento de um número,
                    pode não ser o número correto para resolver  sudoku, para que não ocorra, isso chamamos o método novamente e verificamos: se retornar FALSE o local volta a ser 0):
                        Se SIM:{
                            Retorna TRUE;}

    O método por "padrão" retorna TRUE;
     */
    private static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;

                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
