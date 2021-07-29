import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class cmd_tool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String choice ="";
		String lastOutput="";
		Scanner scn = new Scanner(System.in);
		while(choice!="q") {
			System.out.print("Choose an option([n- copy new set of keys to clipboard] [c- copy last output] [e- encrypt a message] [d- de encrypt a message] [q- quit the app]): ");
			choice = scn.nextLine();
			switch(choice) {
				case "n":
				{
					copyToClipBoard(Encryption.reprogram());
					System.out.println("New keys coppied\ne");
					break;
				}
				case "c":
				{
					if(lastOutput!="") {
						copyToClipBoard(lastOutput);
						System.out.println("Message coppied\n");
					}else {
						System.out.println("No output to copy\n");
					}
					break;
				}
				case "e":
				{
					System.out.print("Enter a message to encrypt: ");
					String message = scn.nextLine();
					lastOutput=Encryption.encrypt(message.toLowerCase());
					System.out.println("\nMessage:\n"+lastOutput+"\n");
					break;
				}
				case "d":
				{
					try {
						System.out.print("Enter a message to de-encrypt: ");
						String message = scn.nextLine();
						lastOutput=Encryption.de_encrypt(message);
						System.out.println("\nMessage:\n\""+lastOutput+"\"\n");
					}catch(Exception e) {
						System.out.println("\n*De-encryption failed, encrypted message is possibly invalid*d\n");
					}
					
					break;
				}
				case "q":
					System.exit(0);
					break;
				default:
					System.out.println("That is not a valid choice. Try again.");
					break;
			}
			
		}
	}
	static void copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

}
