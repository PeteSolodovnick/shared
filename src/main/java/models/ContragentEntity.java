package models;

import address.documents.invoices.DocInvoiceHeadDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_contragent", schema = "public", catalog = "farm")
public class ContragentEntity extends SuperEntity {
    private String address;
    private String contact;
    private String phone;
    private String comments;
    private String okpo;
    private String inn;
    private Boolean nds;
    private TypeContragentEntity refTypeContragentByTypeContraId;
    private CityEntity refCityByCityId;
    private PriceEntity refPriceByPriceId;
    private MarketViewEntity refMarketViewByMarketViewId;
    private KindContragentEntity refKindContragentByKindContraId;
    private Collection<DocInvoiceHeadDocEntity> docInvoiceHeadById;

    @OneToMany(mappedBy = "refContragentEntityByContragentId")
    public Collection<DocInvoiceHeadDocEntity> getDocInvoiceHeadById() {
        return docInvoiceHeadById;
    }

    public void setDocInvoiceHeadById(Collection<DocInvoiceHeadDocEntity> docInvoiceHeadById) {
        this.docInvoiceHeadById = docInvoiceHeadById;
    }

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
    public TypeContragentEntity getRefTypeContragentByTypeContraId() {
        return refTypeContragentByTypeContraId;
    }

    public void setRefTypeContragentByTypeContraId(TypeContragentEntity refTypeContragentByTypeContraId) {
        this.refTypeContragentByTypeContraId = refTypeContragentByTypeContraId;
    }

    @ManyToOne
    @JoinColumn(name = "kind_contra_id", referencedColumnName = "id", nullable = false)
    public KindContragentEntity getRefKindContragentByKindContraId() {
        return refKindContragentByKindContraId;
    }

    public void setRefKindContragentByKindContraId(KindContragentEntity refKindContragentByKindContraId) {
        this.refKindContragentByKindContraId = refKindContragentByKindContraId;
    }

    @ManyToOne
    @JoinColumn(name = "market_view_id", referencedColumnName = "id", nullable = false)
    public MarketViewEntity getRefMarketViewByMarketViewId() {
        return refMarketViewByMarketViewId;
    }

    public void setRefMarketViewByMarketViewId(MarketViewEntity refMarketViewByMarketViewId) {
        this.refMarketViewByMarketViewId = refMarketViewByMarketViewId;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public CityEntity getRefCityByCityId() {
        return refCityByCityId;
    }

    public void setRefCityByCityId(CityEntity refCityByCityId) {
        this.refCityByCityId = refCityByCityId;
    }

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    public PriceEntity getRefPriceByPriceId() {
        return refPriceByPriceId;
    }

    public void setRefPriceByPriceId(PriceEntity refPriceByPriceId) {
        this.refPriceByPriceId = refPriceByPriceId;
    }
}
