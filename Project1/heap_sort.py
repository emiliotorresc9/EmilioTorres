import time
def heapify(valores, n, i, callback):
    largest = i  
    left = 2 * i + 1     
    right = 2 * i + 2    

    if left < n and valores[largest] < valores[left]:
        largest = left

    if right < n and valores[largest] < valores[right]:
        largest = right

    if largest != i:
        valores[i], valores[largest] = valores[largest], valores[i]  

        heapify(valores, n, largest, callback)
    time.sleep(0.07)
    callback(valores) 

def heap_sort(valores, callback):
    n = len(valores)

    for i in range(n // 2 - 1, -1, -1):
        heapify(valores, n, i, callback)

    for i in range(n-1, 0, -1):
        valores[i], valores[0] = valores[0], valores[i]  #
        heapify(valores, i, 0, callback)


def heapifyG(arr, n, i):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    if left < n and arr[largest] < arr[left]:
        largest = left

    if right < n and arr[largest] < arr[right]:
        largest = right

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapifyG(arr, n, largest)

def heap_sortG(arr):
    n = len(arr)
    start_time = time.time()

    # Build a maxheap.
    for i in range(n // 2 - 1, -1, -1):
        heapifyG(arr, n, i)

    # Extract elements one by one
    for i in range(n-1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapifyG(arr, i, 0)

    end_time = time.time()
    operations = n * (n + 1) / 2  # This is a rough estimation.
    time_elapsed = end_time - start_time

    return operations, time_elapsed
