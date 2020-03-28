package com.august.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归生成菜单
 */
public class TreeUtil {
    public static List<TreeItem> getTreeList(List<TreeItem> entityList) {
        List<TreeItem> resultList = new ArrayList<>();
        //获取顶层元素集合
        String parentCode;
        for (TreeItem entity : entityList) {
            parentCode = entity.getParendId();
            //顶层元素的parentCode==null或者为0或者为空
            if (parentCode == null || "0".equals(parentCode) || "".equals(parentCode)) {
                resultList.add(entity);
            }
        }
        //获取每个顶层元素的子数据集合
        for (TreeItem entity : resultList) {
            entity.setChildrenList(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    private static List<TreeItem> getSubList(String id, List<TreeItem> entityList) {
        List<TreeItem> childList = new ArrayList<>();
        String parentId;
        //子集的直接子对象
        for (TreeItem entity : entityList) {
            parentId = entity.getParendId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        //子集的间接子对象
        for (TreeItem entity : childList) {
            entity.setChildrenList(getSubList(entity.getId(), entityList));
        }
        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    public static void main(String[] args) {
        TreeItem item1 = new TreeItem("1", "0", "顶级管理1");
        TreeItem item2 = new TreeItem("2", "0", "顶级管理2");
        TreeItem item3 = new TreeItem("3", "1", "顶级管理3");
        TreeItem item4 = new TreeItem("4", "2", "顶级管理4");
        TreeItem item5 = new TreeItem("5", "3", "顶级管理5");
        TreeItem item6 = new TreeItem("6", "4", "顶级管理6");
        TreeItem item7 = new TreeItem("7", "5", "顶级管理7");
        TreeItem item8 = new TreeItem("8", "6", "顶级管理8");


        List<TreeItem> resultList = new ArrayList<>();
        resultList.add(item1);
        resultList.add(item2);
        resultList.add(item3);
        resultList.add(item4);
        resultList.add(item5);
        resultList.add(item6);
        resultList.add(item7);
        resultList.add(item8);

        List<TreeItem> treeList = getTreeList(resultList);
        System.out.println("treeList: "+treeList.toString());
    }
}


