package com.capitalone.nsb.marketing.stockpile.domain;


import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "SKU", "item_name", "description", "price_per_unit", "quantity", "UPC", "image_url",
        "tax_exempt"
})
public class Item {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price_per_unit")
    private Integer pricePerUnit;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("UPC")
    private Integer uPC;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("SKU")
    private Integer sKU;
    @JsonProperty("tax_exempt")
    private Boolean taxExempt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("item_name")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("item_name")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("price_per_unit")
    public Integer getPricePerUnit() {
        return pricePerUnit;
    }

    @JsonProperty("price_per_unit")
    public void setPricePerUnit(Integer pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("UPC")
    public Integer getUPC() {
        return uPC;
    }

    @JsonProperty("UPC")
    public void setUPC(Integer uPC) {
        this.uPC = uPC;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("SKU")
    public Integer getSKU() {
        return sKU;
    }

    @JsonProperty("SKU")
    public void setSKU(Integer sKU) {
        this.sKU = sKU;
    }

    @JsonProperty("tax_exempt")
    public Boolean getTaxExempt() {
        return taxExempt;
    }

    @JsonProperty("tax_exempt")
    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                ", uPC=" + uPC +
                ", imageUrl='" + imageUrl + '\'' +
                ", sKU=" + sKU +
                ", taxExempt=" + taxExempt +
                '}';
    }
}