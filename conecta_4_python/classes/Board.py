import pprint


class Board:
    board = None
    columns = 7
    rows = 6
    MAX_SCORE = 10000

    def printBoard(self):
        print("")
        output = ""
        for row in range(0, self.rows):
            for column in range(0, self.columns):
                output += str(self.board[row][column]) + ", "
            output += "\n"
        print(output)
        print("")

    def initBoard(self):
        self.board = [[0 for x in range(self.columns)] for y in range(self.rows)]

    def __init__(self):
        self.initBoard()



    def isFinished(self):
        if self.isFull() or abs(self.getScore()) == self.MAX_SCORE:
            return True;
        return False

    def addChip(self, player, column):
        if not column:
            return False
        column = int(column) - 1
        if column < 0 or column >= self.columns:
            print("Bad input...")
            return False
        for row in range(self.rows - 1, -1, -1):
            if self.board[row][column] == 0:
                self.board[row][column] = player
                return True
        return False

    def isFull(self):
        for row in range(0, self.rows):
            for column in range(0, self.columns):
                if self.board[row][row] == 0:
                    return False
        return True

    def getVerticalPoints(self, row, column):
        oponentPoints = 0
        myPoints = 0
        for i in range(0, 4):
            if self.board[row][column] == 1:
                oponentPoints += 1
            elif self.board[row][column] == 2:
                myPoints += 1
            row += 1

        if oponentPoints == 4:
            return -self.MAX_SCORE
        if myPoints == 4:
            return self.MAX_SCORE

        return myPoints

    def getHorizontalPoints(self, row, column):
        oponentPoints = 0
        myPoints = 0

        for i in range(0, 4):
            if self.board[row][column] == 1:
                oponentPoints += 1
            elif self.board[row][column] == 2:
                myPoints += 1
            column += 1

        if oponentPoints == 4:
            return -self.MAX_SCORE
        if myPoints == 4:
            return self.MAX_SCORE

        return myPoints

    def getDiagonalLeftPoints(self, row, column):
        oponentPoints = 0
        myPoints = 0

        for i in range(0, 4):
            if self.board[row][column] == 1:
                oponentPoints += 1
            elif self.board[row][column] == 2:
                myPoints += 1
            column += 1
            row += 1

        if oponentPoints == 4:
            return -self.MAX_SCORE
        if myPoints == 4:
            return self.MAX_SCORE

        return myPoints

    def getDiagonalRightPoints(self, row, column):
        oponentPoints = 0
        myPoints = 0

        for i in range(0, 4):
            if self.board[row][column] == 1:
                oponentPoints += 1
            elif self.board[row][column] == 2:
                myPoints += 1
            column += 1
            row -= 1

        if oponentPoints == 4:
            return -10000
        if myPoints == 4:
            return 10000

        return myPoints

    def getScore(self):
        points = 0

        verticalPoints = 0
        horizontalPoints = 0
        diagonalLeftPoint = 0
        diagonalRightPoints = 0

        for row in range(0, self.rows - 3):
            for column in range(0, self.columns):
                score = self.getVerticalPoints(row, column)
                if abs(score) == self.MAX_SCORE:
                    return score
                verticalPoints += score

        for row in range(0, self.rows):
            for column in range(0, self.columns - 3):
                score = self.getHorizontalPoints(row, column)
                if abs(score) == self.MAX_SCORE:
                    return score
                horizontalPoints += score

        for row in range(0, self.rows - 3):
            for column in range(0, self.columns - 3):
                score = self.getDiagonalLeftPoints(row, column)
                if abs(score) == self.MAX_SCORE:
                    return score
                diagonalLeftPoint += score

        for row in range(3, self.rows):
            for column in range(0, self.columns - 4):
                score = self.getDiagonalRightPoints(row, column)
                if abs(score) == self.MAX_SCORE:
                    return score
                diagonalRightPoints += score

        return verticalPoints + horizontalPoints + diagonalLeftPoint + diagonalRightPoints
