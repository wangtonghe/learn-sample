package com.wthfeng.learn.net.socket;


/**
 * @author wangtonghe
 * @date 2017/8/25 17:52
 */
public interface VoteMsgCoder {


    byte[] encoder(VoteMsg voteMsg);

    VoteMsg decoder(byte[] input);
}
