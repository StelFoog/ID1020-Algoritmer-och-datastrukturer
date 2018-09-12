package com.company;

public class LinkedStack<Item> {
    private int size;
    private Node newest;

     private class Node {
        private Item item;
        private Node link;

        private Node(Item item, Node link) {
            this.item = item;
            this.link = link;
        }
    }

    public LinkedStack() {
         newest = null;
         size = 0;
    }

    public int getSize() { return size; }

    public void push(Item item) {
         Node link = newest;
         newest = new Node(item, link);
         size++;
    }

    public Item pop() {
         if(size == 0) return null;
         Item pass = newest.item;
         newest = newest.link;
         size--;

         return pass;
    }

    // Make main() for testing

}
