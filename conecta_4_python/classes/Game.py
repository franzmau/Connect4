from Board import Board
from SerialManager import SerialManager
from Player import Player

class Game:

    board = None;
    p1 = None;
    p2 = None;

    def __init__(self):
        self.board = Board();
        self.p1 = Player(True, 1)
        self.p2 = Player(False, 2)

    def startGame(self):
        playing = True;
        isPlayer1Turn = True;
        self.board.initBoard();
        print("Serial initial!!!");
        sendManager = SerialManager();
        sendManager.sendNumber('c');
        while playing:
            self.board.printBoard();
            toMove = -1;
            if isPlayer1Turn:
                print("P1 turn...")
                while not self.board.addChip(self.p1.identifier, toMove):
                    toMove = self.p1.getPlayerMove(self.board);
            else:
                print("Computer turn...")
                while not self.board.addChip(self.p2.identifier, toMove):
                    toMove = self.p2.getPlayerMove(self.board);
            if toMove >= 0:
                print("Serial!!!");
                sendManager = SerialManager();
                sendManager.sendNumber(toMove);

            isPlayer1Turn = not isPlayer1Turn;
            score = self.board.getScore();
            if self.board.isFinished():
                playing = False;
                print("Alguien gano");
                if score > 1000:
                    print("Gano la computadora");
                else:
                    print("Ganaste");
