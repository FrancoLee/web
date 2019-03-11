package com.lx.animation.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.animation.adv.Adv;
import com.lx.animation.adv.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by root on 17-12-17.
 */
@Controller
public class BackAdvController {
    @Autowired
    @Qualifier("AdvService")
    AdvService advService;

    @RequestMapping("/back/adv/carousel")
    public String getCarousel(Model model) {
        List<Adv> advList = advService.listCarousel();
        System.out.println(advList.toString());
        if (advList != null)
            model.addAttribute("adv_list", advList);
        return "/back/web/adv_info";
    }

    @RequestMapping("/back/adv/logo")
    public String getLogo(Model model) {
        Adv adv = advService.getLogo();
        if (adv != null)
            model.addAttribute("adv", adv);
        return "/back/web/logo";
    }

    @RequestMapping("/back/adv/addCarousel")
    @ResponseBody
    public String addCarousel(@RequestParam("file") MultipartFile file) {
        JSONObject json = new JSONObject();
        int id = advService.addCarousel(file);
        json.put("id", id);
        return json.toString();
    }

    @RequestMapping("/back/adv/updateCarousel")
    @ResponseBody
    public String updateCarousel(@RequestParam("id") int id,
                                 @RequestParam("info") String info,
                                 @RequestParam("rank") int rank) {
        if (advService.updateCarousel(id, info, rank))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/adv/deleteCarousel")
    @ResponseBody
    public String deleteCarousel(@RequestParam("id") int id) {
        if (advService.deleteCarousel(id))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/adv/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestParam("check") String check) {
        if (advService.deleteBatchCarousel(check))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/adv/add")
    public String addAdvIndex() {
        return "/back/web/adv_add";
    }

    @RequestMapping("/back/adv/logo_edit")
    public String logoChangeIndex() {
        return "/back/web/logo_edit";
    }

    @RequestMapping("/back/adv/updateLogoInfo")
    @ResponseBody
    public String updateLogoInfo(@RequestParam("info") String info) {
        if (advService.updateLogoInfo(info))
            return "1";
        else
            return "0";
    }

    @RequestMapping("/back/adv/updateLogoPic")
    @ResponseBody
    public String updateLogoPic(@RequestParam("file") MultipartFile file) {
        JSONObject json = new JSONObject();
        if (advService.updateLogoPic(file))
            json.put("s", 1);
        else
            json.put("s", 0);
        return json.toString();
    }
}
