package com.ppjun.greendao.shownote;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Package :com.ppjun.greendao.shownote
 * Description :
 * Author :Rc3
 * Created at :2016/11/23 12:26.
 */

@Entity
public class Note  extends  Msg implements Parcelable {
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "content")
    private String content;
    @Property(nameInDb = "date")
    private String date;


    @Generated(hash = 1881903072)
    public Note(Long id, String content, String date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    protected Note(Parcel in) {
        id=in.readLong();
        content = in.readString();
        date = in.readString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(content);
        parcel.writeString(date);

    }
}
