import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;


public class CertificateUtil {

	/**
	 * �� KeyStore �л��˽Կ
	 */
	public static PrivateKey getPrivateKeyFromKeyStore(String keyStorePath, String alias, String password) throws Exception{
		// �����ļ��� KeyStore ����
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream is = new FileInputStream(keyStorePath);
		keyStore.load(is, password.toCharArray());
		is.close();
		// �� KeyStore �л��˽Կ
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
		return privateKey;
	}
	
	/**
	 * �� Certificate �л�ù�Կ
	 */
	public static PublicKey getPublicKeyFromCertificate(String certificatePath) throws Exception{
		// �����ļ��� Certificate ����
		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		FileInputStream is = new FileInputStream(certificatePath);
		Certificate certificate = factory.generateCertificate(is);
		is.close();
		// �� Certificate �л�ù�Կ
		PublicKey publicKey = certificate.getPublicKey();
		return publicKey;
	}
	
	/**
	 * ʹ�ù�Կ����
	 */
	public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * ʹ��˽Կ����
	 */
	public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
}
