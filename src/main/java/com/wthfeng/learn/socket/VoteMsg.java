package com.wthfeng.learn.socket;

/**
 * @author wangtonghe
 * @date 2017/8/25 17:51
 */
public class VoteMsg {

    private boolean isInquiry; // true if inquiry; false if vote
    private boolean isResponse;// true if response from server
    private int candidateID;   // in [0,1000]
    private long voteCount;    // nonzero only in response

    public boolean isInquiry() {
        return isInquiry;
    }

    public void setInquiry(boolean inquiry) {
        isInquiry = inquiry;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
