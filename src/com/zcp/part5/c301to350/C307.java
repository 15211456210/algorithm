package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C307
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/range-sum-query-mutable/
 * @date 2022/9/29 14:42
 */
public class C307 {

    public class SegementTree{
        private int[] src;

        private int[] add;

        private int[] addCache;

        public int[] updateCache;

        public boolean[] updateFlag;

        /**
         * 初始化
         *
         * @param src
         */
    public SegementTree(int[] src) {
            this.src = new int[src.length];
            System.arraycopy(src, 0, this.src, 0, src.length);
            this.add = new int[src.length << 2];//原数组长度的4倍，保证可以装下所有线段树的节点
            this.addCache = new int[src.length << 2];//原数组长度的4倍，保证可以装下所有线段树的节点
            this.updateCache = new int[src.length << 2];//原数组长度的4倍，保证可以装下所有线段树的节点
            this.updateFlag = new boolean[src.length << 2];//原数组长度的4倍，保证可以装下所有线段树的节点
            buildTree();
        }

        /**
         * 构建add线段树
         */
        private void buildTree() {
            doBuild(0, this.src.length - 1, 1);
        }

        /**
         * 左范围，右范围，当前线段树数组位置
         *
         * @param l
         * @param r
         * @param pos
         */
        private void doBuild(int l, int r, int pos) {
            if (l == r) {
                add[pos] = src[l];
                return;
            }
            int mid = l + (r - l) / 2;
            doBuild(l, mid, pos * 2);//构建左子树
            doBuild(mid + 1, r, pos * 2 + 1);//构建右子树
            //计算当前节点的值
            add[pos] = add[pos * 2] + add[pos * 2 + 1];
        }

        /**
         * 在 l-r范围上的数都增加plus
         *
         * @param l
         * @param r
         * @param plus
         */
        public void add(int l, int r, int plus) {
            doAdd(l, r, 0, src.length - 1, 1, plus);
        }

        /**
         * 在l-r上执行任务（L-R范围增加plus）
         *
         * @param L    任务左边界
         * @param R    任务有边界
         * @param l    当前节点表示的左边界
         * @param r    当前节点表示的右边界
         * @param pos  当前线段树对应add、addCACHE数组的下标
         * @param plus 增量
         */
        private void doAdd(int L, int R, int l, int r, int pos, int plus) {
            if (L <= l && R >= r) {
                //表示当前节点表示的范围被任务包含了
                add[pos] += plus * (r - l + 1);
                addCache[pos] += plus;
                return;
            }
            //如果当前节点表示的范围没有全被任务指的范围包含
            //1.先将缓存中当前节点的增量值向下派发，并且计算当前位置的和
            int mid = l + (r - l) / 2;
            assignDown(mid - l + 1, r - mid, pos);
            if (mid >= L) {
                //说明左子树表示的线段范围存在任务中包含的范围
                doAdd(L, R, l, mid, pos * 2, plus);
            }
            if (mid < R) {
                //说明右子树表示的线段范围存在任务中包含的范围
                doAdd(L, R, mid + 1, r, pos * 2 + 1, plus);
            }
            add[pos] = add[pos * 2] + add[pos * 2 + 1];
        }

        /**
         * 将线段树pos对应位置缓存的增量下发一层，同时结算当前节点表示范围的和
         *
         * @param ln  左子树表示范围的个数
         * @param rn  右子树表示范围的个数
         * @param pos
         */
        private void assignDown(int ln, int rn, int pos) {

            if (updateFlag[pos]) {
                //update
//            addCache[pos] = 0;
                updateFlag[pos * 2] = true;
                updateCache[pos * 2] = updateCache[pos];
                add[pos * 2] = ln * updateCache[pos];
                addCache[pos * 2] = 0;
                updateFlag[pos * 2 + 1] = true;
                updateCache[pos * 2 + 1] = updateCache[pos];
                add[pos * 2 + 1] = rn * updateCache[pos];
                addCache[pos * 2 + 1] = 0;
                updateCache[pos] = 0;
                updateFlag[pos] = false;
            }

            if (addCache[pos] != 0) {
                //add
                add[pos * 2] += addCache[pos] * ln;
                addCache[pos * 2] += addCache[pos];
                add[pos * 2 + 1] += addCache[pos] * rn;
                addCache[pos * 2 + 1] += addCache[pos];
                addCache[pos] = 0;//当前节点的增量清空
            }
        }

        /**
         * 查询l-r范围数的和
         *
         * @param l
         * @param r
         * @return
         */
        public int query(int l, int r) {
            return doQuery(l, r, 0, src.length - 1, 1);
        }

        /**
         * 返回 L-R范围值的和
         *
         * @param L   任务左边界
         * @param R   任务右边界
         * @param l   当前节点左边界
         * @param r   当前节点右边界
         * @param pos 当前节点对应下标
         * @return
         */
        private int doQuery(int L, int R, int l, int r, int pos) {
            if (L <= l && R >= r) {
                return add[pos];
            }
            int mid = l + (r - l) / 2;
            assignDown(mid - l + 1, r - mid, pos);
            int left = 0;
            int right = 0;
            if (mid >= L) {
                //说明左子树表示的线段范围存在任务中包含的范围
                left = doQuery(L, R, l, mid, pos * 2);
            }
            if (mid < R) {
                //说明右子树表示的线段范围存在任务中包含的范围
                right = doQuery(L, R, mid + 1, r, pos * 2 + 1);
            }
            return left + right;
        }

        /**
         * 更新l-r范围的值->val
         *
         * @param l
         * @param r
         * @param val
         */
        public void update(int l, int r, int val) {
            doUpdate(l, r, 0, src.length - 1, 1, val);
        }

        /**
         * 更新l-r范围的值->val
         *
         * @param L
         * @param R
         * @param l
         * @param r
         * @param pos
         * @param val
         */
        private void doUpdate(int L, int R, int l, int r, int pos, int val) {
            if (L <= l && R >= r) {
                add[pos] = val * (r - l + 1);
                addCache[pos] = 0;
                updateCache[pos] = val;
                updateFlag[pos] = true;
                return;
            }
            int mid = l + (r - l) / 2;
            assignDown(mid - l + 1, r - mid, pos);
            if (mid >= L) {
                //说明左子树表示的线段范围存在任务中包含的范围
                doUpdate(L, R, l, mid, pos * 2, val);
            }
            if (mid < R) {
                //说明右子树表示的线段范围存在任务中包含的范围
                doUpdate(L, R, mid + 1, r, pos * 2 + 1, val);
            }
            add[pos] = add[pos * 2] + add[pos * 2 + 1];
        }
    }


    class NumArray {

        SegementTree segementTree;

        public NumArray(int[] nums) {
            this.segementTree = new SegementTree(nums);
        }

        public void update(int index, int val) {
            segementTree.update(index,index,val);
        }

        public int sumRange(int left, int right) {
            return segementTree.query(left, right);
        }
    }
}
