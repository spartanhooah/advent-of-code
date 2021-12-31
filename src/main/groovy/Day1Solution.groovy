def sums = []
def firstWindow = []
def secondWindow= []
def thirdWindow = []
def lineNum = 0

new File("DepthInput.txt").eachLine {
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
    sums << thirdWindow.sum()
}

def largerCount = 0

for (def i = 0; i < sums.size() - 1; i++) {
    if (sums[i] < sums[i + 1]) {
        largerCount++
    }
}

println largerCount

private static void handleLine(ArrayList firstWindow, ArrayList sums, String line) {
    if (firstWindow.size() == 3) {
        sums << firstWindow.sum()
        firstWindow.clear()
    }

    firstWindow << line.toInteger()
}
