package com.zcp.part3.orderlist;

/**
 * @description: 平衡搜索二叉树
 * 搜索：任意一个节点的比左孩子大，比右孩子小（要根据具体的比较器，TODO 拓展）
 * 平衡：任意一棵子树的左右子树高度不超过1
 * @projectName:algorithm
 * @see:com.zcp.part2.avltree
 * @author:ZCP
 * @createTime:2021/11/11
 * @version:1.0
 */
public class AVLTree {

    TreeNode root;

    public class TreeNode {
        int key;//主键 ，一定要可以比较大小

        Object value;//值

        int h;//树高度

        TreeNode parent;//父节点

        TreeNode left;//左子树

        TreeNode right;//右子树

        public TreeNode(int key, Object val) {
            this.key = key;
            this.value = val;
        }

        public TreeNode(int key, Object value, int h) {
            this.key = key;
            this.value = value;
            this.h = h;
        }

        public TreeNode(int key, Object value, int h, TreeNode parent) {
            this.key = key;
            this.value = value;
            this.h = h;
            this.parent = parent;
        }
    }

    public AVLTree() {
    }

    /**
     * 插入节点
     *
     * @param key
     * @return
     */
    public Object add(int key, Object val) {
        if (root == null) {
            //如果树为空，直接创建根节点返回
            root = new TreeNode(key, val, 1, null);
            return val;
        }
        TreeNode cur = compareAndInsert(root, key, val);
        //调整树的平衡
        balance(cur);
        return val;
    }

    /**
     * 搜索某个Key
     *
     * @param key
     * @return
     */
    public Object search(int key) {
        TreeNode findNode;
        if ((findNode = doSearch(root, key)) != null) {
            return findNode.value;
        }
        return null;
    }

    /**
     * 搜索
     *
     * @param cur
     * @param key
     * @return
     */
    private TreeNode doSearch(TreeNode cur, int key) {
        if (cur == null) {
            return null;
        }
        if (cur.key == key) {
            return cur;
        } else if (cur.key < key) {
            return doSearch(cur.right, key);
        } else {
            return doSearch(cur.left, key);
        }
    }

    /**
     * 删除节点
     *
     * @param key
     * @return
     */
    public Object delete(int key) {
        TreeNode findNode;
        if ((findNode = doSearch(root, key)) == null) {
            //删除节点之前先查一下有没有
            return null;
        }
        //存在该节点
        //记录需要调整的当前位置
        doDelete(findNode);

        return findNode.key;
    }

    /**
     * 删除节点
     *
     * @param delNode
     */
    private TreeNode doDelete(TreeNode delNode) {
        TreeNode cHead = null;//调整树平衡的开始节点
        TreeNode retNode = delNode;//返回节点
        if (delNode.left == null && delNode.right == null) {
            cHead = delNode.parent;
            delNode.parent = null;
            if (cHead != null) {
                if (cHead.left == delNode) {
                    cHead.left = null;
                } else {
                    cHead.right = null;
                }
            } else {
                root = null;
                return null;
            }
        } else {
            TreeNode mostLeft = delNode.right;
            if (mostLeft == null) {
                //如果要删除的节点右子树为空 说明左子树一定不为空
                //将左子树上来
                //headNode 表示即将上位的左子树头节点
                cHead = delNode.left;
                delNode.left = null;
                cHead.parent = delNode.parent;
                delNode.parent = null;
                if (cHead.parent == null) {
                    //根节点
                    root = cHead;
                } else {
                    if (cHead.parent.left == delNode) {
                        cHead.parent.left = cHead;
                    }else {
                        cHead.parent.right = cHead;
                    }
                }
            } else {
                //右子树不为空，需要将左子树最左节点取出来
                while (mostLeft.left != null) {
                    mostLeft = mostLeft.left;
                }
                if (mostLeft.right != null) {
                    cHead = mostLeft.right;
                    mostLeft.left = null;
                    cHead.parent = mostLeft.parent;
                    mostLeft.parent = null;
                    if (cHead.parent.left == mostLeft) {
                        cHead.parent.left = cHead;
                    } else {
                        cHead.parent.right = cHead;
                    }
                } else {
                    cHead = mostLeft.parent;
                    mostLeft.parent = null;
                    if (cHead == delNode) {
                        delNode.right = null;
                    } else {
                        cHead.left = null;
                    }

                }
                //将delNode替换成mostLeft
                delNode.key = mostLeft.key;
                delNode.value = mostLeft.value;
            }
        }
        balance(cHead);
        return retNode;
    }

