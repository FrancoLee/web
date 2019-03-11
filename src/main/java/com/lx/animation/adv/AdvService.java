package com.lx.animation.adv;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by root on 18-7-22.
 */
public interface AdvService {
    Adv getLogo();

    List<Adv> listCarousel();

    boolean deleteCarousel(int id);

    boolean deleteBatchCarousel(String check);

    boolean updateAdvPic(int id, MultipartFile file);

    boolean updateLogoPic(MultipartFile file);

    int addCarousel(MultipartFile file);

    boolean updateCarousel(int id, String info, int rank);

    boolean updateLogoInfo(String info);
}
