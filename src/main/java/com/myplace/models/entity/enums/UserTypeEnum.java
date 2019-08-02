package com.myplace.models.entity.enums;

import java.util.Objects;

public enum UserTypeEnum {
    //博主
    博主(1, "博主"),
    //银行转账
    其他(2, "其他");

    int index;
    String type;

    UserTypeEnum(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    /**
     * 通过index获取value
     *
     * @param index 枚举索引
     * @return 枚举值
     */
    public static String getValue(Integer index) {
        for (UserTypeEnum c : UserTypeEnum.values()) {
            if (Objects.equals(c.getIndex(), index)) {
                return c.type;
            }
        }
        return null;
    }

    /**
     * 通过value获取index
     *
     * @param type 枚举值
     * @return 枚举索引
     */
    public static String getIndex(String type) {
        for (UserTypeEnum c : UserTypeEnum.values()) {
            if (Objects.equals(c.getType(), type)) {
                return c.index + "";
            }
        }
        return "error";
    }
}
