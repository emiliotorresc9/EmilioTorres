import time
def selection_sort(valores, callback):
    n = len(valores)
    for i in range(n - 1):
        min_idx = i
        for j in range(i + 1, n):
            if valores[j] < valores[min_idx]:
                min_idx = j
        
        valores[i], valores[min_idx] = valores[min_idx], valores[i]
        time.sleep(0.07) 
        callback(valores)  

def selection_sortG(data):
    start_time = time.time()
    n = len(data)
    swap_count = 0
    for i in range(n):
        min_index = i
        for j in range(i+1, n):
            if data[j] < data[min_index]:
                min_index = j
        if min_index != i:
            data[i], data[min_index] = data[min_index], data[i]
            swap_count += 1
    elapsed_time = time.time() - start_time
    return swap_count, elapsed_time