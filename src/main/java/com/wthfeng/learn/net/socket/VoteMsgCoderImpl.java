package com.wthfeng.learn.net.socket;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author wangtonghe
 * @date 2017/8/25 17:58
 */
public class VoteMsgCoderImpl implements VoteMsgCoder {
    @Override
    public byte[] encoder(VoteMsg voteMsg) {
        return new byte[0];
    }

    @Override
    public VoteMsg decoder(byte[] input){
        String text = "hello world this is my text";
        input = text.getBytes();
        ByteArrayInputStream msgStream = new ByteArrayInputStream(input);
        try {
            Scanner s = new Scanner(new InputStreamReader(msgStream, "ASCII"));
            String token = s.next();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new VoteMsg();
    }
}
