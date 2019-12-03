
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
	int level = 50;
	String[] levelkeys = {"!?","e0","sk","qv","ki","?2","32","1q"};
	int[] randomLevels = {25  ,13  ,40  ,50  ,12  ,43  ,34  ,56};
	// each 'coordinates' to a randomized version of the alphabet
	String[] keys = { "xrw", ",rd", "tx0", "l86", "v5?", "abr", ".yy", "zta", "7w0", "ip6", "y14", "rlm", "xo9", "931",
			"osc", "p!n", "j4a", "0!4", "2np", "kli", "iyh", "1d3", "ehb", "ssx", "lmn", "cyd", "htc", "ocg", "h14",
			"?2v", "3iz", "8n2", "8ey", "hur", "jv2", "orh", "l6x", "h,k", "x!k", "br3", "2np", "ddm", "613", "%tu",
			"5ly", "e8n", "es6", "c?y", "4.w", "2cb", "93!", "&vu", "rv1", "xza", "s?7", "!n5", "n4f", "yx%", "pon",
			"j93", "44w", "cm8", "pzo", "2%a", "otg", "5?8", "306", "7hb", "rn&", ".?y", "qxq", "81l", "zez", "mqk",
			"odp", "9o5", "ac0", "e&e", "b3u", "?37", "hta", "&.4", "bjc", "sdo", "ilv", ",3o", "3?,", "19v", "f3k",
			"4ld", "i.!", "fkj", "9i.", "iix", "otd", "a4n", "d1p", "z.v", "hdn", "fu%" };
	// list of accepted characters to scramble
	char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0' };
	// current scrambled list
	char[][] mixedUps = {
			{ 'd', 'v', '1', 'p', 'z', 'w', 't', '5', 'n', '8', 'e', 's', 'q', 'j', '9', 'h', 'i', '%', '!', 'y', 'g',
					'a', '2', 'l', '3', 'u', '.', '4', '?', 'r', 'f', 'k', '6', '&', 'm', 'b', 'x', 'o', ',', '7', '0',
					'c' },
			{ 'f', 'v', 'n', '%', '1', ',', 'j', '?', '7', 'h', 'w', '9', '!', 'r', 'u', 'i', '5', 'z', 'a', 'k', 'o',
					'.', 'g', '6', '0', 'd', '&', 'c', 'p', 'e', '3', 's', '8', 'q', '4', 'b', 'x', '2', 'l', 't', 'm',
					'y' },
			{ 'v', 'u', 'h', '4', 'z', 'p', '5', 'g', 'r', 'n', ',', '!', 'q', '&', 'y', '9', 'j', '6', '7', '.', '8',
					'1', 'e', 'w', 'b', 's', 'd', 'i', 'a', 't', '3', 'l', 'f', 'o', 'm', '2', 'c', '0', 'k', '?', '%',
					'x' },
			{ 'j', '3', '!', 'u', ',', 'r', 'c', 'p', '4', 'o', '2', '1', 'h', 's', 'd', '.', 'a', '9', 'q', 'w', '&',
					'7', 'v', 'l', 'e', 't', 'f', '?', '0', 'k', 'g', 'x', 'b', '6', '%', 'z', 'i', 'y', '5', 'm', 'n',
					'8' },
			{ '&', '%', '6', '5', 'b', '?', 'a', 'q', '2', 'n', 'm', 'v', 'z', '3', 'f', 's', '9', '4', '0', '!', ',',
					'1', 'd', '7', 'g', 'x', 'l', '.', 'o', 'r', 'w', 'u', 'p', 'i', 'h', 'k', 'y', 't', '8', 'e', 'j',
					'c' },
			{ '&', 'n', 'o', '1', 'v', 'p', 't', 'r', '?', '7', 'y', 's', '0', 'z', 'i', 'l', '3', 'g', ',', 'd', 'j',
					'c', 'h', '8', 'f', '2', '.', '5', '4', 'q', '%', '6', 'w', 'm', 'a', 'u', '9', '!', 'k', 'x', 'b',
					'e' },
			{ 'y', 'k', 'o', '4', 'z', 'a', '.', 'p', '1', 'j', 'u', 's', 'd', ',', 'l', '0', '&', 'b', 'f', 'g', '9',
					'h', '%', 'x', 'v', '2', 'm', 'i', '5', 'w', '8', '!', '7', 'n', 't', 'q', 'c', '3', 'e', 'r', '6',
					'?' },
			{ '.', '3', '5', '7', 'd', 'p', '4', 'g', 'v', 'a', 'r', ',', '%', 'x', 'y', 'q', '1', 'j', 'w', 'f', '!',
					'n', 'l', 'h', '8', 'o', '?', 'b', 'm', 'i', 's', '6', '&', 'z', '9', 'u', '2', 'e', '0', 't', 'k',
					'c' },
			{ '6', 'u', 'y', '!', 'm', 'l', '%', 'x', 'p', 'r', 'i', '.', 'w', 'o', 'd', '?', 'j', '8', '2', 'a', '7',
					'g', 'e', 'b', '9', 'z', '&', 'f', 's', ',', '0', 't', 'n', '5', 'c', '4', '3', 'v', '1', 'h', 'q',
					'k' },
			{ 'j', '.', 'p', '0', ',', 'b', '%', 'y', 'w', '7', 'e', '6', '&', 'i', '3', 'g', 'm', 'r', 'k', 'f', 'z',
					'!', 'v', '9', '?', 'u', 'a', 'o', 'd', 'q', '5', 'n', '1', 's', 't', '4', '8', 'c', 'l', '2', 'x',
					'h' },
			{ '0', 'd', 'v', '6', 'c', '9', 'm', '!', 'k', 'q', 'o', 'i', 'f', 'w', '8', '.', '2', 'z', 's', 'x', 'e',
					'b', 'j', '7', 'u', 'n', '5', 'g', ',', '%', 'l', 'p', '4', 't', '1', 'a', '?', 'h', 'y', '3', 'r',
					'&' },
			{ '6', 'x', '!', ',', '&', 'g', '?', 'n', '4', 'h', 'w', '0', 'v', 'q', 'z', 'c', '2', '1', 'u', '.', 'i',
					'3', '%', 's', 'm', 'p', '7', '9', 'f', 'a', 'r', 'o', 'k', 't', 'l', 'y', 'j', 'e', '8', 'd', '5',
					'b' },
			{ 'q', 'd', 'u', 'p', '8', 'n', '4', 'z', 't', 'r', '&', 'y', '7', 'o', '?', '5', 'v', 'l', ',', '3', 'e',
					'i', '.', 's', '0', 'j', 'a', 'f', 'b', '6', 'c', 'x', 'k', '!', 'w', '%', '2', '1', 'm', 'g', '9',
					'h' },
			{ 'f', '?', '.', 'k', 'n', '5', '1', 'p', 'o', 't', 'r', 'x', 'j', '7', 'i', 'y', '4', 'w', 'm', '9', '%',
					'b', 'a', 'c', 'h', '0', 's', 'z', 'e', '2', 'g', 'v', ',', '6', '&', 'q', 'u', 'l', '8', '3', '!',
					'd' },
			{ 'k', 'w', '3', '.', 'p', 'a', 't', '2', '5', '4', '?', 'r', '1', 'j', 'o', '7', '%', 'y', 'b', 'f', 'g',
					'8', 'e', '&', 'l', ',', 'n', '9', 'm', 'i', '!', '0', 'z', 'c', 'd', 'q', 'h', 'v', '6', 'x', 'u',
					's' },
			{ '0', '9', '2', 'p', 'o', 'b', 'm', 'i', 'k', 'r', '!', 'x', 'g', '1', '&', 'y', '6', 'c', '%', ',', 'z',
					'8', 'd', 'h', '4', 'a', '7', '.', '?', '3', '5', 'j', 'e', 't', 'u', 'q', 'w', 's', 'v', 'f', 'l',
					'n' },
			{ 'y', '&', 'k', 'v', 'x', '1', '9', 'r', 'g', 'w', 'q', '.', '%', 'h', 'b', '0', 'p', '7', '8', 'c', 'n',
					'd', 'm', 'o', 'j', '2', 'f', 't', 'e', '3', '4', 'u', 's', '5', 'l', 'z', '6', 'i', ',', '!', '?',
					'a' },
			{ 's', 'v', 'n', '6', '?', '2', 'w', 'j', 'i', '7', 'z', 'm', 'a', '%', '!', '8', 'd', '4', ',', 'p', '9',
					'b', 'y', '3', 'c', 'q', 't', '0', 'f', 'h', 'x', 'r', '1', 'u', 'e', 'o', '.', 'k', '5', '&', 'g',
					'l' },
			{ '6', 'j', 'h', '8', 'u', '4', '2', ',', 'r', 'b', 'l', 'k', 'd', '1', 'c', 'z', 's', 'q', 'n', '0', 'x',
					'p', '!', '?', '%', 'f', '&', 't', 'o', '7', '.', 'a', 'i', 'v', 'y', 'm', 'g', '5', '9', 'e', '3',
					'w' },
			{ '&', 'q', '1', '6', 'l', 'r', 'i', '?', 'p', '9', '8', 't', 'n', '0', 'o', '5', 'j', 'b', 's', 'f', 'z',
					'a', 'g', 'e', 'w', '2', ',', '.', '4', 'x', '7', 'h', 'd', 'c', '3', 'u', 'y', '!', 'k', 'm', '%',
					'v' },
			{ 'd', 'v', '0', '%', 't', 'z', 'b', 'j', 'q', '9', 'u', 'i', 'g', '1', 'l', 's', 'r', 'o', 'e', '?', 'a',
					'h', '3', 'f', ',', '4', '.', '&', '!', 'w', '7', '5', '6', 'm', 'n', 'k', 'p', 'x', '8', 'y', '2',
					'c' },
			{ 'j', '%', '9', 't', 's', '0', 'w', 'o', ',', 'h', 'q', '8', 'x', '!', '?', 'v', '4', 'f', 'l', 'i', '.',
					'z', '1', 'n', '2', 'a', 'e', 'g', '5', 'k', 'u', '7', 'y', '6', 'd', 'b', 'p', 'r', '3', 'c', '&',
					'm' },
			{ '6', 'y', '?', 'r', 'd', 'x', '1', 'o', 'u', 's', '4', 'l', 't', 'a', 'v', 'i', 'p', '7', '%', 'f', '2',
					'k', '8', ',', '&', '3', 'q', '9', '!', 'j', 'm', '0', 'e', 'g', 'z', 'w', 'c', 'n', 'b', 'h', '5',
					'.' },
			{ '?', '!', 'u', '8', 'v', 'r', '6', 'e', 'n', 'i', 'x', 'b', 'q', 't', 'f', 'k', 'd', 'y', '5', 'o', '0',
					'z', 'm', '2', '4', 's', '.', ',', 'w', 'h', '&', 'a', '1', 'c', 'p', '7', 'j', 'l', '3', '%', '9',
					'g' },
			{ 'y', 'q', 'l', 'm', '&', 'n', 'w', 'k', 'j', 'x', 's', '0', 't', 'c', '.', '%', 'p', '?', '2', 'a', 'h',
					'g', '!', 'r', 'v', '1', ',', 'o', 'b', '8', 'z', '3', 'e', '4', 'i', '6', 'd', '5', 'u', '9', '7',
					'f' },
			{ 'l', 'q', 'u', 'n', 't', '4', 'o', 'f', '%', '1', 'i', '5', '2', '7', 'j', 'w', '?', '!', ',', 'e', 'd',
					'a', 'k', 'b', 'y', '3', 'z', '&', 'c', 'm', 's', '9', 'x', '0', 'g', 'h', 'r', 'v', '6', '.', 'p',
					'8' },
			{ 'q', 'w', 'j', 't', 'b', '6', 'i', '2', '0', 'k', '?', '%', 'm', 'e', '7', '&', 'r', '.', '9', 'd', 'h',
					',', 'n', 'z', 's', 'f', '1', '3', '4', 'a', 'p', 'o', '5', 'u', 'l', 'v', 'y', 'c', 'g', '8', 'x',
					'!' },
			{ '2', '4', '.', 'x', '3', 'e', 'u', 'k', 'f', '0', 'q', 'o', 'z', 'd', '8', ',', 'b', '7', '6', 'c', '5',
					'y', 'a', '1', '?', 'i', 'n', '!', 'h', 'w', 'r', '9', 's', 'j', '&', 'v', 'm', 't', '%', 'p', 'g',
					'l' },
			{ 'z', 'v', 'h', 'm', 'k', '!', 'i', 'f', '6', '%', '3', 's', 'j', '7', 'u', 'a', 'c', 'o', '2', '?', 'y',
					'e', '1', '&', 'b', '5', 'w', '.', '4', 'p', 't', 'l', 'q', '8', '0', 'r', 'd', 'n', '9', 'x', ',',
					'g' },
			{ '%', 't', '3', 'c', '8', 'w', 'b', 'k', 'z', '!', 'h', 'm', 's', 'x', '4', '7', '0', '5', 'd', ',', '1',
					'u', 'y', 'p', 'a', 'e', '.', 'l', 'v', '9', '2', 'q', '&', 'i', '?', 'n', 'j', 'o', 'g', '6', 'f',
					'r' },
			{ '2', ',', 'l', '7', '9', 'u', 'k', '3', 'i', 'g', 'a', '0', 's', 'p', 'q', '&', 'x', 'o', 'y', '?', 'e',
					'6', 'f', 'j', 'n', '.', '1', 'v', 'c', '5', '%', 'm', '4', 't', '!', 'z', 'h', 'r', 'w', 'd', '8',
					'b' },
			{ 'i', 'f', 'm', 'q', 'g', '&', '7', 'l', '5', '2', 'x', 'e', '4', '%', 'a', 'o', 'b', '1', 'h', '6', 'n',
					'z', ',', '8', 'r', 'j', '?', 'c', 'd', 't', 'k', '0', '9', 's', 'y', 'w', '!', '3', 'v', 'p', '.',
					'u' },
			{ 'x', '3', '4', '7', 'o', 'e', 'j', 'u', ',', '%', '.', 'k', 'd', 's', '!', 'l', 'r', 'v', '0', '8', 'h',
					'z', 'q', 'c', 'w', '&', 'n', 'm', 'a', 'b', '?', '1', '6', 'i', '2', 't', 'f', 'y', '9', '5', 'g',
					'p' },
			{ '2', '7', 'c', '!', '3', 'r', ',', '8', '?', '1', 'k', 'g', 'y', 'd', 'i', 'j', '.', 'p', 'n', 'b', 'f',
					's', 'h', 'e', 'm', 'a', 'z', '%', 'u', 'v', '&', 'o', '0', '4', 'w', 'l', '9', 'q', '5', 'x', '6',
					't' },
			{ '0', '2', '!', '6', 'a', 'v', '.', 'j', '&', 'q', 's', 'u', 'w', 'e', 'n', '%', 'p', 'd', '9', '1', '?',
					'l', 'b', 'h', 'i', 'g', 'r', 'f', '5', 'c', 'o', 'm', '4', 'y', ',', 'x', 'z', 'k', 't', '3', '8',
					'7' },
			{ '.', '6', 'q', '&', '!', 'o', 'f', 'i', 'z', '7', 'w', ',', '1', '3', 'y', 'd', 'r', '8', 'l', 'h', 's',
					'9', 'v', '4', '2', 'u', 'k', 't', '5', '?', 'b', 'n', '%', 'e', 'c', 'm', 'p', 'j', '0', 'g', 'a',
					'x' },
			{ 'w', 'l', '.', 'y', '?', 'c', 't', 'n', 'z', '8', '6', 'e', 'm', 'x', '9', 'j', '%', 'd', 's', '0', 'r',
					'u', 'p', 'o', 'v', 'i', 'b', '7', '!', '4', 'q', '3', 'a', 'g', '2', '&', 'k', '1', '5', 'f', ',',
					'h' },
			{ 'v', '&', '3', 'i', 'l', '%', 'w', 'p', 'e', 'c', 's', '7', '1', '?', 'z', 'y', 'u', '8', 'm', 'f', 'q',
					'a', ',', '.', '0', '!', '5', 'g', 'o', 'd', '2', 'j', '6', 'r', '9', 'h', 'x', 'n', 'b', '4', 't',
					'k' },
			{ 'q', '8', 'k', 'o', '0', 'y', 'm', 'l', 't', '5', '7', '3', ',', '9', '4', 'i', 'w', 'c', 'a', '%', '1',
					'r', '2', 'h', 'b', 'e', '6', '?', 'd', 'p', 'n', 's', 'x', 'z', 'f', '&', 'v', '.', 'g', 'u', '!',
					'j' },
			{ 'o', '8', 'm', '0', '7', '5', '&', '9', '%', 's', '1', '?', 'y', 'h', 'f', '2', 'k', 'j', 'p', 'x', '.',
					'3', 'a', 'g', 'e', ',', 'u', 'r', 'q', 'n', 'z', 'v', 'i', 'c', 't', '!', '4', 'b', 'd', 'w', '6',
					'l' },
			{ 'f', '!', '.', '9', 'e', '6', 'm', 'l', 'h', 'c', '%', 'r', 'z', 'g', '0', '5', ',', '4', 'o', 'd', 'y',
					'?', 'b', '2', 'w', 'i', 'v', 'q', 'n', 'u', '3', 'k', 'a', 'j', '7', '1', 's', '&', '8', 'p', 't',
					'x' },
			{ 'd', 'o', 'y', '4', 'g', 'q', 'e', '9', 'm', 'n', 'z', 'a', '6', 'c', 'u', 'v', 'r', 'f', '0', '!', 'w',
					'3', 'x', 'k', 'p', '.', ',', '5', 's', '2', '&', '1', '7', '%', 'i', 'h', '8', 'l', 'b', 't', 'j',
					'?' },
			{ '5', 'f', 'z', '0', 's', '.', 'l', 'c', 'g', '2', '&', 't', 'y', 'k', ',', '6', 'b', '1', '4', 'a', '7',
					'o', 'w', 'j', 'e', 'm', '3', '8', 'q', 'i', '?', 'p', 'n', '9', 'x', 'u', 'v', 'd', '%', 'r', 'h',
					'!' },
			{ 'j', '2', '1', '5', '!', '7', 'c', 'f', 'b', '?', 'y', 'u', 'm', 'k', 'p', 'a', 'r', '&', 'e', 'v', '4',
					'6', 'h', ',', '0', 'l', '3', '9', 't', 's', '8', 'z', 'q', 'w', '.', 'x', 'g', 'n', '%', 'o', 'i',
					'd' },
			{ '6', 'j', 'b', 'r', 'p', 'g', 'd', 'm', ',', 'c', 'x', 'a', 'i', '5', '2', '&', '3', 's', 'h', 't', '7',
					'.', '9', 'o', 'k', 'q', '8', '%', 'w', 'e', '4', 'f', 'u', 'n', 'y', 'v', '!', '0', 'z', 'l', '?',
					'1' },
			{ 'e', 'n', '0', 'o', 'u', 'k', 'd', '8', 'c', '6', '9', 'w', 'l', '7', ',', 'b', 'p', '4', 'm', '.', 's',
					't', 'q', '2', 'h', 'a', '3', '1', 'y', '%', 'z', 'v', '!', 'g', '?', 'j', 'r', 'i', '5', 'x', 'f',
					'&' },
			{ '1', 'r', '0', 'b', 'z', 'l', 'c', 'q', '9', 'h', 'x', 'n', 'p', '?', 'e', 'f', '4', 'd', '!', 'j', 'w',
					'g', '3', 's', '5', 'v', ',', '2', '&', 'a', '%', 'u', 't', '.', '7', '8', 'i', 'k', 'y', '6', 'm',
					'o' },
			{ 'v', '.', '!', 'o', 't', 'p', 'a', '5', '1', '?', '&', '4', ',', '0', 'n', 'z', 's', 'g', '3', 'q', 'm',
					'u', '7', 'w', 'x', 'i', 'k', '6', '8', 'j', 'f', '9', 'y', 'e', 'c', 'l', 'r', '%', 'h', 'd', '2',
					'b' },
			{ 'y', '7', 'm', 'r', '.', '!', 'b', 'x', 'g', 'u', 'h', '?', '4', '8', '5', '3', '0', 'd', '2', 'k', 'i',
					'c', '9', '%', 's', 'v', 'o', ',', 'q', 'l', 't', 'a', 'z', 'w', 'f', 'n', '&', 'p', '1', '6', 'e',
					'j' },
			{ 'q', '!', '%', '0', '1', 'p', 'g', '3', 't', 'r', 'b', 'z', '9', 'x', 'i', '2', 'y', '?', '4', 'a', 'w',
					'&', 'u', '.', '6', 'm', '5', 'd', 'j', 'h', ',', 'e', 's', 'c', 'v', 'n', 'o', 'k', 'l', '8', '7',
					'f' },
			{ 'z', 'n', 'k', '1', 'v', 't', '2', '5', '%', 'c', '.', '9', 'p', 's', 'u', '!', '4', ',', 'a', 'y', 'm',
					'?', '&', 'r', 'l', '0', '6', 'h', '7', 'x', 'b', 'o', 'g', '8', 'e', '3', 'f', 'w', 'j', 'd', 'q',
					'i' },
			{ '?', 'l', '0', 'i', '3', 'b', 'z', 't', 'r', 'a', 'n', '.', '8', 'x', '6', '7', ',', '&', 'u', 'g', 'd',
					'h', 'v', '4', 'w', 'c', 'e', 'm', 'q', 'k', '%', '!', 'y', '2', '1', '5', 's', 'f', '9', 'j', 'o',
					'p' },
			{ 'g', 'y', 'b', 'j', '3', 'p', '?', 'a', '5', 'k', '6', 't', 'r', '0', 'e', 'l', '7', '.', 'd', '1', 'q',
					'!', 'w', 's', 'c', 'm', '9', '2', 'z', ',', 'o', '%', 'n', 'v', '8', 'h', 'f', '&', 'i', 'x', '4',
					'u' },
			{ '9', '0', 'r', '1', 'b', 'd', 'v', '3', 'n', 'p', ',', 'i', 'u', 'q', 'k', '5', 'c', 'j', 'g', '%', 'y',
					'f', '.', 'z', '6', '?', 'l', 'm', 'e', 'x', 'h', 's', '&', '7', '4', 't', 'w', 'o', '!', 'a', '8',
					'2' },
			{ 'z', 'j', 'k', '.', 'm', 'n', 'q', 'u', '7', 'l', '9', '1', 'i', 'y', 'e', 'c', '5', '%', 'f', 'b', 'r',
					'p', '6', '2', '?', '!', '8', '&', 'h', 'w', 's', 't', 'a', '3', '0', ',', 'g', 'v', 'x', 'o', '4',
					'd' },
			{ '%', 'n', '2', 'g', 'y', 'p', 'e', 'h', 'd', '0', 'u', '.', '5', '&', '9', 'v', 'w', '8', 'f', 'k', 'a',
					',', 'l', '6', 's', '?', 'i', 'b', '1', 't', 'x', 'r', 'o', 'z', '3', 'q', 'm', '4', '7', 'c', 'j',
					'!' },
			{ '4', ',', 't', '1', 'h', 'd', 'y', 'x', 'b', 'k', 'o', '9', 'r', 'a', 'c', '8', '.', 'n', '!', '7', 'm',
					'u', 'z', 'q', '5', 'e', 'v', '6', '3', 'j', '0', '%', 'l', 'p', '2', 'g', 'f', 'w', 's', '&', 'i',
					'?' },
			{ '6', 'a', '.', 't', '8', '9', 'p', '1', 'i', '?', 'g', '&', 'k', 'd', '!', 'f', 'n', 'x', 'j', '0', '3',
					'l', 'u', 'w', 'b', '%', 'e', 'y', 'r', ',', 'm', '2', '7', '4', 'h', 'q', 's', 'o', '5', 'v', 'c',
					'z' },
			{ '6', 'b', 'f', 'c', 'i', '&', 'k', '.', '2', '%', '!', 'j', 'p', 'e', 'n', 'x', '7', '1', 'r', '3', 'l',
					',', 'd', 's', 'a', 'u', 'w', 'z', 't', 'o', 'h', '4', '0', 'q', '8', '?', 'v', '5', '9', 'y', 'm',
					'g' },
			{ 'p', 'k', '6', '5', '&', 'u', ',', 's', 'n', 'i', 'w', 'm', 'c', 'q', '?', '3', 'e', 'b', '0', 't', 'd',
					'o', 'v', 'l', 'y', 'z', 'g', '1', '8', '!', '%', 'a', '7', '4', '2', 'h', 'j', 'x', 'f', '9', '.',
					'r' },
			{ '8', '?', '5', 'z', 'i', '6', '4', 'u', '&', 'r', '9', '!', 'b', 's', 'q', 'p', 'v', '2', '%', '1', '0',
					'w', 'l', 'e', '3', 'n', 'm', 'o', 'x', 't', 'j', 'd', 'g', 'f', 'k', 'a', '.', 'h', 'c', 'y', '7',
					',' },
			{ '4', 'd', 'l', '?', '!', 'p', '&', '.', ',', 'm', 'e', 'y', 'r', '8', 'h', 'a', 'w', '3', 'v', '9', 'o',
					'f', '2', 's', 'b', 'j', '7', 'q', 'g', 'u', '6', 'i', 'z', 'k', '0', 'x', 'c', 't', '1', 'n', '5',
					'%' },
			{ 'p', 'e', '2', 'j', 'r', '5', 'd', 'g', 'o', '8', 'b', '7', 'k', '3', 'a', 'x', 'm', '.', 't', 's', '!',
					'9', '0', 'z', 'y', '?', '%', 'h', '6', 'v', 'u', ',', 'l', '1', 'n', 'i', '&', 'q', '4', 'c', 'w',
					'f' },
			{ 'e', 'o', '2', 'c', '1', '0', 'y', '6', '3', 'z', 'r', '%', 'h', 'q', 'n', 'g', 'k', '5', 'p', '!', '4',
					'u', 'l', '&', 'b', 'x', '?', '.', ',', 'w', 't', '7', '8', 's', 'i', 'd', 'a', 'v', '9', 'm', 'j',
					'f' },
			{ 'n', 'i', 's', 'y', 'x', 'q', 'e', '9', '5', 'o', '7', 'r', 'w', '4', 'k', 'j', '1', '%', '?', 'm', 't',
					'p', 'g', '!', 'd', 'l', 'f', 'b', ',', '8', '3', 'u', 'c', '&', '2', 'v', 'h', '6', 'z', '0', 'a',
					'.' },
			{ '4', ',', '!', 'p', 'c', '9', 'd', 'j', '.', '1', '%', 'y', 'f', '5', 'k', '?', 'e', 's', 'm', 'i', '3',
					'u', '7', 'z', 'r', 'a', 'q', '8', 'b', 'h', 'g', '&', 'v', 'x', '6', '0', 'w', 'l', 'n', 'o', 't',
					'2' },
			{ 'k', 'p', '3', 'f', 'a', '!', 'u', 'v', 'r', 'h', 'q', 'g', '%', 'o', 'e', 'c', ',', 'm', '9', 'b', 'z',
					'i', 'w', '8', 'x', 's', 'l', '0', '?', '5', '&', '4', 'n', '6', '2', '.', '7', 't', 'd', 'j', 'y',
					'1' },
			{ '.', 'r', 'p', 'j', 'f', '8', 'w', 's', '!', '%', 'm', 'z', '1', 'c', 'k', '?', 'b', '&', '4', 'o', 'a',
					'g', 'y', 'q', ',', 'e', 'x', 'i', 'n', 'v', '9', 't', '6', 'u', 'h', 'l', '7', '3', 'd', '2', '5',
					'0' },
			{ 'y', 'j', 'g', ',', '0', 'm', '!', '%', 'n', '2', 'z', 'w', 'u', 'b', '7', 'x', 'o', 'h', 'v', 'l', 's',
					'&', '?', 'k', 'q', 'c', '9', 'a', '3', '4', 't', 'p', '5', 'i', 'r', '8', 'f', '1', '.', 'e', '6',
					'd' },
			{ '8', 't', '?', '!', 'g', 'c', '3', '0', 's', '2', 'f', 'd', '%', '&', 'm', 'a', 'h', 'z', 'e', 'p', '4',
					'1', ',', 'o', '9', 'x', '.', 'w', 'n', 'v', '5', 'r', '7', 'y', 'b', 'u', 'i', '6', 'j', 'l', 'k',
					'q' },
			{ 'o', 'k', 't', 'z', '%', '8', 'h', '4', '&', 'm', 'x', '!', '3', '5', 'p', 'j', '7', 's', 'v', 'y', 'f',
					'0', ',', 'c', '?', '9', 'n', 'd', 'a', 'q', '.', '2', 'e', 'u', 'i', '1', 'w', '6', 'g', 'b', 'l',
					'r' },
			{ 'u', '8', 'v', 'o', '!', '1', '5', 'z', 'e', 'j', 'a', 'h', '9', '&', 'y', 'l', 'f', '?', 'm', '6', '4',
					'%', '0', '7', 't', 'k', 'd', '.', ',', '2', 'n', 'i', 'g', 'w', 'p', 'q', 'b', 'r', 'c', 'x', 's',
					'3' },
			{ 'k', 'p', '0', 'a', 'u', '%', 'j', '?', 'y', 'q', 'n', 'r', '.', 'x', 'v', 'o', 't', 'h', '7', ',', 'm',
					'i', 'e', '8', '5', '3', 'f', '2', 'z', '1', 'g', 'w', 'c', 'd', 'b', 'l', '6', '4', 's', '!', '&',
					'9' },
			{ '9', 'y', 'p', '.', '1', '!', 'h', 'e', 'w', '5', 't', '2', '?', 'z', 'b', 'k', 'j', '&', 'q', 'l', 'a',
					'4', ',', 'f', 'm', 'u', '8', 'o', '3', 'x', '0', 'c', 'g', 'v', '7', 'r', '%', 'd', '6', 'n', 'i',
					's' },
			{ '4', 'g', '3', 'x', 'l', 'h', '1', '9', '&', '7', '0', 'v', 'b', 'z', 'm', 't', 'y', '?', 'q', '8', '%',
					'n', 'e', 'r', 'k', 'i', '6', 'j', 'a', 'p', 'c', '!', '5', '2', 'o', ',', 'd', 's', 'w', '.', 'f',
					'u' },
			{ 'm', '.', 'v', '6', 'i', 'b', 'f', '4', 's', '8', '9', 'z', 'l', '&', 'o', 'k', 'u', '5', 'p', '?', 'w',
					'y', 'g', 't', '!', 'q', 'n', 'j', 'c', 'r', '1', '2', 'x', ',', 'e', 'a', '7', '0', '3', '%', 'h',
					'd' },
			{ '!', 'b', 'r', 'k', '9', 'f', '8', 's', 'z', '1', 't', 'h', 'y', '%', '5', 'c', 'a', '4', 'g', 'n', 'l',
					'i', 'j', ',', '0', 'm', 'w', '2', 'u', 'q', '.', '?', 'v', 'o', '&', 'x', '6', 'e', 'p', '7', 'd',
					'3' },
			{ '%', 'i', '1', 'y', 't', '?', 's', 'n', ',', 'w', 'l', '.', 'p', '5', 'r', '&', '!', 'g', '0', '2', 'v',
					'f', '6', 'u', 'c', '7', 'j', 'h', 'k', '9', 'x', '8', 'z', 'm', 'd', 'e', 'b', 'o', '4', 'a', '3',
					'q' },
			{ '0', 'v', 'i', 'x', 'k', 'w', 'l', 'd', '5', '3', '.', 'c', 'z', '?', '6', '1', 't', '!', 'n', 'g', 'e',
					'r', 'p', 'o', 'j', 'a', 'm', '9', 'h', 'b', 's', '4', 'f', ',', '2', '%', 'y', 'u', '7', '8', '&',
					'q' },
			{ 'd', '1', 'l', 'v', '3', 'z', 'w', 'r', '4', '6', '7', '!', 'g', 'e', '5', '9', 'm', 'u', 'h', '?', 'j',
					'p', 'x', 'y', 'a', 'f', '&', 'n', '.', 'i', '0', 'k', '%', 'o', 'b', 'q', '2', 't', '8', 's', 'c',
					',' },
			{ '&', '7', '4', '.', 'c', 'q', 'k', 's', 'p', 'j', 'u', '0', 'z', 'w', 'x', 'd', 'm', '1', '!', 'f', 'o',
					'2', 'i', 'r', '3', 'e', 'y', 'b', ',', 'l', '9', 'v', '?', '5', 'a', 'g', '%', 'n', '8', 't', '6',
					'h' },
			{ '1', '7', ',', '2', 'j', 'z', 'x', 'd', 'm', '!', '3', '%', '9', 'q', 'g', 'a', '4', '0', 'b', '.', 'h',
					't', 'c', 'r', 'y', '5', 'k', 'o', 'i', 'n', '8', '?', 's', 'e', '&', 'p', 'l', 'w', 'v', 'f', '6',
					'u' },
			{ 'z', 'r', '2', '0', '9', '!', 'd', 'f', 't', 'l', 'q', '.', 'p', '1', 'v', '?', '%', 'n', 'u', 'k', '7',
					'4', 'c', 's', 'x', 'j', 'i', 'a', '5', ',', '&', 'e', 'm', 'g', 'y', 'h', '8', 'b', '6', 'w', '3',
					'o' },
			{ 'e', 'k', 'r', 'o', 'd', '1', 'm', 'n', 's', '2', '7', '.', '4', '%', 'p', '9', 'h', '!', '3', 't', '8',
					'i', 'q', 'w', 'l', ',', 'v', 'x', '&', '5', 'j', 'f', 'a', 'c', 'y', 'u', '0', 'z', '?', 'b', 'g',
					'6' },
			{ '5', 'h', '8', 'v', 'l', '1', '%', 't', 'p', 'e', 'b', '2', '&', 'z', 'f', '6', '4', 'y', 'a', 'q', 'g',
					'd', '0', ',', 'o', 'k', '!', 'j', 'c', '3', 'n', 'x', '7', 's', 'u', 'm', '.', 'i', 'r', '9', 'w',
					'?' },
			{ 'j', 'm', 'f', 'w', 'p', 'o', '?', 'h', 'b', 'e', '7', 'q', 'd', 'k', 'c', '5', 'u', 's', ',', 'g', '0',
					'i', '!', '6', 'y', '4', 'z', 'l', '.', '3', '&', '1', '2', 'x', 'r', 'n', 't', '%', '8', 'a', 'v',
					'9' },
			{ 'm', 'c', 'f', 's', '6', 'v', '8', '9', 'p', 't', '%', 'l', 'j', '2', '1', 'g', '3', '7', '!', '.', 'o',
					'4', '5', 'd', 'z', '&', 'r', 'x', 'a', 'k', 'n', '0', 'b', 'i', '?', ',', 'e', 'q', 'h', 'u', 'y',
					'w' },
			{ 't', '4', 'i', '!', 'f', '1', 'r', 'j', 'v', '5', 'b', '9', '%', 'x', '7', '8', 'a', '.', 'm', '?', ',',
					'd', '0', 'n', 'k', 'o', '2', 'z', 'w', 'l', '6', 'c', 'h', 'y', '&', 'q', 'u', 'p', '3', 's', 'g',
					'e' },
			{ 'o', '7', 'j', 'u', ',', 'b', 'm', '1', 't', 'p', '.', '3', '0', '%', 'e', 'q', 'g', '!', 'l', 'f', 'c',
					'&', 'n', '2', 'r', 'z', 'd', 'k', 'v', '5', 'h', 'a', 'i', '4', 'w', '9', '6', 'y', 's', 'x', '8',
					'?' },
			{ '0', 'h', 'f', '3', 'n', '&', 'y', 'v', '8', '1', '.', 'i', 'q', '9', 's', 'w', '6', 'o', 'z', 'm', 'e',
					'2', 'p', 't', '!', 'd', '5', 'g', 'j', '?', 'c', 'x', 'l', 'r', ',', 'u', '4', 'k', '7', 'b', 'a',
					'%' },
			{ '0', '2', 'l', 'k', '6', '!', '3', 'n', '5', '?', 'm', 'w', 'u', 'j', 'q', 'o', 'e', 'v', '9', '7', '.',
					'p', '4', '1', 'c', 's', 'r', 'a', ',', 'y', 'f', 'd', 'x', '8', 'b', '&', 'z', 'g', 't', '%', 'i',
					'h' },
			{ 'a', '%', 'q', 'b', '1', '!', '8', '9', 'f', 'r', '5', 'j', '7', '&', 'm', 'p', '.', 's', 'n', 'k', '0',
					'v', 'i', 'h', '4', 'u', 'o', 'y', 'z', 't', '2', 'l', ',', '6', '3', 'w', '?', 'g', 'e', 'x', 'd',
					'c' },
			{ 'b', '5', '.', 'f', 'd', '7', 't', '0', 's', 'x', 'q', '!', 'g', 'z', '6', '3', 'v', 'k', 'w', '4', 'c',
					'u', 'n', '&', 'y', '9', '2', 'l', 'i', '1', 'j', 'a', 'h', ',', 'o', '8', 'm', '?', 'p', 'r', 'e',
					'%' },
			{ '&', 'w', '7', 'j', '!', 'x', '9', 'z', '8', '2', '?', 'c', 's', 't', '5', 'u', '0', '4', '.', '1', 'b',
					'3', '6', 'y', 'k', 'o', 'i', 'p', 'h', 'a', 'n', ',', 'v', 'q', 'l', 'd', 'e', '%', 'g', 'f', 'm',
					'r' },
			{ '?', '5', '%', 'o', 'h', 'n', 'a', 't', 'c', ',', 'm', 'd', '!', 'v', 'r', '0', 'e', 'g', 'u', 'j', 'p',
					'&', '1', 'w', 'k', '9', 'y', 'q', '3', 'f', 's', '7', '4', '.', 'b', 'x', 'l', '8', 'i', '2', 'z',
					'6' },
			{ '!', 'r', 'a', '?', 'h', 'm', '9', 'p', 'e', 'l', '1', 'n', 'u', 'b', '%', 't', 'w', '6', 'v', 'k', 'o',
					'd', 'y', '7', ',', 'z', '.', '8', '&', 'i', 'g', 'q', 'c', 'x', 'j', 's', '0', '3', '4', '5', '2',
					'f' },
			{ 'm', '%', 'c', 't', '0', 'd', 'n', 'x', 'y', '?', 'l', '7', '1', 'a', 's', '9', 'i', '.', '3', 'k', 'o',
					'v', 'h', '5', '8', 'z', 'p', 'f', ',', 'j', '4', 'r', 'e', '6', 'w', 'u', 'g', 'b', '!', '&', 'q',
					'2' },
			{ 'o', 'n', '6', '?', '7', 'f', '&', '9', 'v', ',', 'a', 'k', '1', 'x', 'q', 'h', '5', 'c', 'd', 'w', 'b',
					'g', '8', '4', 's', 't', 'u', '2', 'l', 'i', 'j', '0', 'm', '3', '%', 'e', '!', 'r', '.', 'y', 'p',
					'z' },
			{ 'p', 'j', 'f', '5', '0', 't', 'b', '?', '%', 'w', '1', '8', 'd', '!', '2', 'c', '&', 'k', 'l', '4', 'z',
					'e', '.', 'q', '3', 'u', 'i', 'y', '9', ',', '7', 'g', 'x', 'r', 'm', 'h', '6', 'v', 'o', 'a', 'n',
					's' },
			{ 'g', 't', 'k', 'z', '9', '7', '%', 'q', '2', 'e', 'p', '0', 'j', 'y', 'r', 'm', 'c', 'x', '1', 'l', '&',
					'a', ',', '.', '?', '3', 'h', 'o', 'i', '4', 'd', 's', 'f', '5', 'n', 'w', '6', 'b', 'u', '8', '!',
					'v' } };

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
	public String setLevel(String textIn) {
		level = randomLevels[findInArrayString(levelkeys, textIn.substring(0,2))];
		return textIn.substring(2,textIn.length());
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
					String temp = setLevel(inString);
					for (int i = 0; i < level; i++) {
						try {
							temp = deEnccrypt(temp);
						} catch (IndexOutOfBoundsException e) {
							encrypt.setSelected(true);
							deEncrypt.setSelected(false);
						}
					}
					String myString = "This text will be copied into clipboard";
					StringSelection stringSelection = new StringSelection(temp);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Out.setText(temp);
				} else {
					Random rnd=new Random();
					int levelSelected=rnd.nextInt(levelkeys.length);
					level=randomLevels[levelSelected];
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = encrypt(temp);
					}
					StringSelection stringSelection = new StringSelection(levelkeys[levelSelected]+temp);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Out.setText(levelkeys[levelSelected]+temp);
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
