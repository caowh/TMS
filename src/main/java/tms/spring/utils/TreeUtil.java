package tms.spring.utils;

/**
 * Created by user on 2017/9/8.
 */
import tms.spring.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree解析工具类
 * @author Administrator
 * @version 1.0
 */
public class TreeUtil {

    private List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

    public TreeUtil(List<TreeNode> list){
        treeNodeList = list;
    }

    /**
     *
     * @param nodeId
     * @return
     */
    public TreeNode getNodeById(int nodeId){
        TreeNode treeNode = new TreeNode();
        for (TreeNode item : treeNodeList) {
            if (item.getCid() == nodeId) {
                treeNode = item;
                break;
            }
        }
        return treeNode;
    }

    /**
     *
     * @param nodeId
     * @return
     */
    public List<TreeNode> getChildrenNodeById(int nodeId){
        List<TreeNode> childrenTreeNode = new ArrayList<TreeNode>();
        for (TreeNode item : treeNodeList) {
            if(item.getPid() == nodeId){
                childrenTreeNode.add(item);
            }
        }
        return childrenTreeNode;
    }

    /**
     * 递归生成Tree结构数据
     * @param rootId
     * @return
     */
    public TreeNode generateTreeNode(int rootId){
        TreeNode root = this.getNodeById(rootId);
        List<TreeNode> childrenTreeNode = this.getChildrenNodeById(rootId);
        for (TreeNode item : childrenTreeNode) {
            TreeNode node = this.generateTreeNode(item.getCid());
            root.getChildren().add(node);
        }
        return root;
    }


    public void getDeepChildNode(List<String> list,int rootId){
        List<TreeNode> childrenTreeNode = this.getChildrenNodeById(rootId);
        for (TreeNode item : childrenTreeNode) {
            if(!list.contains(item.getCid())){
                list.add(String.valueOf(item.getCid()));
            }
            this.getDeepChildNode(list,item.getCid());
        }
    }
}

