package com.school.protect.polyalphabetic.controller;

import com.school.protect.polyalphabetic.domain.Cipher;
import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.service.CipherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class CipherWebController {

    private CipherService cipherService;

    @GetMapping()
    public String mainPage(@ModelAttribute("form") CipherRequestDto dto){

        return "main";
    }

    @PostMapping("/cipher")
    public String sendCipherId(CipherRequestDto dto, Model m){
        Long id = cipherService.save(dto);
        Cipher cipher = cipherService.getCipher(id);

        m.addAttribute("key",cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        return "okay";
    }
}
