package br.com.senaigo.mobile.northwindtraders.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bruno on 03/04/16.
 */
public class Shipper implements Parcelable{

    protected Integer shipperId;
    protected String companyname;
    protected String phone;

    public Shipper(){

    }

    public Shipper(Integer shipperId, String companyname, String phone) {
        this.shipperId = shipperId;
        this.companyname = companyname;
        this.phone = phone;
    }

    protected Shipper(Parcel in) {
        companyname = in.readString();
        phone = in.readString();
    }

    public static final Creator<Shipper> CREATOR = new Creator<Shipper>() {
        @Override
        public Shipper createFromParcel(Parcel in) {
            return new Shipper(in);
        }

        @Override
        public Shipper[] newArray(int size) {
            return new Shipper[size];
        }
    };

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "shipperId=" + shipperId +
                ", companyname='" + companyname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyname);
        dest.writeString(phone);
    }
}
