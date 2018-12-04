#!/bin/env/python3

from datetime import datetime
import re

class Guard:
    def __init__(self, id):
        self.id = id
        self.events = []

    def add_event(self, date, event):
        self.events.append({"date": date, "event": event})

class P4:

    date_fmt = '%Y-%m-%d %H:%M'
    puzzle_input = []
    guards = {}

    def __init__(self):
        print("Advent of Code 2018, day 4")
        with open("input4.sorted.txt") as f:
            self.puzzle_input = [self.parse(x.rstrip()) for x in f]

    def parse(self, line):
        return (datetime.strptime(line[1:17], self.date_fmt), line[19:])

    ##
    #
    def init_guards(self):
        current_guard_id = ''
        regexp = re.compile("Guard #(\d+) ")

        for l in self.puzzle_input:
            if "Guard" in l[1]:
                current_guard_id = regexp.match(l[1]).group(1)
                self.add_guard_event(current_guard_id, l[0], 'begins shift')
            else:
                self.add_guard_event(current_guard_id, l[0], l[1])

        # sort events:
        #for k, v in self.guards.items():
        #    v.events = sorted(v.events, key = lambda x : x['date'])

    ##
    # 
    def add_guard_event(self, guard_id, date, event):
        if not guard_id in self.guards.keys():
            self.guards[guard_id] = Guard(guard_id)
        self.guards[guard_id].add_event(date, event)

    def solve(self):
        self.init_guards()


def main():
    p4 = P4()
    p4.solve()


if __name__ == "__main__":
    main()
