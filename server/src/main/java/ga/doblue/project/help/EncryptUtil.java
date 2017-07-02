package ga.doblue.project.help;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;


public class EncryptUtil {
    private static final String key = "!+Doblue+!";
    public static KeyPair rsaKey = null;
    public static KeyPairGenerator clsKeyPairGenerator;
    public static KeyPair clsKeyPair;
    public static KeyFactory fact;
    public static RSAPublicKeySpec clsPublicKeySpec;
    public static RSAPrivateKeySpec clsPrivateKeySpec;
    public static Key clsPublicKey;
    public static Key clsPrivateKey;

    public static String getRSA(String msg) {
        String rsaMsg = "";
        msg += key;
        try {

            if (clsKeyPairGenerator == null) {
                clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
                clsKeyPairGenerator.initialize(2048);
                clsKeyPair = clsKeyPairGenerator.genKeyPair();

            }
            if (fact == null) {
                fact = KeyFactory.getInstance("RSA");
                clsPublicKey = clsKeyPair.getPublic();
                clsPrivateKey = clsKeyPair.getPrivate();
                clsPublicKeySpec = fact.getKeySpec(clsPublicKey, RSAPublicKeySpec.class);
                clsPrivateKeySpec = fact.getKeySpec(clsPrivateKey, RSAPrivateKeySpec.class);
            }
            // RSA 공개키/개인키를 생성한다.

            // 암호화 한다.

            Cipher clsCipher = Cipher.getInstance("RSA");
            clsCipher.init(Cipher.ENCRYPT_MODE, clsPublicKey);
            byte[] arrCipherData = clsCipher.doFinal(msg.getBytes());
            rsaMsg = new String(arrCipherData);


        } catch (Exception e) {

        }
        return rsaMsg;

    }

    public static String desRSA(String rsaMsg) {
        String result = "";

        try {
            if (clsKeyPairGenerator == null) {
                clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
                clsKeyPairGenerator.initialize(2048);

            }
            if (fact == null) {
                fact = KeyFactory.getInstance("RSA");
                clsPublicKey = clsKeyPair.getPublic();
                clsPrivateKey = clsKeyPair.getPrivate();
                clsPublicKeySpec = fact.getKeySpec(clsPublicKey, RSAPublicKeySpec.class);
                clsPrivateKeySpec = fact.getKeySpec(clsPrivateKey, RSAPrivateKeySpec.class);
            }
            byte[] arrCipherData = rsaMsg.getBytes();

            // 복호화 한다.
            Cipher clsCipher = Cipher.getInstance("RSA");

            clsCipher.init(Cipher.DECRYPT_MODE, clsPrivateKey);
            byte[] arrData = clsCipher.doFinal(arrCipherData);
            result = new String(arrData);
        } catch (Exception e) {

        }

        result = result.substring(0, result.lastIndexOf("SH"));
        return result;
    }

    //MD5
    public static String getMD5(String str) {

        String rtnMD5 = "";

        str += key;
        try {
            //MessageDigest 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("MD5");
            //해쉬값 업데이트
            md.update(str.getBytes());
            //해쉬값(다이제스트) 얻기
            byte byteData[] = md.digest();


            StringBuffer sb = new StringBuffer();

            //출력
            for (byte byteTmp : byteData) {
                sb.append(Integer.toString((byteTmp & 0xff) + 0x100, 16).substring(1));
                /*
                int tmp1 = (byteTmp & 0xff);
                int tmp2 = ((byteTmp&0xff) + 0x100);
                
                System.out.println(byteTmp +" : "+ tmp1 +" : "+ tmp2 
                        +" : "+Integer.toString((byteTmp&0xff)+0x100, 16)
                        +" : "+(Integer.toString((byteTmp&0xff) + 0x100, 16).substring(1)));
                */

                // byte 타입의 범위 : -128~127 ( -2^7 ~ 2^7-1 )
                //-129 + 256 = 127
                // 0x100 = 256
            }
            rtnMD5 = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            rtnMD5 = null;
        }
        return rtnMD5;
    }


    //SHA-256    
    public static String getSHA256(String str) {
        String rtnSHA = "";
        str += key;

        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            rtnSHA = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            rtnSHA = null;
        }
        return rtnSHA;
    }


    //Type2. BigInteger를 이용하여 구현.

    public static String getEncryptMD5(String a_origin) throws UnsupportedEncodingException {
        String encryptedMD5 = "";
        MessageDigest md = null;
        a_origin += key;
        try {
            md = MessageDigest.getInstance("MD5");
//            md.update(a_origin.getBytes());
            md.update(a_origin.getBytes(), 0, a_origin.getBytes().length);
            byte byteData[] = md.digest();
            encryptedMD5 = new BigInteger(1, byteData).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encryptedMD5;
    }

    public static String getEncryptSHA256(String a_origin) {
        String encryptedSHA256 = "";
        MessageDigest md = null;
        a_origin += key;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(a_origin.getBytes(), 0, a_origin.length());

            encryptedSHA256 = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        return encryptedSHA256;
    }

}//class

