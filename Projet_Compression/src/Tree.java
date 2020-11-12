
public class Tree implements Comparable<Tree>{
	
	private int frequency;
	private Character character;
	private Tree leftChild; 
	private Tree rightChild;
	
	public Tree(int frequence, Character c) {
		this.frequency = frequence;
		this.character = c;
		this.rightChild = new Tree();
		this.leftChild = new Tree();
	}


	public Tree(int frequency, Character c, Tree leftChild, Tree rightChild) {
		this.frequency = frequency;
		this.character = c;
		this.rightChild = rightChild;
		this.leftChild = leftChild;
	}
	
	public Tree() {
		this.frequency = 0;
		this.character = null;
		this.leftChild = null;
		this.leftChild = null;
	}

	@Override
	public int compareTo(Tree a2) {
		
		int diffFrequency = this.frequency - a2.frequency;
		
		if(diffFrequency != 0) {
			return diffFrequency;
		}
		return this.character.compareTo(a2.character);
	}
	
	public String toString() {
		return "Character:" + character + "Frequence: " + frequency +"\n";
	}

	public int getFrequency() {
		return this.frequency;
	}
	

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Tree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Tree leftChild) {
		this.leftChild = leftChild;
	}

	public Tree getRightChild() {
		return rightChild;
	}

	public void setRightChild(Tree rightChild) {
		this.rightChild = rightChild;
	}
	
}
