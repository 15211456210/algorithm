package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C478
 * @projectName algorithm
 * @description: https://leetcode.com/problems/generate-random-point-in-a-circle/
 * @date 2023/2/21 16:48
 */
public class C478 {

    class Solution {
        double radius;
        double x_center;
        double y_center;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.x_center = x_center;
            this.y_center = y_center;

        }

        public double[] randPoint() {
            double x = x_center - radius + Math.random() * (radius * 2);
            double y = y_center - radius + Math.random() * (radius * 2);
            while ((x - x_center) * (x - x_center) + (y - y_center) * (y - y_center) >= (radius * radius)) {
                x = x_center - radius + Math.random() * (radius * 2);
                y = y_center - radius + Math.random() * (radius * 2);
            }
            return new double[]{x, y};
        }
    }

}
