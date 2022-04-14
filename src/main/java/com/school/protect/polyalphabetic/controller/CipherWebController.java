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
    public String sendCipherId(CipherRequestDto dto, RedirectAttributes redirectAttributes){
        Long id = cipherService.save(dto);
        redirectAttributes.addAttribute("id",id);
        return "redirect:/{id}";
    }

    @GetMapping("/{id}")
    public String showCipher(@PathVariable Long id, Model m){
        Cipher cipher = cipherService.getCipher(id);
        m.addAttribute("key", cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        m.addAttribute("encryption", cipher.getEncryption());
        m.addAttribute("cryptogram", cipher.getCryptogram());

        return "showCipher";
    }
}
