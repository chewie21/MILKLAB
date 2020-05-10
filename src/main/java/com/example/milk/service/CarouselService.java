package com.example.milk.service;

import com.example.milk.domain.Carousel;
import com.example.milk.repos.CarouselRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CarouselService {

    @Autowired
    private CarouselRepo carouselRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public Iterable<Carousel> findAll() {
        return carouselRepo.findAll();
    }

    public boolean checkImg(MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
            return true;
        } else return false;
    }

    public void newCarousel (String carouselInfo, MultipartFile file) throws IOException {
        Carousel carousel = new Carousel(carouselInfo);
        if (checkImg(file)) {
            carousel.setCarouselImg(file.getOriginalFilename());
        }
        carouselRepo.save(carousel);
    }
    public void saveCarousel (Carousel carousel, String carouselInfo, MultipartFile file) throws IOException {
        carousel.setCarouselInfo(carouselInfo);
        if (checkImg(file)) {
            carousel.setCarouselImg(file.getOriginalFilename());
        }
        carouselRepo.save(carousel);
    }
    public void deleteCarousel (Carousel carousel) {
        carouselRepo.delete(carousel);
    }
}
