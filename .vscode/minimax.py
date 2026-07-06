def minimax(depth,node,isMax,values):
    if depth==3:
        return values[node]

    if isMax:
        return max(
            minimax(depth+1,node*2,False,values),
            minimax(depth+1,node*2+1,False,values)
        )
    else:
        return min(
            minimax(depth+1,node*2,True,values),
            minimax(depth+1,node*2+1,True,values)
        )

    values[3,5,9,7,6,-1]
    print(minimax(0,0,True,values))