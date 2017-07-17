public class TaskListLL implements TaskList {
    
    TaskListNode head;
   
    int size; // length of List
    
    // default constructor
    public TaskListLL() {
        head = null;
        size = 0;
    }

    // returns size of list
    public int getLength() { 
        return size;
    }

    // returns true if size == 0 else false if size != 0
    public boolean isEmpty() { 
       return size == 0;
    }

    // if list is empty return null, else return
    // removed task and head = head.next and decrement size. 
    public Task removeHead() {

        if(isEmpty()){
            return null;
        }
        Task remove = head.task;
        head = head.next;
        
        size--;
        
        return remove;    
    }
    
    // Iterates through for loop until finds number,
    // than call removeNode helper method and decrement size. 
    public Task remove(int number) {
            
        TaskListNode prev = null;
        
        for(TaskListNode curr = head ; curr != null; curr = curr.next){
            if(curr.task.getNumber() == number){
                    removeNode(curr,prev);
                    size--;
                    return curr.task;
            }
            prev = curr;
        }
        return null;
    }

    // helper method for removing nodes.
    private void removeNode(TaskListNode curr, TaskListNode prev){
        
        if(curr == head){
            head = head.next;
        }
        else {
            prev.next = curr.next;
        }
    }

    // checks that number is not already inserted in the list.
    // then allocates new newTask node with Task parameters,
    // If isEmpty true  ---> head = newTask & size = 1.
    // If isEmpty false ---> continue into while loop
    // while curr is not null & curr task has greater then or equal
    // priority to succesor than iterate through loop.
    // If prev == null insert newTask @ head else insert task
    // in prev.next.
    // increment size.
    public boolean insert(int priority, int number) {
        
        if (check(number)){ 
            return false;
        }
    
        TaskListNode newTask = new TaskListNode(new Task(priority,number));
        
        if(isEmpty()){
            head = newTask;
            size = 1;
            return true;
        }
        TaskListNode curr = head,prev = null;

        while ( curr != null && curr.task.getPriority() >= priority ){
            prev = curr;
            curr = curr.next; 
        }
        if (prev == null){
            newTask.next = head;
            head = newTask;
        } else {
            prev.next = newTask;
            newTask.next = curr;
        }
        size++;
    
        return true;
    }

    private boolean check(int number){
        
        TaskListNode curr = head;
        
        while ( curr != null ){
            if(curr.task.getNumber() == number){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Checks pre conditions then iterates through for loop until 
    // locating position and then returns value
    // or else returns null if position not found. 
    public Task retrieve(int pos) {

        int i = 0;

        if(isEmpty() || pos < 0){
            return null;
        }
        
        for(TaskListNode curr = head; curr != null; curr = curr.next){
            if (i == pos){
                return curr.task;
            }i++;   
        } 
        return null;           
    }
}