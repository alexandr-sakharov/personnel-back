package ru.personnel.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PasswordUtils {
    public static String generatePassword() throws Exception {
        String upperCaseLetters = RandomStringUtils.random(4, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(4, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(4);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        if(Pattern.matches("^$|(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])^[\\w~'`!@#№?$%^&*()=+<>|\\\\.,:;\\[\\]{} \\x22-]{8,}$", password)){
            return password;
        } else {
            throw new Exception("Ошибка генерации пароля (попробуйте еще раз)");
        }
    }
}
