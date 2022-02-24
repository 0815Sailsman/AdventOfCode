MOST_FREQUENT = 0
LEAST_FREQUENT = 1


def load_report(fname):
    with open("2021/03/" + fname) as file:
        report = file.read().split("\n")
    return report


def check_character_at_index(report, index, mode):
    count0 = 0
    count1 = 0
    for number in report:
        if str(number)[index] == "0":
            count0 += 1
        else:
            count1 += 1
    if mode == MOST_FREQUENT:
        if count0 > count1:
            return 0
        return 1
    else:
        if count0 <= count1:
            return 0
        return 1


def part1():
    report = load_report("report.txt")
    gamma = ""
    epsilon = ""
    for i in range(len(report[0])):
        gamma += str(check_character_at_index(report, i, MOST_FREQUENT))
        epsilon += str(check_character_at_index(report, i, LEAST_FREQUENT))
    return int(gamma, 2) * int(epsilon, 2)


def extract_entries_with_bit_at_index(report, index, bit):
    new = list()
    if len(report) == 1:
        return report
    for entry in report:
        if str(entry)[index] == str(bit):
            new.append(entry)
    return new


def part2():
    report = load_report("report.txt")
    oxygen = report.copy()
    co2 = report.copy()
    for i in range(len(report[0])):
        mfb = check_character_at_index(oxygen, i, MOST_FREQUENT)
        lfb = check_character_at_index(co2, i, LEAST_FREQUENT)
        oxygen = extract_entries_with_bit_at_index(oxygen, i, mfb)
        co2 = extract_entries_with_bit_at_index(co2, i, lfb)
    return int(oxygen[0], 2) * int(co2[0], 2)


if __name__ == "__main__":
    # print(part1())
    print(part2())
