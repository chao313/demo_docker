package demo.spring.boot.docker.enums;


public enum UseStatus {
    IN_USE("IN_USE", 0), NOT_USE("NOT_USE", 1);

    UseStatus(String content, int index) {
        this.content = content;
        this.index = index;
    }

    private String content;
    private int index;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static int getIndexByContent(String content) {
        for (UseStatus sex : UseStatus.values()) {
            if (sex.getContent().equals(content)) {
                return sex.getIndex();
            }
        }
        return 0;
    }

    public static String getContentByIndex(int index) {
        for (UseStatus sex : UseStatus.values()) {
            if (sex.getIndex() == index) {
                return sex.getContent();
            }
        }
        return null;
    }
}
