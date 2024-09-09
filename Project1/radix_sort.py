import time
def radix_sort_(arr, mostrar):
    if len(arr) == 0:
        return arr

    max_val = max(arr)
    exp = 1

    def counting_sort(arr, exp):
        n = len(arr)
        output = [0] * n  
        count = [0] * 10  

        for i in range(n):
            index = (arr[i] // exp)
            count[index % 10] += 1

        for i in range(1, 10):
            count[i] += count[i - 1]

        i = n - 1
        while i >= 0:
            index = (arr[i] // exp)
            output[count[index % 10] - 1] = arr[i]
            count[index % 10] -= 1
            i -= 1
            time.sleep(0.07)
            mostrar(output.copy())  

        for i in range(n):
            arr[i] = output[i]

    while max_val // exp > 0:
        counting_sort(arr, exp)
        exp *= 10


def counting_sort_for_radix(arr, exp):
    n = len(arr)
    output = [0] * n
    count = [0] * 10
    operations = 0

    # Store count of occurrences in count[]
    for i in range(n):
        index = arr[i] // exp
        count[index % 10] += 1
        operations += 1

    # Change count[i] so that count[i] now contains actual
    # position of this digit in output[]
    for i in range(1, 10):
        count[i] += count[i - 1]
        operations += 1

    # Build the output array
    i = n - 1
    while i >= 0:
        index = arr[i] // exp
        output[count[index % 10] - 1] = arr[i]
        count[index % 10] -= 1
        i -= 1
        operations += 1

    # Copy the output array to arr[], so that arr now
    # contains sorted numbers according to current digit
    for i in range(len(arr)):
        arr[i] = output[i]
        operations += 1

    return operations

def radix_sortG(arr):
    # Find the maximum number to know the number of digits
    max_val = max(arr)
    operations = 0
    exp = 1

    # Do counting sort for every digit. The exp is passed as 10^i
    # where i is the current digit number
    while max_val // exp > 0:
        operations += counting_sort_for_radix(arr, exp)
        exp *= 10

    return operations, time.time()