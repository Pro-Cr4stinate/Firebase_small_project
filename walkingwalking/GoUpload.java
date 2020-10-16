package com.example.walkingwalking;

public class GoUpload {
    private String Location;
    private String Date;
    private String ImageUrl;

    public GoUpload(){

    }

    public GoUpload(String location, String date, String imageUrl){
        if (location.trim().equals("")){
            location= "Location Unkown";
        }else if (date.trim().equals("")){
            date = "date not specified";
        }

        location = Location;
        imageUrl = ImageUrl;
        date = Date;

    }

    public String getLocation(){
        return Location;
    }
    public void setLoc(String loc){
        Location  = loc;
    }
    public String GetImageUrl(){
        return ImageUrl;
    }
    public void  setImageUrl(String Url){
        ImageUrl = Url;
    }
}
