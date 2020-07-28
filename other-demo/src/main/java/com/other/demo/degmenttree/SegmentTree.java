package com.other.demo.degmenttree;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 线段树
 * https://mp.weixin.qq.com/s/wUR_-8azybeM_kVwkGHkuA
 *
 * @author guoyj
 * @date 2020/7/15 18:04
 */
@Slf4j
public class SegmentTree {
	/**
	 * n是元素个数
	 */
	static int n = 10;
	static int[] array = {0, 1, 5, 1, 3, 4, 2, 0, 9, 0, 9};
	/**
	 * array是原序列(第一个0是占array[0]位的)
	 */
	static Node[] tree = new Node[4 * n];

	static {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new Node(0, 0, 0, 0);
		}
	}

	/**
	 * 更新节点的值
	 *
	 * @param num 当前节点序号
	 */
	private void updateNode(int num) {
		tree[num].sum = tree[num * 2].sum + tree[num * 2 + 1].sum;
	}

	/**
	 * 建造线段树
	 *
	 * @param l   区间左边界
	 * @param r   区间右边界
	 * @param num 区间元素和
	 */
	private void build(int l, int r, int num) {
		tree[num].left = l;
		tree[num].right = r;
		if (l == r) {
			// left = r说明到达叶子节点
			tree[num].sum = array[l];
			return;
		}
		int mid = (l + r) / 2;
		// 递归左儿子
		build(l, mid, num * 2);
		// 递归右儿子
		build(mid + 1, r, num * 2 + 1);
		updateNode(num);
	}

	/**
	 * 单点修改,把元素i修改为值value
	 *
	 * @param i
	 * @param value
	 * @param num
	 */
	private void modify(int i, int value, int num) {
		if (tree[num].left == tree[num].right) {
			// 到达叶子节点
			tree[num].sum = value;
			return;
		}
		int mid = (tree[num].left + tree[num].right) / 2;
		if (i <= mid) {
			// 递归左儿子
			modify(i, value, num * 2);
		} else {
			// 递归右儿子
			modify(i, value, num * 2 + 1);
		}
		updateNode(num);
	}

	private void modifySegment(int l, int r, int value, int num) {
		// [l,r]每一项都增加value
		if (tree[num].left == l && tree[num].right == r) {
			// 找到当前区间
			// r-l+1是区间元素个数
			tree[num].sum += (r - l + 1) * value;
			tree[num].lazy += value;
			return;
		}
		int mid = (tree[num].left + tree[num].right) / 2;
		if (r <= mid) {
			// 在左区间
			modifySegment(l, r, value, num * 2);
		} else if (l > mid) {
			// 在右区间
			modifySegment(l, r, value, num * 2 + 1);
		} else { // 分成2块
			modifySegment(l, mid, value, num * 2);
			modifySegment(mid + 1, r, value, num * 2 + 1);
		}
		updateNode(num);
	}

	private void pushDown(int num) {
		if (tree[num].left == tree[num].right) {
			// 叶节点不用下传标记
			// 清空当前标记
			tree[num].lazy = 0;
			return;
		}
		// 下传左儿子的懒惰标记
		tree[num * 2].lazy += tree[num].lazy;
		// 下传右儿子的懒惰标记
		tree[num * 2 + 1].lazy += tree[num].lazy;
		// 更新左儿子的值
		tree[num * 2].sum += (tree[num * 2].right - tree[num * 2].left + 1) * tree[num].lazy;
		// 更新右儿子的值
		tree[num * 2 + 1].sum += (tree[num * 2 + 1].right - tree[num * 2 + 1].left + 1) * tree[num].lazy;
		// 清空当前节点的懒惰标记
		tree[num].lazy = 0;
	}

	private int query(int l, int r, int num) {
		if (tree[num].lazy != 0) {
			// 下传懒惰标记
			pushDown(num);
		}
		if (tree[num].left == l && tree[num].right == r) {
			// 找到当前区间
			return tree[num].sum;
		}
		int mid = (tree[num].left + tree[num].right) / 2;
		if (r <= mid) {
			// 在左区间
			return query(l, r, num * 2);
		}
		if (l > mid) {
			// 在右区间
			return query(l, r, num * 2 + 1);
		}
		// 分成2块
		return query(l, mid, num * 2) + query(mid + 1, r, num * 2 + 1);
	}

	public static void main(String[] args) {
		SegmentTree segmentTree = new SegmentTree();
		segmentTree.build(1, 10, 1);
		for (Node node : tree) {
			log.error(JSON.toJSONString(node));
		}
	}
}

@Data
class Node {
	/**
	 * 区间左边界
	 */
	int left;
	/**
	 * 区间右边界
	 */
	int right;
	/**
	 * 区间元素和
	 */
	int sum;
	/**
	 * 懒惰标志
	 */
	int lazy;

	public Node(int left, int right, int sum, int lazy) {
		this.left = left;
		this.right = right;
		this.sum = sum;
		this.lazy = lazy;
	}

	@Override
	public String toString() {
		return "{" +
			"left=" + left +
			", right=" + right +
			", sum=" + sum +
			", lazy=" + lazy +
			'}';
	}
}
