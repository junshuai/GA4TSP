import numpy as np
import matplotlib.pyplot as plt

def draw(filename, fmt, label):
    with open(filename) as f:
        n = int(f.readline())
        v = [float(e) for e in f.readline().split()]
    plt.plot(range(n), v, fmt, label=label)
 

draw("res/OX1.res", 'r-', 'OX_1')
draw("res/OX2.res", 'r--', 'OX_2')
draw("res/OX3.res", 'r-.', 'OX_3')
draw("res/CX1.res", 'g-', 'CX_1')
draw("res/CX2.res", 'g--', 'CX_2')
draw("res/CX3.res", 'g-.', 'CX_3')
draw("res/PMX1.res", 'b-', 'PMX_1')
draw("res/PMX2.res", 'b--', 'PMX_2')
draw("res/PMX3.res", 'b-.', 'PMX_3')

plt.xlabel('No. Iteration')
plt.ylabel('Reciprocal of Fitness')
plt.legend()
plt.show()
