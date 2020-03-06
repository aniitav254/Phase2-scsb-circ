package org.recap.model;

import org.recap.model.jpa.DeliveryRestrictionAbstractEntity;
import org.recap.model.jpa.InstitutionEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by harikrishnanv on 3/4/17.
 */

@Entity
@Table(name="delivery_restriction_cross_partner_t",schema="recap",catalog="")
public class DeliveryRestrictionEntity extends DeliveryRestrictionAbstractEntity {

    @ManyToMany(mappedBy = "deliveryRestrictionEntityList")
    private List<CustomerCodeEntity> customerCodeEntityList;

    /**
     * Gets customer code entity list.
     *
     * @return the customer code entity list
     */
    public List<CustomerCodeEntity> getCustomerCodeEntityList() {
        return customerCodeEntityList;
    }

    /**
     * Sets customer code entity list.
     *
     * @param customerCodeEntityList the customer code entity list
     */
    public void setCustomerCodeEntityList(List<CustomerCodeEntity> customerCodeEntityList) {
        this.customerCodeEntityList = customerCodeEntityList;
    }

}
