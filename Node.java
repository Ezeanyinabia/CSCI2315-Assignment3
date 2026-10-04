// Node of any Reference type T
public class Node<T>
{
private T value; // this is the data value
private Node<T> next; // this is pointing to the next node
// the node constructor
public Node (T v, Node<T> n)
{
value = v;
next = n;
}
// getters and setters for the node's value and next pointer
public T getValue() {return value;}
public Node<T> getNext() {return next;}
public void setValue(T v){value = v;}
public void setNext(Node<T> n){next = n;}
}