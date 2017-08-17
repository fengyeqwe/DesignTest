package com.example.wangfeng.test.MyViews.MoreList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/8/14.
 * email 1040441325@qq.com
 */

public class Node {
    private int id;
    private int pId;
    private String name;
    private int level;
    private boolean isExpand;
    private int icon;
    private List<Node> children =new ArrayList<>();
    private Node parent;

    public Node() {
    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return 获取level
     */
    public int getLevel() {
        return parent==null?0:parent.level+1;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!isExpand){
            for (Node node:children){
                node.setExpand(isExpand);
            }
        }
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     *
     * @return 是否为根节点
     */
    public boolean isRoot(){
        return parent == null;
    }

    /**
     *
     * @return 父节点是否展开
     */
    public boolean isParentExpan(){
        if (parent ==null)return false;
        return parent.isExpand();
    }

    /**
     *
     * @return 是否为叶子节点
     */
    public boolean isLeaf(){
        return children.size()==0;
    }

}
