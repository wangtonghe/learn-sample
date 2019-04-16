package com.wthfeng.learn.lang.alg.dynamicprom;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangtonghe
 * @since 2019/4/7 19:33
 */
public class TimeAssign {

    private static final String END_FLAG = "#";

    private static final String SUFFIX_MIN = "min";

    private static final String LIGHTNING = "lightning";
    private static final int TIME_FOR_LIGHTNING = 5;

    private static Pattern pattern = Pattern.compile("\\d+min");

    private static final int TIME_MORNING = 180;

    private static final int TIME_MIN_AFTERNOON = 180;

    private static final int TIME_MAX_AFTERNOON = 240;

    public static void main(String[] args) {

        // 输入
        System.out.println("请按行输入会议描述，输入完成后以#结束(Please enter a talk by line and end with '#' when the input is completed)");
        Scanner scanner = new Scanner(System.in);
        List<TalkNode> talkList = new ArrayList<>();
        TalkNode talkNode;
        while (true) {
            String line = scanner.nextLine();
            if (line == null || "".equals(line)) {
                System.err.println("请输入会议");
                continue;
            }
            if (END_FLAG.equals(line.trim())) {
                break;
            }
            System.out.println("ok,continue...");
            if ((talkNode = parseTalk(line)) != null) {
                talkList.add(talkNode);
            }
        }

        // 处理
        int day = 1;
        List<ResultNode> resultList = new LinkedList<>();
        while (talkList.size() > 0) {
            handleMorningTalk(day, talkList, resultList);
            handleAfternoonTalk(day, talkList, resultList);
            day++;
        }

        // 输出结果
        printResult(resultList);
    }

    /**
     * 打印输出结果
     *
     * @param resultNodeList 结果集
     */
    private static void printResult(List<ResultNode> resultNodeList) {

        if (resultNodeList == null || resultNodeList.size() == 0) {
            return;
        }
        System.out.println("\n输出结果：");
        resultNodeList.forEach(resultNode -> {
            if (resultNode.noon == 0) {
                System.out.println("Tack " + resultNode.day);
            }
            int noon = resultNode.noon;
            int total = 0;
            for (TalkNode node : resultNode.talks) {
                System.out.println(getTimeDesc(noon, total) + " " + node.getTitle());
                total += node.time;
            }
            if (noon == 0) {
                System.out.println("12:00PM Lunch");
            } else {
                if (total < TIME_MIN_AFTERNOON) {
                    total = TIME_MIN_AFTERNOON;
                }
                System.out.println(getTimeDesc(1, total) + " Networking Event");
                System.out.println();
            }
        });
    }

    /**
     * 获取时间描述
     *
     * @param noon 上下午标识
     * @param time 时间
     * @return string
     */
    private static String getTimeDesc(int noon, int time) {
        String desc;
        if (noon == 0) {
            int hour = 9 + time / 60;
            int min = time % 60;
            String hourStr = hour < 10 ? ("0" + hour) : "" + hour;
            String minStr = min < 10 ? "0" + min : "" + min;
            desc = hourStr + ":" + minStr + "AM";

        } else {
            int hour = 1 + time / 60;
            int min = time % 60;
            String hourStr = hour < 10 ? ("0" + hour) : "" + hour;
            String minStr = min < 10 ? "0" + min : "" + min;
            desc = hourStr + ":" + minStr + "PM";
        }
        return desc;

    }

    /**
     * 处理上午时间安排
     *
     * @param day        第几天
     * @param originList 处理前数组
     * @param resultList 处理结果集
     */
    private static void handleMorningTalk(int day, List<TalkNode> originList, List<ResultNode> resultList) {


        int flag = isLargeSum(originList, TIME_MORNING);
        List<List<TalkNode>> sumList;
        // 若时间不足
        if (flag <= 0) {
            resultList.add(new ResultNode(day, 0, new ArrayList<>(originList)));
            originList.clear();

        } else {
            // 获取时间刚好满足180分钟的会议集合
            List<TalkNode> list = (sumList = findSumList(originList, TIME_MORNING, 0, 1)).size() > 0 ? sumList.get(0) : null;
            // 若没有,选取一个接近的
            if (list == null) {
                list = findCloseSumList(originList, TIME_MORNING);
            }
            originList.removeAll(list);
            resultList.add(new ResultNode(day, 0, list));
        }
    }


