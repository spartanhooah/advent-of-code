def sums = []
def firstWindow = []
def secondWindow= []
def thirdWindow = []
def lineNum = 0

new File("TestInput.txt").eachLine {
    if (it.isInteger()) {
        handleLine(firstWindow, sums, it)

        if (lineNum > 0) {
            handleLine(secondWindow, sums, it)
        }

        if (lineNum > 1) {
            handleLine(thirdWindow, sums, it)
        }
    }

    lineNum++
}

if (firstWindow.size() == 3) {
    sums << firstWindow.sum()
}

if (secondWindow.size() == 3) {
    sums << secondWindow.sum()
}

if (thirdWindow.size() == 3) {
    sums << thirdWindow
}

private static void handleLine(ArrayList firstWindow, ArrayList sums, String line) {
    if (firstWindow.size() == 3) {
        sums << firstWindow.sum()
        firstWindow.clear()
    }

    firstWindow << line.toInteger()
}
