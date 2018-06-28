package tms.spring.entity;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;


public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long uid;
    private Long rid;

    public UserRole(Long uid, Long rid) {
    	this.uid = uid;
    	this.rid = rid;
	}
    public UserRole() {
    }
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    public String toString(){
    	return JSON.toJSONString(this);
    }
}