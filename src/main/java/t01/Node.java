package t01;

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

        if (newValue < value) {
            if (left == null) return left = new Node(newValue, this);
            else return left.addOrGet(newValue);
        }

        // newValue > value
        if (right == null) return right = new Node(newValue,this);
        else return right.addOrGet(newValue);
    }

    public int getStepCount(int anotherValue) {
        return getStepCountHelper(anotherValue, 0, false,false);
    }

    private int getStepCountHelper(int anotherValue, int count, boolean leftWatched, boolean rightWatched) {
        if (anotherValue == value) return count;

        int stepCount = -1;
        if (left != null && anotherValue < value && !leftWatched)
            stepCount = left.getCountWithinSubtree(anotherValue, count + 1);
        if (stepCount != -1) return stepCount;

        if (right != null && anotherValue > value && !rightWatched)
            stepCount = right.getCountWithinSubtree(anotherValue, count + 1);
        if (stepCount != -1 || parent == null) return stepCount;

        // now parent != null
        if (this == parent.left) {
            leftWatched = true;
            rightWatched = false;
        } else if (this == parent.right) {
            rightWatched = true;
            leftWatched = false;
        } else throw new AssertionError("This node must be either left or right child of the parent!");

        return parent.getStepCountHelper(anotherValue, count + 1, leftWatched, rightWatched);
    }

    private int getCountWithinSubtree(int anotherValue, int count) {
        if (anotherValue == value) return count;

        if (anotherValue < value) {
            if (left != null) return left.getCountWithinSubtree(anotherValue, count + 1);
            else return -1;
        }

        // anotherValue > value
        if (right != null) return right.getCountWithinSubtree(anotherValue, count + 1);
        else return -1;
    }

}
