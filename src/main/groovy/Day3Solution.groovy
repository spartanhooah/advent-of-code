import static java.lang.Integer.parseInt

def report = []

new File("TestInput.txt").eachLine {
    if (it.length() > 0) {
        report << it
    }
}

// Part 1
def gamma = ""
def epsilon = ""
def zerosCount = 0
def onesCount = 0

for (i in 0..report[0].length() - 1) {
    report.each {
        switch(it[i]) {
            case "0":
                zerosCount++
                break
            case "1":
                onesCount++
                break
            default:
                break
        }
    }

    gamma += (zerosCount > onesCount) ? "0" : "1"
    epsilon += (zerosCount > onesCount) ? "1" : "0"

    zerosCount = 0
    onesCount = 0
}

println parseInt(gamma, 2) * parseInt(epsilon, 2)

// Part 2
def oxygenList = []
def oxygenRating = 0
def co2List = []
def co2Rating = 0

for (i in 0..report[0].length() - 1) {
    report.each {
        switch (it[i]) {
            case "0":
                zerosCount++
                break
            case "1":
                onesCount++
                break
            default:
                break
        }
    }

    report.each {
        if (zerosCount > onesCount) {
            if (it[i] == "0") {
                oxygenList << it
            } else {
                co2List << it
            }
        } else {
            if (it[i] == "1") {
                oxygenList << it
            } else {
                co2List << it
            }
        }
    }

    zerosCount = 0
    onesCount = 0
}