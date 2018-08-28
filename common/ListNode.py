class ListNode(object):
    '''
    Define List Node
    '''
    def __init__(self, value, next=None):
        self.value = value
        self.next = next
    
    def __repr__(self):
        return str(self.value)