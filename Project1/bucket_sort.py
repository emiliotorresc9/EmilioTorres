
import time
def bucket_sort(valores, progress_callback):
    max_val = max(valores)
    min_val = min(valores)
    rango = (max_val - min_val) / len(valores)
    buckets = [[] for i in range(len(valores))]
    
    for num in valores:
        index = int((num - min_val) / rango)
        if index != len(valores):
            buckets[index].append(num)
        else:
            buckets[index - 1].append(num)
        
    sorted_arr = []
    for i, bucket in enumerate(buckets):
        sorted_bucket = sorted(bucket)
        sorted_arr.extend(sorted_bucket)
        time.sleep(0.07)
        progress_callback(sorted_arr)
    return sorted_arr


def insertion_sort_for_bucket(b):
    operations = 0
    for i in range(1, len(b)):
        up = b[i]
        j = i - 1
        while j >= 0 and b[j] > up:
            b[j + 1] = b[j]
            j -= 1
            operations += 1
        b[j + 1] = up
        operations += 1
    return operations

def bucket_sortG(arr):
    max_val = max(arr)
    size = max_val / len(arr)
    buckets = [[] for _ in range(len(arr))]
    operations = 0

    # Put array elements in different buckets
    for i in range(len(arr)):
        j = int(arr[i] / size)
        if j != len(arr):
            buckets[j].append(arr[i])
        else:
            buckets[len(arr) - 1].append(arr[i])
        operations += 1

    # Sort individual buckets
    for i in range(len(arr)):
        operations += insertion_sort_for_bucket(buckets[i])

    # Concatenate the result
    result_index = 0
    for i in range(len(arr)):
        for j in range(len(buckets[i])):
            arr[result_index] = buckets[i][j]
            result_index += 1
            operations += 1

    return operations, time.time()