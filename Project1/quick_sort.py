import time
def quick_sort(arr, izq, der, callback):
    if izq < der:
        pivot_index = partition(arr, izq, der)
        time.sleep(0.07)
        callback(arr)  # Update visual representation
        quick_sort(arr, izq, pivot_index - 1, callback)
        quick_sort(arr, pivot_index + 1, der, callback)

def partition(arr, izq, der):
    pivote = arr[der]
    i = izq - 1
    for j in range(izq, der):
        if arr[j] < pivote:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[der] = arr[der], arr[i + 1]
    return i + 1

import time

def partitionG(arr, low, high):
    i = low - 1
    pivot = arr[high]

    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quick_sortG(arr, low=0, high=None):
    if high is None:
        high = len(arr) - 1

    if low < high:
        pi = partitionG(arr, low, high)
        quick_sortG(arr, low, pi - 1)
        quick_sortG(arr, pi + 1, high)

def quick_sort_mainG(arr):
    start_time = time.time()
    quick_sortG(arr)
    end_time = time.time()
    operations = len(arr) * (len(arr) - 1) / 2  # This is a rough estimation.
    time_elapsed = end_time - start_time
    return operations, time_elapsed

