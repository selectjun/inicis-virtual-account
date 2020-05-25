package com.inicis.vacct.controller;

import com.inicis.vacct.dto.VacctReqDto;
import com.inicis.vacct.service.InipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InicisController {

    @Autowired
    private InipayService inipayService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/vacct", method = RequestMethod.POST)
    public String getVacct(VacctReqDto vacctReqDto) {
        return inipayService.createVirtualAccount(vacctReqDto);
    }

}
