def depths = []

new File("DepthInput.txt").eachLine {
    if (it.isInteger()) {
        def depth = it.toInteger()
        depths << depth
    }
}

def j = 3
def k = 4

for (def i = 2; i < depths.size(); i++) {

}