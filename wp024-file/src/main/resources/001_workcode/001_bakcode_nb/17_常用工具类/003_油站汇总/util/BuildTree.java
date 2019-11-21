package com.catt.common.util;

import java.util.*;

/**
 * 构建树型数据
 */
public class BuildTree {

	/**
	 * 根据列表数据构造树型数据，原列表数据结构会改变
	 *
	 * @param list      列表数据
	 * @param parentCol 父标识变量名
	 * @param selfCol   标识变量名
	 * @return
	 */
	public static List<Map<String, Object>> createTree(List<Map> list, String parentCol, String selfCol) {
		return createTree(list, parentCol, selfCol, false);
	}

	/**
	 * 根据列表数据构造树型数据
	 *
	 * @param list      列表数据
	 * @param parentCol 父标识变量名
	 * @param selfCol   标识变量名
	 * @param isCopy    是否克隆列表数据进行构造，是：不改变原列表；否：改变原列表
	 * @return
	 */
	public static List<Map<String, Object>> createTree(List<Map> list, String parentCol, String selfCol, boolean isCopy) {
		return createTree(list, parentCol, selfCol, "children", isCopy);
	}

	/**
	 * 根据列表数据构造树型数据
	 *
	 * @param list        列表数据
	 * @param parentCol   父标识变量名
	 * @param selfCol     标识变量名
	 * @param childrenCol 子树变量名
	 * @param isCopy      是否克隆列表数据进行构造，是：不改变原列表；否：改变原列表
	 * @return 树型数据
	 */
	public static List<Map<String, Object>> createTree(List<Map> list, String parentCol, String selfCol, String childrenCol, boolean isCopy) {
		list = isCopy ? cloneList(list) : list;//将数据复制一份，以免对原数据结构改变

		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//完全循环一次,是子节点的最终被替换为null
		for (Iterator<Map> it = list.iterator(); it.hasNext(); ) {
			Map mp = it.next();
			if (mp == null) {
				continue;//为空,说明已经被设为一个子节点了
			}
			setChilds(list, parentCol, selfCol, childrenCol, mp);
		}

		for (Iterator<Map> it = list.iterator(); it.hasNext(); ) {
			Map mp = it.next();
			if (mp != null) {//不为空则说明是一个根节点
				tree.add(mp);
			}
		}

		return tree;
	}

	/**
	 * 根据当前节点找子节点
	 *
	 * @param list        列表数据
	 * @param parentCol   父标识变量名
	 * @param selfCol     标识变量名
	 * @param childrenCol 子树变量名
	 * @param mp          当前节点
	 */
	private static void setChilds(List<Map> list, String parentCol, String selfCol, String childrenCol, Map mp) {
		String sid = com.catt.common.util.collections.MapUtil.getString(mp, selfCol); //当前节点主键
		for (int i = 0; i < list.size(); i++) {
			Map temp = list.get(i);
			if (temp == null) {
				continue;//为空,说明已经被设为一个子节点了
			}

			String pid = com.catt.common.util.collections.MapUtil.getString(temp, parentCol);//循环中的节点父ID
			//当循环节点的父ID=当前结点的主键时,说明循环节点就是一个子节点
			if (pid == sid || (sid != null && sid.equals(pid))) {
				//递归,寻找叶节点
				setChilds(list, parentCol, selfCol, childrenCol, temp);
				//获取子节点集合
				List<Map<String, Object>> arr = (List<Map<String, Object>>) mp.get(childrenCol);
				if (arr == null) {//如果还没有子节点集合,则新建一个
					arr = new ArrayList<Map<String, Object>>();
					mp.put(childrenCol, arr);//得新赋给当前结点
				}
				arr.add(temp);//节点集合添加此子节点
				list.set(i, null);//被设置为子节点后,进行清空标记,不能删除
			}
		}
	}

	/**
	 * 克隆列表，以免对原来list数据结构改变
	 *
	 * @param list
	 * @return
	 */
	private static List<Map> cloneList(List<Map> list) {
		List<Map> cloneList = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			cloneList.add(new HashMap(list.get(i)));
		}
		return cloneList;
	}

}
