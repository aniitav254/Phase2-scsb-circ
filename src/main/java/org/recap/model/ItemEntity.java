package org.recap.model;

import org.apache.commons.lang3.StringUtils;
import org.recap.ReCAPConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.recap.RecapCommonConstants;
import org.recap.model.jpa.*;

/**
 * Created by pvsubrah on 6/11/16.
 */
@Entity
@Table(name = "item_t", schema = "recap", catalog = "")
@IdClass(ItemPK.class)
public class ItemEntity extends ItemAbstractEntity implements Serializable  {

    @Column(name = "IS_CGD_PROTECTION")
    private boolean isCgdProtection;


    @ManyToMany(mappedBy = "itemEntities")
    private List<HoldingsEntity> holdingsEntities;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ITEM_AVAIL_STATUS_ID", insertable = false, updatable = false)
    private ItemStatusEntity itemStatusEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COLLECTION_GROUP_ID", insertable = false, updatable = false)
    private CollectionGroupEntity collectionGroupEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OWNING_INST_ID", insertable = false, updatable = false)
    private InstitutionEntity institutionEntity;

    @ManyToMany(mappedBy = "itemEntities",fetch = FetchType.EAGER)
    private List<BibliographicEntity> bibliographicEntities;


    public boolean isCgdProtection() {
        return isCgdProtection;
    }

    public void setCgdProtection(boolean cgdProtection) {
        isCgdProtection = cgdProtection;
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
     * Gets item status entity.
     *
     * @return the item status entity
     */
    public ItemStatusEntity getItemStatusEntity() {
        return itemStatusEntity;
    }

    /**
     * Sets item status entity.
     *
     * @param itemStatusEntity the item status entity
     */
    public void setItemStatusEntity(ItemStatusEntity itemStatusEntity) {
        this.itemStatusEntity = itemStatusEntity;
    }

    /**
     * Gets collection group entity.
     *
     * @return the collection group entity
     */
    public CollectionGroupEntity getCollectionGroupEntity() {
        return collectionGroupEntity;
    }

    /**
     * Sets collection group entity.
     *
     * @param collectionGroupEntity the collection group entity
     */
    public void setCollectionGroupEntity(CollectionGroupEntity collectionGroupEntity) {
        this.collectionGroupEntity = collectionGroupEntity;
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
     * Is complete boolean.
     *
     * @return the boolean
     */
    public boolean isComplete() {
        if (StringUtils.isNotBlank(this.catalogingStatus) && RecapCommonConstants.COMPLETE_STATUS.equals(this.catalogingStatus)) {
            return true;
        }
        return false;
    }
}


