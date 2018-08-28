class ListNode(object):
     def __init__(self, val, next=None):
         self.val = val
         self.next = next

def addTwoNumers(l1, l2):
    current = 0
    carry = 0
    rList = None
    while l1 or l2 or carry != 0:
        current += carry
        if l1:
            current += l1.val
            l1 = l1.next
        if l2:
            current += l2.val
            l2 = l2.next
        carry = current // 10
        current = current % 10

        if rList is None:
            rList = ListNode(current)
            head = rList
        else:
            rList.next = ListNode(current)
            rList = rList.next
        current = 0
    return head


if __name__ == "__main__":
    l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next.next = ListNode(3)

    l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next.next = ListNode(4)

    newListNode = addTwoNumers(l1, l2)
    while newListNode:
        print(newListNode.val, end=" ")
        newListNode = newListNode.next
