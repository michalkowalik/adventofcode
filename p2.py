#%%
from collections import Counter
codes = []
with open("input2.txt") as f:
    codes = [x.rstrip() for x in f]

#%%
letter_distributions = []

def doubles_and_triples(code):
    res = [0, 0]
    distr = Counter(code)
    distr = {k: v for k, v in distr.items() if v == 2 or v == 3}
    if 2 in distr.values():
        res[0] = 1
    if 3 in distr.values():
        res[1] = 1
    return tuple(res)

for code in codes:
    letter_distributions.append(doubles_and_triples(code))

#%%
twos = sum([x[0] for x in letter_distributions])
threes = sum([x[1] for x in letter_distributions])
print("RES: {0}", twos * threes)

#%% [markdown]
## Part 2

#%%
def code_match(c1, c2):
    differences = 0
    for i in range(0, len(c1)):
        if c1[i] == c2[i]:
            continue
        if differences > 0:
            return False
        differences += 1
    return True

def find_matching_codes():
    for i in range(0, len(codes)):
        c1 = codes[i]
        for j in range(i + 1, len(codes)):
            if code_match(c1, codes[j]):
                return [c1, codes[j]]
    return []

l = find_matching_codes()
res = ''
for c in range(0, len(l[0])):
    if l[0][c] == l[1][c]:
        res += l[0][c]
print(res)