package org.java.mikhail.tasks;

public class Task3 {

    String s = """
            SELECT t1.*
            FROM Table1 t1
            LEFT JOIN Table2 t2
              ON t1.ID = t2.ID AND t1.NAME = t2.NAME
            WHERE t2.ID IS NULL;
            """;

    /* LEFT JOIN ищет совпадения по ID и NAME
    * WHERE t2.ID IS NULL отсекает те строки, которые нашлись (оставляя только отсутствующие)
    *  Повторы сохраняются, потому что мы не используем DISTINCT.
    * */

    /**
     * Потому что LEFT JOIN ... WHERE IS NULL теряет повторы, если в Table2 есть меньше копий, чем в Table1.
     * Но, этот подход будет неправильным, если в Table2 меньше копий, чем в Table1.
     */


    /**
     * Вернет строки из Table1, для которых НЕТ соответствия в Table2 по ID и NAME.
     * Ответ для первой задачи:
     * 1 | Name1
     * 1 | Name1
     * 4 | Name3
     */

    /**
     * JOIN — найдёт все совпадающие строки между Table1 и Table2 по ID и NAME
     * DISTINCT — исключит дубликаты из результата (оставит только уникальные строки)
     * Результат:
     * 2 | Name2
     */
}
