package ru.xpendence.mylovelytaxservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:03
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "inspections")
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE inspections SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
public class Inspection extends AbstractEntity {

    private String companyName;
    private String officialLocation;
    private Long ogrn;
    private Long inn;
    private String inspectionTarget;
    private String otherReasons;
    private LocalDate startDate;
    private Integer limitationDays;
    private Integer limitationHours;
    private String format;
    private String note;

    @Column(name = "company_name", length = 1000)
    public String getCompanyName() {
        return companyName;
    }

    @Column(name = "official_location", length = 1000)
    public String getOfficialLocation() {
        return officialLocation;
    }

    @Column(name = "ogrn")
    public Long getOgrn() {
        return ogrn;
    }

    @Column(name = "inn")
    public Long getInn() {
        return inn;
    }

    @Column(name = "inspection_target", length = 4000)
    public String getInspectionTarget() {
        return inspectionTarget;
    }

    @Column(name = "other_reasons", length = 1000)
    public String getOtherReasons() {
        return otherReasons;
    }

    @Column(name = "start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    @Column(name = "limitation_days")
    public Integer getLimitationDays() {
        return limitationDays;
    }

    @Column(name = "limitation_hours")
    public Integer getLimitationHours() {
        return limitationHours;
    }

    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    @Column(name = "note", length = 4000)
    public String getNote() {
        return note;
    }
}
