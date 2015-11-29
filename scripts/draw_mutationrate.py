import numpy as np
import matplotlib.pyplot as plt

def draw(filename, fmt, label):
    with open(filename) as f:
        n = int(f.readline())
        v = [float(e) for e in f.readline().split()]
    plt.plot(range(n), v, fmt, label=label)
 

draw("res/00.res", 'r-', '0.0')
draw("res/02.res", 'g-', '0.2')
draw("res/04.res", 'b-', '0.4')
draw("res/06.res", 'c-', '0.6')
draw("res/08.res", 'y-', '0.8')
draw("res/10.res", 'm-', '1.0')

plt.xlabel('No. Iteration')
plt.ylabel('Reciprocal of Fitness')
plt.legend()
plt.ylim(ymin = 0)
plt.show()
