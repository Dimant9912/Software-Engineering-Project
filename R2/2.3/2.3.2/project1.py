my_table= [
    [1,2,3,4],
    [5,6,7,8],
    [9,10,11,12]
]
my_table.insert(0,[0,0,0,0])
print(my_table)
for i in my_table:
    i.append(1)
print(my_table)
