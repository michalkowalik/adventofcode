#!/bin/env python 3

import numpy as np


class P11:

    def __init__(self):
        self.grid = np.zeros((300, 300), dtype=np.int)
        self.serial_number = 3031

    def get_cell_value(self, x, y):
        rack_id = x + 10
        power_level = rack_id * y
        power_level += self.serial_number
        power_level *= rack_id
        return (int(power_level / 100) % 10) - 5

    def solve(self):
        for i in range(0, 300):
            for j in range(0, 300):
                self.grid[i, j] = self.get_cell_value(i + 1, j + 1)
        max_x = 0
        max_y = 0
        max_sum = 0
        max_square_size = 2

        for square_size in range(2, 299):
            print("square size: %d" % square_size)
            for i in range(0, 300 - square_size):
                for j in range(0, 300 - square_size):
                    view = self.grid[i:(i+square_size), j:(j+square_size)]
                    if sum(sum(view)) > max_sum:
                        max_sum = sum(sum(view))
                        max_x = i
                        max_y = j
                        max_square_size = square_size
            print("x: %d y: %d, sq: %d, sum: %d" % (max_x + 1, max_y + 1, max_square_size, max_sum))


def main():
    p = P11()
    p.solve()


if __name__ == "__main__":
    main()
