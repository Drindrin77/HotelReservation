import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Huffman {
	private Map<Character, String> codeCharacter;
	private Map<Character, Integer> frequences;
	private Tree finalTree;
	private SortedSet<Tree> sortedTrees;
	
	public Huffman(String sentence) {
		this.codeCharacter = new HashMap<Character, String>();
		this.finalTree = new Tree();
		this.sortedTrees = new TreeSet<Tree>();
		this.constructFrequences(sentence);
	}
	
	public void start() {
		
		frequences.entrySet().forEach(entry->{
			sortedTrees.add(new Tree(entry.getValue(), entry.getKey()));
		});
		
		while(sortedTrees.size()!=1) {
			Tree leftChild = removeFirstTree();
			Tree rightChild = removeFirstTree();
			int frequency = leftChild.getFrequency() + rightChild.getFrequency();
			Tree parent = new Tree(frequency,rightChild.getCharacter(),leftChild,rightChild);
			sortedTrees.add(parent);
		}
		
		this.finalTree = sortedTrees.first();
		
		//DFS
	}
	
	private void dfs(Tree tree, String code) {
		
	}
	
	
	private void constructFrequences(String sentence) {
		this.frequences = new HashMap<Character, Integer>();
		for(int i = 0 ; i < sentence.length(); i++) {
			addFrequency(sentence.charAt(i));
		}
	}
	
	private Tree removeFirstTree() {
		Tree t = sortedTrees.first();
		sortedTrees.remove(t);
		return t;
	}
	
	private void addFrequency(Character c) {
		Integer i = 1;
		
		if(frequences.containsKey(c))
			i = frequences.get(c)+1;
		
		frequences.put(c, i);
	}
}
