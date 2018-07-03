package com.lemon.entity;

import javax.persistence.*;

/**
 * 房源图片
 * Created by kane on 18-6-29
 */
@Entity
@Table(name = "t_house_img")
public class HouseImg {

    private Integer id;
    private Integer houseId; //房源id
    private String address; //图片地址

    public HouseImg() {
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    public Integer getId() {
        return id;
    }
    @Column(name = "house_id", length = 11)
    public Integer getHouseId() {
        return houseId;
    }
    @Column(name = "address", length = 128)
    public String getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "HouseImg{" +
                "id='" + id + '\'' +
                ", houseId='" + houseId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
