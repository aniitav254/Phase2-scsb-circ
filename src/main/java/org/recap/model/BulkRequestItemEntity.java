package org.recap.model;

import org.recap.model.jpa.BulkRequestItemAbstractEntity;
import org.recap.model.jpa.InstitutionEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeshbabuk on 10/10/17.
 */
@Entity
@Table(name = "bulk_request_item_t", schema = "recap", catalog = "")
public class BulkRequestItemEntity extends BulkRequestItemAbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUESTING_INST_ID", insertable = false, updatable = false)
    private InstitutionEntity institutionEntity;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "bulk_request_t",
            joinColumns = @JoinColumn(name = "BULK_REQUEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "REQUEST_ID"))
    private List<RequestItemEntity> requestItemEntities;

    public InstitutionEntity getInstitutionEntity() {
        return institutionEntity;
    }

    public void setInstitutionEntity(InstitutionEntity institutionEntity) {
        this.institutionEntity = institutionEntity;
    }

    public List<RequestItemEntity> getRequestItemEntities() {
        return requestItemEntities;
    }

    public void setRequestItemEntities(List<RequestItemEntity> requestItemEntities) {
        this.requestItemEntities = requestItemEntities;
    }


}