    /**
     * 处理下午时间安排
     *
     * @param day        第几天
     * @param originList 处理前数组
     * @param resultList 处理结果集
     */
    private static void handleAfternoonTalk(int day, List<TalkNode> originList, List<ResultNode> resultList) {


        int flag = isLargeSum(originList, TIME_MAX_AFTERNOON);
        List<List<TalkNode>> sumList;
        // 若时间不足
        if (flag <= 0) {
            resultList.add(new ResultNode(day, 1, new ArrayList<>(originList)));
            originList.clear();

        } else {
            // 选取时间为240分钟的会议集合
            List<TalkNode> list = (sumList = findSumList(originList, TIME_MAX_AFTERNOON, 0, 1)).size() > 0 ? sumList.get(0) : null;
            // 若无，选择一个接近的
            if (list == null) {
                list = findCloseSumList(originList, TIME_MAX_AFTERNOON);
            }
            originList.removeAll(list);

            resultList.add(new ResultNode(day, 1, list));
        }
    }


    /**
     * 解析输入文本
     *
     * @param line 输入行
     * @return
     */
    private static TalkNode parseTalk(String line) {
        int time = 0;

        if (line.contains(LIGHTNING)) {
            time = TIME_FOR_LIGHTNING;
        } else {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String text = matcher.group();
                time = Integer.parseInt(text.substring(0, text.indexOf(SUFFIX_MIN)));
            }
        }
        if (time <= 0) {
            return null;
        }
        return new TalkNode(line, time);
    }

    /**
     * 获取接近sum的子集合（这里不是最接近集合，因为没要求）
     *
     * @param originList 源集合
     * @param sum        和
     * @return 子集合
     */
    private static List<TalkNode> findCloseSumList(List<TalkNode> originList, int sum) {
        originList.sort(((o1, o2) -> o2.getTime() - o1.getTime()));
        int total = 0;
        List<TalkNode> resultList = new ArrayList<>();
        for (TalkNode node : originList) {

            if (node.getTime() + total < sum) {
                resultList.add(node);
                total += node.getTime();
            }
        }
        return resultList;
    }


    /**
     * 遍历求和为sum的子数组集合,目前只取一个
     *
     * @param talkNodeList 目标数组
     * @param sum          和
     * @param start        开始位置，记录遍历到哪里了
     * @param num          所求数组个数，目前为1
     * @return 子数组集合
     */
    private static List<List<TalkNode>> findSumList(List<TalkNode> talkNodeList, int sum, int start, int num) {

        List<Integer> result = new ArrayList<>();
        int len = 1 << talkNodeList.size();
        int tmp;
        for (int i = start; i < len; i++) {
            String binStr = Integer.toBinaryString(i);
            int binLen = binStr.length();
            tmp = 0;
            for (int j = 0; j < binLen; j++) {
                if (binStr.charAt(j) == '1') {
                    int no = talkNodeList.size() - 1 - (binLen - j - 1);
                    tmp += talkNodeList.get(no).time;
                }
            }
            if (tmp == sum) {
                result.add(i);
                if (result.size() == num) {
                    break;
                }
            }
        }
        List<String> finalResultList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            StringBuilder binStr = new StringBuilder(Integer.toBinaryString(result.get(i)));
            int fillNum = talkNodeList.size() - binStr.length();
            for (int j = 0; j < fillNum; j++) {
                binStr.insert(0, "0");
            }
            finalResultList.add(binStr.toString());
        }

        List<List<TalkNode>> list = new ArrayList<>();

        for (String str : finalResultList) {
            List<TalkNode> each = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '1') {
                    each.add(talkNodeList.get(i));
                }
            }
            list.add(each);
        }

        return list;
    }

    private static int isLargeSum(Collection<TalkNode> talkNodes, int sum) {
        return (int) talkNodes.stream().filter(e -> e != null && e.getTime() > 0).mapToLong(TalkNode::getTime).sum() - sum;
    }


    private static class ResultNode {
        /**
         * 表示第几天
         */
        private int day;

        /**
         * 表示上午、下午（0为上午，1为下午）
         */
        private int noon;

        private List<TalkNode> talks;

        private ResultNode(int day, int noon, List<TalkNode> talks) {
            this.day = day;
            this.noon = noon;
            this.talks = talks;
        }
    }

    private static class TalkNode {
        private String title;
        private int time;

        private TalkNode(String title, int time) {
            this.title = title;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TalkNode node = (TalkNode) o;
            return Objects.equals(title, node.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title);
        }

        private String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

    }


}
