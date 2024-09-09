import tkinter as tk
from tkinter import ttk, messagebox, font
import random
import time
import threading
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg 
from matplotlib.figure import Figure

from bubble_sort import bubble_sort, bubble_sortG
from insertion_sort import insertion_sort, insertion_sortG
from selection_sort import selection_sort, selection_sortG
from quick_sort import quick_sort, quick_sort_mainG
from heap_sort import heap_sort, heap_sortG
from merge_sort import merge_sort_, merge_sort_mainG
from shell_sort import shell_sort_, shell_sortG
from counting_sort import counting_sort_, counting_sortG
from radix_sort import radix_sort_, radix_sortG
from bucket_sort import bucket_sort, bucket_sortG

pausado = False 
valores = []
valores2 = []

def pausar():
    global pausado 
    messagebox.showinfo("Ordenamiento pausado", "La función ha sido pausada")
    pausado = True
    
def mostrar(valores):
    global pausado
    while pausado:
        ventana.update()
    frColum.delete("all")
    anchoColum = int(frColum.cget("width")) / len(valores)
    altura_max = int(frColum.cget("height"))
    max_valor = max(valores)
    for i, valor in enumerate(valores):
        if not pausado:
            r = random.randint(200, 255) 
            g = random.randint(200, 255)
            b = random.randint(200, 255)
            color = "#{:02x}{:02x}{:02x}".format(r, g, b)
            frColum.create_rectangle(i * anchoColum, altura_max, (i + 1) * anchoColum, altura_max - (valor/max_valor)*altura_max, fill=color)
            ventana.update()
            ventana.after(int(max(0.0001, 0.05 - velocidad.get()/1000) * 100))
        else:
            break
    
def iniciar():  
    sorting_method = metodo.get()
    if sorting_method == 'bubble': 
        threading.Thread(target=bubble_sort, args=(valores, mostrar)).start()   
    elif sorting_method == 'insertion':
        threading.Thread(target=insertion_sort, args=(valores, mostrar)).start()
    elif sorting_method == 'selection':
        threading.Thread(target=selection_sort, args=(valores, mostrar)).start()
    elif sorting_method == 'quick':
        threading.Thread(target=quick_sort, args=(valores,0,len(valores)-1, mostrar,)).start()    
    elif sorting_method == 'heap':
        threading.Thread(target=heap_sort, args=(valores, mostrar)).start() 
    elif sorting_method == 'bucket':
        threading.Thread(target=bucket_sort, args=(valores, mostrar)).start()   
    elif sorting_method == 'merge': 
        threading.Thread(target=merge_sort_, args=(valores, mostrar)).start()
    elif sorting_method == 'shell':
        threading.Thread(target=shell_sort_, args=(valores, mostrar)).start()
    elif sorting_method == 'counting':  
        threading.Thread(target=counting_sort_, args=(valores, mostrar)).start()
    elif sorting_method == 'radix': 
        threading.Thread(target=radix_sort_, args=(valores, mostrar)).start()
    elif sorting_method == 'bucket':
        threading.Thread(target=bucket_sort, args=(valores, mostrar)).start()

def reiniciar():
    global pausado
    pausado = False
    messagebox.showinfo("Ordenamiento reiniciado", "La función ha sido reiniciada")
    iniciar()

def HacerColumnas():
    global valores, valores2
    try:
        numColumnas = int(ColumnasApp.get())
        if numColumnas < 10 or numColumnas > 1000:
            raise ValueError("El número no está en el rango permitido")
        valores = random.sample(range(10, numColumnas + 10), numColumnas)
        valores2 = valores[:]
        mostrar(valores)
    except ValueError:
        error.config(text="El número no está en el rango permitido")



ventana = tk.Tk()
ventana.title("Proyecto Parcial 2 - Estructura de Datos y Algoritmos")

ancho = 1150
alto = 700

arriba = 0
abajo = 0
ventana.geometry(f"{ancho}x{alto}+{abajo}+{arriba}")

style = ttk.Style()

