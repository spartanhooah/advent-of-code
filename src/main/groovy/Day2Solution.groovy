def horizontal = 0
def vertical = 0
def aim = 0

new File("Day2Input.txt").eachLine {
    def (direction, distance) = it.split()

    if (distance.isInteger()) {
        switch (direction) {
            case "forward":
                horizontal += distance.toInteger()
                vertical += aim * distance.toInteger()
                break
            case "down":
                aim += distance.toInteger()
                break
            case "up":
                aim -= distance.toInteger()
                break
            default:
                println "Got an invalid direction"
        }
    }
}

println horizontal * vertical