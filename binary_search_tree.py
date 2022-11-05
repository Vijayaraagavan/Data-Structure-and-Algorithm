class Node:
    def __init__(self, val):
        self.left = None
        self.right = None
        self.val = val

    def print_tree(self):
        inorder(self)
        print()


def inorder(root):
    # print("root: ", root.val)
    if (root):
        print(root.val, end=" ")
        inorder(root.left)
        inorder(root.right)


def insert(root, v):
    if root is None:
        return Node(v)
    else:
        if root.val == v:
            return root
        if v > root.val:
            root.right = insert(root.right, v)
        else:
            root.left = insert(root.left, v)

    return root


count = 0


def search(root, key):
    global count
    count += 1
    if root is None or root.val == key:
        return root
    if root.val < key:
        return search(root.right, key)
    else:
        return search(root.left, key)


obj = Node(5)
insert(obj, 29)
insert(obj, 4)
insert(obj, 6)
insert(obj, 3)
insert(obj, 2)
insert(obj, 1)
obj.print_tree()
res = search(obj, 1)
print(res.val, "took: {} loops".format(count))
