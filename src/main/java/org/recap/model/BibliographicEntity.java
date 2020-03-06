package org.recap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.recap.model.jpa.BibliographicAbstractEntity;
import org.recap.model.jpa.BibliographicPK;
import org.recap.model.jpa.HoldingsPK;
import org.recap.model.jpa.InstitutionEntity;

/**
 * Created by pvsubrah on 6/10/16.
 */
@Entity
@Table(name = "bibliographic_t", schema = "recap", catalog = "")
@IdClass(BibliographicPK.class)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "BibliographicEntity.getNonDeletedHoldingsEntities",
                query = "SELECT HOLDINGS_T.* FROM HOLDINGS_T, BIBLIOGRAPHIC_HOLDINGS_T WHERE BIBLIOGRAPHIC_HOLDINGS_T.HOLDINGS_INST_ID = HOLDINGS_T.OWNING_INST_ID " +
                        "AND BIBLIOGRAPHIC_HOLDINGS_T.OWNING_INST_HOLDINGS_ID = HOLDINGS_T.OWNING_INST_HOLDINGS_ID AND HOLDINGS_T.IS_DELETED = 0 AND " +
                        "BIBLIOGRAPHIC_HOLDINGS_T.OWNING_INST_BIB_ID = :owningInstitutionBibId AND BIBLIOGRAPHIC_HOLDINGS_T.BIB_INST_ID = :owningInstitutionId",
                resultClass = HoldingsEntity.class),
        @NamedNativeQuery(
                name = "BibliographicEntity.getNonDeletedItemEntities",
                query = "SELECT ITEM_T.* FROM ITEM_T, BIBLIOGRAPHIC_ITEM_T WHERE BIBLIOGRAPHIC_ITEM_T.ITEM_INST_ID = ITEM_T.OWNING_INST_ID " +
                        "AND BIBLIOGRAPHIC_ITEM_T.OWNING_INST_ITEM_ID = ITEM_T.OWNING_INST_ITEM_ID AND ITEM_T.IS_DELETED = 0 AND " +
                        "BIBLIOGRAPHIC_ITEM_T.OWNING_INST_BIB_ID = :owningInstitutionBibId AND BIBLIOGRAPHIC_ITEM_T.BIB_INST_ID = :owningInstitutionId",
                resultClass = ItemEntity.class),
})
public class BibliographicEntity extends BibliographicAbstractEntity implements Serializable {

    @Id
    @Column(name = "OWNING_INST_ID")
    private Integer owningInstitutionId;

    @Id
    @Column(name = "OWNING_INST_BIB_ID")
    private String owningInstitutionBibId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OWNING_INST_ID", insertable = false, updatable = false)
    private InstitutionEntity institutionEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bibliographic_holdings_t", joinColumns = {
            @JoinColumn(name = "OWNING_INST_BIB_ID", referencedColumnName = "OWNING_INST_BIB_ID"),
            @JoinColumn(name = "BIB_INST_ID", referencedColumnName = "OWNING_INST_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "OWNING_INST_HOLDINGS_ID", referencedColumnName = "OWNING_INST_HOLDINGS_ID"),
                    @JoinColumn(name = "HOLDINGS_INST_ID", referencedColumnName = "OWNING_INST_ID")})
    private List<HoldingsEntity> holdingsEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bibliographic_item_t", joinColumns = {
            @JoinColumn(name = "OWNING_INST_BIB_ID", referencedColumnName = "OWNING_INST_BIB_ID"),
            @JoinColumn(name = "BIB_INST_ID", referencedColumnName = "OWNING_INST_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "OWNING_INST_ITEM_ID", referencedColumnName = "OWNING_INST_ITEM_ID"),
                    @JoinColumn(name = "ITEM_INST_ID", referencedColumnName = "OWNING_INST_ID")})
    private List<ItemEntity> itemEntities;

    /**
     * Instantiates a new Bibliographic entity object.
     */
    public BibliographicEntity() {
        //Do Nothing
    }

    /**
     * Gets holdings entities.
     *
     * @return the holdings entities
     */
    public List<HoldingsEntity> getHoldingsEntities() {
        return holdingsEntities;
    }

    /**
     * Sets holdings entities.
     *
     * @param holdingsEntities the holdings entities
     */
    public void setHoldingsEntities(List<HoldingsEntity> holdingsEntities) {
        this.holdingsEntities = holdingsEntities;
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
     * Gets owning institution bib id.
     *
     * @return the owning institution bib id
     */
    public String getOwningInstitutionBibId() {
        return owningInstitutionBibId;
    }

    /**
     * Sets owning institution bib id.
     *
     * @param owningInstitutionBibId the owning institution bib id
     */
    public void setOwningInstitutionBibId(String owningInstitutionBibId) {
        this.owningInstitutionBibId = owningInstitutionBibId;
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
}

