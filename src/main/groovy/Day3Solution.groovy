import static java.lang.Integer.parseInt

def report = []
def zerosCounts = []
def onesCounts = []
def firstLine = true
def lineLength = 0

new File("Day3Input.txt").eachLine {
    if (it.length() > 0) {
        lineLength = it.length()
        report << it

        firstLine = countDigits(firstLine, it, zerosCounts, onesCounts)
    }
}

// Part 1
def gamma = ""
def epsilon = ""

for (i in 0..lineLength - 1) {
    gamma += (zerosCounts[i] > onesCounts[i]) ? "0" : "1"
    epsilon += (zerosCounts[i] > onesCounts[i]) ? "1" : "0"
}

println parseInt(gamma, 2) * parseInt(epsilon, 2)

// Part 2
def oxygenList = report.clone()
def oxygenRating = 0
def co2List = report.clone()
def co2Rating = 0

for (i in 0..lineLength - 1) {
    firstLine = true
    oxygenList = filter(oxygenList, firstLine, true, zerosCounts, onesCounts, i)

    if (oxygenList.size() == 1) {
        oxygenRating = parseInt(oxygenList[0], 2)
    }

    co2List = filter(co2List, firstLine, false, zerosCounts, onesCounts, i)

    if (co2List.size() == 1) {
        co2Rating = parseInt(co2List[0], 2)
    }
}

println oxygenRating * co2Rating

static def countDigits(firstLine, line, zerosCounts, onesCounts) {
    if (firstLine) {
        line.each { digit ->
            zerosCounts << 0
            onesCounts << 0
        }

        firstLine = false
    }

    for (int i : 0..line.length() - 1) {
        line[i] == "0" ? zerosCounts[i]++ : onesCounts[i]++
    }

    return firstLine
}

static def filter(list, firstLine, oxygen, zerosCounts, onesCounts, index) {
    zerosCounts.clear()
    onesCounts.clear()
    def keepList = []

    list.each {
        firstLine = countDigits(firstLine, it, zerosCounts, onesCounts)
    }

    list.each {
        if (oxygen) {
            if (onesCounts[index] >= zerosCounts[index]) {
                if (it[index] == "1") {
                    keepList << it
                }
            } else {
                if (it[index] == "0") {
                    keepList << it
                }
            }
        } else {
            if (onesCounts[index] < zerosCounts[index]) {
                if (it[index] == "1") {
                    keepList << it
                }
            } else {
                if (it[index] == "0") {
                    keepList << it
                }
            }
        }
    }

    return keepList
}