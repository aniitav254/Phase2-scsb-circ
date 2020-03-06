package org.recap.ils.model.nypl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by rajeshbabuk on 8/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "updatedDate",
        "createdDate"
})
public class CommonData {

    @JsonProperty("updatedDate")
    private Object updatedDate;
    @JsonProperty("createdDate")
    private String createdDate;

    /**
     * Gets updated date.
     *
     * @return The  updatedDate
     */
    @JsonProperty("updatedDate")
    public Object getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets updated date.
     *
     * @param updatedDate The updatedDate
     */
    @JsonProperty("updatedDate")
    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets created date.
     *
     * @return The  createdDate
     */
    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate The createdDate
     */
    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}

