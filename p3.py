#!/bin/env/python3

import re

class Claim:
    def __init__(self, id, left, top, width, length):
        self.id = id
        self.top = top
        self.left = left
        self.width = width
        self.length = length

class P3: 
    puzzle_input = []
    rectangles = []
    claims = []

    def __init__(self):
        print("Advent of Code 2018, day 3")
        with open("input3.txt") as f:
            self.puzzle_input = [x.rstrip() for x in f]

    def parse(self, input):
        claims = []
        xre = re.compile("#(\d+) @ (\d+),(\d+): (\d+)x(\d+)")
        for i in self.puzzle_input:
            m  = xre.match(i)
            claim = Claim(int(m[1]), int(m[2]), int(m[3]), int(m[4]), int(m[5]))
            claims.append(claim)
        return claims

    def mark_claim(self, claim):
        for x in range(claim.left, claim.left + claim.width):
            for y in range(claim.top, claim.top + claim.length):
                self.rectangles[x][y].append(claim.id)

    def not_overlapping_claim(self, c):
        for x in range(c.left, c.left + c.width):
            for y in range(c.top, c.top + c.length):
                if len(self.rectangles[x][y]) > 1:
                    return False
        return True 

    def solve_part_2(self):
        x = [c.id for c in self.claims if self.not_overlapping_claim(c)]
        print("P3.2: {0}".format(x[0]))
        # for c in self.claims:
        #     if self.not_overlapping_claim(c):
        #         print("P3.2: {0}".format(c.id))
        #         break

    def solve(self):
        self.claims = self.parse(self.puzzle_input)
        max_width = max([x.left + x.width for x in self.claims])
        max_length = max([x.top + x.length for x in self.claims])
        self.rectangles = [[ [] for y in range(max_length + 1)] for x in range(max_width + 1)]
        for c in self.claims:
            self.mark_claim(c)
        print("P3.1: {0}".format(sum([len([x for x in z if len(x) > 1]) for z in self.rectangles])))
        self.solve_part_2()

def main():
    p3 = P3()
    p3.solve()

if __name__ == "__main__":
    main()
