import time
def counting_sort_(valores, mostrar):
    maximo = max(valores)
    lista_conteo = [0] * (maximo + 1)

    for numero in valores:
        lista_conteo[numero] += 1

    indice = 0
    for i in range(len(lista_conteo)):
        while lista_conteo[i] > 0:
            valores[indice] = i
            time.sleep(0.07)
            mostrar(valores)
            lista_conteo[i] -= 1
            indice += 1



def counting_sortG(arr):
    max_val = max(arr) if arr else 0
    min_val = min(arr) if arr else 0
    range_of_elements = max_val - min_val + 1
    count = [0] * range_of_elements
    output = [0] * len(arr)
    operations = 0

    # Count each element
    for num in arr:
        count[num - min_val] += 1
        operations += 1

    # Change count[i] so that count[i] now contains the actual
    # position of this element in the output array
    for i in range(1, len(count)):
        count[i] += count[i - 1]
        operations += 1

    # Build the output array
    for num in reversed(arr):
        output[count[num - min_val] - 1] = num
        count[num - min_val] -= 1
        operations += 1

    # Copying to original array
    for i in range(len(arr)):
        arr[i] = output[i]
        operations += 1

    return operations, time.time()
