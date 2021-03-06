package cc.haoduoyu.umaru.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XP on 2016/1/23.
 */
public class Song implements Parcelable {

    private int songId;//音乐ID
    private String songTitle;//音乐标题
    private String artistName;//艺术家
    private int artistId;//艺术家ID
    private String albumName;//专辑
    private int albumId;//专辑ID
    private long duration;//时长
    private long size;//大小
    private String songData;//路径
    private int isMusic;
    private String displayName;
    private String mimeType;

    public Song() {
    }

    public Song(String songTitle) {
        this.songTitle = songTitle;
    }

    public Song(int songId, String songTitle, String artistName, String albumName,
                int albumId, long duration, long size, String songData, String displayName, String mimeType) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.albumName = albumName;
        this.albumId = albumId;
        this.duration = duration;
        this.size = size;
        this.songData = songData;
        this.displayName = displayName;
        this.mimeType = mimeType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSongData() {
        return songData;
    }

    public void setSongData(String songData) {
        this.songData = songData;
    }

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.songId);
        dest.writeString(this.songTitle);
        dest.writeString(this.artistName);
        dest.writeInt(this.artistId);
        dest.writeString(this.albumName);
        dest.writeInt(this.albumId);
        dest.writeLong(this.duration);
        dest.writeLong(this.size);
        dest.writeString(this.songData);
        dest.writeInt(this.isMusic);
        dest.writeString(this.displayName);
        dest.writeString(this.mimeType);
    }

    protected Song(Parcel in) {
        this.songId = in.readInt();
        this.songTitle = in.readString();
        this.artistName = in.readString();
        this.artistId = in.readInt();
        this.albumName = in.readString();
        this.albumId = in.readInt();
        this.duration = in.readLong();
        this.size = in.readLong();
        this.songData = in.readString();
        this.isMusic = in.readInt();
        this.displayName = in.readString();
        this.mimeType = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}


