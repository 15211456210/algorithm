package com.zcp.part4.day02;

/**
 * 贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额一次购买只能出一瓶可乐，
 * 且投钱和找零都遵循优先使用大钱的原则 需要购买的可乐数量是m， 其中手头拥有的10、50、100的数量分别为a、b、c 可乐的价格是x(x是10的倍数)
 * 请计算出需要投入硬币次数？
 * 思路1：
 * 循环每次购买可乐
 * 思路2：
 * 根据币种计算
 */
public class Code02_Cola {


    /**
     * putTimes3优化后的方法
     *
     * @param m
     * @param a
     * @param b
     * @param c
     * @param x
     * @return
     */
    public static int putTimes4(int m, int a, int b, int c, int x) {
        if (m * x > a * 10 + b * 50 + c * 100) {
            return -1;
        }

        int[] mianzhi = {100, 50, 10};
        int[] zhang = {c, b, a};

        int count = 0;
        int preZhang = 0;
        int preMoney = 0;

        for (int i = 0; i < mianzhi.length; i++) {
            if (m <= 0) {
                //如果可乐买完了，直接推出循环
                break;
            }
            //首先第一瓶可乐比较特殊，需要判断前面有没有剩余的
            int buyOneNeedCount = (x - preMoney + mianzhi[i] - 1) / mianzhi[i];
            if (zhang[i] >= buyOneNeedCount) {
                //购买第一瓶可乐
                zhang[i] -= buyOneNeedCount;
                count += buyOneNeedCount + preZhang;
                m--;
                //找零
                int rest = buyOneNeedCount * mianzhi[i] + preMoney - x;
                bakMoney(mianzhi, zhang, rest, i + 1, 1);

                //继续
                buyOneNeedCount = (x + mianzhi[i] - 1) / mianzhi[i];//正常情况下买一瓶可乐需要50面值的货币数
                int colaCount = Math.min(zhang[i] / buyOneNeedCount, m);
                count += colaCount * buyOneNeedCount;
                zhang[i] -= buyOneNeedCount * colaCount;
                m -= colaCount;//剩余需要购买的可乐数
                //找零
                rest = buyOneNeedCount * mianzhi[i] - x;
                bakMoney(mianzhi, zhang, rest, i + 1, colaCount);

                preZhang = zhang[i];
                preMoney = preZhang * mianzhi[i];
            } else {
                preMoney = mianzhi[i] * zhang[i] + preMoney;
                preZhang = zhang[i] + preZhang;
            }
        }
        return count;
    }

    /**
     * 找零方法
     * 从下表i位置开始增加货币数量
     *
     * @param mianzhi
     * @param zhang
     * @param rest
     * @param i
     * @param colaCount 购买数量
     */
    private static void bakMoney(int[] mianzhi, int[] zhang, int rest, int i, int colaCount) {
        for (; i < mianzhi.length; i++) {
            while (rest >= mianzhi[i]) {
                rest -= mianzhi[i];
                zhang[i] += colaCount;
            }
        }
    }


    /**
     * 思路2：
     * 根据币种来模拟计算
     *
     * @param m
     * @param a
     * @param b
     * @param c
     * @param x
     * @return
     */
    public static int putTimes3(int m, int a, int b, int c, int x) {
        //判断总金额是否够买m瓶可乐的
        if (m * x > a * 10 + b * 50 + c * 100) {
            return -1;
        }

        int[] mianzhi = {10, 50, 100};
        int[] zhang = {a, b, c};

        int count = 0;
        int preZhang = 0;
        int preMoney = 0;
        //买一瓶可乐需要几张100面值的货币
        int buyOneNeedCCount = (x + mianzhi[2] - 1) / mianzhi[2];
        if (zhang[2] >= buyOneNeedCCount) {
            //使用100面值购买可乐
            int colaCount = Math.min(zhang[2] / buyOneNeedCCount, m);
            count += colaCount * buyOneNeedCCount;//投币次数
            preZhang = zhang[2] - colaCount * buyOneNeedCCount;
            preMoney = preZhang * mianzhi[2];
            m -= colaCount;//剩余需要购买的可乐数
            //找零
            int rest = buyOneNeedCCount * mianzhi[2] - x;

            if (rest >= mianzhi[1]) {
                rest -= mianzhi[1];
                zhang[1] += colaCount;
            }
            if (rest > 0) {
                zhang[0] += colaCount * (rest / mianzhi[0]);
            }
        } else {
            preMoney = mianzhi[2] * zhang[2];
            preZhang = zhang[2];
        }


        if (m > 0) {
            //用50面值的购买
            //首先第一瓶可乐比较特殊，需要判断前面有没有剩余的
            int buyOneNeedBCount = (x - preMoney + mianzhi[1] - 1) / mianzhi[1];//第一瓶可乐需要50面值的货币数
            if (zhang[1] >= buyOneNeedBCount) {
                //50面值数够
                zhang[1] -= buyOneNeedBCount;
                count += buyOneNeedBCount + preZhang;
                m--;
                //找零
                int rest = buyOneNeedBCount * mianzhi[1] + preMoney - x;
                if (rest > 0) {
                    zhang[0] += rest / mianzhi[0];
                }

                //继续
                buyOneNeedBCount = (x + mianzhi[1] - 1) / mianzhi[1];//正常情况下买一瓶可乐需要50面值的货币数
                int colaCount = Math.min(zhang[1] / buyOneNeedBCount, m);
                count += colaCount * buyOneNeedBCount;
                m -= colaCount;//剩余需要购买的可乐数
                //找零
                rest = buyOneNeedBCount * mianzhi[1] - x;
                if (rest > 0) {
                    zhang[0] += colaCount * (rest / mianzhi[0]);
                }

                preZhang = zhang[1] - colaCount * buyOneNeedBCount;
                preMoney = preZhang * mianzhi[1];
            } else {
                preZhang += zhang[1];
                preMoney += zhang[1] * mianzhi[1];
            }
        }


        if (m > 0) {
            //用10面值的购买
            //首先第一瓶可乐比较特殊，需要判断前面有没有剩余的
            int buyOneNeedACount = (x - preMoney + mianzhi[0] - 1) / mianzhi[0];//第一瓶可乐需要50面值的货币数
            if (zhang[0] >= buyOneNeedACount) {
                //50面值数够
                zhang[0] -= buyOneNeedACount;
                count += buyOneNeedACount + preZhang;
                m--;
                //继续
                buyOneNeedACount = (x + mianzhi[0] - 1) / mianzhi[0];//正常情况下买一瓶可乐需要50面值的货币数
                int colaCount = Math.min(zhang[0] / buyOneNeedACount, m);
                count += colaCount * buyOneNeedACount;
                m -= colaCount;//剩余需要购买的可乐数
                preZhang = zhang[0] - colaCount * buyOneNeedACount;
                preMoney = preZhang * mianzhi[0];
            } else {
                preZhang += zhang[0];
                preMoney += zhang[0] * mianzhi[0];
            }
        }
        return count;
    }


