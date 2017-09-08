package tms.spring.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 2017/9/8.
 */
public class TreeNodeTest {
    @Test
    public void equals() throws Exception {
        TreeNode treeNode1=new TreeNode();
        treeNode1.setPid(1);
        treeNode1.setCid(2);
        treeNode1.setName("1");
        TreeNode treeNode2=new TreeNode();
        treeNode2.setPid(1);
        treeNode2.setCid(2);
        treeNode2.setName("1");
        assertEquals(true,treeNode1.equals(treeNode2));
    }

}