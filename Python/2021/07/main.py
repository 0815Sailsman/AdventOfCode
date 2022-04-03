def load_pos_data_from_file(fname):
    with open("Python/2021/07/" + fname) as file:
        content = file.read().split(",")
    return [int(x) for x in content]


# I know that this is bad and takes a few minutes, but it works :shrug:
def brute_force(positions, mode):
    positions.sort()
    print("sorted")
    big = positions[-1]
    all_costs = list()
    for possible_best in range(big):
        all_costs.append(calc_costs_to(positions, possible_best, mode))
    return all_costs.index(min(all_costs)), min(all_costs)


def calc_costs_to(positions, target, mode):
    cost = 0
    for pos in positions:
        if mode == 1:
            cost += abs(pos - target)
        elif mode == 2:
            cost += inflate(abs(pos - target))
    return cost


def inflate(temp_steps):
    cost = 0
    counter = 1
    for step in range(temp_steps):
        cost += counter
        counter += 1
    return cost


def part1():
    positions = load_pos_data_from_file("pos.txt")
    best_x, fuel_cost = brute_force(positions, 1)
    print(best_x)
    print(fuel_cost)


def part2():
    positions = load_pos_data_from_file("pos.txt")
    best_x, fuel_cost = brute_force(positions, 2)
    print(best_x)
    print(fuel_cost)


if __name__ == "__main__":
    # part1()
    part2()
