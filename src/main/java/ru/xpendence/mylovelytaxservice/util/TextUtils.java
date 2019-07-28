package ru.xpendence.mylovelytaxservice.util;

import ru.xpendence.mylovelytaxservice.exception.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 22:44
 * e-mail: v.chernyshov@pflb.ru
 */
public class TextUtils {

    public static List<String> getText(String file) {
        List<String> result = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(file)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();

            while (Objects.nonNull(line)) {
                result.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new ParseException("Не удалось получить текст для файла " + file);
        }
        return result;
    }
}
