package com.zcp.part3.orderlist;

/**
 * @description: sb tree
 * 任何一节点的size（树节点数量）不能大于 叔叔节点的size
 * 四种违规行为：
 * ll：根右旋
 * lr：l左旋，根右旋
 * rr：根左旋
 * rl：r右旋，根左旋
 * @projectName:algorithm
 * @see:com.zcp.part2.sbTree
 * @author:ZCP
 * @createTime:2021/11/13
 * @version:1.0
 */
public class SizeBalanceTree {

    TreeNode root;

    int total;

    public static class TreeNode {
        int key;
        Object value;
        int size;//树节点个数
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int key, Object value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public SizeBalanceTree() {
    }

    /**
     * 添加元素
     *
     * @param key
     * @param val
     */
    public void add(int key, Object val) {
        if (root == null) {
            total++;
            root = new TreeNode(key, val, 1);
        } else {
            root = insert(root, key, val);
        }
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public int delete(int key) {
        if (containsKey(key)) {
            total--;
            root = doDelete(root, key);
        }
        return key;
    }


    /**
     * 删除节点
     *
     * @param root
     * @param key
     * @return
     */
    private TreeNode doDelete(TreeNode root, int key) {
        if (root.key == key) {
            //删除当前节点，并且返回新的头节点（可能会换头）
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.left != null) {
                return root.right;
            } else {
                //左右子树都不为空，取后继节点
                TreeNode pre = root;//当前后继节点的父节点
                pre.size--;
                TreeNode mostLeft = root.right;
                while (mostLeft.left != null) {
                    pre.size--;
                    pre = mostLeft;
                    mostLeft = mostLeft.left;
                }
                //先把后继节点值赋给当前删除的节点
                root.key = mostLeft.key;
                root.value = mostLeft.value;
                //
                if (pre.left == mostLeft) {
                    pre.left = doDelete(mostLeft, mostLeft.key);
                } else {
                    pre.right = doDelete(mostLeft, mostLeft.key);
                }
                return root;
            }
        }
        if (root.left != null) {
            root.left = doDelete(root.left, key);
        }
        if (root.right != null) {
            root.right = doDelete(root.right, key);
        }
        //沿途父节点数量减一
        root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
        return root;
    }

    /**
     * 是否包含某个Key
     *
     * @param key
     * @return
     */
    public boolean containsKey(int key) {
        return get(key) != null;
    }

    /**
     * 获取某个key
     *
     * @param key
     * @return
     */
    public TreeNode get(int key) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }

    /**
     * 插入 返回新的头节点（可能会换头）
     *
     * @param root
     * @param key
     * @param val
     * @return
     */
    private TreeNode insert(TreeNode root, int key, Object val) {
        if (root.key == key) {
            root.value = val;
            return balance(root);
        } else if (root.key < key) {
            if (root.right != null) {
                TreeNode right = insert(root.right, key, val);
                root.right = balance(right);
                return balance(root);
            } else {
                total++;
                root.size++;
                root.right = new TreeNode(key, val, 1);
                return root;
            }
        } else {
            if (root.left != null) {
                TreeNode left = insert(root.left, key, val);
                root.left = balance(left);
                return balance(root);
            } else {
                total++;
                root.size++;
                root.left = new TreeNode(key, val, 1);
                return root;
            }
        }
    }

