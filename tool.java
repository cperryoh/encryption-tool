
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
	//please note that spaces are ignored in the encryption process an spaces that are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	char[] temp;
	int level = 5;
	String[] keys = { "tca", "xzj", "rh,", "j&c", "kgl", "ygd", ",xq", "gqr", " lj", "qvn", "t&y", "plx", "dhf", "h?l",
			"vie", "yol", "s?b", "&& ", "vjz", "gw ", "fuq", "scg", "pyv", "us&", "gzt", "iwv", "&zt", "wrw", "ove",
			"qqe", "hh?", "lya", "nea", "ica", "t%d", "d?s", "vry", "dqm", "hac", " %&", "zts", "zam", "nfz", "tge",
			"pkj", "pzn", "v&a", "jlq", "rw%", " nl", "jlq", "tzh", "n%.", "spm", "h%,", "lal", "fvd", "bjp", "wpd",
			"ym?", "h,u", "ogi", "&ef", "q,c", "x?q", "tuq", "vam", "rp,", "rds", "vny", "&qb", "m r", "psh", "uvr",
			"qbn", " ke", "zgd", "ngp", "vkk", "xth", "a?f", "zck", "pcv", "snh", "?mz", "wv.", "?zs", "?bt", "%lg",
			"sre", "fyf", "dil", "ax&", "ij,", "nsn", "ta%", ".r?", "p,e", "gt&", "bsw" };
	char[] alphebet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%' };
	char[][] mixedUps = {
			{ 'q', 'p', 'j', 'v', '?', 'm', 't', 'i', 'b', 'k', 's', 'g', 'u', '%', 'f', 'a', '&', 'd', 'r', 'y', 'e',
					'z', 'o', 'l', '.', 'x', 'c', 'h', ',', 'n', 'w' },
			{ 's', 'z', 'r', 'n', 'g', 't', '%', 'q', '&', 'l', 'j', 'v', 'f', 'u', '.', 'w', 'd', 'h', 'a', 'i', ',',
					'b', 'e', 'm', 'k', '?', 'x', 'c', 'y', 'o', 'p' },
			{ 'n', 'p', 'r', 't', 'm', 'a', 'd', '%', '&', 'w', 'c', 'j', 'h', 'o', 'u', 'b', 'l', 's', 'x', 'e', 'k',
					'q', 'z', '.', 'g', 'y', '?', 'v', 'i', 'f', ',' },
			{ 'm', 'v', 'j', 'c', 'q', 'y', 'k', 'z', 'd', 'n', '.', '?', 't', 'o', 'f', 's', 'l', 'e', 'u', 'r', 'p',
					'b', 'x', 'h', '%', '&', 'g', ',', 'i', 'w', 'a' },
			{ 'i', '.', 'm', 'l', 'k', 'v', 'n', 'p', 'a', 'x', 'u', 'h', 'z', 'f', 'y', '&', 'r', ',', 'b', 'g', '%',
					'o', '?', 'w', 'd', 'c', 't', 'e', 'q', 'j', 's' },
			{ '&', 'q', ',', 'u', 'k', 'w', 'm', 'g', 'r', 'z', '.', 'e', '?', 'c', 'n', 'f', 'o', 'b', 'p', 'x', 'l',
					't', 'h', 'y', 's', '%', 'v', 'j', 'a', 'd', 'i' },
			{ 'm', 'a', 'c', 't', 'r', 'o', 'l', 's', '.', '?', 'q', 'v', 'k', 'i', 'x', '&', 'e', 'n', 'z', 'u', 'p',
					'd', ',', 'w', 'g', '%', 'j', 'f', 'b', 'y', 'h' },
			{ ',', 'i', 'u', '.', 'h', 'z', 'd', '&', 'q', 'k', 'j', 's', '%', 'x', 'm', 'c', 'w', 't', 'v', 'p', 'n',
					'o', '?', 'y', 'f', 'g', 'l', 'b', 'r', 'a', 'e' },
			{ 'u', 'g', 'x', 'e', 'o', '.', 'z', '&', 'p', 'b', 'r', 'k', 'n', 'w', '?', ',', 'l', 'm', 's', 'h', 't',
					'a', 'c', 'f', 'i', 'v', 'j', 'q', '%', 'd', 'y' },
			{ 'j', 'v', 'c', 'k', 'u', '&', 'g', 'n', 'b', 'l', 'e', '%', 'z', 'a', 'o', 'p', 'y', 'r', '.', '?', 'q',
					't', 'i', 'h', 'd', 'w', 's', 'x', ',', 'f', 'm' },
			{ 'm', 'w', '?', 'g', '&', 'h', 'c', 'i', 'o', '%', 'q', 'r', ',', 'n', 'x', 'z', 'u', 'b', 'd', 'e', 'p',
					'v', 'y', 's', 't', 'f', '.', 'a', 'j', 'l', 'k' },
			{ 'a', '.', 'i', ',', 'w', 'd', 'x', '&', 'j', 's', 'b', 'g', 'k', 't', 'm', 'e', '?', 'o', 'p', 'r', 'l',
					'c', 'h', 'q', '%', 'z', 'v', 'y', 'u', 'n', 'f' },
			{ 'l', 'r', '%', 'x', 'n', 'z', 'p', 'g', 'a', '.', 'j', 't', 'k', 'm', 'e', 'q', 'f', 'u', 's', '?', 'i',
					',', 'y', 'b', '&', 'o', 'h', 'c', 'v', 'w', 'd' },
			{ 'w', 'a', 'q', 'v', 'r', 'x', 'y', 'b', 'z', ',', 's', 'c', 'n', 'i', '?', '&', 'u', 'o', 'l', 'k', 'f',
					'.', 'e', '%', 'd', 't', 'm', 'h', 'p', 'g', 'j' },
			{ 'h', '?', 't', '&', 'z', '%', 'k', 'e', 'a', 'm', 'u', 'g', 'l', 'f', 'i', 'r', 'c', 's', 'x', ',', 'y',
					'n', 'v', 'q', '.', 'p', 'b', 'd', 'j', 'w', 'o' },
			{ 'b', ',', 'o', 'w', 'd', 'n', 'm', 't', 'l', 's', 'q', 'i', 'z', 'u', 'p', 'x', 'f', '?', '.', 'c', 'v',
					'e', 'k', 'g', 'j', 'y', '&', '%', 'r', 'a', 'h' },
			{ 'f', 't', 'w', 'c', '?', 'm', 'd', 'y', 'i', 'o', 'a', 'h', 'g', '.', 'v', 'p', 'b', 'z', 'e', 'q', ',',
					'x', 'r', 'l', '&', 's', 'k', 'n', 'j', '%', 'u' },
			{ 'e', 'm', 'y', 'x', 'g', 's', 'd', 'r', 'w', 'u', 'k', '?', 'i', 'b', 'n', 'z', 'v', 'q', 'o', '&', 'f',
					'.', 'c', 't', '%', 'j', ',', 'l', 'a', 'p', 'h' },
			{ '%', 'k', 'e', 'p', 'n', 'o', 'b', 'c', 'j', 't', 's', 'y', 'z', ',', 'v', 'r', '&', 'q', 'h', 'l', 'd',
					'g', 'm', 'x', 'f', 'a', 'w', '?', '.', 'u', 'i' },
			{ 'n', 'e', 'z', 'a', '?', 'b', 'j', 'p', 'h', 'k', 's', 'm', 'r', 'l', 'g', '.', 'f', ',', 'd', 'w', 'u',
					'&', 'v', 'y', 'q', 't', 'i', '%', 'x', 'c', 'o' },
			{ 'y', 'h', 'p', 'q', 'a', 'b', ',', 'r', '%', 't', 'u', 'd', 's', 'o', 'n', 'g', 'm', 'z', 'j', '.', '&',
					'i', 'c', '?', 'f', 'k', 'x', 'w', 'v', 'l', 'e' },
			{ 'r', ',', 'c', 'y', 'a', 'w', '%', 'n', '?', 'i', 'd', 'e', '.', 'b', 's', 'p', 'g', 'v', 'q', 'm', 'f',
					'u', 'l', 'o', 'h', 't', 'x', 'z', 'j', '&', 'k' },
			{ 'q', 'w', '?', '&', 'o', 't', 'y', 'e', 'b', '%', 'x', 'v', 'p', 'a', 'r', 'm', 'z', ',', 'c', '.', 'g',
					'f', 'n', 'j', 'l', 'k', 'h', 'd', 'i', 'u', 's' },
			{ 'h', 'g', 'o', 'l', 'y', 'i', 'd', 'z', 'w', 's', 'q', 'e', 'a', '?', 'c', 'u', 'k', 'b', 'x', 'j', '&',
					'n', 't', 'm', 'f', '%', ',', '.', 'p', 'v', 'r' },
			{ 'd', 'h', 'c', '&', 'a', 'e', 's', 'v', 'b', '.', ',', 'g', 'o', 'x', 'u', 'w', 'm', 'k', 'l', 'f', '?',
					'j', '%', 'q', 'z', 't', 'n', 'i', 'p', 'r', 'y' },
			{ 'c', ',', 'g', 'r', 'i', 'y', '.', 'm', '%', 'w', 't', '&', '?', 'p', 'l', 'v', 'o', 'n', 'f', 'q', 'd',
					'u', 'e', 'b', 'a', 'h', 'x', 's', 'k', 'z', 'j' },
			{ 'f', 'c', 'k', 'o', ',', 'p', 'z', 'h', 't', 'w', 'm', 'v', 'j', 'a', 'q', 'i', 'l', 'e', 'd', 'u', 'n',
					'&', 'y', '?', 'r', '%', 'b', 's', 'x', '.', 'g' },
			{ 'u', '.', 'o', ',', '%', 't', 'y', 'f', 'm', 'z', 'k', 'b', 's', 'x', 'n', '&', 'v', 'p', 'l', 'g', 'e',
					'h', 'r', 'i', 'w', 'd', 'c', 'q', 'j', 'a', '?' },
			{ 'q', 'i', ',', 'o', 'b', 'v', 'x', 'j', 'r', 'e', 't', 'z', 'c', 'g', 'n', 'p', 'a', '%', 'u', 'f', '.',
					's', 'y', 'h', 'k', '?', 'm', 'l', 'd', '&', 'w' },
			{ 'q', 'h', 't', 'u', 'g', 'w', 'c', 'i', 'v', 'n', '?', '.', 'x', 'p', '%', 'z', 's', '&', 'f', 'l', 'j',
					'k', 'b', 'd', 'm', 'a', 'r', 'y', 'o', 'e', ',' },
			{ 'v', 'i', ',', 'q', 'c', 'j', 'p', 'y', 'l', '.', 'a', 't', 'g', 's', 'b', 'u', 'r', '&', 'e', 'o', 'h',
					'%', 'z', '?', 'd', 'x', 'n', 'k', 'm', 'f', 'w' },
			{ 'm', ',', 's', 'j', 'n', 'h', 'w', 'l', 'q', 'i', 'e', 'o', 'v', '&', '%', 'y', 'g', 'r', 'z', 'k', 'd',
					'u', 'f', 'a', 't', 'p', '?', 'c', '.', 'x', 'b' },
			{ 'n', 'u', 'k', 'h', 'b', '%', 'a', 'g', 'c', 'r', 'w', 'p', 'x', '?', 'o', 'l', 'm', 'd', 's', '.', 'y',
					'&', 'q', ',', 'f', 'v', 'j', 'i', 'z', 't', 'e' },
			{ 'n', 'r', 't', 'a', 'e', 'q', '%', 'm', 'p', 'c', '.', ',', 'y', 'z', 's', 'd', 'g', 'l', '&', 'f', 'w',
					'o', '?', 'h', 'u', 'j', 'x', 'i', 'b', 'k', 'v' },
			{ 's', 'f', ',', 'z', 'o', '.', 'h', 'y', 'x', 'e', 'b', 'd', 'r', '%', 'm', 'j', 'w', 'l', '?', 'a', 'i',
					'c', 'q', 'k', 'u', 'g', 'n', 'p', '&', 'v', 't' },
			{ '?', 'r', 'w', 'c', 'x', 'd', 'p', 'i', 'k', 'v', '.', '%', 'a', 's', ',', 'q', 'f', 'u', 'j', 'h', 'o',
					'b', 'y', 'l', 'm', 'e', '&', 'z', 'g', 't', 'n' },
			{ 'f', 'l', 'v', 'k', 'z', 'w', 't', 'j', 'n', 'm', 'g', 'h', 'o', 'p', 'y', 'a', '%', 'r', 'c', 'i', 'e',
					'x', '&', 'q', ',', '.', 'u', '?', 'b', 's', 'd' },
			{ 'n', 'w', 'u', 'g', 'k', 's', 'd', 'r', 'c', 'p', 'y', 'h', 'i', 'z', 'a', 'o', '.', 'b', 'j', '&', 'x',
					'?', 'e', '%', 't', 'l', 'q', 'f', ',', 'm', 'v' },
			{ 'b', 'm', ',', '?', 'p', 't', 'r', 'i', 'k', 'o', 'l', 'c', 'x', 's', 'q', '%', 'f', '&', 'w', 'e', 'a',
					'h', 'j', 'v', 'n', 'z', '.', 'g', 'y', 'd', 'u' },
			{ 'b', 'p', 's', ',', 'i', 'e', 'v', 'j', 'm', 'k', 'w', '&', 'r', 'q', '?', 'f', 'c', 'y', 'g', 'z', 't',
					'h', 'u', '%', '.', 'o', 'l', 'n', 'd', 'a', 'x' },
			{ 'e', 'o', 'n', '?', 'h', 'y', 'b', 'l', 'a', 'd', 'p', 'q', 'j', '.', 'z', 'c', 'w', 'r', 'm', '&', 'u',
					't', 'f', 'x', 'g', 'k', 'v', 's', '%', 'i', ',' },
			{ 'w', 'v', 'f', 'a', 's', 'h', 'x', 'j', 'i', 'e', 't', 'y', 'c', 'u', 'g', 'd', 'r', 'l', 'p', ',', 'n',
					'?', 'b', '%', 'm', 'z', 'o', 'k', 'q', '&', '.' },
			{ 'h', 'k', 'z', 'p', 'l', 's', 'j', 'y', 'q', 'o', '?', 'c', 'v', 'n', 'r', 'w', 'e', 'g', 'f', '&', '.',
					'x', ',', 'u', 'i', 'a', '%', 't', 'b', 'm', 'd' },
			{ 'k', '.', 'b', 'q', ',', 'y', 'g', 'd', 'w', 'a', 'v', 'j', 'f', 'r', 's', 'n', 'c', '%', 'e', 'h', 'x',
					'&', 'u', 'p', 'z', 't', 'o', 'i', 'm', 'l', '?' },
			{ 'w', 'a', 'h', 'x', 'r', 'j', 'l', 's', 'i', 'g', '.', 't', 'y', ',', 'p', 'k', 'e', 'd', '%', 'o', '?',
					'u', 'v', 'z', 'c', 'q', 'b', 'f', 'm', '&', 'n' },
			{ 'j', 'z', 'd', 'k', 'i', 'n', 's', 'x', 'g', 'f', 'y', '.', 'm', '?', 'q', 'b', '%', 'o', 'c', 'a', 'e',
					'v', 'p', 'r', 'u', 'w', ',', 'l', 'h', '&', 't' },
			{ 'v', '.', 'e', 'z', 'w', '&', '%', ',', 'f', 'k', 'g', 'a', 'o', 'm', 'y', 'p', 'c', 'l', 'u', 'q', 'j',
					'h', 'b', 'd', 't', 'n', 's', 'x', '?', 'r', 'i' },
			{ 'z', 'd', 'f', '?', 't', 'i', ',', 'k', 'l', 'o', 's', 'm', 'w', 'e', 'n', '.', 'u', 'p', 'h', 'c', 'g',
					'q', 'j', 'y', '&', '%', 'x', 'r', 'v', 'b', 'a' },
			{ 'h', 'r', ',', 'j', 'f', 'p', 'o', '.', 'g', '?', 'm', 'b', 'n', 'e', 'l', 'u', '&', 'a', 'z', 'q', '%',
					'x', 'i', 'd', 'c', 'v', 'y', 'k', 's', 't', 'w' },
			{ 'y', ',', 'u', 'f', 's', 'l', 'b', '?', 'v', 'h', 'j', 'e', 'w', 'a', 'k', 'n', 'i', 't', '%', 'q', 'z',
					'x', 'm', 'd', '.', 'p', 'g', 'o', 'r', '&', 'c' },
			{ 'n', 'j', 'f', 't', 'g', 'a', 'e', '&', '.', 'u', 'b', 'i', 'v', '%', 'z', 'r', 'c', 'k', 'd', 'q', '?',
					',', 's', 'l', 'o', 'p', 'x', 'm', 'y', 'w', 'h' },
			{ 'q', 'm', 'p', 'x', 'r', 'o', 'n', 'i', 'f', '&', '.', 's', 'u', 'z', 'c', 'k', 'l', 't', '%', 'g', 'w',
					'e', 'v', '?', 'j', 'h', ',', 'y', 'a', 'd', 'b' },
			{ 'u', 'j', 'm', 'z', 'i', '.', 'o', 'd', 'h', 'x', 'c', 'k', 't', 'w', 'l', 's', 'n', 'g', 'q', 'r', 'v',
					'%', 'y', 'p', 'a', 'b', ',', '&', 'f', 'e', '?' },
			{ 'j', 'n', 'u', 'e', 'z', 'x', 'v', 'c', 'm', 'l', 'd', ',', 'a', '?', '&', 't', 'p', '%', 'f', 'r', 'b',
					'y', 'w', 'q', '.', 'k', 'h', 'o', 'g', 'i', 's' },
			{ 'y', 'x', 'g', 'm', '&', 'z', 's', 'a', 'u', ',', 'h', 'f', '%', 'v', 'o', 'l', '.', 'n', 'e', 'r', 'p',
					'?', 'j', 'k', 'w', 'd', 'i', 't', 'q', 'c', 'b' },
			{ 'h', 'u', '.', 'e', 's', 'a', 'o', ',', 'i', 'y', 't', 'l', 'w', 'n', 'd', 'g', 'j', 'p', 'q', '%', 'b',
					'?', 'k', 'm', 'x', 'c', 'z', 'f', 'r', 'v', '&' },
			{ 't', 'z', 'g', 'i', 'c', 'e', 'o', '&', 'y', 'm', 'h', 'w', 'r', 'q', 'p', '.', 'j', 'u', 'f', 's', 'v',
					'k', '?', '%', 'x', 'd', 'a', ',', 'l', 'b', 'n' },
			{ 'u', 'i', 'o', 'n', 'd', 'a', 'c', 'b', '&', ',', 't', 'j', 'k', 'z', 'v', 'q', '.', 'w', 'y', 'r', 'g',
					'l', 'x', 's', 'p', 'h', 'm', 'f', '%', '?', 'e' },
			{ 'w', 'n', 'q', '?', 'p', 's', 'v', 'x', 'a', '.', 't', ',', 'm', '&', 'h', 'k', 'f', 'c', 'b', 'j', 'd',
					'l', '%', 'y', 'e', 'r', 'u', 'o', 'i', 'g', 'z' },
			{ 'l', 't', 'b', 'i', 'g', 's', 'v', '?', 'q', 'u', '%', 'w', 'k', 'p', 'd', 'x', 'y', 'f', 'j', '&', 'm',
					'a', 'r', 'e', 'c', ',', 'z', 'h', 'n', 'o', '.' },
			{ 'u', '.', 'l', 'g', 'e', '&', 'm', 'q', 'z', ',', 'i', 'p', 'r', 'a', 'x', 'o', 'w', 'v', 'f', '?', 'k',
					'n', 'h', 'j', '%', 's', 'd', 'c', 'b', 't', 'y' },
			{ 'e', 'w', 'i', 'c', 'r', ',', 't', 'z', 'o', 'f', 'j', '.', 'p', 's', 'd', 'q', 'y', 'm', 'k', 'u', 'h',
					'&', 'n', 'g', 'v', 'a', 'l', 'x', 'b', '%', '?' },
			{ 'f', 't', 'g', 'i', 'u', 'k', '?', 's', 'a', 'd', 'p', 'o', 'w', 'c', 'r', 'x', 'n', 'y', 'l', '&', 'h',
					'z', 'b', 'e', '.', 'm', 'q', ',', 'v', '%', 'j' },
			{ 'h', 'o', 'n', 's', 'd', 'l', 'x', 'i', 'c', '&', 'e', 'v', 'z', 'a', 'y', '%', 't', 'b', 'r', 'k', ',',
					'w', '?', '.', 'q', 'j', 'm', 'u', 'f', 'p', 'g' },
			{ ',', 'u', 'e', 'd', 't', 'x', '&', 'k', 'p', 'r', 'm', 'f', 'y', 'i', 'w', '.', 'o', 'q', 'c', 'h', 's',
					'?', '%', 'n', 'j', 'l', 'b', 'a', 'g', 'v', 'z' },
			{ 'x', 't', 'b', 'q', '.', 'n', 'l', 'p', 'd', 'y', 'f', 'h', 'w', 'k', 's', 'g', 'i', 'r', 'z', '&', ',',
					'a', 'm', 'o', 'j', 'u', 'v', '%', 'e', '?', 'c' },
			{ 't', 'm', 'n', 'r', 'i', 'f', 'p', '.', 'e', 'd', 'g', '&', ',', 'h', 'w', '%', 'o', 'k', '?', 'u', 'x',
					'v', 's', 'l', 'b', 'j', 'z', 'a', 'y', 'c', 'q' },
			{ 'l', '%', 'g', 'z', 'n', ',', '&', 'k', '.', 'w', 'x', 'f', '?', 'y', 'e', 'u', 'j', 'v', 'o', 'p', 's',
					'm', 'b', 't', 'q', 'i', 'c', 'h', 'r', 'a', 'd' },
			{ '.', 'u', 'r', 'e', 'z', '&', 'j', 't', 'v', 'h', 'o', ',', 'p', '?', 'i', 'b', 'm', 'l', 'y', 'f', 'w',
					'd', 'g', 'a', 'x', 'n', 'k', 's', 'c', 'q', '%' },
			{ 'c', 'r', ',', 'z', 'b', 'g', 'k', 'e', 'f', 'h', '%', '.', 'l', 'm', 'd', 'w', 'x', 'i', 's', 'a', '&',
					'o', 'n', 'p', 't', 'j', 'v', '?', 'u', 'y', 'q' },
			{ 'b', 'g', 'f', 'l', 'k', '?', 'j', '&', ',', 'c', 'a', 'o', 'i', 'n', 'd', 'h', 'y', 'v', 'x', 't', 'm',
					'z', '.', 'e', 'p', 's', 'q', 'w', 'r', 'u', '%' },
			{ 'd', 'j', '.', ',', 'h', 'b', '%', '&', 'x', 'i', 'y', 'g', 'c', 'q', '?', 's', 'u', 'p', 'f', 'w', 'o',
					'r', 'z', 'e', 'k', 'v', 'm', 'l', 't', 'n', 'a' },
			{ '?', 's', 'l', 'j', 'w', 'b', 't', 'd', '.', 'k', 'i', 'h', '%', 'x', 'f', 'o', 'y', '&', 'q', ',', 'p',
					'c', 'z', 'v', 'r', 'a', 'e', 'u', 'g', 'm', 'n' },
			{ 'j', 'l', 'p', '.', 'e', 'x', 'n', ',', 'q', 'i', 'h', '%', 'y', 'f', 'a', 'w', 's', 'c', 'u', 't', 'v',
					'g', 'r', 'm', '&', 'o', 'z', 'k', '?', 'b', 'd' },
			{ ',', 't', 'x', '?', 'm', '&', 'f', 'c', 'p', 'w', 'a', 'y', 'j', '%', 'd', 'i', 'e', 'u', 'q', 'h', 'k',
					'b', 'l', 'z', 'r', 'v', 'g', 's', 'o', '.', 'n' },
			{ 'w', '%', 'd', 'e', 'm', 'x', 'v', 'z', 'g', 'p', 'i', 'n', '?', 'o', 'q', 'u', 'c', 'f', 'r', 'l', 't',
					',', 'a', '.', '&', 'h', 's', 'b', 'k', 'j', 'y' },
			{ 'u', 'd', 'b', 'a', '.', 'f', 'h', 'n', 'c', 'i', '?', 'k', 'l', 't', 'r', 'o', 'q', 'v', 'z', 'g', 'x',
					'e', 'p', '&', '%', 'j', 'm', ',', 'w', 's', 'y' },
			{ 'u', 'h', 'i', 't', 'r', 'd', 'z', 'g', 'j', '?', 'e', 'q', 's', '.', 'f', 'x', '&', 'b', 'k', 'a', 'w',
					',', 'n', 'p', 'm', '%', 'o', 'v', 'l', 'y', 'c' },
			{ 'o', 'd', 't', 'z', 'c', 'e', '?', 'h', '%', 'm', 'b', 'g', 'p', '&', 'a', ',', '.', 'w', 'j', 'r', 'n',
					'v', 'x', 'q', 'k', 'f', 'u', 'l', 'y', 's', 'i' },
			{ 'e', 'o', '.', 'a', 'j', '?', 'h', 's', 'l', 'g', 'k', 'd', ',', 'u', 'q', 'b', 'm', 'f', 'w', 'i', '&',
					'x', 't', 'r', 'n', 'v', 'c', 'y', 'p', '%', 'z' },
			{ 'i', 'w', 'g', 'f', 'y', '.', 'k', 'c', ',', 's', 'u', 'j', 't', 'e', 'o', 'r', 'p', '%', 'q', 'z', 'h',
					'a', 'l', 'b', '?', 'n', 'v', '&', 'm', 'd', 'x' },
			{ 'l', '?', 'x', 'k', 'o', 'g', 'e', 'd', 'n', 's', 'w', 'q', ',', 'v', '%', 'a', 'b', 'i', 'u', 't', 'c',
					'm', '.', 'f', 'y', '&', 'p', 'r', 'h', 'j', 'z' },
			{ 'b', '&', 'g', 'd', 'k', 'y', 'w', 'e', ',', 'j', 'x', '%', 'z', 'h', 'o', '.', 'v', 'p', 'l', 'f', 'u',
					'c', 't', 's', 'q', 'm', 'r', 'n', '?', 'i', 'a' },
			{ 'g', 'n', 'b', 'p', 'z', '?', 's', 'f', 't', 'a', 'k', 'o', '&', 'v', 'e', 'l', 'd', 'x', 'r', 'u', '%',
					'y', 'q', 'h', 'i', 'w', 'm', '.', 'j', 'c', ',' },
			{ '%', 't', '&', 'v', 'a', 'o', 's', 'm', '?', 'q', 'g', 'p', 'h', 'i', 'y', 'f', 'e', 'w', 'c', 'b', 'x',
					'z', 'r', 'd', ',', '.', 'j', 'l', 'n', 'k', 'u' },
			{ '?', 'b', 'p', '%', 'n', 'l', 'x', 's', 'i', 'e', 'y', 'w', 'r', 'g', '.', '&', 'c', 'a', 'o', 'j', ',',
					't', 'q', 'k', 'u', 'z', 'f', 'v', 'm', 'h', 'd' },
			{ 'd', 's', 'x', 'v', 'e', 'g', '?', 'w', '%', 'z', 'b', ',', 'q', 'c', 'u', 'o', 'l', 'h', 'j', 'y', '.',
					't', 'k', 'i', 'a', 'm', '&', 'p', 'r', 'n', 'f' },
			{ 'k', '.', 'q', 'd', 'l', 'y', 'w', 'z', 't', '?', 'c', 'm', 'o', 'v', 'u', 's', 'b', 'x', 'i', 'j', 'g',
					',', '%', 'r', 'h', 'n', 'a', 'f', '&', 'e', 'p' },
			{ 'x', 'm', '%', 'e', '?', 'v', 'q', 'r', 'b', 'u', 'n', 'a', 'w', 'l', 'p', ',', 'k', 'i', '&', '.', 'c',
					'j', 'h', 'g', 'y', 't', 's', 'f', 'd', 'o', 'z' },
			{ 'b', 'x', 'd', '?', 'y', ',', 'v', 'e', '%', 'z', 'j', 's', 't', 'p', 'n', 'c', 'f', 'g', 'r', 'w', 'h',
					'm', 'i', '.', '&', 'q', 'a', 'o', 'l', 'k', 'u' },
			{ 'x', 'd', 'r', 'y', 'q', 'z', 'l', 'f', 'h', '.', 's', 'g', 'e', 'm', 'w', 'i', '%', ',', '?', 'a', 'c',
					'n', 'k', 'p', 'o', 'b', 't', '&', 'v', 'u', 'j' },
			{ 'v', 'n', 'b', '%', 'r', 'd', 'q', 's', 'i', 'u', '.', 'a', 'y', '?', 'k', 'z', 'm', 'l', 'j', 'c', 'e',
					'f', 'x', '&', 'p', 'g', 'h', 'w', 't', 'o', ',' },
			{ 'g', 'c', 'k', 'q', 'x', 'p', 'z', 'b', ',', 'v', 'h', 'm', 'u', '?', 'y', '&', 'r', 't', 'o', '.', 'e',
					'i', '%', 'd', 'l', 'f', 'n', 'w', 'a', 's', 'j' },
			{ 'v', 'a', 'x', 'r', '%', 'b', 'm', '&', 'h', '?', '.', 'k', 'z', 'l', 'q', 't', 'p', ',', 's', 'f', 'g',
					'u', 'd', 'i', 'j', 'n', 'o', 'w', 'c', 'y', 'e' },
			{ 'v', 'j', 'f', 'c', 'i', 'z', 'g', 'n', 'l', 'o', '?', '.', 'y', 'w', 's', 'u', 'b', 'e', 'd', 'p', '&',
					'h', 'k', 't', 'x', 'm', 'q', 'r', '%', ',', 'a' },
			{ 'h', 'q', 'v', '&', 'y', 'c', '.', 'u', 'n', '%', 'd', 'e', 'x', 'w', 'f', 'g', 'm', 'p', ',', 'i', 'j',
					'k', 'z', 'l', 's', 'o', 't', 'a', 'b', 'r', '?' },
			{ 's', '%', 'v', 't', 'x', 'a', 'y', ',', 'q', 'e', 'b', 'k', 'm', 'g', 'u', 'n', 'l', 'h', 'p', 'd', 'z',
					'w', 'r', 'o', '.', 'c', '&', 'j', 'i', '?', 'f' },
			{ 'g', 'm', 'n', 'l', 'y', '%', '?', 'a', 'e', 'w', 'q', 'v', '&', 'b', 'r', ',', 'z', 'j', 'p', 's', 'd',
					'.', 'x', 'h', 'u', 'c', 'o', 'f', 'i', 'k', 't' },
			{ 'd', '&', 'f', 'i', '?', 'm', '%', 't', 'h', 'n', 'x', 'b', 'q', 'p', 'g', '.', 'l', 'a', 'o', 'v', ',',
					's', 'c', 'j', 'y', 'k', 'u', 'r', 'e', 'z', 'w' },
			{ 'k', 'x', 'c', 'i', 'l', ',', 'h', 'y', '&', 'o', 'w', 'f', 'e', 'v', '?', 'q', 'g', 'u', 'r', 's', 'm',
					'a', 'j', 'd', 'n', 'z', 'b', 'p', '.', '%', 't' } };

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
