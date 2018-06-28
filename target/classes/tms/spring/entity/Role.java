package tms.spring.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
    private Long id;
    /**角色名称*/
    private String name;
    /**角色类型,描述*/
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
    }
    public String toString(){
    	return JSON.toJSONString(this);
    }
}