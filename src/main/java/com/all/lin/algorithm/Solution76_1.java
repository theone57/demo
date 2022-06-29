package com.all.lin.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution76_1 {
    public static void main(String[] args) {
        Solution76_1 solution = new Solution76_1();
        String s = solution.minWindow("aeebcdeacb", "ab");
        System.out.println("s = " + s);
    }
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {// 如果s的长度小于t的长度，那么s中就不可能存在涵盖t中所有字符的子串，因此直接返回""。
            return "";
        }
        int l = 0;//左边界
        int len = Integer.MAX_VALUE;//子串的长度，设为最大值的原因在于，在比较时不受到此值的影响，如果是一个较小数的话，可能取最小值时就会把此处设置的初始值算进去，导致结果错误。
        int lres = -1, rres = -1;//最终涵盖t中所有字符的子串的左右边界，后面会更新
        Map<Character, Integer> map = new HashMap<>();//map的作用在于记录t中存在的字符出现的次数
        for (int i = 0; i < t.length(); i++) {//记录t中存在的字符出现的次数
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (int r = 0; r < s.length(); r++) {//右边界向右延申
            if (map.containsKey(s.charAt(r))) {//如果s中当前字符存在于t中，那么就将map中对应key的value-1。这里保证了map中的key只为t中出现的字符
                map.put(s.charAt(r), map.get(s.charAt(r)) - 1);
            }
            while (isConclude(map) && l <= r) {//当map中所有key的value都小于等于0时(即t中字符全部被包含在当前子串中)，并且左边界不大于右边界 abac abc acb bac bca
                if ((r - l + 1) < len) {//进入循环的都是满足条件的子串，如果当前子串长度比上一个更小的话就更新为当前长度，最后得到的就是满足条件的子串的最小长度
                    len = r - l + 1;
                    lres = l;//更新子串的边界
                    rres = r + 1;//取r+1是为了最后返回s.substring(lres,rres)方便，因为substring(lres,rres)其中rres是不包含的
                }
                if (map.containsKey(s.charAt(l))) {//将左边界右移之前，如果左边界是map中包含的key，那么对其进行加一(从使用的状态变为未使用，此时左边界l就不算在滑动窗口内了)
                    map.put(s.charAt(l), map.get(s.charAt(l)) + 1);
                }
                l++;//左边界右移，和上面if语句是一体的，就是为了左边界右移，收缩滑动窗口
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(lres, rres);//因为有的s不存在t中所有字符，所以永远不会进入while循环中，从而进行满足条件的子串长度的更新。此时len的值仍为初始值，此时根据题意应该返回""。
    }

    public boolean isConclude(Map<Character, Integer> map) {//就是判断当前map中所有value是否小于等于0(意思就是t中的字符全部在子串中出现)
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if (value > 0) {
                return false;
            }
        }
        return true;
    }
}

//作者：Java_su
//        链接：https://leetcode.cn/problems/minimum-window-substring/solution/by-java_su-j2bu/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。