    /**
     * 调整树平衡
     *
     * @param cur
     * @return
     */
    private TreeNode balance(TreeNode cur) {
        //首先cur一定不为空
        if (cur == null) {
            return root;
        }
        //树给调整后会换头，用这个变量记录头
        TreeNode cHead = cur;
        int lh = cur.left == null ? 0 : cur.left.h;//3
        int rh = cur.right == null ? 0 : cur.right.h;//1
        cur.h = Math.max(lh, rh) + 1;
        if (lh > rh + 1) {
            //如果cur左子树不为空，右子树为空判断是ll型还是lr型
            if (cur.left.h > 1) {
                //不平衡
                //左左子树高度
                int llh = cur.left.left == null ? 0 : cur.left.left.h;//2
                //左右子树高度
                int lrh = cur.left.right == null ? 0 : cur.left.right.h;//1
                if (llh >= lrh) {
                    //如果llh==lrh，或则 ll型 只需调整一次，将cur节点右旋
                    cHead = rightHand(cur);
                } else {
                    //lr型，想方设法将底层的r移动到最上面去，
                    //先cur.l左旋
                    leftHand(cur.left);
                    //再将cur右旋
                    cHead = rightHand(cur);
                }
            }
        } else if (rh > lh + 1) {
            if (cur.right.h > 1) {
                //不平衡
                //右左子树高度
                int rlh = cur.right.left == null ? 0 : cur.right.left.h;
                //右右子树高度
                int rrh = cur.right.right == null ? 0 : cur.right.right.h;
                if (rrh >= rlh) {
                    cHead = leftHand(cur);
                } else {
                    //lr型，想方设法将底层的r移动到最上面去，
                    //先cur.l左旋
                    rightHand(cur.right);
                    //再将cur右旋
                    cHead = leftHand(cur);
                }
            }
        }
        //调整好了后继续跳到父节点去调整
        return balance(cHead.parent);
    }

    /**
     * 左旋
     *
     * @param cur
     */
    private TreeNode leftHand(TreeNode cur) {
        TreeNode cRight = cur.right;
        cur.right = cRight.left;
        if (cur.right != null) {
            cur.right.parent = cur;
        }
        cRight.left = cur;
        cRight.parent = cur.parent;
        cur.parent = cRight;
        if (cRight.parent == null) {
            //说明是根节点
            root = cRight;
        } else {
            if (cRight.parent.left == cur) {
                cRight.parent.left = cRight;
            } else {
                cRight.parent.right = cRight;
            }
        }
        //调整树高度
        cur.h = Math.max(cur.left == null ? 0 : cur.left.h, cur.right == null ? 0 : cur.right.h) + 1;
        cRight.h = Math.max(cRight.left == null ? 0 : cRight.left.h, cRight.right == null ? 0 : cRight.right.h) + 1;
        return cRight;
    }

    /**
     * 右旋
     *
     * @param cur
     * @return
     */
    private TreeNode rightHand(TreeNode cur) {

        TreeNode cLeft = cur.left;
        cur.left = cLeft.right;
        if (cLeft.right != null) {
            cLeft.right.parent = cur;
        }
        cLeft.right = cur;
        cLeft.parent = cur.parent;
        cur.parent = cLeft;
        if (cLeft.parent == null) {
            root = cLeft;
        } else {
            if (cLeft.parent.left == cur) {
                cLeft.parent.left = cLeft;
            } else {
                cLeft.parent.right = cLeft;
            }
        }
        //依次调整树高度
        //先调整右旋下来的cur节点高度
        cur.h = Math.max(cur.left == null ? 0 : cur.left.h, cur.right == null ? 0 : cur.right.h) + 1;
        //再调整上去的cLeft
        cLeft.h = Math.max(cLeft.left == null ? 0 : cLeft.left.h, cLeft.right == null ? 0 : cLeft.right.h) + 1;
        //返回调整后的头节点
        return cLeft;
    }

