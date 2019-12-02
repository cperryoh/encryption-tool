
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;

public class tool {
	// please note that spaces are ignored in the encryption process an spaces that
	// are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	char[] temp;
	int level = 5;
	String[] keys = { "f&p", "jaq", "frj", "guh", "il!", "yko", "bmp", "v&s", "wnj", "mvk", "v!?", "!id", "?uh", "zir",
			"q.f", "eey", "z!n", "of?", "ie.", "d!k", "dok", "hvg", "h!z", "dim", "ziy", "pug", "xos", "kep", "tpj",
			"wha", "qs,", "wns", "p,p", "ww,", "&&u", ".!o", "pxj", "l,!", "fjs", "?ne", "jdx", "fin", "exe", "opo",
			"crn", "ipe", "ajo", "abc", "!no", "nz,", "m.h", "wqc", "e&z", "ugp", "phw", "amn", "vcg", "rc%", "w!o",
			"wky", "d&?", "s%b", "qwz", "jn?", "yck", "!ol", "lyt", "aug", "d!v", "yt?", "f,d", "mqd", "zjz", ",ae",
			".!y", "%z,", "p.z", ".t.", "i.g", "us&", "qb?", "v?b", "?tl", "puo", "um.", "%o&", "?hg", "mgs", ".el",
			",bu", "%iy", "rgc", "g&t", "wd,", "w!o", "m!z", "afd", "s&y", "wiu", "ast" };
	char[] alphebet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!' };
	char[][] mixedUps = {
			{ 'x', 'n', '%', 'q', 'w', 'i', 'y', 'g', '&', 'b', 'r', 't', 'u', '?', ',', 'f', 'a', '.', 'p', 'd', 'l',
					'j', 'k', 'e', '!', 'm', 'z', 'c', 'h', 'o', 's', 'v' },
			{ '&', 'r', 'e', 'm', 'o', 'x', 'd', 'c', 'p', 'q', 't', 'k', ',', 'f', 's', 'n', 'w', 'a', 'i', '!', '%',
					'b', 'j', 'h', 'g', '?', 'z', 'v', 'y', 'u', '.', 'l' },
			{ 'q', 't', 'x', 'c', 'd', 's', 'j', '?', 'r', 'v', 'o', 'z', 'n', '%', ',', 'f', 'g', '.', '&', '!', 'i',
					'h', 'a', 'l', 'p', 'w', 'b', 'm', 'y', 'k', 'e', 'u' },
			{ ',', 't', 'p', 'x', 'g', 'q', 'f', 'v', 'k', 'c', '%', 'l', 'j', 'm', '.', 'h', 'z', '&', 'w', 'u', 'y',
					'b', 'e', 'd', 'i', '!', '?', 's', 'o', 'r', 'a', 'n' },
			{ 'e', 't', '%', 'f', 'v', 'r', 'z', 'y', 'o', 'w', 'p', '&', 'j', '.', 's', 'l', '?', 'u', 'b', '!', ',',
					'a', 'k', 'c', 'n', 'x', 'q', 'g', 'd', 'h', 'i', 'm' },
			{ 'o', 'a', 'p', 'm', 'k', 'f', 'w', '?', 'y', 'j', 'i', 'g', 'l', 'c', '%', ',', 'z', 'b', 'v', 'h', '!',
					'n', 'x', '&', '.', 's', 'r', 'q', 'd', 'u', 't', 'e' },
			{ '!', 's', 'v', 'f', ',', 'i', '%', 'h', 'o', 'a', 'n', '&', 'd', 'm', 'b', '.', 'l', 'x', 'e', 'r', '?',
					'k', 'w', 'j', 'q', 'c', 'z', 'p', 'u', 'y', 't', 'g' },
			{ 'p', 's', 'j', 'g', 'l', 'w', '!', '?', 'a', 't', 'z', 'c', '.', 'e', 'h', 'm', '%', 'q', 'o', 'x', 'v',
					'r', 'd', 'f', 'i', 'b', 'u', 'n', ',', '&', 'k', 'y' },
			{ 't', 'd', 'y', '.', 'm', 'w', '?', 'u', 'g', 'p', 'v', 'e', 'z', 'l', 'k', 'j', 'a', 'b', 'r', ',', 'n',
					'x', 'i', '!', 'q', 's', 'o', '%', '&', 'h', 'f', 'c' },
			{ 'd', 'r', 'e', 'c', 't', 'y', '?', 'f', 'j', 'w', 'g', 'v', 'o', 'i', 'k', '.', 'a', ',', 'm', 'x', '%',
					'q', '!', 'p', 'u', 'b', 'h', 'n', 'z', '&', 'l', 's' },
			{ 'z', 'b', 'y', 'm', 'x', '!', 'j', 'k', 'o', 't', 'p', 'i', 'a', 's', 'q', '&', 'e', 'c', '%', 'v', 'n',
					'u', '?', '.', 'w', 'l', 'f', 'h', 'd', 'r', ',', 'g' },
			{ '!', 'w', 'k', '.', 'g', ',', 'a', '&', 'p', 'l', 'j', 'z', 'i', 'y', '%', '?', 'b', 'e', 'c', 't', 'x',
					'r', 'o', 's', 'h', 'f', 'm', 'v', 'n', 'd', 'u', 'q' },
			{ 'k', 'u', 'r', 'b', 'e', 'q', 'y', 'i', 'l', ',', 'c', 's', '?', 'x', 'p', 'd', 't', 'o', 'f', 'w', '%',
					'h', '.', 'j', 'm', 'a', 'v', 'z', 'n', 'g', '!', '&' },
			{ '?', 'n', 'w', '.', 'c', 'j', '%', 'k', 'y', 's', 'p', 'd', 't', 'z', 'l', 'g', 'a', 'v', 'h', 'r', 'x',
					'&', 'o', 'm', '!', 'b', 'u', 'e', 'q', 'i', ',', 'f' },
			{ 'c', 'f', '.', 'n', 'v', '?', 'y', 'h', 'e', '&', 'x', 'b', 'g', 'k', 'p', 'w', 'm', 't', 'r', 'a', 'd',
					'%', 'j', ',', 's', 'u', 'i', 'l', 'q', 'z', '!', 'o' },
			{ 'w', 'b', '.', 'r', '?', ',', 'm', 'q', 'a', 'g', 's', 'y', 'o', '%', 'k', 'z', '!', 'h', 'n', 'j', 'u',
					'l', 'v', 'x', 'p', 'c', 't', 'e', 'i', '&', 'd', 'f' },
			{ 'y', 'u', 'd', 'e', '.', 'z', 'c', 'n', 'b', 'q', 'i', 'g', 'x', 'w', 'p', '!', 'l', 'j', 'o', 'a', '&',
					't', '%', ',', 'm', 'h', '?', 'k', 'f', 'r', 's', 'v' },
			{ ',', 'o', 'p', 'c', 'z', 'x', 'm', '%', 'w', 'h', 'v', 'd', 'e', 'b', 'f', 'n', 'r', '.', 's', 'y', 'q',
					'a', '!', '&', 'i', '?', 'k', 'j', 'l', 'g', 'u', 't' },
			{ 'm', 'j', 't', 'z', 'e', 'v', 'x', 'o', 'f', 'w', 'b', ',', 'g', 'p', '&', 'y', '.', 'i', 'a', '!', 'd',
					'c', 'r', 'k', 'n', '%', 'u', 's', '?', 'l', 'q', 'h' },
			{ '.', 'f', 'l', 'k', 'q', 'y', 'g', 'd', 'c', 'p', 'r', 't', 'h', '?', '&', 'm', 'z', '!', 'e', '%', 'x',
					'n', 'u', 'v', 's', 'w', ',', 'o', 'j', 'i', 'b', 'a' },
			{ 'c', 'q', 's', 'p', 'j', 'i', '!', 'a', '%', 'o', 'k', 'z', '?', '.', ',', 'n', 'd', 'f', 'h', 'v', 'u',
					'x', 'g', 'b', '&', 'y', 't', 'e', 'l', 'm', 'r', 'w' },
			{ 'f', 'k', 'j', 'd', 'i', 'y', 'e', 'a', '?', 'h', ',', 'z', 'r', 'g', 't', '&', 'n', 'x', 'o', '.', '%',
					's', 'c', 'p', 'q', 'b', 'u', 'm', 'l', '!', 'w', 'v' },
			{ 'h', 'l', 'e', '.', '%', 'x', 'q', 'k', 's', '&', 'f', 'd', 'j', 'y', 'v', ',', 'p', 'g', 'u', 'n', '!',
					'?', 'z', 'a', 'm', 'b', 't', 'o', 'i', 'r', 'c', 'w' },
			{ 'v', 's', 'd', 'i', '.', 'k', 'o', 'm', '%', ',', 'p', '&', 'e', 'a', 'z', 'g', 'w', 'q', 'b', '?', 'y',
					'!', 'x', 't', 'j', 'c', 'h', 'l', 'f', 'n', 'u', 'r' },
			{ 'k', 'm', 'g', '%', '!', 'u', 'q', 'c', 'w', 'p', 'z', 'y', 'x', 'v', 'o', ',', 's', 'h', 't', '&', 'a',
					'l', 'e', 'n', 'j', 'd', 'b', 'i', 'f', 'r', '.', '?' },
			{ 'c', 'm', 'p', 'a', 'f', '!', 'n', '&', 'w', 'v', 'j', 'e', 't', 'r', 's', ',', 'y', '.', 'd', 'o', 'z',
					'?', 'h', '%', 'q', 'k', 'b', 'g', 'u', 'x', 'i', 'l' },
			{ 'w', 'y', 'h', 'b', 'o', 'x', 's', '.', 'z', 'p', 't', 'e', 'm', ',', 'u', '!', 'n', 'c', 'd', 'k', 'f',
					'i', 'q', '?', 'a', 'v', 'j', 'g', '&', '%', 'r', 'l' },
			{ 'p', 'n', 's', 'h', 'f', ',', 'w', '.', '!', 'g', 'x', '&', '?', 'v', 'u', 't', 'a', 'y', 'j', 'i', 'o',
					'r', '%', 'c', 'z', 'm', 'q', 'b', 'l', 'd', 'e', 'k' },
			{ 'o', 'z', 's', 'f', 'g', 'n', 'p', 'x', 'y', '.', 'h', 'k', 't', '!', 'r', 'v', 'a', 'q', 'c', 'b', 'j',
					'l', 'd', '%', 'm', 'u', ',', 'i', 'e', '?', 'w', '&' },
			{ 'k', 'i', 'v', 'b', '?', 'u', 'e', 'z', 'r', ',', 'g', 'a', 'n', 'c', 'd', 'p', 'j', 't', '.', 'w', 'y',
					'%', '!', 'o', 'x', 'm', 's', 'q', 'h', '&', 'l', 'f' },
			{ 's', 'b', 'l', 'g', 'u', 'q', 'p', 'k', 'm', 'n', 'r', '%', '&', 'e', 't', 'i', 'o', 'z', 'a', 'd', 'x',
					'.', 'j', 'c', ',', 'h', 'f', '!', 'w', 'v', 'y', '?' },
			{ 'x', 'n', 'w', 'z', '?', 'q', ',', '%', 'b', '&', 'j', 'f', 'c', 'i', 'm', 'v', 'a', 's', 'd', 'h', '.',
					'!', 't', 'e', 'p', 'g', 'k', 'y', 'r', 'o', 'u', 'l' },
			{ 'w', 'x', 's', 'l', 'i', 'y', 'o', 't', 'a', 'r', 'd', '.', 'b', 'm', 'q', 'z', '!', '?', 'h', '&', '%',
					'c', 'p', 'j', ',', 'v', 'n', 'g', 'k', 'u', 'e', 'f' },
			{ 'r', 's', 'o', 'z', 'n', 'x', 'p', 'w', '!', 't', 'l', 'k', 'u', 'b', 'v', '%', 'e', ',', 'a', '.', 'q',
					'y', '&', 'f', 'c', 'g', 'j', 'h', 'm', '?', 'i', 'd' },
			{ 'a', 'v', 'd', 'c', 'o', '?', 'e', 'l', '%', 'p', '.', 'b', 's', 'y', 'r', 'n', 'z', 'w', 'x', 'u', 'h',
					'j', 'i', '!', 'f', 'm', 't', 'g', '&', 'k', 'q', ',' },
			{ 'k', 'd', 'w', 'p', 'o', '%', 'c', 'g', 't', 'l', '&', 's', 'e', '?', 'j', 'b', 'f', 'h', '!', 'a', 'x',
					'v', 'n', 'q', 'r', 'm', 'z', '.', 'i', ',', 'u', 'y' },
			{ 'q', 'c', 'h', 'r', 'e', '!', 'g', 'z', '?', 'a', 'o', 'x', '&', 'k', 'y', 'b', 'm', ',', 'd', 'p', 'i',
					'w', 'n', 'f', 'l', 't', 'j', 'u', '%', 'v', '.', 's' },
			{ '&', 'c', 'b', '?', 'm', 'q', 'a', 'w', 'i', 's', 'l', 'h', 't', '.', 'z', 'u', '%', 'n', 'k', 'y', 'f',
					'x', 'j', 'o', ',', 'v', 'd', '!', 'e', 'r', 'p', 'g' },
			{ ',', 'q', 'o', 'd', 'u', 's', 'm', '.', 'v', 'r', '!', 'x', 'p', 'e', 'h', 'n', 'w', 'k', '?', 'l', '%',
					't', 'b', 'f', 'i', 'z', '&', 'j', 'g', 'y', 'a', 'c' },
			{ 'm', 'g', 'w', 't', 'b', 'u', '!', 's', 'x', '.', 'n', 'd', 'h', 'k', 'e', 'v', 'i', 'y', 'r', 'l', '?',
					'f', 'c', '&', 'q', 'z', 'p', 'j', 'a', '%', 'o', ',' },
			{ 'z', 'f', '?', 'm', 'b', '%', 'r', 'l', ',', 'y', 'v', 'q', 'k', 'h', 'w', 's', 'd', 'x', 'o', '!', '&',
					'n', 'e', 'p', 'g', 'u', 'i', 'a', 'c', 'j', '.', 't' },
			{ '&', '!', 'j', 'w', 'k', 'z', 'v', ',', 'm', 'u', 'd', '?', 's', 'g', 'x', 'p', 'e', 'a', 'y', 'r', 'c',
					'l', '.', 'i', 'b', 'f', '%', 't', 'o', 'n', 'q', 'h' },
			{ 'd', 'v', 'i', 'c', 's', 'b', 'e', 'a', 'y', 'r', 'f', 'p', 'g', ',', 'j', 'm', 'u', '?', '.', 'w', 'o',
					'!', '&', 'h', 't', 'x', '%', 'k', 'q', 'l', 'n', 'z' },
			{ 'p', 'i', '?', 'w', 'u', 'd', 't', 'q', 'f', 'n', '.', 'y', 'j', 'l', 'v', 'g', 'k', 'x', 'm', ',', '!',
					'h', '%', 'r', '&', 'e', 'c', 'z', 'b', 'o', 's', 'a' },
			{ '%', 'k', 'l', 'w', 'u', '&', 'h', 'r', 'c', ',', 'd', 'j', '?', 't', 'v', 'p', 'm', 'n', 'i', 'g', '!',
					'b', 's', 'q', 'z', 'f', 'y', '.', 'a', 'x', 'o', 'e' },
			{ 'v', 'z', 'y', 'e', '%', 'h', 'r', 'n', 'm', 'p', 'x', 'g', 'o', 'q', 'w', '&', ',', 't', 'k', 'd', 'b',
					'i', '.', 'c', 'a', '!', '?', 'l', 'f', 's', 'u', 'j' },
			{ 't', ',', 'l', '%', 'q', 'u', 'r', 'b', 'z', 'y', 'v', 'a', '&', '.', 'f', 'g', 'e', 'i', '!', 'h', 'x',
					'?', 'm', 'o', 's', 'd', 'w', 'c', 'p', 'j', 'n', 'k' },
			{ 'e', 'z', 'g', 'h', '&', 'f', 'n', '.', ',', 'd', '!', 'u', 'v', 'j', 'x', 'l', 'r', 'y', 'c', '?', 'o',
					'i', '%', 's', 'm', 'a', 'q', 't', 'p', 'k', 'w', 'b' },
			{ '!', 'a', '.', '%', 'u', '?', 'o', 'r', 'd', 'v', '&', 'z', 'w', 'l', 'h', 'f', 'e', 'c', 'y', 'i', 's',
					'q', 'n', 'm', 't', 'g', 'k', 'j', ',', 'x', 'b', 'p' },
			{ 'm', 'z', 'l', 'b', 'f', 'n', 'u', '?', 'v', 'i', 'o', 'w', 'k', 'h', 'c', 'e', 't', '%', 's', 'a', 'y',
					'!', '.', 'j', 'g', 'x', 'p', ',', 'd', '&', 'q', 'r' },
			{ 'x', '?', 's', '&', 'f', 't', 'o', '%', 'w', 'h', 'e', 'v', 'q', '!', 'b', 'u', 'l', 'd', 'j', ',', 'g',
					'm', 'a', 'p', 'c', 'i', '.', 'y', 'z', 'n', 'k', 'r' },
			{ 'l', 'x', 'd', 'n', '?', '%', 'o', 'q', 'r', 'b', 'w', 'h', 'v', '.', ',', 'z', 'e', 'a', 'm', 'c', '&',
					'i', 'j', '!', 'u', 'k', 'g', 't', 'y', 'p', 'f', 's' },
			{ 'q', 'i', 'u', 'w', 'e', 'x', '&', '?', 'm', 'l', 'v', 'j', 'z', 'n', '%', '.', 'h', 'c', 'k', 'a', 'p',
					',', 'y', 't', 'r', 'd', 'b', 'f', 'g', 'o', '!', 's' },
			{ 'd', 'e', 's', '&', 'v', 'm', 'u', '%', '?', 'x', 'o', 'b', 'q', 'y', 'k', 'l', 'f', 'h', ',', 'g', 't',
					'a', 'z', 'p', 'c', 'r', 'i', 'j', 'n', '.', '!', 'w' },
			{ 'x', 'v', '%', '&', 't', '!', 's', 'l', 'y', 'z', ',', 'j', 'd', 'p', 'c', 'b', 'w', 'r', '.', 'o', 'k',
					'a', 'g', 'e', 'n', 'h', 'm', 'q', 'f', 'i', '?', 'u' },
			{ 'a', 'm', 'y', 'c', 'i', 'r', 'x', '&', 'p', 'w', 'h', '%', 'q', ',', '?', 's', 'g', 'v', 'k', '.', '!',
					'j', 'f', 't', 'z', 'l', 'n', 'o', 'e', 'b', 'd', 'u' },
			{ 'y', 'j', 'n', 'w', 'o', 'i', 'a', 'k', 'g', 't', 'x', 's', '?', 'v', '.', 'u', ',', 'q', '!', 'c', 'd',
					'r', 'z', 'h', 'b', 'f', '&', '%', 'p', 'e', 'l', 'm' },
			{ 'h', 'c', 'r', 'y', 't', 'k', 'a', 'v', '?', 'l', 'n', 's', '%', '&', 'z', 'u', '.', 'q', 'j', 'x', 'i',
					'd', 'g', 'p', 'w', 'f', 'o', '!', ',', 'm', 'b', 'e' },
			{ 'j', 'x', 'f', 'd', 'b', ',', 'y', 'g', 'r', '!', 'c', 'a', 'k', 'q', 'l', 'u', 't', 'm', 'h', 'w', 'i',
					'.', '%', 'z', '?', '&', 'v', 'o', 'e', 'p', 's', 'n' },
			{ 't', 'l', 'a', 'y', '&', ',', 'j', 'f', 'g', 'e', 'p', '.', 'd', 'z', 'o', 's', 'q', '?', 'c', 'i', '%',
					'n', 'r', 'x', 'k', 'w', 'u', 'v', 'b', 'h', '!', 'm' },
			{ '&', 'z', 't', 'm', 'g', 's', 'w', ',', 'p', 'l', 'd', 'u', 'q', 'h', 'a', '%', 'e', 'r', 'n', '?', 'f',
					'b', 'v', 'y', 'i', '.', 'o', '!', 'c', 'k', 'x', 'j' },
			{ 'w', 'z', ',', '?', 'p', 'r', 'o', 'n', 'j', 'y', 's', 'a', 'm', 'l', 'f', 'b', 'u', 't', 'i', 'k', '!',
					'%', 'd', 'g', '&', '.', 'e', 'x', 'h', 'q', 'c', 'v' },
			{ '!', 'h', '%', 'r', 'p', 'y', 's', 'e', 'l', 'b', 'm', 'f', 'd', '.', 'u', 'g', 't', '?', 'w', 'z', 'a',
					',', 'n', 'q', 'i', 'v', 'k', 'j', 'c', 'o', '&', 'x' },
			{ 'd', 'v', 'm', 'b', 'l', 'c', ',', 'y', 'o', '%', 'r', 't', '.', 'k', 'a', 'x', 'q', 'w', 's', 'i', '&',
					'?', 'h', 'j', 'n', 'u', '!', 'e', 'f', 'g', 'z', 'p' },
			{ ',', 'w', 'h', 'x', 'd', '.', 'f', 'o', 'z', 'r', 's', 'a', 'v', 'q', '&', '%', 'l', 'i', 'e', 'u', 'c',
					'g', 'k', 'j', 'm', '?', 'b', 'p', '!', 't', 'y', 'n' },
			{ ',', 'a', 'c', 'v', 'z', 'l', 'm', 'x', '&', '?', 'p', '!', 't', '%', 'o', 'j', 'r', 's', 'f', 'e', 'n',
					'h', 'q', 'b', 'g', 'u', 'w', '.', 'd', 'i', 'k', 'y' },
			{ 'l', '?', 'c', 'v', 'r', 'a', 'p', 'u', 'y', '%', ',', 'g', 'j', 'w', 'x', 'o', 'h', 's', 'z', 'i', 'm',
					'.', '!', 'f', 'd', 'b', 'n', 't', 'k', '&', 'e', 'q' },
			{ 'g', 'u', 'k', 'j', 'b', 'd', 's', '.', 'x', 'h', '?', 'q', 't', 'y', 'a', 'm', 'o', '!', 'v', 'z', 'l',
					'i', '&', '%', ',', 'r', 'n', 'e', 'f', 'w', 'p', 'c' },
			{ ',', 'k', '.', 'f', 'a', 'g', '&', 's', 'i', 'l', 'v', '%', 'r', 'h', 'p', 'y', 't', 'c', '!', 'n', 'e',
					'w', 'o', 'z', 'd', 'q', 'b', 'j', '?', 'm', 'u', 'x' },
			{ 'g', 'c', 'n', 'p', 'i', 'e', 'a', '!', 'y', 'q', 'l', 'j', 'w', 'f', 'z', 's', 'd', 'r', 'o', 'm', 'k',
					'b', '?', '&', '.', 't', '%', ',', 'u', 'h', 'x', 'v' },
			{ 'e', 'l', '&', '!', 'w', 'k', 'y', 'd', 'j', 'o', 'f', 'q', 'h', 'z', 'n', 'a', 'p', '.', 'b', 's', 't',
					',', 'r', 'i', '?', 'c', 'v', 'm', '%', 'g', 'x', 'u' },
			{ '!', 'x', 'l', 'h', 'f', 's', 'r', '?', ',', 'k', 'b', 'g', 'p', 'j', 'y', 'o', 'i', 'e', 'v', '.', 'z',
					'u', 'd', '%', '&', 'm', 't', 'n', 'w', 'c', 'q', 'a' },
			{ 'f', 'j', ',', 'h', 'z', 'l', '.', 'e', 'o', 'y', 's', 'k', 'm', 'u', '&', 't', 'q', '?', 'd', 'v', '!',
					'x', 'p', '%', 'a', 'g', 'n', 'w', 'r', 'i', 'c', 'b' },
			{ 'z', 'p', 'j', 'o', '!', 'c', 'y', '?', 'x', 'q', 'u', '&', '%', 'i', 'a', 't', 'r', 'd', 'w', 'm', ',',
					'g', 'h', 's', 'l', 'e', 'k', 'b', '.', 'v', 'n', 'f' },
			{ 'y', 'u', 'p', 't', 'j', 'z', 'x', 'n', 'k', 'g', 'o', 'f', '.', 'b', 's', 'i', ',', '?', 'd', 'a', 'h',
					'e', '%', 'w', 'l', 'm', 'v', '&', 'c', '!', 'q', 'r' },
			{ 'a', 'h', 'o', 'q', 'f', 'k', 'i', 'r', '%', 'n', ',', 'e', 'l', 'x', 'd', '.', 'j', '?', 'u', '&', 'z',
					't', 'w', '!', 'y', 'm', 'b', 's', 'p', 'c', 'g', 'v' },
			{ '.', 'd', 'v', 'y', '!', '%', 's', '?', 'l', 'j', 'x', ',', 'h', 'f', 'r', 'o', 'g', 't', 'p', 'b', 'a',
					'e', 'c', 'q', 'z', 'w', 'u', 'm', 'i', 'n', 'k', '&' },
			{ 'q', 'j', 'o', 'b', 'e', 'z', 'h', ',', 'f', 's', 'n', 'c', 'a', 'p', '%', 'm', 'l', 'g', 'y', 'u', 'r',
					'.', '&', 'w', 'd', 'x', 't', 'i', '?', '!', 'v', 'k' },
			{ 'b', 'k', 'y', 'd', 'v', 'w', 'l', 'p', 'e', 'n', 'q', 'r', 'f', 's', 'g', ',', 'h', 'x', 'm', 'j', '&',
					'?', '%', 'c', 'u', '!', '.', 'i', 'o', 'z', 't', 'a' },
			{ '.', 's', 'h', 't', 'a', 'w', 'b', '%', 'l', ',', 'v', 'n', 'c', '!', '?', 'z', 'r', 'i', 'u', 'q', 'm',
					'e', 'x', 'f', 'p', 'o', 'y', 'k', '&', 'd', 'j', 'g' },
			{ 's', '%', 'x', 'g', 'h', 'z', '!', '.', 'k', 'y', 'u', 'o', 'm', 'j', 'i', 't', 'l', 'p', 'b', '&', 'f',
					'd', 'c', 'r', 'e', 'q', 'w', 'v', ',', 'a', 'n', '?' },
			{ 'q', '?', 't', 'i', 's', 'g', 'w', 'y', 'k', 'n', 'z', 'e', 'p', 'j', '&', 'f', 'a', 'd', 'c', ',', 'm',
					'x', '.', 'o', 'u', 'h', 'l', '!', 'b', '%', 'r', 'v' },
			{ '!', '%', 'y', 'k', ',', 'r', 'm', 'd', 'h', 'x', 'c', 'u', 'i', 'v', 's', 'f', 'z', '&', 'l', '?', 'e',
					'j', 'q', '.', 'g', 'o', 't', 'n', 'w', 'p', 'b', 'a' },
			{ 'j', 'q', '?', '&', 'r', '!', 'k', 'z', 'm', '.', 'o', 'u', 'e', ',', 'h', 'a', 't', 'w', 'p', 'l', 'x',
					'n', '%', 'c', 'f', 'g', 'b', 'y', 'd', 'v', 'i', 's' },
			{ 'k', '.', 'b', '!', 'z', 'v', 'p', 'd', 'w', 'y', 'l', 'n', 's', 't', 'm', '?', ',', 'u', 'x', 'c', 'h',
					'&', '%', 'a', 'f', 'q', 'r', 'o', 'j', 'g', 'i', 'e' },
			{ '!', 'e', 'w', 'z', 'b', '.', 'q', 'r', 'u', '?', 'm', 'k', 'h', ',', 'i', 'o', 'j', 'c', 's', 'a', 'l',
					'y', 't', 'f', 'd', '%', 'p', 'g', 'v', 'n', 'x', '&' },
			{ 's', 'r', 'b', 'f', 't', 'g', 'u', 'c', 'z', '!', '&', 'a', 'q', 'h', 'o', 'd', 'v', 'y', 'm', 'p', 'i',
					'w', '%', 'e', 'l', 'n', 'x', '.', '?', ',', 'j', 'k' },
			{ '&', 's', '%', '.', 'r', 'u', 'h', 'o', 'z', 'v', 'a', 'm', 'p', 'k', 'f', ',', 'b', 'd', 'l', '?', 'w',
					'!', 'x', 'j', 'c', 'n', 'q', 'e', 'y', 't', 'g', 'i' },
			{ 'b', 't', 'p', '?', 'f', 'r', 'd', '%', 'w', 'l', 'x', 'g', ',', 'v', 'c', 'j', 'k', 'a', 'm', '.', 'e',
					'i', 'n', 'o', '!', 'q', 'z', 'u', 's', 'h', '&', 'y' },
			{ '%', 'j', 'o', 'g', 'z', 'p', 'v', '.', 's', 'u', 'b', '&', 'e', 'w', 'i', 'k', 'f', ',', '!', '?', 'y',
					'd', 'q', 'a', 't', 'l', 'x', 'r', 'n', 'm', 'h', 'c' },
			{ 'l', ',', 't', 'r', 'v', 'p', 'e', '?', '.', 's', 'o', 'q', 'd', 'x', 'k', 'c', 'f', 'z', '%', 'm', 'a',
					'y', 'i', 'w', 'n', 'h', 'j', 'g', '&', '!', 'b', 'u' },
			{ 'f', 'd', '&', 'i', 'j', 'r', '.', 'o', 'q', 'a', 'w', 's', 'p', 'n', 'k', ',', '%', 't', 'h', 'c', 'l',
					'z', 'y', 'u', 'e', 'v', 'g', 'm', 'b', 'x', '?', '!' },
			{ 'p', 'r', '!', 'j', 'y', 'k', '%', 'f', 'l', 'w', 'o', 'a', ',', 't', 'q', 'c', 'd', 'e', '&', '.', 'h',
					'v', 'm', 'i', 'n', 'x', 'b', 'g', '?', 'u', 's', 'z' },
			{ '%', 'e', 'r', 'm', '?', 'w', 'b', ',', 'k', 'p', 'v', 'q', 'n', 'u', 's', 'f', '.', 'l', 't', 'h', 'o',
					'g', 'z', 'c', 'x', 'y', 'a', 'j', 'd', 'i', '!', '&' },
			{ 'j', 'h', 'r', 'a', 'c', 'o', '%', 'x', 'q', ',', 'i', 'l', '.', 'e', 'w', 'k', 'f', '?', '!', 'n', 'b',
					'u', 'p', 'd', '&', 'y', 't', 'v', 'g', 'z', 's', 'm' },
			{ '&', 'x', 'f', '?', 'm', 'l', '.', 'g', 'i', 'c', 'w', 't', 'r', 'b', 'd', 'j', 'q', 'p', 'o', 'h', 's',
					'v', 'y', ',', 'n', 'e', 'a', '%', '!', 'k', 'z', 'u' },
			{ 'm', 'h', 'i', 'f', 'w', 't', 'd', 'x', 'e', '&', ',', 'b', 'o', '.', 'y', 'k', 'p', 'z', '!', 'c', '?',
					'l', 'q', '%', 'v', 'j', 'g', 'u', 'r', 's', 'a', 'n' },
			{ 'o', '.', 'h', 'l', '&', 'w', 'e', 'a', 'g', 'b', 'p', 's', 'j', 'k', 'd', '?', 'r', 'v', 'i', ',', 't',
					'!', 'c', 'z', 'u', '%', 'x', 'q', 'm', 'y', 'n', 'f' },
			{ '!', 's', '&', 'x', 'h', 'j', 'w', 't', '?', 'f', ',', 'r', 'u', 'o', 'm', 'g', 'l', 'a', '%', 'k', '.',
					'p', 'q', 'i', 'v', 'z', 'n', 'b', 'd', 'y', 'e', 'c' },
			{ 'i', 'x', 'q', '%', 'f', 'p', 'z', 'w', 'a', '?', ',', 'r', 'e', 'l', 'o', 'u', 'm', '!', 'd', 'b', '&',
					'n', 't', '.', 'h', 's', 'k', 'j', 'v', 'y', 'c', 'g' } };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tool window = new tool();
					window.frame.setVisible(true);
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
		int keyCount = 100;
		for (int y = 0; y < keyCount; y++) {
			System.out.print("{");
			temp = shuffle(alphebet);
			for (int i = 0; i < temp.length; i++) {

				System.out.print("'" + temp[i] + "'");
				if (i != temp.length - 1) {
					System.out.print(",");
				}
			}
			System.out.print("}\n");
			if (y != keyCount - 1) {
				System.out.print(",");
			}
		}
		System.out.print("{");
		for (int i = 0; i < keyCount; i++) {
			String keyString = "";
			Random rnd = new Random();
			for (int j = 0; j < 3; j++) {
				keyString = keyString + mixedUps[rnd.nextInt(mixedUps.length)][rnd.nextInt(alphebet.length)];
			}
			System.out.print("\"" + keyString + "\"");
			if (i != keyCount - 1) {
				System.out.print(",");
			}
		}
		System.out.print("}");
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

