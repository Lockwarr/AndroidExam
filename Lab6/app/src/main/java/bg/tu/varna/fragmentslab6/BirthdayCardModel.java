package bg.tu.varna.fragmentslab6;

import android.os.Parcel;
import android.os.Parcelable;

public class BirthdayCardModel implements Parcelable {
    private String name;
    private String message;
    private int age;

    public BirthdayCardModel(String name, String message, int age) {
        this.name = name;
        this.message = message;
        this.age = age;
    }

    protected BirthdayCardModel(Parcel in) {
        name = in.readString();
        message = in.readString();
        age = in.readInt();
    }

    public static final Creator<BirthdayCardModel> CREATOR = new Creator<BirthdayCardModel>() {
        @Override
        public BirthdayCardModel createFromParcel(Parcel in) {
            return new BirthdayCardModel(in);
        }

        @Override
        public BirthdayCardModel[] newArray(int size) {
            return new BirthdayCardModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(message);
        dest.writeInt(age);
    }
}
