package com.javierllaca.text;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.Normalizer;

/**
 * Utilities for manipulating regular expression patterns
 * @author Javier Llaca
 */
public class PatternUtils {
	/**
	 * Pattern containing all accented vowel characters
	 */
	private static Pattern accent = Pattern.compile("[ÁÉÍÓÚáéíóú]");

	/**
	 * Returns copy of str with normalized non-ASCII characters
	 * @param str Input string
	 * @return ASCII-normalized String
	 */
	public static String normalizeEncoding(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Determines whether str contains characters with acute accent
	 * @param str Input string
	 * @return true if character with acute accent is in str, false otherwise
	 */
	public static boolean containsAcuteAccent(String str) {
		Matcher matcher = accent.matcher(str);
		return matcher.find();
	}

	/**
	 * Creates a regular expression pattern from entries in list
	 * @param list ArrayList of Strings with words to be used in pattern
	 * @return Pattern built from String entries in list
	 */
	public static Pattern createRegexFromList(List<String> list) {
		String patternString = "\\b(" + join(list, '|') + ")\\b";
		return Pattern.compile(patternString);
	}

	/**
	 * Returns a string of all items in list separated by separator
	 * @param list List of Strings to be joined
	 * @param separator Separator character to use
	 * @return Joined Strings, null if empty list
	 */
	public static String join(List<String> list, Character separator) {
		if (list.isEmpty()) {
			return null;
		}
		String s = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			s += separator + list.get(i);
		}
		return s;
	}
}
