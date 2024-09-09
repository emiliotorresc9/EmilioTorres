import time

def bubble_sort(valores, update_callback):
    n = len(valores)
    for i in range(n):
        for j in range(0, n - i - 1):
            if valores[j] > valores[j + 1]:
                valores[j], valores[j + 1] = valores[j + 1], valores[j]
                time.sleep(0.07)
                update_callback(valores)


def bubble_sortG(data):
    start_time = time.time()
    n = len(data)
    operations = 0
    for i in range(n):
        for j in range(0, n-i-1):
            operations += 1
            if data[j] > data[j+1]:
                data[j], data[j+1] = data[j+1], data[j]
    end_time = time.time()
    return operations, end_time - start_time
            

