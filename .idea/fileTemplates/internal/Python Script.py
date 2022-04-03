import pathlib


def load_data_from_file(fname):
    with open(str(pathlib.Path(__file__).parent.absolute()) + "\\\\" + fname) as file:
        content = file.read().split(",")
    return content


def part1():
    pass


def part2():
    pass


if __name__ == "__main__":
    part1()
    # part2()
