import time
def shell_sort_(lista, mostrar):
    n = len(lista)
    h = n // 2  

    while h > 0:
        for i in range(h, n):
            temp = lista[i]
            j = i
            while j >= h and lista[j - h] > temp:
                lista[j] = lista[j - h]
                j -= h
                mostrar(lista) 

            lista[j] = temp
            time.sleep(0.07)
            mostrar(lista)  

        h //= 2  



def shell_sortG(arr):
    n = len(arr)
    start_time = time.time()
    gap = n // 2

    operations = 0

    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
                operations += 1
            arr[j] = temp
        gap //= 2

    end_time = time.time()
    time_elapsed = end_time - start_time

    return operations, time_elapsed

