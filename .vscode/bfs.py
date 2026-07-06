from collections import deque

def bfs(graph,goal,start):
    visited=set()
    queue=deque([[start]])

    if start==goal:
        return start
while queue:
    path=queue.popleft()
    node=path[-1]
    if node not in visited:
        visited.add(node)

        for neighbour in graph[node]:
            new_path=list(path)
            new_path.append(node)
            if neighbour == goal:
                return new_path
            queue.append(new_path)
return None

start='A'
goal='F'

graph={
    'A':['B','C'],
}
reuslt=bfs(graph,start,goal)
if result:
    print("path found",result)
    print("path","->".join(result))
            