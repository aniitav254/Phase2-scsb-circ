package org.recap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.recap.model.jpa.CustomerCodeAbstractEntity;
import org.recap.model.jpa.InstitutionEntity;

/**
 * Created by rajeshbabuk on 18/10/16.
 */
@Entity
@Table(name = "customer_code_t", schema = "recap", catalog = "")
public class CustomerCodeEntity extends CustomerCodeAbstractEntity implements Serializable, Comparable<CustomerCodeEntity> {

    @Column(name = "PWD_DELIVERY_RESTRICTIONS")
    private String pwdDeliveryRestrictions;

    @Column(name = "RECAP_DELIVERY_RESTRICTIONS")
    private String recapDeliveryRestrictions;

    @Column(name = "CIRC_DESK_LOCATION")
    private String pickupLocation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cross_partner_mapping_t", joinColumns = {
            @JoinColumn(name = "CUSTOMER_CODE_ID", referencedColumnName = "CUSTOMER_CODE_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "DELIVERY_RESTRICTION_CROSS_PARTNER_ID", referencedColumnName = "DELIVERY_RESTRICTION_CROSS_PARTNER_ID")})
    private List<DeliveryRestrictionEntity> deliveryRestrictionEntityList;

    @Override
    public int compareTo(CustomerCodeEntity customerCodeEntity) {
        if (null != this.getDescription() && null !=  customerCodeEntity && null != customerCodeEntity.getDescription()) {
            return this.getDescription().compareTo(customerCodeEntity.getDescription());
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        CustomerCodeEntity customerCodeEntity = (CustomerCodeEntity) object;

        if (getCustomerCodeId() != null ? !getCustomerCodeId().equals(customerCodeEntity.getCustomerCodeId()) : customerCodeEntity.getCustomerCodeId() != null)
            return false;
        if (customerCode != null ? !customerCode.equals(customerCodeEntity.customerCode) : customerCodeEntity.customerCode != null)
            return false;
        if (description != null ? !description.equals(customerCodeEntity.description) : customerCodeEntity.description != null)
            return false;
        return getCustomerCodeId() != null ? getCustomerCodeId().equals(customerCodeEntity.getOwningInstitutionId()) : customerCodeEntity.getOwningInstitutionId() == null;

    }

    @Override
    public int hashCode() {
        int result = getCustomerCodeId() != null ? getCustomerCodeId().hashCode() : 0;
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (getOwningInstitutionId() != null ? getOwningInstitutionId().hashCode() : 0);
        return result;
    }

    public List<DeliveryRestrictionEntity> getDeliveryRestrictionEntityList() {
        return deliveryRestrictionEntityList;
    }

    public void setDeliveryRestrictionEntityList(List<DeliveryRestrictionEntity> deliveryRestrictionEntityList) {
        this.deliveryRestrictionEntityList = deliveryRestrictionEntityList;
    }

    public String getPwdDeliveryRestrictions() {
        return pwdDeliveryRestrictions;
    }

    public void setPwdDeliveryRestrictions(String pwdDeliveryRestrictions) {
        this.pwdDeliveryRestrictions = pwdDeliveryRestrictions;
    }

    public String getRecapDeliveryRestrictions() {
        return recapDeliveryRestrictions;
    }

    public void setRecapDeliveryRestrictions(String recapDeliveryRestrictions) {
        this.recapDeliveryRestrictions = recapDeliveryRestrictions;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
