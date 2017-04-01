package com.glexer.smartlamp.data.model;

/**
 * Created by Trice on 2017/3/21.
 */

public class UserInfo {
    /**
     * token : xxx
     * user : {"id":"xxx","loginName":"xxx","name":"xxx","role":{"org":{"id":"xxx"}}}
     */

    private String token;
    /**
     * id : xxx
     * loginName : xxx
     * name : xxx
     * role : {"org":{"id":"xxx"}}
     */

    private UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public static class UserEntity {
        private String id;
        private String loginName;
        private String name;
        /**
         * org : {"id":"xxx"}
         */

        private RoleEntity role;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RoleEntity getRole() {
            return role;
        }

        public void setRole(RoleEntity role) {
            this.role = role;
        }

        public static class RoleEntity {
            /**
             * id : xxx
             */

            private OrgEntity org;

            public OrgEntity getOrg() {
                return org;
            }

            public void setOrg(OrgEntity org) {
                this.org = org;
            }

            public static class OrgEntity {
                private String id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
