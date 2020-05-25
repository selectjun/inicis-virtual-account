package com.inicis.vacct.dto;

public class VacctReqDto {

    /**
     * "Pay" 고정
     * size: 3
     * not null: true
     */
    private String type;

    /**
     * "Vacct" 고정
     * size: 5
     * not null: true
     */
    private String paymethod;

    /**
     * 전문생성시간(YYYYMMDDhhmmss)
     * size: 14
     * not null: true
     */
    private String timestamp;

    /**
     * 요청 서버IP
     * size: 15
     * not null: true
     */
    private String clientIp;

    /**
     * 가맹점 ID
     * size:10
     * not null: true
     */
    private String mid;

    /**
     * 가맹점주문번호
     * size: 40
     * not null: true
     */
    private String moid;

    /**
     * 가맹점 URL
     * size: 50
     * not null: true
     */
    private String url;

    /**
     * 상품명
     * size: 80
     * not null: true
     */
    private String goodName;

    /**
     * 결제금액
     * size: 12
     * not null: true
     */
    private String price;

    /**
     * 통화코드(WON/USD)
     * size: 3
     * not null: false
     */
    private String currency;

    /**
     * 구매자명
     * size: 80
     * not null: true
     */
    private String buyerName;

    /**
     * 구매자이메일
     * size: 60
     * not null: true
     */
    private String buyerEmail;

    /**
     * 구매자 연락처
     * size: 40
     * not null: false
     */
    private String buyerTel;

    /**
     * 은행코드
     * size: 2
     * not null: true
     */
    private String bankCode;

    /**
     * 입금예정일자(YYYYMMDD)
     * size: 8
     * not null: true
     */
    private String dtInput;

    /**
     * 입금예정시간(HHMM)
     * size: 4
     * not null: true
     */
    private String tmInput;

    /**
     * 입금자명
     * size: 20
     * not null: true
     */
    private String nmInput;

    /**
     * 현금영수증 발행여부(0: 미발행, 1: 소득공제 발행, 2: 지출증빙)
     * size: 1
     * not null: false
     */
    private String flgCash;

    /**
     * 현금영수증 발행정보(주민번호, 휴대폰번호, 사업장등록번호 등) ENC
     * size:
     * not null: false
     */
    private String cashRegNo;

    /**
     * 전문위변조 HASHHASH
     * hash(KEY+type+paymethod+timestamp+clientIp+mid+moid+price)
     * size: 128
     * not null: true
     */
    private String hashData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMoid() {
        return moid;
    }

    public void setMoid(String moid) {
        this.moid = moid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getDtInput() {
        return dtInput;
    }

    public void setDtInput(String dtInput) {
        this.dtInput = dtInput;
    }

    public String getTmInput() {
        return tmInput;
    }

    public void setTmInput(String tmInput) {
        this.tmInput = tmInput;
    }

    public String getNmInput() {
        return nmInput;
    }

    public void setNmInput(String nmInput) {
        this.nmInput = nmInput;
    }

    public String getFlgCash() {
        return flgCash;
    }

    public void setFlgCash(String flgCash) {
        this.flgCash = flgCash;
    }

    public String getCashRegNo() {
        return cashRegNo;
    }

    public void setCashRegNo(String cashRegNo) {
        this.cashRegNo = cashRegNo;
    }

    public String getHashData() {
        return hashData;
    }

    public void setHashData(String hashData) {
        this.hashData = hashData;
    }

}

