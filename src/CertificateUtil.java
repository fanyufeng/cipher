import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;


public class CertificateUtil {

	/**
	 * 从 KeyStore 中获得私钥
	 */
	public static PrivateKey getPrivateKeyFromKeyStore(String keyStorePath, String alias, String password) throws Exception{
		// 加载文件到 KeyStore 对象
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream is = new FileInputStream(keyStorePath);
		keyStore.load(is, password.toCharArray());
		is.close();
		// 从 KeyStore 中获得私钥
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
		return privateKey;
	}
	
	/**
	 * 从 Certificate 中获得公钥
	 */
	public static PublicKey getPublicKeyFromCertificate(String certificatePath) throws Exception{
		// 加载文件到 Certificate 对象
		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		FileInputStream is = new FileInputStream(certificatePath);
		Certificate certificate = factory.generateCertificate(is);
		is.close();
		// 从 Certificate 中获得公钥
		PublicKey publicKey = certificate.getPublicKey();
		return publicKey;
	}
	
	/**
	 * 使用公钥加密
	 */
	public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 使用私钥解密
	 */
	public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
}
