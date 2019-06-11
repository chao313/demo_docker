package demo.spring.boot.docker.enums;


public enum OperatingSystem {
    CENTOS("centos", 1), UBUNTU("ubuntu", 2);

    OperatingSystem(String content, int index) {
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
        for (OperatingSystem sex : OperatingSystem.values()) {
            if (sex.getContent().equals(content)) {
                return sex.getIndex();
            }
        }
        return 0;
    }

    public static String getContentByIndex(int index) {
        for (OperatingSystem sex : OperatingSystem.values()) {
            if (sex.getIndex() == index) {
                return sex.getContent();
            }
        }
        return null;
    }
}
