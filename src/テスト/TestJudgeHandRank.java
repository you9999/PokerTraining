package テスト;

import java.util.Arrays;

public class TestJudgeHandRank {

	public static void main(String[] args) throws Exception {

		JudgeHandRank judgeHandRank = new JudgeHandRank();

		System.out.println("StraightFlush");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 113, 112, 111, 110, 214, 314 })));

		System.out.println("FourCard");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 113, 112, 111, 214, 314, 414 })));

		System.out.println("FullHouse");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 214, 314, 113, 213, 112, 212 })));

		System.out.println("Flush");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 103, 105, 108, 109, 110, 112 })));

		System.out.println("Straight");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 202, 103, 104, 205, 312, 412 })));

		System.out.println("ThreeCard");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 214, 314, 104, 205, 312, 413 })));

		System.out.println("TwoPare");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 214, 103, 104, 204, 312, 412 })));

		System.out.println("OnePare");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 214, 103, 104, 205, 311, 412 })));

		System.out.println("HighCard");
		System.out.println(Arrays.toString(
				judgeHandRank.judgeHandRank(new int[] { 114, 202, 103, 104, 208, 311, 412 })));

	}
}
