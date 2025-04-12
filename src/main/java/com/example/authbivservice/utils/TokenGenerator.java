package com.example.authbivservice.utils;

import liquibase.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    /**
     * Метод для генерации токена.
     * Условия генерации:
     * - Количество цифр: 6
     * - Отсутствуют лидирующие нули
     * - Отсутствует повторение соседних цифр
     *
     * @return String сгенерированный код
     */
    public String generate() {

        int[] code = new int[6];
        int index = 0;
        code[index++] = (int) ((Math.random() * (10 - 1)) + 1);

        while (index < code.length) {

            int i = (int) (Math.random() * 10);
            if (i == code[index - 1]) i = (int) (Math.random() * 10);
            code[index++] = i;
        }
        return StringUtil.join(code, "");
    }
}
