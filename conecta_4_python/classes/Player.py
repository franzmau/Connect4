from GameData import GameData
from Board import Board
import copy;


class Player:

    MAX_DEPTH = 1;

    def __init__(self, isHuman, identifier):
        self.isHuman = isHuman;
        self.identifier = identifier;

    def getPlayerMove(self, board):
        if self.isHuman:
		    return self.getHumanMove();
        return self.getComputerMove(board);

    def getHumanMove(self):
        number = input("Enter the column to play")
        return number;


    def getComputerMove(self, board):
        print("THINKING...");
        output = self.minMax(board,True, self.MAX_DEPTH, - sys.maxint - 1, sys.maxint);
        print(output.column);
        return output.column;

    def minMax(self, board, isPlaying, depth, alpha, beta):
        output = GameData();
        score = board.getScore();

        currentPlayer = 1
        if isPlaying:
            currentPlayer = 2

        if board.isFinished() or depth <= 0:
            output.column = None;
            output.score = score;
            return output;

        for i in range(0, board.columns):
            b = copy.deepcopy(board);
            if b.addChip(currentPlayer, i):
                nextMove = self.minMax(b, not isPlaying, depth - 1, alpha,beta);
                if isPlaying:
                    if output.column == None or nextMove.score > output.score:
                        output.column = i;
                        alpha = output.score = nextMove.score;
                else:
                    if output.column == None or nextMove.score < output.score:
                        output.column = i;
                        alpha = output.score = nextMove.score;
            if (alpha >= beta):
                break;

        return output;
