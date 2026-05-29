package es.uca.saladillo.model;

public class PageMetrics {
    private  final String url, title;
    private final int linkCount;
    private final long executionTime;

    public PageMetrics(String url, String title, int linkCount, long executionTime) {
        this.url = url;
        this.title = title;
        this.linkCount = linkCount;
        this.executionTime = executionTime;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getLinkCount() {
        return linkCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
