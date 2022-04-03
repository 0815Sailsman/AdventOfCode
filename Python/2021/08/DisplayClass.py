from consts import *


class DisplayClass:

    # Mixed values should be a list of the 10 relevant codes as strings without any other chars inbetween
    def __init__(self, mixed_values):
        # Keys are the actual Positions
        # Values are what activates them
        # For example when the key TOP has the value BOT, then it looks something like this:
        """"
         aaaa               gggg
        b    c             b    c
        b    c             b    c
         dddd       ->      dddd
        e    f             e    f
        e    f             e    f
         gggg               aaaa
        """
        self.segments = {
            TOP: None,
            TOP_RIGHT: None,
            BOT_RIGHT: None,
            BOT: None,
            BOT_LEFT: None,
            TOP_LEFT: None,
            MID: None,
        }
        self.extract_combinations(mixed_values)
        self.translate_letters_to_positions()

    def extract_combinations(self, mixed_values):
        # We start by comparing 7 and 1 to find the code for the top segment
        code_1 = None
        code_4 = None
        code_7 = None
        code_8 = None
        codes069 = list()
        for entry in mixed_values:
            if len(entry) == 2:
                code_1 = entry
            elif len(entry) == 3:
                code_7 = entry
            elif len(entry) == 4:
                code_4 = entry
            elif len(entry) == 6:
                codes069.append(entry)
            elif len(entry) == 7:
                code_8 = entry
        self.segments[TOP] = code_7.replace(code_1[0], "").replace(code_1[1], "")

        # 0, 6 and 9 all have len 6 and are active on bot right, but one is inactive on top right
        # So we compare all of them with the values from the code1. Only one is missing one of the two codes
        # With this information we can find out TOP_RIGHT and BOT_RIGHT, and we know which code is the 6
        code_6 = None
        for code in codes069:
            if len(code.replace(code_1[0], "").replace(code_1[1], "")) == 5:
                code_6 = code
        if code_1[0] in code_6:
            self.segments[TOP_RIGHT] = code_1[1]
            self.segments[BOT_RIGHT] = code_1[0]
        else:
            self.segments[TOP_RIGHT] = code_1[0]
            self.segments[BOT_RIGHT] = code_1[1]

        # Now we look at 0 and 9 and remove every segment of 4 from it and the TOP Segment
        # We are left with 2 active segments for 0 and 1 active segment for 9
        # Therefore we know their respective codes and by comparing with 8 we find out MID and BOT_LEFT
        code_0 = None
        code_9 = None
        for code in codes069:
            if code == code_6:
                continue
            temp_code = code.replace(self.segments[TOP], "")
            for i in range(4):
                temp_code = temp_code.replace(code_4[i], "")
            if len(temp_code) == 2:
                code_0 = code
            elif len(temp_code) == 1:
                code_9 = code
        mid_char = code_8
        for i in code_0:
            mid_char = mid_char.replace(i, "")
        self.segments[MID] = mid_char
        bot_left_char = code_8
        for i in code_9:
            bot_left_char = bot_left_char.replace(i, "")
        self.segments[BOT_LEFT] = bot_left_char

        # Find TOP_LEFT by replacing RIGHTSIDE and MID in 4
        self.segments[TOP_LEFT] = code_4.replace(self.segments[MID], "").replace(self.segments[TOP_RIGHT], "").replace(self.segments[BOT_RIGHT], "")

        # Find BOT by subtracting rest from 8
        bot_char = code_8
        bot_char = bot_char.replace(self.segments[TOP], "")
        bot_char = bot_char.replace(self.segments[TOP_RIGHT], "")
        bot_char = bot_char.replace(self.segments[TOP_LEFT], "")
        bot_char = bot_char.replace(self.segments[MID], "")
        bot_char = bot_char.replace(self.segments[BOT_RIGHT], "")
        bot_char = bot_char.replace(self.segments[BOT_LEFT], "")
        self.segments[BOT] = bot_char


    def translate_letters_to_positions(self):
        for pos, val in self.segments.items():
            if val == "a":
                self.segments[pos] = TOP
            elif val == "b":
                self.segments[pos] = TOP_LEFT
            elif val == "c":
                self.segments[pos] = TOP_RIGHT
            elif val == "d":
                self.segments[pos] = MID
            elif val == "e":
                self.segments[pos] = BOT_LEFT
            elif val == "f":
                self.segments[pos] = BOT_RIGHT
            elif val == "g":
                self.segments[pos] = BOT

    def convert_code_to_dec(self, code):
        active_segments = list()
        if "a" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(TOP)])
        if "b" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(TOP_LEFT)])
        if "c" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(TOP_RIGHT)])
        if "d" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(MID)])
        if "e" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(BOT_LEFT)])
        if "f" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(BOT_RIGHT)])
        if "g" in code:
            active_segments.append(list(self.segments.keys())[list(self.segments.values()).index(BOT)])
        active_segments.sort()
        if active_segments == [TOP, TOP_LEFT, TOP_RIGHT, BOT_LEFT, BOT_RIGHT, BOT]:
            return 0
        elif active_segments == [TOP_RIGHT, BOT_RIGHT]:
            return 1
        elif active_segments == [TOP, TOP_RIGHT, MID, BOT_LEFT, BOT]:
            return 2
        elif active_segments == [TOP, TOP_RIGHT, MID, BOT_RIGHT, BOT]:
            return 3
        elif active_segments == [TOP_LEFT, TOP_RIGHT, MID, BOT_RIGHT]:
            return 4
        elif active_segments == [TOP, TOP_LEFT, MID, BOT_RIGHT, BOT]:
            return 5
        elif active_segments == [TOP, TOP_LEFT, MID, BOT_LEFT, BOT_RIGHT, BOT]:
            return 6
        elif active_segments == [TOP, TOP_RIGHT, BOT_RIGHT]:
            return 7
        elif active_segments == [TOP, TOP_LEFT, TOP_RIGHT, MID, BOT_LEFT, BOT_RIGHT, BOT]:
            return 8
        elif active_segments == [TOP, TOP_LEFT, TOP_RIGHT, MID, BOT_RIGHT, BOT]:
            return 9
        return -1


if __name__ == "__main__":
    string = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab cdfeb fcadb cdfeb cdbaf"
    data = string.split(" ")[0:10]
    d = DisplayClass(data)
    solution = ""
    for word in string.split(" ")[10:]:
        solution += str(d.convert_code_to_dec(word))
    print(solution)
