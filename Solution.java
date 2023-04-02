import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Scanner input = new Scanner(System.in);

        while(true){
            String rute = input.nextLine();
            String[] command = rute.split(" ");
            if(command [0].equals("MASUK")) {
                list.Tambah(command[1]);
            }else if (command [0].equals("SISIP")) {
                int a = Integer.parseInt(command[2]);
                list.insertAt(command[1], a);
            }else if(command [0].equals("HAPUS")){
                int lepas = Integer.parseInt(command[1]);
                list.removeAt(lepas);
            }else if(command [0].equals("SIMPAN")){
                break;
            }
        }
        System.out.println("Mode Perjalanan");

        while(true){
            String perintah = input.nextLine();
            if(perintah.equals("PERGI NEXT")){
                list.displayNextValues();
            }else if(perintah.equals("PERGI BEFORE")){
                list.printPrevious();
            }else if(perintah.equals("SELESAI")){
                System.exit(0);
            }
        }
    }
}
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;


    Node(T data){
        this.data = data;
    }

    public Node getPrev() {
        return prev;
    }
}

class DoublyLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    Node<T> current;

    void Tambah(T data){
        if (tail == null) {
            tail = new Node<T>(data);
            head = tail;
            current = head;
        } else {
            tail.next = new Node<T>(data);
            tail.next.prev = tail;
            tail = tail.next;
        }
    }

    void removeTail(){
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> currNode = head;
            while (currNode.next.next != null) currNode = currNode.next;
            tail = currNode;
            currNode.next = null;
        }
    }

    void removeHead(){
        if (head == tail) {
            head = null;
            tail = null;
        } else head = head.next;
        current = head;
    }

    void removeAt(int idx){
        if (idx == 0){
            removeHead();
        }else {
            Node<T> currNode = head;
            for (int i=0; i < idx - 1; i++){
                currNode = currNode.next;
            }
            if(currNode.next == tail){
                removeTail();
            }else{

                currNode.next = currNode.next.next;
                currNode.next.prev = currNode;
            }


        }
    }


    void insertAt(T data, int index){
        Node<T> A = head;
        if(head == null){
            head = tail = new Node<>(data);
        }else if(index == 0){
            Node<T> temp = head;
            head.prev = new Node<>(data);
            head = head.prev;
            head.next = temp;
            current = head;
        }else{
            for (int i=0; i< index - 1; i++){
                A=A.next;
            }
            if(A == tail){
                Tambah(data);
            }else{
                Node<T> B = A.next;
                Node<T> temp = new Node<>(data);

                temp.next = B;
                temp.prev = A;

                A.next = temp;
                B.prev = temp;
            }
        }
    }

    public void displayNextValues() {

        // traverse the list and print the next value of each node
        if (current.next != null) {
            current = current.next;
            System.out.println("Sedang berada di "+ current.data);
        } else {
            System.out.println("Tujuan tidak valid");
        }

    }



    public void printPrevious() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("Sedang berada di " + current.data);
        } else {
            System.out.println("Tujuan tidak valid");
        }
    }
}

