package ru.xpendence.mylovelytaxservice.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.xpendence.mylovelytaxservice.entity.Inspection;
import ru.xpendence.mylovelytaxservice.exception.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 15:51
 * e-mail: v.chernyshov@pflb.ru
 */
public class InspectionsParser {

    private final static Map<String, LocalDate> dates;

    private final static String FILE_URL
            = "https://www.nalog.ru/html/sites/www.new.nalog.ru/docs/kont/svodplanpr19_.xlsx";
    private final static int CONNECTION_TIMEOUT = 10000;
    private final static int READ_TIMEOUT = 20000;
    private final static String PATH_NAME = "svodplanpr19_.xlsx";

    static {
        dates = new HashMap<>();
        dates.put("Январь", LocalDate.of(2019, 1, 1));
        dates.put("Февраль", LocalDate.of(2019, 2, 1));
        dates.put("Март", LocalDate.of(2019, 3, 1));
        dates.put("Апрель", LocalDate.of(2019, 4, 1));
        dates.put("Май", LocalDate.of(2019, 5, 1));
        dates.put("Июнь", LocalDate.of(2019, 6, 1));
        dates.put("Июль", LocalDate.of(2019, 7, 1));
        dates.put("Август", LocalDate.of(2019, 8, 1));
        dates.put("Сентябрь", LocalDate.of(2019, 9, 1));
        dates.put("Октябрь", LocalDate.of(2019, 10, 1));
        dates.put("Ноябрь", LocalDate.of(2019, 11, 1));
        dates.put("Декабрь", LocalDate.of(2019, 12, 1));
    }

    public static List<Inspection> parse() {
        try {
            FileUtils.copyURLToFile(new URL(FILE_URL), new File(PATH_NAME), CONNECTION_TIMEOUT, READ_TIMEOUT);
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(PATH_NAME));
            XSSFSheet myExcelSheet = myExcelBook.getSheet("План проверок");

            List<Inspection> inspections = new ArrayList<>();
            for (Row row : myExcelSheet) {
                if (row.getRowNum() > 8) inspections.add(createInspectionFromRow(row));
            }
            return inspections;
        } catch (Exception e) {
            throw new ParseException("Не удалось обновить справочник проверок.");
        }
    }

    private static Inspection createInspectionFromRow(Row row) {
        Inspection inspection = new Inspection();
        inspection.setCompanyName(row.getCell(0).getStringCellValue());
        inspection.setOfficialLocation(row.getCell(1).getStringCellValue());
        inspection.setOgrn(Long.parseLong(row.getCell(4).getStringCellValue()));
        inspection.setInn(Long.parseLong(row.getCell(5).getStringCellValue()));
        inspection.setInspectionTarget(row.getCell(6).getStringCellValue());
        inspection.setOtherReasons(row.getCell(10).getStringCellValue());
        inspection.setStartDate(dates.get(row.getCell(11).getStringCellValue()));

        String limitationInDays = row.getCell(12).getStringCellValue();
        if (Objects.nonNull(limitationInDays) && !limitationInDays.isEmpty()) {
            inspection.setLimitationDays(Integer.parseInt(limitationInDays));
        }
        String limitationInHours = row.getCell(13).getStringCellValue();
        if (Objects.nonNull(limitationInHours) && !limitationInHours.isEmpty()) {
            inspection.setLimitationHours(Integer.parseInt(limitationInHours));
        }
        inspection.setFormat(row.getCell(14).getStringCellValue());
        inspection.setNote(row.getCell(18).getStringCellValue());
        return inspection;
    }
}