    /**
     * 插入节点
     * 返回当前插入的节点
     *
     * @param root
     * @param key
     * @param val
     */
    private TreeNode compareAndInsert(TreeNode root, int key, Object val) {
        if (root.key == key) {
            //当前遍历到的节点就是对应的节点，直接替换覆盖value
            root.value = val;
            return root;
        } else if (root.key > key) {
            //当前遍历到的节点比要插入的大，往左边走
            if (root.left == null) {
                root.left = new TreeNode(key, val, 1, root);
                //更新root树高度
                if (root.right == null) {
                    root.h = 2;
                }
                return root.left;
            }
            return compareAndInsert(root.left, key, val);
        } else {
            if (root.right == null) {
                root.right = new TreeNode(key, val, 1, root);
                //更新root树高度
                if (root.left == null) {
                    root.h = 2;
                }
                return root.right;
            }
            return compareAndInsert(root.right, key, val);
        }
    }


    static boolean isAVLTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isAVL(root).isAvl;
    }

    private static Info isAVL(TreeNode root) {
        Info info = new Info();
        if (root.left == null && root.right == null) {
            info.isAvl = true;
            info.max = root.key;
            info.min = root.key;
            info.h = 1;
        } else if (root.left != null && root.right == null) {
            Info leftInfo = isAVL(root.left);
            info.max = Math.max(root.key, leftInfo.max);
            info.min = Math.min(root.key, leftInfo.min);
            info.h = leftInfo.h + 1;
            info.isAvl = info.h < 3 && leftInfo.max < root.key;
        } else if (root.left == null && root.right != null) {
            Info rightInfo = isAVL(root.right);
            info.max = Math.max(root.key, rightInfo.max);
            info.min = Math.min(root.key, rightInfo.min);
            info.h = rightInfo.h + 1;
            info.isAvl = info.h < 3 && rightInfo.min > root.key;
        } else {
            //root.left != null && root.right != null
            Info leftInfo = isAVL(root.left);
            Info rightInfo = isAVL(root.right);
            info.h = Math.max(leftInfo.h, rightInfo.h) + 1;
            info.max = Math.max(leftInfo.max, rightInfo.max);
            info.min = Math.min(leftInfo.min, rightInfo.min);
            info.isAvl = leftInfo.isAvl && rightInfo.isAvl && leftInfo.max < root.key && rightInfo.min > root.key && Math.abs(leftInfo.h - rightInfo.h) < 2;
        }
        return info;
    }

    public static class Info {
        boolean isAvl;
        int h;
        int min;
        int max;

        public Info() {
        }

        public Info(boolean isAvl, int h, int min, int max) {
            this.isAvl = isAvl;
            this.h = h;
            this.min = min;
            this.max = max;
        }

        public Info(int h, int min, int max) {
            this.h = h;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean checkParent(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkParent2(root);
    }


    private static boolean checkParent2(TreeNode root) {
        boolean left = true;
        boolean right = true;
        if (root.left != null) {
            if (root.left.parent != root) {
                return false;
            }
            left = checkParent2(root.left);
        }
        if (root.right != null) {
            if (root.right.parent != root) {
                return false;
            }
            right = checkParent2(root.right);
        }
        return left && right;
    }

    public static void main(String[] args) {

//        AVLTree tree = new AVLTree();
//        tree.add(7, 7);
//        tree.add(1, 7);
//        tree.add(6, 7);
//        tree.add(9, 7);
//        tree.add(4, 7);
//        tree.delete(9);
//        tree.add(5, 7);
//        tree.add(3, 7);
//        tree.delete(7);
//        tree.delete(6);
//        System.out.println(checkParent2(tree.root));
//        System.out.println();
        int testTime = 10000;
        int range = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            AVLTree avlTree = new AVLTree();
            for (int k = 0; k < 1000; k++) {
                if (Math.random() < 0.8) {
                    int key = (int) (Math.random() * range);
//                    System.out.println("add" + key);
                    avlTree.add(key, Math.random() * range);

                } else {
                    int key = (int) (Math.random() * range);
//                    System.out.println("del" + key);
                    avlTree.delete(key);

                }

//                if (!checkParent(avlTree.root)){
//                    System.out.println("出错了");
//                    System.exit(0);
//                }
                if (!isAVLTree(avlTree.root)) {
                    System.out.println("出错了");
                    System.exit(0);
                    break;
                }
            }
        }
        System.out.println("测试结束");
    }

}
