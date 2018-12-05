#!/bin/env python3

from datetime import datetime
import re


class Guard:
    def __init__(self, id):
        self.id = id
        self.events = []
        self.sleeping_time = []
        self.total_sleep = 0

    def add_event(self, date, event):
        self.events.append({"date": date, "event": event})

    def add_sleep_times(self):
        dates = set([x['date'].date() for x in self.events])
        has_slept = False

        for d in dates:
            sleep_start = 0
            wake_start = 0
            timesheet = {'date': d, 'sheet': [0]*60}
            for event in [e for e in self.events if e['date'].date() == d]:
                if event['event'] == 'falls asleep':
                    sleep_start = event['date'].minute
                    has_slept = True
                if event['event'] == 'wakes up':
                    wake_start = event['date'].minute
                    for i in range(sleep_start, wake_start):
                        timesheet['sheet'][i] = 1
                    has_slept = False
            if has_slept:
                for i in range(sleep_start, 60):
                    timesheet['sheet'][i] = 1

            self.sleeping_time.append(timesheet)

    def add_total_sleep(self):
        self.total_sleep = sum([sum(x['sheet']) for x in self.sleeping_time])


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
        regexp = re.compile("Guard #([0-9]+) ")

        for l in self.puzzle_input:
            if "Guard" in l[1]:
                current_guard_id = regexp.match(l[1]).group(1)
                self.add_guard_event(current_guard_id, l[0], 'begins shift')
            else:
                self.add_guard_event(current_guard_id, l[0], l[1])

    ##
    #
    def add_guard_event(self, guard_id, date, event):
        if guard_id not in self.guards.keys():
            self.guards[guard_id] = Guard(guard_id)
        self.guards[guard_id].add_event(date, event)

    def most_slept_minute(self, guard):
        # sum minutes:
        s = [x['sheet'] for x in guard.sleeping_time]
        sum_minutes = [sum(x) for x in zip(*s)]
        max = 0
        max_index = 0
        for i in range(0, 60):
            if sum_minutes[i] > max:
                max = sum_minutes[i]
                max_index = i
        return (max_index, max)

    def solve(self):
        self.init_guards()
        for guard in self.guards.items():
            guard[1].add_sleep_times()
            guard[1].add_total_sleep()

        # find sleepy head:
        sleepy_head = sorted([(g[1].id, g[1].total_sleep) for g in self.guards.items()],
                             key=lambda x: x[1])[-1]
        print("Sleepy head ID: {0}".format(sleepy_head[0]))

        # sum minutes:
        res = self.most_slept_minute(self.guards[sleepy_head[0]])
        print("he sleeps mostly at minute {0}".format(res[0]))
        self.solve2()

    def solve2(self):
        r = [(g[1].id, self.most_slept_minute(g[1])) for g in self.guards.items()]
        r = sorted(r, key=lambda x: x[1][1])
        print(r[-1])
        print("answer:{0}".format(int(r[-1][0]) * int(r[-1][1][0])))


def main():
    p4 = P4()
    p4.solve()


if __name__ == "__main__":
    main()
