/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class for implementing the security algorithm for PIN
 * 
 * @author EPL463
 */
public class SecurityAlgorithm {

	/**
	 * attribute to salt the hash
	 */
	protected byte[] salt;
	/**
	 * attribute of random generator
	 */
	protected Random rnd;

	/**
	 * Constructor, creates salt from random generator
	 */
	public SecurityAlgorithm() {
		salt = new byte[16];
		rnd = new Random(123);
		rnd.nextBytes(salt);

	}

	/**
	 * Create PIN from the given key
	 * 
	 * @param key
	 */
	public void createPIN(String key) {
		try {
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = f.generateSecret(spec).getEncoded();

			System.out.println("hash: " + new BigInteger(1, hash).toString(16));

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			Logger.getLogger(SecurityAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	// this function should be implemented for authenticating the entered user
	// PIN (last 4 digits of the bank account) using the hash key that encrypts
	// the PIN. Use the above API for creating the hash based on the key and
	// comparing with the hashed PIN stored in the "accounts.txt" file.
	/**
	 * Checks the given account number and PIN if they match. Then it generates the
	 * hash of the PIN and checks if its the same from the stored PIN of Account
	 * from the Account Database.
	 * 
	 * @param accountNumber account number
	 * @param key given PIN
	 * @return true if match, false if not
	 */
	public boolean checkPIN(String accountNumber, String key) {
		try {
			String substring = accountNumber.substring(accountNumber.length() - 4, accountNumber.length());
			if (!substring.equals(key))
				return false;
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = f.generateSecret(spec).getEncoded();

			String hashedString = new BigInteger(1, hash).toString(16);
			if (AccountsDatabase.getAccountsDatabase().getAccount(accountNumber).getPIN().equals(hashedString))
				return true;
			else
				return false;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			Logger.getLogger(SecurityAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

}
