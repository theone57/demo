package com.all.lin.algorithm;

import java.util.HashSet;
import java.util.Set;

class Solution39_1 {
    Set<String> list = new HashSet<>();

    public String[] permutation(String s) {

        boolean[] vis = new boolean[s.length()];
        dfs(s, new StringBuffer(""), vis);

        return list.toArray(new String[list.size()]);

    }

    public void dfs(String s, StringBuffer str, boolean[] vis) {

        if (s.length() == str.length()) {
            list.add(str.toString());
        }

        for (int i = 0; i < s.length(); i++) {
            if (!vis[i]) {
                vis[i] = true;
                str.append(s.charAt(i));
                dfs(s, str, vis);
                vis[i] = false;
                str.deleteCharAt(str.length() - 1);
            }
        }
    }
}

//作者：dehua-5
//        链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/solution/li-yong-by-dehua-5-c5sa/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。