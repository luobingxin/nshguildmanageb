package xyz.bfdwdd.nshguildmanageb.base.invitationcode.config;

import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;
import java.util.Base64;

@Configuration
public class CodeGeneratorConfig {
    private final SecureRandom random = new SecureRandom();

    public String generateBaseCode() {
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(nonce);
    }

    public String generateQueryCode() {
        return String.format("%d-%d",
                System.currentTimeMillis() / 1000,
                random.nextInt(999999));
    }
}