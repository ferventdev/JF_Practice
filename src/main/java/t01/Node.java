package t01;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Aleksandr Shevkunenko on 24.06.2017.
 */
@Getter
public class Node {
    private final int value;
    private Node left;
    private Node right;
    private Node parent;

    private Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public Node(int value) {
        this(value, null);
    }

    public Node addOrGet(int newValue) {
        if (newValue == value) return this;

        if (newValue < value)
            if (left == null) return left = new Node(newValue,this);
            else return left.addOrGet(newValue);

        // newValue > value
        if (right == null) return right = new Node(newValue,this);
        else return right.addOrGet(newValue);
    }
}
