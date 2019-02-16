package et.ts.bean;


public class Music {


    public int type;

    public enum Type{
        LOCAL,
        ONLINE
    }

    // 歌曲类型:本地/网络
    private Type typ;
    // [本地歌曲]歌曲id
    private long id;
    // 艺术家
    private String artist;
    // 专辑
    private String album;
    // 持续时间
    private long duration;
    // 音乐路径
    private String path;
    // 专辑封面路径
    private String coverPath;
    // 文件名
    private String fileName;
    // 文件大小
    private long fileSize;

    public String title;
    // 后期可加入Glide加载网络图片Url
    public int imageId;

    public interface TYPE {
        int TYPE_GRID_THREE = 0x01;
        int TYPE_GRID_TWO = 0x02;
        int TYPE_LIST = 0x03;
        int TYPE_TITLE = 0x04;
    }

    public Type getTyp() {
        return typ;
    }

    public void setTyp(Type typ) {
        this.typ = typ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * 对比本地歌曲是否相同
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Music)) {
            return false;
        }
        return this.getId() == ((Music) o).getId();
    }
}
