package com.example.footballquiz.authorization;

public class Users {
        String userId,username,profile;

        public Users(String userId, String username, String profile) {
                this.userId = userId;
                this.username = username;
                this.profile = profile;
        }

        public Users() {
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getProfile() {
                return profile;
        }

        public void setProfile(String profile) {
                this.profile = profile;
        }
}
