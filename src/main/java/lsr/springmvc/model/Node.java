package lsr.springmvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsr on 2017/2/8.
 */
public abstract class Node<T> {

    String id;
    int level;
    String parentId;
    ArrayList<T> children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public  T getChildAt(int childIndex){
        return this.children.get(childIndex);
    }

    public int getChildCount(){
        if(this.children == null){
            return 0;
        }
        return this.children.size();
    }

    public  boolean isLeaf(){
        if(children == null){
            return false;
        }
        return this.children.size()==0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<T> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<T> children) {
        this.children = children;
    }

    public  List<T> children(){
        return this.children;
    }
}
