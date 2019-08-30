package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_contragent", schema = "public", catalog = "farm")
public class RefContragentEntity extends SuperEntity{
    private String name;
    private String address;
    private String contact;
    private String phone;
    private String comments;
    private RefTypeContragentEntity refTypeContragentByTypeContraId;
    private RefCityEntity refCityByCityId;
    private RefPriceEntity refPriceByPriceId;
    private RefMarketViewEntity refMarketViewByMarketViewId;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "contact", nullable = true, length = 100)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = 255)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefContragentEntity that = (RefContragentEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, contact, phone, comments, getId());
    }

    @ManyToOne
    @JoinColumn(name = "type_contra_id", referencedColumnName = "id", nullable = false)
    public RefTypeContragentEntity getRefTypeContragentByTypeContraId() {
        return refTypeContragentByTypeContraId;
    }

    public void setRefTypeContragentByTypeContraId(RefTypeContragentEntity refTypeContragentByTypeContraId) {
        this.refTypeContragentByTypeContraId = refTypeContragentByTypeContraId;
    }

    @ManyToOne
    @JoinColumn(name = "market_view_id", referencedColumnName = "id", nullable = false)
    public RefMarketViewEntity getRefMarketViewByMarketViewId() {
        return refMarketViewByMarketViewId;
    }

    public void setRefMarketViewByMarketViewId(RefMarketViewEntity refMarketViewByMarketViewId) {
        this.refMarketViewByMarketViewId = refMarketViewByMarketViewId;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public RefCityEntity getRefCityByCityId() {
        return refCityByCityId;
    }

    public void setRefCityByCityId(RefCityEntity refCityByCityId) {
        this.refCityByCityId = refCityByCityId;
    }

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    public RefPriceEntity getRefPriceByPriceId() {
        return refPriceByPriceId;
    }

    public void setRefPriceByPriceId(RefPriceEntity refPriceByPriceId) {
        this.refPriceByPriceId = refPriceByPriceId;
    }
}
