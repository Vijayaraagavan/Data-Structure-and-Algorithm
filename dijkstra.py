import sys
from collections import defaultdict
from heapq import heapify, heappop, heappush

class Node:
    def __init__(self, id, weight):
        self.id = id
        self.distance = weight
        self.pred = []

class graph:
    def __init__(self, gra):
        self.adjList = defaultdict(list)
        for i in gra:
            for j in gra[i]:
                self.adjList[i].append(Node(j, gra[i][j]))
    def printgraph(self):
        print(self.adjList)

def dijkstra(res, src, dest):
    NodeCost = {}
    NodePath = {}
    for i in res.adjList:
        NodeCost[i] = sys.maxsize
        NodePath[i] = [i]
    NodeCost[src] = 0
    visited = []
    tmp = src
    for i in range(5):
        if tmp not in visited:
            visited.append(tmp)
            min_heap = []
        for j in res.adjList[tmp]:
            if j.id not in visited:
                visited.append(tmp)
                vertex = j
                cost = NodeCost[tmp] + vertex.distance 
                print("cost is:", cost)
                if cost < NodeCost[vertex.id]:
                    NodeCost[vertex.id] = cost
                    path = NodePath[tmp] + list(vertex.id)
                    print(path)
                    NodePath[vertex.id] = path
                heappush(min_heap, (NodeCost[vertex.id], vertex.id))
                print("pushed: ", vertex.id)
        heapify(min_heap)
        tmp = min_heap[0][1]
    print("f is : ", NodePath)

# get inputs here
# v, e = map(int, input().split())
# src, dest = map(str, input().split())
# graph = defaultdict(list)
graph1 = {
    'A':{'B':2, 'C':4},
    'B':{'A':2, 'C':3, 'D': 8},
    'C': {'A':4, 'B':3, 'E':5, 'D':2},
    'D':{'B':8, 'C':2, 'E':11, 'F':22},
    'E':{'C':5, 'D':11, 'F':1},
    'F':{'D':22, 'E':1}
}
res = graph(graph1)
dijkstra(res, 'A', 'F')




