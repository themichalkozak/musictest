package com.example.themichalkozak.music;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by themichalkozak on 10/04/2018.
 */


public class Album implements Parcelable{

    private String mArtistName;
    private String mAlbumName;
    private String drawable;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public Album(String mArtistName, String mAlbumName, String drawable, ArrayList<Track> tracks) {
        this.mArtistName = mArtistName;
        this.mAlbumName = mAlbumName;
        this.drawable = drawable;
        this.tracks = tracks;
    }




    public String getmArtistName() {
        return mArtistName;
    }

    public String getmAlbumName() {
        return mAlbumName;
    }

    public String getDrawable() {
        return drawable;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }



    private Track findTrack(String trackName){
        for(Track checkedTrack : this.tracks){
            if(checkedTrack.equals(trackName)){
                return checkedTrack;
            }
        }
        return null;
    }

    public boolean addTrack(String trackName){
        if(findTrack(trackName) == null) {
            this.tracks.add(new Track(trackName, mArtistName, drawable));
            return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(drawable);
        dest.writeString(mAlbumName);
        dest.writeString(mArtistName);
        dest.writeTypedList(this.tracks);



    }

    private Album(Parcel in){
        this.drawable = in.readString();
        this.mAlbumName = in.readString();
        this.mArtistName = in.readString();

        in.readTypedList(this.tracks,Track.CREATOR);
    }


    public static final Parcelable.Creator<Album> CREATOR =
            new Parcelable.Creator<Album>(){
                @Override
                public Album createFromParcel(Parcel source) {

                    return new Album(source);
                }

                @Override
                public Album[] newArray(int size) {
                    return new Album[size];
                }
            };
}
