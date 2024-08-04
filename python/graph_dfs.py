'''
u v
7 9
A B
A C
A F
C E
C F
C D
D E
D G
G F
'''
from collections import defaultdict

def dfs(graph, start, path, visited):
    path.append(start)
    visited[start] = True
    for neighbour in graph[start]:
        if visited[neighbour] == False:
            dfs(graph, neighbour, path, visited)
    return path

v, e = map(int, input().split())
graph = defaultdict(list)
for i in range(e):
    u, v = map(str, input().split())
    graph[u].append(v)
    graph[v].append(u)
print(graph)
path = list()
start = 'A'
visited = defaultdict(bool)
res = dfs(graph, start, path, visited)
print(res)