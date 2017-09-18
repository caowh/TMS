package tms.spring.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/9/8.
 */
public class TreeNode extends Object{

    private int cid;
    private String name;
    private int pid;
    private List children = new ArrayList();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TreeNode){
            TreeNode treeNode=(TreeNode)obj;
            if(cid==treeNode.getCid()&&pid==treeNode.getPid()&& name.equals(treeNode.getName())){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return cid+pid+name!=null?name.hashCode():0;
    }
}
