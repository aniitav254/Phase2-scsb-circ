package org.recap.ils.model.nypl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by rajeshbabuk on 23/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "jobId",
        "owningInstitutionId",
        "itemBarcode"
})
public class RecallData extends CommonData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("jobId")
    private String jobId;
    @JsonProperty("owningInstitutionId")
    private String owningInstitutionId;
    @JsonProperty("itemBarcode")
    private String itemBarcode;

    /**
     * Gets id.
     *
     * @return the id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets job id.
     *
     * @return the job id
     */
    @JsonProperty("jobId")
    public String getJobId() {
        return jobId;
    }

    /**
     * Sets job id.
     *
     * @param jobId the job id
     */
    @JsonProperty("jobId")
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * Gets owning institution id.
     *
     * @return the owning institution id
     */
    @JsonProperty("owningInstitutionId")
    public String getOwningInstitutionId() {
        return owningInstitutionId;
    }

    /**
     * Sets owning institution id.
     *
     * @param owningInstitutionId the owning institution id
     */
    @JsonProperty("owningInstitutionId")
    public void setOwningInstitutionId(String owningInstitutionId) {
        this.owningInstitutionId = owningInstitutionId;
    }

    /**
     * Gets item barcode.
     *
     * @return the item barcode
     */
    @JsonProperty("itemBarcode")
    public String getItemBarcode() {
        return itemBarcode;
    }

    /**
     * Sets item barcode.
     *
     * @param itemBarcode the item barcode
     */
    @JsonProperty("itemBarcode")
    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

}
