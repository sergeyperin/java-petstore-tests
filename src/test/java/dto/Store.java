package dto;

import org.joda.time.format.ISODateTimeFormat;

import java.math.BigInteger;

public class Store {
    private BigInteger id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    /**
     * No args constructor for use in serialization
     */
    public Store() {
    }

    /**
     * @param petId
     * @param quantity
     * @param id
     * @param shipDate
     * @param complete
     * @param status
     */
    public Store(BigInteger id, Integer petId, Integer quantity, String shipDate, String status, Boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = ISODateTimeFormat.dateTimeParser().parseDateTime(shipDate).toString();
        this.status = status;
        this.complete = complete;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}