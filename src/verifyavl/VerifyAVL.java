package verifyavl;

public class VerifyAVL {
    public static boolean verifyAVL(AVLNode root) {
        try {
            verifyBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            verifyHeight(root);
            verifyBalance(root);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private static int verifyHeight(AVLNode root){
        if (root == null){
            return 0;
        }
        int left = verifyHeight(root.left);
        int right = verifyHeight(root.right);
        if (root.left != null && root.left.height != left - 1) {
            throw new IllegalStateException();
        }
        if (root.right != null && root.right.height != right - 1) {
            throw new IllegalStateException();
        }
        if (root.height != Math.max(verifyHeight(root.left), verifyHeight(root.right))) {
            throw new IllegalStateException();
        }
        return Math.max(verifyHeight(root.left), verifyHeight(root.right)) + 1;
    }

    private static void verifyBST(AVLNode node, int min, int max) {
        if (node == null) {
            return;
        }
        if (node.left != null)
            verifyBST(node.left, min, node.left.key);
        if (node.right != null)
            verifyBST(node.right, node.right.key, max);
        if (node.right != null && (node.right.key < node.key || node.right.key > max))
            throw new IllegalStateException();
        if (node.left != null && (node.left.key < min || node.left.key > node.key))
            throw new IllegalStateException();
    }

    private static void verifyBalance(AVLNode node){
        if(node == null || (node.left == null && node.right == null)){
            return;
        }
        if ((node.right == null && (node.left.height > 1 || node.height > 1)) || (node.left == null && (node.right.height > 1 || node.height > 1)) ||
                (node.left != null && node.right != null && Math.abs(node.left.height - node.right.height) > 1 )) {
            throw new IllegalStateException();
        }
        verifyBalance(node.left);
        verifyBalance(node.right);
    }
    /* TODO: Add private methods if you want (recommended) */
}