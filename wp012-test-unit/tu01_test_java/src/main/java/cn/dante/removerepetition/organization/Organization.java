package cn.dante.removerepetition.organization;

import lombok.Data;

@Data
public class Organization {
    //这段代码也能去重吗
    String id;
    String eName; //英文名字
    String cName; //中文名字
    String telCountryCode;
    String telAreaCode;
    String telLocalNumber;
    String faxCountryCode;
    String faxAreaCode;
    String faxLocalNumber;
    String contactPersonEFirstName; //联系人的英文名
    String contactPersonELastName; //联系人的英文姓
    String contactPersonCFirstName; //联系人的中文名
    String contactPersonCLastName; //联系人的中文姓
    String contactPersonTelCountryCode;
    String contactPersonTelAreaCode;
    String contactPersonTelNumber;
    String contactPersonFaxCountryCode;
    String contactPersonFaxAreaCode;
    String contactPersonFaxLocalNumber;
    String contactPersonMobileCountryCode;
    String contactPersonMobileAreaCode;
    String contactPersonMobileLocalNumber;

}