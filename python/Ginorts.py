from functools import reduce

def my_key(x):
    if x.islower():
        return 1, x
    if x.isupper():
        return 2, x
    if x.isdigit():
        y = int(x)
        if y % 2 == 1:
            return 3, x
        else:
            return 4, x
    return 5, x


if __name__ == '__main__':
    word = input()
    print(reduce(lambda x, y: x + y, (sorted(word, key=my_key))))
