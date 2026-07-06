def dfs(graph,start,goal):
    visited=set()
    stack=[[start]]

    while stack:
        path=stack.pop()
        node=path[-1]

        if node==goal:
            return path

        if node not in visited:
            visited.add(node)
            for neighbour in graph[node]:
                new_path=list(path)
                new_path.append(node)
                stack.append(new_path)
    return None

    start='A'
    goal='F'

    result = dfs(graph,start,goal)
    if result:
        print("Path found",result)
        print("path: ","->".join(result))
    else :
        print("path not found")