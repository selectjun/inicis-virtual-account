package com.inisis.vacct.dto;

public class VacctResDto {

    /**
     * 응답코드("00" 성공)
     * size: 2
     * not null: true
     */
    private String resultCode;

    /**
     * 응답메시지
     * size: 100
     * not null: true
     */
    private String resultMsg;

    /**
     * 채번TID
     * size: 40
     * not null: false
     */
    private String tid;

    /**
     * 승인일자
     * size: 8
     * not null: false
     */
    private String authDate;

    /**
     * 승인시간
     * size: 6
     * not null: false
     */
    private String authTime;

    /**
     * 가상계좌번호
     * size: 16
     * not null: false
     */
    private String vacct;

    /**
     * 예금주명
     * size:
     * not null: false
     */
    private String vaccName;

    /**
     * 가상계좌 은행코드
     * size: 2
     * not null: false
     */
    private String vacctBankCode;

    /**
     * 가상계좌 입금예정날짜
     * size: 2
     * not null: false
     */
    private String validDate;

    /**
     * 가상계좌 입금예정시간
     * size: 2
     * not null: false
     */
    private String validTime;

    /**
     * 결제금액
     * size: 12
     * not null: false
     */
    private String price;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public String getAuthTime() {
        return authTime;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }

    public String getVacct() {
        return vacct;
    }

    public void setVacct(String vacct) {
        this.vacct = vacct;
    }

    public String getVaccName() {
        return vaccName;
    }

    public void setVaccName(String vaccName) {
        this.vaccName = vaccName;
    }

    public String getVacctBankCode() {
        return vacctBankCode;
    }

    public void setVacctBankCode(String vacctBankCode) {
        this.vacctBankCode = vacctBankCode;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
