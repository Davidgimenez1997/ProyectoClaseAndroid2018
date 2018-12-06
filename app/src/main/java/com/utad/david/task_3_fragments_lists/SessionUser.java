package com.utad.david.task_3_fragments_lists;

import com.utad.david.task_3_fragments_lists.Model.User;

public class SessionUser {

    private static SessionUser instance = new SessionUser();

    public User user;

    private SessionUser() {
        user = new User();
    }

    public static SessionUser getInstance() {
        if (instance == null){
            synchronized (SessionUser.class){
                if (instance == null) {
                    instance = new SessionUser();
                }
            }
        }
        return instance;
    }
}
