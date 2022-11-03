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
from collections import defaultdict, deque

def bfs(graph, start, path, visited):
    que = deque()
    que.append(start)
    path.append(start)
    visited[start] = True
    while len(que) != 0:
        parent = que.popleft()
        for neighbour in graph[parent]:
            if visited[neighbour] == False:
                que.append(neighbour)
                path.append(neighbour)
                visited[neighbour] = True
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
res = bfs(graph, start, path, visited)
print(res)