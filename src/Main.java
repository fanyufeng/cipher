import java.security.PrivateKey;
import java.security.PublicKey;

public class Main {
	// ����ܵ�����
	public static final String DATA = "6daece2d66788cfc235c953405625f80";
	// KeyStore ����
	public static final String password = "123456";
	// �洢�� KeyStore ���֤�����
	public static final String alias = "www.jikexueyuan.com";
	// Certificate ·��
	public static final String certificatePath = "jikexueyuan.cer";
	// KeyStore ·��
	public static final String keyStorePath = "jikexueyuan.keystore";

	public static void main(String[] args) throws Exception {
		// ��Կ����--˽Կ����
		// ������õ�˽Կ��
		PrivateKey privateKey = CertificateUtil.getPrivateKeyFromKeyStore(keyStorePath, alias, password);
		// ������õ���Կ��
		PublicKey publicKey = CertificateUtil.getPublicKeyFromCertificate(certificatePath);
		// ʹ�ù�Կ���м���
		byte[] cipher = CertificateUtil.encrypt(DATA.getBytes(), publicKey);
		System.out.println("result" + BytesToString.fromBytesToString(cipher));
		// ʹ��˽Կ���н���
		byte[] plain = CertificateUtil.decrypt(cipher, privateKey);
		System.out.println("reason" + new String(plain));
	}
	
}
