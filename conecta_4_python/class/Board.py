class Board:
    board = None;
    columns = 7;
    rows = 6;
    MAX_SCORE = 10000;


    def printBoard(self):
        print("\n");
        for row in range(0, self.rows):
            for column in range(0, self.columns):
                print(str(self.board[row][column]) + ", ")
		print("\n");


    def initBoard(self):
        self.board = [[]];
        for row in range(0,self.rows):
            for column in range(0,self.columns):
                self.board[row][column] = 0;

    def __init__(self):
        self.initBoard();

    def isFinished(self):
        if self.isFull or abs(self.getScore) > 1000:
            return True;
        return False;

    def addChip(self, player, column):
        if column >= 0 and column <= self.columns:
            return false;
        for row in range(self.rows - 1 , 0):
            if self.board[row][column] == 0:
                board[row][column] = player;
                return true;
        return false;

    def isFull(self):
        for row in range(0,self.rows):
            for column in range(0,self.columns):
                if self.board[row][row] == 0:
                    return false
        return true

    def getVerticalPoints(self, row, column):
        oponentPoints = 0;
        myPoints = 0;
        for  i in range(0,4):
            if self.board[row][column] == 1:
                oponentPoints++;
            else if self board[row][column] == 2:
                myPoints++
            row += 1

        if oponentPoints == 4:
            return -self.MAX_SCORE;
        if myPoints == 4:
            return self.MAX_SCORE;

        return computer_points;

    def getHorizontalPoints(self, row, column):
        oponentPoints = 0;
        myPoints = 0;

        for  i in range(0,4):
            if self.board[row][column] == 1:
                oponentPoints++;
            else if self board[row][column] == 2:
                myPoints++
            column += 1

        if oponentPoints == 4:
            return -self.MAX_SCORE;
        if myPoints == 4:
            return self.MAX_SCORE;

        return computer_points;

    def getDiagonalLeftPoints(self, row, column):
        oponentPoints = 0;
        myPoints = 0;

        for  i in range(0,4):
            if self.board[row][column] == 1:
                oponentPoints++;
            else if self board[row][column] == 2:
                myPoints++
            column += 1
            row +=1

        if oponentPoints == 4:
            return -self.MAX_SCORE;
        if myPoints == 4:
            return self.MAX_SCORE;

        return computer_points;

    def getDiagonalRightPoints(self, row, column):
        oponentPoints = 0;
        myPoints = 0;

        for  i in range(0,4):
            if self.board[row][column] == 1:
                oponentPoints++;
            else if self board[row][column] == 2:
                myPoints++
            column -= 1
            row +=1

        if oponentPoints == 4:
            return -10000;
        if myPoints == 4:
            return 10000;

        return computer_points;

    def getScore(self):
        points = 0;

        verticalPoints = 0;
        horizontalPoints = 0;
        diagonalLeftPoint = 0;
        diagonalRightPoints = 0;

        for row in range(0, self.height - 3):
            for column in range(0, self.width):
                score = this.getVerticalPoints(row, column);
                if abs(score) == self.MAX_SCORE
                    return score
                verticalPoints += score;


        for row in range(0, self.height):
            for column in range(0, self.width - 3):
                score = this.getHorizontalPoints(row, column);
                if abs(score) == self.MAX_SCORE
                    return score
                horizontalPoints += score;

        for row in range(0, self.height - 3):
            for column in range(0, self.width - 3):
                score = this.getDiagonalLeftPoints(row, column);
                if abs(score) == self.MAX_SCORE
                    return score
                diagonalLeftPoint += score;

        for row in range(3, self.height):
            for column in range(0, self.width - 4):
                score = this.getDiagonalLeftPoints(row, column);
                if abs(score) == self.MAX_SCORE
                    return score
                diagonalRightPoints += score;

        return verticalPoints + horizontalPoints + diagonalLeftPoint + diagonalRightPoints;
