package com.school.protect.polyalphabetic.service;

import com.school.protect.polyalphabetic.domain.Cipher;
import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.domain.CipherJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CipherService {

    private CipherJPARepository cipherRepository;
    public static char alphaBoard[][] = new char[5][5]; //암호판
    public static boolean oddFlag = false;

    @Transactional
    public Long save(CipherRequestDto dto){
        return cipherRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Cipher getCipher(Long id){
        encryption(id);
        return cipherRepository.findById(id).get();
    }

    //암호화
    public void encryption(Long id){
        Cipher cipher = cipherRepository.findById(id).get();
        String cry = setBoards(cipher.getKey());
        String value = cipher.getValue();

        //공백 제거
        value.replaceAll(" ","");
        //z -> q
        value.replaceAll("z","q");

        String encry = strEncryption(cipher.getKey(), value);
        //암호판과 암호화된 문자열 저장
        cipher.setCryptogram(cry, encry);

    }

    //암호판 세팅
    public String setBoards(String key){
        String keyForSet=""; //중복 문자 제거
        key+="abcdefghijklmnopqrstuvwxyz";
        int keyLenCnt=0;

        //중복된 문자 제거
        for (int i = 0; i < key.length(); i++) {
            if(key.indexOf(key.charAt(i)) == i) keyForSet += key.charAt(i);
        }

        //암호판에 입력
        for(int i=0; i<alphaBoard.length; i++){
            for(int j=0; j<alphaBoard[i].length;j++){
                alphaBoard[i][j] = keyForSet.charAt(keyLenCnt++);
            }
        }
        return keyForSet;
    }

    //매핑
    public String strEncryption(String key, String value){
        String encry ="";
        ArrayList<char[]> playFair = new ArrayList<char[]>();
        ArrayList<char[]> encPlayFair = new ArrayList<char[]>();
        int x1 = 0 , x2 = 0 , y1 = 0, y2 = 0;
        String encStr ="";

        for( int i = 0 ; i < value.length() ; i+=2 ) // arraylist 세팅
        {
            char[] tmpArr = new char[2];
            tmpArr[0] = value.charAt(i);
            try{
                if( value.charAt(i) == value.charAt(i+1)) //글이 반복되면 x추가
                {
                    tmpArr[1] = 'x';
                    i--;
                }else{
                    tmpArr[1] = value.charAt(i+1);
                }
            }catch(StringIndexOutOfBoundsException e)
            {
                tmpArr[1] = 'x';
                oddFlag = true;
            }
            playFair.add(tmpArr);
        }

        for(int i = 0; i < playFair.size() ; i++ )
        {
            char[] tmpArr = new char[2];
            for( int j = 0 ; j < alphaBoard.length ; j++ ) //쌍자암호의 각각 위치체크
            {
                for( int k = 0 ; k < alphaBoard[j].length ; k++ )
                {
                    if(alphaBoard[j][k] == playFair.get(i)[0])
                    {
                        x1 = j;
                        y1 = k;
                    }
                    if(alphaBoard[j][k] == playFair.get(i)[1])
                    {
                        x2 = j;
                        y2 = k;
                    }
                }
            }


            if(x1==x2) //행이 같은경우
            {
                tmpArr[0] = alphaBoard[x1][(y1+1)%5];
                tmpArr[1] = alphaBoard[x2][(y2+1)%5];
            }
            else if(y1==y2) //열이 같은 경우
            {
                tmpArr[0] = alphaBoard[(x1+1)%5][y1];
                tmpArr[1] = alphaBoard[(x2+1)%5][y2];
            }
            else //행, 열 모두 다른경우
            {
                tmpArr[0] = alphaBoard[x2][y1];
                tmpArr[1] = alphaBoard[x1][y2];
            }

            encPlayFair.add(tmpArr);

        }

        for(int i = 0 ; i < encPlayFair.size() ; i++)
            encStr += encPlayFair.get(i)[0]+""+encPlayFair.get(i)[1]+" ";

        return encStr;
    }
}
