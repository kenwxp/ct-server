package com.cloudtimes.common.utils;

import com.cloudtimes.common.utils.sign.Base64;
import org.apache.xmlbeans.impl.util.HexBin;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SHA1withRSAUtil {
    public enum PriKeyType {
        //定义一个枚举类型
        PKCS1, PKCS8
    }

    /**
     * 解析PKCS8格式私钥
     *
     * @param privateKeyStr
     * @return
     */
    private static PrivateKey parsePKCS8KeyStr(String privateKeyStr) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析PKCS1格式私钥
     *
     * @param privateKeyStr
     * @return
     */
    private static PrivateKey parsePKCS1KeyStr(String privateKeyStr) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            RSAPrivateKey rsaPrivateKeyStructure = RSAPrivateKey.getInstance(ASN1Sequence.fromByteArray(Base64.decode(privateKeyStr)));
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(rsaPrivateKeyStructure.getModulus(), rsaPrivateKeyStructure.getPrivateExponent());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
            return keyFactory.generatePrivate(rsaPrivateKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥签名
     *
     * @param privateKeyStr 私钥字符串
     * @param data          需签名的内容
     * @return
     */
    public static byte[] sign(String privateKeyStr, PriKeyType type, String data) {
        byte[] arr = new byte[0];
        try {
            PrivateKey privateKey = null;
            switch (type) {
                case PKCS1:
                    privateKey = parsePKCS1KeyStr(privateKeyStr);
                    break;
                case PKCS8:
                    privateKey = parsePKCS8KeyStr(privateKeyStr);
                    break;
            }
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            arr = signature.sign();
            System.out.println("jdk rsa sign:" + HexBin.encode(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 公钥验签
     *
     * @param publicKeyStr 公钥
     * @param srcStr       原内容
     * @param sign         签名
     * @return
     */
    public static boolean verify(String publicKeyStr, String srcStr, String sign) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(srcStr.getBytes());
            byte[] bytesSign = Base64.decode(sign);
            return signature.verify(bytesSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String data = "你好";
        String rawPrivKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRh7G9TbUTuW0O3hhjp3dGjNHFX1KFBaEMVxFt2LWenCL82V/G0NuAmfSjl99liaJUqX6sfNUsDjoIeUFNCQGs8GisgBWYIFeq3zTfc11k3qpNnD0WxGwKwv5dzYCT4+yw5THW5sRgnvMrW/ckd88mHgAAevPDFegMyzS/iSZnUuK8zxnXY45ukdCA1C6spCgM5U+1iXbDgdmqdnWe+QO10sAF28tg56VO0/OWpNVblSxAehyafUu5bUkqrlBLsyssWeme+mmZ0Xfy7Iu7KwudSC1th7ONHkumTKkL/NMYcz1Vfxjt1gWAN+sbvMvj8kuu1Q5NEjAmmWMG2d2Te0OFAgMBAAECggEAEz7pv4o2Rzx/crbmbM9p07etyjdX92WOLc6Z7Hf7VNV7PwsUQnu+e5xgoP3lukzgXefoa7E6k8/x1ssKWsC1KwTEIwPxVyP31A4etOpR0JQt24OtBITHcQjmkMsrlONQmvhEhhIQqGJ88CACaB08FONkxqt77zHHdWGuQSJHJOS5h9mpvg/Pu03w/lyaITfnG7W8w9M4di9reFJN75TjobVty/zWZUr3TKh2bPZ9MhVJyiF12odroTkyoYNr0aYYezTlnO2nHlriysi9zfgT2qUvYAJ4MYI84TT7PAHrTcjWSf7p7GP82d8litlFLW5N45IWB/nnB0baOINU2XC5pQKBgQD43kQLT6VFkxxm0t7HUsHz1Utx4A1bDjGK33Zel6Aen8O2CATgz1l6Ms0eBoQgdX6xlZDyvWyykw/o7DT+L8VRtgcSVRHaJ10SujJPlvUPiYkBXX/5mOfrqXTR10xImAD4YSkTihKCrTWShL+409tTGYUn+2Y4LbVXXVx4/k/3dwKBgQCVs1MJVv2HtZJo34Ewed8fWIKbTJv4dU9QTpiGRQa+ycMVaQMBEnmfWZbqMnAZKT3MD2OvUECXegyQ7hnvVY8XAlHA4AJf5EqYghUamHkoZCz9xZnIT0rzdFNh2rbh4cSHVGo6Gta0Ag/rxkD64W+h9DddrXI5GMPdXcTzfbMT4wKBgQDfKfC6c+EwUorVaaJ7+qoRjOLqao+PZ1rlvRzYFf9vdrR5NxeowEbsXUGx+wXgVXwWopgoE9S1DUrnA2lBxnSaoTAWMFKh3UqI0I7s7+Ew3ZQNtVpc59CQcpRe10GAl03HbNj4vId8xvWTPZbulovGKMxO3hD7r4E9pmqV1PbAYQKBgAdSvG8qiECJS15CAMnkTFv2Mn1yY/blljmxfpii68+NgzOa7YCx7K007sMKMJ2qfNFst02uOVqvp8m3PCGPJ+7SODmdO8CBJH0NcAbbCqJFNEY90mYQKvR57lJNJsEqEDkmkgPBqFsp9qlsC2W6fYGItuSoRXwxj/NWUPowXgBNAoGAXOaz+qLVaFLGFt+MiLf3yWSBAaKuHIiMcHSUB7nhGFKx4wyyOqrp8nVBwIkzj+VPOvXaTZ3pIcLUZkyaqQHAThhEFKMorwDL0tHS+VPEBhdKFXGSiWUBecHp2g5B41kMz/239zFbzZTahIzlwcl1b4HC8qgqs4J8Q+taKcwdMlU=";
        String rawPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkYexvU21E7ltDt4YY6d3RozRxV9ShQWhDFcRbdi1npwi/NlfxtDbgJn0o5ffZYmiVKl+rHzVLA46CHlBTQkBrPBorIAVmCBXqt8033NdZN6qTZw9FsRsCsL+Xc2Ak+PssOUx1ubEYJ7zK1v3JHfPJh4AAHrzwxXoDMs0v4kmZ1LivM8Z12OObpHQgNQurKQoDOVPtYl2w4HZqnZ1nvkDtdLABdvLYOelTtPzlqTVW5UsQHocmn1LuW1JKq5QS7MrLFnpnvppmdF38uyLuysLnUgtbYezjR5LpkypC/zTGHM9VX8Y7dYFgDfrG7zL4/JLrtUOTRIwJpljBtndk3tDhQIDAQAB";
        byte[] sign = SHA1withRSAUtil.sign(rawPrivKey,PriKeyType.PKCS8, data);
        boolean ok = SHA1withRSAUtil.verify(rawPubKey, data, Base64.encode(sign));
        System.out.println(ok);
    }
}
