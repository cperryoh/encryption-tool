import java.util.Random;

/*
 * Quick run-down on how this works(READ IF CONFUSED): So we have a few arrays
 * of keys and arrays that correlate to them 
 * keys->mixups 
 * levelkeys->random
 * levels
 * 
 * Mixups: array of ciphers or mixups of the array, charsToUse (Cipher replaces one character for another) 
 * Keys: Array of strings, each 3 letters long. Each string correlates to a mixup
 * 
 * randomLevels: Array of random numbers, this gives the encryptor multiple
 * options of how many times to encrypt our string 
 * levelkeys: an array of two character long strings that correlate to a number in random levels
 * 
 * CharsToUse: Valid characters that can be encrypted and be used in decryption
 * 
 * Methodology: All encryption does is it first decides how many times to
 * encrypt our string(using randomLevels). After that is takes the string the
 * user provided, runs it through the encrypt() function a certain number of
 * times, and store the output, it takes that output and does that process over
 * and over again, constantly building upon it's self. Once we have a final
 * string that has been ran a number of times(decided by a random index
 * selection from randomLevels) through encrypt(), we tack on our key that
 * correlates to our encryption level to the beginning and spit out a final
 * output. 
 * encrypt(): in this method we chose a cipher from mixups, run our
 * input through it, then tack on our key for our chosen mixup to the beginning
 * and return.
 * 
 * For decryption, it is a bit more complicated. First, we find out how many
 * times we ran our original string through encrypt(). After we figure that out,
 * we remove the first two characters(the key that told us how many times our
 * string was encrypted over). Then we loop our string through decrypt() that
 * many times. 
 * decrypt(): this method takes note of the first three characters(a
 * mixup key) and connects it to a mix up. Then starting from the fourth
 * character(as to not include the key). We reverse the cipher from our mixup we
 * found using the key to CharsToUse and build a new string that we then output
 * for either further decryption or final output to the user.
 * 
 * 
 * This is powerful because it uses 100 ciphers to make a countless number of
 * others. This is because each output from encrypt(), including the key, is ran
 * back through, re-encrypting the whole string using a new(or possibly the
 * same) cipher compared to the previous one.
 */
public class Encryption {
	public CipherData data;
	public Encryption() {
		data =new CipherData();
	}

	// generates a new set of keys to be copied back into the code
	
	public static char[] shuffle(char[] array) {
		Random rand = new Random();
		char[] out = array;
		for(int i=0;i<out.length;i++){
			int randIndex=rand.nextInt(array.length);
			char temp =out[i];
			out[i]=out[randIndex];
			out[randIndex]=temp;
		}
		return out;
	}

	public String addCipher(String message) {
		String inString = message;
		Random rndRandom = new Random();

		// chooses mix up index
		int mixUpIndex = rndRandom.nextInt(data.keys.length);

		// add our selected key to the beginning of our string
		String outString = data.keys[mixUpIndex];

		// chooses the mix up that will be used for cipher
		char[] keyToUse = data.mixedUps[mixUpIndex];
		for(int i = 0; i < message.length();i++){
			char curChar = message.charAt(i);
			if(curChar!=' '){
				outString+=keyToUse[findInArray(data.charsToUse,curChar)];
			}else{
				outString+=' ';
			}
		}
		return outString;
	}

	public String removeCipher(String in) {
		String out = "";
		// finds which key was used for this encryption using the keys array and the
		// first three chars of our input
		char[] keyUsed = data.mixedUps[findInArray(data.keys, in.substring(0, 3))];

		// We start off at 3 because we don't want to include the first 3 chars to our
		// output, so we start af the 4th character in our string
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {

				// go from cipher -> actual characters
				out += data.charsToUse[findInArray(keyUsed, in.toCharArray()[i])];
			} else {

				// if there is a space, add it to our output
				out += " ";
			}
		}
		return out;
	}

	public String de_encrypt(String message) throws IndexOutOfBoundsException {
		String temp = message;

		// find out how many times the message was ran through the algorithm using our
		// level keys and the first two characters of our input
		int ecyrtptionLevelIndex = findInArray(data.levelKeys, temp.substring(0, 2));
		temp = temp.substring(2);

		// loop till we have a string that has no more keys left and just the message
		for (int i = 0; i < data.randomLevels[ecyrtptionLevelIndex]; i++) {
			try {
				temp = removeCipher(temp);
			} catch (IndexOutOfBoundsException e) {
				throw e;
			}
		}
		// copyToClipBoard(temp);
		// clipboard.setContents(stringSelection, null);
		return temp;
	}

	public String encrypt(String message) {
		Random rnd = new Random();

		// select random lvl key(how many times encrypt() will be ran on our temp
		// string)
		int levelSelectedIndex = rnd.nextInt(data.levelKeys.length);

		// value to store our out put after encryption to be dumped back into encrypt()
		String temp = message;

		// run our message through the encrypt algorithm for our chosen count
		for (int i = 0; i < data.randomLevels[levelSelectedIndex]; i++) {
			temp = addCipher(temp);
		}
		// copyToClipBoard(levelKeys[levelSelectedIndex] + temp);
		// clipboard.setContents(stringSelection, null);

		// add our chosen level key to our encrypted string
		// level key is a signature that tells the dencryptor how many times we ran
		// encrypt() on our string
		return data.levelKeys[levelSelectedIndex]+temp;
	}

	public int findInArray(String[] array, String value) {

		// loop through each index looking for a string that matches the value varibe
		for (int i = 0; i < array.length; i++) {
			// if the item in the current index matches our input, return i(our current
			// index)
			if (array[i].equals(value)) {
				return i;
			}
		}
		// if we can't find it, return -1
		return -1;
	}

	// the same thing as the string version, but with chars, Ex: 'H'
	public int findInArray(char[] array, char value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