style.configure("TNotebook", tabposition="n", background="white")
bold_font = font.Font(weight="bold")
pestañas = ttk.Notebook(ventana, style="TNotebook")
pestaña1 = ttk.Frame(pestañas)
pestaña2 = ttk.Frame(pestañas)
pestañas.add(pestaña1, text="Ordenamiento")
pestañas.add(pestaña2, text="Graficas Big O")
pestañas.pack(expand=1, fill='both')

metodo = tk.StringVar(value="bubble")

tk.Label(pestaña1, text="ORDENAMIENTO POR BARRAS", font=('ARIAL', 14)).pack()
frOrde = tk.Frame(pestaña1, borderwidth=2, relief="groove",bg="white")
frOrde.pack(side=tk.LEFT, fill="both", expand=True)

tk.Label(frOrde, text="ORDENAMIENTOS",fg= "purple", font=bold_font,bg="white").pack(pady=30)
tk.Radiobutton(frOrde, text="BUBBLE", variable=metodo, value='bubble', fg="brown",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="INSERTION", variable=metodo, value='insertion', fg="green",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="SELECTION", variable=metodo, value='selection',fg="brown",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="MERGE", variable=metodo, value='merge',fg="green",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="QUICK", variable=metodo, value='quick',fg="brown",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="HEAP", variable=metodo, value='heap',fg="green",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="SHELL", variable=metodo, value='shell',fg="brown",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="COUNTING", variable=metodo, value='counting',fg="green",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="RADIX", variable=metodo, value='radix',fg="brown",bg="white").pack(anchor=tk.W)
tk.Radiobutton(frOrde, text="BUCKET", variable=metodo, value='bucket',fg="green",bg="white").pack(anchor=tk.W)

tk.Button(frOrde, text="INICIAR", command=iniciar, bg="white", fg="blue").pack(fill="x")

frColum = tk.Canvas(pestaña1, width=650, height=500, bg='white')
frColum.pack(side=tk.LEFT, padx=0, pady=0)

frVel = tk.Frame(pestaña1, borderwidth=2, relief="groove",bg="white")
frVel.pack(side=tk.RIGHT, fill="both", expand=True)

tk.Label(frVel, text="VELOCIDAD",fg= "purple", font=bold_font, bg="white").pack(pady=30)
velocidad = tk.DoubleVar(value=50)
tk.Scale(frVel,bg="white", from_=1, to=200, variable=velocidad, fg="brown",orient=tk.HORIZONTAL).pack()
tk.Label(frVel, text="Ajustar Velocidad", fg="brown").pack()
tk.Button(frVel, text="PAUSAR", fg="blue",command=pausar,bg="white").pack(fill="x", pady=40)  
tk.Button(frVel, text="REINICIAR", fg="blue",command=reiniciar, bg="white").pack(fill="x", pady=10)


ColumnasApp = tk.Entry(pestaña1)
ColumnasApp.pack(side=tk.BOTTOM, fill="x", padx=10, pady=5)
boton_generar = tk.Button(pestaña1, text="Ingrese valor entre 10 a 1000", command=HacerColumnas, bg="white", fg="blue")
boton_generar.pack(side=tk.BOTTOM, fill="x", padx=2, pady=2)

error = tk.Label(pestaña1, fg="red")
error.pack(side=tk.BOTTOM, fill="x", padx=10, pady=5)

tk.Label(pestaña2, text="Complejidad Asintótica de los Algoritmos (Big-O)", font=('ARIAL', 14)).pack()



#PESTAÑA 2
def generarAleatorios():
    return random.sample(range(1, 2001), 2000)

def generarEnOrden():
    return list(range(1, 2001))

def generarInversos():
    return list(range(2000, 0, -1))

frCombo2 = tk.Frame(pestaña2, borderwidth=2, relief="ridge")
frCombo2.pack(side=tk.TOP, padx=10, pady=10)

combo_box2 = ttk.Combobox (frCombo2, state="readonly", width=40)
combo_box2['values'] = ["Generar valores aleatorios",
    "Generar valores del 1 al 2000",
    "Generar valores del 2000 al 1"
]
combo_box2.set("Seleccione la opcion deseada")
combo_box2.pack(side=tk.LEFT, padx=5, pady=5)

