package org.recap.ils.model.nypl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by rajeshbabuk on 8/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "jobId",
        "processed",
        "success",
        "patronBarcode",
        "itemBarcode",
        "desiredDateDue"
})
public class CheckoutData extends CommonData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("jobId")
    private String jobId;
    @JsonProperty("processed")
    private Boolean processed;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("patronBarcode")
    private String patronBarcode;
    @JsonProperty("itemBarcode")
    private String itemBarcode;
    @JsonProperty("desiredDateDue")
    private String desiredDateDue;

    /**
     * Gets id.
     *
     * @return The  id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets job id.
     *
     * @return The  jobId
     */
    @JsonProperty("jobId")
    public String getJobId() {
        return jobId;
    }

    /**
     * Sets job id.
     *
     * @param jobId The jobId
     */
    @JsonProperty("jobId")
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * Gets processed.
     *
     * @return The  processed
     */
    @JsonProperty("processed")
    public Boolean getProcessed() {
        return processed;
    }

    /**
     * Sets processed.
     *
     * @param processed The processed
     */
    @JsonProperty("processed")
    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    /**
     * Gets success.
     *
     * @return The  success
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success The success
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Gets patron barcode.
     *
     * @return The  patronBarcode
     */
    @JsonProperty("patronBarcode")
    public String getPatronBarcode() {
        return patronBarcode;
    }

    /**
     * Sets patron barcode.
     *
     * @param patronBarcode The patronBarcode
     */
    @JsonProperty("patronBarcode")
    public void setPatronBarcode(String patronBarcode) {
        this.patronBarcode = patronBarcode;
    }

    /**
     * Gets item barcode.
     *
     * @return The  itemBarcode
     */
    @JsonProperty("itemBarcode")
    public String getItemBarcode() {
        return itemBarcode;
    }

    /**
     * Sets item barcode.
     *
     * @param itemBarcode The itemBarcode
     */
    @JsonProperty("itemBarcode")
    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    /**
     * Gets desired date due.
     *
     * @return The  desiredDateDue
     */
    @JsonProperty("desiredDateDue")
    public String getDesiredDateDue() {
        return desiredDateDue;
    }

    /**
     * Sets desired date due.
     *
     * @param desiredDateDue The desiredDateDue
     */
    @JsonProperty("desiredDateDue")
    public void setDesiredDateDue(String desiredDateDue) {
        this.desiredDateDue = desiredDateDue;
    }

}

