package com.mobileai.dxc.db.pojo;

import lombok.Data;

@Data
public class User {

    private int userId;

    private String name;

    /**
     * 是否实名认证
     */
    private boolean certified;

    private String phone;

    private String picName;

    /**
     * 建造者
     */
    public static class Builder {

        private User ins;

        public Builder() {
            ins = new User();
        }

        public Builder setUserId(int userId) {
            ins.userId = userId;
            return this;
        }

        public Builder sePhone(String phone) {
            ins.phone = phone;
            return this;
        }

        public Builder setName(String name) {
            ins.name = name;
            return this;
        }

        public Builder setCertified(boolean certified) {
            ins.certified = certified;
            return this;
        }

        public Builder setPicName(String picName) {
            ins.picName = picName;
            return this;
        }

        public User build() {
            return ins;
        }

    }

}