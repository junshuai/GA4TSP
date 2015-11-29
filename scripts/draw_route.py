import numpy as np
import matplotlib.pyplot as plt
import sys

n = 0
x = []
y = []

def dist(u, v):
    return ((x[u]-x[v])**2 + (y[u]-y[v])**2)**0.5

with open(sys.argv[1]) as f:
    n = int(f.readline())
    x = [0] * n;
    y = [0] * n;
    for i in range(n):
        x[i], y[i] = map(float, f.readline().split())
    f.readline()
    order = [int(e) for e in f.readline().split()]

route_x = [x[i-1] for i in order]
route_y = [y[i-1] for i in order]
route_x.append(x[order[0]-1])
route_y.append(y[order[0]-1])

distance = dist(order[0]-1, order[n - 1]-1)
for i in range(n-1):
    distance += dist(order[i]-1, order[i + 1]-1)

plt.title("Travel %d cities with the total distance of %.2f." % (n, distance))
plt.plot(route_x, route_y, 'r-o')
plt.show()
