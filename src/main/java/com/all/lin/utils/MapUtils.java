//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.all.lin.utils;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class MapUtils {
    private MapUtils() {
    }

    public static TreeMap<String, Object> getTreeMap(Map<String, Object> map) {
        TreeMap<String, Object> treeMap = Maps.newTreeMap();
        Iterator var2 = map.entrySet().iterator();

        while(true) {
            Entry entry;
            Object value;
            do {
                if (!var2.hasNext()) {
                    return treeMap;
                }

                entry = (Entry)var2.next();
                value = entry.getValue();
            } while(null == value);

            if (value instanceof Map) {
                Map<String, Object> bodyMap = (Map)value;
                TreeMap<String, Object> bodyTree = Maps.newTreeMap();
                Iterator var7 = bodyMap.keySet().iterator();

                while(var7.hasNext()) {
                    String key = (String)var7.next();
                    Object bodyValue = bodyMap.get(key);
                    if (null != bodyValue) {
                        bodyTree.put(key, bodyValue);
                    }
                }

                if (org.apache.commons.collections.MapUtils.isNotEmpty(bodyTree)) {
                    value = bodyTree;
                }
            }

            treeMap.put((String)entry.getKey(), value);
        }
    }
}
