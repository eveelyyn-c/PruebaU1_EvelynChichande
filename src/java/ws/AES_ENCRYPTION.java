/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 *
 * @author EVELYN
 */
public class AES_ENCRYPTION {

    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    public AES_ENCRYPTION() {
        init(); // Llama a init() en el constructor para generar la llave una vez al crear la instancia
    }

    public AES_ENCRYPTION(SecretKey key, Cipher encryptionCipher) {
        this.key = key;
        this.encryptionCipher = encryptionCipher;
    }

    public SecretKey getKey() {
        return key;
    }

    public Cipher getEncryptionCipher() {
        return encryptionCipher;
    }

    public void setEncryptionCipher(Cipher encryptionCipher) {
        this.encryptionCipher = encryptionCipher;
    }

    public void init() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_SIZE);
            key = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace(); // Maneja la excepción adecuadamente en tu aplicación
        }
    }

    public String encrypt(String DataToEncrypt) throws Exception {
        byte[] dataInBytes = DataToEncrypt.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] dataInBytes = decode(encryptedData);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
