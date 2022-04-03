import pathlib
from DisplayClass import DisplayClass


def load_data_from_file(fname):
    with open(str(pathlib.Path(__file__).parent.absolute()) + "\\\\" + fname) as file:
        content = file.read().split("\n")
    return content


def extract_relevant_data(file_data):
    temp = list()
    for entry in file_data:
        temp.append(entry.split("|")[1].split(" "))
    return temp


def count_relevant_occurrences(data):
    total = 0
    for row in data:
        for entry in row:
            if is_valid_value(entry):
                total += 1
    return total


def is_valid_value(value):
    length = len(value)
    if length == 2 or length == 4 or length == 3 or length == 7:
        return True
    return False


def part1():
    file_data = load_data_from_file("7-segment-data.txt")
    # Reduce every line to output values
    data = extract_relevant_data(file_data)
    print(count_relevant_occurrences(data))


def part2():
    file_data = load_data_from_file("7-segment-data.txt")
    total = 0
    for line in file_data:
        string = line.replace("| ", "")
        data = string.split(" ")[0:10]
        display = DisplayClass(data)
        solution = ""
        for word in string.split(" ")[10:]:
            solution += str(display.convert_code_to_dec(word))
        total += int(solution)
    print(total)


if __name__ == "__main__":
    # part1()
    part2()
