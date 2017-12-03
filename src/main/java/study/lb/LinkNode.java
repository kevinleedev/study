package study.lb;

/**
 * 单向链表
 * <p>
 * <p>desc: </p>
 * author: lilin
 * created: 2017/12/3 01:32
 **/
public class LinkNode {

    private Object data;
    private LinkNode next;

    public Object getData() {
        return data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public LinkNode(Object data) {
        this.data = data;
    }

    public LinkNode addNode(LinkNode newNode) {
        if (null == this.next) {
            this.next = newNode;
        } else {
            this.next.addNode(newNode);
        }
        return this;
    }

    public boolean contains(Object data) {
        if (null == data) {
            return false;
        }
        if (data.equals(this.data)) {
            return true;
        } else {
            if (null != this.next) {
                return this.next.contains(data);
            } else {
                return false;
            }
        }
    }

    public boolean removeNode(Object data) {
        if (null == data) {
            return false;
        } else {
            if (null != this.next) {
                if (this.next.data.equals(data)) {
                    //删除当前节点，this的next直接只想next的next
                    this.next = this.next.next;
                    return true;
                } else {
                    return this.next.removeNode(data);
                }
            }
            return false;
        }
    }

    public LinkNode reverse(LinkNode head) {
        if (null == head || null == head.getNext()) {
            return head;
        }
        LinkNode reverseHead = reverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reverseHead;
    }

    /**
     * 打印链表
     */
    public void print() {
        if (null == this) {
            System.out.println("LinkList is empty");
        } else {
            LinkNode h = this;
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            while (null != h) {
                if (0 != sb.substring(1).length()) {
                    sb.append(",");
                }
                sb.append(h.getData());
                h = h.next;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }

}

class Main {

    public static void main(String[] args) {
        LinkNode linkNode = new LinkNode(1);
//        LinkNode linkNode2 = new LinkNode(2);
//        LinkNode linkNode3 = new LinkNode(3);
//        LinkNode linkNode4 = new LinkNode(4);
//        linkNode.setNext(linkNode2);
//        linkNode2.setNext(linkNode3);
//        linkNode3.setNext(linkNode4);

        linkNode.addNode(new LinkNode(2)).addNode(new LinkNode(3)).addNode(new LinkNode(4));

        linkNode.print();

        System.out.println(linkNode.contains(11));

        linkNode.removeNode(2);
        linkNode.print();

        LinkNode newHead = linkNode.reverse(linkNode);
        newHead.print();
    }
}
