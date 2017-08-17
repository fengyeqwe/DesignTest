package com.example.wangfeng.test.MyViews.MoreList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/8/14.
 * email 1040441325@qq.com
 */

public class TreeHelper {
    public static <T>List<Node> getSortedNodes(List<T> datas, int defaultExpandlevel){
        List<Node> result = new ArrayList<>();
        List<Node> nodes =convetData2Node(datas);
        return result;
    }

    private static <T> List<Node> convetData2Node(List<T> datas) {
        List<Node> nodes = new ArrayList<>();
      /*  for(T t:datas){
            int id = -1;
            int pId = -1;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields)
            {
                if (f.getAnnotation(TreeNodeId.class) != null)
                {
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null)
                {
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null)
                {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (id != -1 && pId != -1 && label != null)
                {
                    break;
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }*/
        return null;
    }
}