	private void initialize() {
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
		encrypt.setBounds(250, 12, 155, 29);
		frame.getContentPane().add(encrypt);

		deEncrypt.setBounds(250, 49, 155, 29);
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
		Out.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Out.setLineWrap(true);

		Out.setEditable(false);
		Out.setBounds(422, 85, 388, 393);
		frame.getContentPane().add(Out);

		JTextArea In = new JTextArea();
		In.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String inString = In.getText().toLowerCase();
				Out.setText("");

				if (deEncrypt.isSelected()) {
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = deEnccrypt(temp);
					}
					Out.setText(temp);
				} else {
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = encrypt(temp);
					}
					Out.setText(temp);
				}
			}
		});
		In.setLineWrap(true);
		In.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		In.setBounds(15, 85, 388, 393);
		frame.getContentPane().add(In);
	}

	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();
		int choosen = rndRandom.nextInt(3);
		String outString = keys[choosen];
		char[] keyToUse = mixedUps[choosen];
		for (int i = 0; i < inString.toCharArray().length; i++) {
			if (inString.toCharArray()[i] != ' ') {
				outString = outString + Out.getText() + keyToUse[findInArray(alphebet, inString.toCharArray()[i])];
			} else {
				outString = outString + " ";
			}
		}
		return outString;
	}

	String deEnccrypt(String in) {
		String out = "";
		char[] keyUsed = mixedUps[findInArrayString(keys, in.substring(0, 3))];
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {
				out = out + alphebet[findInArray(keyUsed, in.toCharArray()[i])];
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
