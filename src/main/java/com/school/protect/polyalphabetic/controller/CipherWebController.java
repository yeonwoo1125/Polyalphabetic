package com.school.protect.polyalphabetic.controller;

import com.school.protect.polyalphabetic.domain.Cipher;
import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.service.CipherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CipherWebController {

    private CipherService cipherService;

    @GetMapping()
    public String mainPage(@ModelAttribute("form") CipherRequestDto dto){

        return "main";
    }

    @PostMapping("/cipher")
    public String insertCipher(CipherRequestDto dto, Model m){
        Long id = cipherService.save(dto);
        Cipher cipher = cipherService.getCipher(id);

        m.addAttribute("key",cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        m.addAttribute("id",id);
        return "chkInput";
    }

    @GetMapping("/encryption/{id}")
    public String valueToEncryption(@PathVariable Long id, Model m){
        Cipher cipher = cipherService.getCipher(id);

        m.addAttribute("key", cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        m.addAttribute("encryption", cipher.getEncryption());
        return "showEncryption";
    }

    @GetMapping("/encryption/course/{id}")
    public String encryptionCourse(@PathVariable Long id, Model m){
        Cipher cipher = cipherService.getCipher(id);
        m.addAttribute("key",cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        m.addAttribute("cryptogram", cipher.getCryptogram());
        m.addAttribute("id", id);
        return "showEncryptionCourse";
    }

    @GetMapping("/decryption/{id}")
    public String encryptionToValue(@PathVariable Long id, Model m){
        Cipher cipher = cipherService.getCipher(id);

        m.addAttribute("key", cipher.getKey());
        m.addAttribute("value",cipher.getValue());
        m.addAttribute("encryption", cipher.getEncryption());
        m.addAttribute("decryption", cipher.getValue());
        return "showDecryption";
    }
    @DeleteMapping()
    public String deleteCipher(){
        cipherService.deleteCipher();
        return "redirect:";
    }
}
