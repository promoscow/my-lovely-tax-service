package ru.xpendence.mylovelytaxservice.exception;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 12:09
 * e-mail: v.chernyshov@pflb.ru
 */
public class DataBaseException extends RuntimeException {

    public DataBaseException(String s) {
        super(s);
    }
}
