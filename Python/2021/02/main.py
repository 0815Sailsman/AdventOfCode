def load_instructions(fname):
    with open("Python/2021/02/" + fname) as file:
        instructions = file.read().split("\n")
    return instructions


def apply_action1(depth, horizontal, action):
    operation, value = action.split(" ")
    value = int(value)
    if operation == "forward":
        horizontal += value
    elif operation == "down":
        depth += value
    elif operation == "up":
        depth -= value
    return depth, horizontal


def part1():
    instructions = load_instructions("instructions.txt")
    depth = 0
    horizontal = 0
    for instruction in instructions:
        depth, horizontal = apply_action1(depth, horizontal, instruction)
    return depth * horizontal


def apply_action2(depth, horizontal, aim, action):
    operation, value = action.split(" ")
    value = int(value)
    if operation == "forward":
        horizontal += value
        depth += value * aim
    elif operation == "down":
        aim += value
    elif operation == "up":
        aim -= value
    return depth, horizontal, aim


def part2():
    instructions = load_instructions("instructions.txt")
    depth = 0
    horizontal = 0
    aim = 0
    for instruction in instructions:
        depth, horizontal, aim = apply_action2(depth, horizontal, aim, instruction)
    return horizontal * depth

if __name__ == "__main__":
    # print(part1())
    print(part2())