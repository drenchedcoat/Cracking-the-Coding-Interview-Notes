/**

	Hash Tables:
		Maps key to values with a hash code
	1. Compute the key's hash code
	2. Map the hash code to index in the array.
	3. At index, there is a linked list of keys and values. 
	   Store, search, delete key and value in this index.
*/

/**

	ArrayList & Resizable Arrays:
		Lists that are resizable.
		Some languages have array/list classses that are automatically resizable.

*/

ArrayList<String> merge(String[] words, String [] more) {
	ArrayList<String> sentence =  new ArrayList<String>();
	for (String w : words) sentence.add(w);
	for (String w : word) sentence.add(w);
	return sentence;
}

//StringBuilder: imagine you're concatenating a list of strings as below:

String joinWords(String[] words){
	String sentence = "";
	for (String w : words) {
		sentence = sentence + w;
	}
	return sentence;
}

//Runtime = O(xn^2)
/** on each concatenation a new copy of the string is created. so...
	iteration 1: copies x characters
	iteration 2: copies 2x characters
	iteration 3: copies 3x characters
	...
	iteration n: copies nx characters
runtime: O(nx^2)
VERY HIGH, stringBuilder can help avoid this problem:
*/

String joinWords(String[] words) {
	StringBuilder sentence = new StringBuilder();
	for (String w : words) {
		sentence.append(w);
	}
	return sentence.toString();
}

//--------------------------------------------

//Implement an algorithm to determine if a string has all unique characters.
//What if you cannot use additional data structures?

boolean isUnique(String str){
	//assuming the ASCII alphabet contains 128 characters
	//if more than that = there is guaranteed a duplicate character
	//auto reject string and return false
	if (str.length() > 128) return false;

	//O(n) where n = length of string
	//create a boolean list where we will store bool val=true at index if found through iteration
	//if empty at list[val], set as true
	//if already exists aka if true, duplicate is found and function returns false
	boolean[] char_set = new boolean[128];
	for (int i = 0; i < str.length(); i++){
		int val = str.charAt(i);
		if (char_set[val]) return false;
		char_set[val] = true;
	}
	return true;
}
//Time complexity = O(n) where n=length of string
//space complexity = O(1)

//reduced space usage by a factor of 8using bit vector
//assume str includes only letters a through z lowercase

boolean isUnique(String str){
	int checker = 0;
	for (int i = 0; i < str.length(); i++){
		int val = str.charAt(i) - 'a';
		if ((checker & (1 << val)) > 0) return false;
		checker |= (1 << val);
	}
	return true;
}

//--------------------

//Given two strings, write a method checking if one is the permutation of the other.
//eg: "dog" = "god"
//eg: 'dog ' != "god"

//Method 1: create two arrays
//at index for the characters, +1 when found through iteration,
//In the end, compare lists count for each character
//return if not identical
boolean permutation(String left, String right) {
	//case if strings have different sizes
	if left.length() != right.length() {
		return false;
	}

	//assuming ASCII of 128 different characters
	//letters is a counter array, stores # of the char found
	int[] letters = new int[128]; 

	char[] left_arr = left.toCharArray(); //toCharArray: returns left string into an array of chars
	for (char c : left_arr) {
		letters[c]++;
	}

	//iterates through rightside word
	//decreases counter list letter per char found
	//returns false if counter is not 0
	for (int i = 0; i < right.length(); i++) {
		int c = (int) right.charAt(i);
		letters[c]--;
		if (letters[c] < 0) { //auto return false if there is more of said char in rightside word
			return false;
		}
	}
	return true;
}

//---------------------

/**
	Write a method that replaces all spacesin a string with %20.
	Eg: input: "John Wick 666"
	    output: "John%20Wick%20666"
	Assume there is sufficient space at the end of the string 
	to hold the additional characters that will be replaced.
*/

/**
	Approach:
		First iteration:
			Find # of spaces in the string.
			x3 this number.
			This will be the amount of space needed to add the %20 characters.
		Second iteration:
			If non space, copy as is.
			If space, add '%20'
	(All of this is done using character arrays, not Strings
	 because strings are immutable in Java)
*/

void replaceSpaces(char[] str, int trueLength) {
	int spaceCount = 0, index, i = 0;
	for (i = 0; i < trueLength; i++) {
		if (str[i] == ' ') {
			spaceCount++;
		}
	}

	//set index value for accurate selection of character index through iteration
	index = trueLength + spaceCount * 2;

	if (trueLength < str.length) {
		str[trueLength] = '\0'; //end array
	}

	//backwards iteration
	for (i = trueLength - 1; i >= 0; i--) {
		if (str[i] == ' ') {
			str[index - 1] = '0';
			str[index - 2] = '2';
			str[index - 3] = '%';
			index = index - 3;
		} else {
			str[index - 1] = str[i];
			index--;
		}
	}
}

//---------------------

/**
	Create a method that determines if a string is a palindrome permutation.
	Assume nonalphabetical characters are disregarded in the determination.
	Eg: 
		Input: "Tact Coa"
		Output: true
		palindrome permutations: "taco cat", "atco cta", "ctao atc", etc
*/