frTextValores2 = tk.Frame(pestaña2, borderwidth=2, relief="ridge")
frTextValores2.pack(side=tk.LEFT, padx=10, pady=10, fill="both", expand=True)

textovalores2 = tk.Text(frTextValores2, width=30, height=20)
textovalores2.pack(side=tk.LEFT, fill="both", expand=True)
scrollbar2 = tk.Scrollbar(frTextValores2, command=textovalores2.yview)
scrollbar2.pack(side=tk.RIGHT, fill="y")
textovalores2.config(yscrollcommand=scrollbar2.set)

canvas_graficas2 = tk.Canvas(pestaña2, width=800, height=600, bg='white')
canvas_graficas2.pack(side=tk.RIGHT, padx=20, pady=20)

def mostrarvalores2(valoresBigO):
    textovalores2.delete(1.0, tk.END)
    textovalores2.insert(tk.END, str(valoresBigO))

def seleccionar_opcion2(event):
    opcion_seleccionada2 = combo_box2.get()
    valoresBigO = []
    if opcion_seleccionada2 == "Generar valores aleatorios":
        valoresBigO = generarAleatorios()
    elif opcion_seleccionada2 == "Generar valores del 1 al 2000":
        valoresBigO = generarEnOrden()
    elif opcion_seleccionada2 == "Generar valores del 2000 al 1":
        valoresBigO = generarInversos()
    mostrarvalores2(valoresBigO)
    ordenar_y_mostrar_graficas(valoresBigO)  # Pass valoresBigO as an argument

combo_box2.bind("<<ComboboxSelected>>", seleccionar_opcion2)

def ordenar_y_mostrar_graficas(valoresBigO):
    for widget in canvas_graficas2.winfo_children():
        widget.destroy()
        return valoresBigO

    sizes = range(100, 2001, 100) 
    operations = {alg: [] for alg in ['Bubble', 'Insertion', 'Selection', 'Heap', 'Shell', 'Merge', 'Quick', 'Counting', 'Radix', 'Bucket']}
    times = {alg: [] for alg in ['Bubble', 'Insertion', 'Selection', 'Heap', 'Shell', 'Merge', 'Quick', 'Counting', 'Radix', 'Bucket']}
    total_times = {alg: 0 for alg in ['Bubble', 'Insertion', 'Selection', 'Heap', 'Shell', 'Merge', 'Quick', 'Counting', 'Radix', 'Bucket']}

    colors = ['blue', 'green', 'red', 'cyan', 'magenta', 'yellow', 'black', 'orange', 'purple', 'brown']

    for size in sizes:
        data = random.sample(valoresBigO, size)
        for idx, (alg, func) in enumerate([('Bubble', bubble_sortG), ('Insertion', insertion_sortG), ('Selection', selection_sortG),
                                           ('Heap', heap_sortG), ('Shell', shell_sortG), ('Merge', merge_sort_mainG), ('Quick', quick_sort_mainG),
                                           ('Counting', counting_sortG), ('Radix', radix_sortG), ('Bucket', bucket_sortG)]):
            start_time = time.time()
            ops, _ = func(data.copy())
            end_time = time.time()
            elapsed_time = end_time - start_time
            operations[alg].append(ops)
            times[alg].append(elapsed_time)
            total_times[alg] += elapsed_time

    fig = Figure(figsize=(12, 8), dpi=100)
    axes = [fig.add_subplot(4, 3, i + 1) for i in range(len(operations))]

    for ax, (alg, color) in zip(axes, zip(operations, colors)):
        avg_time = total_times[alg] / len(sizes)
        ax.plot(sizes, operations[alg], marker='o', color=color) 
        ax.set_title(f'{alg} Sort (Time: {avg_time:.4f} s)')
        ax.set_xlabel('Items')
        ax.set_ylabel('Steps')

    fig.tight_layout(pad=3.0)
    canvas = FigureCanvasTkAgg(fig, master=canvas_graficas2)
    canvas.draw()
    canvas.get_tk_widget().pack(side=tk.TOP, fill=tk.BOTH, expand=True)

ventana.mainloop()