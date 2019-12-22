package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_contragent", schema = "public", catalog = "farm")
public class RefContragentEntity extends SuperEntity{
    private String address;
    private String contact;
    private String phone;
    private String comments;
    private String okpo;
    private String inn;
    private Boolean nds;
    private RefTypeContragentEntity refTypeContragentByTypeContraId;
    private RefCityEntity refCityByCityId;
    private RefPriceEntity refPriceByPriceId;
    private RefMarketViewEntity refMarketViewByMarketViewId;
    private RefKindContragentEntity refKindContragentByKindContraId;

    @Basic
    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    @Basic
    @Column(name = "okpo", nullable = true, length = 50)
    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    @Basic
    @Column(name = "inn", nullable = true, length = 30)
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
    @Basic
    @Column(name = "nds")
    public Boolean getNds() {
        return nds;
    }

    public void setNds(Boolean nds) {
        this.nds = nds;
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

    @ManyToOne
    @JoinColumn(name = "type_contra_id", referencedColumnName = "id", nullable = false)
    public RefTypeContragentEntity getRefTypeContragentByTypeContraId() {
        return refTypeContragentByTypeContraId;
    }

    public void setRefTypeContragentByTypeContraId(RefTypeContragentEntity refTypeContragentByTypeContraId) {
        this.refTypeContragentByTypeContraId = refTypeContragentByTypeContraId;
    }

    @ManyToOne
    @JoinColumn(name = "kind_contra_id", referencedColumnName = "id", nullable = false)
    public RefKindContragentEntity getRefKindContragentByKindContraId() {
        return refKindContragentByKindContraId;
    }

    public void setRefKindContragentByKindContraId(RefKindContragentEntity refKindContragentByKindContraId) {
        this.refKindContragentByKindContraId = refKindContragentByKindContraId;
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
