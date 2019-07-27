package ru.xpendence.mylovelytaxservice.base;

import lombok.Getter;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 10:51
 * e-mail: v.chernyshov@pflb.ru
 */
@Getter
public enum TaxType {
    USN("Упрощённая система налогообложения"),
    ENVD("Единый налог на вменённый доход"),
    PSN("Патентная система налогообложения"),
    ESHN("Единый сельскохозяйственный налог");

    private String description;

    TaxType(String description) {
        this.description = description;
    }
}