    /**
     * 思路：
     * 模拟每次购买可乐
     *
     * @param m
     * @param a
     * @param b
     * @param c
     * @param x
     * @return
     */
    public static int putTimes2(int m, int a, int b, int c, int x) {
        //判断总金额是否够买m瓶可乐的
        if (m * x > a * 10 + b * 50 + c * 100) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            int rest = x;//扣减后剩余要投的钱
            while (rest > 0 && c > 0) {
                c--;
                count++;
                rest -= 100;
            }
            while (rest > 0 && b > 0) {
                b--;
                count++;
                rest -= 50;
            }
            while (rest > 0 && a > 0) {
                a--;
                count++;
                rest -= 10;
            }
            //此时rest<0
            //计算找回的钱
            int bak = -rest;
            if (bak >= 50) {
                b++;
                bak -= 50;
            }
            a += (bak / 10);
        }
        return count;
    }






    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */

    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    // 正式的方法
    // 要买的可乐数量，m
    // 100元有a张
    // 50元有b张
    // 10元有c张
    // 可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        //              0    1   2
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        // 总共需要多少次投币
        int puts = 0;
        // 之前面值的钱还剩下多少总钱数
        int preQianRest = 0;
        // 之前面值的钱还剩下多少总张数
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            // 要用之前剩下的钱、当前面值的钱，共同买第一瓶可乐
            // 之前的面值剩下多少钱，是preQianRest
            // 之前的面值剩下多少张，是preQianZhang
            // 之所以之前的面值会剩下来，一定是剩下的钱，一直攒不出一瓶可乐的单价
            // 当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
            // 那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= curQianFirstBuyZhang) { // 如果之前的钱和当前面值的钱，能凑出第一瓶可乐
                // 凑出来了一瓶可乐也可能存在找钱的情况，
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
                puts += curQianFirstBuyZhang + preQianZhang;
                zhang[i] -= curQianFirstBuyZhang;
                m--;
            } else { // 如果之前的钱和当前面值的钱，不能凑出第一瓶可乐
                preQianRest += qian[i] * zhang[i];
                preQianZhang += zhang[i];
                continue;
            }
            // 凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
            // 以下过程就是后续的可乐怎么用当前面值的钱来买
            // 用当前面值的钱，买一瓶可乐需要几张
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            // 用当前面值的钱，一共可以搞定几瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            // 用当前面值的钱，每搞定一瓶可乐，收货机会吐出多少零钱
            int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
            // 每次买一瓶可乐，吐出的找零总钱数是oneTimeRest
            // 一共买的可乐数是curQianBuyColas，所以把零钱去提升后面几种面值的硬币数，
            // 就是giveRest的含义
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            // 当前面值去搞定可乐这件事，一共投了几次币
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            // 还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
            m -= curQianBuyColas;
            // 当前面值可能剩下若干张，要参与到后续买可乐的过程中去，
            // 所以要更新preQianRest和preQianZhang
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
//            int m = 4;
//            int a = 4;
//            int b = 1;
//            int c = 5;
//            int x = 140;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            int ans3 = putTimes2(m, a, b, c, x);
            int ans4 = putTimes3(m, a, b, c, x);
            int ans5 = putTimes4(m, a, b, c, x);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4 || ans1 != ans5) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }

}
