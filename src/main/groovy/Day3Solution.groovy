import static java.lang.Integer.parseInt

def report = []
def zerosCounts = []
def onesCounts = []
def firstLine = true

new File("TestInput.txt").eachLine {
    if (it.length() > 0) {
        report << it

        if (firstLine) {
            it.each { digit ->
                zerosCounts << 0
                onesCounts << 0
            }

            firstLine = false
        }

        for (int i : 0..it.length() - 1) {
            it[i] == "0" ? zerosCounts[i]++ : onesCounts[i]++
        }
    }
}

// Part 1
def gamma = ""
def epsilon = ""

for (i in 0..report[0].length() - 1) {
    gamma += (zerosCounts[i] > onesCounts[i]) ? "0" : "1"
    epsilon += (zerosCounts[i] > onesCounts[i]) ? "1" : "0"
}

println parseInt(gamma, 2) * parseInt(epsilon, 2)

// Part 2
def oxygenList = report.clone()
def oxygenRating = 0
def co2List = report.clone()
def co2Rating = 0

for (i in 0..report[0].length() - 1) {
    report.each {
        if (onesCounts[i] >= zerosCounts[i]) {
            if (it[i] == "0") {
                oxygenList.remove(it)
            } else {
                co2List.remove(it)
            }
        } else {
            if (it[i] == "1") {
                oxygenList.remove(it)
            } else {
                co2List.remove(it)
            }
        }

        if (oxygenList.size() == 1) {
            oxygenRating = parseInt(oxygenList[0], 2)
        }

        if (co2List.size() == 1) {
            co2Rating = parseInt(co2List[0], 2)
        }
    }
}

println oxygenRating * co2Rating