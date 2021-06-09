
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.Box;

public class tool {
	// please note that spaces are ignored in the encryption process an spaces that
	// are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	boolean consumed;
	int level = 50;
	String[] keys = { "iho", "1pi", "1=i", "=ce", "dcs", "u7i", "vjx", "0hd", ",vq", "9o,", "pk=", "p%w", "r0t", "=kf",
			"87z", ",k9", "!qn", "34c", "j6%", "??b", ".9j", "!cq", "isg", "a70", "q9=", "wyq", "6rm", "syt", "%l/",
			"cmu", "cu=", ",c=", "gf4", ",0w", "61!", "76s", "t1!", "7mg", "23%", "du/", "u1?", "ea4", "?g5", "du1",
			"1yd", "=e%", "quo", "f1u", "j7=", "7xj", "o?q", "giu", ",8d", "k4,", "9?0", "qqc", "rsv", "w1d", "n01",
			"up,", "bzw", "y=,", "spu", "bai", "z.w", "et0", "7?y", "2q7", "4jf", "jfl", "w1i", "x.&", "q=z", "k=e",
			".&y", "f6q", "&ew", "ln8", "l84", "=ue", ",u8", ",70", "h0!", "qxd", "ktv", "46:", "vec", "rbm", ",i8",
			"gzr", "9gu", "9ws", "6a/", "zj2", "9gb", "g!7", "r8&", "0wa", "h/6", "aj:" };
	char[][] mixedUps = {
			{ 'w', 'g', '&', 'a', '1', 'j', '%', 'e', '7', 'y', 't', 's', 'u', 'o', 'v', '2', '3', 'l', 'c', '0', ':',
					',', '!', '6', '8', '9', 'q', 'p', '=', 'b', 'n', '.', 'z', 'k', 'r', 'f', '?', '/', '4', 'h', 'm',
					'5', 'i', 'd', 'x' },
			{ '?', '&', '%', 'u', 'p', 'x', 'z', '1', 'a', 'j', '6', '/', 'b', 'k', '2', 'h', ':', '=', '0', 'q', 'o',
					'.', '9', 't', '!', 'w', 'r', 'e', 'c', '3', '8', '4', 'y', 'l', 's', 'f', 'v', ',', 'n', 'g', 'd',
					'm', '5', 'i', '7' },
			{ 'e', 'k', 'p', '?', '/', '9', ':', 'c', 'w', '6', 'd', '1', 'f', 'q', 'o', 'x', '.', 'l', 't', 'h', '=',
					'v', 'z', '8', '7', 'r', 'y', 'u', ',', 'n', 'm', '&', '5', 'i', 'j', '2', '3', '!', 'b', 'g', '%',
					'a', 's', '0', '4' },
			{ '9', 'm', 'k', 't', 'a', 'z', '6', 's', '.', '5', 'i', 'v', 'y', 'w', '&', '0', ',', 'f', 'l', '!', 'b',
					'?', 'n', '/', 'q', 'h', 'j', ':', '3', 'd', 'g', 'o', 'p', '1', 'r', '2', '4', '%', 'x', '8', 'e',
					'7', 'u', '=', 'c' },
			{ 't', 'f', 'g', '.', 'x', 'v', 'l', 'o', '5', 'q', 'b', 'a', 'u', '2', '?', '9', 'm', 'y', 'p', '3', 'c',
					'n', 'z', ':', ',', '1', 'w', 's', '&', 'k', 'r', '6', '4', 'e', '7', '0', '=', '!', '/', 'i', '%',
					'j', 'd', '8', 'h' },
			{ 'f', 'q', '/', '0', '7', 'r', 'c', 'w', 'k', 's', '5', '4', '?', ',', '!', 't', '6', '.', 'o', '%', '3',
					'&', '9', '=', 'p', 'v', 'a', 'h', 'l', 'e', 'z', 'i', '2', 'd', 'm', 'x', 'b', 'n', '8', 'j', 'g',
					'u', 'y', ':', '1' },
			{ 'l', 'n', 'j', 'f', '?', 't', '1', 'g', 'q', '9', '!', '2', 'm', '4', '0', 'b', 'v', 's', 'e', 'o', ',',
					'/', 'a', 'y', '.', 'd', 'i', '=', 'z', '5', '6', ':', 'r', '&', 'k', 'c', '8', 'u', '%', '3', '7',
					'w', 'p', 'x', 'h' },
			{ 'b', 'l', ':', 'g', 't', '/', '9', '2', 'n', '7', 'r', '5', '%', '1', '8', 'k', 'z', 'o', '?', '3', '4',
					'i', ',', 'q', 'e', 'x', 'u', 'c', 'h', '!', 'a', 'p', 'y', '=', '.', 'f', 'v', '6', '0', 'd', 'm',
					'&', 'w', 'j', 's' },
			{ 'q', 'h', 'v', '0', ':', 'k', '1', 'a', 'r', '7', 'x', 'd', '3', 'p', '?', '%', 'e', 'i', 'u', 'o', 'b',
					'=', 's', 'f', 'm', '&', '2', 'j', '8', '/', 'g', '.', 'c', '6', ',', 'n', '!', 'y', '5', 't', 'z',
					'4', 'l', '9', 'w' },
			{ 'v', 'm', 'i', '4', 'e', 'b', 'o', '.', ',', 'f', 'u', '%', '3', '8', 'q', 'h', '5', '!', 'c', '0', 'a',
					'l', '/', '=', 'y', 'j', '1', '9', '?', 'r', '6', ':', 'n', 'p', '2', 's', 'w', 'k', 'd', '7', 'z',
					'&', 'g', 't', 'x' },
			{ 'n', '!', 'l', 'h', '=', 'j', 'd', 'a', 'm', ':', '%', 'f', 'k', 'p', 'c', '1', 's', '7', 'i', '0', 'r',
					'5', '2', '3', '?', 'q', 'g', 'b', 'y', 'e', 'u', '4', '.', '/', 'w', ',', 'v', '9', 't', 'z', 'x',
					'&', '6', 'o', '8' },
			{ '2', 'g', '9', ':', '?', 'x', 's', 'k', 'w', '7', 'n', '5', 'a', 'e', '1', '!', '0', '/', 't', 'f', ',',
					'v', 'y', 'u', 'i', '%', 'q', '.', 'r', 'b', 'o', '8', 'h', '=', 'l', '3', 'j', 'd', '4', 'm', '&',
					'6', 'z', 'p', 'c' },
			{ 'x', 's', '=', 'r', 'd', '3', '&', 'n', 'j', '/', 'u', '2', 'q', 'b', 'y', 'i', 'o', '5', '6', ':', '4',
					'm', 'a', ',', 'f', 'k', '9', '.', 'h', '0', '%', 'z', 'e', 'p', 'v', '7', 'l', '8', '1', '!', 't',
					'?', 'g', 'c', 'w' },
			{ ',', '&', '=', 'a', '0', 'r', 'w', 's', '8', 'o', '1', '7', 'm', 'p', '?', 'e', 'd', 'f', '4', 'n', '9',
					':', '/', '%', 'y', 'b', 't', 'z', 'l', 'x', 'q', 'h', 'v', 'k', '3', 'j', '.', 'g', 'i', '5', 'u',
					'c', '6', '!', '2' },
			{ 'l', 'd', '4', '7', 'h', '/', '5', 'e', 'z', '=', 'c', 't', '6', 'n', 'i', 'y', 's', 'v', '3', 'b', 'q',
					',', '1', 'a', '&', '?', '%', '0', '.', 'o', 'u', 'm', 'f', 'w', '8', 'g', 'j', ':', 'k', '!', '2',
					'x', 'r', 'p', '9' },
			{ '%', 'b', '2', '/', '3', 'l', 'j', '6', 'w', '.', '1', 'z', 's', '4', 'q', 'i', '?', '!', '5', 'e', 'd',
					'=', 'a', '8', ',', 'x', 'p', '0', 't', 'n', ':', 'g', 'r', 'y', 'h', 'f', 'v', '9', '&', 'k', 'u',
					'c', 'o', 'm', '7' },
			{ 'l', 'z', 'g', 'a', 'd', 'o', 'c', 's', '&', '6', '2', 'p', 'v', '%', 'r', 'b', '5', '!', '0', 'x', '1',
					'?', 'm', '8', '3', ':', 'n', ',', '7', 'y', 'i', 'h', '=', 'e', 't', 'u', 'q', '/', 'f', 'k', '4',
					'9', '.', 'w', 'j' },
			{ 'i', 'n', '0', 'l', 't', 'o', 's', '.', 'd', '2', 'r', 'q', 'u', 'f', 'p', '/', '6', 'j', '7', 'w', 'b',
					'k', 'h', '1', 'm', '4', '9', 'x', '%', 'g', '?', '!', '&', 'z', 'e', ':', 'a', 'v', '8', '3', '5',
					',', '=', 'c', 'y' },
			{ 'j', '6', 't', 'a', 's', '9', '4', 'q', '!', '2', 'p', '.', 'r', '?', 'g', 'x', '3', '0', 'f', 'z', 'l',
					'&', 'v', 'm', 'o', 'n', '5', 'w', 'k', 'u', '1', '%', 'y', 'i', ',', ':', '8', '7', 'b', '=', '/',
					'e', 'c', 'd', 'h' },
			{ 'r', '7', '/', 'e', 'l', '6', 'o', '1', 'y', 's', 'c', ':', '.', 't', '?', 'w', '3', 'p', '2', 'v', 'n',
					'd', '8', ',', 'z', 'm', 'k', '%', 'f', 'u', 'i', 'g', 'x', 'q', '!', '9', 'a', 'j', 'h', '=', '&',
					'b', '5', '4', '0' },
			{ 'y', '!', ':', 'b', 'r', 'j', 'a', '0', 'l', '3', '2', '9', '?', '8', '/', 'e', 's', 'd', '&', 'f', '.',
					'1', 'q', 't', 'w', 'u', 'v', ',', '7', '%', '=', 'x', 'c', 'i', 'm', 'o', 'h', '4', 'n', 'g', '6',
					'p', 'z', '5', 'k' },
			{ 'h', 'v', '4', '1', 'r', 'q', 's', '3', 'u', 'o', 'f', 'p', 'n', '!', 'w', '8', 'i', '&', '2', '%', 'c',
					'5', 'y', '?', 'e', 'a', 'd', 'z', 'm', ',', 'x', '6', '/', '9', '.', 'j', '7', 'g', 'k', '=', 'l',
					't', 'b', ':', '0' },
			{ 'u', '4', 'c', '1', 'a', 'm', '9', 'q', 'z', 'h', 'y', '/', '%', 'g', '&', '!', ',', '5', 'd', '8', 'w',
					'v', '6', 'j', 'l', 'e', '=', 'p', 'i', '?', 'k', '2', 's', 't', 'o', ':', '0', 'b', 'x', '3', '7',
					'.', 'f', 'n', 'r' },
			{ 'n', 'o', 'h', 'd', '8', 'z', 'g', 'q', 's', '9', 'x', 'v', ':', '5', 'y', '=', 'c', ',', 'a', 'p', '4',
					'r', '1', 'w', '.', 'u', '0', '%', '!', '3', '?', 'k', 'j', '/', 'm', 'b', 'i', '6', '7', 'l', 'e',
					'f', '2', 't', '&' },
			{ '3', ',', 'n', 'o', 'g', 'r', 'c', 'y', 'v', '5', 't', 'b', '!', 'k', '9', '/', 'j', 'p', '7', '&', 'x',
					'8', 'w', '?', 'a', ':', 'd', 'm', '2', 'l', 'e', 'i', 'f', '%', '.', 'q', '1', 'h', '=', 'z', '4',
					'0', 'u', 's', '6' },
			{ '9', 'p', 'e', '8', 'b', '1', 'w', 'd', 'i', '3', 'j', 'z', '?', 'y', 'l', 'x', 'n', '6', '=', '%', 'f',
					'7', '0', 'a', 'k', 'q', 'm', 'r', '.', ',', ':', 's', 'g', '4', '5', '/', 'h', 'u', 't', '2', '&',
					'v', 'c', '!', 'o' },
			{ 'u', 'q', 'k', '9', 't', '=', '2', 'a', 'y', 'o', 'l', 'p', 'd', ':', '&', '4', ',', 'r', '5', 'h', 'n',
					'w', '0', 'z', 'i', 'm', 'j', 'v', '3', 'f', '7', 'e', 'g', '/', 'c', 'x', '8', 's', '?', '!', '1',
					'6', '.', '%', 'b' },
			{ 'f', '!', '3', 't', 'l', 'w', 's', '5', 'a', 'd', '7', '&', '9', 'q', '0', '=', '?', 'j', '.', 'b', 'i',
					'2', '1', 'z', 'm', 'v', 'k', 'c', 'u', ',', 'o', 'h', 'x', 'y', '8', 'n', 'r', 'e', '/', 'g', 'p',
					':', '4', '6', '%' },
			{ 'n', '5', 'x', 'o', 'd', '8', 'a', '.', '=', 'g', '3', 'e', '6', 'q', 'i', 'w', '0', 'y', 's', 'k', '!',
					'm', 'v', 'j', 'z', ',', 'p', 'f', ':', '2', '9', '4', 't', 'u', '&', '?', 'c', 'l', 'b', '1', 'r',
					'7', 'h', '/', '%' },
			{ 'j', 'k', '.', 'f', '9', '6', 'e', '&', '/', ':', '1', 'l', 'c', '!', 'n', 'b', 'g', 'q', 'r', 's', '0',
					'a', 't', 'i', 'o', 'm', ',', 'u', '5', 'p', 'z', '?', 'd', 'h', 'w', '%', '3', 'v', '7', 'y', '=',
					'x', '2', '4', '8' },
			{ 'n', '&', 'f', 'y', '8', '=', 'q', 's', 'x', '2', 'u', 'r', 'a', '0', 'k', '4', ',', '/', 'c', 'i', '5',
					'o', 'm', 't', 'g', '!', 'h', '1', 'd', 'v', 'e', '?', '.', 'l', 'b', '%', 'p', 'z', '6', ':', '3',
					'9', 'j', '7', 'w' },
			{ '1', 'l', 'p', 'q', '%', 'j', '&', 'd', 'i', 'e', '2', 's', 'k', '.', '6', '/', '9', 't', 'm', '3', 'x',
					'5', ':', 'w', '=', 'v', '?', 'y', 'u', '0', 'z', ',', 'f', 'g', '7', '!', 'n', 'b', 'o', '4', 'h',
					'c', 'r', 'a', '8' },
			{ 'k', 'n', '6', 'g', 'j', '=', 'i', ',', '&', 'm', '0', 'x', 'q', 't', '5', '9', 'd', 'h', 'f', 'r', '2',
					'a', '3', 'w', 's', '!', '1', 'b', 'c', 'v', '4', 'o', '%', '/', ':', '7', 'z', '8', 'l', 'y', '?',
					'p', 'e', '.', 'u' },
			{ 'w', '8', 'm', '.', 'y', 't', 'r', 'u', 'a', 'x', '6', '!', 'q', 'o', 'k', 'b', '9', ':', '/', 'p', '0',
					'n', ',', 'h', '1', 'e', 's', 'c', '=', '?', 'j', '4', 'z', '2', '7', 'i', '%', '&', '3', 'g', '5',
					'd', 'l', 'v', 'f' },
			{ 'j', '1', ',', '0', '!', 'v', 'c', '?', 'r', 'f', '7', '&', 'x', '9', 'y', '4', 't', 'n', 'u', 's', 'z',
					'h', 'w', '.', 'k', 'd', '8', '=', 'm', '2', ':', 'g', 'e', 'l', 'i', '/', '5', 'p', 'a', '6', 'q',
					'b', 'o', '%', '3' },
			{ ',', '0', 'u', '6', '7', 'k', 'd', 'n', 'y', '?', '1', 'i', 'q', 'j', 'e', 'f', 's', 'b', '3', 'h', '8',
					'm', 'x', 'z', '2', '/', '!', 'g', 'o', '4', '.', 'l', '%', 'r', '9', 'v', 'p', ':', '5', '&', 'a',
					'w', 't', 'c', '=' },
			{ 'f', 'w', ':', 'l', '3', '!', 't', 'v', '?', 'o', '4', 'k', 'd', 'c', 'j', '0', '2', '.', 'z', '6', '=',
					'g', 'i', 'e', '&', '9', '1', '/', 'b', 's', 'p', 'y', 'q', '7', 'u', '8', '5', '%', 'a', 'r', 'h',
					'x', ',', 'n', 'm' },
			{ '3', 'l', '?', 'b', 'n', '&', 'v', '=', 'p', 'm', '6', 'h', '/', '2', 'g', 'j', '7', 'q', 'k', ':', '1',
					'c', '8', 't', 'i', 'e', 's', '9', 'a', 'w', 'y', 'd', 'f', '0', 'u', 'r', 'z', '5', ',', '%', 'o',
					'x', '.', '4', '!' },
			{ 'f', 'p', '%', '3', 'c', 'm', 'j', 'b', 'a', '6', '&', 'z', 'u', 's', 'v', 'l', '4', '!', '0', '9', '.',
					'x', ':', '1', 'w', 'q', '2', 'n', '5', '?', '8', '7', 'y', 'e', 'g', ',', '=', 'h', 'o', 't', 'i',
					'/', 'r', 'd', 'k' },
			{ '%', 'y', 'v', 's', 'p', '9', 'q', '0', 'e', 'o', '6', '1', '!', '8', 'm', ',', 'r', '3', 'g', '.', 'h',
					'&', 'u', 'd', 'l', 'c', 'n', ':', '2', 'z', 'x', 'a', '5', '?', '7', 'j', 'k', 'w', '4', '/', 'b',
					't', 'f', '=', 'i' },
			{ 'q', 'z', 'r', '9', 'g', '?', '1', 'k', 's', 'i', '3', ':', '6', 'a', '5', 'v', 'h', 'p', 'f', 'm', 't',
					'%', '&', '8', 'u', 'c', 'o', '!', '4', '=', 'b', 'j', '2', 'n', 'w', '0', '7', 'l', 'd', ',', '.',
					'x', 'e', '/', 'y' },
			{ 'f', 'e', ':', '/', '1', '8', 's', 'h', '2', 'r', 'u', '0', '=', '5', 'y', 't', 'q', '?', 'c', '&', 'd',
					'6', ',', 'l', 'm', '7', 'i', 'g', '3', '9', 'n', 'w', 'b', '%', '4', 'o', 'z', 'v', '.', 'x', '!',
					'p', 'k', 'a', 'j' },
			{ '9', 'y', '=', '3', 'c', ',', '1', '0', 't', 'b', '2', ':', '/', 'w', 'x', 'n', 'h', '&', 'm', '.', 'j',
					's', 'v', 'o', '%', '!', '5', 'a', 'u', 'r', 'p', '?', 'k', 'z', 'i', '6', '4', '8', 'e', 'g', 'f',
					'q', 'l', '7', 'd' },
			{ 'f', '!', ':', 's', ',', '%', 'j', '=', 'i', 'o', '0', '.', 'v', 'p', '1', '7', '&', 'g', '8', 'y', 'c',
					't', 'n', '4', '5', 'z', '6', 'k', 'h', 'd', '2', 'e', 'u', 'b', '9', 'm', 'l', '/', 'q', '3', 'w',
					'a', 'x', '?', 'r' },
			{ 'z', 'o', 'm', '5', 'j', ':', 'a', 'v', 'y', '9', 'c', 'i', 'd', 'u', ',', '8', '4', 'r', '=', 'e', '!',
					'6', 'l', '2', '1', 'n', 'p', '%', 'q', '/', 'k', '7', '0', '?', '3', 'g', 't', 'b', 'w', 'x', 'f',
					's', '&', '.', 'h' },
			{ '%', 'd', 'c', ',', 'y', '.', 'p', 'u', 'm', '&', 'k', 'r', '8', '7', 'z', 'x', 'a', '3', 'l', '6', 't',
					's', 'i', ':', '5', '!', '1', 'q', 'v', '?', 'f', 'j', 'w', 'b', 'h', '4', 'o', '9', '=', 'g', '0',
					'2', 'n', '/', 'e' },
			{ 'm', 'w', '!', '.', 'q', 'f', 'v', ':', 'r', 'g', '%', '3', '/', 'y', '1', 'k', '6', 's', 'a', 'i', 'e',
					'u', 'l', 'c', 'p', 'b', '?', '7', 'h', '4', '2', 'j', 'z', '5', '&', '9', '8', '0', '=', ',', 'x',
					'n', 't', 'd', 'o' },
			{ 'u', '.', '=', 'b', 'a', '%', 'c', 'm', '?', '1', '2', 'f', '4', 'g', 'z', 'j', 's', 'i', 'n', 'h', 'p',
					'9', 'e', 'w', '5', '&', '8', '7', ',', 'o', 'y', '6', 'v', '0', 'r', 'l', 'd', 'q', 'k', 'x', ':',
					'3', '/', '!', 't' },
			{ 'h', 'w', 'u', 'q', ',', '&', '4', '3', '9', '7', '!', '6', '0', '2', '5', 'o', 'a', 'b', 'e', '/', 'm',
					'g', 'l', '.', '8', 'f', 'j', 'i', '1', '=', 'c', 'p', ':', 'r', '?', 'z', 't', 'k', 'x', 'v', 'y',
					'%', 's', 'n', 'd' },
			{ 'h', 'w', '&', 'e', '=', '6', '2', '7', '/', 's', '0', 'u', 'k', 't', 'y', 'b', 'i', 'd', ':', '.', 'l',
					'g', 'o', 'p', 'f', '8', 'z', 'j', 'a', '4', '%', 'n', '5', 'v', 'c', '1', '?', '!', 'r', 'q', 'm',
					'x', '3', '9', ',' },
			{ 's', 'g', ',', 't', 'k', 'o', '/', 'e', '3', 'l', '5', 'h', '&', '%', 'n', 'w', 'f', 'b', 'm', ':', 'j',
					'7', '.', 'a', 'd', 'p', 'q', 'y', 'v', 'x', '?', 'z', '9', '1', '4', 'u', '!', '8', '=', 'c', '2',
					'0', 'r', 'i', '6' },
			{ ',', 'l', 'z', '0', 'o', 'b', 'p', '/', '%', '?', 'h', ':', '.', 'k', 'i', 'a', 'd', 'w', 'v', 't', '4',
					'c', '=', '&', 'n', 's', 'j', 'f', 'y', '5', 'x', '1', 'q', 'g', 'r', '!', '3', 'e', '2', '7', '8',
					'6', '9', 'm', 'u' },
			{ 'j', '/', 'w', '8', '.', 'u', 'b', '0', 'x', 'o', 'z', 'g', 'y', 'l', '9', 'n', 'k', '6', ':', ',', 'q',
					'c', '!', 'f', '4', '7', '?', 'd', 's', '1', 'v', 'p', '&', 'r', '=', 'i', 'a', 'h', '2', 'm', '5',
					'%', 'e', 't', '3' },
			{ '0', 'h', 'y', '/', 'b', 'u', '4', '1', 'l', 'w', '&', 'd', 'x', 'p', 'v', ',', '7', '!', 'c', 'e', '=',
					's', 'r', 'q', 'g', 'o', '2', '?', 't', '.', 'a', '6', 'i', '8', '%', '9', '5', 'j', 'k', ':', '3',
					'n', 'z', 'f', 'm' },
			{ 'l', '1', '5', '&', 'g', 'z', 'p', '4', 'b', 'j', '.', 'a', '?', 'w', '8', '3', ':', '0', 'r', 'v', ',',
					'x', 't', '6', '7', '2', 'f', 'h', '%', 'k', 'q', 'o', 'c', 'i', 'n', 'u', 'y', '9', '/', '=', 'm',
					'd', '!', 'e', 's' },
			{ 'j', 'r', '4', 't', 'u', '=', '7', 'n', '.', '1', '%', '3', 'z', '8', 'l', '&', 'c', 'g', 'k', 's', '0',
					'i', 'b', 'q', 'e', 'y', '6', '5', '9', 'h', '/', 'd', '2', 'p', '?', 'a', 'x', 'w', 'm', 'f', 'v',
					'o', ',', '!', ':' },
			{ '.', 'i', '7', 'z', 'l', 't', ':', 'm', 'p', '&', '1', 'e', '8', '/', 's', '3', 'b', 'd', 'y', 'q', ',',
					'4', '6', '2', '0', 'c', '!', '9', 'r', 'h', '%', 'k', 'x', 'o', 'g', '=', 'j', 'n', 'u', '?', 'w',
					'5', 'f', 'v', 'a' },
			{ '3', '?', 'e', 'q', '2', 'c', '6', 'u', ':', 'n', 'l', 't', '=', '&', '4', 'h', 'v', 'a', '/', 'x', '8',
					'p', 'f', ',', 'd', 's', 'k', 'm', '%', '5', 'z', 'b', 'o', '7', 'r', 'g', '1', 'i', '9', '0', '.',
					'w', 'j', '!', 'y' },
			{ 'o', '7', 'k', ',', 'y', '&', '4', 'g', '0', 'p', 'r', 'n', 'u', '.', 'm', 'f', 'q', 'a', '5', 'e', '=',
					'6', 'x', '1', 'd', '%', 'b', 't', 'w', '!', 'v', '?', '8', 'l', 'c', 'i', 'j', ':', '3', '/', '9',
					's', 'h', 'z', '2' },
			{ '1', 't', 'y', 'i', 'g', 'h', 'u', 'w', 'm', 'o', 'p', '%', 'f', '4', 'c', 'r', '7', '0', 's', 'b', '3',
					'6', 'z', 'l', '/', 'e', '&', ',', 'k', 'a', '=', 'q', 'j', '2', '?', 'n', '5', 'v', 'd', '9', ':',
					'x', '.', '8', '!' },
			{ 'j', '2', '=', 'c', 'u', '/', 'h', 'i', 's', ',', '3', 'e', '%', 'g', 'v', 'y', '1', '?', '7', '6', 'n',
					'l', 'd', ':', 'x', 'a', '9', 'z', '&', 'k', 'q', 't', 'f', 'w', '0', 'r', 'b', 'm', 'o', '!', '8',
					'p', '5', '4', '.' },
			{ 'u', '/', 't', 's', 'i', 'a', 'y', 'c', 'q', '!', 'w', '9', 'f', 'z', '&', 'm', '.', '7', ':', 'h', 'g',
					'0', '6', 'o', 'r', 'b', 'e', 'd', '=', 'l', 'j', 'v', 'x', '4', '2', '5', '?', '8', '1', '3', 'k',
					'%', 'n', 'p', ',' },
			{ '4', 'm', '.', 'c', 'q', 'i', '5', 'x', '1', '6', '2', 'v', 'l', 'r', '=', 'j', 'y', '%', 'd', 'o', ':',
					'a', 'w', 'z', '8', '/', 'u', 'k', ',', 's', '7', 'p', 'b', '0', 't', '?', 'f', 'g', '!', 'h', 'e',
					'n', '3', '&', '9' },
			{ 'z', '/', 't', '2', 'l', '4', '=', 'r', 'q', ':', 'v', '0', '?', 'y', 'g', ',', 'j', '.', 'p', 'e', 'a',
					'x', 'w', 's', 'k', '8', 'f', 'i', 'm', 'o', '3', 'b', 'n', '1', '!', '5', '6', '9', '%', 'c', '7',
					'u', 'd', 'h', '&' },
			{ 'k', '8', 'l', 'y', '3', '/', '2', 'j', '!', ',', '0', 'a', 'h', 'b', 'x', '5', 'm', '4', ':', 'u', 'c',
					'%', 'q', '7', 'z', 'w', '9', 't', 'o', 'n', 'v', '?', 's', 'i', '=', '1', '6', 'r', '.', 'g', 'e',
					'&', 'p', 'f', 'd' },
			{ 'y', '?', '6', 'd', 'f', 'o', ':', '.', 'a', 'r', 'i', 'u', '2', 'q', '&', '8', 'g', '3', '5', '!', 'w',
					'0', '=', 'p', 'n', 'j', '/', 't', 'h', 'e', 'c', 'v', 's', 'x', 'l', 'm', '4', '1', 'z', '%', '7',
					'9', ',', 'k', 'b' },
			{ '=', '6', 's', '7', 'y', '!', 'z', 'p', 'i', '.', 'm', ':', 'e', '&', ',', 'f', 'a', 't', '2', 'r', '9',
					'4', '5', 'l', 'd', '1', 'n', 'u', 'q', '3', 'g', 'v', 'h', 'o', '/', '?', 'k', 'c', '8', '%', 'b',
					'j', '0', 'w', 'x' },
			{ 'a', '%', '2', 'l', 'k', 'm', '6', 'f', '/', 'w', '&', '8', '1', 'p', '3', 'i', 't', '?', 'e', 'c', '!',
					'=', 'q', ':', 'n', 's', 'r', 'o', 'd', 'z', 'j', '0', 'g', '7', '4', 'h', '5', '9', '.', 'y', 'x',
					'b', 'v', ',', 'u' },
			{ 'l', '/', 't', 'k', '0', 'r', 'h', 'c', 'g', 'n', 'z', 'a', '6', 'b', '%', '.', 'j', 'd', 'v', '?', '9',
					'p', '1', 'e', 'q', '=', 'x', 'u', 'w', 'y', ',', '5', 'o', '7', 'f', '&', '2', '3', ':', 's', 'm',
					'8', '4', 'i', '!' },
			{ '=', 'r', 'x', 'v', '!', '6', 'h', 'g', '7', '&', ':', 'b', 'i', 'q', 'j', 'a', '3', '1', '?', 'p', 'n',
					'/', '8', '4', 'k', ',', 'f', 'y', 'o', 'm', 's', '9', 'd', '2', 'c', 'z', '.', 'e', 'u', '5', '0',
					'%', 'l', 't', 'w' },
			{ 'q', 'y', 'g', 'k', '!', 'x', 't', '8', 'b', 'n', 'a', 'l', 'p', '5', '&', 'f', 'h', 'm', '0', '=', '4',
					',', 'r', 'd', 'j', '/', 'e', '7', 'i', 'c', '3', 's', 'u', '.', 'z', '?', '6', '9', ':', '%', 'w',
					'2', '1', 'o', 'v' },
			{ '4', '5', 'u', '/', '&', 'v', 'c', 'q', 'l', 'f', ',', '=', 'k', '1', '0', 'g', 'x', '.', 'm', 't', 'h',
					'7', '?', '6', 'j', 'i', '%', 'p', ':', '2', '8', 'r', '3', '!', 'z', '9', 's', 'a', 'o', 'b', 'w',
					'd', 'y', 'n', 'e' },
			{ 'm', 'i', '.', 'l', 'p', '2', '6', 'd', 'n', '/', 'a', '%', 'y', 'x', '8', 'z', 'e', '0', 'f', 'b', 'r',
					',', '4', '!', '5', 'w', '&', '9', 'h', 'o', ':', '=', '7', '3', 'g', 'j', 'q', 'u', '?', 'v', '1',
					's', 'c', 't', 'k' },
			{ 's', '7', ':', 'g', '.', '2', 'x', '3', 'u', 'l', ',', 'f', '8', 'k', 't', 'b', '6', 'h', 'y', 'a', 'e',
					'm', '=', 'w', '/', '&', '?', '9', 'r', 'p', 'c', 'i', '!', '0', 'j', 'q', '%', 'n', 'v', '1', '5',
					'z', 'o', '4', 'd' },
			{ 's', 'g', '3', '.', 'f', 'c', '?', 'u', 'l', ',', '&', 'v', 'k', 'y', 'p', '0', '6', '8', 'h', 'w', '2',
					't', ':', '7', 'e', 'r', 'i', 'z', 'm', 'q', 'j', 'o', 'b', '1', 'd', 'n', 'x', '!', '/', '4', 'a',
					'5', '9', '%', '=' },
			{ '?', 'q', 's', '9', '4', '3', 'p', 'n', '1', 'z', '!', 'o', 'y', 'd', 'c', 'h', ',', 'g', '8', '.', 'u',
					'r', 'i', '0', 'a', 'x', '2', '6', 'v', 't', '7', 'w', 'l', '5', '/', 'm', 'k', 'f', '&', 'e', '=',
					'%', 'j', ':', 'b' },
			{ 'g', 'l', 'r', ':', '5', 'i', 'a', 'm', '8', '3', '?', 's', 'h', 'b', '=', 'y', ',', '1', '.', 'o', 'u',
					'p', 'd', 'j', 'n', 'e', '0', '2', '!', 'k', '6', 'f', 't', '4', 'c', 'w', 'z', '%', 'x', '&', 'q',
					'v', '9', '7', '/' },
			{ 'r', 'x', 'p', 'f', '8', '?', ',', 'l', '/', '%', 'i', '.', 'h', 'm', '3', 'j', 'k', 'e', '7', 'd', '0',
					'w', 'y', '9', 'u', '!', '2', 't', 's', '6', 'v', 'z', 'c', 'g', 'b', 'a', ':', '1', '&', '4', 'n',
					'q', '5', '=', 'o' },
			{ '9', 'k', 'h', 'm', 'o', 'j', 'r', '7', 's', 'f', 't', '1', 'z', 'e', '8', '0', 'q', '=', ':', 'x', 'g',
					'6', 'v', 'w', 'n', 'c', '4', 'y', 'p', 'u', '.', 'a', 'l', '3', '/', 'd', '5', '!', '%', '?', 'b',
					'i', '&', '2', ',' },
			{ 'f', '4', 'u', ':', '1', '2', 'k', 's', '0', 'z', '3', 'h', 'j', '.', '8', 'x', 'c', 'y', '&', '/', 'n',
					'5', 'w', 'o', 'd', '7', 't', 'g', 'r', ',', 'v', 'b', 'p', '9', 'q', 'a', '?', '!', '%', '6', 'l',
					'i', '=', 'm', 'e' },
			{ 'j', 'q', '5', '9', 'a', '0', '.', 'b', '=', 'z', 'm', 'v', 'e', 'l', '4', '6', 'u', 'h', 'i', 'f', '8',
					'r', '?', '3', 'o', ':', 't', 'g', 'k', 'c', '!', '%', 'd', 'p', 'n', '/', 'y', 's', 'x', '1', 'w',
					'2', '&', '7', ',' },
			{ 'h', 'b', '9', 'c', 'w', 'p', 'u', '4', 'i', 'y', 'm', '1', 'x', ',', 'o', '?', ':', 's', 't', '!', '8',
					'l', 'd', '=', 'z', 'v', 'n', '7', 'k', '6', '&', '0', 'a', '3', 'q', 'j', 'g', '.', '2', '5', 'r',
					'%', 'e', 'f', '/' },
			{ 'v', '?', '1', 'x', '2', '.', '!', '9', 'a', '%', '6', 'k', 'h', '&', 'l', 'y', 'u', 'w', 'm', '0', 'j',
					'g', 'n', '7', ',', 'c', 'r', '8', 'z', '5', 't', 'f', 'e', 'o', 's', '/', '4', ':', '=', 'd', 'i',
					'q', 'p', 'b', '3' },
			{ 'd', 'v', 'w', 't', 'y', 'r', 'f', 'q', 'o', '1', '&', 'u', 'e', '4', 'c', ',', 'g', '5', 'm', '.', '/',
					'8', 'n', '6', 'b', 'i', '=', 'k', '7', '9', 's', ':', '3', 'p', 'l', '?', '2', '!', '%', 'z', 'x',
					'a', 'h', '0', 'j' },
			{ 'w', '1', 'o', '6', 'x', 'p', 'h', 'a', 'v', 'c', 's', '8', 'z', 'y', '2', '9', '5', 'm', 'u', '=', '0',
					'f', '%', 'l', ',', '&', 'd', '7', '/', 'e', 'g', 'r', '.', 'q', '?', '!', '4', 'i', '3', 't', ':',
					'n', 'j', 'b', 'k' },
			{ '1', 'm', '5', 'n', 'v', '8', 'i', 'b', '9', 'w', 'f', '6', 'r', '%', '.', ':', 'e', 'x', 's', 'a', 'q',
					'p', '/', '?', 'g', '=', 'z', 'j', 'd', 'h', 't', '!', 'u', '7', '4', 'l', 'o', 'c', '&', '2', '0',
					',', '3', 'y', 'k' },
			{ '6', 'e', 'r', '8', 'x', 'd', ':', 'c', '=', 't', 'z', 'v', ',', '7', '0', 'l', '1', '9', '%', 'n', '!',
					'h', 'u', 'f', 'a', '4', 'i', '&', '?', 'k', '.', '3', 'j', 'o', '5', '/', 'b', 'q', '2', 'p', 'g',
					'y', 'w', 'm', 's' },
			{ 's', '0', 'c', 'u', ':', '.', '7', 'h', '=', '2', '/', '!', 'p', '%', 'l', '4', 'g', 'z', '?', 'v', '&',
					'y', 'o', 'a', 'k', 'w', 'j', 'x', '8', 'm', '3', ',', 'i', 'd', '6', '5', '9', 'f', 't', 'r', 'e',
					'q', '1', 'n', 'b' },
			{ ':', 'c', '0', '=', 'p', 'v', '9', 'e', 'b', '1', '4', 'i', 'y', '2', '8', 'k', 'u', '6', '?', 'q', 'j',
					'3', '5', 'g', 'm', '/', 'f', '%', 'h', ',', 'a', 'n', '7', 'z', 'w', 'x', '&', 's', '!', '.', 'o',
					'r', 't', 'd', 'l' },
			{ 'm', 'k', '=', 'q', '&', 'c', 't', '4', 'l', 'g', '5', '8', '.', 'r', 'u', '1', '3', ',', '0', 'p', 'd',
					'b', 'x', 'i', 'h', 'o', 's', '7', '/', 'z', 'n', '6', 'f', 'y', 'w', '2', '!', 'a', 'j', 'v', '%',
					'9', '?', ':', 'e' },
			{ ':', '=', 'g', 'x', 'l', '9', '/', 'p', '5', 'u', '4', 'o', 'm', 'v', 'f', '0', '&', 'n', 's', 'y', '7',
					'!', '%', '2', '?', '1', 'w', '6', 'e', '.', 't', 'z', '3', 'c', ',', 'd', 'i', '8', 'a', 'h', 'b',
					'q', 'k', 'r', 'j' },
			{ 'n', '=', ':', 'w', 'i', '2', 's', '?', '%', 't', 'v', 'g', '4', '/', 'd', 'r', '9', 'z', '1', ',', '8',
					'7', '3', 'x', 'p', 'k', '&', 'y', 'b', '!', '0', 'a', 'm', 'l', '6', 'j', 'u', 'q', 'f', 'o', 'h',
					'c', 'e', '.', '5' },
			{ '/', 'v', '7', 'h', '2', 'w', '6', 'd', 'a', '3', 't', 'i', '&', '%', '8', ',', 'r', 'l', 'q', '!', '4',
					'c', 'o', 'b', '9', 's', 'u', 'e', 'g', 'j', 'x', 'm', 'y', '?', ':', 'k', 'n', '=', '0', '.', '1',
					'z', 'p', '5', 'f' },
			{ '0', 'u', '!', '7', '5', 'm', 'y', 'o', 'z', '&', 'l', 'c', 'g', '1', 'p', '/', 'r', 'x', 'e', '4', '9',
					'w', 'f', 's', 'v', '6', '=', '.', ',', 'a', 'k', 'n', 'd', '8', 'q', '%', '?', '3', 't', 'i', ':',
					'2', 'h', 'j', 'b' },
			{ ':', '=', ',', 'l', '1', '7', 'r', '9', 's', 'w', 'q', 'v', 'c', '3', '.', '6', '0', 'p', 'i', 'd', 'y',
					'2', '4', 'x', '?', 'u', '8', '!', 'k', 'b', '/', 'm', 'a', 't', '%', 'z', 'g', 'e', 'o', 'f', '5',
					'j', 'n', '&', 'h' },
			{ '&', 'w', 'h', 'f', '8', ':', 'k', '3', '=', 'o', 'i', '%', '9', 'j', 'a', 'v', '1', 'y', '?', 'n', 'u',
					'r', 'p', 'd', 'b', 'c', 's', 'l', '4', '2', '/', '!', '0', 'e', ',', 'z', 'x', 'g', '7', '.', '5',
					'6', 't', 'q', 'm' },
			{ 'j', ':', 'c', '%', 'k', 'f', '8', '?', '3', 'a', 'b', '6', '&', 'p', '5', '0', '/', 'z', '7', 'h', 'g',
					't', 'r', '4', '.', '!', 'y', '=', 'e', 'u', 'x', 'n', 's', 'i', 'd', 'w', '2', 'm', 'q', '9', '1',
					'v', ',', 'o', 'l' },
			{ 'a', 'c', 'v', 'f', '8', 'g', 'o', '2', '/', '3', '&', '%', '9', 'j', 'p', 'w', 'z', ':', '5', '6', 'b',
					'i', '7', 'y', 'e', 'm', '=', 't', 'h', '1', '!', 'q', 'r', '0', '.', 'x', 'k', 'd', '?', 'n', 's',
					'l', 'u', ',', '4' },
			{ 'p', 'b', 'v', 'e', 'h', '8', '/', '&', '=', 'z', '!', 'j', '4', '?', '.', 'r', 'f', 'd', 'k', 'w', '%',
					'n', 'i', 'y', 'o', 't', 'u', '9', '1', 'a', 'q', 'g', ',', '3', '5', '7', 's', 'l', '6', 'm', 'c',
					'2', '0', 'x', ':' },
			{ 'd', 'v', 'i', 'r', 'a', '9', 'y', '=', '5', 'e', 'k', 'z', '3', '2', '8', 'c', '/', '&', 'f', 'g', '0',
					',', 'l', 'h', '1', '%', 'j', '6', 'p', 'q', 'b', '?', 'x', 's', 'w', 't', 'u', '7', 'o', '.', 'm',
					':', 'n', '!', '4' } };
	int[] randomLevels = { 51, 14, 71, 81, 19, 12, 73, 96 };
	String[] levelKeys = { ":/", "v:", "uc", "ie", "92", "/b", "zs", "o0" };
	char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0', '/', ':', '=' };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tool window = new tool();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tool() {
		initialize();

	}

	// shuffle char array
	char[] shuffle(char[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {
			
			//get random index
			int randomIndexToSwap = rand.nextInt(array.length);
			
			//move chars to new positions
			char temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		return array;
	}

	private void initialize() {
		/* start of frame initialization */
		//No need to pay attention unless you want to change the look of the app
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JRadioButton encrypt = new JRadioButton("Encrypt");
		encrypt.setSelected(true);
		encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (encrypt.isSelected()) {
					deEncrypt.setSelected(false);
				}
			}
		});
		encrypt.setBounds(366, 49, 155, 29);
		frame.getContentPane().add(encrypt);

		deEncrypt.setBounds(250, 49, 114, 29);
		deEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//toggle between the two radio buttons
				if (deEncrypt.isSelected()) {
					encrypt.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(deEncrypt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 85, 365, 369);
		frame.getContentPane().add(scrollPane);

		JTextArea In = new JTextArea();
		scrollPane.setViewportView(In);
		In.setLineWrap(true);
		In.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(409, 85, 405, 369);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(Out);
		Out.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Out.setLineWrap(true);
		Out.setEditable(false);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		int mixUpKeyCount = 100;

		JMenuItem makeNewKeys = new JMenuItem("Make New Keys");

		// Generates new keys to be copied back into script for use
		makeNewKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make mix up keys
				String out = "String[] keys ={";
				for (int i = 0; i < mixUpKeyCount; i++) {
					String keyString = "";
					Random rnd = new Random();
					for (int j = 0; j < 3; j++) {
						keyString = keyString + alphabet[rnd.nextInt(alphabet.length)];
					}
					out += "\"" + keyString + "\"";
					if (i != mixUpKeyCount - 1) {
						out += ",";
					}
				}
				out += "};";

				// make mix ups
				out += "char[][] mixedUps = {";
				char[] temp;
				for (int y = 0; y < mixUpKeyCount; y++) {
					out += "{";
					// shuffles the alphabet
					temp = shuffle(alphabet);

					// prints to console with correct formating in case you would like to use new
					// keys
					for (int i = 0; i < temp.length; i++) {
						out += "'" + temp[i] + "'";
						if (i != temp.length - 1) {
							out += ",";
						}
					}
					out += "}";
					if (y != mixUpKeyCount - 1) {
						out += ",";
					}
				}
				// make random levels
				out += "};";
				out += "int[] randomLevels = {";
				for (int i = 0; i < randomLevels.length; i++) {
					String keyString = "";
					Random rnd = new Random();
					keyString = keyString + Integer.toString(rnd.nextInt(100 - 10) + 10);
					out += keyString;
					if (i != randomLevels.length - 1) {
						out += ",";
					}
				}
				out += "};";
				// make random level keys
				out += "String[] levelKeys ={";
				for (int i = 0; i < randomLevels.length; i++) {
					String keyString = "";
					Random rnd = new Random();
					for (int j = 0; j < 2; j++) {
						keyString = keyString + alphabet[rnd.nextInt(alphabet.length)];
					}
					out += "\"" + keyString + "\"";
					if (i != randomLevels.length - 1) {
						out += ",";
					}
				}
				out += "};";
				copyToClipBoard(out);
			}
		});
		menuBar.add(makeNewKeys);

		Component horizontalStrut = Box.createHorizontalStrut(575);
		menuBar.add(horizontalStrut);
		frame.setVisible(true);
		/* end of frame initialization */

		/* start of decryption/encryption logic */
		In.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				/* pulls text from input and clears output */
				String inString = In.getText().toLowerCase();
				Out.setText("");

				/* decryption logic */
				if (deEncrypt.isSelected()) {
					String temp = inString;
					
					//find out how many times the message was ran through the algorithm using our level keys and the first two characters of our input
					int ecyrtptionLevelIndex = findInArrayString(levelKeys, temp.substring(0, 2));
					temp = temp.substring(2);
					
					//loop till we have a string that has no more keys left and just the message
					for (int i = 0; i < randomLevels[ecyrtptionLevelIndex]; i++) {
						try {
							temp = deEnccrypt(temp);
						} catch (IndexOutOfBoundsException e) {
							encrypt.setSelected(true);
							deEncrypt.setSelected(false);
						}
					}
					// copyToClipBoard(temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(temp);
				} else {
					Random rnd = new Random();

					// select random lvl key
					int levelSelectedIndex = rnd.nextInt(levelKeys.length);

					String temp = inString;
					
					//run our message through the encrypt algorithm for our chosen count
					for (int i = 0; i < randomLevels[levelSelectedIndex]; i++) {
						temp = encrypt(temp);
					}
					// copyToClipBoard(levelKeys[levelSelectedIndex] + temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(levelKeys[levelSelectedIndex] + temp);
				}
			}
		});
	}
	
	//copy text to clipboard
	void copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	
	//encryption algorithm
	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();

		// chooses mix up index
		int mixUpIndex = rndRandom.nextInt(keys.length);
		
		//add our selected key to the beginning of our string
		String outString = keys[mixUpIndex];

		// chooses the key that will be used for this level of encryption
		char[] keyToUse = mixedUps[mixUpIndex];

		// encrypts the string with the chosen key each iteration 
		for (int i = 0; i < inString.toCharArray().length; i++) {
			
			//if the current char is a space, skip it 
			if (inString.toCharArray()[i] != ' ') {
				outString += keyToUse[findInArray(alphabet, inString.toCharArray()[i])];
			} else {
				outString = outString + " ";
			}
		}
		return outString;
	}

	String deEnccrypt(String in) {
		String out = "";
		// finds which key was used for this encryption
		char[] keyUsed = mixedUps[findInArrayString(keys, in.substring(0, 3))];
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {
				out = out + alphabet[findInArray(keyUsed, in.toCharArray()[i])];
			} else {
				out = out + " ";
			}
		}
		return out;
	}

	int findInArrayString(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	int findInArray(char[] array, char value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
