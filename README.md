# 이니시스 - 가상 계좌번호(채번)

 회사에서 가상 계좌번호 채번 및 입금여부 확인을 위해, 이니시스 API를 사용하게 되었다. 처음하는 작업이기에, 아래에 따로 정리해본다. (문서에 일부 내용을 가져와, 내가 이해한 내용에 대한 설명을 붙였다.)





## 대칭키

INIAPI는 필드 암호화 및 전문 Hash를 위해 대칭키를 사용합니다
KEY와 IV는 [**상점관리자**](https://iniweb.inicis.com/)에서 생성/조회 가능합니다.

=> hashData 및 AES 암호화를 위해 사용. 



## 암호화

INIAPI는 주요 데이터는 암호화하여 전송하며, 암호화 알고리즘은 **AES(AES/CBC/PKCS5Padding)**를 사용합니다.

=> 현재 암호화가 필요로 하는 데이터는 [ cashRegNo ]로 현금영수증 발행정보이다. (API 문서 기준)



## API 요청

**각 파라미터는 key=value로 세팅하여 POST로 요청하세요.**

 => 쿼리스트링 형식으로 데이터 전달.

=> UriComponentsBuilder 클래스를 사용하여, 쿼리스트링 생성.

```java
/**
 * 가상 계좌번호을 채번하기 위한 URL 생성
 * @param vacctReqDto   요청 데이터
 * @param requestUrl    요청 URL
 */
public String buildVacctReqUri(VacctReqDto vacctReqDto, String requestUrl) {
    return UriComponentsBuilder.fromHttpUrl(requestUrl)
        .queryParam("type", vacctReqDto.getType())
        .queryParam("paymethod", vacctReqDto.getPaymethod())
        .queryParam("timestamp", vacctReqDto.getTimestamp())
        .queryParam("clientIp", vacctReqDto.getClientIp())
        .queryParam("mid", vacctReqDto.getMid())
        .queryParam("moid", vacctReqDto.getMoid())
        .queryParam("url", vacctReqDto.getUrl())
        .queryParam("goodName", vacctReqDto.getGoodName())
        .queryParam("price", vacctReqDto.getPrice())
        .queryParam("currency", vacctReqDto.getCurrency())
        .queryParam("buyerName", vacctReqDto.getBuyerName())
        .queryParam("buyerEmail", vacctReqDto.getBuyerEmail())
        .queryParam("bankCode", vacctReqDto.getBankCode())
        .queryParam("buyerTel", vacctReqDto.getBuyerTel())
        .queryParam("dtInput", vacctReqDto.getDtInput())
        .queryParam("tmInput", vacctReqDto.getTmInput())
        .queryParam("nmInput", vacctReqDto.getNmInput())
        .queryParam("flgCash", vacctReqDto.getFlgCash())
        .queryParam("cashRegNo", vacctReqDto.getCashRegNo())
        .queryParam("hashData", vacctReqDto.getHashData())
        .toUriString();
}
```



## hash 검증

INIAPI는 요청전문 위변조 확인을 위해 hash를 생성합니다.
hash 알고리즘은 **SHA-512**를 사용하며, hash에 필요한 PlainText는 각 API별로 상이합니다.

=> 요청 시, hashData를 반드시 생성하여 전달.

=> hashData는 KEY, type, paymethod, timestamp, clientIp, mid, moid, price 더하여 생성.

```java
/**
 * 가상 계좌번호을 채번하기 위한 hash 값 생성
 * @param vacctReqDto   요청 데이터
 */
public String generateVacctReqHashData(VacctReqDto vacctReqDto) throws Exception {
    return crypt.getSha512(KEY +
                           vacctReqDto.getType() +
                           vacctReqDto.getPaymethod() +
                           vacctReqDto.getTimestamp() +
                           vacctReqDto.getClientIp() +
                           vacctReqDto.getMid() +
                           vacctReqDto.getMoid() +
                           vacctReqDto.getPrice());
}
```



## 가상계좌입금통보

상점관리자 페이지에 로그인 후, [ 통보 URL ]로 데이터를 얻는다.

(아직 상점관리자 페이지에 로그인을 하지 못하므로, 추후 상세 내역을 업데이트할 예정이다. )



## JAVA 내부에서 API 요청

- 나는 JAVA 내부에서 API를 요청하기 위해, SPRING에서 제공해주는 <code>RestTemplate</code>를 사용하였다.

- header에 <code>Content-type</code>, <code>charset</code>를 추가하기 위해  <code>HttpHeaders</code>를 이용하였다.

```java
/**
 * 이니시스 API: 계좌번호 채번
 * POST /api/v1/formpay HTTP/1.1
 * Host: iniapi.inicis.com
 * Content-type: application/x-www-form-urlencoded;charset=utf-8
 * @param vacctReqDto   요청 데이터
 */
public String createVirtualAccount(VacctReqDto vacctReqDto) {
    String url = "/api/v1/formpay";

    RestTemplate restTemplate = new RestTemplate();
    String response = null;
    try {
        vacctReqDto.setUrl(SITE_URL);
        vacctReqDto.setType("Pay");
        vacctReqDto.setPaymethod("Vacct");
        vacctReqDto.setTimestamp(SignatureUtil.getTimestamp());
        vacctReqDto.setClientIp(InetAddress.getLocalHost().getHostAddress());
        vacctReqDto.setMid(MID);
        vacctReqDto.setMoid(MID + "_" + SignatureUtil.getTimestamp());
        vacctReqDto.setHashData(generateVacctReqHashData(vacctReqDto));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("charset", "utf-8");

        response = restTemplate.exchange(
            buildVacctReqUri(vacctReqDto, HOST + url),
            HttpMethod.POST,
            new HttpEntity<>(headers),
            String.class).toString();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return response;
}
```









## 참고문헌

> INICIS WEB MANUAL: https://manual.inicis.com/iniapi/