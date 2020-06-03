
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
	String[] keys = { "&7k", "&jh", "1vq", "phl", "m:t", "%r1", "ni!", "6&i", "/ta", ":z8", "l!?", ":hm", "7lp", "b2d",
			"w%m", "ps!", "ca3", "na!", "&=:", "4pt", ".j0", "8,?", "hdx", "&mz", "rn?", "6,6", "yv3", "e5g", "clx",
			"1!r", "8zb", "zs2", "ito", "57f", "1?w", "vj,", "7c,", "/io", "v%q", "w!r", "ucu", "40c", "y3t", "q55",
			"xnv", "kww", "34y", "dwq", "yf0", "&:8", "3s4", "4tp", "%!e", "gkk", "e!1", "s&i", ":5g", "2iy", "fz3",
			"0f7", "5,x", "?6b", "7c3", ",qv", "81t", "7v1", ".vr", "d3g", "k/4", "fxy", "/0?", "q8,", "v5t", "w,3",
			"7:d", "757", "jbh", "s/7", "7yf", "qfc", "qh2", "?1y", "zp2", "l4!", "lw8", "ei?", "psw", "bqq", "v4=",
			":m9", "73!", "j2t", "/1:", "lyh", ":14", "e1e", "nsv", "my6", "%,2", "8m," };
	char[][] mixedUps = {
			{ '4', 'w', '1', '&', 'h', '%', '0', 'i', 'z', 'a', 'q', 't', 'c', 'o', '?', 'v', 'y', 'j', '9', '.', ',',
					'd', '=', 'm', '!', '5', 's', '7', '8', 'x', 'n', 'u', ':', 'e', 'g', 'f', 'b', '/', 'p', '3', '6',
					'l', '2', 'r', 'k' },
			{ 'w', 'h', 'z', '0', 'n', 'o', 'i', 'k', 'y', 'b', '4', '&', 'r', 'm', 'p', 's', '2', '3', ',', '1', 'l',
					'!', '%', 'g', ':', '8', '/', 'e', 'x', '=', '6', 'a', 'u', 'd', 'f', '9', '7', '5', '.', 'c', 'v',
					't', '?', 'j', 'q' },
			{ 'o', 'b', '4', '0', 'u', '6', 'd', '/', 'v', '.', 'g', 's', '2', '8', 'w', 'h', 'f', '%', 'y', '?', 'm',
					'9', '7', ':', 'r', 'a', 'q', '1', 't', '!', '&', 'e', 'z', 'c', 'l', 'n', 'i', 'j', '3', 'p', 'k',
					'=', 'x', ',', '5' },
			{ 'q', '2', 'z', 'v', 'e', ':', '5', '6', 'd', 'u', '9', 'y', '&', '0', 'g', 'f', '3', '7', '?', '%', '!',
					'w', 'r', '1', 'j', '=', '/', 't', '.', 'p', 'x', ',', 'k', 'm', 'l', 'a', 'b', '4', 's', 'c', '8',
					'o', 'h', 'n', 'i' },
			{ '1', '0', 'g', 'k', 'r', 'w', 'z', '.', 'd', 'p', 'y', '4', 'x', 'f', 'v', 'l', 'a', 'o', 'u', ':', ',',
					'c', '2', '3', '7', '=', 't', '9', 'm', '?', 'b', 'j', 'e', 'n', '!', '6', 'q', '&', 'h', '5', '%',
					'8', '/', 'i', 's' },
			{ ',', '3', 'w', 'v', 't', '&', 'h', 'g', 'z', 'e', '5', 'r', 'y', '?', '!', 'd', '9', 'n', 'f', 'm', '4',
					':', 'a', '0', '/', '8', 'o', '.', '6', 'p', '%', '7', 'j', 'l', 'b', 'x', '2', 's', '=', 'q', 'c',
					'u', '1', 'k', 'i' },
			{ '9', 'l', 'r', ',', '7', 'o', 'm', '?', '.', 'b', 'n', '4', 'v', 'p', '8', 'c', 'd', '%', '6', '1', 's',
					'g', 'w', 'a', 'k', '5', '/', ':', 'i', 'e', '=', 'j', 't', '3', 'y', '&', 'u', 'f', '0', '!', 'z',
					'x', 'q', 'h', '2' },
			{ 'x', 'n', 'o', 'm', 'c', ',', 'z', 'y', 'g', 'b', 'k', 'j', 'w', 'r', 'u', '/', 's', 'p', 't', 'l', 'v',
					'2', 'i', 'e', '1', 'd', '0', '8', '7', '&', '?', 'q', 'h', '=', ':', '%', '!', '.', '3', 'a', '5',
					'9', '6', '4', 'f' },
			{ 'r', 'o', 'a', 'c', 'i', '/', '?', '&', 'b', 'x', 'e', 'k', '6', 'p', ':', 'l', '7', 'v', 'n', '1', 'w',
					'%', 'd', 'm', '!', ',', 'u', '9', '8', '2', 'g', '4', '3', 'z', '.', '5', 'f', '0', 'y', 'q', 'h',
					'=', 't', 's', 'j' },
			{ 'w', 'f', '&', 'l', 'b', 'j', '0', 'e', 'n', 'k', 'a', 'o', 't', 'd', '%', 'm', 'c', 'p', '?', '1', 'y',
					's', '2', 'z', '9', 'r', '6', '3', '7', '.', 'x', ':', '4', 'h', ',', '!', '/', 'v', '8', 'u', 'q',
					'g', '5', '=', 'i' },
			{ '8', 'x', '=', '!', '.', '0', 'd', 'a', 'q', 'h', '9', '/', '5', 'v', 'r', 'k', ',', 'n', 'b', 'j', '2',
					'w', 'l', '%', '4', ':', '?', 's', 'i', 'p', 't', 'f', 'g', 'u', '1', 'm', 'z', '&', 'e', '7', '3',
					'o', '6', 'y', 'c' },
			{ 'l', '!', '%', 'p', 'v', '5', ':', 'q', 'o', 'z', 'b', 'w', '9', 'x', '=', 's', 'c', 'k', 'j', 'i', 'd',
					'e', '/', '?', 'u', '1', '0', 'y', 't', 'g', '4', '3', '6', 'a', '2', '&', '7', 'n', 'f', '.', '8',
					'r', ',', 'h', 'm' },
			{ '9', 'u', 'f', '8', 's', 'w', 'k', 'l', 'g', '3', '=', 'o', ':', 'z', 'c', 'e', 'i', 'j', '0', 't', '?',
					'r', 'b', 'h', '7', 'a', '6', 'm', '&', 'v', '4', '!', 'n', '.', 'y', 'd', '2', ',', '5', '%', 'x',
					'p', '/', 'q', '1' },
			{ 't', '=', 'w', 'b', 'd', 'r', 'u', '%', '?', 'c', 's', 'e', 'h', 'x', '!', 'o', '4', 'z', 'v', '.', '&',
					'm', '3', '6', ':', 'j', 'n', 'k', '9', 'f', '1', '0', 'l', '7', '2', '8', 'g', '5', 'p', 'a', 'y',
					'i', 'q', ',', '/' },
			{ 'o', 'e', '3', 't', '1', 's', '8', 'd', 'r', 'q', '?', 'p', '9', '!', '4', 'g', 'b', 'u', 'j', 'x', '0',
					'=', '2', 'k', 'l', '5', '%', '.', ':', 'h', '/', '6', 'z', '&', 'i', 'n', 'y', 'a', 'v', 'c', 'f',
					'm', ',', '7', 'w' },
			{ 'i', '!', '=', '/', '7', 'd', '1', 'p', 'v', 't', 'u', '0', '9', '4', '&', 'y', ',', '%', 'f', 'h', 'b',
					'?', 'm', '5', 'g', '3', 'a', 'l', 'e', 'k', 's', 'j', 'o', '2', 'q', 'w', '.', '6', 'r', 'x', '8',
					'n', 'z', ':', 'c' },
			{ 'y', 'g', '0', 'q', 'i', '/', 'k', '2', 'o', 'l', 'a', '3', ':', '!', ',', 't', '%', '6', 'h', 'm', '&',
					'=', 'p', '4', 'j', '?', 'x', '1', '.', 'z', '9', 'n', 'u', 'f', '8', 'c', 'd', 'w', 'v', '7', 's',
					'5', 'r', 'e', 'b' },
			{ 's', 'y', 'b', '/', '2', '1', 'p', '7', '&', 'c', 'e', '=', 'u', 'j', '.', ',', '3', 'd', '0', 'o', 'z',
					':', 'h', '4', 'l', 'a', '5', 'n', 't', 'g', 'f', 'm', '8', 'v', '!', 'i', 'w', 'q', 'x', '%', 'r',
					'6', '?', '9', 'k' },
			{ '?', '&', 'd', 'g', '5', '1', 'o', 'i', 'n', 'y', '.', 'e', '3', 'm', 'c', 'l', 'z', 'x', 'h', 'j', '=',
					'u', '4', 'q', 'p', '7', '6', ',', '!', 'r', 't', 's', ':', 'b', 'a', 'v', 'k', '9', 'w', '/', 'f',
					'8', '0', '%', '2' },
			{ 'i', 'b', 'h', 'd', '.', ',', '5', 's', '0', '2', '4', 'n', 'a', '/', 'o', 'x', '6', '8', '!', 'j', 't',
					'w', '%', 'p', 'k', '9', '3', '?', 'v', '7', 'm', 'l', 'q', 'e', 'y', '1', 'r', 'u', '=', 'c', ':',
					'f', '&', 'z', 'g' },
			{ 's', '&', 'e', 't', ',', 'a', '6', '4', 'i', 'n', '.', '!', 'o', 'k', 'p', 'j', '0', 'd', '3', 'l', 'b',
					'q', 'r', 'm', '%', '?', 'v', 'y', 'u', 'x', '8', ':', '2', '/', 'h', 'c', 'f', '9', '5', 'g', '=',
					'7', 'z', '1', 'w' },
			{ 'x', 'n', 'd', 'z', 'q', '.', 'e', 'y', 'v', 'h', '&', ',', 'i', '!', '/', 's', '8', '9', 'p', 'g', 'a',
					'7', '3', ':', 't', 'r', 'l', '0', 'u', '?', 'w', 'c', 'j', '6', 'k', 'f', '4', 'o', 'm', '1', '=',
					'5', '2', '%', 'b' },
			{ '%', '9', '&', 'd', 'v', 'x', 'z', 'g', '0', 'n', 'y', '/', 'o', 's', 'r', '.', 'c', '7', 'h', 'p', 't',
					'1', 'f', '5', '2', '=', '8', 'i', 'q', '3', '?', 'k', ',', 'u', '!', 'm', 'w', 'b', 'e', 'j', ':',
					'l', '6', '4', 'a' },
			{ 'p', '5', 'c', '=', '.', 'k', 'z', 'm', ':', 'r', 'u', 'o', '4', 'l', '/', '0', 'n', 'g', 'f', 'e', '1',
					'd', '?', 's', '%', '9', 't', '2', 'i', ',', '3', 'w', 'j', '&', '7', 'q', '6', '!', 'v', '8', 'x',
					'h', 'b', 'a', 'y' },
			{ 'b', 'x', '?', '%', '.', '8', '3', 'o', '/', 'z', '7', 'g', 's', '!', 'q', 'w', 'k', 'i', '1', '4', '&',
					'l', ':', 'n', ',', 'f', 'h', 't', 'e', 'd', 'u', '5', '=', '9', 'c', 'p', 'r', 'j', '2', '6', 'a',
					'v', '0', 'm', 'y' },
			{ 'z', '.', '!', '&', 'h', 'y', '9', '5', 'j', 'r', '%', 'd', 'q', ',', '1', 'b', 'f', 'x', '8', 'u', 'o',
					'c', 's', 'a', 'l', 'v', 't', '/', '4', '0', 'e', '=', 'i', '6', '2', '3', ':', 'p', '7', 'w', 'n',
					'k', '?', 'm', 'g' },
			{ '?', 'l', '%', 'y', 'f', 'x', 'a', '5', 'e', 'r', '9', ':', '/', '4', 'u', 'h', '&', 'b', 'n', '!', 'o',
					'w', '=', 'v', '.', 'q', 'd', ',', 'i', 'p', 'z', 't', 's', '7', '0', 'k', 'g', '6', '1', 'j', '8',
					'3', 'm', '2', 'c' },
			{ ':', '4', 'r', '?', '6', 'o', 'v', '9', 'h', 'g', '8', 'b', 'j', 'n', '=', 'u', 'e', 'y', 's', 'd', ',',
					'i', 'a', 'f', 'z', '!', 'q', '&', '.', 'm', 'w', '0', 't', '1', '7', '%', '5', 'p', 'c', '3', '2',
					'x', 'l', '/', 'k' },
			{ 'a', '3', 'e', '7', ',', 'u', '0', '9', 'z', '/', 'n', 'o', '!', 'c', 'p', 'f', 't', 'i', 'm', '?', '%',
					'j', 'd', 's', '4', 'h', '5', '.', 'b', 'x', 'q', 'r', '=', '2', 'l', 'w', 'v', 'y', 'k', '&', ':',
					'1', 'g', '8', '6' },
			{ '?', '4', 'l', '/', '9', '1', 'j', '.', ':', 'u', '%', 'k', '7', '&', 'y', '3', 'g', 'd', '5', '=', '8',
					'z', '6', ',', 'o', 'q', 'n', 'x', 'm', '0', 'a', 'w', '!', 'f', 'p', 'i', 't', 'c', 'h', 'v', '2',
					'b', 'r', 'e', 's' },
			{ 'j', '6', 'u', '1', '2', 'k', 'm', '0', 'o', '.', '9', 'p', '?', 'd', '/', 'g', '!', 'z', ',', '5', 'c',
					'3', 'l', 'f', ':', '=', 'e', 'i', 'a', '&', 'q', 'w', '8', '7', 'x', 'v', 'b', '%', 'h', 't', 'n',
					'4', 's', 'r', 'y' },
			{ 't', 'i', '/', 'e', 'w', '6', '7', '9', '?', 'z', '8', 'm', 'v', 'c', 'n', '=', '!', '.', 'r', ':', 'u',
					'k', '3', '5', '%', 'q', '2', 'j', 'y', '0', '4', '&', 'p', 'b', 'h', 'o', 'x', 'f', 's', 'l', 'd',
					'g', 'a', ',', '1' },
			{ '3', '9', 'r', 'n', 'i', '/', 'j', '=', 'a', 'g', 'c', 'o', 'e', '5', '?', 'l', '1', 'p', 'w', 'y', '6',
					'2', 'd', '%', '0', '4', 'f', 'q', 'z', ':', '7', 'u', '8', 'b', 's', 'h', '!', 't', 'x', ',', '.',
					'k', 'v', 'm', '&' },
			{ ':', 'w', 's', '2', '5', 'f', ',', 'i', '6', '1', 'r', '=', 'v', 'b', '7', 'm', '%', 'q', 'g', 't', 'd',
					'k', 'u', '0', '3', '!', 'c', 'o', '8', 'e', 'h', 'n', '&', 'y', 'a', 'z', '?', '9', '4', '/', 'l',
					'j', 'x', 'p', '.' },
			{ '5', 'e', 'm', 's', 'i', '1', 'u', '2', 'r', 'g', 'n', ',', 'w', 'v', 'q', 'a', 'z', 'o', 'y', 'p', 'k',
					't', '/', 'b', '4', 'h', '0', 'l', '?', '3', ':', '8', '%', 'j', '!', '9', 'f', 'd', '&', 'c', '.',
					'x', '=', '7', '6' },
			{ ',', '?', 'v', 'e', '9', 'z', 'f', 'q', 'x', 'm', 'p', 'h', 'i', '%', 'k', 'd', 'y', 'n', '8', '!', ':',
					'7', 's', 'j', 'w', '3', 'o', 'b', 't', '2', 'u', '1', 'c', 'l', '6', 'a', '&', 'r', '4', '.', '0',
					'g', '/', '=', '5' },
			{ 'y', 'w', 'f', '5', ',', '!', '7', 'p', 'o', 'e', 'i', 'z', 'x', '%', '=', 'd', 'r', '3', '4', 't', 'k',
					'?', 's', '6', '/', '2', 'm', '.', 'l', '8', 'n', '&', 'c', 'q', '1', 'b', 'g', ':', 'u', 'a', 'v',
					'0', 'h', 'j', '9' },
			{ ':', '6', '/', '%', 'h', 't', 'm', 'y', 'u', 'w', 'c', 'r', 'l', '7', 'k', '1', 'o', '3', '9', '?', '2',
					'p', 'q', 'v', '=', 'e', 'i', 'j', 'z', ',', '5', 'x', '8', 'g', 'n', '0', '4', 'a', 'd', '!', '&',
					's', 'f', 'b', '.' },
			{ 'k', 'o', '7', 'p', 's', 'f', '&', '=', '.', '9', 'x', 'l', 'z', 'h', '?', 'u', '3', '%', '6', 'j', 'm',
					'1', '5', 'n', '2', 'd', 'w', 'q', '4', ':', ',', 'c', 'r', '0', 'g', 'a', 'e', '!', 'b', 't', 'v',
					'/', '8', 'i', 'y' },
			{ '!', '4', 'a', 'u', '9', '5', 'c', 'k', '%', 'j', 'b', 'n', '?', 'd', 'm', 'y', '3', '.', 's', 'x', '6',
					'7', 't', '/', 'f', '1', 'r', 'g', ',', '&', '2', 'w', '=', '0', 'e', 'i', 'l', 'h', 'z', ':', 'o',
					'q', 'p', '8', 'v' },
			{ 's', ':', '4', 'v', 'd', '?', 'b', 'q', '3', 'r', 'n', '8', ',', 't', '9', '6', 'j', '7', 'l', '0', '1',
					'p', 'g', 'c', '5', 'z', '=', 'y', 'e', 'u', '&', 'k', '.', 'm', 'o', 'x', 'i', '2', 'f', '!', '%',
					'/', 'w', 'a', 'h' },
			{ 'k', '3', 'y', 'q', 'l', 'c', 'p', 'f', '5', '7', 'm', 'z', 'g', '2', 'b', 'h', 'w', '0', '%', 'j', '9',
					'=', ':', ',', 'r', 'e', 'x', 'u', '!', '.', '?', 'd', 'n', 'i', 't', '4', '8', '6', '1', '&', 'o',
					'v', 'a', 's', '/' },
			{ '5', '=', 'q', 'k', '%', 'b', 'v', 'l', '.', 'r', 'd', '4', 'm', 'p', '/', 'f', '6', '2', '&', 'o', '!',
					'7', 'y', 'i', 'w', '9', '?', 'a', 'z', '3', 'j', 's', ',', 'e', 'h', 'u', 'c', 'x', 't', 'n', 'g',
					'8', ':', '1', '0' },
			{ 'q', 'd', '!', 'z', '9', '/', 'a', ':', 'h', 'v', '8', '3', 'l', 'c', '%', '=', 't', 'o', 'n', 'f', 'b',
					'0', '?', ',', '4', 'e', 'u', '6', '2', 'x', 'i', '5', '7', 'k', '&', 'y', 'r', '1', 'j', 'g', 'w',
					'p', '.', 'm', 's' },
			{ '1', '?', 'q', '9', 'u', '/', 'w', 'e', 'd', '2', 'l', '5', '!', 'j', '3', 'z', ',', 'o', 'h', '6', '&',
					'b', '7', 'n', '0', '%', 'a', '4', 'c', ':', 'v', '8', 'm', 'g', 'r', 'f', '.', '=', 'k', 'p', 't',
					's', 'i', 'x', 'y' },
			{ 'r', '!', 'b', 't', 'f', 'h', 'm', 'e', ':', 'c', 'q', '2', '=', 'a', '5', '8', '7', '0', 's', '1', '9',
					'u', '3', '&', 'k', 'p', 'v', 'j', ',', 'g', 'z', 'y', 'x', '.', '?', '4', 'w', '/', 'n', 'o', '%',
					'd', 'l', 'i', '6' },
			{ 't', 'e', 'x', '=', 'g', 'o', '2', 'q', 'b', 'w', 's', 'k', '3', '1', 'y', 'd', '9', 'v', '/', 'i', '0',
					'f', ':', '4', '.', 'm', 'r', '6', 'n', '!', 'h', '%', 'a', 'c', ',', 'u', 'l', 'p', 'z', '?', '&',
					'j', '8', '5', '7' },
			{ 'f', 't', '?', 'g', 'l', '%', '7', 'y', '0', '!', '3', 'v', 'j', 'i', '&', 'c', '5', '/', ':', 'h', 'q',
					'6', 'm', '2', 'p', '.', 'z', '4', 'o', '1', 'a', 'u', ',', 'r', 'x', 'e', 'd', 'w', 'k', 'n', '9',
					'8', 'b', 's', '=' },
			{ '7', '!', '6', 'l', 'r', 'q', 'f', '%', '/', '0', 'i', 'y', '2', 'p', 'w', ':', 'v', 'k', '=', 'u', 'g',
					'j', 'b', 'o', '5', ',', 'e', 'h', 'm', 't', 'd', '&', '9', '4', '.', '?', '3', 'z', '8', 'a', '1',
					'n', 's', 'c', 'x' },
			{ 'q', 'e', 'z', 'c', 'p', '/', 'w', '%', 'y', 'b', '1', '5', 'i', '?', ':', 'o', 'v', '.', 'l', 'r', '6',
					'3', 's', '&', 'n', 'j', 'u', '9', '2', '!', 'd', 'h', '=', 'k', 'm', 'x', '7', ',', 'f', '4', 't',
					'8', 'a', '0', 'g' },
			{ 'q', 'z', 'k', 'r', 'm', 'u', 'o', '=', 'w', '6', 'x', 'd', '8', 'p', 'a', 'b', '%', '&', '2', '9', ',',
					'0', '3', 'c', 'e', '4', '/', 'i', ':', 'j', 'v', '?', 'y', 'l', 't', 'n', 'f', 'g', 's', '1', '7',
					'!', 'h', '.', '5' },
			{ ',', '8', '!', '.', '9', '0', 'a', 'j', '5', '/', 'k', 'i', 'm', '?', 'c', 's', 'g', '=', '%', 'p', ':',
					'z', 'r', 'v', '&', 'y', 'x', '1', 'l', 'w', 't', 'n', 'f', 'd', '7', '6', 'h', 'e', 'b', '3', 'u',
					'q', 'o', '4', '2' },
			{ 'c', '8', 'x', '3', ':', ',', 'p', '0', 'k', '?', '!', '=', 's', 'o', 'u', '2', 'l', '9', 'y', 'm', '&',
					'g', 'i', 'r', '5', 'b', 'q', 'h', 'f', '6', '4', 'e', 'd', 'w', '%', 't', '/', '7', 'j', '1', '.',
					'z', 'v', 'a', 'n' },
			{ '6', 'y', '5', 't', 'g', 'm', 'w', '=', ':', 'n', ',', '?', '9', '/', '3', 'u', 'x', 'p', 'f', 'v', 'k',
					'j', '&', 'o', '4', '2', 'h', 'e', '!', 'z', 'r', '7', '%', 'b', '8', 's', 'i', '1', 'd', '.', 'c',
					'l', 'a', 'q', '0' },
			{ ',', '2', 't', '1', 'n', '&', 'm', 'b', '5', '!', 'k', 'u', '?', '%', '4', '3', 'p', '=', 'z', 'e', 'c',
					'o', '9', 'x', 'w', ':', 'v', 'j', '/', '8', 'f', '6', 'g', 's', 'q', 'r', '7', 'd', 'l', 'a', '0',
					'.', 'y', 'i', 'h' },
			{ 'm', 'b', '.', ':', 's', 'j', 'e', ',', 'v', 'n', '2', 'z', '8', 'l', 'h', '1', '3', '/', '&', 'k', 'q',
					'w', 'u', 't', 'x', 'y', '0', 'i', '4', 'g', 'd', '7', '%', '!', '?', 'r', 'o', 'c', 'a', '5', 'f',
					'6', '=', '9', 'p' },
			{ '4', 'd', '0', 'j', '.', 'h', 'y', 'b', '&', '7', '9', 'l', ':', 'q', 'i', 'u', 'w', 't', 'o', 'a', 'g',
					'c', 'k', '5', 'r', ',', 'm', '1', 's', '6', '%', '8', 'n', '/', 'p', '?', '=', '!', 'f', 'z', 'x',
					'v', '2', '3', 'e' },
			{ '0', '.', ':', '7', 'x', 'h', 'b', 'q', '9', '6', '/', 'd', 'l', 'u', '%', 'f', '8', 'r', 's', 'a', 'g',
					'z', 'y', ',', '2', 'p', '3', 'i', '&', 'e', '4', 'o', '!', '?', 'j', 'k', 'v', '5', 'c', 'n', '=',
					't', '1', 'w', 'm' },
			{ 'y', 'z', 'p', 'e', 'w', '4', '.', '8', '/', 'o', 'l', ',', 'd', '2', 'm', 'b', 'x', '!', 't', '0', 'h',
					'u', 'g', '1', '?', 'n', 'q', '%', 'c', '3', '7', '5', '6', 'r', ':', 'i', '&', '9', 's', '=', 'k',
					'a', 'j', 'v', 'f' },
			{ 'i', 'r', '!', 'x', '8', 'k', '?', 's', 'b', 'w', 'a', '/', '9', 't', 'u', 'q', 'l', 'n', 'm', 'o', 'c',
					'v', '7', 'z', '%', '6', ',', 'g', 'j', '1', '2', '&', 'd', '0', '3', 'h', 'y', '=', 'p', 'f', '.',
					'4', '5', ':', 'e' },
			{ 'l', 'u', 'm', 'x', 's', 'g', 'r', 'v', '6', 'o', '4', '2', '9', 'z', 'n', 'h', 'i', 'q', '&', 'c', 'w',
					'e', ',', '!', '0', 'j', '3', '1', '8', '5', 'p', 'y', '.', 'd', 'k', 't', ':', 'b', 'f', '=', 'a',
					'7', '?', '%', '/' },
			{ 'p', 'i', 'v', '2', '!', 'e', 'g', 'z', 'x', '6', '7', '0', '5', 'd', 's', 'n', 'b', '=', 'm', 'c', ':',
					',', '.', '?', 't', 'f', '%', 'o', 'u', '1', '4', 'a', '8', 'q', '/', 'r', 'y', 'w', '&', '3', 'l',
					'9', 'j', 'h', 'k' },
			{ 'j', 'p', 'r', 'b', 'm', '7', '1', '=', '6', 's', '0', '&', 'v', '4', 't', 'a', ',', 'z', 'c', '.', 'd',
					'x', 'e', '/', '5', 'o', '?', '2', '9', 'l', 'y', '3', 'k', 'q', '%', 'g', ':', 'h', 'w', '!', 'f',
					'i', 'u', '8', 'n' },
			{ 't', 'a', 'c', '&', '/', 'o', 'x', '3', 'e', 'p', '1', '6', '?', '.', 'f', 'v', 'h', 'n', '4', 'w', '7',
					'l', 'q', 'd', 'u', '=', '%', '!', '0', 'k', 'z', ',', 'b', 'i', 'g', 'm', 'r', 's', 'y', ':', '2',
					'9', 'j', '8', '5' },
			{ 'a', 'v', 'u', '.', 'm', '7', ',', '!', '%', '&', 'h', 'q', 'z', 'p', 'k', 'r', 'b', ':', 'g', 'y', 'i',
					'?', '9', 'o', '3', '0', 'e', 'c', '4', 'n', 'w', 'j', '1', 'd', '2', 'x', '5', '6', 'f', '/', 't',
					's', 'l', '8', '=' },
			{ '/', 'j', '=', 'k', 'n', '!', '%', '.', '8', 'g', '4', 'p', '1', ':', '&', ',', 'y', 'w', 'v', 'i', 'u',
					'm', 'r', 'd', 'a', 'o', 'x', 'h', '9', '3', '0', '?', 'f', '7', 's', 'z', 'e', 't', 'l', 'q', '2',
					'5', '6', 'c', 'b' },
			{ 'd', 'b', 'y', 'r', '6', '8', '&', '1', 'w', 'm', '4', 't', 'v', 'k', '3', '.', 'i', 'x', '!', 'l', 'j',
					'o', '9', 'n', ':', '=', 'u', 'f', '%', 'e', 'q', 'z', '2', 'c', 'g', '0', '7', '/', '?', 'p', 'h',
					'5', 'a', ',', 's' },
			{ 'b', 'o', '1', 'k', ':', '&', 'h', 'w', '3', 'm', 'a', '/', 'u', '=', 'i', '5', '9', '7', 'r', 'c', 'f',
					',', 'e', 't', '4', 'v', '?', 'z', '6', 'x', 'n', '!', 'd', '0', 'y', '.', 'p', 'q', 'l', 'j', '%',
					'2', '8', 'g', 's' },
			{ 'q', '3', '%', '/', 'f', 'r', '&', 'y', 'l', 'k', 'n', '6', 'i', 's', 'z', '2', '9', '8', '.', 'v', 'o',
					'g', '7', 'c', 'b', 'm', '0', '5', 'd', 'e', 'w', 'p', ':', 'j', '1', 'x', '?', '=', ',', 'u', '!',
					'a', '4', 'h', 't' },
			{ 'm', 'q', 'g', 't', '5', 'y', '=', 'w', '2', 'p', 'l', 'v', 'b', 'n', 'i', '6', 'f', '0', 's', '/', 'd',
					',', '7', 'u', ':', 'z', '.', 'e', 'j', '?', 'k', '!', 'o', '1', 'h', 'a', 'x', '8', 'r', '9', '&',
					'3', '%', '4', 'c' },
			{ '9', '3', 'm', 'a', 'u', 'w', ':', 'd', '7', 'v', 'n', '&', '1', '0', 'j', '%', 'e', '/', 'r', '5', ',',
					'6', 'o', '8', '!', '.', '4', 'l', 'q', 'g', 's', '2', '?', 'z', '=', 'b', 'c', 't', 'y', 'i', 'f',
					'x', 'h', 'p', 'k' },
			{ 'p', 'a', 'm', 'h', '9', '0', 's', '1', '3', '2', 't', 'y', '&', 'o', 'q', '%', 'f', 'i', '!', 'v', '?',
					'8', 'u', '4', '/', '.', 'w', 'g', 'd', 'z', '5', 'r', '6', 'c', 'b', 'x', ':', 'l', 'k', '=', ',',
					'e', 'n', '7', 'j' },
			{ 'x', 'q', ':', 'y', 'v', 'o', 'd', 'c', '&', 'r', '/', '0', 'w', 's', '4', '%', '?', '8', '2', 'p', 'b',
					'm', 'l', 'h', 'u', ',', '1', '9', 't', 'g', '7', 'z', 'j', 'k', 'n', '3', 'a', '!', '5', '6', '=',
					'.', 'f', 'e', 'i' },
			{ ':', 'r', '4', '=', 'b', 'e', '2', '&', '5', 'j', 'z', '6', 'u', '0', 'k', '7', '%', 'h', '!', 'n', 'g',
					'y', '8', 'i', 's', 'x', 'p', 'q', 'o', 'c', 'd', '.', 'a', '?', 'l', 'w', 'f', 't', '1', '/', 'v',
					'3', '9', ',', 'm' },
			{ 'z', '=', 'p', 'l', '/', 'd', 'h', 'x', '!', 'k', 'r', 'o', '6', 'q', ',', '&', 'j', 'm', '%', '9', 'b',
					'e', 'f', 'g', '1', '4', 'w', 'y', ':', '8', 't', 'n', '3', 'v', '.', 'u', '5', '?', 'i', 's', 'c',
					'2', '0', 'a', '7' },
			{ '0', '!', 'c', '=', '&', '?', 'a', '5', 'o', 'e', 'k', 's', '1', 'j', '4', 'm', 'n', 'i', 'v', 'g', '%',
					'7', ':', '.', '2', 'p', 'r', 'w', 'q', 'l', 'f', 'u', 'x', 't', '/', 'z', '9', '3', '8', 'h', ',',
					'd', '6', 'y', 'b' },
			{ 'q', '6', 'p', 'j', '1', 'x', '8', 'u', 's', '%', 'm', '9', 'n', '=', '?', 'w', 'a', '&', 't', 'k', 'r',
					'3', ',', 'l', 'f', 'e', 'h', '0', 'o', 'b', 'v', '/', '7', 'i', 'd', '2', 'g', '!', 'y', '.', '5',
					'c', '4', 'z', ':' },
			{ 'k', '?', '1', ':', '&', 'i', 'v', '.', 'o', 'n', '3', 'x', '%', 'm', '=', 'a', 'e', ',', 'f', '8', '!',
					'h', '7', '0', 'y', 's', 'c', '2', 'j', '9', '6', '5', 'p', 'd', 'l', 'w', 'q', '4', '/', 'b', 't',
					'z', 'g', 'r', 'u' },
			{ 's', 'v', 'r', '7', 'h', 'o', '%', 'c', 'e', 'z', 'g', 'i', 'p', 'b', ':', '9', '=', 'n', '8', 'l', '!',
					'3', '/', 'x', '?', '4', 'f', 'k', 'q', 'd', ',', '0', 'm', 'y', '5', '1', 't', '2', 'j', 'a', '.',
					'w', '6', '&', 'u' },
			{ 'e', 'u', '=', '6', ':', 'g', '9', 'z', 'n', 'b', 'd', 'x', 'f', '!', '3', 'm', 'j', 'c', '1', '7', 'l',
					'r', 'k', '5', 'i', 'p', '0', 'a', '?', '&', '%', '4', 's', 'h', 't', '2', ',', 'y', '/', '.', 'o',
					'8', 'v', 'w', 'q' },
			{ 'j', 'm', '7', 'o', '.', '9', 'g', 'k', '/', '?', 'u', '=', 'd', 'w', 'n', 'x', '3', 'y', 'i', '%', '8',
					'h', 's', ',', 'a', '6', '4', 'z', '&', 't', 'e', 'v', '!', ':', '2', 'r', '1', 'l', 'b', 'p', 'c',
					'0', '5', 'q', 'f' },
			{ 'z', '.', '=', '?', '4', 'i', 'j', 't', '6', '8', 'd', 'l', 'v', 'x', 'n', 'c', 'r', '/', 'g', 'f', 'w',
					'a', '1', 'h', 'y', ',', '2', '!', 'o', '0', '%', ':', '9', 'u', 'b', 'm', '&', 'p', '3', 'k', 's',
					'q', '7', '5', 'e' },
			{ '6', 'n', 'h', '9', '%', 'i', 'p', '1', 'z', 'l', 'u', '5', 'x', ':', 'q', '/', '?', 'd', '!', 'c', '=',
					'y', 'e', '7', '&', 'o', 'w', '3', '0', '8', 'j', 'm', 'b', 'v', '.', '4', '2', 'r', 'a', 't', 'f',
					's', 'k', ',', 'g' },
			{ '6', 'z', '5', '%', 'd', '9', 'n', ':', 'j', '.', '0', '&', '=', '?', 'e', 'f', 's', 'y', 'g', 'i', 'k',
					'2', 'q', 'r', '7', '!', 'w', 'h', 'a', 't', 'p', 'c', 'l', '1', 'u', 'b', 'm', 'o', '8', '/', 'x',
					'4', 'v', '3', ',' },
			{ 'n', 'm', '6', 'b', 'h', 'k', '1', 's', 'p', '5', '!', '4', '%', 'a', 'd', 'y', '7', ':', 'q', 'o', '3',
					'2', '8', 'c', '?', 'u', '0', 't', 'j', 'e', 'g', ',', '&', 'v', '=', 'i', '9', 'r', 'w', 'f', 'l',
					'.', '/', 'z', 'x' },
			{ 'g', 'k', ':', '?', 'p', 'u', 'c', '0', 's', '3', 'd', 'q', '4', '6', 'y', 'v', 'x', 'e', '.', '9', 'i',
					'r', 'z', 'b', 'o', '2', '&', '1', 'm', '7', 'h', '%', '=', 'w', 'f', 'n', '8', 't', ',', 'l', '!',
					'a', 'j', '/', '5' },
			{ 'e', 'x', 'n', '0', 'w', 'z', 's', 'g', 'd', 'u', 'q', ',', 'h', 'i', 'y', '2', '5', 'v', ':', 'm', 'o',
					'c', '&', 't', '=', 'l', 'k', 'r', '1', '8', '.', '?', 'a', 'b', '7', '4', 'j', '%', 'f', '9', '6',
					'3', '/', 'p', '!' },
			{ '4', '8', 'w', 'm', 'u', '1', 'd', 'g', '.', 'x', '!', '6', 'a', 'b', '2', 'i', 'e', 't', '%', '7', 'c',
					'p', 'v', 'h', 'n', '3', '/', 'r', '9', '?', ',', 'j', '5', '&', '=', ':', 'y', 'o', 'l', 'k', 'f',
					's', '0', 'q', 'z' },
			{ 'e', 's', 'n', 'x', '?', '&', 'b', '5', 'z', '/', 'h', 'a', '.', 'i', 'w', 'l', '8', 'p', '3', '9', '2',
					'q', ',', 'u', 'g', '!', 'k', 'm', 'y', 'c', 'j', 't', 'r', 'f', '0', 'o', '6', '=', 'v', '7', ':',
					'd', '%', '4', '1' },
			{ 'i', 'm', 't', 'z', '%', 'j', 'w', 'a', 'x', '/', 'f', 'l', ',', '0', 'g', 'v', '9', '.', '3', '7', 'y',
					'5', 'k', 'h', '!', 'q', '&', '?', 'c', '8', 'd', '6', 's', 'n', 'o', ':', 'e', '4', 'b', '=', 'u',
					'2', 'p', 'r', '1' },
			{ 'o', 'c', 'k', '3', ':', 't', '/', 'u', '!', 'i', '0', 'a', 'h', '5', '7', '&', 'n', 'e', 'w', 'q', 'r',
					'x', '8', '=', 'p', '6', ',', 'v', 'y', '9', '2', 'd', 'b', 'g', 'm', 'z', 'f', 'j', '.', '4', 's',
					'?', 'l', '%', '1' },
			{ '?', 'a', 'z', '!', '4', 'i', 'u', 'g', '5', 'f', ',', '8', '.', 'r', 'e', 'v', '7', ':', '&', '1', '/',
					'h', '%', 'm', '9', 'y', '6', 'p', 'k', '0', 'l', 'n', 'b', 'x', '2', 'w', 'c', 's', 'j', 'q', 'o',
					'd', 't', '=', '3' },
			{ 'o', '7', 'm', 'h', '0', '1', 'n', 'y', 'c', '&', '5', 't', 'i', 'x', '3', 'f', 'j', 'z', 'u', 'd', 'a',
					'2', '.', '9', ':', 'e', '?', '8', 'r', 'k', 'q', 'g', 'p', 's', '=', 'w', ',', '!', 'l', 'b', '%',
					'v', '/', '4', '6' },
			{ 'm', 'g', 'x', 'v', '2', 'r', 'e', 'w', 'a', '5', 'j', 'u', '?', 'n', 'q', 'i', '.', 'o', '6', 'c', 'p',
					'h', 'l', 's', '%', '4', 't', '7', '1', '/', '9', '3', 'k', ',', '0', 'b', '!', 'f', '8', 'z', '&',
					'd', ':', '=', 'y' },
			{ 'r', 'h', 'p', '7', '4', '?', 't', '2', 'j', 'n', 'o', '%', 'u', '6', '3', '!', '5', 'i', '9', 'b', 'g',
					'0', 'c', 'q', 'z', 'x', '=', 'e', ':', ',', 'y', '&', '8', '.', 'm', 'v', 'f', 'k', 'l', 'd', 'a',
					'w', '/', '1', 's' },
			{ 'k', 'e', 'p', 'z', '.', '2', 'u', '5', '!', 'f', '1', 'g', '6', '9', 'i', ':', '?', 'd', 'j', '&', 'c',
					'o', '4', 'v', '7', 't', 'y', '0', 'm', '8', ',', 'h', '=', 'r', 'n', 'a', '3', '/', 'x', 's', '%',
					'w', 'b', 'q', 'l' },
			{ 'o', '/', '1', '=', 'f', 'g', '3', 'b', 'm', '9', '6', 'i', 'z', 'j', '%', 'c', 'a', '&', '4', '5', '7',
					'8', ',', '!', 't', '?', 'n', ':', 'r', '.', 'e', 'h', 'd', '0', '2', 'v', 's', 'x', 'p', 'k', 'w',
					'y', 'l', 'u', 'q' },
			{ 'k', 'g', 'm', '&', '!', '5', '3', '.', 'y', '6', 'e', 'x', '8', 'p', 's', '0', 'n', 'v', '1', '?', '/',
					'o', 'i', '9', 'c', '2', ',', '7', 'd', '4', 'w', 'u', 'f', 'j', ':', 't', '%', '=', 'b', 'z', 'a',
					'q', 'l', 'h', 'r' },
			{ '4', '=', '2', ':', '/', 's', 'v', ',', '?', 'u', 'o', 'm', 'n', 'f', '1', 'y', '8', 'h', 'l', 'w', '9',
					'p', '3', 't', 'g', '.', 'x', 'c', '%', 'j', '6', 'i', '7', 'e', '!', '0', 'r', 'q', 'k', '5', 'b',
					'z', '&', 'd', 'a' },
			{ 'k', '4', '/', '.', 'd', 'v', '&', '3', 'j', 'f', '=', 'p', '2', '9', '%', ',', 'a', '1', 'e', '6', 'c',
					'8', 'l', 'b', 't', 'u', 'w', 'z', '!', 's', 'n', 'q', 'm', 'g', '7', ':', 'y', 'r', '?', 'x', 'o',
					'h', '5', 'i', '0' } };
	int[] randomLevels = { 50, 47, 54, 97, 55, 99, 51, 31 };
	String[] levelKeys = { "ac", "tr", "gv", "q0", "::", "eh", "6z", "m?" };
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
			int randomIndexToSwap = rand.nextInt(array.length);
			char temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}

		return array;
	}

	public String setLevel(String textIn) {
		level = randomLevels[findInArrayString(levelKeys, textIn.substring(0, 2))];
		return textIn.substring(2, textIn.length());
	}

	private void initialize() {
		/* start of frame initialization */
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
					int ecyrtptionLevelIndex = findInArrayString(levelKeys, temp.substring(0, 2));
					temp = temp.substring(2);

					for (int i = 0; i < randomLevels[ecyrtptionLevelIndex]; i++) {
						try {
							temp = deEnccrypt(temp);
						} catch (IndexOutOfBoundsException e) {
							encrypt.setSelected(true);
							deEncrypt.setSelected(false);
						}
					}
					copyToClipBoard(temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(temp);
				} else {
					Random rnd = new Random();

					// select random lvl key
					int levelSelectedIndex = rnd.nextInt(levelKeys.length);

					String temp = inString;
					for (int i = 0; i < randomLevels[levelSelectedIndex]; i++) {
						temp = encrypt(temp);
					}
					copyToClipBoard(levelKeys[levelSelectedIndex] + temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(levelKeys[levelSelectedIndex] + temp);
				}
			}
		});
	}

	void copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();
		// chooses mix up index
		int mixUpIndex = rndRandom.nextInt(keys.length);
		String outString = keys[mixUpIndex];
		// chooses the key that will be used for this level of encryption
		char[] keyToUse = mixedUps[mixUpIndex];
		// encrypts the string with the chosen key
		for (int i = 0; i < inString.toCharArray().length; i++) {
			if (inString.toCharArray()[i] != ' ') {
				outString = outString + Out.getText() + keyToUse[findInArray(alphabet, inString.toCharArray()[i])];
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
