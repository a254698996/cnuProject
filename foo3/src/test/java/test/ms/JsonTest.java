package test.ms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		String jsonStr = "{ \"a\": 1, \"b\": { \"c\": 2, \"d\": [3,4] } }";
		Map<String, String> jsonToMap = jsonToMap(null, jsonStr);
		System.out.println("--------------");
		printMap(jsonToMap);

		String text = "key1=value1;key2=value2\nkeyA=valueA\n";
		Map<String, String>[] load = load(text);
		for (Map<String, String> map : load) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				System.out.println(next);
			}
		}
		String sore = sore(load);
		System.out.println(sore);

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

	private static Map<String, String> jsonToMap(String keyStr, String jsonStr) {
		if (jsonStr == null || jsonStr.trim().length() == 0) {
			return null;
		}
		Map<String, String> jsonToMap = new HashMap<String, String>();
		JSONObject json = new JSONObject(jsonStr);
		Set<String> keySet = json.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			Object object = json.get(next);
			try {
				JSONObject jsonObject = new JSONObject(object.toString());
				System.out.println("key  " + next + " jsonObject  " + jsonObject + "   object " + object);
				jsonToMap.putAll(jsonToMap(next, jsonObject.toString()));
				continue;
			} catch (Exception e) {
				// e.printStackTrace();
			}
			jsonToMap.put(keyStr == null ? next : keyStr + "." + next, object.toString());
		}
		return jsonToMap;
	}

	private static void printMap(Map jsonToMap) {
		Set<Entry<String, Object>> entrySet = jsonToMap.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			System.out.println(next);
		}
	}

	private static String sore(Map<String, String>[] strArray) {
		if (strArray == null || strArray.length == 0) {
			return null;
		}
		StringBuilder sbl = new StringBuilder();
		for (Map<String, String> map : strArray) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			StringBuilder sbl2 = new StringBuilder();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String key = next.getKey();
				String value = next.getValue();
				sbl2.append(key).append("=").append(value).append(";");
			}
			sbl.append(sbl2.subSequence(0, sbl2.length() - 1)).append("\\n");
		}
		return sbl.substring(0, sbl.length() - 2);
	}

	private static Map[] load(String text) {
		if (text == null || text.length() == 0) {
			return null;
		}
		int index = 0;
		String[] split = text.split("\\n");
		Map[] map = new Map[split.length];
		for (String string : split) {
			String[] split2 = string.split(";");
			Map<String, String> hashMap = new HashMap<String, String>();
			for (String string2 : split2) {
				String[] split3 = string2.split("=");
				hashMap.put(split3[0], split3[1]);
			}
			map[index] = hashMap;
			index++;
		}
		return map;
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

// class Node {
// private String nodeName;
// private int nodeValue;
//
// public Node(String nodeName, int nodeValue) {
// super();
// this.nodeName = nodeName;
// this.nodeValue = nodeValue;
// }
//
// @Override
// public int hashCode() {
// final int prime = 31;
// int result = 1;
// result = prime * result + ((nodeName == null) ? 0 : nodeName.hashCode());
// result = prime * result + nodeValue;
// return result;
// }
//
// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null)
// return false;
// if (getClass() != obj.getClass())
// return false;
// Node other = (Node) obj;
// if (nodeName == null) {
// if (other.nodeName != null)
// return false;
// } else if (!nodeName.equals(other.nodeName))
// return false;
// if (nodeValue != other.nodeValue)
// return false;
// return true;
// }
//
// public String getNodeName() {
// return nodeName;
// }
//
// public void setNodeName(String nodeName) {
// this.nodeName = nodeName;
// }
//
// public int getNodeValue() {
// return nodeValue;
// }
//
// public void setNodeValue(int nodeValue) {
// this.nodeValue = nodeValue;
// }
//
// @Override
// public String toString() {
// return "Node [nodeName=" + nodeName + ", nodeValue=" + nodeValue + "]";
// }
//
// }

// class Path {
// private Node beginNode;
// private Node endNode;
//
// public Path(Node beginNode, Node endNode) {
// super();
// this.beginNode = beginNode;
// this.endNode = endNode;
// }
//
// public Node getBeginNode() {
// return beginNode;
// }
//
// public void setBeginNode(Node beginNode) {
// this.beginNode = beginNode;
// }
//
// public Node getEndNode() {
// return endNode;
// }
//
// public void setEndNode(Node endNode) {
// this.endNode = endNode;
// }
//
// @Override
// public int hashCode() {
// final int prime = 31;
// int result = 1;
// result = prime * result + ((beginNode == null) ? 0 : beginNode.hashCode());
// result = prime * result + ((endNode == null) ? 0 : endNode.hashCode());
// return result;
// }
//
// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null)
// return false;
// if (getClass() != obj.getClass())
// return false;
// Path other = (Path) obj;
// if (beginNode == null) {
// if (other.beginNode != null)
// return false;
// } else if (!beginNode.equals(other.beginNode))
// return false;
// if (endNode == null) {
// if (other.endNode != null)
// return false;
// } else if (!endNode.equals(other.endNode))
// return false;
// return true;
// }
//
// @Override
// public String toString() {
// return "Path [beginNode=" + beginNode + ", endNode=" + endNode + "]";
// }
//
// }
