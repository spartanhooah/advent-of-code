import static java.lang.Integer.max
import static java.lang.Integer.parseInt

def lines = []
def maxX = 0
def maxY = 0

new File("Day5Input.txt").eachLine {
    if (it.length() > 0) {
        it.split(" -> ").collect {
            it.split(",").collect { parseInt(it) }
        }.collate(2).each {
            if (max(it[0][0], it[1][0]) > maxX) {
                maxX = max(it[0][0], it[1][0])
            }

            if (max(it[0][1], it[1][1]) > maxY) {
                maxY = max(it[0][1], it[1][1])
            }

            lines << new Line(a: new Point(x: it[0][0], y: it[0][1]), b: new Point(x: it[1][0], y: it[1][1]))
        }
    }
}

def diagram = new int[maxY + 1][maxX + 1]

lines.each { line ->
    def points = line.generatePoints()

    points.each { point ->
        diagram[point.y][point.x]++
    }
}

def total = 0

for (int i : 0..maxY) {
    for (int j : 0..maxX) {
//        print diagram[i][j] == 0 ? "." : "${diagram[i][j]}"
        if (diagram[i][j] > 1) {
            total++
        }
    }
    println()
}

println total

class Line {
    Point a
    Point b

    def generatePoints() {
        def containedPoints = []

        def xs = a.x..b.x
        def ys = a.y..b.y

        if (xs.size() == 1) { // vertical line
            ys.each {
                containedPoints << new Point(x: xs[0], y: it)
            }
        } else if (ys.size() == 1) { // horizontal line
            xs.each {
                containedPoints << new Point(x: it, y: ys[0])
            }
        } else { // diagonal line
            (0..xs.size() - 1).each {
                containedPoints << new Point(x: xs[it], y: ys[it])
            }
        }

        return containedPoints
    }
}

class Point {
    def x
    def y
}