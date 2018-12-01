#%%
import functools
import itertools

#%%
with open("input.txt") as f:
    drifts = [int(x.strip()) for x in f]

#%% [markdown]
## 1'st puzzle:
functools.reduce(lambda x, y: x + y, drifts, 0)

#%% [markdown]
## 2'nd puzzle:
def freq_generator():
    fr = 0
    while True:
        for x in drifts:
            fr = fr + x
            yield fr

frequencies = set([0])
for current_frequency in freq_generator():
    if current_frequency in frequencies:
        print(current_frequency)
        break
    frequencies.add(current_frequency)
