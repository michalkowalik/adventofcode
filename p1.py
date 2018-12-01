#%%
import os
import sys
import functools

#%%
with open("input.txt") as f:
    drifts = [int(x.strip()) for x in f]

#%% [markdown]
## 1'st puzzle:
functools.reduce(lambda x, y: x + y, drifts, 0)

#%% [markdown]
## 2'nd puzzle:

