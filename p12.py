#!/bin/env python 3


class P12:

    initial_state = ("##...#......##......#.####.##.#..#..####.#."
                     "######.##..#.####...##....#.#.####.####.#..#.######.##...")
    # initial_state = "#..#.#..##......###...###"
    input_file = "input12.txt"
    generations = 100

    def __init__(self):
        self.lines = self.read_input()
        self.pots = [1 if x == '#' else 0 for x in self.initial_state]

        # Zero shift - where in the list actual "zero" index happens
        self.zero_shift = 0
        self.rules = {}
        for l in self.lines:
            (k, v) = self.parse_input(l)
            self.rules[k] = v

    def read_input(self):
        with open(self.input_file) as f:
            return [line.strip() for line in f.readlines()]

    def parse_input(self, l):
        key = int("".join(["1" if x == "#" else "0" for x in l[0:5]]), 2)
        value = True if l[9] == "#" else False
        return (key, value)

    # single emulation step:
    def step(self):
        t = []

        self.pots = [0, 0, 0] + self.pots
        self.zero_shift += 1

        self.pots += [0, 0, 0]

        for i in range(2, len(self.pots) - 2):
            slice = self.pots[i - 2: i + 3]
            key = int("".join([str(x) for x in slice]), 2)
            if key in self.rules.keys() and self.rules[key]:
                t.append(1)
            else:
                t.append(0)
        return t

    # add or remove list elements as required.
    # Add if pot is taken, remove if empty
    def solve(self):
        print("".join(["#" if x == 1 else "." for x in self.pots]) + str(self.zero_shift))
        for i in range(0, self.generations):
            self.pots = self.step()
            # print("".join(["#" if x == 1 else "." for x in self.pots]) + str(self.zero_shift))

        res = sum([z - self.zero_shift for z in range(0, len(self.pots)) if self.pots[z] == 1])
        print("Q12, P1: %d\n" % res)

    # 50 000 000 000 iterations later..
    # start with 100, that stabilises the cell automata
    def solve2(self):
        self.solve()
        ww = self.pots[self.zero_shift:]
        indices = [z for z in range(0, len(ww)) if ww[z] == 1]
        s = 50000000000 - 100
        print("Q12, P2: %d\n" % sum([i + s for i in indices]))


def main():
    p = P12()
    p.solve()


if __name__ == "__main__":
    main()
