package vn.sapo.address.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AddressResult {

    private Integer id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private Integer customerId;

    private Integer supplierId;

    private String line1;

    private String line2;

    private Integer wardId;

    private String wardName;

    private Integer districtId;

    private String districtName;

    private Integer provinceId;

    private String provinceName;

    private String zipCode;

    private boolean isShipping;

    private boolean isReceiveBill;

    @Override
    public String toString() {
        return "AddressResult{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", customerId=" + customerId +
                ", supplierId=" + supplierId +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", wardId=" + wardId +
                ", wardName='" + wardName + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isShipping=" + isShipping +
                ", isReceiveBill=" + isReceiveBill +
                '}';
    }
}
