
cube = lambda x: x ** 3


def fibonacci(n):
    # return a list of fibonacci numbers

    if n == 0:
        return []
    if n == 1:
        return [0]

    result = [0, 1]
    [result.append(result[i - 1] + result[i]) for i in range(1, n - 1)]

    return result
