package main;

abstract class Cipher {
  protected String alphabet;

  protected Cipher() {
    this.alphabet = getAlphabet();
  }

  protected static String getAlphabet() {
    StringBuilder alphabet = new StringBuilder();

    for (int i = 65; i < 91; i++) {
      alphabet.append((char) i);
    }

    return alphabet.toString();
  }

  protected String getShiftedAlphabet(int key) {
    return this.alphabet.substring(key) + this.alphabet.substring(0, key);
  }

  abstract String encrypt(String input);

  abstract String decrypt(String input);
}
