package com.common.password;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.security.keystore.KeyProtection;

import androidx.annotation.RequiresApi;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class PasswordManager {

    private static final String KEY_ALIAS = "my_password_key";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void savePassword(Context context, String password) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            if (!keyStore.containsAlias(KEY_ALIAS)) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
                keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .setUserAuthenticationRequired(false) // 设置为 true 则需要用户验证才能使用秘钥
                        .build());
                keyGenerator.generateKey();
            }

            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());

            // 将加密后的密码保存到 SharedPreferences 或其他安全存储方式
            // ...

        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
        }
    }
}
