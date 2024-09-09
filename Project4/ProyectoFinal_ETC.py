import turtle
import random

# Configuración de la ventana de Turtle
wn = turtle.Screen()
wn.bgcolor("white")
wn.title("Maze Solver with Turtle")
wn.setup(width=800, height=800)
pixels = 300

# Representación del laberinto
maze = [
    "################################",
    "#S      #     #                #",
    "# ##### # # # # #### ######### #",
    "#   #   # #   #    # # #     # #",
    "# ### ### # # # ## ### # ### # #",
    "#   #   # # # ###  #     #     #",
    "### ### # # # #   ## ##### ### #",
    "#   #   # # # # ###  #     #   #",
    "# ##### # # # # #   ## ##### ###",
    "# #     # # #   # ###  #     # #",
    "# # ##### # # ###     ## ##### #",
    "# #     # #   # # ### ## # # # #",
    "# ##### # ### #   # # #  # # # #",
    "# #   # #   ####### # ##       #",
    "#   ### # #   #G       # #######",
    "# # #   # ### ##########       #",
    "### ## ##     #   #    ### ### #",
    "#    #    ##### # # ##   #     #",
    "# #########   ###   #### ## ####",
    "#           ### ### #### ## ####",
    "# ###### ##   # ###   #        #",
    "# #    #  ### #   # # ##########",
    "#   ## ##  ## # # # #          #",
    "# ####  ##    # # ############ #",
    "# #   #  #### # # #            #",
    "# # # ## #### # # ### ##########",
    "# # #  #    # # # #            #",
    "# # ## #### # # # ############ #",
    "# #             #              #",
    "################################"
]

# Clase para dibujar el laberinto
class MazeTurtle(turtle.Turtle):
    def __init__(self):
        super().__init__()
        self.shape("square")
        self.color("black")
        self.penup()
        self.speed(0)

    def change_color(self, coordinate_type):
        if coordinate_type == "start_point":
            self.color("green")
        elif coordinate_type == "end_point":
            self.color("yellow")
        elif coordinate_type == "default":
            self.color("black")

# Clase para la tortuga que resolverá el laberinto
class SolverTurtle(turtle.Turtle):
    def __init__(self):
        super().__init__()
        self.shape("turtle")
        self.color("blue")
        self.penup()
        self.speed(0)
        self.pendown()

    def change_color(self):
        colors = ["blue", "red", "green", "purple", "orange", "pink", "brown"]
        self.color(random.choice(colors))

# Función para dibujar el laberinto
def draw_maze(maze):
    for y in range(len(maze)):
        for x in range(len(maze[y])):
            if maze[y][x] == '#':
                maze_turtle.goto(x * 20 - pixels, pixels - y * 20)
                maze_turtle.stamp()
            elif maze[y][x] == 'S':
                start_pos = (x, y)
                maze_turtle.change_color("start_point")
                maze_turtle.goto(x * 20 - pixels, pixels - y * 20)
                maze_turtle.stamp()
                maze_turtle.change_color("default")
            elif maze[y][x] == 'G':
                end_pos = (x, y)
                maze_turtle.change_color("end_point")
                maze_turtle.goto(x * 20 - pixels, pixels - y * 20)
                maze_turtle.stamp()
                maze_turtle.change_color("default")
    return start_pos, end_pos

# Función para mover la tortuga a la siguiente casilla
def move_turtle(x, y, heading):
    solver_turtle.setheading(heading)
    solver_turtle.goto(x * 20 - pixels, pixels - y * 20)

# Función para resolver el laberinto con enfoque Greedy
def solve_maze_greedy(maze, start, end):
    def is_valid_move(maze, x, y):
        return maze[y][x] in " G"

    paths = []
    move_order = [(0, -1, 90), (1, 0, 0), (0, 1, 270), (-1, 0, 180)]

    def greedy_search(position, path):
        if position == end:
            paths.append(path[:])
            solver_turtle.change_color()
            way_back(path)
            return

        for dx, dy, heading in move_order:
            nx, ny = position[0] + dx, position[1] + dy
            if is_valid_move(maze, nx, ny) and (nx, ny) not in path:
                move_turtle(nx, ny, heading)
                greedy_search((nx, ny), path + [(nx, ny)])
                move_turtle(position[0], position[1], heading)

    greedy_search(start, [start])
    return paths

def solve_maze_backtracking(maze, start, end):
    def is_valid_move(maze, x, y):
        return maze[y][x] in " G"

    paths = []
    move_order = [(0, -1, 90), (1, 0, 0), (0, 1, 270), (-1, 0, 180)]

    def backtracking_search(position, path):
        if position == end:
            paths.append(path[:])
            solver_turtle.change_color()
            way_back(path)
            return

        for dx, dy, heading in move_order:
            nx, ny = position[0] + dx, position[1] + dy
            if is_valid_move(maze, nx, ny) and (nx, ny) not in path:
                move_turtle(nx, ny, heading)
                backtracking_search((nx, ny), path + [(nx, ny)])
                move_turtle(position[0], position[1], heading)

    backtracking_search(start, [start])
    return paths

# Función para regresar al punto de partida
def way_back(path):
    for x, y in reversed(path):
        heading = solver_turtle.towards(x * 20 - pixels, pixels - y * 20)
        move_turtle(x, y, heading)

if __name__ == "__main__":
    # Instancia los objetos
    maze_turtle = MazeTurtle()
    solver_turtle = SolverTurtle()

    # Dibuja el Laberinto y establece las coordenadas de Inicio y Fin
    start_pos, end_pos = draw_maze(maze)
    paths_greedy = solve_maze_greedy(maze, start_pos, end_pos)
    paths_greedy.sort(key=len)
    if paths_greedy:
        optimal_path_greedy = paths_greedy[0]
        print("Optimal Path with Greedy:", optimal_path_greedy)
    else:
        print("No paths found with Greedy")
    solver_turtle.change_color()
    paths_backtracking = solve_maze_backtracking(maze, start_pos, end_pos)
    paths_backtracking.sort(key=len)
    if paths_backtracking:
        optimal_path_backtracking = paths_backtracking[0]
        print("Optimal Path with Backtracking:", optimal_path_backtracking)
    else:
        print("No paths found with Backtracking")
    for path in paths_greedy:
        print("Greedy Solution Path:", path)
    for path in paths_backtracking:
        print("Backtracking Solution Path:", path)

    # Mantiene la ventana abierta
    wn.mainloop()
