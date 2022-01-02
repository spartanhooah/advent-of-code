import static java.lang.Integer.parseInt

def lineNumber = 0
def drawnNumbers
def boards = []
def currentBoard = new Board()

new File("Day4Input.txt").eachLine {
    if (it.length() > 0) {
        if (lineNumber == 0) {
            drawnNumbers = it.trim().split(",").collect { parseInt(it) }
        } else {
            currentBoard.addRow(it.trim().split("\\s+"))

            if (lineNumber % 5 == 0) {
                boards << currentBoard
                currentBoard = new Board()
            }
        }

        lineNumber++
    }
}

// Part 1
def anyWon = false

for (int number : drawnNumbers) {
    if (anyWon) {
        break
    }

    boards*.markNumber(number)

    for (Board board : boards) {
        if (board.hasWon()) {
            anyWon = true
            println board.total() * number

            break
        }
    }
}

// Part 2
for (int number : drawnNumbers) {
    boards*.markNumber(number)

    def removedBoards = boards.findAll { board ->
        board.hasWon()
    }

    boards.removeAll(removedBoards)

    if (!boards) {
        println removedBoards[0].total() * number
        break
    }
}

class Board {
    private def rows = []

    def addRow(row) {
        def newRow = []

        row.each {
            newRow << new Space(number: parseInt(it), marked: false)
        }

        rows << newRow
    }

    def markNumber(number) {
        rows.each { row ->
            row.each { space ->
                if (space.number == number) {
                    space.marked = true
                }
            }
        }
    }

    boolean hasWon() {
        def columns = fillColumns()

        def winningRow = rows.any { row ->
            row.every { space ->
                space.marked
            }
        }

        def winningColumn = columns.any { column ->
            column.every { space ->
                space.marked
            }
        }

        return winningRow || winningColumn
    }

    def fillColumns() {
        def columns = []

        for (int i : 0..rows.size() - 1) {
            columns << []

            for (int j : 0..rows[0].size() - 1) {
                columns[i][j] = rows[j][i]
            }
        }

        return columns
    }

    int total() {
        def total = 0

        rows.each { row ->
            total += row.findAll { !it.marked }.inject(0) { acc, val -> acc + val.number }
        }

        return total
    }

    private class Space {
        int number
        boolean marked
    }
}