import pathlib


def load_data_from_file(fname):
    with open(str(pathlib.Path(__file__).parent.absolute()) + "\\\\" + fname) as file:
        content = file.read().split("\n")
    return content


def part1():
    data = load_data_from_file("map.txt")
    lows = list()
    for y in range(len(data)):
        for x in range(len(data[y])):
            # Build necessary (and possible) fields to check
            necessary_fields = list()
            # Check if field is at top border
            if y > 0:
                necessary_fields.append((y-1, x))
            # Check right
            if x < len(data[y]) - 1:
                necessary_fields.append((y, x+1))
            # Check bot
            if y < len(data) - 1:
                necessary_fields.append((y+1, x))
            # Check right
            if x > 0:
                necessary_fields.append((y, x-1))
            completed_checks = 0
            for check in necessary_fields:
                if data[check[0]][check[1]] > data[y][x]:
                    completed_checks += 1
            if completed_checks == len(necessary_fields):
                lows.append(int(data[y][x]))

    result = 0
    for low in lows:
        result += low + 1
    print(result)


def extract_data(pdata):
    data = list()
    for y in pdata:
        data.append([int(x) for x in y])
    return data


def mark_basins(pdata):
    data = pdata
    basin_counter = 0
    for yind in range(len(data)):
        for xind in range(len(data[yind])):
            # IDs will be numbers > 999
            if (not data[yind][xind] > 999) and data[yind][xind] != 9:
                data = path(data, yind, xind, basin_counter+1000)
            basin_counter += 1
    return data


def path(pdata, yind, xind, id):
    data = pdata
    # Set current field to id
    data[yind][xind] = id
    # Check if there is a top field. If there is a field that has not been marked and isnt a wall -> recursion
    if yind > 0:
        if (not data[yind-1][xind] > 999) and data[yind-1][xind] != 9:
            data = path(data, yind-1, xind, id)
    if xind < len(data[yind]) - 1:
        if (not data[yind][xind+1] > 999) and data[yind][xind+1] != 9:
            data = path(data, yind, xind+1, id)
    if yind < len(data) - 1:
        if (not data[yind+1][xind] > 999) and data[yind+1][xind] != 9:
            data = path(data, yind+1, xind, id)
    if xind > 0:
        if (not data[yind][xind-1] > 999) and data[yind][xind-1] != 9:
            data = path(data, yind, xind-1, id)
    return data


def count_basin_sizes(data):
    results = dict()
    for y in data:
        for x in y:
            if x > 999:
                if x in results:
                    results[x] += 1
                else:
                    results[x] = 1
    return results


def compute_product_of_3_largest(sizes):
    l = list(sizes.values())[:]
    l.sort(reverse=True)
    return l[0] * l[1] * l[2]


def part2():
    data = load_data_from_file("map.txt")
    clean = extract_data(data)
    marked_map = mark_basins(clean)
    sizes = count_basin_sizes(marked_map)
    result = compute_product_of_3_largest(sizes)
    print(result)


if __name__ == "__main__":
    # part1()
    part2()
