package com.all.lin.jdk8.stream8.groupingby;

public enum SortEnum {
        SIX("六年级", 1),
        SEVEN("七年级", 2),
        NINE("九年级", 3),
        OTHER("其他", -1);
 
        private SortEnum(String name, Integer value) {
            this.value = value;
            this.name = name;
        }
 
        public Integer getValue() {
            return value;
        }
 
        private Integer value;
 
        public String getName() {
            return name;
        }
 
        private String name;
 
        public static Integer getSort(String name) {
            if (name == null) {
                return OTHER.getValue();
            }
            SortEnum[] instances = SortEnum.values();
            for (SortEnum i : instances) {
                if (name != null && name.equals(i.getName())) {
                    return i.getValue();
                }
            }
            return OTHER.getValue();
        }
    }