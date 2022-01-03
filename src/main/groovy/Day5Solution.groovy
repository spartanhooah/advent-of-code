import static java.lang.Integer.max
import static java.lang.Integer.parseInt

def lines = []
def maxX = 0
def maxY = 0

new File("TestInput.txt").eachLine {
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
    if (line.a.x == line.b.x) {
        if (line.b.y > line.a.y) {
            for (int i : line.a.y..line.b.y) {
                diagram[i][line.a.x]++
            }
        } else {
            for (int i : line.b.y..line.a.y) {
                diagram[i][line.a.x]++
            }
        }
    } else if (line.a.y == line.b.y) {
        if (line.b.x > line.a.x) {
            for (int i : line.a.x..line.b.x) {
                diagram[line.a.y][i]++
            }
        } else {
            for (int i : line.b.x..line.a.x) {
                diagram[line.a.y][i]++
            }
        }
    }
}

def total = 0

for (int i : 0..maxY) {
    for (int j : 0..maxX) {
        print diagram[i][j] == 0 ? "." : "${diagram[i][j]}"
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
}

class Point {
    def x
    def y
}
