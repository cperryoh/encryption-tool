
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
import javax.swing.JScrollPane;

public class tool {
	// please note that spaces are ignored in the encryption process an spaces that
	// are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	char[] temp;
	boolean consumed;
	int level = 200;
	// each 'coordinates' to a randomized version of the alphabet
	String[] keys = { "u.n", "dzp", "pg,", ".a?", "bgo", "vty", "ivz", "hdc", "c?g", "pgm", "vd,", "l!x", "gl?", "l?s",
			"c?p", "igt", ",l,", "dqc", "xwk", "o!o", "zqd", "kyp", "nep", "yqj", "epq", "h.t", ",ig", "tpb", "iib",
			"mff", ".tb", ".xw", ",k?", "!at", "mr,", "t.!", "&.p", "pg!", "aqn", "fyj", ",x.", "tmt", "ukg", "imy",
			"%nh", "%i&", "ezj", "ten", "uoa", "gw,", "nrs", ".%f", "l,w", "bah", "jrk", "wez", "isn", "ezz", "cwz",
			"jy%", "%lq", "dr,", "zm.", "hsf", "d?x", "oos", "?v%", "oay", "puf", ".o.", "ejg", "vsb", "inm", "kzu",
			"riw", ".lr", "wnt", "wki", "hlp", "!&f", "jki", "gkn", "yl.", "&h%", "mwi", "sfp", "l.m", "sem", "ziw",
			"v,a", "va,", ",zu", "l.r", "dzh", "lif", "ykc", "d,%", "enp", "xhn", "e&!" };
	// list of accepted characters to scramble
	char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!' };
	// current scrambled list
	char[][] mixedUps = {
			{ 'b', 'z', 's', 'i', 'p', '.', 'f', 'g', '?', 'l', 'w', 'r', 'k', 'm', ',', 'j', 'u', 'a', 'y', 'd', 'v',
					'%', '&', 'o', 'q', 't', 'c', 'e', 'h', '!', 'n', 'x' },
			{ 'h', 'm', 'r', 'g', 'i', 'z', 'w', 'a', 'p', 'u', 'v', '?', 's', '&', 'e', '.', 'l', 'o', 'q', 'x', 'y',
					'c', '!', 'd', 'f', 'k', ',', 'j', 'n', 'b', '%', 't' },
			{ 'i', 'y', 'j', 'a', '&', 'c', 'u', 'v', 's', 'w', 'l', 'g', 'h', '?', 't', 'z', 'q', 'n', 'k', 'f', 'o',
					'r', '%', 'p', 'b', ',', 'd', 'x', 'e', '!', 'm', '.' },
			{ 'z', 'e', 'q', 'n', 'u', 'k', 'c', 'r', 'a', 'w', 'x', 'j', '!', 'f', 'm', 'i', 'd', '&', '%', 'l', 'h',
					'b', ',', '?', 'p', 'o', 's', 'y', 'g', 'v', '.', 't' },
			{ '!', 'r', 'e', 'h', 'p', 'y', 'c', '&', 'k', 'b', 'f', 'o', 'm', 't', '%', 'n', 'i', 's', 'v', '?', 'j',
					',', 'q', 'l', 'g', 'x', '.', 'z', 'u', 'w', 'a', 'd' },
			{ 'c', 'y', 'k', '!', 'r', 'b', 'o', 'a', 'e', 'n', 't', 'v', '%', 'q', '?', 'f', 'm', 'j', 'i', 'w', 'z',
					'p', '&', 'l', '.', 'g', 's', 'd', 'x', 'u', ',', 'h' },
			{ 'm', 'f', '?', 'x', 'b', 'n', 'h', 't', 'j', 'p', 'z', 'l', '%', 'u', '.', 'r', 'o', 'i', 'c', 'q', 'd',
					's', 'v', 'g', 'y', 'e', 'a', 'k', ',', '&', 'w', '!' },
			{ 'm', 'a', 'f', 'b', '?', 'v', 's', '&', 'h', '.', ',', 'q', 'x', 'e', 't', 'z', 'r', 'k', 'u', 'n', 'i',
					'p', '%', 'c', 'g', 'w', 'o', 'l', 'd', '!', 'y', 'j' },
			{ 'q', 'p', 'e', 'a', 'f', 'g', 'y', 'j', 'l', '%', 'o', 'r', 't', 'b', 'k', 'n', 'u', 'd', '.', 'h', 'v',
					'i', '!', 's', 'w', 'm', 'z', '&', 'c', '?', ',', 'x' },
			{ 'g', 's', 'u', '?', 'z', 'a', 'l', '.', 'y', 'n', 'd', 'h', 'k', 'v', 't', 'q', 'b', 'w', 'c', 'p', ',',
					'!', 'm', 'r', 'i', 'x', 'f', 'o', '&', 'j', 'e', '%' },
			{ 'd', '?', 'r', 's', 'm', 'n', 'z', '%', 'g', 'k', '!', 'e', '&', 'u', 'h', 't', 'q', 'b', ',', '.', 'c',
					'o', 'v', 'i', 'f', 'x', 'l', 'a', 'p', 'y', 'w', 'j' },
			{ 's', 'c', 'z', 'q', 'v', 'x', '%', 'i', 'e', 'b', 'r', 'o', 't', '&', 'u', 'g', 'y', '?', '.', ',', 'k',
					'd', 'l', 'p', 'f', 'j', 'w', 'h', '!', 'a', 'n', 'm' },
			{ 'z', 'q', 'o', 'c', 'h', '&', '.', 't', 'l', 'w', 'f', 'a', 'n', 'v', 'p', '?', 'm', ',', 'i', 'y', 'k',
					's', 'x', '%', 'e', 'j', 'r', 'd', 'u', 'g', 'b', '!' },
			{ 'z', 'u', 'b', 'm', 'q', 'v', 'i', '?', 'k', ',', 'y', 'a', 'j', '%', 's', 'd', 'o', '&', 'h', 't', 'x',
					'p', 'w', 'l', 'e', '!', 'g', 'c', 'f', 'n', 'r', '.' },
			{ '.', 'v', 'p', 'c', 'k', 'q', 's', 'h', '!', 't', 'a', 'i', 'b', '%', 'n', 'o', 'g', 'd', 'z', '&', 'w',
					'y', 'l', 'r', ',', 'm', 'u', '?', 'f', 'x', 'j', 'e' },
			{ '?', 'a', 't', 's', 'n', ',', 'i', 'f', 'm', 'o', 'k', 'd', 'c', 'w', 'e', 'z', 'r', '&', 'y', 'q', 'p',
					'!', 'b', 'h', '%', 'v', 'l', '.', 'u', 'j', 'x', 'g' },
			{ 's', 'u', 't', '&', 'b', 'x', 'k', 'w', 'm', 'z', 'g', '?', 'd', 'j', 'r', 'i', 'l', 'f', 'o', '!', 'c',
					'%', 'v', ',', 'n', 'h', '.', 'a', 'p', 'e', 'q', 'y' },
			{ 'l', 'm', ',', 'w', 'v', 'b', 'y', '&', 'u', '%', 's', 'o', 'p', 'f', 'c', 'z', 'e', 't', 'q', '.', 'j',
					'i', 'k', '!', 'd', 'a', 'x', 'g', 'r', '?', 'h', 'n' },
			{ 'v', 'h', 'c', 'u', 'o', 't', '%', '?', 'g', 'k', 'a', 's', ',', 'n', '!', 'j', '.', 'm', 'i', 'z', 'd',
					'x', 'r', 'p', 'e', 'y', '&', 'w', 'b', 'l', 'f', 'q' },
			{ 'k', 'b', '!', 'd', 'o', '?', 'l', 't', 'u', ',', 'x', 'a', 'j', 'v', 'p', 'n', 'r', 'h', '&', 'y', 's',
					'q', 'm', 'e', 'z', 'c', '%', 'i', 'f', 'g', 'w', '.' },
			{ 'y', 'x', '&', 'h', 'a', '?', 'l', 's', '%', 'o', '.', 'b', 'r', 'c', 'w', 'i', 'j', 'f', 'd', 't', 'z',
					'!', ',', 'g', 'q', 'p', 'v', 'e', 'u', 'n', 'm', 'k' },
			{ 'w', 'o', 's', 'd', '?', '&', 'b', 'l', 'f', 'j', 'e', 'a', 'r', 'x', 'q', 'u', 'h', ',', '%', 'n', 'k',
					'g', 'i', 'c', 'y', 'm', '!', '.', 't', 'z', 'v', 'p' },
			{ 'f', 'o', 'h', 'c', 'e', 'r', 'x', 'a', 'v', 'k', 'q', '.', 'z', 'l', '%', 'n', 't', 'g', 'i', 'd', ',',
					'u', 'b', 's', 'p', '?', '!', 'j', 'y', '&', 'w', 'm' },
			{ '%', 'l', 'i', 'v', '&', 'q', 'f', '.', 'j', '?', 'm', 'c', 'p', 'd', ',', 'e', 'o', 'g', '!', 'x', 'a',
					'n', 'w', 'b', 'h', 'z', 'y', 't', 'u', 'r', 's', 'k' },
			{ 'n', 'r', '!', 'p', ',', '&', 'z', 't', 'c', 'g', 'w', 'u', '%', 'y', 'x', 'j', 'd', 'q', 'e', 'o', 'm',
					'b', 'f', 'l', 'k', 's', '.', 'i', 'a', '?', 'v', 'h' },
			{ 'w', 'e', 'k', '?', 'p', 'j', 's', 'o', 'f', '.', ',', '&', 'h', 'v', 'c', 'd', 'i', 'a', 'u', 'l', 'g',
					'q', 'y', '!', 'z', 'r', '%', 't', 'b', 'm', 'n', 'x' },
			{ 'r', '%', 'a', 'n', 'l', 'h', 't', 's', 'b', 'p', 'm', 'w', 'y', '.', 'i', '?', 'j', '&', 'v', 'q', 'k',
					'!', 'f', ',', 'g', 'x', 'z', 'u', 'c', 'd', 'e', 'o' },
			{ 'j', 'e', 'r', 'l', 'a', 'f', '?', 'w', 'b', 't', '!', 'u', ',', 'i', 'm', 's', 'h', '.', 'n', '&', 'z',
					'x', 'o', 'g', 'p', 'c', '%', 'k', 'd', 'q', 'v', 'y' },
			{ 'j', 'v', 'w', 'e', 's', 'd', 'm', 'l', '.', 'i', 'p', 't', 'u', 'q', 'r', 'o', 'h', 'a', '!', 'y', 'g',
					'%', 'c', '?', 'x', '&', 'f', 'z', ',', 'b', 'n', 'k' },
			{ 'o', 'f', 'd', 'p', 'w', '%', 'a', 'q', 'x', 'n', 'u', 'k', ',', 'e', '?', 'l', 't', 'c', '!', 's', 'm',
					'b', 'v', 'r', 'h', '&', 'y', 'z', 'j', '.', 'g', 'i' },
			{ '&', 'i', 'z', 'l', 'e', 'd', 's', 'y', 'u', '?', 'm', 'r', 'k', 'f', 'a', 't', ',', 'c', 'v', 'q', 'h',
					'w', 'o', 'x', '!', '%', 'j', 'b', '.', 'g', 'p', 'n' },
			{ 'q', 'i', 'g', 'w', 'y', ',', 'p', 'r', 'j', 'f', 'b', '&', 'v', 'n', 'm', '%', 'a', 'u', '?', 'h', 'o',
					'.', '!', 'z', 'e', 'c', 't', 'k', 'd', 's', 'x', 'l' },
			{ 'g', 'v', 'h', 'b', 'p', 'k', '&', 'q', 'w', '.', 'm', 'y', 't', 'i', 'z', 'd', 's', '!', 'e', '%', 'n',
					'a', 'l', 'o', 'x', ',', 'j', '?', 'c', 'r', 'u', 'f' },
			{ 'j', 'g', 'x', 'q', 'e', 'z', '.', '!', '&', 'n', 'a', 'w', 't', 'u', 's', 'p', 'd', 'c', 'h', 'm', 'b',
					'%', 'l', '?', ',', 'k', 'v', 'f', 'y', 'o', 'r', 'i' },
			{ 't', 'q', 's', 'w', 'j', 'r', 'h', '.', 'o', 'c', '&', 'l', '?', 'f', 'd', '!', 'm', 'u', 'e', 'y', 'b',
					'n', 'a', 'i', 'k', '%', 'z', 'p', 'g', 'v', 'x', ',' },
			{ 'y', 'm', 'l', '!', 'k', '%', 'q', '&', 'h', 'o', 'd', 'z', 'i', 's', 'x', 'j', 'f', 'c', 'r', 'b', 'w',
					'e', 'p', 'v', ',', 'n', '?', 'u', 'a', '.', 'g', 't' },
			{ 'g', 'j', '?', '%', 'e', 'w', 'o', 'd', 'z', 'h', 'm', '!', 'c', 'x', 'k', 'n', 'r', 't', 'b', 'q', 'p',
					'y', 'f', 'u', 'a', 'l', '&', ',', 'v', 'i', 's', '.' },
			{ 'y', 'n', 'b', 'j', 'k', 'z', '?', 'f', 's', '.', 'w', 't', '%', 'e', 'p', 'c', 'h', 'm', 'd', '!', ',',
					'a', 'i', 'r', 'x', 'q', 'u', 'l', 'v', 'o', '&', 'g' },
			{ '?', 'h', '.', 'm', 't', '&', 'b', '%', 'n', 'f', 'r', 'l', 'u', 'j', 'w', 'a', ',', 'x', 'c', 'o', 'e',
					'p', 'z', 's', 'd', 'v', 'i', '!', 'y', 'g', 'q', 'k' },
			{ 'w', ',', '!', 's', 'y', 'x', 't', 'm', '%', 'o', 'h', 'r', 'q', 'l', '?', 'j', 'e', 'p', 'i', 'b', 'k',
					'g', 'v', '.', '&', 'a', 'u', 'n', 'c', 'z', 'd', 'f' },
			{ 'u', 'n', 'o', 'k', '.', 'w', 't', 'i', 'e', '?', 'h', 'g', 'c', 'x', 'd', 'p', '%', 'j', 'q', 'm', 'b',
					'a', 'l', 'z', '&', 'r', 'v', '!', 's', 'y', 'f', ',' },
			{ 'n', 'c', '%', 'e', 'i', 'y', 'b', 'h', 'o', 'f', 'q', '&', 'v', 'd', 'm', 'j', 'l', 'w', 'g', 'z', 'r',
					'.', '!', 'p', 's', 'x', '?', 'a', ',', 't', 'k', 'u' },
			{ 'a', 'r', 'y', 'b', 'm', 'k', 'u', 'g', '&', 'd', 't', 'q', 'p', 'x', 'h', 'f', 's', 'c', 'z', 'v', '?',
					'.', ',', 'w', 'o', '%', 'i', '!', 'j', 'e', 'n', 'l' },
			{ 'n', '.', 'z', 'g', 'q', 'j', 'c', 'f', 's', 'p', '!', 'b', 'h', 'l', 'i', 'u', 'm', 'w', '&', '?', 'y',
					'd', ',', '%', 'x', 'e', 'o', 'r', 'a', 't', 'k', 'v' },
			{ 'i', '%', 'a', 'g', 'w', 'k', 's', 'u', 'b', 'l', 'q', 'j', 'f', ',', 'h', '&', 'n', 'x', 'o', 'v', '.',
					'y', 'm', '!', 'z', '?', 'r', 'p', 'c', 'd', 't', 'e' },
			{ 'g', 'k', 'p', 'r', 'j', '.', 'n', 'f', 'o', 'x', 't', 'e', 'b', '!', 'l', '&', 'q', '?', ',', 'y', 'h',
					'w', 'c', 'm', 'd', 'a', 's', '%', 'u', 'v', 'z', 'i' },
			{ 'x', 'w', 'r', 'm', 'p', 'z', ',', 'j', '.', 'g', 'a', 'f', '&', 'n', 'h', 'b', 'q', 't', '?', 'y', 'v',
					'k', 'e', 'd', '%', 'c', 'i', 'u', 's', 'l', '!', 'o' },
			{ 'o', 'j', '%', 'g', ',', 'h', 'f', 'b', 'p', 'n', 'w', 't', 'q', 'y', 'c', '!', '.', 'm', 'd', 'l', 'k',
					'e', 'x', 'a', 'v', '&', 'z', 's', 'r', 'i', 'u', '?' },
			{ '!', 'b', 'i', 'h', '?', 'g', 'f', '%', 'y', 'w', '&', 't', 'v', 'p', '.', 'e', 'm', ',', 'x', 'j', 'a',
					'o', 'z', 'k', 'n', 'q', 'u', 'l', 'd', 's', 'c', 'r' },
			{ 'g', 'h', 'b', 'y', 'v', '!', 'w', '.', 'p', 'n', '?', 'o', 's', 'e', 'x', 'u', 'k', 'm', 'l', 'c', 'q',
					't', 'i', '%', 'f', '&', 'z', 'j', 'r', 'd', 'a', ',' },
			{ 'p', 'h', 'b', 'c', 's', 'w', 'n', 'q', 'm', 'u', 'f', '%', 'a', '?', 'y', 'x', 'd', 'g', 't', '&', 'r',
					'e', 'j', 'o', 'k', 'z', '!', 'v', 'i', '.', 'l', ',' },
			{ 'l', 'h', 'f', 'e', '!', 'a', 't', 'r', 'c', '?', 'y', 's', 'q', 'd', 'n', 'z', 'j', 'v', '.', 'm', '%',
					'o', 'p', 'i', 'g', '&', 'x', 'u', ',', 'k', 'w', 'b' },
			{ 'd', 'z', 'q', 'g', 'r', 'b', 'f', 'k', 'j', 'l', '!', '&', 'i', ',', 'm', 'w', 'n', '.', 'p', 'o', 'y',
					'%', '?', 'x', 't', 's', 'u', 'v', 'a', 'h', 'c', 'e' },
			{ 's', 'n', 'z', '.', 'a', 'q', 'd', 'y', 'r', 't', ',', 'j', 'l', 'u', 'o', '?', 'x', 'k', '&', '!', 'e',
					'b', '%', 'i', 'g', 'v', 'c', 'h', 'f', 'p', 'w', 'm' },
			{ 't', 'r', 'j', 'a', '%', 's', 'z', '?', 'k', 'x', 'u', '.', 'g', 'p', 'b', 'c', 'y', 'e', 'o', 'h', 'v',
					'd', '&', 'q', 'm', 'l', 'n', ',', '!', 'f', 'i', 'w' },
			{ '%', '&', 'i', 'n', 't', '!', 'b', '.', 'q', 'v', 'j', 'e', 'p', 'h', 'm', 'l', 'r', 'g', 'w', 'y', 'd',
					'c', 'o', 'z', ',', 's', 'f', 'x', 'u', 'a', '?', 'k' },
			{ '.', 'u', 'y', 'v', 'd', ',', 'j', 'i', 'z', 's', 'm', '&', 'p', 'f', 'g', 'o', '%', 't', 'h', 'n', 'l',
					'k', 'c', 'x', 'b', 'a', 'q', 'r', 'w', '?', 'e', '!' },
			{ 'm', 'y', 'k', 'x', 'e', 'i', 'q', '%', 'h', 't', 'b', 'f', '.', 'l', 'n', 'g', 'j', 'd', ',', 'a', 'z',
					'o', 'v', 'u', 'r', 'w', 's', '&', 'c', '!', '?', 'p' },
			{ 'm', 'n', 'a', 'd', 'f', ',', 'b', '.', '?', 'g', 'p', 'q', 'c', 'w', '%', 'h', 'j', 'v', '&', 'y', 'u',
					'o', '!', 'x', 'i', 'l', 't', 'r', 'e', 's', 'k', 'z' },
			{ '%', 'k', 'u', 'x', 'a', 'l', 'h', 'j', 'f', 'm', 'e', 't', 'd', '.', 'o', '!', 'w', '?', 'p', ',', 'z',
					'b', 'r', 'c', 'i', 'n', 's', 'q', 'y', 'g', 'v', '&' },
			{ 'l', '%', 'k', '&', 'm', 'i', 'j', 'e', 'f', 'c', 'd', '!', 'p', 'x', 'h', 's', ',', 'w', 'y', '?', '.',
					'r', 'g', 'n', 'q', 'u', 'b', 'o', 'v', 'z', 't', 'a' },
			{ 'l', 'k', 'w', 'j', 'g', 'n', 'd', 'i', 'p', 'e', 'x', 'm', 'y', '!', 's', ',', '.', 't', 'b', 'a', 'v',
					'h', 'u', '?', 'z', '&', 'r', 'f', '%', 'q', 'c', 'o' },
			{ 't', 'w', 'e', 'g', 'x', 'm', 'y', 'v', 'h', 'f', 'u', 'a', ',', 'k', 'd', '&', '%', 'j', 'b', 'n', 'p',
					'l', '?', '.', 'r', 'o', 's', '!', 'i', 'c', 'z', 'q' },
			{ '%', 'k', 's', 'h', 'a', 'y', 'j', '&', 'n', 'i', 't', ',', 'c', '!', 'm', 'g', 'x', 'b', 'p', 'l', 'd',
					'v', 'w', 'e', 'o', 'f', 'z', '.', '?', 'u', 'r', 'q' },
			{ 'f', 'b', '%', 't', 'j', '?', ',', '.', 'e', 'z', 'm', 'y', '&', 'c', 'w', 'x', 'd', '!', 'i', 'o', 'v',
					'p', 'a', 'n', 'k', 'q', 'u', 'g', 's', 'h', 'l', 'r' },
			{ 's', 'k', 'h', 'a', 'b', 'j', 'n', '!', 'y', 'u', '%', 'i', 't', 'l', '?', 'p', 'x', 'f', 'w', 'g', 'e',
					'o', 'r', '&', '.', 'z', ',', 'd', 'v', 'c', 'm', 'q' },
			{ 'n', 'a', 'y', 'z', 'j', 't', 'i', 'e', 'g', 'w', '?', 'b', 's', 'k', 'q', '!', 'p', 'x', 'u', '&', 'c',
					'm', 'l', 'r', '.', 'd', 'v', ',', 'o', '%', 'f', 'h' },
			{ 'g', 'e', 'h', 't', 'm', 'u', 'x', 'c', 'r', 'v', 'i', 'b', 'f', 'k', '&', 'd', '.', 'l', 'q', 'a', 's',
					'o', ',', 'p', 'y', '%', 'j', 'w', '!', 'z', 'n', '?' },
			{ 'j', ',', '&', '.', 'v', '!', 'u', 'b', 'f', 'l', 'q', 'r', 'm', 'y', '%', 'i', 'h', 'n', 'o', 'k', 'g',
					'a', 'p', 'z', 'w', 'c', 'e', 't', 'x', 'd', 's', '?' },
			{ 'f', 'j', 'h', 'u', '!', 'i', 'p', 'm', 'o', '?', 'r', 't', 'x', 'b', 'v', 'c', 'n', '&', ',', 'a', 'e',
					'd', 'y', '.', 'l', 'g', 'k', 'q', '%', 'z', 's', 'w' },
			{ 'j', '!', 'r', 'i', '&', 'u', 'f', 'n', 'x', 'd', 'l', 'e', 'v', 'g', 'w', 'q', 'c', 'm', '.', ',', '%',
					'y', 'h', 'z', 'k', 'a', 'b', 't', '?', 'o', 'p', 's' },
			{ 't', 'y', '%', 'c', '&', 'w', 'h', ',', '.', 'b', 'm', 'k', 'i', 'p', 'v', '!', '?', 'd', 'z', 'u', 'a',
					'l', 'f', 'e', 'x', 'n', 'j', 'r', 'o', 's', 'q', 'g' },
			{ '&', '%', 'h', 'r', 'j', 'k', 'i', 'b', 'u', 'm', 'p', 'z', 'q', '?', 'o', '.', 's', 'f', 'g', 'n', ',',
					'w', 't', 'c', 'x', 'd', 'a', 'y', '!', 'e', 'v', 'l' },
			{ '.', 'c', 'z', 'u', 'o', 'h', 'a', 'q', 'f', 'v', '&', 'n', 't', 'l', 'm', 'p', 'd', 'x', 'r', 'g', '%',
					',', '!', 's', 'e', 'y', 'w', 'k', 'j', 'i', '?', 'b' },
			{ '.', ',', 'f', 'w', 'q', 's', 'z', 'v', 'y', '%', 'b', 'm', 'x', 'i', 'j', 'u', 'a', 'p', 'c', 'l', 'e',
					'o', '!', 'n', 'r', 'k', 't', '&', 'h', '?', 'd', 'g' },
			{ '.', 's', 'w', 'y', '%', 'z', 'p', 'k', 'b', 'q', 'l', 'x', 'c', 'o', 'j', 'u', 'f', 't', 'e', 'n', '!',
					'h', 'd', 'r', ',', 'v', '&', 'm', 'g', 'a', 'i', '?' },
			{ 'x', 's', 'r', 'l', 'k', 'm', 'o', 'q', 'a', 'c', 'v', 'w', 'p', 'z', 't', 'y', 'b', ',', 'u', '!', '.',
					'&', 'g', 'e', 'j', 'i', 'h', '?', '%', 'n', 'f', 'd' },
			{ 'd', 'r', 'e', '.', 'k', ',', '&', 'c', 'g', 'z', 'i', 'm', 'n', 'o', 'a', 'u', 'w', 'b', 'h', 'j', '%',
					's', '?', '!', 'p', 'y', 't', 'x', 'q', 'f', 'l', 'v' },
			{ '%', 'o', 'r', 'd', 'i', ',', '!', 'n', 'z', 'j', 't', 'p', 'u', 'b', 'x', 's', 'q', 'h', 'g', '&', 'y',
					'.', 'f', 'l', '?', 'm', 'a', 'e', 'c', 'w', 'k', 'v' },
			{ 'i', 'g', 'm', '!', 'q', 'u', ',', '.', 'b', 'v', 'o', 't', 'y', 'h', 'l', 'p', 'k', 'c', '&', 'r', 'f',
					'n', '%', 'd', '?', 'w', 'z', 'j', 'a', 's', 'e', 'x' },
			{ '.', 's', 'k', ',', 'h', 'w', 'a', 'q', 'o', 'p', 'g', 'z', 'j', 'd', 'e', '!', '?', 'x', 'n', 'v', 'f',
					'm', 'u', 'l', 'y', 'b', 'i', 'r', '%', 'c', '&', 't' },
			{ 'h', 'k', 'w', 'a', '&', 'r', 'q', 'f', 'l', 'u', 'p', 'v', 'i', 'o', '?', 't', 'n', 'z', '!', '.', 'e',
					'y', 'm', 'd', 'x', 'g', 'j', 's', 'b', 'c', '%', ',' },
			{ ',', 'o', 'n', 'y', 'u', 'v', 'z', 'p', 'a', 'f', '%', 'm', '!', 'q', 'w', 'x', 'l', 'g', 'h', 's', 'd',
					'&', 'j', 'k', '.', 'e', '?', 'c', 't', 'b', 'i', 'r' },
			{ 'x', 'z', '&', 'b', 'a', 'm', 'd', 'v', 'o', 'g', 'c', 'l', 'e', 'k', 't', 'r', 's', 'f', '.', 'p', '!',
					'i', 'w', 'u', 'h', '%', 'j', 'y', '?', 'n', 'q', ',' },
			{ 'r', '&', 'u', 'x', 'g', 'b', 'v', 'k', 't', '.', 'p', 'z', 'o', '?', 'f', 'e', 'm', '!', '%', 'd', ',',
					's', 'h', 'i', 'q', 'n', 'w', 'c', 'j', 'a', 'l', 'y' },
			{ '.', 'c', 'q', 'g', 'i', 'e', 't', 'h', 'o', '%', 'l', 'w', 'z', '?', 's', 'x', 'b', '&', 'v', 'm', 'n',
					'k', 'u', 'y', 'd', ',', 'p', 'a', '!', 'r', 'j', 'f' },
			{ 'b', '?', 'a', ',', 'z', '&', 'f', 'k', '%', 'r', 'n', 'h', 'd', 'o', 'q', '.', 'c', 'u', 'x', 'i', 'l',
					'j', 's', 'p', 't', 'w', 'y', 'g', 'e', 'v', 'm', '!' },
			{ 'q', 'r', 'z', 'd', '%', '.', 's', 'i', 't', 'h', 'f', 'k', '?', 'b', '&', 'l', 'c', 'u', 'x', 'j', 'p',
					'!', 'n', 'w', ',', 'v', 'e', 'a', 'o', 'g', 'y', 'm' },
			{ 'z', 's', 'h', '.', 'r', 't', 'k', '&', 'w', 'f', 'i', 'b', 'y', 'm', 'u', '%', ',', '!', 'd', '?', 'q',
					'a', 'x', 'p', 'j', 'e', 'g', 'v', 'o', 'c', 'l', 'n' },
			{ 't', 's', ',', 'g', '?', 'o', '%', 'f', 'i', 'j', 'b', '.', 'q', 'v', 'r', '!', 'w', 'z', 'p', 'm', 'e',
					'n', 'd', 'k', '&', 'l', 'y', 'c', 'x', 'u', 'a', 'h' },
			{ 'k', 'u', '%', 'o', 'c', 'j', 'i', 't', 'r', 'q', 'e', 'z', '?', 'm', 'w', 'd', '.', 'y', 'p', 'h', 'g',
					'!', 'n', 'x', 'l', ',', 'a', 'b', 's', 'v', 'f', '&' },
			{ 'i', 's', 'u', 'd', 'w', 'a', 'x', 't', 'm', 'e', 'z', 'o', 'p', 'b', 'g', 'j', 'r', 'f', 'y', 'k', 'n',
					'%', '!', '&', '?', 'v', ',', 'c', '.', 'l', 'q', 'h' },
			{ 'h', 'f', 'r', 'i', 'w', 't', 'a', 's', 'g', '%', '.', 'j', 'd', 'm', 'n', 'v', '!', 'z', 'u', 'p', 'e',
					'&', 'c', 'x', 'o', ',', 'k', 'y', 'l', 'b', '?', 'q' },
			{ 'w', 't', 'x', 'q', ',', '?', 'd', 'a', 'e', 'v', 'm', '.', 'p', '!', 'n', 'r', 'z', 'y', '%', 'k', 'u',
					'c', '&', 's', 'b', 'h', 'f', 'i', 'g', 'o', 'j', 'l' },
			{ '?', 'i', 'g', 'x', 'e', 'n', '.', 'u', 'v', 'r', 'c', 'p', '%', 'y', 't', 'b', '&', 'm', ',', 'a', 'j',
					'h', 'f', 's', 'd', 'l', '!', 'k', 'o', 'q', 'w', 'z' },
			{ 'k', 'x', 'p', ',', '.', '%', 'g', '!', 'a', 'b', 'e', 'n', 'o', 'y', 'v', 's', '&', 'z', 'w', 'r', 'l',
					'm', 'h', 'i', 'd', 'c', 'u', 'f', 'j', 't', 'q', '?' },
			{ 'c', 'r', 'd', 't', 'a', 'f', '!', 'v', 'y', ',', '.', 'l', 'n', 'z', 'o', 'p', 'j', 'k', 'q', '%', 'm',
					'i', 'w', 'u', 'e', 'x', '&', 's', 'b', '?', 'g', 'h' },
			{ '%', '?', 'c', 'p', 'r', '!', 'e', 'v', '&', 't', 'u', 'k', '.', 'j', 'q', 'n', 'y', 'a', ',', 'd', 'o',
					'w', 'x', 'h', 'z', 'i', 'l', 'b', 'g', 'f', 's', 'm' },
			{ 'c', ',', 'k', 'n', 'f', 'b', 'm', 'w', 'l', '!', '?', 's', 'o', 'e', 'y', 't', '.', 'g', 'p', 'h', 'x',
					'r', 'u', 'd', '&', 'v', 'a', '%', 'j', 'z', 'i', 'q' },
			{ 'o', 'n', 'x', '?', 'l', 'y', 'g', 't', '.', 's', 'r', 'b', '&', 'p', 'a', 'k', 'd', 'v', '!', 'z', 'i',
					'w', 'c', 'j', 'e', ',', 'q', 'f', 'h', 'u', 'm', '%' } };

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
		// outputs a new set of keys and scrambled characters to use whenever the
		// program opens
		int keyCount = 100;
		for (int y = 0; y < keyCount; y++) {
			System.out.print("{");
			temp = shuffle(alphabet);
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
				keyString = keyString + mixedUps[rnd.nextInt(mixedUps.length)][rnd.nextInt(alphabet.length)];
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 85, 365, 369);
		frame.getContentPane().add(scrollPane);

		JTextArea In = new JTextArea();
		scrollPane.setViewportView(In);
		// when key down either decrypt or encrypt
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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(409, 85, 405, 369);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(Out);
		Out.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Out.setLineWrap(true);

		Out.setEditable(false);
	}

	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();
		int choosen = rndRandom.nextInt(keys.length);
		String outString = keys[choosen];
		// chooses the key that will be used for this level of encryption
		char[] keyToUse = mixedUps[choosen];
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
