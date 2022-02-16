package com.zcp.part3.indextree;

public class IndexTree {

    int[] src;

    int[] help;

    public IndexTree(int[] src) {
        buildSrc(src);
        buildHelp();
    }

    /**
     * 构建help数组
     */
    private void buildHelp() {
        help = new int[src.length];
        for (int i = 1; i < help.length; i++) {
            int edge = i ^ (i & (-i));
            for (int cur = i; cur > edge; cur--) {
                help[i] += src[cur];
            }
        }
    }

    /**
     * 构建src数组，为方便后续下标换算，统一下标从1开始
     *
     * @param sc
     */
    private void buildSrc(int[] sc) {
        this.src = new int[sc.length + 1];
        for (int i = 0; i < sc.length; i++) {
            this.src[i + 1] = sc[i];
        }
    }

    /**
     * 返回下标start-end的和
     * 注意这里是用户传进来的下标，需要加+1
     *
     * @param start
     * @param end
     * @return
     */
    public int sum(int start, int end) {
        return sum(end + 1) - sum(start);
    }

    /**
     * 返回下标1-end的和
     *
     * @param end
     * @return
     */
    public int sum(int end) {
        int cur = end;
        int sum = 0;
        while (cur != 0) {
            sum += help[cur];
            cur = cur ^ (cur & (-cur));//去掉二进制最右侧的1
        }
        return sum;
    }

    /**
     * 更新下标index位置的数
     *
     * @param index
     * @param value
     */
    public void update(int index, int value) {
        //算出和原先的差值
        int diff = value - src[index + 1];
        src[index + 1] = value;
        if (diff != 0) {
            int cur = index + 1;
            while (cur < help.length) {
                //取help数组中每个会被影响的书加上diff偏差值
                help[cur] += diff;
                cur += cur & (-cur);//cur+二进制最右侧的1，目的为了进位
            }
        }
    }

    public static class Check {

        int[] src;

        public Check(int[] sc) {
            src = new int[sc.length];
            System.arraycopy(sc, 0, src, 0, sc.length);
        }

        public int sum(int start, int end) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += src[i];
            }
            return sum;
        }

        public void update(int index, int value) {
            src[index] = value;
        }
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length) + 1;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int size = 500;
        int range = 100;

        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(size, range);
            IndexTree indexTree = new IndexTree(array);
            Check check = new Check(array);
            for (int i1 = 0; i1 < 100; i1++) {
                if (Math.random() < 0.5) {
                    int index = (int) Math.random() * array.length;
                    int value = (int) Math.random() * range;
                    check.update(index, value);
                    indexTree.update(index, value);
                } else {
                    int start = (int) Math.random() * array.length;
                    int end = start + (int) Math.random() * (array.length - start);
                    if (check.sum(start, end) != indexTree.sum(start, end)) {
                        System.out.println("出错了");
                        break;
                    }
                }
            }
        }
        System.out.println("测试结束");
    }

}
