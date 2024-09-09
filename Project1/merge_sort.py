import time
def merge_sort_(arr, mostrar):
    def merge(arr, l, m, r):
        n1 = m - l + 1
        n2 = r - m
        L = arr[l:l + n1]
        R = arr[m + 1:m + 1 + n2]
        i = j = 0
        k = l

        while i < n1 and j < n2:
            if L[i] <= R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1
            time.sleep(0.07)
            mostrar(arr)

        while i < n1:
            arr[k] = L[i]
            i += 1
            k += 1
            time.sleep(0.1)
            mostrar(arr)

        while j < n2:
            arr[k] = R[j]
            j += 1
            k += 1
            time.sleep(0.07)
            mostrar(arr)

    def merge_sort(arr, l, r):
        if l < r:
            m = l + (r - l) // 2
            merge_sort(arr, l, m)
            merge_sort(arr, m + 1, r)
            merge(arr, l, m, r)

    merge_sort(arr, 0, len(arr) - 1)


def mergeG(arr, l, m, r):
    n1 = m - l + 1
    n2 = r - m
    L = arr[l:l + n1]
    R = arr[m + 1:m + 1 + n2]

    i = j = 0
    k = l
    while i < n1 and j < n2:
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    while i < n1:
        arr[k] = L[i]
        i += 1
        k += 1

    while j < n2:
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sortG(arr, l=0, r=None):
    if r is None:
        r = len(arr) - 1

    if l < r:
        m = (l + (r - 1)) // 2

        merge_sortG(arr, l, m)
        merge_sortG(arr, m + 1, r)
        mergeG(arr, l, m, r)

def merge_sort_mainG(arr):
    start_time = time.time()
    merge_sortG(arr)
    end_time = time.time()
    operations = len(arr) * (len(arr) - 1) / 2  # This is a rough estimation.
    time_elapsed = end_time - start_time
    return operations, time_elapsed
