import numpy


def arrays(arr):
    return numpy.flipud(numpy.array(arr, float))


arr = input().strip().split(' ')
result = arrays(arr)
print(result)
