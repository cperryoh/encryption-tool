
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

	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("De encrypt");
	char[] temp;
	String[] keys = { "tca", "xzj", "rh,", "j&c", "kgl", "ygd", ",xq", "gqr", " lj", "qvn", "t&y", "plx", "dhf", "h?l",
			"vie", "yol", "s?b", "&& ", "vjz", "gw ", "fuq", "scg", "pyv", "us&", "gzt", "iwv", "&zt", "wrw", "ove",
			"qqe", "hh?", "lya", "nea", "ica", "t%d", "d?s", "vry", "dqm", "hac", " %&", "zts", "zam", "nfz", "tge",
			"pkj", "pzn", "v&a", "jlq", "rw%", " nl", "jlq", "tzh", "n%.", "spm", "h%,", "lal", "fvd", "bjp", "wpd",
			"ym?", "h,u", "ogi", "&ef", "q,c", "x?q", "tuq", "vam", "rp,", "rds", "vny", "&qb", "m r", "psh", "uvr",
			"qbn", " ke", "zgd", "ngp", "vkk", "xth", "a?f", "zck", "pcv", "snh", "?mz", "wv.", "?zs", "?bt", "%lg",
			"sre", "fyf", "dil", "ax&", "ij,", "nsn", "ta%", ".r?", "p,e", "gt&", "bsw" };
	char[] alphebet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', ',', '.', '?', '&', '%' };
	char[][] mixedUps = {
			{ 'q', '?', 'j', 'i', '%', '.', 'v', 's', 'c', 'm', 'b', 'k', 't', 'r', 'h', 'l', 'g', 'w', 'd', 'u', 'p',
					'z', 'a', 'y', '&', ',', 'o', 'x', 'n', 'e', 'f', ' ' },
			{ 'z', 'm', ',', 'k', 'y', 'x', 'g', 'n', 'o', 'h', 'v', 'f', 'e', '&', '%', 'a', 'c', 'l', 'i', 'p', '?',
					'r', 's', 'd', 'b', 'q', 'j', ' ', '.', 'u', 't', 'w' },
			{ 'f', 'z', 'r', 'y', '.', '?', 'a', 'h', 'm', 'p', 'k', 'l', 's', 'x', 'g', '&', 'u', 'd', ',', 'j', 'b',
					'v', ' ', 'i', 'w', 'q', '%', 'e', 't', 'n', 'o', 'c' },
			{ 'n', 'c', 'a', ',', 's', 'f', 'd', 'x', '&', 't', 'j', 'z', 'b', 'o', 'q', '%', 'v', 'r', 'u', 'g', 'e',
					'?', 'm', 'k', 'h', 'i', 'l', ' ', '.', 'p', 'w', 'y' },
			{ 'y', 'l', 'c', 'n', 'i', 'q', 'p', '&', 'k', 's', 'r', ' ', 'x', 'o', 'z', 'v', 'f', 'd', 'g', 'u', 'e',
					'm', 'j', '?', 'h', 'a', 'b', ',', '%', '.', 't', 'w' },
			{ 's', '.', 'o', 'e', 'y', 'u', '&', '?', 'k', 'd', 'v', ' ', 't', 'i', 'a', 'j', 'f', 'm', 'z', 'x', 'h',
					'b', 'l', '%', ',', 'w', 'p', 'n', 'q', 'c', 'r', 'g' },
			{ 'x', 'a', 'k', 'j', 'd', 'y', '%', 'l', 'z', '?', 'q', 'u', 'm', 'h', 'n', 'w', '.', ',', 'f', 's', 'g',
					'r', 'b', 'o', 'e', '&', 'c', 't', 'v', 'i', ' ', 'p' },
			{ ' ', 't', 's', 'l', 'h', 'g', 'w', '%', 'q', 'x', ',', 'n', '.', 'i', 'a', 'u', 'm', 'y', 'd', 'c', 'z',
					'?', 'v', 'k', 'r', 'b', 'e', 'j', 'f', '&', 'p', 'o' },
			{ ' ', '%', 'h', '.', 'n', 'w', 'q', 'y', 'v', '&', 't', 'c', 'd', 'm', 'o', 'p', 'r', 'u', 'e', 'z', 'k',
					'a', 'f', 'b', 'x', ',', 'g', 's', 'l', 'j', '?', 'i' },
			{ ' ', '?', 'o', 'w', 'z', 'c', ',', '%', '.', 'r', 'd', 'm', 'n', 'b', 'q', 'k', 'h', 'j', 'a', 'i', '&',
					'v', 'p', 's', 'u', 'f', 'y', 'e', 'g', 'l', 'x', 't' },
			{ ' ', 't', 'n', 'p', 'w', 'm', '?', 'r', 'l', 'd', 'o', 's', 'k', 'u', 'a', 'v', 'g', 'i', '%', '.', '&',
					'y', ',', 'f', 'j', 'e', 'x', 'h', 'q', 'c', 'b', 'z' },
			{ 'k', '.', 'n', '%', 'g', 's', 'p', 'y', 'v', 't', 'e', '?', 'z', ' ', 'w', 'b', 'i', 'r', '&', 'l', 'c',
					'o', 'a', 'q', ',', 'd', 'h', 'm', 'f', 'x', 'j', 'u' },
			{ 'g', 'e', 'j', 'm', 'f', 'd', 'b', 'a', 'r', 'y', 'o', 'c', '.', 'q', ' ', '&', 's', 'h', 'n', 'x', 'i',
					'?', '%', 'z', 'k', 't', 'l', 'p', 'w', 'u', ',', 'v' },
			{ 'o', '.', 'e', 'q', '&', 'h', 'g', 'i', 'c', 'l', 'f', 'k', 'v', 'm', 't', '%', 'u', 'b', 'y', ',', 'w',
					's', 'd', 'r', ' ', 'n', 'a', 'j', '?', 'z', 'p', 'x' },
			{ 'b', 'n', 'q', 'c', 'z', ',', 'j', 'v', 'x', '&', 'k', 'o', ' ', '%', 's', 'h', 'i', 'e', 'p', 'g', 'f',
					'a', 'd', '.', 'u', 'y', 'r', 'm', 't', 'l', 'w', '?' },
			{ 'z', 'a', 'l', 'e', '%', 'o', 'q', 'r', 'h', 's', 'y', 'f', 'x', 'u', 'k', 'b', '.', ',', 'v', '?', 'n',
					'j', 'g', 'p', ' ', 'm', 'c', 't', '&', 'd', 'i', 'w' },
			{ 'e', 'u', 'g', 'q', '&', 'b', 's', 't', 'z', 'h', 'c', 'f', 'k', ' ', 'a', 'y', 'j', ',', 'l', '.', '?',
					'x', 'o', 'v', 'd', 'w', '%', 'r', 'p', 'i', 'n', 'm' },
			{ 'v', 'm', 'y', 'g', 'c', 'a', 'b', 'j', '&', 'k', 'd', 'p', ' ', '?', 'h', 's', 'n', 'u', 'r', 'o', 'q',
					'z', '.', 'l', ',', 't', 'f', 'x', 'i', 'w', '%', 'e' },
			{ 'b', 'p', '?', ',', 'h', 'k', 'z', 'l', 'e', '.', 'd', 'y', 's', 'r', 'c', 'w', 'o', 'i', 'q', 'f', 'a',
					'u', ' ', 'j', 'v', 'n', '&', '%', 'x', 'm', 'g', 't' },
			{ 'z', 'w', 't', 'v', 'f', 'd', 'p', 'i', ',', 'e', 'x', 'k', 'c', '.', 'q', 's', 'b', '?', 'r', 'j', 'n',
					'y', 'l', 'o', 'h', '%', 'm', '&', 'g', ' ', 'a', 'u' },
			{ 'r', '%', 'm', 'h', 'a', 'i', 'z', '?', ',', 'q', 'l', 'f', '.', 'n', 't', 'j', 'c', 'v', 'k', 'x', ' ',
					'p', 'y', 's', 'e', 'o', '&', 'w', 'g', 'd', 'u', 'b' },
			{ 'z', '?', 'e', 'i', 'a', '%', '.', 'q', 'x', 'p', 'k', 'c', 'v', 'n', ',', '&', 'r', 'g', 'm', 'u', 'd',
					'y', 'w', 's', 'l', 'h', 'j', 'f', ' ', 'b', 't', 'o' },
			{ 't', 'r', 'w', 'o', '.', 'n', 'q', 'c', ',', 'u', 'e', 'v', 'y', 'g', 'l', 'd', 'i', ' ', 'b', '%', 'h',
					'a', 'j', '?', 'm', '&', 's', 'p', 'x', 'z', 'k', 'f' },
			{ '%', 'w', ',', 'n', '?', 'z', 'o', 'c', 'y', 'u', 'e', 'r', '&', 'l', 'v', 'i', 'j', '.', 'b', 't', 'k',
					'a', 'g', 's', 'm', 'p', 'x', 'd', ' ', 'h', 'q', 'f' },
			{ 'm', 'y', 'z', 'r', 'b', '?', 'p', 'j', 'o', ' ', 'i', 's', '&', 'l', 'u', 'w', 'k', '.', 'x', 'a', 'c',
					'h', 'n', 'e', 't', 'q', 'f', ',', '%', 'v', 'd', 'g' },
			{ 's', '&', 'i', 'r', 'v', 'o', 'a', 'k', 't', 'b', '.', 'e', 'f', 'm', 'p', 'g', 'x', ',', 'u', 'd', 'h',
					'c', '%', 'n', 'l', 'z', 'q', '?', 'y', ' ', 'w', 'j' },
			{ 'o', 'f', 'v', 'b', 'p', '?', 'l', 's', 'm', 'a', 'x', 'i', '&', 't', 'd', '.', ',', '%', 'e', 'g', 'q',
					'z', 'k', 'n', 'r', 'j', 'u', ' ', 'h', 'w', 'c', 'y' },
			{ 'y', 'h', 'p', '?', 'z', 'm', '.', 'x', ' ', 'f', 'n', 'j', '&', '%', 'c', 'r', 'e', 'v', 'w', 'o', 'k',
					'u', 'd', 't', 'l', ',', 'g', 'q', 'b', 'i', 's', 'a' },
			{ 'c', 't', 'y', 'o', 'g', 'm', 'h', ' ', 'f', 'w', 'z', 'n', 'i', 'l', 'x', '&', 'u', '.', ',', 'd', 'a',
					'b', 'j', 'p', 's', 'v', 'k', 'e', '?', '%', 'r', 'q' },
			{ 'x', 'l', 'w', 't', 'z', '&', 'n', 'f', 'a', 'd', '?', 'y', 'e', 'i', 'r', ',', 'g', 'o', 'h', 'v', 'b',
					'c', 'k', ' ', 'u', 'm', '.', 's', 'p', 'q', 'j', '%' },
			{ 'r', 'b', ',', 'c', 'f', 'p', 'z', 'e', 'n', '?', 'a', 'x', 'g', '&', 'v', 't', 'y', ' ', 'o', '.', '%',
					'u', 'm', 's', 'i', 'd', 'w', 'h', 'l', 'q', 'j', 'k' },
			{ 'y', 'z', 'x', 'c', 't', 'l', 'e', 'd', 'w', 'q', 'g', '%', '.', ' ', 'm', 's', 'j', 'v', 'p', 'u', 'h',
					'b', 'k', 'r', 'f', 'n', '?', '&', 'o', 'i', ',', 'a' },
			{ 'q', 'c', 'i', 'g', 't', ' ', 'a', 'o', '?', 'p', 'j', 'w', 'h', 'e', 's', 'z', 'u', 'k', 'n', 'd', 'r',
					'y', 'b', 'x', '&', ',', 'l', '%', 'f', '.', 'v', 'm' },
			{ 'q', ' ', 'e', 't', 'a', 'i', '%', 'y', 'x', 'v', 'u', 'l', 'g', 'n', 'o', 's', '?', 'b', 'd', 'r', 'f',
					'&', 'm', 'c', 'w', 'k', 'h', '.', ',', 'z', 'p', 'j' },
			{ 'a', 'l', 'y', 'm', 'h', ',', '%', 'n', 's', ' ', 'd', 'r', 'x', 'c', 'v', 'z', 'j', 'g', '&', 'w', 'o',
					'?', 'q', '.', 'k', 'i', 'e', 'b', 'p', 'f', 't', 'u' },
			{ 'a', 'p', 'i', 's', 'z', 'y', 'q', 'r', 'x', 'g', 'd', 'h', '%', 'e', 'b', 't', '?', 'k', ',', 'u', ' ',
					'v', '.', 'l', 'w', 'c', 'j', 'o', '&', 'n', 'm', 'f' },
			{ 's', 'a', 'e', 'o', 'i', 'm', 'q', 'k', 'h', 'l', 'u', '?', 'v', 'd', ' ', 'y', 'n', 'f', 'g', 'c', ',',
					't', 'r', '&', 'x', 'z', '%', 'b', 'w', 'p', 'j', '.' },
			{ 'a', 'k', ',', 'q', 'x', 'c', 'f', 'y', 'd', '?', 'm', 't', 'w', 'g', 'r', 'j', 'o', ' ', 'u', 'i', 's',
					'.', 'e', 'n', 'b', 'v', 'z', 'p', '&', 'l', '%', 'h' },
			{ 'f', 'a', 'w', 's', 'l', 'z', '?', 'q', 'p', 'm', 'y', 'g', 'c', 't', 'j', ',', '%', 'u', 'k', 'r', 'n',
					'o', 'b', 'i', 'd', 'v', '&', ' ', 'x', 'h', '.', 'e' },
			{ 's', 'w', 'o', 'l', 'm', 'n', '?', 'g', 'e', 'z', 't', '%', 'k', 'r', 'j', 'q', 'p', 'c', 'i', '&', ',',
					'h', 'a', '.', 'b', ' ', 'x', 'y', 'u', 'v', 'd', 'f' },
			{ ' ', 'd', 'm', 'p', 'x', '?', 'y', 'k', 'j', 'w', 'b', 'e', '&', 'h', '%', 'a', 'r', 'c', 's', 'u', '.',
					',', 't', 'g', 'v', 'l', 'i', 'z', 'f', 'q', 'o', 'n' },
			{ '&', '?', 'x', 'h', 'p', 'b', 'y', 'k', 'o', 'l', 'j', 'i', 'c', ',', 'z', 'e', 'u', '.', ' ', 'a', 'm',
					'f', 'g', 'w', 'd', 't', 's', 'q', 'r', 'v', '%', 'n' },
			{ 'g', ',', 'j', 'q', 'w', '.', 'o', 'f', 'x', 'l', 'i', 's', 'z', 'p', 'v', 'y', '&', 'e', 'k', 't', 'd',
					'%', 'h', ' ', 'n', 'b', '?', 'a', 'c', 'u', 'r', 'm' },
			{ '&', 'm', 'd', 'x', 'k', 's', 'b', 'n', '.', ' ', 'q', 'e', 'i', 'l', 'o', 'r', '%', '?', 't', 'u', 'f',
					',', 'g', 'c', 'p', 'w', 'v', 'y', 'a', 'z', 'h', 'j' },
			{ 't', 'v', 'w', 'm', 'r', '&', 'f', 'i', 'u', 'g', ' ', '.', 'k', 'z', 'c', '%', 'd', 'y', 'h', 'o', 'b',
					'q', ',', 'x', 's', 'a', 'p', 'e', 'j', '?', 'n', 'l' },
			{ 'p', ' ', ',', 'v', '.', 'w', 'g', 'm', 'r', 'h', 't', 'y', 'k', 'l', '&', '%', 'j', 'u', 'b', 'x', 'f',
					'a', 'z', 'q', 's', 'e', '?', 'c', 'd', 'i', 'o', 'n' },
			{ 'u', 'w', 'e', ' ', 'f', 'j', 'm', 't', 'g', 'a', 's', 'v', 'p', '%', 'k', 'z', '&', ',', 'r', 'd', 'h',
					'b', 'n', 'o', 'x', 'q', 'c', 'i', 'l', '?', 'y', '.' },
			{ 'p', 'm', ',', 'e', 'w', 'v', 'r', 'o', 'i', 'n', 'k', 'x', 's', '.', 'c', '?', 'l', 'd', 'q', '%', '&',
					'y', 'u', 'f', 'j', 't', ' ', 'h', 'z', 'g', 'a', 'b' },
			{ 'h', 'e', '.', 'r', 'z', 'q', '&', 'g', '%', 'x', 'v', 't', 'f', 'i', 'y', 'n', ' ', 'm', 'k', ',', 'j',
					'p', 'u', '?', 'w', 'l', 's', 'b', 'a', 'c', 'd', 'o' },
			{ 'z', 'd', 'c', 'x', 'h', '%', 'w', 's', '?', 'm', 'v', 'y', 'a', 'r', '.', 'b', 'k', 'q', 'u', ' ', 'j',
					'e', '&', ',', 'n', 't', 'o', 'g', 'p', 'f', 'l', 'i' },
			{ ' ', 'd', 'x', ',', '&', 'v', 'q', 'p', 'u', 'l', 'i', 't', 'k', 'f', 's', 'h', 'r', 'e', '%', 'y', 'm',
					'o', '.', 'j', 'z', 'c', 'a', 'w', 'g', 'b', '?', 'n' },
			{ 'l', 'j', 'f', 'o', '%', 'r', ' ', 's', 'p', 'n', 'u', '.', 'w', '?', 'h', 'a', '&', 'g', 'b', 'm', 'e',
					't', 'x', 'v', ',', 'c', 'y', 'd', 'q', 'i', 'z', 'k' },
			{ 'l', '?', ',', 'x', 'j', 'i', 'w', 'o', 'm', '%', '.', 'd', 'k', 'y', 'a', 'h', 'p', 'q', 'f', 'e', '&',
					'n', 's', 'v', 'z', 't', ' ', 'b', 'g', 'r', 'u', 'c' },
			{ 'f', 'x', 'w', 'v', 'z', 'h', '?', '&', 'n', 'e', '%', 'o', 'a', ',', 'b', 'l', '.', 'i', 't', 'g', 'k',
					'r', 'q', 'c', 'p', 'd', 'j', 's', 'u', 'y', 'm', ' ' },
			{ 'd', 'w', 'i', 'y', ' ', 'g', 'm', 'k', 'b', 'a', 'o', 'e', 't', ',', 'r', 'u', '&', 'q', 'c', 'l', '%',
					'n', 'f', 'p', '?', 'x', 'j', '.', 'z', 'v', 'h', 's' },
			{ 'p', 'j', 'l', 'u', 'd', 'o', 'b', 't', ' ', 'w', 'g', 'm', ',', 'z', 'i', '.', '&', '?', 'f', 'x', 'n',
					'h', 'q', 'y', 's', '%', 'v', 'e', 'r', 'k', 'c', 'a' },
			{ 'h', ' ', 'w', 'o', ',', 'i', 'v', 'a', 'l', 'm', 'u', 'z', 'j', '&', 's', 'x', 'q', 'b', 'n', 'g', 'd',
					'%', 'y', 'f', '?', 'c', 'k', 'e', 'r', 'p', 't', '.' },
			{ 'k', 'b', 'o', 'i', 'v', 'j', 'd', 'y', '?', 't', 'x', 'q', 'p', ',', 'u', 'm', ' ', 's', '.', 'f', '%',
					'g', 'h', 'l', 'c', 'a', 'r', 'e', 'n', 'w', 'z', '&' },
			{ 'o', 'g', 'l', 'p', 'm', '?', 'q', 'x', 'v', 'y', 'u', 't', 'r', 'i', 'j', '%', '&', 'h', 'b', 'c', '.',
					'w', ',', 'e', 'f', 'a', 's', 'd', 'z', 'k', ' ', 'n' },
			{ 'g', 'q', 'u', 'o', 'v', 't', 'x', ',', 'a', '.', 'h', '?', '&', 'y', 'w', 'n', 'p', 'c', '%', 'f', 'd',
					'j', 'z', 'k', 's', ' ', 'r', 'e', 'i', 'l', 'm', 'b' },
			{ 't', 's', ',', 'l', 'e', 'c', '%', 'a', 'f', 'd', 'r', 'k', '?', 'o', 'q', 'u', 'v', 'p', '.', 'b', 'z',
					'm', '&', ' ', 'x', 'g', 'i', 'n', 'h', 'w', 'j', 'y' },
			{ 'b', 'c', 'u', 'e', 'v', '%', '.', 'j', 'd', 's', 'y', 'k', 'h', 'r', 'f', 't', 'm', ',', 'p', '&', 'g',
					' ', 'z', 'a', 'w', 'o', '?', 'q', 'n', 'x', 'i', 'l' },
			{ 'j', 'i', 'd', 'r', 'u', 'v', '%', 'z', 'b', 'e', 'm', 's', '.', 'f', 'n', ',', 'p', ' ', 'c', 'g', 'k',
					'x', '?', 'o', 'q', 'a', 'y', 't', '&', 'w', 'h', 'l' },
			{ 'i', 'p', 's', 'z', 'x', 'h', 'c', 'e', 'a', ',', 'b', '?', 'f', '%', 't', 'w', 'r', 'l', 'v', 'o', 'u',
					'q', 'j', 'd', ' ', 'n', '.', '&', 'y', 'm', 'g', 'k' },
			{ 'y', 'm', 'v', 'e', 'n', 's', 'f', 't', 'p', 'k', 'w', 'j', 'd', 'x', ',', 'r', 'l', '.', 'u', 'a', '&',
					'h', 'g', 'o', 'z', ' ', 'b', 'i', '%', 'q', '?', 'c' },
			{ '?', 'e', 'j', 's', 'k', 'y', 'n', 'f', 'v', 'l', 'p', 'q', 'a', 't', 'b', 'c', 'o', 'h', 'g', '&', 'x',
					'%', 'm', '.', 'i', 'r', ',', 'd', 'u', ' ', 'w', 'z' },
			{ 'x', 'b', 'j', 'a', ',', '?', 'i', 'n', 'v', 'e', '%', '&', 'g', 'u', 'c', 'o', 'm', 'h', 'l', 'd', 'q',
					'f', 'z', 'k', 't', ' ', 'p', 'r', '.', 'w', 's', 'y' },
			{ 'w', 'l', 'o', 'n', 'h', ',', 'r', '&', 'u', 'q', 'p', 'v', 'j', 'f', 'b', 'i', '?', 's', 'x', 'm', 'z',
					'k', '.', 'c', 'y', 'd', '%', 't', 'g', 'e', 'a', ' ' },
			{ 'z', 'l', '?', ',', ' ', 'm', 'g', 'b', 'o', 'j', 't', '&', 'u', 'x', 'r', 'd', 'f', 'p', 'w', 'i', '.',
					'%', 'e', 'v', 'c', 'n', 's', 'a', 'h', 'q', 'y', 'k' },
			{ 'c', 'e', 'd', 'w', 'u', 'i', '%', '&', '?', 'j', 'b', 'g', ',', 's', 'h', 'z', 'y', ' ', 'f', 'q', 'k',
					'n', 't', 'p', 'l', '.', 'v', 'r', 'x', 'a', 'o', 'm' },
			{ 'x', 'n', 'i', ' ', '%', 'z', 'o', 'u', 'b', 'r', 'j', 'f', 'v', 'g', 'p', ',', '.', 'k', 'q', 'c', '?',
					'&', 'd', 'h', 'a', 'm', 'w', 't', 'e', 's', 'l', 'y' },
			{ 'm', 'j', 'u', 'v', 'x', 'c', ' ', 'a', 'h', 'f', 'o', '%', 's', 't', ',', 'b', '?', 'p', '.', 'i', 'l',
					'z', 'q', 'w', 'y', 'r', 'd', 'n', '&', 'e', 'g', 'k' },
			{ 'v', 'o', 'p', 's', 'r', 't', ',', 'i', 'l', 'f', 'b', '?', 'x', '&', 'k', 'u', 'j', 'q', 'g', 'w', 'm',
					'a', 'n', 'y', ' ', 'd', '.', 'e', 'z', 'h', '%', 'c' },
			{ 'z', 'k', 'n', 'x', 'l', 'u', '?', 'm', '&', 'b', 'w', 'i', ',', '.', 'g', 'y', ' ', 'e', 'h', 'a', '%',
					'd', 'v', 'c', 'j', 'o', 's', 'r', 't', 'p', 'f', 'q' },
			{ 'f', 'w', 'd', ' ', '.', 'i', 'g', 'y', 'c', 'n', 'x', 'q', 'b', 'p', 'k', ',', 't', '%', 'z', '?', 'a',
					'l', 'u', 'o', 's', 'h', 'j', 'v', '&', 'm', 'r', 'e' },
			{ 'k', 'i', '&', ',', 'w', 'o', 'h', 'l', 'j', 'e', '.', 'c', 'y', 'u', 'n', 'x', 'v', 'z', '%', 'a', 'g',
					'f', 's', '?', 'd', 'p', 'b', 'r', 'q', 'm', ' ', 't' },
			{ 'x', 'g', 'm', 's', ' ', '.', 'u', 'd', 'k', 'p', 'i', 'q', 'w', 'r', '?', '&', 'h', 'a', 'l', 'v', 'f',
					',', 'y', 'j', 't', 'c', 'e', '%', 'n', 'z', 'o', 'b' },
			{ 'l', 'e', 'h', 'a', 'c', 'k', 'p', 't', 'g', 'm', 'o', 'v', 'i', 'r', ' ', 'n', 'w', 's', 'z', '%', 'b',
					'q', 'f', 'd', 'x', 'j', ',', 'y', '.', '?', '&', 'u' },
			{ 'c', 'p', 'q', 'z', '.', 's', '&', '?', 'i', 'w', 'u', '%', 'r', ',', 'x', 'n', 'v', 't', 'l', 'f', 'k',
					'j', 'a', 'm', 'o', 'd', ' ', 'h', 'y', 'g', 'b', 'e' },
			{ '%', ' ', 'v', 'i', 'b', 'd', 'r', 'o', 'w', 'y', 'c', 'h', 'j', 'e', 't', 'z', ',', 'a', 'k', 'x', 'f',
					's', 'u', 'm', '&', 'n', '.', 'q', 'p', 'g', 'l', '?' },
			{ 'm', 's', 'd', 'q', 'v', 'b', 'i', ' ', 'n', 'c', '.', '&', 'z', 'k', 'x', 'y', 'l', '?', 'f', 'g', 'j',
					'p', 'e', 't', 'h', ',', 'o', 'u', 'a', 'r', 'w', '%' },
			{ 'h', ' ', 's', 'i', 'o', 'f', 't', 'u', '.', 'c', 'd', '&', 'q', 'a', 'n', 'v', 'w', 'p', 'g', 'l', 'm',
					'x', 'b', '?', 'j', 'z', 'k', ',', 'r', '%', 'e', 'y' },
			{ 'c', 'i', 'n', 'u', 'd', 'p', 'j', 'b', '.', '&', 'f', 'g', 't', 'k', 'q', 'z', '?', ' ', ',', '%', 'm',
					'w', 'h', 'a', 'r', 'l', 'v', 'e', 'y', 's', 'o', 'x' },
			{ 'j', 'b', 'v', 'd', 'f', 'k', 'n', ' ', 'c', '%', 't', 'z', '.', 'g', 'y', 'i', 'x', 'm', 'a', 'p', 'w',
					',', 'l', 's', 'o', 'h', '&', 'u', 'e', 'q', '?', 'r' },
			{ 'y', 'h', 'n', 'p', '?', 'j', 'i', 'f', 'q', 't', 'w', ',', 'e', 'x', '%', 'r', '.', ' ', 'b', 'c', 'o',
					'k', '&', 'd', 'u', 'v', 'z', 'm', 's', 'l', 'g', 'a' },
			{ 'f', 'e', 'o', 't', ',', 'b', 'l', 'n', 'j', 'z', 'a', 'w', '.', '%', ' ', 'x', 'v', 'u', '&', 'r', 'g',
					'd', 'p', '?', 'h', 's', 'k', 'y', 'q', 'i', 'c', 'm' },
			{ 'a', 'x', 'h', ' ', 'q', 'd', 'f', 'w', 'e', 'p', '.', '%', 'c', 'm', 's', 'v', 'u', '&', 'r', '?', 'b',
					'n', 'k', 'y', ',', 't', 'i', 'o', 'g', 'j', 'z', 'l' },
			{ 'f', 'n', 's', 'i', '?', 'q', ',', 'm', 'v', 'k', 'l', 'p', 'r', ' ', '%', 'e', 't', 'o', '.', 'a', 'y',
					'g', 'z', 'h', 'w', 'j', 'c', '&', 'u', 'd', 'b', 'x' },
			{ '.', 'q', 'w', 'o', 'j', '%', 'y', ',', '&', 'p', 'x', 'v', 'f', 'h', 'r', 'e', '?', 's', 'n', 'b', 'd',
					'c', 'm', 'i', 'g', 'z', 'u', 'k', 't', 'l', 'a', ' ' },
			{ 'j', 'b', '.', 'f', 'o', 'p', 'w', 'q', 'k', 'v', 'y', 'i', 'g', ',', 't', 'x', '&', 'r', 'e', 'n', 's',
					'c', 'u', '%', 'm', 'a', 'h', 'd', ' ', '?', 'z', 'l' },
			{ 'b', 'y', 'f', 'g', '?', 's', 'h', '%', 'x', ',', 'i', 'z', 'l', 'o', 'u', 'k', 'm', 'd', 'r', 'p', 'j',
					'a', '.', 'e', ' ', 'v', 'q', '&', 'n', 'w', 'c', 't' },
			{ 'k', 'f', 'l', 'w', ',', 'n', 'p', 'd', 'a', 't', 'h', 'x', 'e', '?', 'j', 'z', ' ', 'm', '.', 'o', '&',
					'u', 'v', 'b', 'c', 'r', 'q', 'g', 'i', '%', 'y', 's' },
			{ 'f', 'v', 'k', 'a', '.', '?', 'l', 'z', '&', 'p', 'n', 'r', 'j', 'o', 'c', '%', ' ', 'm', 'e', 't', 'd',
					'i', 'w', ',', 'g', 's', 'b', 'u', 'q', 'y', 'h', 'x' },
			{ '&', 'f', '?', 'p', 'e', ' ', ',', '%', 'w', 'u', 'r', 'a', '.', 'd', 'm', 't', 'g', 'o', 'b', 'c', 's',
					'h', 'z', 'q', 'i', 'y', 'l', 'n', 'j', 'v', 'x', 'k' },
			{ 'w', '?', 'x', 'a', 's', 'r', 'm', 'y', 'b', '%', 'v', 'z', 'j', 'c', 't', 'h', 'n', 'p', 'd', 'u', 'q',
					'k', '.', 'l', 'o', 'f', ',', ' ', 'i', 'e', 'g', '&' },
			{ 'w', '?', 'n', 'l', 'y', 'g', 'e', 'q', ' ', '&', 'z', 'j', 'x', 'b', 's', 'c', 'f', 'o', '.', '%', 'p',
					'h', ',', 'm', 'u', 'r', 't', 'a', 'i', 'd', 'v', 'k' },
			{ 'x', ' ', 'y', 'w', 'f', '%', 'p', 'e', 'c', 'a', 'j', 'o', 'h', 'g', 'z', '?', 'm', 'r', 'u', 'i', 'l',
					'q', 'd', '&', '.', 'v', 't', 's', ',', 'b', 'k', 'n' },
			{ 'w', 'r', '&', 'h', 'a', 'n', 'u', 'g', 'j', 'p', 'z', 'm', 'f', '%', 'b', 'd', ',', '.', 'o', 'l', 'c',
					'k', 'i', '?', 't', 'e', 'q', ' ', 'y', 'v', 's', 'x' },
			{ 'y', 'd', 'v', 'j', 'g', 'q', 'w', ',', 'h', '&', 'f', 'z', 'r', 's', 'u', '.', ' ', 'k', 'a', 'i', '?',
					'm', 'o', 'l', 't', 'x', 'b', 'p', 'n', '%', 'e', 'c' },
			{ 'v', ',', 'k', 'q', 'z', '.', 's', 'n', 'f', '?', ' ', 'j', '&', 'y', 'r', 't', 'l', 'g', 'h', 'o', 'w',
					'p', 'e', 'i', 'x', 'a', 'm', '%', 'u', 'c', 'd', 'b' } };

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
				int level = 100;
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
