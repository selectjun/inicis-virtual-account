package com.inicis.vacct.service;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import com.inicis.vacct.dto.VacctReqDto;
import com.inicis.vacct.utils.Crypt;
import com.inicis.vacct.utils.SignatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

@Service
@PropertySource("classpath:application.properties")
public class InipayService {

    @Value("${site.url}")
    private String SITE_URL; // 사이트 URL

    @Value("${inipay.host}")
    private String HOST; // 이니시스 요청 URL

    @Value("${inipay.mid}")
    private String MID;	// 가맹점 ID

    @Value("${inipay.key}")
    private String KEY;	// 가맹점 ID

    @Value("${inipay.iv}")
    private String IV;	// 가맹점 ID

    @Autowired
    private Crypt crypt;

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

            System.out.print("Request URL: ");
            System.out.println(buildVacctReqUri(vacctReqDto, HOST + url));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.add("charset", "utf-8");

            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            response = restTemplate.exchange(
                    buildVacctReqUri(vacctReqDto, HOST + url),
                    HttpMethod.POST,
                    new HttpEntity<>(headers),
                    String.class).getBody();

            System.out.print("Response: ");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

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

    /**
     * 가상 계좌번호을 채번하기 위한 URL 생성
     * @param vacctReqDto   요청 데이터
     * @param requestUrl    요청 URL
     */
    public String buildVacctReqUri(VacctReqDto vacctReqDto, String requestUrl) {
        String url = UriComponentsBuilder.fromHttpUrl(requestUrl)
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
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        return UriUtils.decode(url, "UTF-8");
    }

}

