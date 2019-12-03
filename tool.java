
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
	int level = 15;
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
			{ 'm', '6', '4', 'i', 'w', 'n', '3', 'f', 'q', 'h', 's', '!', '%', 'b', '9', 'c', 'z', '5', 'a', '?', 'g',
					'0', 'u', '1', 'j', '.', 'o', '7', '&', '8', 'l', 't', ',', 'v', 'k', 'p', 'y', 'x', '2', 'r', 'e',
					'd' },
			{ '&', 'u', 'i', 't', 's', 'c', 'f', 'm', 'z', 'g', '?', 'w', '3', 'o', 'j', 'b', '1', 'x', 'h', '9', ',',
					'v', '6', 'r', 'p', '4', '7', 'a', 'l', '2', 'e', 'k', '5', 'q', 'n', '!', '0', '.', '%', '8', 'd',
					'y' },
			{ '1', 'i', 'm', 'f', 'w', 'g', '&', 'p', '4', 'r', 's', '!', 'h', '%', '.', 'b', 'x', 'o', 'z', 'j', '8',
					'v', 'y', '2', '7', '0', 'n', '3', '5', '9', 'd', 'e', '?', 'k', 'l', '6', 't', 'q', 'c', ',', 'u',
					'a' },
			{ 'r', 'm', 'o', 'c', '&', '3', '6', '4', 'w', 'i', '0', 'g', '.', 'j', '2', '8', 'k', 'l', 'z', 't', 'b',
					'7', 'p', 'd', 's', 'h', '!', 'x', 'v', '1', 'f', '5', 'a', 'y', '9', '%', 'e', '?', ',', 'q', 'n',
					'u' },
			{ 's', 'k', '2', '!', '.', 'z', '8', '&', 'c', 'g', 'l', 'o', 'f', 'e', 'p', '7', '6', ',', 'a', 'r', 'd',
					'u', 'b', '4', '0', '9', 'i', '5', 't', 'y', '3', 'n', 'w', '?', 'j', 'v', '%', 'm', '1', 'h', 'q',
					'x' },
			{ '6', 'g', 'm', 'x', 'i', 'k', '%', 'o', 'r', '&', '9', 'l', 'n', '7', '3', 's', 'z', 'u', 'h', 'e', '5',
					'v', 'f', '!', 'j', 'c', '2', 'q', 'd', '0', 'y', '8', 'w', 'a', '4', '.', 'b', 't', '?', ',', 'p',
					'1' },
			{ '.', 'h', '5', 'f', 'j', 'n', '&', 'l', 'w', 'x', 'g', '7', 'i', '1', '0', 'y', '?', '3', '2', 'k', 'm',
					'u', 'c', 't', 's', '8', 'e', '6', 'z', 'd', 'v', 'r', 'b', '%', 'o', '9', '4', 'p', ',', 'q', '!',
					'a' },
			{ 'y', 's', 'j', '.', 'w', 'k', 'p', 'q', ',', 'f', 'v', '3', 'h', '6', 'z', '%', '8', '0', '4', 'r', 'u',
					'b', 'g', 'c', 'd', '2', '&', 'o', 'n', 'a', 'l', '9', 'i', 't', '7', 'e', '!', '5', '?', 'x', 'm',
					'1' },
			{ 'l', 'r', 'g', 's', 'j', '6', 'k', 'c', '.', 'm', 'i', '4', 't', ',', 'a', 'x', 'o', '&', 'p', '%', 'n',
					'1', '?', 'd', '0', 'u', 'f', '!', '7', 'h', '2', '8', 'b', '5', 'y', '3', 'z', 'q', 'e', '9', 'v',
					'w' },
			{ 'q', 'i', 'v', 'n', '5', 'd', '2', '3', 'c', '?', '%', 'x', 'o', 'w', 't', 'e', '!', '.', 'z', 'm', 'f',
					'u', '1', ',', 'l', 's', 'g', 'r', 'p', '0', '9', 'b', '7', 'y', 'k', '6', 'j', 'h', '8', '4', 'a',
					'&' },
			{ 'k', ',', 'c', 's', '!', 'o', '9', 'm', '5', 'g', 'f', '3', '?', '0', 'p', 'h', '%', 'j', 'z', '&', '6',
					'2', 'e', 'r', 'b', 'v', 'd', '7', 'i', 'q', '.', 'y', '8', 'x', 'u', '4', '1', 't', 'n', 'w', 'a',
					'l' },
			{ 's', '7', 'q', ',', '1', 'g', 'n', 'w', '?', '2', '&', 'r', 'z', 'a', '%', '5', '.', '!', '4', 'b', 'p',
					'i', 'm', '9', '6', '0', 'o', 'e', 'f', '8', 'u', 'l', 'x', 'c', 't', 'd', 'k', 'v', '3', 'y', 'j',
					'h' },
			{ '?', '0', 's', 'z', 'k', 'o', 'j', '9', '2', 'e', 'u', '4', 'h', 't', 'i', '8', '.', '1', 'g', 'r', 'w',
					'5', '6', 'p', 'f', '7', 'q', 'l', 'a', '&', '3', 'x', 'b', 'n', 'y', '!', ',', 'c', 'm', 'v', 'd',
					'%' },
			{ 't', '8', 'x', 'c', 'i', 'r', '2', 'j', '4', '9', ',', 'f', '5', 'k', 'b', 'h', '7', '!', '1', 'l', 's',
					'o', 'v', 'm', 'g', '%', 'n', 'p', '&', 'z', 'y', 'u', '.', 'w', '?', 'a', '0', '3', '6', 'd', 'q',
					'e' },
			{ 'w', ',', 'f', 'z', 'k', 'u', '&', '%', 'j', '7', 'd', 'r', 'i', '0', '8', '.', 'x', 'g', '9', 'b', '2',
					'3', 'n', 't', 'o', 'h', 'a', 's', '5', 'c', 'q', 'm', '4', 'e', 'y', '!', '6', 'p', '1', 'l', 'v',
					'?' },
			{ '7', ',', 'w', 'd', '8', '%', 'i', '.', 'g', '4', 'y', 's', '!', '?', 't', '9', '6', 'p', 'k', 'o', '1',
					'h', 'x', 'm', 'u', 'z', 'r', 'c', 'n', 'f', 'b', '3', '&', 'j', 'l', '5', 'e', 'a', 'q', 'v', '0',
					'2' },
			{ 'i', '.', '4', 'a', '8', '0', 'p', 'x', '&', 'v', 'o', '9', 'm', '3', 'y', 'f', '2', 't', 'j', 'e', 'w',
					'1', 'g', '7', 'c', 'd', 'l', 'b', '6', '?', '!', 'h', 'q', 's', '5', '%', 'k', 'r', 'u', ',', 'n',
					'z' },
			{ 'f', 'd', 'v', 'h', ',', '4', '9', '3', 'c', 'n', '.', 'g', 'w', 'b', '7', '2', '5', 'm', '0', 'e', 'y',
					'i', '%', '!', 'r', '1', 'k', 'x', 'z', 'p', 'l', 'u', 's', 'o', '&', '6', 't', '?', 'a', 'q', 'j',
					'8' },
			{ 'g', 'y', 'j', '.', 'w', 'c', 'k', '&', '7', 'q', '2', '?', '4', '3', 'd', 'e', 'h', 'f', 'a', 'i', 'm',
					'u', '0', '1', 'l', 's', '8', 't', '6', '!', '9', 'p', 'v', 'x', 'z', '%', 'b', ',', '5', 'r', 'n',
					'o' },
			{ '8', ',', 'k', 'y', 'e', 'c', 'o', 'u', '9', '2', '4', 't', 'i', 'd', '1', 'g', '.', 's', 'x', '!', 'j',
					'h', '&', 'a', '3', 'n', '0', 'w', 'm', 'b', 'l', 'r', 'v', '5', '7', 'p', '?', 'f', '6', 'q', 'z',
					'%' },
			{ 's', 'h', 'd', 'r', '8', 'j', 'w', 'o', '1', '0', 't', 'u', ',', 'l', '3', 'e', 'z', 'p', '4', '7', '!',
					'n', '6', 'f', 'x', 'k', 'y', '&', '5', 'a', '?', '.', 'v', 'q', 'g', '2', 'i', '%', '9', 'c', 'b',
					'm' },
			{ 'v', 'z', '3', 'm', 'c', 'l', 'k', '4', '.', '6', 's', ',', 'w', '9', 'u', 'j', 'p', '?', 'i', 't', 'd',
					'q', 'h', 'x', '2', 'n', '1', 'a', '&', '%', 'e', 'y', '!', 'g', 'r', 'b', 'f', 'o', '8', '7', '0',
					'5' },
			{ 'k', '?', 'u', '.', 'g', 'i', 't', '9', 's', 'p', 'n', '3', '&', 'v', 'r', 'l', ',', 'b', '!', 'a', '7',
					'h', 'j', '8', 'c', 'y', '0', '2', '4', 'q', 'f', '6', 'o', '5', 'd', '1', 'm', 'w', 'z', '%', 'x',
					'e' },
			{ 'd', '8', '9', 'r', '4', '1', 'h', '0', 'b', 'f', ',', 'v', 'w', 'x', 'a', 'u', 'p', 'i', '2', 'e', 'g',
					'j', '.', 't', 'z', 'q', 's', '5', 'l', 'o', 'y', '7', '%', 'k', 'n', '3', 'c', '6', '&', '!', '?',
					'm' },
			{ 'o', 'n', 'h', 'g', '7', '0', '8', 'p', '5', 'w', '1', 'x', 'l', 't', '.', 'q', '!', 'j', 'i', 'c', '4',
					'u', '?', 'a', '2', '9', 'r', '&', 'd', 'm', 'z', 'f', 'e', '6', ',', 'b', 'y', 'v', 's', '%', 'k',
					'3' },
			{ '7', 'm', '5', '6', 'p', 'c', '2', '1', 'h', '4', 'i', 'q', 'z', '.', '?', '0', 'd', ',', 'w', 'y', 'b',
					'l', '3', '8', 'u', 'v', 'j', 'o', '9', 't', '%', '!', 'x', 's', 'n', 'g', 'e', '&', 'r', 'a', 'f',
					'k' },
			{ 'q', 'a', 'h', '7', 'v', '1', '?', 'j', '.', 'u', '9', 'e', 's', 'z', '!', 'w', 'd', '2', 'o', 'x', 't',
					'f', '4', '3', 'c', 'i', 'n', '0', 'y', '5', '6', '&', 'm', 'l', '%', 'k', ',', '8', 'b', 'p', 'r',
					'g' },
			{ 'm', '.', 'o', 'i', 'x', '6', '3', '4', '8', 'z', 's', 'u', 'p', '9', 'c', 'r', 'g', '!', 'y', '0', 'f',
					',', 'a', '%', 'k', '1', 'w', 'v', '2', 'l', 'b', 'q', 'j', 'n', '5', 'h', 't', 'e', '?', 'd', '7',
					'&' },
			{ 'y', '3', '9', '2', '?', 'c', 'o', 'l', 'h', 'm', '4', '1', 'u', 't', 's', 'q', 'x', 'k', 'b', '!', 'v',
					'r', 'e', 'i', 'n', '%', '7', 'j', 'z', 'a', 'p', 'd', 'f', ',', 'w', '&', '8', '.', '6', '5', 'g',
					'0' },
			{ '?', 'x', 'q', 'd', 'k', 'r', '3', 'b', 't', 'v', '&', 's', '8', '%', '9', 'i', 'o', 'j', '0', '7', 'w',
					'e', '!', 'y', 'u', 'z', '2', 'a', '4', 'p', 'f', 'n', 'h', '.', 'l', 'm', 'g', '1', '5', 'c', ',',
					'6' },
			{ 'x', 'p', 'f', 'q', 'd', 'h', '7', 's', '8', '%', 'n', 'g', 'v', 'l', '1', 'c', '?', '0', '9', 'z', 'y',
					'!', 'm', 't', 'j', '6', '4', 'a', 'k', 'w', '3', '.', 'r', '2', 'b', '5', 'i', 'o', '&', 'u', ',',
					'e' },
			{ '%', 'q', '8', '0', 'k', 's', ',', 'x', '7', 'j', '2', 'y', 'd', 't', 'a', 'i', 'e', '3', 'h', '6', '!',
					'4', 'l', 'f', 'w', '1', 'z', 'r', '9', 'u', '.', 'g', 'b', 'v', 'o', 'm', '&', 'p', '?', 'c', 'n',
					'5' },
			{ 'q', 'b', 's', 'a', 'p', 'z', 't', '8', 'w', 'l', 'k', '%', 'y', '1', '6', '0', 'e', 'g', 'x', '5', 'c',
					'4', '.', '7', 'j', '3', 'u', 'n', 'f', '&', ',', 'm', 'r', 'd', '2', 'v', 'o', 'h', '?', 'i', '9',
					'!' },
			{ 's', 'u', '9', 'r', 'w', '3', 'e', 'q', 'a', 'i', 'f', 'b', 't', 'o', '?', '6', '!', 'm', '8', '.', '2',
					',', '4', 'j', 'v', 'd', 'g', 'h', 'l', '1', 'p', '%', 'k', 'c', '5', '0', 'x', 'y', '&', 'z', 'n',
					'7' },
			{ '%', '8', '.', '7', 'o', 'p', '6', ',', '&', 'f', 'c', 'r', 'n', '4', '5', 'k', 's', 'i', '?', 'j', 'x',
					'9', 'd', '0', 'a', 'e', 't', '!', '1', 'q', 'v', 'h', 'l', 'y', '3', 'g', 'z', 'm', '2', 'u', 'w',
					'b' },
			{ 'j', 'v', 'l', 'h', 'c', '!', 't', 'f', '1', 'i', 'x', '5', 'a', ',', 'u', 'q', 'd', '%', 'e', '6', '4',
					'r', 'n', '8', '0', '2', 'p', 'o', 'b', 'g', 'k', '?', '7', 'z', 'm', 's', '3', 'w', '9', '.', '&',
					'y' },
			{ 'y', 'e', 't', 'l', 'f', 'a', '9', '.', 'z', '2', '?', 'u', '4', '6', '0', '!', 's', 'g', 'j', 'r', 'm',
					'q', '3', 'v', '1', 'o', 'd', 'k', 'p', '7', 'i', ',', 'x', '%', 'n', 'w', 'c', '&', 'h', '8', '5',
					'b' },
			{ '4', '1', 'u', 'x', 'j', '.', 'k', 's', 'l', 'w', 'v', '3', '6', '&', '8', '%', '5', 'n', '!', 'h', 'b',
					'0', 'r', 'm', 'q', 'f', 'p', 'i', 'g', 'a', 'c', 't', '9', '7', '?', 'z', 'e', 'y', '2', 'o', 'd',
					',' },
			{ 'a', '3', 'g', 'q', 'y', 'v', 'o', 'j', '8', 'k', '4', 'w', '%', '5', 'f', '0', 'c', 'z', 'u', 'n', '6',
					'b', 'p', 's', ',', '9', 't', 'r', 'h', '.', '?', 'l', '&', '!', 'i', 'd', '1', '2', 'm', 'x', 'e',
					'7' },
			{ 'g', '!', 'c', 'v', 'w', 'e', '.', 'i', '3', 'k', 'o', 'a', '%', '7', 'j', 't', 'u', '2', 's', 'q', '0',
					'z', 'f', 'y', 'l', '9', ',', '1', 'd', '&', 'n', 'p', 'r', 'b', '8', 'h', '4', '6', 'm', 'x', '5',
					'?' },
			{ 'z', '2', 'v', '9', 'j', 'a', 'l', '%', 'h', 'g', '3', '?', 'b', 'e', '5', 'd', 'r', 'm', 'p', '&', 'y',
					'c', 'k', ',', '1', 'o', '.', 'i', '6', '0', '8', 'u', 'w', 'n', 'x', '4', '!', 's', 'q', '7', 't',
					'f' },
			{ 'i', 'v', '9', 's', '4', '?', 'g', 'r', 'a', 'c', 'e', 'q', 'z', '8', 'w', 'y', 'b', 'h', '!', '5', 'f',
					'n', '2', 't', 'x', '.', '0', 'k', ',', 'd', '1', '7', 'j', '3', 'o', 'm', '&', '6', '%', 'p', 'u',
					'l' },
			{ 'y', 'b', '0', '2', '.', '&', 'q', 's', 'n', '5', ',', 'h', 'm', 'e', 'o', '4', '7', 'w', '8', 'd', 'f',
					'g', '9', 'k', 't', 'c', 'v', 'l', '6', 'a', '!', '?', 'u', '1', 'p', 'i', 'r', '%', 'j', '3', 'x',
					'z' },
			{ '8', 'n', 'h', ',', 'b', '3', 'g', 'a', 'i', '&', 'm', '4', 'x', 't', 'l', 'e', 'v', '0', '2', '6', 'y',
					'r', '?', '.', 'f', 'q', 'p', '9', '%', '1', 'o', 'd', 'w', 's', 'z', 'j', '!', '5', 'c', 'u', 'k',
					'7' },
			{ 'y', 'c', 'b', 'v', 'a', 'x', '!', ',', 'd', '8', 'r', 'j', '.', '5', 'p', '%', '?', 'h', 'q', 'n', 'i',
					'9', 'l', 'g', '4', 'k', '2', 'z', '&', 't', 's', '7', '3', '6', 'f', 'u', 'm', 'e', 'w', '0', 'o',
					'1' },
			{ 'a', '8', 'l', '&', 'y', 'n', 't', 'c', 'o', 'k', 'h', 'r', '2', 'i', 'j', '1', 'q', '0', ',', '7', '!',
					'4', 'u', 'e', '3', 'b', 'w', 'p', '5', 'g', 's', '%', 'z', 'v', '.', 'f', '9', '6', 'd', 'm', 'x',
					'?' },
			{ '0', 'm', 'r', 'l', '1', 'c', 'b', '8', '6', ',', 'j', 'e', 's', 'q', '9', 't', 'z', '&', '!', 'p', 'i',
					'.', 'd', 'o', 'w', 'a', 'k', '?', '7', '2', 'u', '4', '5', 'y', 'f', '%', 'h', '3', 'v', 'n', 'x',
					'g' },
			{ '5', '6', '0', 'a', 'c', 'r', '.', 'o', 'l', 'p', 'x', 'e', 'w', 'g', '2', 't', 'b', '3', 'z', 'q', 'i',
					'd', 'f', 'v', '?', 'j', '1', '7', 's', 'y', '8', 'm', '&', 'n', '!', '%', 'h', 'u', '4', 'k', ',',
					'9' },
			{ '5', '9', 'r', ',', 'i', '.', 'j', '1', 'n', 'd', 'y', 'v', 'e', 'x', '2', '3', '8', '0', 'f', '!', 'a',
					'g', 'l', 'o', 'b', 'z', 'q', 'c', 'u', '?', '%', 'h', 'k', '7', 'p', 'm', '&', '6', 's', '4', 't',
					'w' },
			{ 'g', 'b', 'o', '0', 'z', '8', '?', 'm', 'f', '5', '2', 'q', 't', '.', 'a', '!', '1', 'i', '%', 'h', 'n',
					'w', 'x', 'd', 'u', '&', ',', 's', '7', '4', 'l', 'k', 'y', 'p', 'j', '6', 'r', 'v', '3', 'e', '9',
					'c' },
			{ 'h', '5', '1', 's', 'y', 'z', 'd', 'v', 'i', 'c', 'k', '4', 'u', 'l', 'n', '0', 'o', 'r', 'q', '!', '8',
					'p', '6', 'x', ',', 'b', '3', 'e', 'm', 'w', 't', '.', 'a', '?', '7', '2', 'g', '%', 'j', '&', 'f',
					'9' },
			{ '5', 'u', 'i', 'a', '&', '?', 'p', 'e', '%', ',', 'y', '8', 'w', '.', 'k', 'g', '6', 'l', 'f', '3', '2',
					'q', 'c', 'h', '0', 'v', 'z', 't', '7', 'x', 'o', 'n', 'j', 'd', 'b', 'm', '9', '4', 's', '!', '1',
					'r' },
			{ ',', '0', '?', 'j', '1', 'k', '5', '8', 'f', '.', '%', 'l', 'a', 'v', '&', '2', 'e', 'm', 's', '4', 'p',
					'b', 'n', 'z', 'h', 'i', 'x', '9', 't', 'y', '7', 'o', '6', '!', '3', 'q', 'd', 'w', 'c', 'u', 'g',
					'r' },
			{ '.', 'g', '7', 'p', '?', 's', 'i', 'o', '6', '3', 'e', 'k', 'j', 'v', '4', 'h', '5', 'f', 'c', 'm', 'x',
					'y', '2', '0', '9', 'd', '8', '!', ',', 'n', '&', 'a', 'l', 'w', '%', 'u', 'r', 'z', 't', 'b', 'q',
					'1' },
			{ '9', 'j', '.', '?', 'h', 'x', '6', 'a', 'v', '1', 'o', 'g', '!', 'e', 's', 'd', 'k', 'i', 'w', 'n', 'c',
					'5', 'f', 'y', '%', 'b', 'u', '4', 'r', 'l', ',', '0', '&', '3', 't', '2', 'q', 'z', '8', 'm', '7',
					'p' },
			{ '1', 'n', '&', 'a', 'j', 'w', '7', 'm', '.', 'x', 'y', '5', '?', 'z', 'l', 'h', '9', 'b', '0', '!', 'o',
					'3', 's', '4', 'd', 'c', 'i', 'v', 'p', 't', '6', '%', 'q', 'r', '2', 'e', '8', 'f', 'u', 'g', ',',
					'k' },
			{ 'b', 'l', 'w', 'i', 'y', '?', '5', 'q', 't', 'c', 'z', 'v', 'd', '!', 'a', '&', 'x', '%', '.', 'o', '7',
					'9', '2', 'h', 's', 'm', '0', '1', 'u', 'f', '6', 'g', 'j', ',', '8', '3', '4', 'e', 'n', 'r', 'k',
					'p' },
			{ 'w', '4', 'v', ',', 'm', '&', 'o', 'x', '%', 'y', 'c', 'h', 'j', '0', '.', '3', '5', '2', 'p', 's', 'l',
					'9', 'n', 'f', '6', 'd', 'e', '?', 'g', '8', 'z', 't', 'k', 'q', 'a', '1', '!', 'i', 'r', 'b', 'u',
					'7' },
			{ 'g', '&', '2', '6', '!', 'o', 'l', 'n', '?', '8', 'a', 'f', 'y', '5', '.', 'q', 'd', 'u', '1', 'e', ',',
					'm', '3', '4', 'i', 'r', '0', '9', 'k', 's', '%', 'c', 'v', 'b', 'p', 'w', '7', 'z', 't', 'h', 'j',
					'x' },
			{ '2', '!', 'b', 'n', '%', '7', 's', '3', '5', '0', 'c', '8', 'u', 'a', 'd', 'f', '?', '6', 'j', '9', '4',
					',', 'r', 'q', 'g', 'x', 'm', 'o', '1', 'v', 'p', 'h', 'k', 'z', '&', 't', 'w', 'e', 'i', '.', 'y',
					'l' },
			{ 'c', '&', '3', 'g', 'd', 'f', 'a', 'u', 'v', 'x', ',', 'h', '!', '4', 'w', '6', 't', 'r', 'q', 'm', '2',
					'b', '.', 'i', 'l', 'k', '1', '8', 'z', 'p', '%', 'y', 'o', '?', '9', '0', 's', '7', '5', 'j', 'e',
					'n' },
			{ 'v', 't', '3', '6', '%', 'b', 'k', 'i', 'o', 'a', '9', '.', 'x', 'r', '8', '4', '7', 'j', ',', 'y', 'n',
					'h', '5', 'p', 'f', '?', 'z', '1', '!', 'e', '&', 'c', '2', 's', 'm', 'g', 'd', 'u', 'q', '0', 'w',
					'l' },
			{ '8', '?', 'o', 'b', 'j', 'g', 'f', 'r', 'l', 'n', 'v', 'c', 'y', '!', '3', 's', 'h', '4', 'u', 'w', 't',
					'%', '7', 'k', '&', '2', 'd', '1', 'p', 'i', 'a', 'z', '9', 'm', ',', '5', '0', 'q', 'e', 'x', '6',
					'.' },
			{ '5', 'u', 'k', '6', 'q', '3', '1', 'l', 'e', 'r', 'i', 'd', '7', '?', 's', 'x', 'c', 'a', 'w', 'h', '4',
					'z', 'v', '!', 'o', '&', 'f', 'j', 'g', 'y', '8', '9', '.', 'p', '%', ',', '2', 't', 'n', 'b', 'm',
					'0' },
			{ '1', 'q', '8', 'n', 'e', 'y', ',', 'b', 'c', 'h', '5', '4', 'g', '?', '2', 'j', '7', 'z', 't', 'r', '9',
					'!', 's', '&', 'o', '%', 'f', 'd', 'w', 'i', 'p', '0', 'k', '.', '3', 'v', 'm', '6', 'l', 'u', 'x',
					'a' },
			{ 's', '5', '6', 'h', '&', '0', 'z', 'r', '4', 'l', '?', '1', '7', '%', 'm', '2', 'k', '8', 'x', 'a', 'e',
					'd', 'c', 'w', 'q', 'f', 'g', '!', 'n', 'u', '.', 'y', 'p', 'i', 'j', 't', '3', 'v', 'b', ',', '9',
					'o' },
			{ '9', 'p', 'f', 'j', 'i', 'm', '%', '4', 'v', 'k', ',', '8', 'c', '6', '.', 'e', '7', 'q', 'd', 'o', 'r',
					'b', '!', 's', 'h', '3', 'x', 'l', '1', 't', 'y', '2', '?', 'w', 'z', 'n', 'a', 'u', '&', '5', 'g',
					'0' },
			{ 'j', 'g', 'q', 'z', '3', 'r', '5', 'u', 'o', 'v', '8', '7', 'n', 'h', 'c', '!', 'd', 'm', 'f', 'a', '1',
					'i', 'w', 'k', 'l', 'y', '%', '?', '&', '6', 'x', 'b', 's', ',', '4', '9', '.', 'e', '2', '0', 'p',
					't' },
			{ '2', 'r', 'v', 'k', '5', '6', 'q', 'e', 'w', '!', 'u', 'g', '%', 'm', '.', 's', 'y', 'd', 'a', 't', '9',
					'h', 'l', 'z', '3', 'n', '7', '4', '8', 'p', '?', 'i', 'b', 'j', '0', ',', 'o', 'f', 'c', '1', '&',
					'x' },
			{ '3', 'g', 'h', ',', 'f', 'r', 'w', 'n', 'p', 'o', 'k', '1', 'd', '8', 'u', 'l', '0', '9', 'b', 'q', 's',
					'i', '4', '5', '!', '2', 'm', 'z', 'j', '%', 'v', 'a', 'x', '6', '&', 'c', '7', 'y', '?', '.', 'e',
					't' },
			{ '&', '9', 'w', 't', '3', 'r', '5', 's', 'l', 'y', 'k', 'h', 'i', 'b', '8', 'a', 'q', ',', 'c', 'e', '.',
					'v', '?', 'j', '2', '4', '7', '0', '1', 'u', 'p', 'z', 'g', 'd', 'n', '%', 'm', 'o', '!', 'f', 'x',
					'6' },
			{ '0', ',', '1', 'y', 'g', 'v', 'm', 'o', '%', '5', 'u', 'd', 'b', 'h', 't', '?', 'l', 'i', '&', 'n', 'x',
					'e', 'p', '.', 's', 'f', '2', '6', 'a', 'c', 'w', '!', 'k', '3', 'j', '4', 'q', '8', '7', '9', 'z',
					'r' },
			{ '!', '0', 'p', 'v', 'o', 'q', '2', 'a', 'd', 'z', 'h', ',', 'i', 'm', '4', '5', '8', 'r', '6', '?', 'y',
					'l', 's', 'w', 'e', 'b', '.', 't', '3', '&', 'j', '1', 'n', '%', 'k', 'x', 'f', '9', 'u', 'g', 'c',
					'7' },
			{ 'p', 'o', '7', 's', 'z', '1', 'f', 'w', 'e', 'v', 'd', 'h', '6', 'r', '.', '2', 'n', 'j', '3', 'c', 'u',
					'k', '5', 'l', '!', 'a', '%', 't', '0', 'i', 'm', '8', '9', 'y', ',', 'b', '?', 'g', 'x', '&', 'q',
					'4' },
			{ '9', 'n', 'o', 'c', 'r', '7', '1', 'b', 'i', 'v', 'a', 'w', 'u', 'l', 'e', 'g', '&', ',', '4', '2', '5',
					's', '%', '.', '6', 'm', 'd', '8', 'x', '?', '!', 'q', 'f', '3', 'k', 'y', '0', 'p', 't', 'z', 'h',
					'j' },
			{ 'a', 'w', '4', 'p', 'j', 'v', '7', 'o', '1', '%', '!', '0', '.', '6', '&', 'u', 'g', 's', 'y', 'q', 'l',
					'?', 'b', 'z', 'k', 'h', 'c', 'x', ',', 'f', 'i', '3', 'e', 't', '9', 'r', '5', 'n', 'd', 'm', '8',
					'2' },
			{ 'n', '%', '1', '0', 'x', 'o', 'h', 'l', 'q', 'b', '!', '8', 'm', 'z', 'w', '5', 'e', 'v', ',', 'a', '4',
					'?', '.', 'c', 't', 'g', '3', 'j', '&', '7', 'u', '2', 'i', 'p', 'r', 's', 'k', 'y', 'f', 'd', '6',
					'9' },
			{ 'o', '1', '&', '2', '4', 'h', 'q', 'g', 'j', ',', 'n', 's', 'd', 'p', '?', '%', '7', 'm', 'c', 'u', 'f',
					'v', 'e', '.', 'i', 't', 'k', '3', 'y', '5', '8', 'a', 'l', '9', 'r', '0', 'w', '!', 'z', '6', 'x',
					'b' },
			{ '.', '5', 'o', '3', 'm', 'p', 'h', 'g', 'k', 'f', 'w', 'j', 'c', 'q', 's', '1', '2', 'y', 'x', '%', ',',
					'u', '4', '&', 'l', 'v', '8', 't', 'i', '!', 'z', '6', 'e', 'b', '0', 'r', '7', 'n', 'd', 'a', '9',
					'?' },
			{ '&', '2', 'y', '%', 'l', '.', '3', '?', 'j', 'b', '8', 'g', 'r', ',', 'z', 'd', 'q', '6', 'm', 's', 'x',
					'k', '!', '7', 'w', '0', 'u', 'c', 'e', '1', 'f', 'n', 'i', '9', 'h', 'a', '5', 'o', 'p', 't', '4',
					'v' },
			{ '1', 'g', 'u', '!', '6', 'h', 'z', 'j', ',', 'y', '7', 'i', '0', 't', 'r', 'v', '3', '9', '5', 'f', 'e',
					'l', 'o', 'x', 'n', '8', 'd', '4', 'c', 'q', 'k', 'b', '?', '&', 'p', '%', 'a', '2', 'w', 's', 'm',
					'.' },
			{ 's', '2', '.', 'c', ',', 'b', 'd', '8', '%', 'i', 'n', 'z', 'e', 'a', 'y', '7', '4', 'l', 'x', '6', 'r',
					'm', 'j', '5', '?', '0', 'p', 'k', '&', 'f', 'q', 'u', '3', 'w', 'g', 'v', 'h', 't', '1', '!', 'o',
					'9' },
			{ '8', 'y', 'k', '4', 'c', 'z', '!', '7', 'q', 'r', 's', 'b', '%', 'm', 'h', ',', 'p', '0', 'u', 'w', '6',
					'a', 't', 'e', '&', 'f', 'n', 'i', '1', 'l', '.', 'o', '9', '?', '2', 'j', 'x', 'v', 'd', '3', 'g',
					'5' },
			{ '6', 'k', 'u', 's', 'r', 'w', '&', '.', 'g', '7', '8', '5', 'x', '9', 'l', '0', '4', 'n', '1', 'b', 'e',
					'!', '3', 'o', '2', 'p', 'a', 'v', 't', 'j', 'f', '?', ',', 'q', 'c', 'i', 'y', '%', 'h', 'd', 'z',
					'm' },
			{ '0', '%', 'k', 'l', 'g', '?', '8', '5', 'm', 'f', 's', '4', 'p', 'd', 'e', 'u', '7', 'o', '2', 't', 'q',
					'h', 'x', 'y', 'b', '&', '.', '!', 'r', 'i', 'a', 'n', ',', 'j', 'w', 'z', '3', '6', '1', 'v', 'c',
					'9' },
			{ 'b', 'n', '3', 'f', 'y', 't', '2', 'k', '0', 'o', 'm', 'z', ',', 'r', 'd', 'h', 'e', 'q', 'a', 'c', '8',
					'&', '9', 'x', '5', 'v', '7', 'j', '?', 'p', 'u', '.', '6', '%', '!', 's', 'i', 'l', '1', 'w', '4',
					'g' },
			{ 'q', '&', 'b', 'u', '9', '.', ',', 'c', '!', '3', 'k', 'y', 'e', 'v', '2', '8', 'o', 'p', 'm', '5', 'h',
					'l', 'j', 'w', '4', 'i', 't', 'x', 's', 'a', '0', '?', 'd', 'n', '1', '%', 'r', 'g', 'z', '6', 'f',
					'7' },
			{ 'r', '!', '3', 'a', 'g', '8', '0', 'p', 'w', 'c', 's', 't', '.', '7', '&', 'q', 'o', '?', 'u', 'x', 'n',
					'e', 'z', '4', 'l', '5', 'h', ',', '%', 'b', 'd', '9', 'k', 'v', 'm', 'f', 'y', 'i', '2', '6', '1',
					'j' },
			{ '5', '?', 'd', '!', 'w', 'c', 's', 'a', '%', 'e', 'l', 't', '&', 'q', '.', 'x', 'f', 'k', 'h', ',', '2',
					'p', 'g', '7', '6', 'm', 'y', 'v', 'i', 'b', 'j', '9', 'o', 'z', '1', 'n', '8', 'u', '3', '4', 'r',
					'0' },
			{ 'l', '5', 'y', 'z', '2', 'v', 't', 's', 'n', 'c', '8', 'm', 'p', 'i', ',', 'f', 'x', '.', 'j', 'r', 'u',
					'q', '7', 'o', '9', 'd', 'b', 'w', '&', 'g', '?', '!', 'a', '1', '6', '0', 'e', '3', 'k', '%', 'h',
					'4' },
			{ 'r', 't', '5', 'n', 'u', 'w', 'b', '3', 'f', '?', 'p', 'g', 'v', '4', 'y', '2', 'h', 'q', 'c', '1', 'a',
					'7', '!', '6', '&', 'l', 'z', 'm', 'o', 'i', 'x', ',', 'e', '9', '0', 'k', '8', '.', 'j', 's', 'd',
					'%' },
			{ 't', '&', 'e', 'f', '5', 'x', ',', '!', 'j', 'b', 'n', '2', '%', 'd', '8', 'y', 'o', '9', 'i', 'p', 'a',
					'g', '.', 'h', 'q', 'l', 'm', 'c', 'z', 'r', '7', 'v', 'u', '?', '0', '1', '3', 'k', 's', '6', '4',
					'w' },
			{ 'b', '&', 's', 'w', 'l', 'q', ',', '7', 'g', '2', '5', 'e', '3', 'p', 'k', 'z', 'h', 'n', 'm', 'c', '9',
					'!', '0', 'o', 'j', 't', 'v', 'r', 'y', '%', 'd', 'x', '4', '1', '8', '6', 'a', '?', 'u', '.', 'i',
					'f' },
			{ 'i', '0', 'l', '%', 'v', '6', '9', 't', ',', 'n', '5', '&', 'j', 'c', 'f', 'x', 'd', 'g', '4', 'r', 'u',
					's', 'k', 'w', 'b', '1', 'p', 'h', '2', 'm', 'z', '8', 'y', '.', 'q', '7', 'a', 'o', '3', '!', '?',
					'e' },
			{ '1', 'm', ',', 'f', 'r', 'p', 's', '3', 'u', 'a', 'v', 'h', 'c', 'd', '&', 'j', 'g', '%', '7', 'q', 'x',
					'9', 'w', 'y', '0', '5', '.', '!', '2', 'k', 'n', 'o', 'z', '6', 'i', '8', '4', 'b', 'e', 't', '?',
					'l' },
			{ ',', 'v', 'p', 'n', '.', 'u', 'o', 'd', '2', '8', '1', '6', 'x', 'j', '4', 's', 'g', 'f', 'a', 'i', '%',
					'c', 'l', 'k', 'e', '7', 'w', '?', 'm', 'q', '&', 'z', 'y', 'b', 't', '0', '!', '5', 'r', '9', 'h',
					'3' },
			{ 'm', 'l', 'j', 'i', '%', '4', 'd', 'n', 's', 'r', '5', 'a', ',', 'w', '.', '!', 'k', '9', '2', 'f', '8',
					'x', '&', 'v', 'o', '0', 'g', 'q', 'p', 'b', 'z', '6', '1', 'h', 'u', '?', '7', '3', 'c', 'e', 'y',
					't' },
			{ '%', ',', 'u', 'd', 'c', 'p', '!', '&', '8', 'v', 'm', '5', 'j', 'e', 's', 'r', 'w', '6', 'f', '1', 'g',
					'0', '3', 'z', '2', 'h', 'a', 'o', 'k', '9', 'n', '?', '7', 't', '4', 'b', '.', 'y', 'x', 'i', 'q',
					'l' },
			{ '&', 't', 'q', ',', 'j', '5', '!', '?', 'r', 'x', 'f', 'w', 'y', 'z', 'a', '%', 'g', '9', '.', 'b', 'c',
					'd', '3', 'e', '0', 'o', 'v', '7', '6', 'p', '4', '8', 'm', '2', '1', 'l', 's', 'h', 'k', 'n', 'u',
					'i' },
			{ '6', 'r', 'l', 'c', '8', 'g', '0', 'o', 'k', 'b', '?', ',', 'u', 'v', 'q', 'y', '!', 'f', 'd', 'z', 'h',
					's', 'e', 'p', '%', '5', 'n', '9', '3', '&', 'x', '7', '2', 'j', 'a', 'w', 'm', '1', 'i', '4', 't',
					'.' } };

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
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = encrypt(temp);
					}
					String myString = "This text will be copied into clipboard";
					StringSelection stringSelection = new StringSelection(temp);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
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
