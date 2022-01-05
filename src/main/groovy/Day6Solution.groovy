// Test input
//def input = [3, 4, 3, 1, 2]

// Real input
def input = [3, 5, 3, 1, 4, 4, 5, 5, 2, 1, 4, 3, 5, 1, 3, 5, 3, 2, 4, 3, 5, 3, 1, 1, 2, 1, 4, 5, 3, 1, 4, 5, 4, 3, 3, 4, 3, 1, 1, 2, 2, 4, 1, 1, 4, 3, 4, 4, 2, 4, 3, 1, 5, 1, 2, 3, 2, 4, 4, 1, 1, 1, 3, 3, 5, 1, 4, 5, 5, 2, 5, 3, 3, 1, 1, 2, 3, 3, 3, 1, 4, 1, 5, 1, 5, 3, 3, 1, 5, 3, 4, 3, 1, 4, 1, 1, 1, 2, 1, 2, 3, 2, 2, 4, 3, 5, 5, 4, 5, 3, 1, 4, 4, 2, 4, 4, 5, 1, 5, 3, 3, 5, 5, 4, 4, 1, 3, 2, 3, 1, 2, 4, 5, 3, 3, 5, 4, 1, 1, 5, 2, 5, 1, 5, 5, 4, 1, 1, 1, 1, 5, 3, 3, 4, 4, 2, 2, 1, 5, 1, 1, 1, 4, 4, 2, 2, 2, 2, 2, 5, 5, 2, 4, 4, 4, 1, 2, 5, 4, 5, 2, 5, 4, 3, 1, 1, 5, 4, 5, 3, 2, 3, 4, 1, 4, 1, 1, 3, 5, 1, 2, 5, 1, 1, 1, 5, 1, 1, 4, 2, 3, 4, 1, 3, 3, 2, 3, 1, 1, 4, 4, 3, 2, 1, 2, 1, 4, 2, 5, 4, 2, 5, 3, 2, 3, 3, 4, 1, 3, 5, 5, 1, 3, 4, 5, 1, 1, 3, 1, 2, 1, 1, 1, 1, 5, 1, 1, 2, 1, 4, 5, 2, 1, 5, 4, 2, 2, 5, 5, 1, 5, 1, 2, 1, 5, 2, 4, 3, 2, 3, 1, 1, 1, 2, 3, 1, 4, 3, 1, 2, 3, 2, 1, 3, 3, 2, 1, 2, 5, 2]

def fishes = [
        0: BigInteger.ZERO,
        1: BigInteger.ZERO,
        2: BigInteger.ZERO,
        3: BigInteger.ZERO,
        4: BigInteger.ZERO,
        5: BigInteger.ZERO,
        6: BigInteger.ZERO,
        7: BigInteger.ZERO,
        8: BigInteger.ZERO
] as TreeMap

input.each {
    fishes[it]++
}

(1..256).each {
    def tempOnes = fishes[1]
    def tempZeros = fishes[0]

    for (int i in 1..7) {
        fishes[i] = fishes[i + 1]
    }

    if (fishes[0] != 0) {
        fishes[8] = fishes[0]
        fishes[6] += tempZeros
    } else {
        fishes[8] = 0
    }

    fishes[0] = tempOnes
}

def sum = fishes.inject(0) { acc, it ->
    acc + it.value
}

println sum