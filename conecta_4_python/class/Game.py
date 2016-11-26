
class Game:

    board = None;
    p1 = None;
    p2 = None;

    def __init__(self):
        self.board = new Board();
        p1 = new Player(true, 1)
        p2 = new Player(false, 2)

    def startGame(self):
        playing = True;
        isPlayer1Turn = True;
        self.board.initBoard();
        self.board.printBoard();
        while playing:
            toMove = -1;
            if isPlayer1Turn:
                print("P1 turn...")
                while not self.board.addChip(p1.identifier, toMove):
                    toMove = p1.getMove(board);
            else:
                print("Computer turn...")
                while not self.board.addChip(p2.identifier, toMove):
                    toMove = p2.getMove(board);
            if toMove >= 0:
                print("Serial!!!");

            isPlayer1Turn = !isPlayer1Turn;
            score = self.board.getScore();
            print ("Score: " + score);
            if self.board.isFinished():
                playing = False;
                print("Alguien gano");
                if score > 1000:
                    print("Gano la computadora");
                else:
                    print("Ganaste");
