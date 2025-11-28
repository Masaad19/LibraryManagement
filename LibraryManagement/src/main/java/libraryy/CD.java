package libraryy;

import library.service.CDFine;

public class CD extends MediaItem {

    private String id;

    public CD(String title, String id) {
        this.title = title;
        this.setId(id);
    }

    @Override
    public int getBorrowPeriod() {
        return 7;
    }

    @Override
    public int calculateFine(int overdueDays) {
        return new CDFine().calculateFine(overdueDays);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}