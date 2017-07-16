package tms.spring.entity;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;

public class RolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long rid;
    private Long pid;

    public RolePermission() {
	}
    public RolePermission(Long rid, Long pid) {
    	this.rid = rid;
    	this.pid = pid;
    }
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String toString(){
    	return JSON.toJSONString(this);
    }
}