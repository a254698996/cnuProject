package test.ms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class T_3 {

	public static void main(String[] args) {

		Set<Node> nodeSet = new HashSet<>();
		Node aNode = new Node("A", 1);
		Node bNode = new Node("B", 2);
		Node cNode = new Node("C", 2);

		nodeSet.add(aNode);
		nodeSet.add(bNode);
		nodeSet.add(cNode);

		Set<Path> pathSet = new HashSet<Path>();
		pathSet.add(new Path(aNode, bNode));
		pathSet.add(new Path(bNode, cNode));
		pathSet.add(new Path(aNode, cNode));

		boolean existCyc = existCyc(aNode, pathSet);
		if (existCyc) {
			System.out.println("存在环");
			return;
		}

		List<LinkedList<Path>> pathList = new ArrayList<>();

		findPath(aNode, nodeSet, pathSet, pathList);

		int pathIndex = 1;
		System.out.println("pathList size " + pathList.size());
		Iterator<LinkedList<Path>> iterator2 = pathList.iterator();
		while (iterator2.hasNext()) {
			LinkedList<Path> next = iterator2.next();
			Set<Node> linkedListNodeSet = new HashSet<>();
			Iterator<Path> iterator3 = next.iterator();
			while (iterator3.hasNext()) {
				Path next2 = iterator3.next();
				linkedListNodeSet.add(next2.getBeginNode());
				linkedListNodeSet.add(next2.getEndNode());
				System.out.print(next2 + ",");
			}
			int total = 0;
			Iterator<Node> iterator = linkedListNodeSet.iterator();
			while (iterator.hasNext()) {
				Node next2 = iterator.next();
				total += next2.getNodeValue();
			}
			System.out.println("节点 权重之和 " + total + " ,第 " + pathIndex++ + "  条路 ");
		}

	}

	private static boolean existCyc(Node beginNode, Set<Path> pathSet) {
		Iterator<Path> iterator = pathSet.iterator();
		while (iterator.hasNext()) {
			Path next = iterator.next();
			Node endNode = next.getEndNode();
			if (endNode.equals(beginNode)) {
				return true;
			}
		}
		return false;
	}

	private static void findPath(Node beginNode, Set<Node> nodeSet, Set<Path> pathSet,
			List<LinkedList<Path>> pathList) {
		if (beginNode == null || nodeSet.isEmpty() || nodeSet == null || pathSet.isEmpty() || pathSet == null) {
			return;
		}

		Set<Path> currentPathSet = new HashSet<>();
		Iterator<Path> iterator = pathSet.iterator();
		while (iterator.hasNext()) {
			Path currPath = iterator.next();
			Node currNode = currPath.getBeginNode();
			if (currNode.equals(beginNode)) {
				currentPathSet.add(currPath);
				iterator.remove();
			}
		}
		for (Path currPath : currentPathSet) {
			LinkedList<Path> currlinkedList = null;
			for (LinkedList<Path> linkedList : pathList) {
				if (!linkedList.isEmpty()) {
					Iterator<Path> iterator2 = linkedList.iterator();
					while (iterator2.hasNext()) {
						Path next = iterator2.next();
						Node endNode = next.getEndNode();
						if (currPath.getBeginNode().equals(endNode)) {
							currlinkedList = linkedList;
							break;
						}
					}
				}
			}
			if (currlinkedList == null) {
				LinkedList<Path> temp = new LinkedList<>();
				pathList.add(temp);
				currlinkedList = temp;
			}

			currlinkedList.add(currPath);

			Node nextBeginNode = currPath.getEndNode();
			findPath(nextBeginNode, nodeSet, pathSet, pathList);
		}
	}
}

class Node {
	private String nodeName;
	private int nodeValue;

	public Node(String nodeName, int nodeValue) {
		super();
		this.nodeName = nodeName;
		this.nodeValue = nodeValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeName == null) ? 0 : nodeName.hashCode());
		result = prime * result + nodeValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeName == null) {
			if (other.nodeName != null)
				return false;
		} else if (!nodeName.equals(other.nodeName))
			return false;
		if (nodeValue != other.nodeValue)
			return false;
		return true;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}

	@Override
	public String toString() {
		return "Node [nodeName=" + nodeName + ", nodeValue=" + nodeValue + "]";
	}

}

class Path {
	private Node beginNode;
	private Node endNode;

	public Path(Node beginNode, Node endNode) {
		super();
		this.beginNode = beginNode;
		this.endNode = endNode;
	}

	public Node getBeginNode() {
		return beginNode;
	}

	public void setBeginNode(Node beginNode) {
		this.beginNode = beginNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginNode == null) ? 0 : beginNode.hashCode());
		result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (beginNode == null) {
			if (other.beginNode != null)
				return false;
		} else if (!beginNode.equals(other.beginNode))
			return false;
		if (endNode == null) {
			if (other.endNode != null)
				return false;
		} else if (!endNode.equals(other.endNode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Path [beginNode=" + beginNode + ", endNode=" + endNode + "]";
	}

}
