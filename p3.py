#!/bin/env/python3

class P3:

    puzzle_input = []
    rectangles = []

    def __init__(self):
        print("Advent of Code 2018, day 3")
        with open("input3.txt") as f:
            self.puzzle_input = [x.rstrip() for x in f]

    def parse(self, input):
        return []

    def solve(self):
        self.rectangles = self.parse(self.puzzle_input)


def main():
    p3 = P3()
    p3.solve()
    print("done")

if __name__ == "__main__":
    main()
