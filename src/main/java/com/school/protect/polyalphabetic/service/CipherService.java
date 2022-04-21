package com.school.protect.polyalphabetic.service;

import com.school.protect.polyalphabetic.domain.Cipher;
import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.domain.CipherJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CipherService {

    private CipherJPARepository cipherRepository;
    public static char alphabetBoard[][] = new char[5][5]; //암호판
    public static boolean oddFlag = false;
    public static String zCheck = "";

    @Transactional
    public Long save(CipherRequestDto dto) {
        return cipherRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Cipher getCipher(Long id) {
        encryption(id);
        return cipherRepository.findById(id).get();
    }

    @Transactional
    public void deleteCipher() {
        cipherRepository.deleteAll();
    }

    //암호화
    public void encryption(Long id) {
        Cipher cipher = cipherRepository.findById(id).get();
        String cryptogram = setBoards(cipher.getKey());
        String value = cipher.getValue();

        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ') //공백제거
            {
                value = value.substring(0, i) + value.substring(i + 1, value.length());

            }

            if (value.charAt(i) == 'z') //z를 q로 바꿔줘서 처리함.
            {
                value = value.substring(0, i) + 'q' + value.substring(i + 1, value.length());
                zCheck += 1;
            } else {
                zCheck += 0;
            }
        }

        String encryption = strEncryption(cipher.getKey(), value);
        //암호판과 암호화된 문자열 저장
        cipher.setCryptogram(cryptogram, encryption);

    }

    //암호판 세팅
    public String setBoards(String key) {
        String keyForSet = "";                    // 중복된 문자가 제거된 문자열을 저장할 문자열.
        boolean duplicationFlag = false;        // 문자 중복을 체크하기 위한 flag 변수.
        int keyLengthCount = 0;                    // alphabetBoard에 keyForSet을 넣기 위한 count변수.

        key += "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();    // 키에 모든 알파벳을 추가.

        // 중복처리
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < keyForSet.length(); j++) {
                if (key.charAt(i) == keyForSet.charAt(j)) {
                    duplicationFlag = true;
                    break;
                }
            }
            if (!(duplicationFlag)) keyForSet += key.charAt(i);  //만약 중복문자가 아니면 keyforSet에 현재 값을 넣어
            duplicationFlag = false;    //duplicationFlag는 다시 false로 초기화
        }

        //배열에 대입
        for (int i = 0; i < alphabetBoard.length; i++) {
            for (int j = 0; j < alphabetBoard[i].length; j++) {
                alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
            }
        }

        return keyForSet;
    }

    //매핑
    public String strEncryption(String key, String value) {
        ArrayList<char[]> playFair = new ArrayList<>();   //
        ArrayList<char[]> encPlayFair = new ArrayList<>();    //암호문
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        String encStr = "";

        //암호키의 길이만큼 반복
        for (int i = 0; i < value.length(); i += 2) {
            char[] tmpArr = new char[2];
            tmpArr[0] = value.charAt(i);
            try {
                if (value.charAt(i) == value.charAt(i + 1)) {
                    tmpArr[1] = 'X';    //매핑한 값 2개가 같으면 뒤쪽 값을 X로 변경
                    i--;                //i+2했을때 뒤쪽 값이 나올 수 있도록 i--
                } else {
                    tmpArr[1] = value.charAt(i + 1);
                }
            } catch (StringIndexOutOfBoundsException e) {
                tmpArr[1] = 'X';
                oddFlag = true;
            }
            playFair.add(tmpArr);   //매핑된 값을 playFair에 크기2 배열로 추가
        }

        for (int i = 0; i < playFair.size(); i++) {
            char[] tmpArr = new char[2];
            for (int j = 0; j < alphabetBoard.length; j++) {
                for (int k = 0; k < alphabetBoard[j].length; k++) {
                    //암호테이블에서 매핑한 두개의 알파벳과 같은 알파벳의 위치를 저장
                    if (alphabetBoard[j][k] == playFair.get(i)[0]) {
                        x1 = j;
                        y1 = k;
                    }
                    if (alphabetBoard[j][k] == playFair.get(i)[1]) {
                        x2 = j;
                        y2 = k;
                    }
                }
            }


            if (x1 == x2) {
                tmpArr[0] = alphabetBoard[x1][(y1 + 1) % 5];
                tmpArr[1] = alphabetBoard[x2][(y2 + 1) % 5];
            } else if (y1 == y2) {
                tmpArr[0] = alphabetBoard[(x1 + 1) % 5][y1];
                tmpArr[1] = alphabetBoard[(x2 + 1) % 5][y2];
            } else {
                tmpArr[0] = alphabetBoard[x2][y1];
                tmpArr[1] = alphabetBoard[x1][y2];
            }
            encPlayFair.add(tmpArr);
        }

        for (int i = 0; i < encPlayFair.size(); i++) {
            encStr += encPlayFair.get(i)[0] + "" + encPlayFair.get(i)[1] + " ";
        }

        return encStr;
    }
}
