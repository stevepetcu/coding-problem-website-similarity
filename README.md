# Daily Coding Problem #856 [Medium]

Problem asked by Quora.

## Problem Statement

You are given a list of `(website, user)` pairs that represent users visiting websites. 
Come up with a program that identifies the top k pairs of websites with the greatest similarity.

For example, suppose `k = 1`, and the list of tuples is:

```text
[('a', 1), ('a', 3), ('a', 5),
 ('b', 2), ('b', 6),
 ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5)
 ('d', 4), ('d', 5), ('d', 6), ('d', 7),
 ('e', 1), ('e', 3), ('e', 5), ('e', 6)]
```

Then a reasonable similarity metric would most likely conclude that `a` and `e` are 
the most similar, so your program should return `[('a', 'e')]`.

## Solution

**Assumption**: the pairs order is irrelevant, so we're only going to keep unique pairs. 
For example, out of `('a', 'b')` and `('b', 'a')`, we're only going to count `('a', 'b')`.

### Approach

1. Transform the data into something easier to work with:
```text
[
    ('a', [1, 3, 5]),
    ('b', [2, 6]),
    …
    ('e', [1, 3, 5, 6])
]
```

2. Create a website pair similarity array:
```text
[
    ((website_1, website_2), (similarity, difference)),
    …
]
```
where:
- `similarity` = number of unique users who visited both sites
- `difference` = the difference between the total number of users who visited website_1 and website_2

3. Order the resulting array `desc` by the `similarity` value and then `asc` by the `difference` value
4. Return the first `k` number of pairs of websites
