import time
def insertion_sort(valores, callback):
    for i in range(1, len(valores)):
        key = valores[i]
        j = i - 1
        while j >= 0 and valores[j] > key:
            valores[j + 1] = valores[j]
            j -= 1
            valores[j + 1] = key
        time.sleep(0.07)
        callback(valores)

def insertion_sortG(data):
    start_time = time.time()
    n = len(data)
    move_count = 0
    for i in range(1, n):
        key = data[i]
        j = i - 1
        while j >= 0 and key < data[j]:
            data[j+1] = data[j]
            j -= 1
            move_count += 1
        data[j+1] = key
    elapsed_time = time.time() - start_time
    return move_count, elapsed_time