package com.lemon.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 房源
 * Created by kane on 18-6-29
 */
@Entity
@Table(name = "t_house")
public class House {

    private Integer id;
    private String name;        //房源名字
    private String price;       //价格
    private String peopleNum;  //居住人数
    private String acreage;    //面积
    private String houseType;  //户型
    private String roomNum;    //房间数量
    private String bedNum;     //床位数量
    private String toilet;      //卫生间数量
    private String introduce;   //介绍
    private String address;     //详细地址
    private String facilities;  //配套设施
    private String img;         //默认图片

    private List<HouseImg> imgs;//房源图片
    private List<Order> orders; //房源订单

    @Transient
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Transient
    public List<HouseImg> getImgs() {
        return imgs;
    }
    public void setImgs(List<HouseImg> imgs) {
        this.imgs = imgs;
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    public Integer getId() {
        return id;
    }
    @Column(name = "name", length = 32)
    public String getName() {
        return name;
    }
    @Column(name = "price", length = 6)
    public String getPrice() {
        return price;
    }
    @Column(name = "people_num", length = 2)
    public String getPeopleNum() {
        return peopleNum;
    }
    @Column(name = "acreage", length = 4)
    public String getAcreage() {
        return acreage;
    }
    @Column(name = "house_type", length = 12)
    public String getHouseType() {
        return houseType;
    }
    @Column(name = "room_num", length = 2)
    public String getRoomNum() {
        return roomNum;
    }
    @Column(name = "bed_num", length = 2)
    public String getBedNum() {
        return bedNum;
    }
    @Column(name = "toilet", length = 2)
    public String getToilet() {
        return toilet;
    }
    @Column(name = "introduce", length = 256)
    public String getIntroduce() {
        return introduce;
    }
    @Column(name = "address", length = 128)
    public String getAddress() {
        return address;
    }
    @Column(name = "facilities", length = 256)
    public String getFacilities() {
        return facilities;
    }
    @Column(name = "img", length = 256)
    public String getImg() {
        return img;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", peopleNum='" + peopleNum + '\'' +
                ", acreage='" + acreage + '\'' +
                ", houseType='" + houseType + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", bedNum='" + bedNum + '\'' +
                ", toilet='" + toilet + '\'' +
                ", introduce='" + introduce + '\'' +
                ", address='" + address + '\'' +
                ", facilities='" + facilities + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
