import static java.lang.Integer.parseInt

def heightMap = [:]

def index = 0
new File("Day9Input.txt").eachLine {
    heightMap[index++] = it.split("").collect {
        parseInt(it)
    }
}

def maxX = heightMap[0].size() - 1
def maxY = index - 1

def lows = []

heightMap.eachWithIndex { entry, i ->
    (0..maxX).each {j ->
        def currentHeight = heightMap[i][j]

        if (i == 0 && j == 0) { // top-left corner
            if (currentHeight < heightMap[i + 1][j] && currentHeight < heightMap[i][j + 1]) {
                lows << currentHeight
            }
        } else if (i == 0 && j == maxX) { // top-right corner
            if (currentHeight < heightMap[i + 1][j] && currentHeight < heightMap[i][j - 1]) {
                lows << currentHeight
            }
        } else if (i == maxY && j == 0) { // bottom-left corner
            if (currentHeight < heightMap[i - 1][j] && currentHeight < heightMap[i][j + 1]) {
                lows << currentHeight
            }
        } else if (i == maxY && j == maxY) { // bottom-right corner
            if (currentHeight < heightMap[i - 1][j] && currentHeight < heightMap[i][j - 1]) {
                lows << currentHeight
            }
        } else if (i == 0) { // top row
            if (currentHeight < heightMap[i][j - 1] && currentHeight < heightMap[i][j + 1] && currentHeight < heightMap[i + 1][j]) {
                lows << currentHeight
            }
        } else if (i == maxY) { // bottom row
            if (currentHeight < heightMap[i][j - 1] && currentHeight < heightMap[i][j + 1] && currentHeight < heightMap[i - 1][j]) {
                lows << currentHeight
            }
        } else if (j == 0) { // left side
            if (currentHeight < heightMap[i - 1][j] && currentHeight < heightMap[i + 1][j] && currentHeight < heightMap[i][j + 1]) {
                lows << currentHeight
            }
        } else if (j == maxX) { // right side
            if (currentHeight < heightMap[i - 1][j] && currentHeight < heightMap[i + 1][j] && currentHeight < heightMap[i][j - 1]) {
                lows << currentHeight
            }
        } else { // non-border cells
            if (currentHeight < heightMap[i - 1][j] && currentHeight < heightMap[i + 1][j] && currentHeight < heightMap[i][j - 1] && currentHeight < heightMap[i][j + 1]) {
                lows << currentHeight
            }
        }
    }
}

def result = lows.sum() + lows.size()
println result