    /**
     * 树平衡调整
     * * ll：根右旋
     * * lr：l左旋，根右旋
     * * rr：根左旋
     * * rl：r右旋，根左旋
     *
     * @param root
     * @return
     */
    private TreeNode balance(TreeNode root) {
        int l = root.left == null ? 0 : root.left.size;
        int r = root.right == null ? 0 : root.right.size;
        int ll = root.left == null ? 0 : root.left.left == null ? 0 : root.left.left.size;
        int lr = root.left == null ? 0 : root.left.right == null ? 0 : root.left.right.size;
        int rr = root.right == null ? 0 : root.right.right == null ? 0 : root.right.right.size;
        int rl = root.right == null ? 0 : root.right.left == null ? 0 : root.right.left.size;
        if (l < rr) {
            TreeNode head = leftRotation(root);
            TreeNode rHead = balance(head);
            rHead.left = balance(rHead.left);
            return rHead;
        } else if (l < rl) {
            root.right = rightRotation(root.right);
            TreeNode head = leftRotation(root);
            TreeNode rHead = balance(head);
            rHead.left = balance(rHead.left);
            rHead.right = balance(rHead.right);
            return rHead;
        } else if (r < ll) {
            TreeNode head = rightRotation(root);
            TreeNode rHead = balance(head);
            rHead.right = balance(rHead.right);
            return rHead;
        } else if (r < lr) {
            root.left = leftRotation(root.left);
            TreeNode head = rightRotation(root);
            TreeNode rHead = balance(head);
            rHead.left = balance(rHead.left);
            rHead.right = balance(rHead.right);
            return rHead;
        } else {
            //就算是无需调整也应该把size大小调整对
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            return root;
        }
    }

    /**
     * 右旋
     *
     * @param root
     * @return
     */
    private TreeNode rightRotation(TreeNode root) {
        TreeNode head = root.left;
        root.left = head.right;
        head.right = root;
        root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
        head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
        return head;
    }

    /**
     * 左旋
     *
     * @param root
     * @return
     */
    private TreeNode leftRotation(TreeNode root) {
        TreeNode head = root.right;
        root.right = head.left;
        head.left = root;
        root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
        head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
        return head;
    }

    public static class Info {
        boolean isSBTree;
        int size;
        int lSize;
        int rSize;
        int min;
        int max;

        public Info() {
        }

        public Info(int size, int lSize, int rSize, int min, int max) {
            this.size = size;
            this.lSize = lSize;
            this.rSize = rSize;
            this.min = min;
            this.max = max;
        }
    }

    /**
     * 校验是否是SB树
     *
     * @param root
     * @return
     */
    public static Info checkSBTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info info = new Info();
        Info left = checkSBTree(root.left);
        Info right = checkSBTree(root.right);
        if (left != null && right != null) {
            info.lSize = left.size;
            info.rSize = right.size;
            info.size = info.lSize + info.rSize + 1;
            info.min = Math.min(right.min, Math.min(root.key, left.min));
            info.max = Math.max(right.max, Math.min(root.key, left.max));
            info.isSBTree = left.isSBTree && right.isSBTree && left.max < root.key && right.min > root.key
                    && info.lSize >= right.lSize && info.lSize >= right.rSize && info.rSize >= left.lSize && info.rSize >= left.rSize;
        } else if (left != null && right == null) {
            info.lSize = left.size;
            info.size = info.lSize + info.rSize + 1;
            info.min = Math.min(root.key, left.min);
            info.max = Math.min(root.key, left.max);
            info.isSBTree = left.isSBTree && left.max < root.key && info.rSize >= left.lSize && info.rSize >= left.rSize;
        } else if (left == null && right != null) {
            info.rSize = right.size;
            info.size = info.lSize + info.rSize + 1;
            info.min = Math.min(root.key, right.min);
            info.max = Math.min(root.key, right.max);
            info.isSBTree = right.isSBTree && right.min > root.key && info.lSize >= right.lSize && info.lSize >= right.rSize;
        } else {
            info.size = 1;
            info.max = root.key;
            info.min = root.key;
            info.isSBTree = true;
        }
        return info;
    }

    public static void main(String[] args) {

        int testTime = 1000;
        int range = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            SizeBalanceTree sb = new SizeBalanceTree();
//            System.out.println("------");
            for (int k = 0; k < 1000; k++) {
                if (Math.random() < 0.8) {
                    int key = (int) (Math.random() * range);
//                    System.out.println("add" + key);
                    sb.add(key, Math.random() * range);
                    if (!checkSBTree(sb.root).isSBTree && sb.total < 4) {
                        System.out.println("出错了");
                        System.exit(0);
                        break;
                    }
                } else {
                    int key = (int) (Math.random() * range);
//                    System.out.println("del" + key);
                    sb.delete(key);

                }
            }
        }
        System.out.println("测试结束");


    }


}
