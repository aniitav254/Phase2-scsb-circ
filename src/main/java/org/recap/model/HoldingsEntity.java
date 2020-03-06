package org.recap.model;

import org.recap.model.jpa.HoldingsAbstractEntity;
import org.recap.model.jpa.HoldingsPK;
import org.recap.model.jpa.InstitutionEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by pvsubrah on 6/11/16.
 */
@Entity
@Table(name = "holdings_t", schema = "recap", catalog = "")
@IdClass(HoldingsPK.class)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "HoldingsEntity.getNonDeletedItemEntities",
                query = "SELECT ITEM_T.* FROM ITEM_T, ITEM_HOLDINGS_T WHERE ITEM_HOLDINGS_T.ITEM_INST_ID = ITEM_T.OWNING_INST_ID AND " +
                        "ITEM_HOLDINGS_T.OWNING_INST_ITEM_ID = ITEM_T.OWNING_INST_ITEM_ID AND ITEM_T.IS_DELETED = 0 AND " +
                        " ITEM_HOLDINGS_T.OWNING_INST_HOLDINGS_ID = :owningInstitutionHoldingsId AND ITEM_HOLDINGS_T.HOLDINGS_INST_ID = :owningInstitutionId",
                resultClass = ItemEntity.class)
})
public class HoldingsEntity extends HoldingsAbstractEntity implements Serializable {

    @Id
    @Column(name = "OWNING_INST_ID")
    private Integer owningInstitutionId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OWNING_INST_ID", insertable = false, updatable = false)
    private InstitutionEntity institutionEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_holdings_t", joinColumns = {
            @JoinColumn(name="OWNING_INST_HOLDINGS_ID", referencedColumnName = "OWNING_INST_HOLDINGS_ID"),
            @JoinColumn(name="HOLDINGS_INST_ID", referencedColumnName = "OWNING_INST_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name="OWNING_INST_ITEM_ID", referencedColumnName = "OWNING_INST_ITEM_ID"),
                    @JoinColumn(name="ITEM_INST_ID", referencedColumnName = "OWNING_INST_ID") })
    private List<ItemEntity> itemEntities;

    /**
     * Instantiates a new Holdings entity object.
     */
    public HoldingsEntity() {
        //Do nothing
    }

    /**
     * Gets owning institution id.
     *
     * @return the owning institution id
     */
    public Integer getOwningInstitutionId() {
        return owningInstitutionId;
    }

    /**
     * Sets owning institution id.
     *
     * @param owningInstitutionId the owning institution id
     */
    public void setOwningInstitutionId(Integer owningInstitutionId) {
        this.owningInstitutionId = owningInstitutionId;
    }

    /**
     * Gets institution entity.
     *
     * @return the institution entity
     */
    public InstitutionEntity getInstitutionEntity() {
        return institutionEntity;
    }

    /**
     * Sets institution entity.
     *
     * @param institutionEntity the institution entity
     */
    public void setInstitutionEntity(InstitutionEntity institutionEntity) {
        this.institutionEntity = institutionEntity;
    }


    @ManyToMany(mappedBy = "holdingsEntities")
    private List<BibliographicEntity> bibliographicEntities;

    /**
     * Gets bibliographic entities.
     *
     * @return the bibliographic entities
     */
    public List<BibliographicEntity> getBibliographicEntities() {
        return bibliographicEntities;
    }

    /**
     * Sets bibliographic entities.
     *
     * @param bibliographicEntities the bibliographic entities
     */
    public void setBibliographicEntities(List<BibliographicEntity> bibliographicEntities) {
        this.bibliographicEntities = bibliographicEntities;
    }

    /**
     * Gets item entities.
     *
     * @return the item entities
     */
    public List<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    /**
     * Sets item entities.
     *
     * @param itemEntities the item entities
     */
    public void setItemEntities(List<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        HoldingsEntity holdingsEntity = (HoldingsEntity) o;

        return owningInstitutionHoldingsId.equals(holdingsEntity.owningInstitutionHoldingsId);

    }

    @Override
    public int hashCode() {
        return owningInstitutionHoldingsId.hashCode();
    }
}
