def load_depths(fname):
    with open("Python/2021/01/" + fname) as file:
        depths = [int(x) for x in file.read().split("\n")]
    return depths


def calc_increases(plist):
    increases = 0
    for i in range(0, len(plist) - 1):
        if plist[i+1] > plist[i]:
            increases += 1
    return increases


def part1():
    depths = load_depths("depths.txt")
    return calc_increases(depths)


def calc_sums(single_depths):
    sums = list()
    for i in range(len(single_depths) - 2):
        first = single_depths[i]
        second = single_depths[i+1]
        third = single_depths[i+2]
        sums.append(first + second + third)
    return sums


def part2():
    depths = load_depths("depths.txt")
    sums = calc_sums(depths)
    increases = calc_increases(sums)
    return increases


if __name__ == "__main__":
    # print(part1())
    print(part2())
