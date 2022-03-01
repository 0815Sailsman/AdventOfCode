VALUES = "values"
CHECKED = "checked"


def load_bingo_data(fname):
    with open("Python/2021/04/" + fname) as file:
        content = file.read().split("\n\n")
    return [int(x) for x in content[0].split(",")], content[1:]


def extract_field_from_readings(file_field):
    bingo_field = {VALUES: list(), CHECKED: [[False for i in range(5)] for j in range(5)]}
    for row in [x.replace("  ", " ").strip() for x in file_field.split("\n")]:
        bingo_field[VALUES].append([int(x) for x in row.split(" ")])
    return bingo_field


def mark_number_as_marked_on_field(field, p_number):
    for y, row in enumerate(field[VALUES]):
        for x, number in enumerate(row):
            if p_number == number:
                field[CHECKED][y][x] = True
    return field


def has_field_won(field):
    # Check Rows
    for row in field[CHECKED]:
        if all(row):
            return True
    for col in list(zip(*field[CHECKED][::-1])):
        if all(col):
            return True
    return False


def score(field, last_draw):
    score = 0
    # Sum unchecked numbers
    for y, row in enumerate(field[VALUES]):
        for x, number in enumerate(row):
            if not field[CHECKED][y][x]:
                score += number
    return score * last_draw


def part1():
    draws, fields = load_bingo_data("bingo.txt")
    formatted_fields = list()
    for unformatted_field in fields:
        formatted_fields.append(extract_field_from_readings(unformatted_field))
    for draw in draws:
        for ind, field in enumerate(formatted_fields):
            new_field = mark_number_as_marked_on_field(field, draw)
            formatted_fields[ind] = new_field
            if has_field_won(new_field):
                return score(new_field, draw)


def part2():
    done_index = list()
    draws, fields = load_bingo_data("bingo.txt")
    formatted_fields = list()
    for unformatted_field in fields:
        formatted_fields.append(extract_field_from_readings(unformatted_field))
    for draw in draws:
        for field in formatted_fields:
            if not formatted_fields.index(field) in done_index:
                new_field = mark_number_as_marked_on_field(field, draw)
                formatted_fields[formatted_fields.index(field)] = new_field
                if has_field_won(new_field):
                    if len(done_index) == len(formatted_fields) - 1 and formatted_fields[formatted_fields.index(field)] not in done_index:
                        return score(new_field, draw)
                    done_index.append(formatted_fields.index(new_field))


if __name__ == "__main__":
    # print(part1())
    print(part2())
