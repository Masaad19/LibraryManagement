package library.service;

import libraryy.MediaItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OverdueReportService {

    public OverdueReport generate(List<MediaItem> items, LocalDate today) {

        List<MediaItem> overdueList = new ArrayList<>();
        int total = 0;

        for (MediaItem item : items) {
            if (item.isOverdue(today)) {
                overdueList.add(item);
                total += item.calculateFine(item.overdueDays(today));
            }
        }
        return new OverdueReport(overdueList, total);
    }
}
