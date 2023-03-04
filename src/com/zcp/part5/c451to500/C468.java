package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C468
 * @projectName algorithm
 * @description: https://leetcode.com/problems/validate-ip-address/submissions/
 * @date 2023/2/15 15:56
 */
public class C468 {

    public String validIPAddress(String queryIP) {
        if (queryIP == null || queryIP.length() == 0) {
            return "Neither";
        }
        if (isIPv4(queryIP)) {
            return "IPv4";
        } else if (isIPv6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    public boolean isIPv4(String queryIP) {

        String[] addrs = queryIP.split("\\.");
        int count = appearCount(queryIP, '.');
        if (addrs.length != 4 || count != 3) {
            return false;
        }
        try {
            for (String addr : addrs) {
                Integer addrNum = Integer.valueOf(addr);
                if (addrNum == null || addrNum < 0 || addrNum > 255 || !String.valueOf(addrNum).equals(addr)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }


    }

    public boolean isIPv6(String queryIP) {

        String[] addrs = queryIP.split(":");
        int count = appearCount(queryIP, ':');
        if (addrs.length != 8 || count != 7) {
            return false;
        }
        try {
            for (String addr : addrs) {
                char[] chars = addr.toCharArray();
                if (chars.length < 1 || chars.length > 4) {
                    return false;
                }
                for (char c : chars) {
                    if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f'))) {
                        return false;
                    }
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public int appearCount(String s, char c) {
        int count = 0;
        for (char sc : s.toCharArray()) {
            if (sc == c) {
                count++;
            }
        }
        return count;
    }
}
