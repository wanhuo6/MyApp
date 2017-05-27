package com.ahuo.personapp.entity.response;

import com.ahuo.entity.entity.User;
import com.ahuo.tools.network.retrofit.BaseResponseEntity;

import java.util.List;


/**
 * Created on 17-5-27
 *
 * @author liuhuijie
 */

public class GetUserResponse extends BaseResponseEntity {

    private List<User> users;

    public GetUserResponse() {
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
