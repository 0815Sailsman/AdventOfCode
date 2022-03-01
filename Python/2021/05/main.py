def read_vents_from_file(fname):
    with open("Python/2021/05/" + fname) as file:
        content = file.read().split("\n")
    return content


def extract_lines_from_file_readings(flines):
    done = []
    for line in flines:
        temp_line = line.replace(" ", "").split("->")
        string_list = [x.split(",") for x in temp_line]
        t_list = []
        for sub in string_list:
            t_list.append((int(sub[0]), int(sub[1])))
        done.append(t_list)
    return done


def find_max(lines):
    current_max_x = 0
    current_max_y = 0
    for line in lines:
        for point in line:
            if point[0] > current_max_x:
                current_max_x = point[0]
            if point[1] > current_max_y:
                current_max_y = point[1]
    return current_max_x, current_max_y


def collect_points(lines, x, y, diagonals=False):
    mat = [[0 for j in range(x+1)] for i in range(y+1)]
    for line in lines:
        startx, starty = line[0]
        endx, endy = line[1]
        # Sort start and end by size
        if startx > endx:
            points_x = [x for x in range(startx, endx - 1, -1)]
        else:
            points_x = [x for x in range(startx, endx + 1)]
        if starty > endy:
            points_y = [y for y in range(starty, endy - 1, -1)]
        else:
            points_y = [y for y in range(starty, endy + 1)]
        if len(points_x) == 1:
            for y_coord in points_y:
                mat[y_coord][points_x[0]] += 1
        elif len(points_y) == 1:
            for x_coord in points_x:
                mat[points_y[0]][x_coord] += 1
        elif len(points_x) == len(points_y) and diagonals:
            for i in range(len(points_x)):
                mat[points_y[i]][points_x[i]] += 1
    return mat


def count_entries_larger_than(mat, threshold):
    count = 0
    for line in mat:
        for number in line:
            if number > threshold:
                count += 1
    return count


def part1():
    flines = read_vents_from_file("vents.txt")
    lines = extract_lines_from_file_readings(flines)
    max_x, max_y = find_max(lines)
    mat = collect_points(lines, max_x, max_y)
    return count_entries_larger_than(mat, 1)


def part2():
    flines = read_vents_from_file("vents.txt")
    lines = extract_lines_from_file_readings(flines)
    max_x, max_y = find_max(lines)
    mat = collect_points(lines, max_x, max_y, True)
    return count_entries_larger_than(mat, 1)


if __name__ == "__main__":
    # print(part1())
    print(part2())
