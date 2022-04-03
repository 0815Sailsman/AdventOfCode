import logging
import time


def load_fish_data_from_file(fname):
    with open("Python/2021/06/" + fname) as file:
        content = file.read().split(",")
    return content


def reproduce(ages):
    stage1 = time.perf_counter()
    new_ages = [x-1 for x in ages]
    stage2 = time.perf_counter()
    new_fish = new_ages.count(-1)
    new_ages.sort()
    stage3 = time.perf_counter()
    # new_ages = [x for x in new_ages if x is not -1]
    # new_ages = [x for x in new_ages if x > -1]
    # new_ages = list(filter((-1).__ne__, new_ages))
    stage4 = time.perf_counter()
    new_ages.extend(new_fish * [6] + new_fish * [8])
    stage5 = time.perf_counter()
    print("1 " + str(stage2 - stage1))
    print("2 " + str(stage3 - stage2))
    print("3 " + str(stage4 - stage3))
    print("4 " + str(stage5 - stage4))
    return new_ages


def part1():
    ages = load_fish_data_from_file("fish.txt")
    ages = [int(x) for x in ages]
    logging.info(str(ages))
    for day in range(80):
        ages = reproduce(ages)
        print(day)
    return len(ages)


def smart_reproduce(fishes):
    num = fishes[0]
    for i in range(8):
        fishes[i] = fishes[i+1]
    fishes[6] += num
    fishes[8] = num
    return fishes


def part2():
    ages = load_fish_data_from_file("fish.txt")
    ages = [int(x) for x in ages]
    logging.info(str(ages))
    fishes = [ages.count(x) for x in range(9)]
    for day in range(256):
        # print(fishes, sum(fishes))
        fishes = smart_reproduce(fishes)
    return sum(fishes)


if __name__ == "__main__":
    logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.DEBUG)
    logging.info(str(part1()))
    # logging.info(str(part2()))
